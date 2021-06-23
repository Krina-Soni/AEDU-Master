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

public class AssignSubject {
    Connection conn = null;
    String url = "jdbc:mysql://localhost:3306/";
    String dbName = "aedudev1";
    String driver1 = "com.mysql.jdbc.Driver";
    String userName = "root";
    String password = "root";
    Statement statement;
    ResultSet queryRs;
    ResultSet Qcount;
    WebDriver driver;
    ExtentTest extentTest;

    @FindBy(how = How.XPATH,
            using = "/html/body/div[1]/aside/div/section/ul[2]/li[3]/a/span")
    private WebElement SideMenuAcademics;

    @FindBy(how = How.XPATH,
            using = "/html/body/div[1]/aside/div/section/ul[2]/li[3]/ul/li[2]/a")
    private WebElement AssignSubjectClick;

    @FindBy(how = How.XPATH,
            using = "/html/body/div[1]/div[1]/section[2]/div[1]/form/div/button/i")
    private WebElement SearchBtn;

    @FindBy(how = How.XPATH,
            using = "/html/body/div[1]/div[1]/section[2]/div[1]/form/div/div/div[2]/div/span")
    private WebElement SelectClassFieldValidation;

    @FindBy(how = How.XPATH,
            using = "/html/body/div[1]/div[1]/section[2]/div[1]/form/div/div/div[3]/div/span")
    private WebElement SelectSectionFieldValidation;

    @FindBy(how = How.XPATH,
            using = "//*[@id=\"class_id\"]")
    private WebElement SelectClass;

    @FindBy(how = How.XPATH,
            using = "//*[@id=\"class_id\"]/option[3]")
    private WebElement SelectClassOption;

    @FindBy(how = How.XPATH,
            using = "//*[@id=\"section_id\"]")
    private WebElement SelectSection;

    @FindBy(how = How.XPATH,
            using = "//*[@id=\"section_id\"]/option[2]")
    private WebElement SelectSectionOption;

    @FindBy(how = How.XPATH,
            using = "//*[@id=\"subject_id_0\"]")
    private WebElement SelectSubject;

    @FindBy(how = How.XPATH,
            using = "//*[@id=\"class_id\"]/option[3]")
    private WebElement SelectSubjectOption;

    @FindBy(how = How.XPATH,
            using = "//*[@id=\"teacher_id_0\"]")
    private WebElement SelectTeacher;

    @FindBy(how = How.XPATH,
            using = "//*[@id=\"teacher_id_0\"]/option[2]")
    private WebElement SelectTeacherOption;

    @FindBy(how = How.XPATH,
            using = "//*[@id=\"btnAdd\"]")
    private WebElement AddBtn;

    @FindBy(how = How.XPATH,
            using = "//*[@id=\"subject_id_1\"]")
    private WebElement SelectSubject2;

    @FindBy(how = How.XPATH,
            using = "//*[@id=\"subject_id_1\"]/option[3]")
    private WebElement SelectSubjectOption2;

    @FindBy(how = How.XPATH,
            using = "//*[@id=\"teacher_id_1\"]")
    private WebElement SelectTeacher2;

    @FindBy(how = How.XPATH,
            using = "//*[@id=\"teacher_id_1\"]")
    private WebElement SelectTeacheroption2;

    @FindBy(how = How.XPATH,
            using = "//*[@id=\"subject_id_7\"]")
    private WebElement subject7;

    @FindBy(how = How.XPATH,
            using = "//*[@id=\"subject_id_7\"]/option[6]")
    private WebElement subject7value;

    @FindBy(how = How.XPATH,
            using = "//*[@id=\"teacher_id_7\"]")
    private WebElement teacher7;

    @FindBy(how = How.XPATH,
            using = "//*[@id=\"teacher_id_7\"]/option[4]")
    private WebElement selectteacher;


    @FindBy(how = How.XPATH,
            using = "//*[@id=\"submit\"]")
    private WebElement Savebtn;

    @FindBy(how = How.XPATH,
            using = "//*[@id=\"btnRemove\"]")
    private WebElement Deletebtn;

    @FindBy(how = How.XPATH,
            using = "/html/body/div[1]/div[1]/section[2]/div[1]/form/div/div/div[1]/div")
    private WebElement Textverification;

    public AssignSubject(WebDriver driver, ExtentTest test) {
        this.driver = driver;
        this.extentTest = test;
        PageFactory.initElements(driver, this);
    }
    public void openNewTab() throws InterruptedException {
        Thread.sleep(2000);
        for (String childTab : driver.getWindowHandles()) {
            driver.switchTo().window(childTab);
        }
        Thread.sleep(1000);
    }

