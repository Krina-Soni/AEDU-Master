package AEDU.Pages;

import AEDU.Actions.ActionClass;
import AEDU.Actions.VerificationClass;
import AEDU.Testcases.Basecase;
import AEDU.Utilities.DatabaseFunctions;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;


import javax.swing.*;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

public class ExamList extends Basecase {

    WebDriver driver;
    ExtentTest test;
    Connection conn = null;
    Statement statement;
    ResultSet queryRs;

    @FindBy(how = How.XPATH, using = "//*[@id=\"sibe-box\"]/ul[2]/li[11]/ul/li[1]/a")
    public WebElement btnExamlist;

    @FindBy(how = How.XPATH, using = "/html/body/div[1]/div[1]/section[2]/div[1]/div[1]/div/div/h3")
    public WebElement lblExamination;

    @FindBy(how = How.XPATH, using = "//*[@id=\"name\"]")
    public WebElement txtboxName;

    @FindBy(how = How.XPATH, using = "//*[@id=\"note\"]")
    public WebElement txtboxNote;

    @FindBy(how = How.XPATH, using = "//*[@id=\"form1\"]/div[1]/div[1]/span/p")
    public WebElement lblAddError;

    @FindBy(how = How.XPATH, using = "//*[@id=\"form1\"]/div[1]/div[1]")
    public WebElement lblAddSuccess;

    @FindBy(how = How.XPATH, using = "//*[@id=\"form1\"]/div[1]/div[1]")
    public WebElement lblExamUpdate;

    @FindBy(how = How.XPATH, using = "/html/body/div[1]/div[1]/section[2]/div[1]/div/div/div[2]/div[2]/div/div")
    public WebElement lblExamNotAllotted;

    @FindBy(how = How.XPATH, using = "/html/body/div[1]/div[1]/section[2]/div[1]/div/div/div[1]/h3")
    public WebElement lblExamAllot;

    @FindBy(how = How.XPATH, using = "/html/body/div[1]/div[1]/section[2]/div[1]/div/div/div[2]/div[2]/table/tbody/tr/td[2]/span")
    public WebElement lblExamScheduled;

    @FindBy(how = How.XPATH, using = "//*[@id=\"form1\"]/div[2]/button")
    public WebElement btnSave;

    @FindBy(how = How.XPATH, using = "//*[@id=\"employeeform\"]/div[2]/button")
    public WebElement btnEditSave;

    @FindBy(how = How.XPATH, using = "/html/body/div[1]/div[1]/section[2]/div[1]/div/div/div[2]/div[2]/table/tbody/tr/td[2]/a")
    public WebElement btnViewSchedule;

    public ExamList(WebDriver driver, ExtentTest test) {
        this.driver = driver;
        this.test = test;
        PageFactory.initElements(driver, this);
    }

    //Global Variables
    String name = "Subject_Testing";
    String updatedName = "Johnny_Harper_Testing";
    String note = "This is the test description for the Note";
    String updatedNote = "Updated Testing Note";

    public void openNewTab() throws InterruptedException {
        Thread.sleep(2000);
        System.out.println("open new tab function");
        for (String childTab : driver.getWindowHandles()) {
            driver.switchTo().window(childTab);
        }
    }

    public void checkBlanksubmission() throws IOException, InterruptedException {
        ActionClass actionClass = new ActionClass(driver, test);
        VerificationClass verificationClass = new VerificationClass(driver, test);
        Thread.sleep(3000);
        actionClass.clickOnObject(btnExamlist);
        Thread.sleep(2000);
        verificationClass.verifyTextPresent(lblExamination, "Add Exam");
        actionClass.captureScreen("Verify_redirection_to.Exam");
        Thread.sleep(3000);
        actionClass.clickOnObject(btnSave);
        verificationClass.verifyTextPresent(lblAddError, "The Name field is required.");
    }

    public void check_Add() throws InterruptedException, IOException {
        ActionClass actionClass = new ActionClass(driver, test);
        VerificationClass verificationClass = new VerificationClass(driver, test);
        actionClass.clickOnObject(btnExamlist);
        Thread.sleep(2000);
        actionClass.setValueinTextbox(txtboxName, name);
        Thread.sleep(2000);
        actionClass.setValueinTextbox(txtboxNote, note);
        actionClass.clickOnObject(btnSave);
        Thread.sleep(2000);
        verificationClass.verifyTextPresent(lblAddSuccess, "Exam added successfully");
        actionClass.captureScreen("Exam Added Successfully");
    }

