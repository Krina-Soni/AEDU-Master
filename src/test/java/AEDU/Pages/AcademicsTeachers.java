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

import java.awt.*;
import java.io.IOException;
import java.sql.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class AcademicsTeachers {
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

    @FindBy(how = How.XPATH, using = "//*[@id=\"sibe-box\"]/ul[2]/li[3]/ul/li[4]/a")
    private WebElement ClickOnTeachers;

    @FindBy(how = How.CSS, using = ".form-group:nth-child(2) > #category")
    private WebElement TeacherNameField;

    @FindBy(how = How.CSS, using = ".form-group:nth-child(3) > #category")
    private WebElement EmailField;

    @FindBy(how = How.XPATH, using = "//*[@id=\"form1\"]/div[1]/div[3]/select")
    private WebElement ClickGender;

    @FindBy(how = How.XPATH, using = "//*[@id=\"form1\"]/div[1]/div[3]/select/option[3]")
    private WebElement SelectFemaleGender;

    @FindBy(how = How.XPATH, using = "//*[@id=\"form1\"]/div[1]/div[3]/select/option[2]")
    private WebElement SelectMaleGender;

    @FindBy(how = How.XPATH, using = "//form[@id='form1']/div/div[4]/input")
    private WebElement DateOfBirthField;

    @FindBy(how = How.CSS, using = ".datepicker-days .datepicker-switch")
    private WebElement SwitchToMonth;

    @FindBy(how = How.CSS, using = ".datepicker-months .datepicker-switch")
    private WebElement SwitchToYear;

    @FindBy(how = How.CSS, using = ".datepicker-years .prev")
    private WebElement ChangeYear1;

    @FindBy(how = How.CSS, using = ".datepicker-years .prev")
    private WebElement ChangeYear2;

    @FindBy(how = How.CSS, using = ".datepicker-years .prev")
    private WebElement ChangeYear3;

    @FindBy(how = How.CSS, using = ".year:nth-child(7)")
    private WebElement SelectYear;

    @FindBy(how = How.CSS, using = ".month:nth-child(7)")
    private WebElement SelectMonth;

    @FindBy(how = How.CSS, using = "tr:nth-child(3) > .day:nth-child(5)")
    private WebElement SelectDay;

    @FindBy(how = How.XPATH, using = "//textarea[@id='address']")
    private WebElement AddressField;

    @FindBy(how = How.XPATH, using = "//input[@id='phone']")
    private WebElement PhoneField;

    @FindBy(how = How.XPATH, using = "//input[@id='punch_id']")
    private WebElement PunchIdField;

    @FindBy(how = How.XPATH, using = "//*[@id=\"form1\"]/div[1]/div[8]/div/label")
    private WebElement ChooseFileField;

    @FindBy(how = How.XPATH, using = "//*[@id=\"form1\"]/div[2]/button")
    private WebElement AddSaveButton;

    @FindBy(how = How.CSS, using = ".form-group:nth-child(2) p")
    private WebElement TeacherNameValidation;

    @FindBy(how = How.CSS, using = ".form-group:nth-child(3) p")
    private WebElement EmailValidation;

    @FindBy(how = How.CSS, using = ".form-group:nth-child(4) p")
    private WebElement GenderValidation;

    @FindBy(how = How.CSS, using = ".form-group:nth-child(5) p")
    private WebElement DateOFBirthValidation;

    @FindBy(how = How.CSS, using = ".form-group:nth-child(7) p")
    private WebElement PhoneValidation;

    @FindBy(how = How.XPATH, using = "//*[@id=\"form1\"]/div[1]/div[1]")
    private WebElement TeacherAddSuccess;

    @FindBy(how = How.XPATH, using = "//*[@id=\"DataTables_Table_0_filter\"]/label/input")
    private WebElement SearchField;

    @FindBy(how = How.XPATH, using = "//*[@id=\"form1\"]/div[1]/div[1]")
    private WebElement TeacherEditSuccess;

    @FindBy(how = How.XPATH, using = "//*[@id=\"DataTables_Table_0\"]/tbody/tr/td[6]/a[2]")
    private WebElement EditButton;

    @FindBy(how = How.XPATH, using = "//*[@id=\"employeeform\"]/div[2]/button")
    private WebElement EditSaveButton;

    @FindBy(how = How.XPATH, using = "//*[@id=\"DataTables_Table_0\"]/tbody/tr/td[6]/a[3]")
    private WebElement ClickonDeleteTeacher;

    @FindBy(how = How.XPATH, using = "//*[@id=\"form1\"]/div[1]/div[1]")
    private WebElement DeleteSuccessMessage;

    public AcademicsTeachers(WebDriver driver, ExtentTest test) throws InterruptedException, IOException {
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

//  Test all mandatory field validations.
    public void TeacherValidation() throws IOException, InterruptedException {
        ActionClass actionClass = new ActionClass(driver1, extentTest);
//        actionClass.clickOnObject(ClickOnAcademics);
        Thread.sleep(5000);
        actionClass.clickOnObject(ClickOnTeachers);
        Thread.sleep(3000);
        JavascriptExecutor jsetaskscore1 = (JavascriptExecutor) driver1;
        jsetaskscore1.executeScript("scrollBy(0, 600)");
        Thread.sleep(3000);
        actionClass.clickOnObject(AddSaveButton);
        Thread.sleep(3000);
        actionClass.captureScreen("Teachers_1");
        VerificationClass verificationClass = new VerificationClass(this.driver1, extentTest);
        verificationClass.verifyTextPresent(this.TeacherNameValidation, "The Teacher field is required.");
        verificationClass.verifyTextPresent(this.EmailValidation, "The Email field is required.");
        verificationClass.verifyTextPresent(this.GenderValidation, "The Gender field is required.");
        jsetaskscore1.executeScript("scrollBy(0, 300)");
        Thread.sleep(3000);
        actionClass.captureScreen("Teachers_2");
        verificationClass.verifyTextPresent(this.DateOFBirthValidation, "The Date of Birth field is required.");
        verificationClass.verifyTextPresent(this.PhoneValidation, "The Phone field is required.");
    }
//   Test by adding teacher and compare with database.
    public void AddTeacher(String TeacherName, String Email, String Address, String Phone, String PunchId) throws InterruptedException, IOException, SQLException {
        ActionClass actionClass = new ActionClass(driver1, extentTest);
      //actionClass.clickOnObject(ClickOnAcademics);
        Thread.sleep(5000);
        actionClass.clickOnObject(ClickOnTeachers);
        Thread.sleep(2000);
        actionClass.setValueinTextbox(TeacherNameField, TeacherName);
        Thread.sleep(2000);
        actionClass.setValueinTextbox(EmailField, Email);
        Thread.sleep(2000);
        actionClass.clickOnObject(ClickGender);
        Thread.sleep(2000);
        actionClass.clickOnObject(SelectFemaleGender);
        Thread.sleep(2000);
        JavascriptExecutor jsetaskscore1 = (JavascriptExecutor) driver1;
        jsetaskscore1.executeScript("scrollBy(0, 300)");
        actionClass.clickOnObject(DateOfBirthField);
        Thread.sleep(2000);
        actionClass.clickOnObject(SwitchToMonth);
        Thread.sleep(2000);
        actionClass.clickOnObject(SwitchToYear);
        Thread.sleep(2000);
        actionClass.clickOnObject(ChangeYear1);
        Thread.sleep(2000);
        actionClass.clickOnObject(ChangeYear2);
        Thread.sleep(2000);
        actionClass.clickOnObject(ChangeYear3);
        Thread.sleep(2000);
        actionClass.clickOnObject(SelectYear);
        Thread.sleep(2000);
        actionClass.clickOnObject(SelectMonth);
        Thread.sleep(2000);
        actionClass.clickOnObject(SelectDay);
        Thread.sleep(2000);
        actionClass.setValueinTextbox(AddressField, Address);
        Thread.sleep(2000);
        actionClass.setValueinTextbox(PhoneField, Phone);
        Thread.sleep(2000);
        jsetaskscore1.executeScript("scrollBy(0, 300)");
        actionClass.setValueinTextbox(PhoneField, Phone);
        Thread.sleep(2000);
        actionClass.setValueinTextbox(PunchIdField, PunchId);
        Thread.sleep(2000);
        actionClass.clickOnObject(AddSaveButton);
        Thread.sleep(2000);
        actionClass.captureScreen("Teachers_3");
        VerificationClass verificationClass = new VerificationClass(this.driver1, extentTest);
        verificationClass.verifyTextPresent(this.TeacherAddSuccess, "Teacher added successfully");
        actionClass.setValueinTextbox(SearchField, TeacherName);
        Thread.sleep(2000);
        actionClass.captureScreen("Teachers_4");

//      Check if the list is empty or not.
        Boolean isPresent = driver1.findElements(By.xpath("//*[@id=\"DataTables_Table_0\"]/tbody/tr[1]/td[2]")).size() > 0;
        ArrayList<String> ListTeacherNameinFront = new ArrayList<String>();
        ArrayList<String> ListTeacherEmailinFront = new ArrayList<String>();
        ArrayList<String> ListTeacherDOBinFront = new ArrayList<String>();
        ArrayList<String> ListTeacherPhoneinFront = new ArrayList<String>();
        if(isPresent==true){
            List<WebElement> ListSubject = driver1.findElements(By.xpath("//*[@id=\"DataTables_Table_0\"]/tbody/tr"));
            Thread.sleep(3000);
            int listsize = ListSubject.size();
            for (int i = 1; i <= listsize; i++) {
                String s = driver1.findElement(By.xpath("//*[@id=\"DataTables_Table_0\"]/tbody/tr[" + i + "]/td[1]")).getText();
                String s1 = driver1.findElement(By.xpath("//*[@id=\"DataTables_Table_0\"]/tbody/tr[" + i + "]/td[2]")).getText();
                String s2 = driver1.findElement(By.xpath("//*[@id=\"DataTables_Table_0\"]/tbody/tr[" + i + "]/td[3]")).getText();
                String s3 = driver1.findElement(By.xpath("//*[@id=\"DataTables_Table_0\"]/tbody/tr[" + i + "]/td[5]")).getText();
                System.out.println("Value in list is: " + s);
                System.out.println("Value in list is: " + s1);
                System.out.println("Value in list is: " + s2);
                System.out.println("Value in list is: " + s3);
                ListTeacherNameinFront.add(driver1.findElement(By.xpath("//*[@id=\"DataTables_Table_0\"]/tbody/tr[" + i + "]/td[1]")).getText());
                ListTeacherEmailinFront.add(driver1.findElement(By.xpath("//*[@id=\"DataTables_Table_0\"]/tbody/tr[" + i + "]/td[2]")).getText());
                ListTeacherDOBinFront.add(driver1.findElement(By.xpath("//*[@id=\"DataTables_Table_0\"]/tbody/tr[" + i + "]/td[3]")).getText());
                ListTeacherPhoneinFront.add(driver1.findElement(By.xpath("//*[@id=\"DataTables_Table_0\"]/tbody/tr[" + i + "]/td[5]")).getText());
            }
            System.out.println(ListTeacherNameinFront);
            System.out.println(ListTeacherEmailinFront);
            System.out.println(ListTeacherDOBinFront);
            System.out.println(ListTeacherPhoneinFront);
            System.out.println("The subject is added.");
            extentTest.log(Status.PASS, "The subject is added and present in the list.");
        }else{
            System.out.println("There is no such entry in the list");
            extentTest.log(Status.FAIL, "There is no such entry in the list");
        }

//      The List of teacher in Database
        DatabaseFunctions DAB = new DatabaseFunctions(extentTest);
        conn = DAB.connect();
        statement = conn.createStatement();
        String teachers = "SELECT * FROM `teachers` WHERE name = '" + TeacherName + "'";
        queryRs = statement.executeQuery(teachers);
        ArrayList<String> teacherNameListinDB = new ArrayList<String>();
        ArrayList<String> teacherEmailListinDB = new ArrayList<String>();
        ArrayList<String> teacherDOBListinDB = new ArrayList<String>();
        ArrayList<String> teacherPhoneListinDB = new ArrayList<String>();
        if (queryRs.next()) {
            String teachername = null;
            String teacheremail = null;
            Date teacherdob = null;
            String teacherphone = null;
            teachername = queryRs.getString("name");
            teacheremail = queryRs.getString("email");
            teacherdob = queryRs.getDate("dob");
            DateFormat formatter = new SimpleDateFormat("dd.MM.yyyy");
            String strDate= formatter.format(teacherdob);
            teacherphone = queryRs.getString("phone");
            System.out.println("Value in database list: " + teachername);
            System.out.println("Value in database list: " + teacheremail);
            System.out.println("Value in database list: " + teacherdob);
            System.out.println("Value in database list: " + teacherphone);
            teacherNameListinDB.add(teachername);
            teacherEmailListinDB.add(teacheremail);
            teacherDOBListinDB.add(strDate);
            teacherPhoneListinDB.add(teacherphone);
            System.out.println(teacherNameListinDB);
            System.out.println(teacherEmailListinDB);
            System.out.println(teacherDOBListinDB);
            System.out.println(teacherPhoneListinDB);
            extentTest.log(Status.PASS, "The entered data is in database");
        }else{
            System.out.println("There is no such entry in database.");
            extentTest.log(Status.FAIL,"There is no such entry in database.");
        }

//      Compare front and db lists
        actionClass.CompareStringList(ListTeacherNameinFront, teacherNameListinDB);
        actionClass.CompareStringList(ListTeacherEmailinFront, teacherEmailListinDB);
        actionClass.CompareStringList(ListTeacherDOBinFront, teacherDOBListinDB);
        actionClass.CompareStringList(ListTeacherPhoneinFront, teacherPhoneListinDB);
    }
//  Test by editing teacher and compare with database.
    public void EditTeacher(String TeacherName, String EditTeacherName, String EditEmail, String Phone, String PunchId ) throws InterruptedException, IOException, SQLException {
        ActionClass actionClass = new ActionClass(driver1, extentTest);
      //actionClass.clickOnObject(ClickOnAcademics);
        Thread.sleep(7000);
        actionClass.clickOnObject(ClickOnTeachers);
        Thread.sleep(2000);
        actionClass.setValueinTextbox(SearchField, TeacherName);
        Thread.sleep(2000);
        actionClass.clickOnObject(EditButton);
        Thread.sleep(4000);
        actionClass.setValueinTextbox(TeacherNameField, EditTeacherName);
        Thread.sleep(2000);
        actionClass.setValueinTextbox(EmailField, EditEmail);
        Thread.sleep(2000);
        JavascriptExecutor jsetaskscore1 = (JavascriptExecutor) driver1;
        jsetaskscore1.executeScript("scrollBy(0, -800)");
        Thread.sleep(2000);
        PhoneField.clear();
        actionClass.setValueinTextbox(PhoneField, Phone);
        Thread.sleep(2000);
        PunchIdField.clear();
        actionClass.setValueinTextbox(PunchIdField, PunchId);
        Thread.sleep(2000);
        JavascriptExecutor jsetaskscore2 = (JavascriptExecutor) driver1;
        jsetaskscore2.executeScript("scrollBy(0, -500)");
        actionClass.clickOnObject(EditSaveButton);
        Thread.sleep(2000);
        actionClass.captureScreen("Teachers_5");
        VerificationClass verificationClass = new VerificationClass(this.driver1, extentTest);
        verificationClass.verifyTextPresent(this.TeacherEditSuccess, "Teacher updated successfully");
        actionClass.setValueinTextbox(SearchField, EditTeacherName);
        Thread.sleep(2000);
        actionClass.captureScreen("Teachers_6");

//      Check if the list is empty or not.
        Boolean isPresent = driver1.findElements(By.xpath("//*[@id=\"DataTables_Table_0\"]/tbody/tr[1]/td[2]")).size() > 0;
        ArrayList<String> ListTeacherNameinFront = new ArrayList<String>();
        ArrayList<String> ListTeacherEmailinFront = new ArrayList<String>();
        if(isPresent==true){
            List<WebElement> ListSubject = driver1.findElements(By.xpath("//*[@id=\"DataTables_Table_0\"]/tbody/tr"));
            Thread.sleep(3000);
            int listsize = ListSubject.size();
            for (int i = 1; i <= listsize; i++) {
                String s = driver1.findElement(By.xpath("//*[@id=\"DataTables_Table_0\"]/tbody/tr[" + i + "]/td[1]")).getText();
                String s1 = driver1.findElement(By.xpath("//*[@id=\"DataTables_Table_0\"]/tbody/tr[" + i + "]/td[2]")).getText();
                System.out.println("Value in list is: " + s);
                System.out.println("Value in list is: " + s1);
                ListTeacherNameinFront.add(driver1.findElement(By.xpath("//*[@id=\"DataTables_Table_0\"]/tbody/tr[" + i + "]/td[1]")).getText());
                ListTeacherEmailinFront.add(driver1.findElement(By.xpath("//*[@id=\"DataTables_Table_0\"]/tbody/tr[" + i + "]/td[2]")).getText());
            }
            System.out.println(ListTeacherNameinFront);
            System.out.println(ListTeacherEmailinFront);
            System.out.println("The subject is added.");
            extentTest.log(Status.PASS, "The subject is added and present in the list.");
        }else{
            System.out.println("There is no such entry in the list");
            extentTest.log(Status.FAIL, "There is no such entry in the list");
        }

//      The List of teacher in Database
        DatabaseFunctions DAB = new DatabaseFunctions(extentTest);
        conn = DAB.connect();
        statement = conn.createStatement();
        String teachers = "SELECT * FROM `teachers` WHERE name = '" + EditTeacherName + "'";
        queryRs = statement.executeQuery(teachers);
        ArrayList<String> teacherNameListinDB = new ArrayList<String>();
        ArrayList<String> teacherEmailListinDB = new ArrayList<String>();
        if (queryRs.next()) {
            String teachername = null;
            String teacheremail = null;
            Date teacherdob = null;
            String teacherphone = null;
            teachername = queryRs.getString("name");
            teacheremail = queryRs.getString("email");
            System.out.println("Value in database list: " + teachername);
            System.out.println("Value in database list: " + teacheremail);
            teacherNameListinDB.add(teachername);
            teacherEmailListinDB.add(teacheremail);
            System.out.println(teacherNameListinDB);
            System.out.println(teacherEmailListinDB);
            extentTest.log(Status.PASS, "The entered data is in database");
        }else{
            System.out.println("There is no such entry in database.");
            extentTest.log(Status.FAIL,"There is no such entry in database.");
        }

//      Compare front and db lists
        actionClass.CompareStringList(ListTeacherNameinFront, teacherNameListinDB);
        actionClass.CompareStringList(ListTeacherEmailinFront, teacherEmailListinDB);
    }
//  Test by deleting teacher and compare with database.
    public void DeleteTeacher(String TeacherName) throws InterruptedException, IOException, SQLException, AWTException {
        ActionClass actionClass= new ActionClass(driver1, extentTest);
//      actionClass.clickOnObject(ClickOnAcademics);
        Thread.sleep(7000);
        actionClass.clickOnObject(ClickOnTeachers);
        Thread.sleep(2000);
        actionClass.setValueinTextbox(SearchField, TeacherName);
        Thread.sleep(3000);
        JavascriptExecutor jse = (JavascriptExecutor) driver1;
        jse.executeScript("scrollBy(0, -600)");
        WebElement deletebutton = driver1.findElement(By.xpath("//*[@id=\"DataTables_Table_0\"]/tbody/tr/td[6]/a[3]"));
        jse.executeScript("arguments[0].scrollIntoView();",deletebutton);
        JavascriptExecutor jsetaskscore1 = (JavascriptExecutor) driver1;
        jsetaskscore1.executeScript("scrollBy(0, -600)");
        Thread.sleep(3000);
        actionClass.clickOnObject(ClickonDeleteTeacher);
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
        actionClass.captureScreenUsingRobot("Teachers_7");
        alert.accept();
        Thread.sleep(3000);
        VerificationClass verificationClass = new VerificationClass(driver1, extentTest);
        verificationClass.verifyTextPresent(DeleteSuccessMessage, "Subject deleted successfully");
        Thread.sleep(1000);
        actionClass.captureScreen("Teachers_8");

//      Test if the entry is not in list
        actionClass.setValueinTextbox(SearchField, TeacherName);
        Thread.sleep(2000);
        actionClass.captureScreen("Subjects_8");
        Boolean isPresent = driver1.findElements(By.xpath("//*[@id=\"DataTables_Table_0\"]/tbody/tr[1]/td[2]")).size() > 0;
        ArrayList<String> ListTeacherNameinFront = new ArrayList<String>();
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
                ListTeacherNameinFront.add(driver1.findElement(By.xpath("//*[@id=\"DataTables_Table_0\"]/tbody/tr[" + i + "]/td[1]")).getText());
            }
            System.out.println(ListTeacherNameinFront);
            System.out.println("The subject is added.");
            extentTest.log(Status.PASS, "The subject is added and present in the list.");
        }else{
            System.out.println("There is no such entry in the list");
            extentTest.log(Status.FAIL, "There is no such entry in the list");
        }

//      The List of teacher in Database
        DatabaseFunctions DAB = new DatabaseFunctions(extentTest);
        conn = DAB.connect();
        statement = conn.createStatement();
        String subjects = "SELECT * FROM `teachers` WHERE name = '" + TeacherName + "'";
        queryRs = statement.executeQuery(subjects);
        ArrayList<String> subjectTeacherNamesListinDB = new ArrayList<String>();
        if (queryRs.next()) {
            String teachernames = null;
            teachernames = queryRs.getString("name");
            System.out.println("Value in database list: " + teachernames);
            subjectTeacherNamesListinDB.add(queryRs.getString("name"));
            System.out.println(subjectTeacherNamesListinDB);
            extentTest.log(Status.PASS, "The entered data is in database");
        } else{
            System.out.println("There is no such entry in database.");
            extentTest.log(Status.FAIL,"There is no such entry in database.");
        }
        actionClass.CompareStringList(ListTeacherNameinFront, subjectTeacherNamesListinDB);
    }
}
