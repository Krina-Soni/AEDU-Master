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
import org.openqa.selenium.support.ui.Select;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

public class reception_page {

    WebDriver driver;
    ExtentTest test;
    Connection conn = null;
    Statement statement;
    ResultSet queryRs;

    @FindBy(how = How.XPATH, using = "//*[@id=\"form-username\"]")
    public WebElement txtBox_login_username;

    @FindBy(how = How.XPATH, using = "//*[@id='form-username']")
    public WebElement txtBox_receptionL_username;

    @FindBy(how = How.XPATH, using = "//*[@id=\"form-password\"]")
    public WebElement txtBox_login_password;

    @FindBy(how = How.XPATH, using = "/html/body/div[1]/div/div/div[2]/div/div/div[2]/form[1]/button")
    public WebElement btnLogin;

    @FindBy(how = How.XPATH, using = "//*[@id=\"form1\"]/div[2]/button")
    public WebElement btnSave;

    @FindBy(how = How.CSS, using = "#form1 > div.box-footer > button")
    public WebElement updateBTNsave;

    @FindBy(how = How.XPATH, using = "//*[@id=\"form1\"]/div[1]/div[1]/span/p")
    public WebElement lblBlank_Save;

    @FindBy(how = How.XPATH, using = "//*[@id=\"form1\"]/div[1]/div[2]/span/p")
    public WebElement lblBlank_Email;

    @FindBy(how = How.XPATH, using = "//*[@id=\"form1\"]/div[1]/div[3]/span/p")
    public WebElement lblBlank_Gender;

    @FindBy(how = How.XPATH, using = "//*[@id=\"form1\"]/div[1]/div[4]/span/p")
    public WebElement lblBlank_DOB;

    @FindBy(how = How.XPATH, using = "//*[@id=\"form1\"]/div[1]/div[6]/span/p")
    public WebElement lblBlank_Phone;

    @FindBy(how = How.NAME, using = "name")
    public WebElement txtName;

    @FindBy(how = How.NAME, using = "email")
    public WebElement txtEmail;

    @FindBy(how = How.CSS, using = "#dob")
    public WebElement btnDOB;

    @FindBy(how = How.XPATH, using = "/html/body/div[3]/div[1]/div/table/tbody/tr[4]/td[2]")
    public WebElement btnDOB_Date;

    @FindBy(how = How.NAME, using = "address")
    public WebElement txtAddress;

    @FindBy(how = How.NAME, using = "phone")
    public WebElement txtPhone;

    @FindBy(how = How.XPATH, using = "//*[@id='scheduleModal']/div/div/div[2]/div/p")
    public WebElement lblView_name;

    @FindBy(how = How.XPATH, using = "/html/body/div[1]/div[1]/section[2]/div/div/div/div/h3/small/a")
    public WebElement btnLogin_Details;

    @FindBy(how = How.XPATH, using = "/html/body/div[1]/div[1]/section[2]/div/div/div/div/h3/small/a")
    public WebElement lbl_Valid_user_Login;

    @FindBy(how = How.XPATH, using = "//*[@id=\"form1\"]/div[1]/div[1]")
    public WebElement lbl_record_deleted;

    @FindBy(how = How.CLASS_NAME, using = "next")
    public WebElement btnNext_calendar;

    @FindBy(how = How.CLASS_NAME, using = "day")
    public WebElement btnDay;

    @FindBy(how = How.XPATH, using = "//*[@id='form1']/div[1]/div[7]/div/input")
    public WebElement Image;

   @FindBy(how = How.CLASS_NAME, using = "prev")
    public WebElement btnPrev;

   @FindBy(how = How.XPATH, using = "//*[@id=\"form1\"]/div[1]/div[1]")
    public WebElement lblAddReceptionist;

    @FindBy(how = How.XPATH, using = "//*[@id='scheduleModal']/div/div/div[3]/button")
    public WebElement btnCancel;

    @FindBy(how = How.XPATH, using = "//*[@id='employeeform']/div[1]/div[3]/select")
    public WebElement btnGender;

    @FindBy(how = How.XPATH, using = "//*[@id=\"sibe-box\"]/ul[2]/li[6]/a")
    public WebElement btnFrontOffice;

