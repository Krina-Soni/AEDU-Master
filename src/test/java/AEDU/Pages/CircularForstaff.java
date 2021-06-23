package AEDU.Pages;
import AEDU.Actions.ActionClass;
import AEDU.Actions.VerificationClass;
import AEDU.Utilities.DatabaseFunctions;
import AEDU.constants.CommonVar;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import java.awt.*;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

public class CircularForstaff {
    WebDriver driver1;
    ExtentTest extentTest;
    Connection conn = null;
    Statement statement;
    ResultSet queryRs;
    ResultSet Qcount;
    String url = "jdbc:mysql://localhost:3306/";
    String dbName = "aedudev1";
    String driver = "com.mysql.jdbc.Driver";
    String userName = "root";
    String password = "root";

    @FindBy(how = How.XPATH,
            using = "/html/body/div[1]/aside/div/section/ul[2]/li[5]/a")
    private WebElement SideMenuCirularClick;

    @FindBy(how = How.XPATH,
            using = "/html/body/div[1]/aside/div/section/ul[2]/li[5]/ul/li[2]/a")
    private WebElement SideMenuCirularForStaffCLick;

    @FindBy(how = How.XPATH,
            using = "/html/body/div[1]/div/section[2]/div/div/form/div/div[3]/button")
    private WebElement Submit;

    @FindBy(how = How.XPATH,
            using = "/html/body/div[1]/div/section[2]/div/div/form/div/div[2]/div/div[1]/div[1]/span/p")
    private WebElement TitleFieldValidation;

    @FindBy(how = How.XPATH,
            using = "/html/body/div[1]/div/section[2]/div/div/form/div/div[2]/div/div[1]/div[2]/span/p")
    private WebElement DescriptionFieldvalidation;

    @FindBy(how = How.XPATH,
            using = "/html/body/div[1]/div/section[2]/div/div/form/div/div[2]/div/div[1]/div[3]/span/p")
    private WebElement SharewithFieldValidation;

    @FindBy(how = How.XPATH,
            using = "/html/body/div[1]/div/section[2]/div/div/form/div/div[2]/div/div[2]/div/div[1]/span/p")
    private WebElement DateFieldValidation;

    @FindBy(how = How.XPATH,
            using = "//*[@id=\"title\"]")
    private WebElement TitleField;

    @FindBy(how = How.CLASS_NAME,
            using = "wysihtml5-sandbox")
    private WebElement MessageField;

    @FindBy(how = How.XPATH,
            using = "/html/body/div[1]/div/section[2]/div/div/form/div/div[2]/div/div[1]/div[3]/label[1]")
    private WebElement ShareWithAccountatCheckbox;

    @FindBy(how = How.XPATH,
            using = "/html/body/div[1]/div/section[2]/div/div/form/div/div[2]/div/div[1]/div[3]/label[4]")
    private WebElement ShareWithTeacherCheckbox;

    @FindBy(how = How.XPATH,
            using = "//*[@id=\"publish_date\"]")
    private WebElement PublishDate;

    @FindBy(how = How.XPATH,
            using = "/html/body/div[1]/div/section[2]/div/div/form/div/div[2]/div/div[2]/div/div[2]/div/div/input")
    private WebElement UploadFile;
    @FindBy(how = How.XPATH,
            using = "/html/body/div[1]/div[1]/section[2]/div/div/div[1]/div[2]/div[1]")
    private WebElement ConfimMsg;

    public CircularForstaff(WebDriver driver, ExtentTest test) {
        this.driver1 = driver;
        this.extentTest = test;
        PageFactory.initElements(driver, this);
    }
    public void openNewTab() throws InterruptedException {
        Thread.sleep(2000);
        for (String childTab : driver1.getWindowHandles()) {
            driver1.switchTo().window(childTab);
        }
        Thread.sleep(1000);
    }

   public void CheckAddCircularValidation()throws IOException,InterruptedException{
        ActionClass actionClass = new ActionClass(driver1,extentTest);
        actionClass.clickOnObject(this.SideMenuCirularClick);
        Thread.sleep(5000);
        actionClass.clickOnObject(this.SideMenuCirularForStaffCLick);
         JavascriptExecutor jsetaskscore = (JavascriptExecutor) driver1;
         jsetaskscore.executeScript("scrollBy(0,350)");
         actionClass.clickOnObject(this.Submit);
         VerificationClass very = new VerificationClass(driver1,extentTest);
         very.verifyTextPresent(this.TitleFieldValidation,"The Title field is required.");
         very.verifyTextPresent(this.DateFieldValidation,"The Publish Date field is required.");
       actionClass.captureScreen("s1");
         jsetaskscore.executeScript("scrollBy(0,350)");
         very.verifyTextPresent(this.DescriptionFieldvalidation,"The Message field is required.");
         very.verifyTextPresent(this.SharewithFieldValidation,"The Share with field is required.");
       actionClass.captureScreen("s2");
    }

    public void AddCircularwithValiddata(String Title,String enterdate,String Title1) throws IOException, InterruptedException, AWTException, SQLException {
        ActionClass actionClass = new ActionClass(driver1,extentTest);
        //actionClass.clickOnObject(this.SideMenuCirularClick);
        Thread.sleep(5000);
        actionClass.clickOnObject(this.SideMenuCirularForStaffCLick);
        actionClass.clickOnObject(this.TitleField);
        actionClass.setValueinTextbox(this.TitleField,Title);
        actionClass.clickOnObject(this.MessageField);
        driver1.findElement(By.className("wysihtml5-sandbox")).sendKeys(Title1);
        actionClass.clickOnObject(this.PublishDate);
        actionClass.setValueinTextbox(this.PublishDate,enterdate);
        Thread.sleep(1000);
        actionClass.clickOnObject(this.UploadFile);
        WebElement uploadElement = driver1.findElement(By.id("files"));
        uploadElement.sendKeys("/home/addweb/Desktop/dummy.pdf");
        actionClass.captureScreen("s1");
        JavascriptExecutor jsetaskscore = (JavascriptExecutor) driver1;
        jsetaskscore.executeScript("scrollBy(0,350)");
        actionClass.clickOnObject(this.ShareWithAccountatCheckbox);
        actionClass.clickOnObject(this.ShareWithTeacherCheckbox);
        jsetaskscore.executeScript("scrollBy(0,350)");
        actionClass.captureScreen("s2");
        actionClass.clickOnObject(this.Submit);
        String s = Title;
        VerificationClass very= new VerificationClass(driver1,extentTest);
        very.verifyTextPresent(this.ConfimMsg,"Circular Added Successfully!!!");
        actionClass.captureScreen("s3");
        DatabaseFunctions DAB = new DatabaseFunctions(extentTest);
        conn = DAB.connect();
        statement = conn.createStatement();
        String CirculareList1 = "SELECT title ,id FROM `send_notification` WHERE title='Testing circular'";
        ResultSet queryRs1 = statement.executeQuery(CirculareList1);
        ArrayList listNames = new ArrayList();
        while (queryRs1.next()) {
            String s1 = null;
            s1 = queryRs1.getString("title");
            listNames.add(s1);
            actionClass.CompareListandstring(s, s1);
        }
    }
}

