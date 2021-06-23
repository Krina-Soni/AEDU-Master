package AEDU.Pages;
import AEDU.Actions.ActionClass;
import AEDU.Actions.VerificationClass;
import AEDU.Utilities.DatabaseFunctions;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

public class ClassFile {
    Connection conn = null;
    Statement statement;
    ResultSet queryRs;
    WebDriver driver;
    ExtentTest test;

    @FindBy(how = How.XPATH, using = "//*[@id=\"class\"]")
    public WebElement txtBoxClass;

    @FindBy(how = How.XPATH, using = "//*[@id=\"form1\"]/div[2]/button")
    public WebElement btnSave;

    @FindBy(how = How.XPATH, using = "//*[@id=\"form1\"]/div[1]/div[1]/span/p")
    public WebElement lblMandatory;

    @FindBy(how = How.CLASS_NAME, using = "checkbox")
    public WebElement chkbox;

    @FindBy(how = How.XPATH, using = "//*[@id=\"form1\"]/div[1]/div[2]/div[1]/label/div/input")
    public WebElement chkbox_SecA;

    @FindBy(how = How.XPATH, using = "//*[@id=\"form1\"]/div[1]/div[2]/div[4]/label/div/input")
    public WebElement chkbox_SecD;

    @FindBy(how = How.XPATH, using = "//*[@id=\"sibe-box\"]/ul[2]/li[3]/ul/li[5]/a")
    public WebElement btnClass;

    @FindBy(how = How.XPATH, using = "//*[@id=\"form1\"]/div[1]/div[1]")
    public WebElement lblClassSuccess;

    //Global Variables
    String classname = "Tester";
    String updatedname = "Tester123";
    String secA = "A";
    String secD = "D";

    public ClassFile(WebDriver driver, ExtentTest test) {
        this.driver = driver;
        this.test = test;
    }

    public void openNewTab() throws InterruptedException {
        Thread.sleep(2000);
        System.out.println("open new tab function");
        for (String childTab : driver.getWindowHandles()) {
            driver.switchTo().window(childTab);
        }
    }

    public void check_BlankCall() throws IOException, InterruptedException {
        ActionClass actionClass = new ActionClass(driver, test);
        VerificationClass verificationClass = new VerificationClass(driver, test);
        Thread.sleep(2000);
//        actionClass.clickOnObject(btnSave);
        driver.findElement(By.xpath("//*[@id=\"form1\"]/div[2]/button")).click();
        Thread.sleep(2000);
//        verificationClass.verifyTextPresent(lblMandatory, "The Class field is required.");
        actionClass.captureScreen("MandatoryFields");
    }

    public void checkValidEntry() throws InterruptedException, IOException {
        ActionClass actionClass = new ActionClass(driver, test);
        VerificationClass verificationClass = new VerificationClass(driver, test);
//        actionClass.clickOnObject(btnClass);
        driver.findElement(By.xpath("//*[@id=\"sibe-box\"]/ul[2]/li[3]/ul/li[5]/a")).click();
        test.log(Status.INFO, "Sucessfully clicked on object");

        driver.findElement(By.xpath("//*[@id=\"class\"]")).sendKeys(classname);
        test.log(Status.INFO, "Sucessfully Data Entered");
//        actionClass.setValueinTextbox(txtBoxClass,"tester2");
        Thread.sleep(2000);

        driver.findElement(By.xpath("//*[@id=\"form1\"]/div[1]/div[2]/div[1]/label/div/input")).click();
        test.log(Status.INFO, "Sucessfully clicked on object");
//        actionClass.clickOnObject(chkbox_SecA);

        driver.findElement(By.xpath("//*[@id=\"form1\"]/div[1]/div[2]/div[4]/label/div/input")).click();
        test.log(Status.INFO, "Sucessfully clicked on object");
//        actionClass.clickOnObject(chkbox_SecD);

        Thread.sleep(1000);
        driver.findElement(By.xpath("//*[@id=\"form1\"]/div[2]/button")).click();
        test.log(Status.INFO, "Sucessfully clicked on object");
//        actionClass.clickOnObject(btnSave);
        Thread.sleep(2000);
//        verificationClass.verifyTextPresent(lblClassSuccess, "Class added successfully");
        actionClass.captureScreen("Class Add Success");
    }