    @FindBy(how = How.XPATH, using = "//*[@id=\"form1\"]/div[1]/div[2]/span/p")
    public WebElement lblDuplicateEmail;

    @FindBy(how = How.XPATH, using = "//*[@id=\"form1\"]/div[1]/div[6]/span/p")
    public WebElement lblDuplicatePhone;

    @FindBy(how = How.XPATH, using = "//*[@id=\"sibe-box\"]/ul[2]/li[6]/ul/li[8]/a")
    public WebElement btnReceptionist;

    @FindBy(how = How.XPATH, using = "//*[@id=\"employeeform\"]/div[2]/button")
    public WebElement btnUpdateSave;

        public reception_page(WebDriver driver, ExtentTest test) {
            this.driver = driver;
            this.test = test;
            PageFactory.initElements(driver, this);
        }

        //Global Variables
        String email = "akash.m.addweb@gmail.com";
        String updated_email = "johnnyharpertesting@gmail.com";
        String name = "Johnny_Harper";
        String updatedName="Updated Admin";

        public void openNewTab() throws InterruptedException {
        Thread.sleep(2000);
        ActionClass action_class = new ActionClass(driver, test);
        System.out.println("open new tab function");
        for(String childTab : driver.getWindowHandles()){
        driver.switchTo().window(childTab);
        }
    }

        public void blankSave_Receptionist() throws InterruptedException, IOException {
            ActionClass action_class = new ActionClass(driver, test);
            VerificationClass verification_class = new VerificationClass(driver, test);
            Thread.sleep(5000);
            action_class.clickOnObject(btnFrontOffice);
            Thread.sleep(5000);
            action_class.clickOnObject(btnReceptionist);
            Thread.sleep(5000);
            JavascriptExecutor js = (JavascriptExecutor) driver;
            js.executeScript("window.scrollBy(0,600)");
            action_class.clickOnObject(updateBTNsave);
            Thread.sleep(2000);
            verification_class.verifyTextPresent(lblBlank_Save, "The Reception field is required.");
            Thread.sleep(2000);
            action_class.captureScreen("Blank_Save_Reception");
        }