    public void verify_addDB() throws SQLException {
        DatabaseFunctions DAB = new DatabaseFunctions(test);
        conn = DAB.connect();
        statement = conn.createStatement();
        String query = "SELECT * FROM exams where name='"+name+"'";
        System.out.println(query);
        queryRs = statement.executeQuery(query);

        while (queryRs.next()) {
            String s1= null;
            s1 = queryRs.getString("name");
            System.out.println("Expected name. is " + s1);
            if (name.equals(s1))
            {
                System.out.println("User Matched");
                test.log(Status.PASS,"Data is Present in the Database");
            } else
            {
                System.out.println("Please check the users");
                test.log(Status.PASS,"Data is NOT Present in the Database");
            }
        }
    }

    public void editing_entry() throws InterruptedException, IOException {
        ActionClass actionClass = new ActionClass(driver, test);
        VerificationClass verificationClass = new VerificationClass(driver, test);

        actionClass.clickOnObject(btnExamlist);
        Thread.sleep(2000);

        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollBy(0,200)");

        List<WebElement> ListRecord = driver.findElements(By.xpath("//*[@id='DataTables_Table_0']/tbody/tr"));
        int RecordSize = ListRecord.size();
        System.out.println(RecordSize);

        for (int i = 1; i <= RecordSize; i++) {
            String s = driver.findElement(By.xpath("//*[@id=\"DataTables_Table_0\"]/tbody/tr["+i+"]/td[1]/a[1]")).getText();
            System.out.println("Value in list is: " + s);

            if (s.equals(name)) {
                System.out.println("Element Found for Edit");
                Thread.sleep(2000);
                driver.findElement(By.xpath("//*[@id=\"DataTables_Table_0\"]/tbody/tr["+i+"]/td[2]/a[1]")).click();
                Thread.sleep(2000);
                break;
            }
        }
        actionClass.updateValueinTextbox(txtboxName,updatedName);
        Thread.sleep(2000);
        actionClass.updateValueinTextbox(txtboxNote,updatedNote);
        Thread.sleep(3000);
        actionClass.clickOnObject(btnEditSave);
        Thread.sleep(2000);
        verificationClass.verifyTextPresent(lblExamUpdate,"Exam update successfully");
        actionClass.captureScreen("Exam name Updated");
    }

    public void deleting_entry() throws InterruptedException, IOException {
        ActionClass actionClass = new ActionClass(driver, test);
        VerificationClass verificationClass=new VerificationClass(driver,test);
        actionClass.clickOnObject(btnExamlist);
        Thread.sleep(2000);

        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollBy(0,150)");

        List<WebElement> ListRecord = driver.findElements(By.xpath("//*[@id='DataTables_Table_0']/tbody/tr"));
        int RecordSize = ListRecord.size();
        System.out.println(RecordSize);

        for (int i = 1; i <= RecordSize; i++) {
            String s = driver.findElement(By.xpath("//*[@id=\"DataTables_Table_0\"]/tbody/tr["+i+"]/td[1]/a[1]")).getText();
            System.out.println("Value in list is: " + s);

            if (s.equals(updatedName)) {
                System.out.println("Element Found for Edit");
                Thread.sleep(2000);
                driver.findElement(By.xpath("//*[@id=\"DataTables_Table_0\"]/tbody/tr["+i+"]/td[2]/a[2]")).click();
                test.log(Status.PASS,"Exam List Entry Found for Deletion");
                Thread.sleep(2000);
                driver.switchTo().alert().accept();
                Thread.sleep(2000);
                actionClass.captureScreen("Entry Deleted");
                test.log(Status.PASS,"Exam List Entry Deleted");
                break;
            }
        }
    }

