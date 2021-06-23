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
import org.openqa.selenium.support.PageFactory;

import java.io.IOException;
import java.sql.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class CircularParent {
    WebDriver driver1;
    ExtentTest extentTest;
    Connection conn = null;
    //  Docker
//  String url = "jdbc:mysql://localhost:6603/";
//  localDB
    String url = "jdbc:mysql://localhost:3306/";
    String dbName = "aedu-dev1";
    String driver = "com.mysql.jdbc.Driver";
    String userName = "root";
    String password = "";
    Statement statement;
    ResultSet queryRs;

    @FindBy(how = How.XPATH, using = "//*[@id=\"sibe-box\"]/ul[2]/li[5]/a")
    private WebElement ClickOnCircular;

    @FindBy(how = How.XPATH, using = "//*[@id=\"sibe-box\"]/ul[2]/li[5]/ul/li[3]/a")
    private WebElement ClickOnCircularforParent;

    @FindBy(how = How.XPATH, using = "//button[contains(.,' Submit')]")
    private WebElement ClickOnSubmit;

    @FindBy(how = How.XPATH, using = "//*[@id=\"form1\"]/div/div[2]/div/div[1]/div[1]/span/p")
    private WebElement TitleFieldValidation;

    @FindBy(how = How.XPATH, using = "//*[@id=\"form1\"]/div/div[2]/div/div[2]/div/div[1]/span/p")
    private WebElement PublishDateFieldValidation;

    @FindBy(how = How.XPATH, using = "//*[@id=\"form1\"]/div/div[2]/div/div[1]/div[2]/span/p")
    private WebElement MessageFieldValidation;

    @FindBy(how = How.XPATH, using = "//*[@id=\"form1\"]/div/div[2]/div/div[1]/span/p")
    private WebElement ClassFieldValidation;

    @FindBy(how = How.XPATH, using = "//*[@id=\"title\"]")
    private WebElement TitleField;

    @FindBy(how = How.CLASS_NAME, using = "wysihtml5-sandbox")
    private WebElement MessageField;

    @FindBy(how = How.XPATH, using = "//*[@id=\"publish_date\"]")
    private WebElement PublishOnField;

    @FindBy(how = How.CSS, using = "tr:nth-child(5) > .day:nth-child(4)")
    private WebElement PublishDate;

    @FindBy(how = How.XPATH, using = "/html/body/div[1]/div/section[2]/div/div/form/div/div[2]/div/div[1]/div[4]/div[1]/div[2]/div/div[1]")
    private WebElement Section;

    @FindBy(how = How.XPATH, using = "/html/body/div[1]/div/section[2]/div/div/form/div/div[2]/div/div[1]/div[4]/div[1]/div[2]/div/div[2]/div/label[1]")
    private WebElement ClassField;

    @FindBy(how = How.XPATH, using = "//*[@id=\"form1\"]/div/div[2]/div/div[1]/div[4]/div[2]/div[2]/div/div[1]/div/label")
    private WebElement ScrollText;

    @FindBy(how = How.XPATH, using = "/html/body/div[1]/div[1]/section[2]/div/div/div[1]/div[2]/div[1]")
    private WebElement SuccessMessage;

    @FindBy(how = How.XPATH, using = "//*[@id=\"form1\"]/div/div[2]/div/div[2]/div/div[2]/div/div/input")
    private WebElement SelectFile;

    public CircularParent(WebDriver driver, ExtentTest test) throws InterruptedException, IOException {
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

//  Test if all the mandatory fields shows validation messages
    public void AddCircularValidation() throws InterruptedException, IOException {
        ActionClass actionClass = new ActionClass(driver1, extentTest);
       // actionClass.clickOnObject(ClickOnCircular);
        Thread.sleep(3000);
        actionClass.clickOnObject(ClickOnCircularforParent);
        Thread.sleep(3000);
        JavascriptExecutor jsetaskscore = (JavascriptExecutor) driver1;
        jsetaskscore.executeScript("arguments[0].scrollIntoView();",ClickOnSubmit);
        actionClass.clickOnObject(ClickOnSubmit);
        Thread.sleep(3000);
        actionClass.captureScreen("Circular_Parent_1");
        Thread.sleep(3000);
        jsetaskscore.executeScript("scrollBy(0, 300)");
        Thread.sleep(3000);
        actionClass.captureScreen("Circular_Parent_2");
        VerificationClass verificationClass=new VerificationClass(driver1, extentTest);
        verificationClass.verifyTextPresent(TitleFieldValidation, "The Title field is required.");
        verificationClass.verifyTextPresent(PublishDateFieldValidation, "The Publish Date field is required.");
        verificationClass.verifyTextPresent(MessageFieldValidation, "The Message field is required.");
        verificationClass.verifyTextPresent(ClassFieldValidation, "The Class field is required.");
    }

//  Test if the circular is added and the added circular data matches with database
    public void AddCircular(String Title, String Message, String FileLocation) throws InterruptedException, IOException, SQLException {
        ActionClass actionClass = new ActionClass(driver1, extentTest);
        JavascriptExecutor jsetaskscore = (JavascriptExecutor) driver1;
      //actionClass.clickOnObject(ClickOnCircular);
        Thread.sleep(3000);
        actionClass.clickOnObject(ClickOnCircularforParent);
        Thread.sleep(2000);
        actionClass.setValueinTextbox(TitleField, Title);
        Thread.sleep(2000);
        driver1.findElement(By.className("wysihtml5-sandbox")).sendKeys(Message);
        Thread.sleep(2000);
        actionClass.clickOnObject(PublishOnField);
        Thread.sleep(2000);
        actionClass.clickOnObject(PublishDate);
        Thread.sleep(3000);
        actionClass.captureScreen("Circular_Parent_2");
        actionClass.clickOnObject(SelectFile);
        WebElement uploadElement = driver1.findElement(By.id("files"));
        uploadElement.sendKeys(FileLocation);
        Thread.sleep(2000);
        jsetaskscore.executeScript("scrollBy(0, 600)");
        actionClass.clickOnObject(Section);
        actionClass.clickOnObject(ClassField);
        actionClass.captureScreen("Circular_Parent_2");
        Thread.sleep(2000);
        jsetaskscore.executeScript("arguments[0].scrollIntoView();",ClickOnSubmit);
        actionClass.clickOnObject(ClickOnSubmit);
        Thread.sleep(3000);
        VerificationClass verificationClass= new VerificationClass(driver1, extentTest);
        verificationClass.verifyTextPresent(SuccessMessage, "Circular Added Successfully!!!");
        actionClass.captureScreen("Circular_Parent_3");

        ArrayList<String> circulartitlesinFront = new ArrayList<String>();
        ArrayList<String> circularMessageinFront = new ArrayList<String>();
        circulartitlesinFront.add(Title);
        circularMessageinFront.add(Message);
        System.out.println(circulartitlesinFront);
        System.out.println(circularMessageinFront);

//      The circular added in Database
        DatabaseFunctions DAB = new DatabaseFunctions(extentTest);
        conn = DAB.connect();
        statement = conn.createStatement();
        String circular = "SELECT * FROM `send_notification` ORDER BY ID DESC LIMIT 1";
        queryRs = statement.executeQuery(circular);
        ArrayList<String> circulartitlesinDB = new ArrayList<String>();
        ArrayList<String> circularMessageinDB = new ArrayList<String>();
        ArrayList<String> circularIdinDB = new ArrayList<String>();
        if (queryRs.next()) {
            circulartitlesinDB.add(queryRs.getString("send_notification.title"));
            String messageinDB=queryRs.getString("send_notification.message");
            String trimmessageinDB = messageinDB.replaceAll("\\<.*?\\>", "");
            circularMessageinDB.add(trimmessageinDB);
            circularIdinDB.add(queryRs.getString("send_notification.id"));
            System.out.println(circulartitlesinDB);
            System.out.println(circularMessageinDB);
            System.out.println(circularIdinDB);
            extentTest.log(Status.PASS, "The entered data is in database");
        } else{
            System.out.println("There is no such entry in database.");
            extentTest.log(Status.FAIL,"There is no such entry in database.");
        }
        statement = conn.createStatement();
        String circular1 = "SELECT * FROM `circular_file` ORDER BY ID DESC LIMIT 1";
        queryRs = statement.executeQuery(circular1);
        ArrayList<String> circularIdinDB2 = new ArrayList<String>();
        if (queryRs.next()) {
            circularIdinDB2.add(queryRs.getString("circular_file.send_notification_id"));
            System.out.println(circularIdinDB2);
            extentTest.log(Status.PASS, "The entered data is in database");
        } else{
            System.out.println("There is no such entry in database.");
            extentTest.log(Status.FAIL,"There is no such entry in database.");
        }

//      Test if all the data entered in circular matches with database
        if (circulartitlesinDB.equals(circulartitlesinFront)){
            System.out.println("Added title of circular matched with database");
            extentTest.log(Status.PASS, "Added title of circular matched with database");
        }
        else{
            System.out.println("Added title of circular did not match with database");
            extentTest.log(Status.FAIL, "Added title of circular did not match with database");
        }
        if (circularMessageinDB.equals(circularMessageinFront)){
            System.out.println("Added message of circular matched with database");
            extentTest.log(Status.PASS, "Added message of circular matched with database");
        }
        else{
            System.out.println("Added message of circular did not match with database");
            extentTest.log(Status.FAIL, "Added message of circular did not match with database");
        }
        if(circularIdinDB2.equals(circularIdinDB)){
            System.out.println("Added files in circular matched with database");
            extentTest.log(Status.PASS, "Added file in circular matched with database");
        }
        else{
            System.out.println("Added files in circular did not match with database");
            extentTest.log(Status.FAIL, "Added file in circular did not match with database");
        }
    }
}