        public void Mandatory_fields() throws InterruptedException, IOException {

            ActionClass action_class = new ActionClass(driver, test);
            VerificationClass verification_class = new VerificationClass(driver, test);
            //Blank Name and Valid name
//            Thread.sleep(5000);
//            action_class.clickOnObject(btnFrontOffice);
            Thread.sleep(5000);
            action_class.clickOnObject(btnReceptionist);
            Thread.sleep(5000);
            Thread.sleep(5000);
            action_class.clickOnObject(updateBTNsave);
            Thread.sleep(1000);
            action_class.captureScreen("InValid Name");
            action_class.setValueinTextbox(txtName, "Johnny_Harper");
            verification_class.verifyTextPresent(lblBlank_Save, "The Reception field is required.");
            Thread.sleep(1000);
            action_class.clickOnObject(updateBTNsave);
            action_class.captureScreen("ValidName");

            //Blank Email and valid email
            action_class.clickOnObject(updateBTNsave);
            action_class.captureScreen("InValid Email");
            Thread.sleep(1000);
            verification_class.verifyTextPresent(lblBlank_Email, "The Email field is required.");
            action_class.setValueinTextbox(txtEmail, email);
            Thread.sleep(1000);
            action_class.clickOnObject(updateBTNsave);
            action_class.captureScreen("Valid Email");
           //Blank and Valid Gender selection
                Thread.sleep(3000);
                action_class.clickOnObject(updateBTNsave);
                action_class.captureScreen("Invalid Gender");
                Thread.sleep(2000);
                verification_class.verifyTextPresent(lblBlank_Gender, "The Gender field is required.");
                Select drpdownGender = new Select(driver.findElement(By.name("gender")));
                drpdownGender.selectByVisibleText("Male");
                Thread.sleep(1000);

            //Blank DOB and Valid DOB
                Thread.sleep(2000);
                action_class.clickOnObject(updateBTNsave);
                verification_class.verifyTextPresent(lblBlank_DOB, "The Date of Birth field is required.");
                action_class.captureScreen("Invalid_DOB");
                Thread.sleep(5000);
                action_class.clickOnObject(btnDOB);
                Thread.sleep(5000);
                Select dob_year = new Select(driver.findElement(By.cssSelector("body > div.daterangepicker.dropdown-menu.single.opensright.show-calendar > div.calendar.first.single.right > div > table > thead > tr:nth-child(1) > th.month > select.yearselect")));
                dob_year.selectByVisibleText("1992");
                Thread.sleep(5000);
                Select dob_month = new Select(driver.findElement(By.cssSelector("body > div.daterangepicker.dropdown-menu.single.opensright.show-calendar > div.calendar.first.single.right > div > table > thead > tr:nth-child(1) > th.month > select.monthselect")));
                dob_month.selectByVisibleText("Feb");

                JavascriptExecutor js = (JavascriptExecutor) driver;
                js.executeScript("window.scrollBy(0,600)");

                Thread.sleep(2000);
                action_class.clickOnObject(btnDOB_Date);
                js.executeScript("window.scrollBy(0,600)");
            //Adding Address
            action_class.setValueinTextbox(txtAddress, "619, 619,avnrdblr-2, Avenue Road\n" + "\n" + "Bangalore, Karnataka, 560002\n" +
                    "\n" + "22280358");
            //Blank Phone and Valid Phone
            js.executeScript("window.scrollBy(0,600)");
            action_class.clickOnObject(updateBTNsave);
            Thread.sleep(2000);
            verification_class.verifyTextPresent(lblBlank_Phone, "The Phone field is required.");
            js.executeScript("window.scrollBy(0,600)");
            action_class.captureScreen("Blank Phone");
            action_class.setValueinTextbox(txtPhone, "9876543210");
            //Uploading Image
            js.executeScript("window.scrollBy(0,600)");
            Thread.sleep(2000);
//            WebElement element = driver.findElement(By.xpath("//*[@id='form1']/div[1]/div[7]/div/input"));
////            element.sendKeys("/Users/Addweb-24/Downloads/IMG_20200312_182631.jpg");
//            action_class.setValueinTextbox(this.Image,"/Users/Addweb-24/Downloads/IMG_20200312_182631.jpg");
            Thread.sleep(2000);
            action_class.clickOnObject(updateBTNsave);
            Thread.sleep(2000);
            action_class.captureScreen("Saved");
        }

        public void addValidRecord() throws InterruptedException, IOException {

            ActionClass action_class = new ActionClass(driver, test);
            VerificationClass verification_class = new VerificationClass(driver, test);
//            Thread.sleep(5000);
//            action_class.clickOnObject(btnFrontOffice);
            Thread.sleep(5000);
            action_class.clickOnObject(btnReceptionist);
            Thread.sleep(5000);
            action_class.setValueinTextbox(txtName,"Peter Parker");
            Thread.sleep(1000);
            action_class.setValueinTextbox(txtEmail,"johnnyharpertesting71@gmail.com");
            Thread.sleep(1000);
            Select drpdownGender = new Select(driver.findElement(By.name("gender")));
            drpdownGender.selectByVisibleText("Male");
            Thread.sleep(1000);
            action_class.clickOnObject(btnDOB);
            Select dob_year = new Select(driver.findElement(By.xpath("/html/body/div[3]/div[1]/div/table/thead/tr[1]/th[2]/select[2]")));
            dob_year.selectByVisibleText("1993");
            Thread.sleep(2000);
            Select dob_month = new Select(driver.findElement(By.xpath("/html/body/div[3]/div[1]/div/table/thead/tr[1]/th[2]/select[1]")));
            dob_month.selectByVisibleText("Jan");
            JavascriptExecutor js = (JavascriptExecutor) driver;
            js.executeScript("window.scrollBy(0,600)");
            Thread.sleep(2000);
            action_class.clickOnObject(btnDOB_Date);
            js.executeScript("window.scrollBy(0,600)");
            Thread.sleep(1000);
            action_class.setValueinTextbox(txtAddress,"this is a test address");
            Thread.sleep(1000);
            action_class.setValueinTextbox(txtPhone,"7228823533");
            Thread.sleep(1000);
            action_class.clickOnObject(btnSave);
            verification_class.verifyTextPresent(lblAddReceptionist,"Reception added successfully");
            action_class.captureScreen("All valid record");
    }