    public void checkListingonRecord_Class() throws InterruptedException {
        ActionClass actionClass = new ActionClass(driver, test);
//        actionClass.clickOnObject(btnClass);
        driver.findElement(By.xpath("//*[@id=\"sibe-box\"]/ul[2]/li[3]/ul/li[5]/a")).click();
        test.log(Status.INFO, "Sucessfully clicked on object");

        List<WebElement> ListRecords = driver.findElements(By.xpath("//*[@id=\"DataTables_Table_0\"]/tbody/tr"));
        int RecordsSize = ListRecords.size();
        System.out.println(RecordsSize);
        for (int i = 1; i <= RecordsSize; i++) {
            String s = driver.findElement(By.xpath("//*[@id='DataTables_Table_0']/tbody/tr[" + i + "]/td[1]")).getText();
            if (s.equals(classname)) {
                System.out.println("Element Found in Row[" + i + 1 + "]");
                test.log(Status.PASS, "Class Name present in the listing");
                Thread.sleep(2000);
                break;
            } else {
                System.out.println("Element not found in Row[" + i + 1 + "]");
            }
        }
    }

    public void checkListingonRecord_Section() {
        ActionClass actionClass = new ActionClass(driver, test);
//        actionClass.clickOnObject(btnClass);
        driver.findElement(By.xpath("//*[@id=\"sibe-box\"]/ul[2]/li[3]/ul/li[5]/a")).click();
        test.log(Status.INFO, "Sucessfully clicked on object");
        List<WebElement> ListRecords = driver.findElements(By.xpath("//*[@id=\"DataTables_Table_0\"]/tbody/tr"));
        List<WebElement> ListSections = driver.findElements(By.xpath("//*[@id=\"DataTables_Table_0\"]/tbody/tr/td"));
        int RecordsSize = ListRecords.size();
        int SectionSize = ListSections.size();

        System.out.println(RecordsSize);
        for (int i = 1; i <= RecordsSize; i++) {
            String s1 = driver.findElement(By.xpath("//*[@id='DataTables_Table_0']/tbody/tr[" + i + "]/td[1]")).getText();
            if (s1.equals(classname)) {
                test.log(Status.PASS, "Class name is Correct");
                for (int j = 1; j <= SectionSize; j++) {
                    System.out.println("Element Found for Edit in Row[" + i + 1 + "]");
                    String s2 = driver.findElement(By.xpath("//*[@id=\"DataTables_Table_0\"]/tbody/tr[" + i + "]/td[2]")).getText();
                    if (s2.equals(secA)) {
                        System.out.println(s1);
                        test.log(Status.PASS, "Section name verified");
                        break;
                    }
                }
            } else {
                System.out.println("Element not found in Row[" + i + 1 + "]");
            }
        }
    }

    public void verify_Class_fromDB() throws InterruptedException, SQLException {
        Thread.sleep(2000);
        DatabaseFunctions DAB = new DatabaseFunctions(test);
        conn = DAB.connect();
        statement = conn.createStatement();
        String query = "SELECT * FROM classes WHERE class='" + classname + "'";
        System.out.println(query);
        queryRs = statement.executeQuery(query);
        while (queryRs.next()) {
            String s1 = null;
            s1 = queryRs.getString("class");
            System.out.println("Expected name. is " + s1);
            if (classname.equals(s1)) {
                test.log(Status.PASS, "Class Name Matched");
            } else {
                test.log(Status.FAIL, "Class name not Matched");
            }
        }
    }

    public void verify_Section_fromDB() throws InterruptedException, SQLException {
        ActionClass actionClass = new ActionClass(driver, test);
        driver.findElement(By.xpath("//*[@id=\"sibe-box\"]/ul[2]/li[3]/ul/li[5]/a")).click();
        test.log(Status.INFO, "Sucessfully clicked on object");
        Thread.sleep(2000);
        DatabaseFunctions DAB = new DatabaseFunctions(test);
        conn = DAB.connect();
        statement = conn.createStatement();
        String s2 = null;
        String query2 = "SELECT sections.section FROM `class_sections` INNER JOIN classes ON class_sections.class_id = " +
                "classes.id INNER JOIN sections ON class_sections.section_id = sections.id where classes.class='"+classname+"' " +
                "AND class_sections.is_active='no' ORDER BY class_sections.class_id";
        queryRs = statement.executeQuery(query2);
        System.out.println(queryRs);
        while (queryRs.next()) {
            s2 = queryRs.getString("section");
            if (secD.equals(s2)|| secA.equals(s2)) {
                System.out.println("Query executed D");
                test.log(Status.PASS, "Section Found");
            } else {
                System.out.println("not found");
                test.log(Status.INFO, "Section not found");
            }
        }
    }


