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
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
public class HomeWork {

    static WebDriver driver1;
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
            using = "/html/body/div[1]/aside/div/section/ul[2]/li[8]/a/span")
    private WebElement SideMenuHomeWorkClick;

    @FindBy(how = How.XPATH,
            using = "/html/body/div[1]/div[1]/section[2]/div/div/div[1]/div[1]/div/a")
    private WebElement AddHomeWorkbtn;

    @FindBy(how = How.XPATH,
            using = "//*[@id=\"title\"]")
    private WebElement HomeWorkTitle;

    @FindBy(how = How.CLASS_NAME,
            using = "wysihtml5-sandbox")
    private WebElement MessageField;

    @FindBy(how = How.XPATH,
            using = "//*[@id=\"subject_id\"]")
    private WebElement SelectSubjectField;

    @FindBy(how = How.XPATH,
            using = "//*[@id=\"subject_id\"]/option[2]")
    private WebElement SubjectSelectionField;

    @FindBy(how = How.XPATH,
            using = "//*[@id=\"class_id\"]")
    private WebElement ClassField;

    @FindBy(how = How.XPATH,
            using = "//*[@id=\"class_id\"]/option[2]")
    private WebElement ClassSeclection;

    @FindBy(how = How.XPATH,
            using = "//*[@id=\"publish_date\"]")
    private WebElement PublishOnDate;

    @FindBy(how = How.XPATH,
            using = "/html/body/div[1]/div/section[2]/div/div/form/div/div[2]/div/div[2]/div/div[2]/div/div/input")
    private WebElement UploadFile;

    @FindBy(how = How.XPATH,
            using = "/html/body/div[1]/div/section[2]/div/div/form/div/div[3]/button")
    private WebElement SubmitBtn;

    @FindBy(how = How.XPATH,
            using = "/html/body/div[1]/div/section[2]/div/div/form/div/div[2]/div/div[1]/div[5]/div/div/label[2]")
    private WebElement SelectSection;

    @FindBy(how = How.XPATH,
            using = "/html/body/div[1]/div[1]/section[2]/div/div/div[1]/div[2]/div[1]/div")
    private WebElement VeryText;

    @FindBy(how = How.XPATH,
            using = "/html/body/div[1]/div/section[2]/div/div/form/div/div[2]/div/div[1]/div[1]/span/p")
    private WebElement TitleFieldValidation;
    //
    @FindBy(how = How.XPATH,
            using = "/html/body/div[1]/div/section[2]/div/div/form/div/div[2]/div/div[2]/div/div[1]/span/p")
    private WebElement PublishdateValidation;

    @FindBy(how = How.XPATH,
            using = "/html/body/div[1]/div/section[2]/div/div/form/div/div[2]/div/div[1]/div[2]/span/p")
    private WebElement MessageValidation;

    @FindBy(how = How.XPATH,
            using = "/html/body/div[1]/div/section[2]/div/div/form/div/div[2]/div/div[1]/div[3]/div/span/p")
    private WebElement SubjectFieldValidation;

    @FindBy(how = How.XPATH,
            using = "/html/body/div[1]/div/section[2]/div/div/form/div/div[2]/div/div[1]/div[4]/div/span/p")
    private WebElement ClassFieldvalidation;

    @FindBy(how = How.XPATH,
            using = "/html/body/div[1]/div/section[2]/div/div/form/div/div[2]/div/div[1]/div[5]/span/p")
    private WebElement SectionFieldvalidation;

    @FindBy(how = How.XPATH,
            using = "//*[@id=\"type\"]")
    private WebElement StatusFeild;

    @FindBy(how = How.XPATH,
            using = "/html/body/div[1]/div[1]/section[2]/div/div/div[1]/div[2]/div/div/div/form/div[2]/div/button")
    private WebElement SearchBtn1;

    @FindBy(how = How.XPATH,
            using = "//*[@id=\"type\"]/option[3]")
    private WebElement SelectStatus;

    @FindBy(how = How.XPATH,
            using = "/html/body/div[1]/div[1]/section[2]/div/div/div[2]/div/div/div/div[2]/label/input")
    private WebElement SearchField;

    @FindBy(how = How.XPATH,
            using = " /html/body/div[1]/div[1]/section[2]/div/div/div[2]/div/div/div/table/tbody/tr[1]/td[4]/a[1]/i")
    private WebElement ViewBtn;

    @FindBy(how = How.XPATH,
            using = "html/body/div[1]/div[1]/section[2]/div/div/div[2]/div/div/div/table/tbody/tr[1]/td[4]/a[2]")
    private WebElement EditBtn;

    @FindBy(how = How.XPATH,
            using = "html/body/div[1]/div[1]/section[2]/div/div/div[2]/div/div/div/table/tbody/tr[1]/td[4]/a[2]")
    private WebElement EditMessage;

    @FindBy(how = How.XPATH,
            using = "/html/body/div[1]/div[1]/section[2]/div/div/form/div/div[3]/div/button")
    private WebElement Submit1;

    @FindBy(how = How.XPATH,
            using = "/html/body/div[1]/div[1]/section[2]/div/div/div[2]/div/div/div/table/tbody/tr[1]/td[4]/form/button/i")
    private WebElement Deletebtn;
    @FindBy(how = How.XPATH,
            using = "/html/body/div[1]/div[1]/section[2]/div/div/div[1]/div[2]/div[1]/div")
    private WebElement Deletemessage;
    @FindBy(how = How.XPATH,
            using = "/html/body/div[1]/div[1]/section[2]/div/div/div[1]/div[2]/div[1]/div")
    private WebElement EditMessage1;

    public HomeWork(WebDriver driver, ExtentTest test) {
        this.driver1 = driver;
        this.extentTest = test;
        PageFactory.initElements(driver, this);
    }

    public void openNewTab() throws InterruptedException {
        ActionClass actionClass = new ActionClass(driver1, extentTest);
        Thread.sleep(2000);
        driver1.getCurrentUrl();
        JavascriptExecutor jsetaskscore1 = (JavascriptExecutor) driver1;
        jsetaskscore1.executeScript("scrollBy(0, -300)");
        Thread.sleep(2000);

        for (String childTab : driver1.getWindowHandles()) {
            driver1.switchTo().window(childTab);
        }

        Thread.sleep(1000);
    }

    public void AddHomeWork(String Title, String HomeWorkDescribtion, String EntertPublishDate) throws InterruptedException, InterruptedException, SQLException, IOException {
        ActionClass actionClass = new ActionClass(driver1, extentTest);
        Thread.sleep(2000);
        actionClass.clickOnObject(this.SideMenuHomeWorkClick);
        Thread.sleep(3000);
        actionClass.clickOnObject(this.AddHomeWorkbtn);
        Thread.sleep(2000);
        actionClass.clickOnObject(this.HomeWorkTitle);
        actionClass.setValueinTextbox(this.HomeWorkTitle, Title);
        actionClass.clickOnObject(this.MessageField);
        driver1.findElement(By.className("wysihtml5-sandbox")).sendKeys(HomeWorkDescribtion);
        actionClass.clickOnObject(this.PublishOnDate);
        actionClass.setValueinTextbox(this.PublishOnDate, EntertPublishDate);
        Thread.sleep(1000);
        actionClass.captureScreen("Homework Added Successfully1");
        JavascriptExecutor jsetaskscore = (JavascriptExecutor) driver1;
        jsetaskscore.executeScript("scrollBy(0,550)");
        actionClass.clickOnObject(this.SelectSubjectField);
        actionClass.clickOnObject(this.SubjectSelectionField);
        Thread.sleep(5000);
        actionClass.clickOnObject(this.ClassSeclection);
        Thread.sleep(1000);
        actionClass.clickOnObject(this.ClassField);
        actionClass.clickOnObject(this.ClassSeclection);
        Thread.sleep(1000);
        actionClass.clickOnObject(this.SelectSection);
        actionClass.captureScreen("Homework Added Successfully2");
        jsetaskscore.executeScript("scrollBy(0,100)");
        actionClass.clickOnObject(this.SubmitBtn);
        actionClass.captureScreen("Homework Added Successfully");
        VerificationClass very = new VerificationClass(driver1, extentTest);
        very.verifyTextPresent(this.VeryText, "Homework added successfully!");
        DatabaseFunctions DAB = new DatabaseFunctions(extentTest);
        conn = DAB.connect();
        statement = conn.createStatement();
        String CirculareList1 = "SELECT title FROM `student_homework` WHERE title='" + Title + "'";
        ResultSet queryRs1 = statement.executeQuery(CirculareList1);
        ArrayList listNames = new ArrayList();
        while (queryRs1.next()) {
            String s1 = null;
            s1 = queryRs1.getString("title");
            listNames.add(s1);
            actionClass.CompareListandstring(Title, s1);
        }
    }

    public void Validations() throws IOException, InterruptedException {
        ActionClass actionClass = new ActionClass(driver1, extentTest);
        actionClass.clickOnObject(this.SideMenuHomeWorkClick);
        actionClass.clickOnObject(this.AddHomeWorkbtn);
        Thread.sleep(1000);
        JavascriptExecutor jsetaskscore = (JavascriptExecutor) driver1;
        jsetaskscore.executeScript("scrollBy(0,550)");
        actionClass.clickOnObject(this.SubmitBtn);
        actionClass.captureScreen("Add Homework validation");
        jsetaskscore.executeScript("scrollBy(0,-550)");
        actionClass.captureScreen("Add Homework validation");
        VerificationClass verificationClass = new VerificationClass(driver1, extentTest);
        verificationClass.verifyTextPresent(this.TitleFieldValidation, "The Title field is required.");
        verificationClass.verifyTextPresent(this.PublishdateValidation, "The Date field is required.");
        verificationClass.verifyTextPresent(this.MessageValidation, "The Message field is required.");
        verificationClass.verifyTextPresent(this.SubjectFieldValidation, "The Subject field is required.");
        verificationClass.verifyTextPresent(this.ClassFieldvalidation, "The Class field is required.");
        verificationClass.verifyTextPresent(this.SectionFieldvalidation, "The Section field is required.");

    }

    public void MAtchListWithdb() throws IOException, InterruptedException, SQLException {
        ActionClass actionClass = new ActionClass(driver1, extentTest);
        actionClass.clickOnObject(this.SideMenuHomeWorkClick);
        actionClass.clickOnObject(this.StatusFeild);
        actionClass.clickOnObject(this.SelectStatus);
        actionClass.clickOnObject(this.SearchBtn1);
        actionClass.captureScreen("Search Homework");
        JavascriptExecutor jsetaskscore = (JavascriptExecutor) driver1;
        jsetaskscore.executeScript("scrollBy(0,550)");
        actionClass.captureScreen("Search Homework1");
        List<WebElement> ListHomework = driver1.findElements(By.xpath("/html/body/div[1]/div[1]/section[2]/div/div/div[2]/div/div/div/table/tbody/tr[1]/td[1]"));
        int ClassStudentsize = ListHomework.size();
        String s = null;
        for (int i = 1; i <= ClassStudentsize; i++) {
            s = driver1.findElement(By.xpath("/html/body/div[1]/div[1]/section[2]/div/div/div[2]/div/div/div/table/tbody/tr[1]/td[1]/a")).getText();
            System.out.println(s);
        }
        DatabaseFunctions DAB = new DatabaseFunctions(extentTest);
        conn = DAB.connect();
        statement = conn.createStatement();
        String HomeworkList = "SELECT title FROM `student_homework` WHERE title='" + s + "'";
        ResultSet queryRs1 = statement.executeQuery(HomeworkList);
        while (queryRs1.next()) {
            String s1 = null;
            s1 = queryRs1.getString("title");
            actionClass.CompareListandstring(s, s1);
        }
    }

    public void ViewHomework(String SearchItem) throws IOException, InterruptedException {
        ActionClass actionClass = new ActionClass(driver1, extentTest);
        actionClass.clickOnObject(this.SideMenuHomeWorkClick);
        actionClass.clickOnObject(this.StatusFeild);
        actionClass.clickOnObject(this.SelectStatus);
        actionClass.clickOnObject(this.SearchBtn1);
        actionClass.clickOnObject(this.SearchField);
        actionClass.setValueinTextbox(this.SearchField, SearchItem);
        actionClass.captureScreen("Search Homework");
        String s = null;
        s = driver1.findElement(By.xpath("/html/body/div[1]/div[1]/section[2]/div/div/div[2]/div/div/div/table/tbody/tr[1]/td[1]/a")).getText();
        System.out.println(s);
        actionClass.clickOnObject(this.ViewBtn);
        actionClass.captureScreen("View Homework");
        WebElement TargetElement = driver1.findElement(By.xpath("//*[@id=\"title\"]"));
        String a = TargetElement.getAttribute("value");
        actionClass.CompareListandstring(s, a);

    }

    public void EditHomeWork(String SearchItem, String UpdatedText) throws IOException, InterruptedException, SQLException {
        ActionClass actionClass = new ActionClass(driver1, extentTest);
        actionClass.clickOnObject(this.SideMenuHomeWorkClick);
        actionClass.clickOnObject(this.StatusFeild);
        actionClass.clickOnObject(this.SelectStatus);
        actionClass.clickOnObject(this.SearchBtn1);
        actionClass.clickOnObject(this.SearchField);
        actionClass.setValueinTextbox(this.SearchField, SearchItem);
        DatabaseFunctions DAB = new DatabaseFunctions(extentTest);
        conn = DAB.connect();
        statement = conn.createStatement();
        String CirculareList1 = "SELECT title,id FROM `student_homework` WHERE title='" + SearchItem + "'";
        ResultSet queryRs1 = statement.executeQuery(CirculareList1);
        String expectedText = SearchItem;
        ArrayList listNames = new ArrayList();
        int s2 = 0;
        while (queryRs1.next()) {
            String s1 = null;
            s2 = 0;
            s1 = queryRs1.getString("title");
            s2 = queryRs1.getInt("id");
            listNames.add(s1);
//                actionClass.CompareListandstring(expectedText, s1);
        }

        actionClass.clickOnObject(this.EditBtn);
        actionClass.captureScreen("Edit Homework page");
        actionClass.clickOnObject(this.HomeWorkTitle);
        actionClass.setValueinTextbox(this.HomeWorkTitle, UpdatedText);
        JavascriptExecutor jsetaskscore = (JavascriptExecutor) driver1;
        jsetaskscore.executeScript("scrollBy(0,750)");
        Thread.sleep(1000);
        actionClass.clickOnObject(this.Submit1);
        actionClass.captureScreen("Homework updated");
        VerificationClass Very = new VerificationClass(driver1, extentTest);
        Very.verifyTextPresent(this.EditMessage1, "Homework Updated successfully!");
        String PostalReceiveupdate = "SELECT title,id FROM `student_homework` WHERE title='" + SearchItem + "' AND id ='" + s2 + "'";
        ResultSet queryRs2 = statement.executeQuery(PostalReceiveupdate);
        String expectedText1 = UpdatedText;
        int S3 = 0;
        ArrayList listNames3 = new ArrayList();
        while (queryRs2.next()) {
            String s1 = null;
            s1 = queryRs2.getString("title");
            S3 = queryRs2.getInt("id");
            listNames3.add(s1);
            actionClass.CompareListandstring(expectedText, s1);
        }
        actionClass.CompareList(listNames, listNames3);
        extentTest.log(Status.INFO, "Records updated successfully " + SearchItem + "updated with " + UpdatedText);
        System.out.println("Records updated successfully " + SearchItem + "updated with" + UpdatedText);
    }

    public void Delete(String SearchItem) throws IOException, InterruptedException {
        ActionClass actionClass = new ActionClass(driver1, extentTest);
        actionClass.clickOnObject(this.SideMenuHomeWorkClick);
        Thread.sleep(1000);
        actionClass.clickOnObject(this.StatusFeild);
        actionClass.clickOnObject(this.SelectStatus);
        actionClass.clickOnObject(this.SearchBtn1);
        actionClass.clickOnObject(this.SearchField);
        actionClass.setValueinTextbox(this.SearchField, SearchItem);
        actionClass.captureScreen("Home work Deleted1");
        JavascriptExecutor jsetaskscore = (JavascriptExecutor) driver1;
        jsetaskscore.executeScript("scrollBy(0,100)");
        actionClass.clickOnObject(this.Deletebtn);
        actionClass.captureScreen("Home work Deleted2");
        VerificationClass verificationClass = new VerificationClass(driver1, extentTest);
        verificationClass.verifyTextPresent(this.Deletemessage, "Homework deleted successfully!");
    }
}










