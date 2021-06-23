package AEDU.Pages;

import AEDU.Actions.ActionClass;
import AEDU.Actions.VerificationClass;
import AEDU.Utilities.DatabaseFunctions;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class phoneCall {

    Connection conn = null;
    String url = "jdbc:mysql://localhost:3306/";
    String dbName = "aedudev1";
    String driver1 = "com.mysql.jdbc.Driver";
    String userName = "root";
    String password = "root";
    Statement statement;
    ResultSet queryRs;

    WebDriver driver;
    ExtentTest test;

    @FindBy(how = How.XPATH, using = "/html/body/div[1]/aside/div/section/ul[2]/li[6]/a")
    private WebElement FrontOfficeMenuClick;

    @FindBy(how = How.XPATH, using = "//*[@id=\"form1\"]/div[2]/button")
    public WebElement btnSave;

    @FindBy(how = How.XPATH, using = "//*[@id=\"DataTables_Table_0\"]/tbody/tr/td[6]/a[1]")
    public WebElement btnView;

    @FindBy(how = How.XPATH, using = "//*[@id=\"DataTables_Table_0_filter\"]/label/input")
    public WebElement txtsearch;

    @FindBy(how = How.XPATH, using = "//*[@id='form1']/div[1]/div[2]/span/p")
    public WebElement lblPhone;

    @FindBy(how = How.XPATH, using = "//*[@id=\"form1\"]/div[1]/div[8]/span/p")
    public WebElement lblCalltype;

    @FindBy(how = How.XPATH, using = "//*[@id=\"form1\"]/div[1]/div[2]/input")
    public WebElement txtBoxPhone;

    @FindBy(how = How.XPATH, using = "//*[@id=\"form1\"]/div[1]/div[8]/div[2]/label[1]/input")
    public WebElement rbIncoming;

    @FindBy(how = How.XPATH, using = "//*[@id=\"form1\"]/div[1]/div[8]/div[2]/label[2]/input")
    public WebElement rbOutgoing;

    @FindBy(how = How.XPATH, using = "//*[@id=\"form1\"]/div[1]/div[1]")
    public WebElement lblAlert;

    @FindBy(how = How.XPATH, using = "//*[@id=\"form1\"]/div[1]/div[1]/input")
    public WebElement txtboxName;

    @FindBy(how = How.XPATH, using = "//*[@id=\"date\"]")
    public WebElement calendar;

    @FindBy(how = How.CLASS_NAME, using = "datepicker-switch")
    public WebElement calendar_year;

    @FindBy(how = How.CSS, using = ".datepicker-months .prev")
    public WebElement calendar_set_prev;

    @FindBy(how = How.CSS, using = ".datepicker-days .next")
    public WebElement calendar_set_next;

    @FindBy(how = How.NAME, using = "description")
    public WebElement txtboxDescription;

    @FindBy(how = How.NAME, using = "note")
    public WebElement txtBoxNote;

    @FindBy(how = How.XPATH, using = "//*[@id=\"form1\"]/div[1]/div[6]/div/div[2]/div/input")
    public WebElement btnCallduration;

    @FindBy(how = How.XPATH, using = "//*[@id=\"form1\"]/div[1]/div[6]/div/div[1]/table/tbody/tr[1]/td[1]/a")
    public WebElement incrementHour;

    @FindBy(how = How.XPATH, using = "//*[@id=\"form1\"]/div[1]/div[6]/div/div[1]/table/tbody/tr[1]/td[3]/a")
    public WebElement incrementMinute;

    @FindBy(how = How.XPATH, using = "//*[@id=\"form1\"]/div[1]/div[6]/div/div[1]/table/tbody/tr[1]/td[5]/a")
    public WebElement incrementSecond;

    @FindBy(how = How.XPATH, using = "//*[@id=\"getdetails\"]/table/tbody/tr[1]/td[1]")
    public WebElement lblView_Usercheck;

    @FindBy(how = How.XPATH, using = "//*[@id=\"follow_up_date\"]")
    public WebElement followup_Calendar;

    @FindBy(how = How.XPATH, using = "//*[@id=\"sibe-box\"]/ul[2]/li[6]/ul/li[3]/a")
    public WebElement btnPhoneCall;

    @FindBy(how = How.XPATH, using = "//*[@id=\"calldetails\"]/div/div/div[1]/button")
    public WebElement btnViewClose;

    //Global Variables
    String name="Johnny_Hacker";
    String updatedName="Addweb_Solution";
    String phone="1234567890";
    String updatedPhone="1231231230";
    String description="A detailed account of the certain or salient aspects, characteristics, or features of a subject matter or something seen, heard, or otherwise experienced or known. See also definition and explanation.Read more: http://www.businessdictionary.com/definition/description.html";
    String updatedDescription="Hello Updated description";
    String note="For zoom in, provide any value greater than 100 and for zoom out, less than 100. User can also set the size of window as per its requirement. Dimension originalDim = driver. manage().";
    String updatedNote="Hello Updated Note";
    String expectedName = "Johnny_Hacker";
    String expectedContact="1234567890";
    String expectedDescription="A detailed account of the certain or salient aspects, characteristics, or features of a subject matter or something seen, heard, or otherwise experienced or known. See also definition and explanation.Read more: http://www.businessdictionary.com/definition/description.html";
    String expectedNote="For zoom in, provide any value greater than 100 and for zoom out, less than 100. User can also set the size of window as per its requirement. Dimension originalDim = driver. manage().";
    String expected_updatedName="Addweb_Solution";
    String expected_updatedPhone="1231231230";
    String expected_updatedDescription="Hello Updated description";
    String expected_updatedNote="Hello Updated Note";

    public phoneCall(WebDriver driver, ExtentTest test)  {
        this.driver = driver;
        this.test = test;
        PageFactory.initElements(driver, this);
    }

    public void openNewTab() throws InterruptedException {
        Thread.sleep(2000);
        System.out.println("open new tab function");
        for (String childTab : driver.getWindowHandles()) {
            driver.switchTo().window(childTab);
            Thread.sleep(2000);
        }
    }

    public void checkMandatoryFields() throws IOException, InterruptedException {

        ActionClass action_class = new ActionClass(driver,test);
//        Thread.sleep(5000);
//        action_class.clickOnObject((this.FrontOfficeMenuClick));
        Thread.sleep(5000);
        action_class.clickOnObject(btnPhoneCall);
        Thread.sleep(2000);
        action_class.clickOnObject(btnSave);
        Thread.sleep(2000);
        //Check for mandatory fields
        if (lblPhone.isDisplayed()) {
            ActionClass.captureScreen("Phone Validation working");

            JavascriptExecutor js = (JavascriptExecutor) driver;
            js.executeScript("window.scrollBy(0,600)");

            if (lblCalltype.isDisplayed()) {
                ActionClass.captureScreen("Call Type validation working");
                System.out.println("validation works");
            } else {
                System.out.println("Call Type validation not displayed");
            }
        } else {
            System.out.println("Both validations not displayed");
        }
    }

    public void saveMandatory_phoneCall() throws InterruptedException, IOException {

        ActionClass action_class = new ActionClass(driver,test);
        VerificationClass verification_class = new VerificationClass(driver,test);
        Thread.sleep(5000);
        action_class.clickOnObject(btnPhoneCall);
        Thread.sleep(2000);
        action_class.setValueinTextbox(txtBoxPhone,phone);
        Thread.sleep(2000);
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollBy(0,600)");
        Thread.sleep(1000);
        action_class.clickOnObject(rbIncoming);
        Thread.sleep(1000);
        action_class.clickOnObject(btnSave);
        Thread.sleep(3000);
        verification_class.verifyTextPresent(lblAlert,"Call added successfully");
        Thread.sleep(2000);
        action_class.captureScreen("Call Log added");
    }

    public void addingPhoneCallrec() throws InterruptedException, IOException {

        ActionClass action_class = new ActionClass(driver,test);
        VerificationClass verification_class = new VerificationClass(driver,test);
//        Thread.sleep(5000);
//        action_class.clickOnObject((this.FrontOfficeMenuClick));
        Thread.sleep(5000);
        action_class.clickOnObject(btnPhoneCall);
        //Name
        action_class.setValueinTextbox(txtboxName,name);
        Thread.sleep(2000);
        //Phone
        action_class.setValueinTextbox(txtBoxPhone,phone);
        Thread.sleep(2000);
        driver.findElement(By.xpath("//*[@id='date']")).click();
        driver.findElement(By.xpath("/html/body/div[4]/div[1]/table/tbody/tr[3]/td[2]")).click();
        //Date- Calendar
        action_class.clickOnObject(calendar);
        Thread.sleep(2000);
        action_class.clickOnObject(calendar_year);
        Thread.sleep(4000);
        action_class.clickOnObject(calendar_set_prev);
        Thread.sleep(2000);
        driver.findElement(By.cssSelector(".month:nth-child(8)")).click();
        Thread.sleep(2000);
        driver.findElement(By.cssSelector("tr:nth-child(3) > .day:nth-child(5)")).click();
        //Description
        action_class.setValueinTextbox(txtboxDescription,description);
        Thread.sleep(2000);
        JavascriptExecutor jsetaskscore = (JavascriptExecutor) driver;
        jsetaskscore.executeScript("scrollBy(0, 450)");
        //Follow up- Calendar
        action_class.clickOnObject(followup_Calendar);
        Thread.sleep(2000);
        action_class.clickOnObject(calendar_set_next);
        Thread.sleep(2000);
        action_class.clickOnObject(calendar_set_next);
        Thread.sleep(2000);
        driver.findElement(By.cssSelector("tr:nth-child(3) > .day:nth-child(6)")).click();
        //Note
        action_class.setValueinTextbox(txtBoxNote,note);
        Thread.sleep(2000);
        //Call Duration
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollBy(0,200)");
        Thread.sleep(2000);
        action_class.clickOnObject(btnCallduration);
        Thread.sleep(1000);
        action_class.clickOnObject(incrementHour);
        Thread.sleep(1000);
        action_class.clickOnObject(incrementMinute);
        Thread.sleep(1000);
        action_class.clickOnObject(incrementSecond);
        Thread.sleep(1000);
        //Call Type
        action_class.clickOnObject(rbIncoming);
        Thread.sleep(1000);
        action_class.clickOnObject(btnSave);
        Thread.sleep(1000);
        verification_class.verifyTextPresent(lblAlert, "Call added successfully");;
        action_class.captureScreen("Call Log added successfully");
    }

    public void verifyingPhoneCallrec() throws InterruptedException, SQLException {
        Thread.sleep(2000);
        ActionClass actionClass=new ActionClass(driver,test);
        Thread.sleep(5000);
        actionClass.clickOnObject(btnPhoneCall);
        DatabaseFunctions DAB = new DatabaseFunctions(test);
        conn = DAB.connect();
        statement = conn.createStatement();
        String query = "SELECT * FROM general_calls WHERE name= '"+name+"'";
        System.out.println(query);
        queryRs = statement.executeQuery(query);

        while (queryRs.next()) {
            String s1,s2,s4,s3= null;
            s1 = queryRs.getString("name");
            s2=queryRs.getString("contact");
            s3=queryRs.getString("description");
            s4=queryRs.getString("note");

            System.out.println("Expected name. is " + s1);

            if (expectedName.equals(s1))
            {
                System.out.println("User Matched");
                test.log(Status.PASS,"User Name Matched");

                if (expectedContact.equals(s2)){
                    System.out.println("Contact matched");
                    test.log(Status.PASS,"Contact no. Matched");

                    if (expectedDescription.equals(s3)){
                        System.out.println("Description matched");
                        test.log(Status.PASS,"Description Matched");

                        if(expectedNote.equals(s4)){
                            System.out.println("Note matched");
                            test.log(Status.PASS,"Note Matched");
                        }else
                                {
                                    System.out.println("Note not matched");
                                    test.log(Status.FAIL,"Note not Matched");
                                }
                        }else{
                            System.out.println("Description not match");
                        test.log(Status.FAIL,"Description not Matched");
                            }
                }else
                    {
                    System.out.println("Contact not match");
                        test.log(Status.FAIL,"Contact not Matched");
                    }
            } else
                {
                System.out.println("Please check the users");
                test.log(Status.FAIL,"User not Matched");
            }
        }
   }

    public void viewing_phoneCallrec() throws InterruptedException, IOException {
        ActionClass ActionClass = new ActionClass(driver,test);
        VerificationClass verification_class = new VerificationClass(driver,test);
//        Thread.sleep(5000);
//        ActionClass.clickOnObject((this.FrontOfficeMenuClick));
        Thread.sleep(5000);
        ActionClass.clickOnObject(btnPhoneCall);
        //Click and Verify the record
        Thread.sleep(2000);
        List<WebElement> ListRecord = driver.findElements(By.xpath("//*[@id=\"DataTables_Table_0\"]/tbody/tr"));
        int RecordSize = ListRecord.size();
        for (int i = 1; i <= RecordSize; i++) {
            String s = driver.findElement(By.xpath("//*[@id=\"DataTables_Table_0\"]/tbody/tr[" + i + "]/td[1]")).getText();
            System.out.println("Value in list is: " + s);
            test.log(Status.PASS,"Going to view the Phonecall Log of: " +s);
            ActionClass.captureScreen("Log_Front");
            ActionClass.setValueinTextbox(txtsearch,name);
            ActionClass.clickOnObject(btnView);
            test.log(Status.PASS,"Going to view the Phonecall Log of");
            Thread.sleep(2000);
            String match = driver.findElement(By.xpath("//*[@id=\"getdetails\"]/table/tbody/tr[1]/td[1]")).getText();
            if (s.equals(match)) {
                Thread.sleep(5000);
                System.out.println("Element Found for View");
                test.log(Status.PASS,"Correct View of Phonecall Log");
                Thread.sleep(2000);
                ActionClass.captureScreen("Verified");
                ActionClass.clickOnObject(btnViewClose);
                break;
            }else
            {
                System.out.println("Element not found");
                test.log(Status.FAIL,"Incorrect View of Phonecall Log");
            }
        }

    }

    public void updating_phoneCall() throws InterruptedException, IOException {
        ActionClass action_class = new ActionClass(driver, test);
        VerificationClass verification_class = new VerificationClass(driver, test);
//        Thread.sleep(5000);
//        action_class.clickOnObject((this.FrontOfficeMenuClick));
        Thread.sleep(5000);
        action_class.clickOnObject(btnPhoneCall);
        Thread.sleep(2000);
        List<WebElement> ListRecords = driver.findElements(By.xpath("//*[@id=\"DataTables_Table_0\"]/tbody/tr"));
        int RecordsSize = ListRecords.size();
        for (int i = 1; i <= RecordsSize; i++) {
            String s = driver.findElement(By.xpath("//*[@id='DataTables_Table_0']/tbody/tr[" + i + "]/td[1]")).getText();

            if (s.equals(name)) {
                System.out.println("Element Found for Edit in Row[" +i+1+ "]");
                Thread.sleep(2000);
                driver.findElement(By.xpath("//*[@id='DataTables_Table_0']/tbody/tr[" + i + "]/td[6]/a[2]")).click();
                Thread.sleep(2000);
                action_class.captureScreen("Fields Filled for update");
                break;
            }
            else
            {
                System.out.println("Element not found in Row[" +i+1+ "]");
            }
        }
        action_class.setValueinTextbox_update(txtboxName,updatedName);
        Thread.sleep(3000);
        action_class.setValueinTextbox_update(txtBoxPhone,updatedPhone);
        Thread.sleep(2000);
        action_class.clickOnObject(calendar);
        Thread.sleep(2000);
        action_class.clickOnObject(calendar_year);
        Thread.sleep(4000);
        action_class.clickOnObject(calendar_set_prev);
        Thread.sleep(2000);
        driver.findElement(By.cssSelector(".month:nth-child(8)")).click();
        Thread.sleep(2000);
        driver.findElement(By.cssSelector("tr:nth-child(3) > .day:nth-child(4)")).click();
        Thread.sleep(2000);
        action_class.setValueinTextbox_update(txtboxDescription,updatedDescription);
        Thread.sleep(3000);
        //Follow up- Calendar
        action_class.clickOnObject(followup_Calendar);
        Thread.sleep(2000);
        action_class.clickOnObject(calendar_set_next);
        Thread.sleep(2000);
        action_class.clickOnObject(calendar_set_next);
        Thread.sleep(2000);
        driver.findElement(By.cssSelector("tr:nth-child(3) > .day:nth-child(5)")).click();
        Thread.sleep(2000);
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollBy(0,600)");
        action_class.setValueinTextbox_update(txtBoxNote,updatedNote);
        Thread.sleep(2000);
        action_class.clickOnObject(rbOutgoing);
        Thread.sleep(1000);
        action_class.clickOnObject(btnSave);
        Thread.sleep(2000);
        verification_class.verifyTextPresent(lblAlert,"Call detail is updated successfully");
        action_class.captureScreen("Details updated Success");
    }

    public void verify_updated_phoneCall() throws SQLException, InterruptedException {
        ActionClass action_class = new ActionClass(driver, test);
//        Thread.sleep(5000);
//        action_class.clickOnObject((this.FrontOfficeMenuClick));
        Thread.sleep(5000);
        action_class.clickOnObject(btnPhoneCall);
        DatabaseFunctions DAB = new DatabaseFunctions(test);
        conn = DAB.connect();
        statement = conn.createStatement();
        String query = "SELECT * FROM general_calls WHERE name= '"+ updatedName+"'";
        System.out.println(query);
        queryRs = statement.executeQuery(query);
        System.out.println(queryRs);
        while (queryRs.next()) {
            String s1,s2,s4,s3 = null;
            s1 = queryRs.getString("name");
            s2=queryRs.getString("contact");
            s3=queryRs.getString("description");
            s4=queryRs.getString("note");
            System.out.println("Expected name. is"+s1);
            if (expected_updatedName.equals(s1))
            {
                test.log(Status.PASS,"Updated User Name Matched");
                System.out.println("Updated User Matched");
                if (expected_updatedPhone.equals(s2)){
                    test.log(Status.PASS,"Updated Contact Matched");
                    System.out.println("Updated Contact matched");
                    if (expected_updatedDescription.equals(s3)){
                        test.log(Status.PASS,"Updated Description Matched");
                        System.out.println("Updated Description matched");
                        if(expected_updatedNote.equals(s4)){
                            test.log(Status.PASS,"Updated Note Matched");
                            System.out.println("Updated Note matched");
                        }else
                        {
                            test.log(Status.PASS,"Updated Note not Matched");
                            System.out.println("Updated Note not matched");
                        }
                    }else{
                        test.log(Status.PASS,"Updated Description not Matched");
                        System.out.println("Updated Description not match");
                    }
                }else
                {
                    test.log(Status.PASS,"Updated Contact not Matched");
                    System.out.println("Updated Contact not match");
                }
            } else
            {
                test.log(Status.PASS,"Updated User not Matched");
                System.out.println("Updated Please check the users");
            }
        }
    }

    public void delete_phoneCall() throws IOException, InterruptedException {
        ActionClass action_class = new ActionClass(driver, test);
        VerificationClass verification_class = new VerificationClass(driver, test);
//        Thread.sleep(5000);
//        action_class.clickOnObject((this.FrontOfficeMenuClick));
        Thread.sleep(5000);
        action_class.clickOnObject(btnPhoneCall);
        List<WebElement> ListRecord = driver.findElements(By.xpath("//*[@id=\"DataTables_Table_0\"]/tbody/tr"));
        int Recordsize = ListRecord.size();
        for (int i = 1; i <= Recordsize; i++) {
            String s = driver.findElement(By.xpath("//*[@id=\"DataTables_Table_0\"]/tbody/tr[" + i + "]/td[1]")).getText();
            System.out.println("Value in list is: " + s);
            if (s.equals(updatedName)) {
                System.out.println("Element Found for Delete");
                Thread.sleep(2000);
                driver.findElement(By.xpath("//*[@id=\"DataTables_Table_0\"]/tbody/tr[" + i + "]/td[6]/a[3]")).click();
                Thread.sleep(2000);
                driver.switchTo().alert().accept();
                Thread.sleep(2000);
                verification_class.verifyTextPresent(lblAlert, "Call deleted successfully");
                action_class.captureScreen("User_Deleted");
                Thread.sleep(3000);
                break;
            }else
            {
                System.out.println("Element not found");
            }
        }
    }

    public void verifying_DeletefromDB() throws InterruptedException, SQLException {
        Thread.sleep(2000);
        DatabaseFunctions DAB = new DatabaseFunctions(test);
        conn = DAB.connect();
        statement = conn.createStatement();
        String query = "SELECT * FROM general_calls WHERE name='"+updatedName+"'";
        System.out.println(query);
        queryRs = statement.executeQuery(query);
        while (queryRs.next()) {
            String s1= null;
            s1 = queryRs.getString("name");
            System.out.println("Expected name. is " + s1);
            if (expectedName.equals(s1))
            {
                System.out.println("User Matched");
                test.log(Status.FAIL,"Data Still Present");
            } else
            {
                System.out.println("Please check the users");
                test.log(Status.PASS,"Data Not Present");
            }
        }
    }
}