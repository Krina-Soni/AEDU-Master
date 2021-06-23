package AEDU.Pages;

import AEDU.Actions.ActionClass;
import AEDU.Actions.VerificationClass;
import AEDU.Utilities.DatabaseFunctions;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class AcademicsSubjects {
    WebDriver driver1;
    ExtentTest extentTest;
    Connection conn = null;
//  Docker
//  String url = "jdbc:mysql://localhost:6603/";
//  localDB
    String url = "jdbc:mysql://localhost:3306/";
    String dbName = "aedudev1";
    String driver = "com.mysql.jdbc.Driver";
    String userName = "root";
    String password = "root";
    Statement statement;
    ResultSet queryRs;

    @FindBy(how = How.XPATH, using = "//*[@id=\"sibe-box\"]/ul[2]/li[3]/a")
    private WebElement ClickOnAcademics;

    @FindBy(how = How.XPATH, using = "//*[@id=\"sibe-box\"]/ul[2]/li[3]/ul/li[3]/a")
    private WebElement ClickOnSubjects;

    @FindBy(how = How.XPATH, using = "//*[@id=\"form1\"]/div[2]/button")
    private WebElement SaveButton;

    @FindBy(how = How.CSS, using = ".form-group:nth-child(2) > #category")
    private WebElement SubjectNameField;

    @FindBy(how = How.CSS, using = ".form-group:nth-child(5) > #category")
    private WebElement SubjectCodeField;

    @FindBy(how = How.XPATH, using = "//label[contains(.,'Theory')]")
    private WebElement TheoryRadioButton;

    @FindBy(how = How.XPATH, using = "//label[contains(.,'Practical')]")
    private WebElement PracticalRadioButton;

    @FindBy(how = How.XPATH, using = "//*[@id=\"form1\"]/div[1]/div[1]/span/p")
    private WebElement SubjectNameValidation;

    @FindBy(how = How.CSS, using = "label:nth-child(1) > input")
    private WebElement SearchField;

    @FindBy(how = How.CSS, using = ".alert")
    private WebElement SubjectSuccessMessage;

    @FindBy(how = How.XPATH, using = "//*[@id=\"DataTables_Table_0\"]/tbody/tr/td[4]/a[1]/i")
    private WebElement ClickonEditSubject;

    @FindBy(how = How.CSS, using = ".form-group:nth-child(2) > #category")
    private WebElement EditSubjectField;

    @FindBy(how = How.CSS, using = ".form-group:nth-child(5) > #category")
    private WebElement EditSubjectCodeField;

    @FindBy(how = How.XPATH, using = "//button[contains(.,'Save')]")
    private WebElement EditSaveButton;

    @FindBy(how = How.XPATH, using = "//*[@id=\"DataTables_Table_0\"]/tbody/tr[1]/td[4]/a[2]")
    private WebElement ClickonDeleteSubject;

    @FindBy(how = How.XPATH, using = "//*[@id=\"form1\"]/div[1]/div[1]")
    private WebElement DeleteSuccessMessage;


    public AcademicsSubjects(WebDriver driver, ExtentTest test) throws InterruptedException, IOException {
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

//  Test Subject field validation.
    public void SubjectValidation() throws IOException, InterruptedException {
        ActionClass actionClass = new ActionClass(driver1, extentTest);
        //actionClass.clickOnObject(ClickOnAcademics);
        Thread.sleep(2000);
        actionClass.clickOnObject(ClickOnSubjects);
        Thread.sleep(1000);
        actionClass.clickOnObject(SaveButton);
        Thread.sleep(2000);
        VerificationClass verificationClass = new VerificationClass(this.driver1, extentTest);
        verificationClass.verifyTextPresent(this.SubjectNameValidation, "The Subject Name field is required.");
        actionClass.captureScreen("Subjects_1");
    }

//  Test by adding subject and compare with database.
    public void AddSubject(String SubjectName, String SubjectCode) throws IOException, InterruptedException, SQLException {
        ActionClass actionClass = new ActionClass(driver1, extentTest);
//        actionClass.clickOnObject(ClickOnAcademics);
        Thread.sleep(2000);
        actionClass.clickOnObject(ClickOnSubjects);
        Thread.sleep(2000);
        actionClass.setValueinTextbox(SubjectNameField, SubjectName);
        Thread.sleep(2000);
        actionClass.clickOnObject(PracticalRadioButton);
        Thread.sleep(2000);
        actionClass.setValueinTextbox(SubjectCodeField, SubjectCode);
        Thread.sleep(2000);
        actionClass.clickOnObject(SaveButton);
        Thread.sleep(3000);
        VerificationClass verificationClass = new VerificationClass(this.driver1, extentTest);
        verificationClass.verifyTextPresent(this.SubjectSuccessMessage, "Subject added successfully");
        actionClass.captureScreen("Subjects_2");
        actionClass.setValueinTextbox(SearchField, SubjectName);
        Thread.sleep(2000);
        actionClass.captureScreen("Subjects_3");

//      Check if the list is empty or not.
        Boolean isPresent = driver1.findElements(By.xpath("//*[@id=\"DataTables_Table_0\"]/tbody/tr[1]/td[2]")).size() > 0;
        ArrayList<String> ListSubjectNameinFront = new ArrayList<String>();
        ArrayList<String> ListSubjectCodeinFront = new ArrayList<String>();
        ArrayList<String> ListSubjectTypeinFront = new ArrayList<String>();
        if(isPresent==true){
            List<WebElement> ListSubject = driver1.findElements(By.xpath("//*[@id=\"DataTables_Table_0\"]/tbody/tr"));
            Thread.sleep(3000);
            int listsize = ListSubject.size();
            for (int i = 1; i <= listsize; i++) {
                String s = driver1.findElement(By.xpath("//*[@id=\"DataTables_Table_0\"]/tbody/tr[" + i + "]/td[1]")).getText();
                String s1 = driver1.findElement(By.xpath("//*[@id=\"DataTables_Table_0\"]/tbody/tr[" + i + "]/td[2]")).getText();
                String s2 = driver1.findElement(By.xpath("//*[@id=\"DataTables_Table_0\"]/tbody/tr[" + i + "]/td[3]")).getText();
                System.out.println("Value in list is: " + s);
                System.out.println("Value in list is: " + s1);
                System.out.println("Value in list is: " + s2);
                ListSubjectNameinFront.add(driver1.findElement(By.xpath("//*[@id=\"DataTables_Table_0\"]/tbody/tr[" + i + "]/td[1]")).getText());
                ListSubjectCodeinFront.add(driver1.findElement(By.xpath("//*[@id=\"DataTables_Table_0\"]/tbody/tr[" + i + "]/td[2]")).getText());
                ListSubjectTypeinFront.add(driver1.findElement(By.xpath("//*[@id=\"DataTables_Table_0\"]/tbody/tr[" + i + "]/td[3]")).getText());
            }
            System.out.println(ListSubjectNameinFront);
            System.out.println(ListSubjectCodeinFront);
            System.out.println(ListSubjectTypeinFront);
            System.out.println("The subject is added.");
            extentTest.log(Status.PASS, "The subject is added and present in the list.");
        }else{
            System.out.println("There is no such entry in the list");
            extentTest.log(Status.FAIL, "There is no such entry in the list");
        }

//      The List of Subjects in Database
        DatabaseFunctions DAB = new DatabaseFunctions(extentTest);
        conn = DAB.connect();
        statement = conn.createStatement();
        String subjects = "SELECT * FROM `subjects` WHERE name = '" + SubjectName + "'";
        queryRs = statement.executeQuery(subjects);
        ArrayList<String> subjectNamesListinDB = new ArrayList<String>();
        ArrayList<String> subjectCodeListinDB = new ArrayList<String>();
        ArrayList<String> subjectTypeListinDB = new ArrayList<String>();
        if (queryRs.next()) {
            String subjectnames = null;
            String subjecttype = null;
            String subjectcode = null;
            subjectnames = queryRs.getString("name");
            subjecttype = queryRs.getString("code");
            subjectcode = queryRs.getString("type");
            System.out.println("Value in database list: " + subjectnames);
            System.out.println("Value in database list: " + subjectcode);
            System.out.println("Value in database list: " + subjecttype);
            subjectNamesListinDB.add(queryRs.getString("name"));
            subjectCodeListinDB.add(queryRs.getString("code"));
            subjectTypeListinDB.add(queryRs.getString("type"));
            System.out.println(subjectNamesListinDB);
            extentTest.log(Status.PASS, "The entered data is in database");
        }else{
            System.out.println("There is no such entry in database.");
            extentTest.log(Status.FAIL,"There is no such entry in database.");
        }

//      Compare front and db lists
        actionClass.CompareStringList(ListSubjectNameinFront, subjectNamesListinDB);
        actionClass.CompareStringList(ListSubjectCodeinFront, subjectCodeListinDB);
        actionClass.CompareStringList(ListSubjectTypeinFront, subjectTypeListinDB);
    }

//  Test by editing subject and compare with database.
    public void EditSubject(String SubjectName, String EditSubjectName, String EditSubjectCode) throws InterruptedException, SQLException, IOException {
        ActionClass actionClass= new ActionClass(driver1, extentTest);
//        actionClass.clickOnObject(ClickOnAcademics);
        Thread.sleep(2000);
        actionClass.clickOnObject(ClickOnSubjects);
        Thread.sleep(2000);
        actionClass.setValueinTextbox(SearchField, SubjectName);
        actionClass.clickOnObject(ClickonEditSubject);
        Thread.sleep(2000);
        actionClass.setValueinTextbox(EditSubjectField, EditSubjectName);
        Thread.sleep(2000);
        actionClass.clickOnObject(TheoryRadioButton);
        Thread.sleep(1000);
        actionClass.setValueinTextbox(EditSubjectCodeField, EditSubjectCode);
        Thread.sleep(2000);
        actionClass.clickOnObject(EditSaveButton);
        VerificationClass verificationClass = new VerificationClass(this.driver1, extentTest);
        verificationClass.verifyTextPresent(this.SubjectSuccessMessage, "Subject updated successfully");
        actionClass.captureScreen("Subjects_4");
        actionClass.setValueinTextbox(SearchField, EditSubjectName);
        Thread.sleep(2000);
        actionClass.captureScreen("Subjects_5");

//      Check if the list is empty or not.
        Boolean isPresent = driver1.findElements(By.xpath("//*[@id=\"DataTables_Table_0\"]/tbody/tr[1]/td[2]")).size() > 0;
        ArrayList<String> ListSubjectNameinFront = new ArrayList<String>();
        ArrayList<String> ListSubjectCodeinFront = new ArrayList<String>();
        ArrayList<String> ListSubjectTypeinFront = new ArrayList<String>();
        if(isPresent==true){
            List<WebElement> ListSubject = driver1.findElements(By.xpath("//*[@id=\"DataTables_Table_0\"]/tbody/tr"));
            Thread.sleep(3000);
            int listsize = ListSubject.size();
            for (int i = 1; i <= listsize; i++) {
                String s = driver1.findElement(By.xpath("//*[@id=\"DataTables_Table_0\"]/tbody/tr[" + i + "]/td[1]")).getText();
                String s1 = driver1.findElement(By.xpath("//*[@id=\"DataTables_Table_0\"]/tbody/tr[" + i + "]/td[2]")).getText();
                String s2 = driver1.findElement(By.xpath("//*[@id=\"DataTables_Table_0\"]/tbody/tr[" + i + "]/td[3]")).getText();
                System.out.println("Value in list is: " + s);
                System.out.println("Value in list is: " + s1);
                System.out.println("Value in list is: " + s2);
                ListSubjectNameinFront.add(driver1.findElement(By.xpath("//*[@id=\"DataTables_Table_0\"]/tbody/tr[" + i + "]/td[1]")).getText());
                ListSubjectCodeinFront.add(driver1.findElement(By.xpath("//*[@id=\"DataTables_Table_0\"]/tbody/tr[" + i + "]/td[2]")).getText());
                ListSubjectTypeinFront.add(driver1.findElement(By.xpath("//*[@id=\"DataTables_Table_0\"]/tbody/tr[" + i + "]/td[3]")).getText());
            }
            System.out.println(ListSubjectNameinFront);
            System.out.println(ListSubjectCodeinFront);
            System.out.println(ListSubjectTypeinFront);
            System.out.println("The subject is added.");
            extentTest.log(Status.PASS, "The subject is added and present in the list.");
        }else{
            System.out.println("There is no such entry in the list");
            extentTest.log(Status.FAIL, "There is no such entry in the list");
        }

//      The List of Subjects in Database
        DatabaseFunctions DAB = new DatabaseFunctions(extentTest);
        conn = DAB.connect();
        statement = conn.createStatement();
        String subjects = "SELECT * FROM `subjects` WHERE name = '" + EditSubjectName + "'";
        queryRs = statement.executeQuery(subjects);
        ArrayList<String> subjectNamesListinDB = new ArrayList<String>();
        ArrayList<String> subjectCodeListinDB = new ArrayList<String>();
        ArrayList<String> subjectTypeListinDB = new ArrayList<String>();
        if (queryRs.next()) {
            String subjectnames = null;
            String subjectcode = null;
            String subjecttype = null;
            subjectnames = queryRs.getString("name");
            subjectcode = queryRs.getString("code");
            subjecttype = queryRs.getString("type");
            System.out.println("Value in database list: " + subjectnames);
            System.out.println("Value in database list: " + subjectcode);
            System.out.println("Value in database list: " + subjecttype);
            subjectNamesListinDB.add(queryRs.getString("name"));
            subjectCodeListinDB.add(queryRs.getString("code"));
            subjectTypeListinDB.add(queryRs.getString("type"));
            System.out.println(subjectNamesListinDB);
            System.out.println(subjectCodeListinDB);
            System.out.println(subjectTypeListinDB);
            extentTest.log(Status.PASS, "The entered data is in database");
        }else{
            System.out.println("There is no such entry in database.");
            extentTest.log(Status.FAIL,"There is no such entry in database.");
        }
        actionClass.CompareStringList(ListSubjectNameinFront, subjectNamesListinDB);
        actionClass.CompareStringList(ListSubjectCodeinFront, subjectCodeListinDB);
        actionClass.CompareStringList(ListSubjectTypeinFront, subjectTypeListinDB);
    }

//  Test by deleting subject and compare with database.
    public void DeleteSubject(String SubjectName) throws InterruptedException, IOException, SQLException, AWTException {
        ActionClass actionClass= new ActionClass(driver1, extentTest);
//       actionClass.clickOnObject(ClickOnAcademics);
        Thread.sleep(5000);
        actionClass.clickOnObject(ClickOnSubjects);
        Thread.sleep(2000);
        actionClass.setValueinTextbox(SearchField, SubjectName);
        actionClass.clickOnObject(ClickonDeleteSubject);
        Thread.sleep(2000);
        Alert alert = driver1.switchTo().alert();
        String alertMessage= driver1.switchTo().alert().getText();
        System.out.println(alertMessage);
        String expectedAlertMessage= "Are you sure you want to delete this item?";
        if(alertMessage.equals(expectedAlertMessage)){
            System.out.println("The expected alert message matches with actual alert message.");
            extentTest.log(Status.PASS, "The expected alert message matches with actual alert message.");
        }else{
            System.out.println("The expected alert message does not match with actual alert message.");
            extentTest.log(Status.FAIL, "The expected alert message does not match with actual alert message.");
        }
        Thread.sleep(3000);
        actionClass.captureScreenUsingRobot("Subjects_6");
        alert.accept();
        Thread.sleep(3000);
        VerificationClass verificationClass = new VerificationClass(driver1, extentTest);
        //verificationClass.verifyTextPresent(DeleteSuccessMessage, "Subject deleted successfully");
        Thread.sleep(1000);
        actionClass.captureScreen("Subjects_7");

//      Test if the entry is not in list
        actionClass.setValueinTextbox(SearchField, SubjectName);
        Thread.sleep(2000);
        actionClass.captureScreen("Subjects_8");
        Boolean isPresent = driver1.findElements(By.xpath("//*[@id=\"DataTables_Table_0\"]/tbody/tr[1]/td[2]")).size() > 0;
        ArrayList<String> ListSubjectNameinFront = new ArrayList<String>();
        ArrayList<String> ListSubjectCodeinFront = new ArrayList<String>();
        ArrayList<String> ListSubjectTypeinFront = new ArrayList<String>();
        if(isPresent==true){
            List<WebElement> ListSubject = driver1.findElements(By.xpath("//*[@id=\"DataTables_Table_0\"]/tbody/tr"));
            Thread.sleep(3000);
            int listsize = ListSubject.size();
            for (int i = 1; i <= listsize; i++) {
                String s = driver1.findElement(By.xpath("//*[@id=\"DataTables_Table_0\"]/tbody/tr[" + i + "]/td[1]")).getText();
                String s1 = driver1.findElement(By.xpath("//*[@id=\"DataTables_Table_0\"]/tbody/tr[" + i + "]/td[2]")).getText();
                String s2 = driver1.findElement(By.xpath("//*[@id=\"DataTables_Table_0\"]/tbody/tr[" + i + "]/td[3]")).getText();
                System.out.println("Value in list is: " + s);
                System.out.println("Value in list is: " + s1);
                System.out.println("Value in list is: " + s2);
                ListSubjectNameinFront.add(driver1.findElement(By.xpath("//*[@id=\"DataTables_Table_0\"]/tbody/tr[" + i + "]/td[1]")).getText());
                ListSubjectCodeinFront.add(driver1.findElement(By.xpath("//*[@id=\"DataTables_Table_0\"]/tbody/tr[" + i + "]/td[2]")).getText());
                ListSubjectTypeinFront.add(driver1.findElement(By.xpath("//*[@id=\"DataTables_Table_0\"]/tbody/tr[" + i + "]/td[3]")).getText());
            }
            System.out.println(ListSubjectNameinFront);
            System.out.println(ListSubjectCodeinFront);
            System.out.println(ListSubjectTypeinFront);
            System.out.println("The subject is added.");
            extentTest.log(Status.PASS, "The subject is added and present in the list.");
        }else{
            System.out.println("There is no such entry in the list");
            extentTest.log(Status.INFO, "There is no such entry in the list");
        }

//      The List of Subjects in Database
        DatabaseFunctions DAB = new DatabaseFunctions(extentTest);
        conn = DAB.connect();
        statement = conn.createStatement();
        String subjects = "SELECT * FROM `subjects` WHERE name = '" + SubjectName + "'";
        queryRs = statement.executeQuery(subjects);
        ArrayList<String> subjectNamesListinDB = new ArrayList<String>();
        ArrayList<String> subjectCodeListinDB = new ArrayList<String>();
        ArrayList<String> subjectTypeListinDB = new ArrayList<String>();
        if (queryRs.next()) {
            String subjectnames = null;
            String subjectcode = null;
            String subjecttype = null;
            subjectnames = queryRs.getString("name");
            subjectcode = queryRs.getString("code");
            subjecttype = queryRs.getString("type");
            System.out.println("Value in database list: " + subjectnames);
            System.out.println("Value in database list: " + subjectcode);
            System.out.println("Value in database list: " + subjecttype);
            subjectNamesListinDB.add(queryRs.getString("name"));
            subjectCodeListinDB.add(queryRs.getString("code"));
            subjectTypeListinDB.add(queryRs.getString("type"));
            System.out.println(subjectNamesListinDB);
            System.out.println(subjectCodeListinDB);
            System.out.println(subjectTypeListinDB);
            extentTest.log(Status.PASS, "The entered data is in database");
        } else{
            System.out.println("There is no such entry in database.");
            extentTest.log(Status.INFO,"There is no such entry in database.");
        }
        actionClass.CompareStringList(ListSubjectNameinFront, subjectNamesListinDB);
        actionClass.CompareStringList(ListSubjectCodeinFront, subjectCodeListinDB);
        actionClass.CompareStringList(ListSubjectTypeinFront, subjectTypeListinDB);
    }
}