        public void duplicateEmail() throws InterruptedException, IOException {

            ActionClass action_class = new ActionClass(driver, test);
            VerificationClass verification_class = new VerificationClass(driver, test);
//            Thread.sleep(5000);
//            action_class.clickOnObject(btnFrontOffice);
            Thread.sleep(5000);
            action_class.clickOnObject(btnReceptionist);
            Thread.sleep(5000);
            action_class.setValueinTextbox(txtEmail, email);
            Thread.sleep(1000);
            action_class.clickOnObject(btnSave);
            Thread.sleep(2000);
            verification_class.verifyTextPresent(lblDuplicateEmail,"The Email field must contain a unique value.");
            Thread.sleep(2000);
            action_class.captureScreen("Unique Email");
            action_class.clickOnObject(btnSave);
            Thread.sleep(2000);

        }

        public void duplicatePhone() throws InterruptedException, IOException {

            ActionClass action_class = new ActionClass(driver, test);
            VerificationClass verification_class = new VerificationClass(driver, test);
//            Thread.sleep(5000);
//            action_class.clickOnObject(btnFrontOffice);
            Thread.sleep(5000);
            action_class.clickOnObject(btnReceptionist);
            Thread.sleep(5000);
            action_class.setValueinTextbox(txtPhone,"7228823533");
            Thread.sleep(1000);
            action_class.clickOnObject(btnSave);
            Thread.sleep(2000);
            verification_class.verifyTextPresent(lblDuplicatePhone,"The Phone field must contain a unique value.");
            Thread.sleep(2000);
            JavascriptExecutor js = (JavascriptExecutor) driver;
            js.executeScript("window.scrollBy(0,600)");
            action_class.captureScreen("Unique Phone");
            action_class.clickOnObject(btnSave);
            Thread.sleep(2000);

        }

        public void Viewing_record() throws IOException, InterruptedException, SQLException {

            ActionClass action_class = new ActionClass(driver, test);
            VerificationClass verification_class = new VerificationClass(driver, test);
//            Thread.sleep(5000);
//            action_class.clickOnObject(btnFrontOffice);
            Thread.sleep(5000);
            action_class.clickOnObject(btnReceptionist);
            Thread.sleep(5000);
            List<WebElement> ListRecord = driver.findElements(By.xpath("//*[@id='DataTables_Table_0']/tbody/tr"));
            int RecordSize = ListRecord.size();
            System.out.println(RecordSize);
            DatabaseFunctions DAB = new DatabaseFunctions(test);
            conn = DAB.connect();
            statement = conn.createStatement();
            for (int i = 1; i <= RecordSize; i++) {
                String s = driver.findElement(By.xpath("//*[@id=\"DataTables_Table_0\"]/tbody/tr[" + i + "]/td[1]")).getText();
                System.out.println("Value in list is: " + s);

                if (s.equals(name)) {
                    System.out.println("Element Found for View");
                    Thread.sleep(2000);
                    driver.findElement(By.xpath("//*[@id=\"DataTables_Table_0\"]/tbody/tr["+i+"]/td[5]/a[1]")).click();
                    Thread.sleep(2000);
                    action_class.clickOnObject(btnLogin_Details);
                    Thread.sleep(2000);
                    verification_class.verifyTextPresent(lblView_name,name);
                    Thread.sleep(2000);
                    action_class.captureScreen("Valid View Data");
                    action_class.clickOnObject(btnCancel);
                    Thread.sleep(2000);
                    break;
                } else {
                    System.out.println("Element not found");
                }
            }
    }