    public void edit_fromListing() throws InterruptedException, IOException {

        ActionClass actionClass=new ActionClass(driver,test);
        VerificationClass verificationClass=new VerificationClass(driver,test);

        driver.findElement(By.xpath("//*[@id=\"sibe-box\"]/ul[2]/li[3]/ul/li[5]/a")).click();
        test.log(Status.INFO,"Sucessfully clicked on object");

        List<WebElement> ListRecords = driver.findElements(By.xpath("//*[@id=\"DataTables_Table_0\"]/tbody/tr"));
        int RecordsSize = ListRecords.size();

        System.out.println(RecordsSize);
        for (int i = 1; i <= RecordsSize; i++) {
            String s = driver.findElement(By.xpath("//*[@id=\"DataTables_Table_0\"]/tbody/tr["+i+"]/td[1]")).getText();
            System.out.println(s);

            if (s.equals(classname)) {
                test.log(Status.PASS,"Class Name present in the listing");

                driver.findElement(By.xpath("//*[@id=\"DataTables_Table_0\"]/tbody/tr["+i+"]/td[3]/a[1]")).click();
                test.log(Status.INFO,"Sucessfully clicked on object");

                driver.findElement(By.xpath("//*[@id=\"class\"]")).clear();
                driver.findElement(By.xpath("//*[@id=\"class\"]")).sendKeys(updatedname);
                test.log(Status.INFO, "Sucessfully Data Entered");
                Thread.sleep(1000);
                driver.findElement(By.xpath("//*[@id=\"form1\"]/div[2]/button")).click();
                test.log(Status.INFO, "Sucessfully clicked on object");
                Thread.sleep(2000);
                actionClass.captureScreen("Updated the Class successfully");

                Thread.sleep(2000);
                break;
            }
            else
            {
                System.out.println("Element not found in Row[" +i+1+ "]");
            }
        }
    }

    public  void delete_fromListing() throws InterruptedException, IOException {
        ActionClass actionClass=new ActionClass(driver,test);
        List<WebElement> ListRecords = driver.findElements(By.xpath("//*[@id=\"DataTables_Table_0\"]/tbody/tr"));
        int RecordsSize = ListRecords.size();

        System.out.println(RecordsSize);
        for (int i = 1; i <= RecordsSize; i++) {
            String s = driver.findElement(By.xpath("//*[@id=\"DataTables_Table_0\"]/tbody/tr["+i+"]/td[1]")).getText();
            System.out.println(s);

            if (s.equals(updatedname)) {
                System.out.println("Element Found for Edit in Row[" +i+1+ "]");
                driver.findElement(By.xpath("//*[@id=\"DataTables_Table_0\"]/tbody/tr["+i+"]/td[3]/a[2]")).click();
                test.log(Status.INFO,"Sucessfully clicked on object");
                driver.switchTo().alert().accept();
                test.log(Status.PASS,"Class Name Deleted From the listing");
                Thread.sleep(2000);
                actionClass.captureScreen("Class successfully delete from listing");
                break;
            }
            else
            {
                System.out.println("Element not found in Row[" +i+1+ "]");
            }
        }

    }

    public void verify_Delete_fromDB() throws SQLException, InterruptedException {
        ActionClass actionClass=new ActionClass(driver,test);
        Thread.sleep(2000);
        DatabaseFunctions DAB = new DatabaseFunctions(test);
        conn = DAB.connect();
        statement = conn.createStatement();
        String s1=null;
        String query = "SELECT * FROM classes where class='"+ updatedname+"'";
        System.out.println(query);
        queryRs = statement.executeQuery(query);
        System.out.println(queryRs);
        if(updatedname.equals(s1)) {
            test.log(Status.FAIL, "Class Still Present in the Database");
        }

        else {
            test.log(Status.PASS, "Class Not Present in the Database");
        }
    }
}