    public void CheckFiltervalidation() throws IOException, InterruptedException {
        ActionClass actionClass = new ActionClass(driver, extentTest);
        Thread.sleep(5000);
        actionClass.clickOnObject(this.SideMenuAcademics);
        Thread.sleep(5000);
        actionClass.clickOnObject(this.AssignSubjectClick);
        Thread.sleep(2000);
        actionClass.clickOnObject(this.SearchBtn);
        VerificationClass very = new VerificationClass(driver, extentTest);
        very.verifyTextPresent(this.SelectClassFieldValidation, "The Class field is required.");
        very.verifyTextPresent(this.SelectSectionFieldValidation, "The Section field is required.");
        actionClass.captureScreen("Val1");
        //check individul field validation
        actionClass.clickOnObject(this.AssignSubjectClick);
        Thread.sleep(2000);
        actionClass.clickOnObject(this.SelectClass);
        Thread.sleep(2000);
        actionClass.clickOnObject(this.SelectClassOption);
        Thread.sleep(2000);
        actionClass.clickOnObject(this.SearchBtn);
        Thread.sleep(2000);
        very.verifyTextPresent(this.SelectSectionFieldValidation, "The Section field is required.");
        Thread.sleep(1000);
        actionClass.captureScreen("Val2");
    }

    public void CheckAssignSubjectFunctionality() throws InterruptedException, InterruptedException, IOException {
        ActionClass actionClass = new ActionClass(driver, extentTest);
       //actionClass.clickOnObject(this.SideMenuAcademics);
        Thread.sleep(5000);
        actionClass.clickOnObject(this.AssignSubjectClick);
        actionClass.clickOnObject(this.SelectClass);
        actionClass.clickOnObject(this.SelectClassOption);
        Thread.sleep(1000);
        actionClass.clickOnObject(this.SelectSection);
        actionClass.clickOnObject(this.SelectSectionOption);
        actionClass.clickOnObject(this.SearchBtn);
        Thread.sleep(1000);
        actionClass.clickOnObject(this.SelectSubject);
        actionClass.clickOnObject(this.SelectSubjectOption);
        actionClass.clickOnObject(this.SelectTeacher);
        actionClass.clickOnObject(this.SelectTeacherOption);
        actionClass.clickOnObject(this.AddBtn);
        JavascriptExecutor jsetaskscore = (JavascriptExecutor) driver;
        jsetaskscore.executeScript("scrollBy(0,350)");
        actionClass.clickOnObject(this.SelectSubject2);
        actionClass.clickOnObject((this.SelectSubjectOption2));
        actionClass.clickOnObject(this.SelectTeacher2);
        actionClass.clickOnObject(this.SelectTeacheroption2);
        Thread.sleep(5000);
        JavascriptExecutor jsetaskscore3 = (JavascriptExecutor) driver;
        jsetaskscore3.executeScript("scrollBy(0,350)");
        actionClass.clickOnObject(this.subject7);
        actionClass.clickOnObject(this.subject7value);
        actionClass.clickOnObject(this.teacher7);
        actionClass.clickOnObject(this.selectteacher);
        Thread.sleep(5000);
        JavascriptExecutor jsetaskscore2 = (JavascriptExecutor) driver;
        jsetaskscore2.executeScript("scrollBy(0,350)");
        actionClass.clickOnObject(this.Savebtn);
        VerificationClass very = new VerificationClass(driver, extentTest);
        very.verifyTextPresent(this.Textverification, "Record updated successfully");
        actionClass.captureScreen("Record updated successfully");
    }