    public void Update_fields() throws InterruptedException, IOException {
        ActionClass action_class = new ActionClass(driver, test);
//        Thread.sleep(5000);
//        action_class.clickOnObject(btnFrontOffice);
        Thread.sleep(5000);
        action_class.clickOnObject(btnReceptionist);
        Thread.sleep(5000);
            Thread.sleep(2000);
            List<WebElement> ListRecords = driver.findElements(By.xpath("//*[@id=\"DataTables_Table_0\"]/tbody/tr"));
            int RecordsSize = ListRecords.size();
            for (int i = 1; i <= RecordsSize; i++) {
                String s = driver.findElement(By.xpath("//*[@id='DataTables_Table_0']/tbody/tr[" + i + "]/td[1]")).getText();

                if (s.equals(name)) {
                    System.out.println("Element Found for Edit in Row[" +i+1+ "]");
                    Thread.sleep(2000);
                    driver.findElement(By.xpath("//*[@id='DataTables_Table_0']/tbody/tr[" + i + "]/td[5]/a[2]")).click();
                    Thread.sleep(2000);
                    action_class.captureScreen("Fields Filled for update");
                    break;
                }
                else
                {
                    System.out.println("Element not found in Row[" +i+1+ "]");
                }
            }
            action_class.updateValueinTextbox(txtName, "Updated Admin");
            Thread.sleep(2000);
            action_class.updateValueinTextbox(txtEmail, updated_email);
            Thread.sleep(3000);
            //Gender
            Thread.sleep(2000);
            Select drpdownGender = new Select(driver.findElement(By.name("gender")));
            drpdownGender.selectByVisibleText("Female");
            JavascriptExecutor js = (JavascriptExecutor) driver;
            js.executeScript("window.scrollBy(0,700)");
            Thread.sleep(2000);
            action_class.updateValueinTextbox(txtAddress, "Besides Bikes Auto Hero Honda Showroom, Vijay Cross Roads, Navrangpura, Ahmedabad, Gujarat 380009");
            Thread.sleep(1000);
            action_class.updateValueinTextbox(txtPhone, "1478523690");
            Thread.sleep(2000);
            driver.findElement(By.xpath("//*[@id=\"employeeform\"]/div[2]/button")).click();
            Thread.sleep(2000);
            test.log(Status.PASS,"Data updated Successfully");
    }

        public void verifying_DatafromDB() throws SQLException, InterruptedException {
            ActionClass action_class = new ActionClass(driver, test);
//            Thread.sleep(5000);
//            action_class.clickOnObject(btnFrontOffice);
            Thread.sleep(5000);
            action_class.clickOnObject(btnReceptionist);
            Thread.sleep(5000);
            DatabaseFunctions DAB = new DatabaseFunctions(test);
            conn = DAB.connect();
            statement = conn.createStatement();
            String query = "SELECT * FROM reception WHERE name='"+updatedName+"'";
            System.out.println(query);
            queryRs = statement.executeQuery(query);

            while (queryRs.next()) {
                String s1 = null;
                s1 = queryRs.getString("name");
                System.out.println("Expected name. is " + s1);

                if (updatedName.equals(s1)) {
                    System.out.println("User Matched");
                    test.log(Status.PASS, "Details Matched");
                } else {
                    System.out.println("User Matched");
                    test.log(Status.FAIL, "Data Not Present");
                }
            }
        }

        public void Login_with_Receptionist() throws InterruptedException, SQLException {

            ActionClass action_class = new ActionClass(driver, test);
            //Thread.sleep(5000);
            //action_class.clickOnObject(btnFrontOffice);
            Thread.sleep(5000);
            action_class.clickOnObject(btnReceptionist);
            Thread.sleep(5000);
            driver.get("http://localhost/addwebsms/site/login?login_name=reception");
            DatabaseFunctions DAB = new DatabaseFunctions(test);
            conn = DAB.connect();
            statement = conn.createStatement();
            String query = "SELECT email,password FROM reception WHERE email= '" + updated_email + "'";
            queryRs = statement.executeQuery(query);

            while (queryRs.next()) {
                String s1, s2 = null;
                s1 = queryRs.getString("email");
                s2 = queryRs.getString("password");
                System.out.println(s1 + " "+ s2);

                if (s1 == null) {
                    System.out.println("username NOT found");
                    if (s2 == null)
                        System.out.println("password NOT found");
                }
                    action_class.setValueinTextbox(txtBox_receptionL_username, s1);
                    action_class.setValueinTextbox(txtBox_login_password, s2);

                Thread.sleep(2000);
                action_class.clickOnObject(btnLogin);
            }
        }

