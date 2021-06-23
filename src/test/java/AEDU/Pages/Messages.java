package AEDU.Pages;
import AEDU.Actions.ActionClass;
import AEDU.Actions.VerificationClass;
import AEDU.Utilities.DatabaseFunctions;
import com.aventstack.extentreports.ExtentTest;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
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
public class Messages {

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
            using = "/html/body/div[1]/aside/div/section/ul[2]/li[7]/a/span")
    private WebElement SideMenuMessge;

    @FindBy(how = How.XPATH,
            using = "/html/body/div[1]/div[1]/section[1]/form/button")
    private WebElement AddMessageBtn;

    @FindBy(how = How.XPATH,
            using = "//*[@id=\"send\"]")
    private WebElement SENDBTN;

    @FindBy(how = How.XPATH,
            using = "/html/body/div[1]/div[1]/section[2]/div/div/div/div/div/div/div/form/div/div[1]/div/span/p")
    private WebElement ClassFieldValidation;

    @FindBy(how = How.XPATH,
            using = "/html/body/div[1]/div[1]/section[2]/div/div/div/div/div/div/div/form/div/div[2]/div/span/p")
    private WebElement SectionFeildValidation;

    @FindBy(how = How.XPATH,
            using = "/html/body/div[1]/div[1]/section[2]/div/div/div/div/div/div/div/form/div/div[3]/div/span/p")
    private WebElement StudentFeildValidation;

    @FindBy(how = How.XPATH,
            using = "/html/body/div[1]/div[1]/section[2]/div/div/div/div/div/div/div/form/div/div[4]/div[2]/span/p")
    private WebElement TitleFeildValidation;

    @FindBy(how = How.XPATH,
            using = "/html/body/div[1]/div[1]/section[2]/div/div/div/div/div/div/div/form/div/div[5]/div[2]/span/p")
    private WebElement MessageFeildValidation;

    @FindBy(how = How.XPATH,
            using = "//*[@id=\"section_id\"]")
    private WebElement SelectSection;
    @FindBy(how = How.XPATH,
            using = "//*[@id=\"class_id\"]")
    private WebElement SelectClass;

    @FindBy(how = How.XPATH,
            using = "//*[@id=\"student_id\"]")
    private WebElement SelectStudent;

    @FindBy(how = How.XPATH,
            using = "//*[@id=\"title\"]")
    private WebElement Title;

    @FindBy(how = How.XPATH,
            using = "//*[@id=\"message\"]")
    private WebElement AddMessage;

    @FindBy(how = How.XPATH,
            using = "/html/body/div[1]/div[1]/section[2]/div/div/div/div/div/div/div/form/div/div[6]")
    private WebElement AllowCheckBox;

    @FindBy(how = How.XPATH,
            using = "//*[@id=\"class_id\"]/option[2]")
    private WebElement Selectclass;

    @FindBy(how = How.XPATH,
            using = "//*[@id=\"section_id\"]/option[2]")
    private WebElement SelectSectionoptiom;

    @FindBy(how = How.XPATH,
            using = "//*[@id=\"student_id\"]/option[3]")
    private WebElement SelectStudentOPtion;

    @FindBy(how = How.XPATH,
            using = "/html/body/div[1]/div[1]/section[2]/div/div/div[2]/div/div[2]/div/table/tbody/tr[1]/td[1]/a")
    private WebElement StudentNameinList;

    @FindBy(how = How.XPATH,
            using = "/html/body/div[1]/div[1]/section[2]/div/div/div[2]/div/div[2]/div/table/tbody/tr[1]/td[2]")
    private WebElement TitleInList;

    @FindBy(how = How.XPATH,
            using = "/html/body/div[1]/div[1]/section[2]/div/div/div[2]/div/div[2]/div/table/tbody/tr[1]/td[3]")
    private WebElement MessageInList;

    @FindBy(how = How.XPATH,
            using = "/html/body/div[1]/div[1]/section[1]/form/button")
    private WebElement AddMessagebtn;

    @FindBy(how = How.XPATH,
            using = "//*[@id=\"class_id\"]")
    private WebElement Classfiledforaddmsg;

    @FindBy(how = How.XPATH,
            using = "//*[@id=\"section_id\"]")
    private WebElement SectionFieldforaddMessage;

    @FindBy(how = How.XPATH,
            using = "//*[@id=\"student_id\"]")
    private WebElement StudentFiledforaddmessage;

    @FindBy(how = How.XPATH,
            using = "//*[@id=\"class_id\"]/option[2]")
    private WebElement SelectClassforaddMessage;

    @FindBy(how = How.XPATH,
            using = "//*[@id=\"section_id\"]/option[2]")
    private WebElement Selectsectionforaddmessage;

    @FindBy(how = How.XPATH,
            using = "/html/body/div[1]/div[1]/section[2]/div/div/div/div/div[1]")
    private WebElement MatchResult;

    @FindBy(how = How.XPATH,
            using = "//*[@id=\"message\"]")
    private WebElement Messagefildinchatvindow;

    @FindBy(how = How.XPATH,
            using = "//*[@id=\"send\"]")
    private WebElement SendMEssagewindow;


    public Messages(WebDriver driver, ExtentTest test) {
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
    }

    public void CheckAddMessageValidations() throws IOException, InterruptedException {
        ActionClass actionClass = new ActionClass(driver1, extentTest);
        actionClass.clickOnObject(this.SideMenuMessge);
        actionClass.clickOnObject(this.AddMessageBtn);
        actionClass.clickOnObject(this.SENDBTN);
        actionClass.captureScreen("Add Message validation");
        VerificationClass verificationClass = new VerificationClass(driver1, extentTest);
        verificationClass.verifyTextPresent(this.ClassFieldValidation, "The Class field is required.");
        verificationClass.verifyTextPresent(this.SectionFeildValidation, "The Section field is required.");
        verificationClass.verifyTextPresent(this.StudentFeildValidation, "The Student field is required.");
        verificationClass.verifyTextPresent(this.TitleFeildValidation, "The Title field is required.");
        verificationClass.verifyTextPresent(this.MessageFeildValidation, "The Message field is required.");
    }

    public void AddMessage(String AddTitle, String textMessage) throws IOException, InterruptedException, SQLException {
        ActionClass actionClass = new ActionClass(driver1, extentTest);
        actionClass.clickOnObject(this.SideMenuMessge);
        actionClass.clickOnObject(this.AddMessageBtn);
        actionClass.captureScreen("Add Message sreen");
        actionClass.clickOnObject(this.SelectClass);
        actionClass.clickOnObject(this.SelectClass);
        actionClass.clickOnObject(this.SelectSection);
        actionClass.clickOnObject(this.SelectSectionoptiom);
        actionClass.clickOnObject(this.SelectStudent);
        actionClass.clickOnObject(this.SelectStudentOPtion);
        Select select;
        select = new Select(driver1.findElement(By.xpath("//*[@id=\"student_id\"]")));
        WebElement option = select.getFirstSelectedOption();
        String s = null;
        s = option.getText();
        String riddhi = s;
        System.out.println(riddhi);
        actionClass.clickOnObject(this.Title);
        actionClass.setValueinTextbox(this.Title, AddTitle);
        actionClass.clickOnObject(this.AddMessage);
        actionClass.setValueinTextbox(this.AddMessage, textMessage);
        actionClass.clickOnObject(this.AllowCheckBox);
        actionClass.clickOnObject(this.SENDBTN);
        actionClass.captureScreen("Message Chat window");
        String MessageText;
        MessageText = driver1.findElement(By.xpath("/html/body/div[1]/div[1]/section[2]/div/div/div/div/div[1]")).getText();
        System.out.println(MessageText);
        DatabaseFunctions DAB = new DatabaseFunctions(extentTest);
        conn = DAB.connect();
        statement = conn.createStatement();
        String CirculareList1 = "Select * from student_messages JOIN students ON students.id=student_messages.student_id WHERE student_messages.title='" + AddTitle + "'";
        ResultSet queryRs1 = statement.executeQuery(CirculareList1);
        while (queryRs1.next()) {
            String s2 = null;
            String s3 = null;
            s2 = queryRs1.getString("title");
            s3 = queryRs1.getString("message");
            actionClass.CompareListandstring(s2, AddTitle);
            actionClass.CompareListandstring(s3, textMessage);
            actionClass.CompareListandstring(AddTitle, MessageText);
        }
    }

    public void MatchTheList() throws IOException, InterruptedException, SQLException {
        ActionClass actionClass = new ActionClass(driver1, extentTest);
        actionClass.clickOnObject(this.SideMenuMessge);
        JavascriptExecutor jsetaskscore1 = (JavascriptExecutor) driver1;
        jsetaskscore1.executeScript("scrollBy(0, 500)");
        actionClass.captureScreen("Messagelist");
        List<WebElement> ListStudent = driver1.findElements(By.xpath("//*[@id=\"DataTables_Table_0\"]/tbody/tr[1]"));
        int ClassStudentsize = ListStudent.size();
        String s = null;
        for (int i = 1; i <= ClassStudentsize; i++) {
            s = driver1.findElement(By.xpath("/html/body/div[1]/div[1]/section[2]/div/div/div[2]/div/div[2]/div/table/tbody/tr[1]/td[" + i + "]/a")).getText();
            System.out.println("Value in list is: " + s);
            String s1 = null;
            s1 = driver1.findElement(By.xpath("/html/body/div[1]/div[1]/section[2]/div/div/div[2]/div/div[2]/div/table/tbody/tr[1]/td[2]")).getText();
            System.out.println("Title in list is" + s1);
            String s2 = null;
            s2 = driver1.findElement(By.xpath("/html/body/div[1]/div[1]/section[2]/div/div/div[2]/div/div[2]/div/table/tbody/tr[1]/td[3]")).getText();
            System.out.println("Message in list is" + s2);
            String s3 = null;
            s3 = driver1.findElement(By.xpath("/html/body/div[1]/div[1]/section[2]/div/div/div[2]/div/div[2]/div/table/tbody/tr[1]/td[5]")).getText();
            String krina = s3.toLowerCase();
            System.out.println(s3.toLowerCase());
            DatabaseFunctions DAB = new DatabaseFunctions(extentTest);
            conn = DAB.connect();
            statement = conn.createStatement();
            String CirculareList1 = "Select * from student_messages JOIN students ON students.id=student_messages.student_id WHERE student_messages.title='" + s1 + "'";
            ResultSet queryRs1 = statement.executeQuery(CirculareList1);
            while (queryRs1.next()) {
                String a1 = null;
                String a2 = null;
                String a3 = null;
                String a4 = null;
                a1 = queryRs1.getString("firstname");
                a2 = queryRs1.getString("title");
                a3 = queryRs1.getString("message");
                a4 = queryRs1.getString("role");
                System.out.println(a4);
                actionClass.CompareListandstring(a1, s);
                actionClass.CompareListandstring(a2, s1);
                actionClass.CompareListandstring(a3, s2);
                actionClass.CompareListandstring(a4, krina);
            }
        }
    }

    public void MatchChatwindowMessage(String AddTitle, String textMessage, String Messageforwindow) throws IOException, InterruptedException, SQLException {
        ActionClass actionClass = new ActionClass(driver1, extentTest);
        actionClass.clickOnObject(this.SideMenuMessge);
        actionClass.clickOnObject(this.AddMessageBtn);
        actionClass.captureScreen("Add Message1");
        actionClass.clickOnObject(this.SelectClass);
        actionClass.clickOnObject(this.Selectclass);
        actionClass.clickOnObject(this.SelectSection);
        actionClass.clickOnObject(this.SelectSectionoptiom);
        actionClass.clickOnObject(this.SelectStudent);
        actionClass.clickOnObject(this.SelectStudentOPtion);
        actionClass.captureScreen("Add Message2");
        Select select;
        select = new Select(driver1.findElement(By.xpath("//*[@id=\"student_id\"]")));
        WebElement option = select.getFirstSelectedOption();
        String s = null;
        s = option.getText();
        String riddhi = s;
        System.out.println(riddhi);
        actionClass.clickOnObject(this.Title);
        actionClass.setValueinTextbox(this.Title, AddTitle);
        actionClass.clickOnObject(this.AddMessage);
        actionClass.setValueinTextbox(this.AddMessage, textMessage);
        actionClass.clickOnObject(this.AllowCheckBox);
        actionClass.captureScreen("Add Message3");
        actionClass.clickOnObject(this.SENDBTN);
        actionClass.clickOnObject(this.Messagefildinchatvindow);
        actionClass.setValueinTextbox(this.Messagefildinchatvindow, Messageforwindow);
        actionClass.clickOnObject(this.SendMEssagewindow);
        actionClass.captureScreen("Message Chat window");
        String MessageText;
        MessageText = driver1.findElement(By.xpath("/html/body/div[1]/div[1]/section[2]/div/div/div/div/div[1]")).getText();
        System.out.println(MessageText);
        DatabaseFunctions DAB = new DatabaseFunctions(extentTest);
        conn = DAB.connect();
        statement = conn.createStatement();
        String CirculareList1 = "SELECT * FROM `student_messages`WHERE message ='" + Messageforwindow + "'";
        ResultSet queryRs1 = statement.executeQuery(CirculareList1);
        while (queryRs1.next()) {
            String s1 = null;
            s1 = queryRs1.getString("message");
            actionClass.CompareListandstring(Messageforwindow, s1);
        }
    }
}