    public void CheckDataWithDatabase() throws IOException, InterruptedException, SQLException {
        ActionClass actionClass = new ActionClass(driver, extentTest);
        //actionClass.clickOnObject(this.SideMenuAcademics);
        Thread.sleep(5000);
        actionClass.clickOnObject(this.AssignSubjectClick);
        actionClass.clickOnObject(this.SelectClass);
        actionClass.clickOnObject(this.SelectClassOption);
        Thread.sleep(1000);
        actionClass.clickOnObject(this.SelectSection);
        actionClass.clickOnObject(this.SelectSectionOption);
        actionClass.captureScreen("s1");
        actionClass.clickOnObject(this.SearchBtn);
        JavascriptExecutor jsetaskscore = (JavascriptExecutor) driver;
        jsetaskscore.executeScript("scrollBy(0,350)");
        actionClass.captureScreen("s2");
        Thread.sleep(1000);
        actionClass.clickOnObject(this.SelectSubject);
        String s = driver.findElement(By.xpath("//*[@id=\"subject_id_0\"]/option[2]")).getText();
        actionClass.clickOnObject(this.SelectTeacher);
        String s1 = driver.findElement(By.xpath("//*[@id=\"teacher_id_0\"]/option[2]")).getText();
        System.out.println("Value in list is: " + s);
        System.out.println("Value in list is: " + s1);
        DatabaseFunctions DAB = new DatabaseFunctions(extentTest);
        conn = DAB.connect();
        statement = conn.createStatement();
        String AssignClass = "SELECT teacher_subjects.*, subjects.name, classes.class,sections.section, teachers.name FROM teacher_subjects JOIN subjects ON subjects.id = teacher_subjects.subject_id JOIN class_sections ON class_sections.id = teacher_subjects.class_section_id JOIN classes ON classes.id = class_sections.class_id JOIN sections ON sections.id = class_sections.section_id JOIN teachers ON teachers.id= teacher_subjects.teacher_id WHERE teacher_subjects.teacher_id= 1 AND teacher_subjects.session_id = 15 AND classes.id=16 AND subjects.id = 1 AND class_sections.id=43 AND teachers.name=\"Jhon\"\n";
        ArrayList<String> subjectNamesListinDB = new ArrayList<String>();
        ArrayList<String> Teachername = new ArrayList<String>();
        ResultSet queryRs1 = statement.executeQuery(AssignClass);

        if (queryRs1.next()) {
            String subjectnames = null;
            String Teachername1 = null;
            subjectnames = queryRs1.getString("name");
            Teachername1 = queryRs1.getString("teachers.name");
            System.out.println("Subject in database is  " + subjectnames);
            Teachername1 = queryRs1.getString("teachers.name");
            System.out.println("Teacher name in the database is   " + Teachername1);

            subjectNamesListinDB.add(queryRs1.getString("name"));
            Teachername.add(queryRs1.getString("teachers.name"));

            System.out.println(subjectNamesListinDB);
            System.out.println(Teachername);
            extentTest.log(Status.PASS, "The entered data is in database");

        } else {
            System.out.println("There is no such entry in database.");
            extentTest.log(Status.FAIL, "There is no such entry in database.");
        }
    }

    public void CompareSubjectlistwithDatabase() throws IOException, InterruptedException, SQLException {
        ActionClass actionClass = new ActionClass(driver, extentTest);
        //actionClass.clickOnObject(this.SideMenuAcademics);
        Thread.sleep(5000);
        actionClass.clickOnObject(this.AssignSubjectClick);
        actionClass.clickOnObject(this.SelectClass);
        actionClass.clickOnObject(this.SelectClassOption);
        Thread.sleep(1000);
        actionClass.clickOnObject(this.SelectSection);
        actionClass.clickOnObject(this.SelectSectionOption);
        actionClass.captureScreen("s2");
        actionClass.clickOnObject(this.SearchBtn);
        JavascriptExecutor jsetaskscore = (JavascriptExecutor) driver;
        jsetaskscore.executeScript("scrollBy(0,350)");
        actionClass.captureScreen("s3");
        Thread.sleep(1000);
//        actionClass.clickOnObject(this.SelectSubject);
        List<WebElement> ListStudent = driver.findElements(By.xpath("//*[@id=\"subject_id_0\"]"));
        int ClassStudentsize = ListStudent.size();
        System.out.println(ClassStudentsize);
        ArrayList listNames1 = new ArrayList();
        for (int i = 1; i <= ClassStudentsize; i++) {
            String s = driver.findElement(By.xpath("//*[@id=\"subject_id_0\"]/option[" + i + "]")).getText();
            System.out.println("Value in list is: " + s);
            listNames1.add(s);
        }
        DatabaseFunctions DAB = new DatabaseFunctions(extentTest);
        conn = DAB.connect();
        statement = conn.createStatement();
        String Subjects = "SELECT name,type FROM `subjects`";
        ResultSet queryRs1 = statement.executeQuery(Subjects);
        ArrayList E = new ArrayList();
        while (queryRs1.next()) {
            String s1 = null;
            s1 = queryRs1.getString("name");
            String s2 = queryRs1.getString("type");
            String s4 = null;
            s4 = ""+ s1 +"" +" (" + "" + s2 + "" + ")";
            System.out.println(s4);
            E.add(s4);
        }
        actionClass.CompareList(listNames1,E);
    }
}