        public void check_username() throws InterruptedException, IOException, SQLException {

            ActionClass action_class=new ActionClass(driver,test);
            //Thread.sleep(5000);
            //action_class.clickOnObject(btnFrontOffice);
            Thread.sleep(5000);
            action_class.clickOnObject(btnReceptionist);
            VerificationClass verification_class=new VerificationClass(driver, test);
            driver.get("http://localhost/addwebsms/site/login?login_name=reception");
            DatabaseFunctions DAB = new DatabaseFunctions(test);
            conn = DAB.connect();
            statement = conn.createStatement();
            String query = "SELECT email,password FROM reception WHERE email='"+updated_email+"'";
            System.out.println(query);
            queryRs = statement.executeQuery(query);
            System.out.println(queryRs);

            while (queryRs.next()) {
                String s1, s2 = null;
                s1 = queryRs.getString("email");
                s2 = queryRs.getString("password");

                action_class.setValueinTextbox(txtBox_login_username, s1);
                action_class.setValueinTextbox(txtBox_login_password, s2);
                Thread.sleep(2000);
                action_class.clickOnObject(btnLogin);
                Thread.sleep(2000);
                verification_class.verifyTextPresent(lbl_Valid_user_Login, s1);
                Thread.sleep(2000);
                action_class.captureScreen("Valid_user_login");
            }
        }

        public void delete_new_record() throws InterruptedException, IOException, SQLException {

            ActionClass action_class = new ActionClass(driver, test);
            VerificationClass verification_class = new VerificationClass(driver, test);
//            driver.get("http://localhost/addwebsms/site/login?login_name=Admin");
//            action_class.setValueinTextbox(txtBox_login_username, "addwebsolution@gmail.com");
//            action_class.setValueinTextbox(txtBox_login_password, "addweb123");
//            action_class.clickOnObject(btnLogin);
//            action_class.clickOnObject(btnReceptionist);
            //Thread.sleep(5000);
            //action_class.clickOnObject(btnFrontOffice);
            Thread.sleep(5000);
            action_class.clickOnObject(btnReceptionist);
            //Accepting the Alert
            List<WebElement> ListRecord = driver.findElements(By.xpath("//*[@id=\"DataTables_Table_0\"]/tbody/tr"));
            int Recordsize = ListRecord.size();

            for (int i = 1; i <= Recordsize; i++) {
                String s = driver.findElement(By.xpath("//*[@id=\"DataTables_Table_0\"]/tbody/tr[" + i + "]/td[1]")).getText();
                System.out.println("Value in list is: " + s);

                if (s.equals(updatedName)) {
                    System.out.println("Element Found for Delete");
                    Thread.sleep(2000);
                    driver.findElement(By.xpath("//*[@id=\"DataTables_Table_0\"]/tbody/tr[" + i + "]/td[5]/a[3]")).click();
                    Thread.sleep(2000);
                    driver.switchTo().alert().accept();
                    Thread.sleep(2000);
                    verification_class.verifyTextPresent(lbl_record_deleted, "Reception deleted successfully");
                    action_class.captureScreen("User_Deleted");
                    Thread.sleep(3000);
                    break;
                } else {
                    System.out.println("Element not found");
                }
            }
            DatabaseFunctions DAB = new DatabaseFunctions(test);
            conn = DAB.connect();
            statement = conn.createStatement();
            String query = "SELECT name FROM reception WHERE email='"+updated_email+"'";
            System.out.println(query);
            queryRs = statement.executeQuery(query);
            System.out.println(queryRs);

            while (queryRs.next()) {
                String s1 = null;
                s1 = queryRs.getString("name");
                if (s1.equals(null)){
                    test.log(Status.PASS, "Record Deleted from The database");
                }
            }
        }

        public void verifying_DeletefromDB() throws InterruptedException, SQLException {
        ActionClass action_class = new ActionClass(driver, test);
            //Thread.sleep(5000);
            //action_class.clickOnObject(btnFrontOffice);
            Thread.sleep(5000);
            action_class.clickOnObject(btnReceptionist);
        Thread.sleep(2000);
        DatabaseFunctions DAB = new DatabaseFunctions(test);
        conn = DAB.connect();
        statement = conn.createStatement();
        String query = "SELECT * FROM reception WHERE name='"+updatedName+"'";
        System.out.println(query);
        queryRs = statement.executeQuery(query);

        while (queryRs.next()) {
            String s1= null;
            s1 = queryRs.getString("name");

            System.out.println("Expected name. is " + s1);

            if (updatedName.equals(s1))
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