    public void verify_delete_listing() throws InterruptedException, IOException {

        ActionClass actionClass = new ActionClass(driver, test);
        VerificationClass verificationClass=new VerificationClass(driver,test);
        actionClass.clickOnObject(btnExamlist);
        Thread.sleep(2000);

        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollBy(0,150)");

        List<WebElement> ListRecord = driver.findElements(By.xpath("//*[@id='DataTables_Table_0']/tbody/tr"));
        int RecordSize = ListRecord.size();
        System.out.println(RecordSize);

        for (int i = 1; i <= RecordSize; i++) {
            String s = driver.findElement(By.xpath("//*[@id=\"DataTables_Table_0\"]/tbody/tr["+i+"]/td[1]/a[1]")).getText();
            System.out.println("Value in list is: " + s);

            if (s.equals(updatedName)) {
                System.out.println("Element Found for Edit");
                Thread.sleep(2000);
                test.log(Status.FAIL,"Delete Exam list Entry still present in the Listing");
                actionClass.captureScreen("Entry Still present- Exam List");
                break;
            }
        }

        actionClass.captureScreen("Entry Deleted Exam List");
        test.log(Status.PASS,"Entry is Deleted from The listing");
    }

    public void verify_Delete_DB() throws SQLException {
        DatabaseFunctions DAB = new DatabaseFunctions(test);
        conn = DAB.connect();
        statement = conn.createStatement();
        String query = "SELECT * FROM exams where name= '"+updatedName+"'";
        System.out.println(query);
        queryRs = statement.executeQuery(query);

        while (queryRs.next()) {
            String s1= null;
            s1 = queryRs.getString("name");
            System.out.println("Expected name. is " + s1);

            if (s1.equals(updatedName))
            {
                System.out.println("User Matched");
                test.log(Status.FAIL,"Data Still Present");
                break;
            }
            else
            {
                System.out.println("Please check the users");

            }
        }
        test.log(Status.PASS,"Data Not Present");
    }

    public void examStatus_notAllot() throws InterruptedException, IOException {
        ActionClass actionClass = new ActionClass(driver, test);
        VerificationClass verificationClass = new VerificationClass(driver, test);

        actionClass.clickOnObject(btnExamlist);
        Thread.sleep(2000);

        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollBy(0,200)");

        List<WebElement> ListRecord = driver.findElements(By.xpath("//*[@id='DataTables_Table_0']/tbody/tr"));
        int RecordSize = ListRecord.size();
        System.out.println(RecordSize);

        for (int i = 1; i <= RecordSize; i++) {
            String s = driver.findElement(By.xpath("//*[@id=\"DataTables_Table_0\"]/tbody/tr["+i+"]/td[1]/a[1]")).getText();
            System.out.println("Value in list is: " + s);

            if (s.equals(name)) {
                System.out.println("Element Found for Edit");
                Thread.sleep(2000);
                driver.findElement(By.xpath("//*[@id=\"DataTables_Table_0\"]/tbody/tr["+i+"]/td[2]/a[3]")).click();
                Thread.sleep(3000);
                verificationClass.verifyTextPresent(lblExamAllot,""+name+" Exam Status");
                Thread.sleep(3000);
                verificationClass.verifyTextPresent(lblExamNotAllotted,"Exam Not Allotted");
                test.log(Status.PASS,"Exam is NOT alloted");
                actionClass.captureScreen("Exam Not Alloted");
                Thread.sleep(2000);
                break;
            }
        }
        test.log(Status.FAIL,"Exam is already alloted");
    }

    public void examStatus_Allot() throws InterruptedException, IOException {
        ActionClass actionClass = new ActionClass(driver, test);
        VerificationClass verificationClass = new VerificationClass(driver, test);
        actionClass.clickOnObject(btnExamlist);
        Thread.sleep(2000);
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollBy(0,200)");

        driver.findElement(By.xpath("//*[@id=\"DataTables_Table_0\"]/tbody/tr[13]/td[2]/a[3]"));
        verificationClass.verifyTextPresent(lblExamAllot,""+name+" Exam Status");
        Thread.sleep(3000);
        verificationClass.verifyTextPresent(lblExamScheduled,"yes");
        test.log(Status.PASS,"Exam has been Scheduled");
        actionClass.captureScreen("Exam has been scheduled");
    }

    public void view_ExamSchedule() throws InterruptedException, IOException {
        ActionClass actionClass = new ActionClass(driver, test);
        VerificationClass verificationClass=new VerificationClass(driver,test);
        actionClass.clickOnObject(btnExamlist);
        Thread.sleep(2000);
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollBy(0,200)");
        actionClass.clickOnObject(btnViewSchedule);
        Thread.sleep(2000);
        actionClass.captureScreen("Classes Schedule");

    }

}