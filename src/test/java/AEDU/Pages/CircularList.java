package AEDU.Pages;
import AEDU.Actions.ActionClass;
import AEDU.Actions.VerificationClass;
import AEDU.Utilities.DatabaseFunctions;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import junit.framework.Assert;
import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ISelect;
import org.openqa.selenium.support.ui.Select;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.List;

public class CircularList {
    static WebDriver driver1;
    ExtentTest extentTest;
    Connection conn = null;
    Statement statement;
    ResultSet queryRs;
    ResultSet Qcount;
    //    Docker
//  String url = "jdbc:mysql://localhost:6603/";
//  localDB
    String url = "jdbc:mysql://localhost:3306/";
    String dbName = "aedudev1";
    String driver = "com.mysql.jdbc.Driver";
    String userName = "root";
    String password = "root";

    @FindBy(how = How.XPATH,
            using = "/html/body/div[1]/aside/div/section/ul[2]/li[5]/a/span")
    private WebElement SideMenuCircularClick;

    @FindBy(how = How.XPATH,
            using = "/html/body/div[1]/aside/div/section/ul[2]/li[5]/ul/li[1]/a")
    private WebElement SideMenuCircularListClick;

    @FindBy(how = How.XPATH,
            using = "//*[@id=\"role\"]")
    private WebElement SelectRoleField;

    @FindBy(how = How.XPATH,
            using = "//*[@id=\"role\"]/option[1]")
    private WebElement SelectRoleOption1;

    @FindBy(how = How.ID,
            using = "type")
    private WebElement SelectStatusField;

    @FindBy(how = How.XPATH,
            using = "//*[@id=\"type\"]/option[1]")
    private WebElement SelectStatusOption1;

    @FindBy(how = How.XPATH,
            using = "/html/body/div[1]/div[1]/section[2]/div/div/div[1]/div[2]/div/div/div/form/div[3]/div/button")
    private WebElement SearchButton;

    @FindBy(how = How.XPATH,
            using = "/html/body/div[1]/div[1]/section[2]/div/div/div[1]/div[2]/div/div/div/form/div[1]/div/span/p")
    private WebElement SelectRoleFieldValidation;

    @FindBy(how = How.XPATH,
            using = "/html/body/div[1]/div[1]/section[2]/div/div/div[1]/div[2]/div/div/div/form/div[2]/div/span/p")
    private WebElement SlectStausFieldValidation;

    @FindBy(how = How.XPATH,
            using = "//*[@id=\"type\"]/option[3]")
    private WebElement SlectStausOption3;

    @FindBy(how = How.XPATH,
            using = " /html/body/div[1]/div[1]/section[2]/div/div/div[2]/div/div/div/table/tbody/tr[1]/td[5]/a[1]")
    private WebElement ViewCircular;

    @FindBy(how = How.XPATH,
            using = "//*[@id=\"title\"]")
    private WebElement ViewCircularTitle;

    @FindBy(how = How.XPATH,
            using = "//*[@id=\"publish_date\"]")
    private WebElement ViewCircularPublishdate;

    @FindBy(how = How.XPATH,
            using = "//*[@id=\"DataTables_Table_0_filter\"]/label/input")
    private WebElement SearchField;

    @FindBy(how = How.XPATH,
            using = "/html/body/div[1]/div[1]/section[2]/div/div/div[2]/div/div/div/table/tbody/tr[1]/td[5]/a[2]")
    private WebElement Editbtn;

    @FindBy(how = How.XPATH,
            using = "/html/body/div[1]/div[1]/section[2]/div/div/form/div/div[3]/div[1]/button")
    private WebElement SubmitBtn;
    @FindBy(how = How.XPATH,
            using = "/html/body/div[1]/div[1]/section[2]/div/div/div[2]/div/div/div/table/tbody/tr[1]/td[5]/form/button")
    private WebElement DeleteBtn;

    @FindBy(how = How.XPATH,
            using = "/html/body/div[1]/div[1]/section[2]/div/div/div[1]/div[2]/div[1]/div")
    private WebElement DeleteMessage;
    @FindBy(how = How.XPATH,
            using = "/html/body/div[1]/div[1]/section[2]/div/div/div[2]/div/div/div/table/tbody/tr[1]/td[2]")
    private WebElement DateField;

    @FindBy(how = How.XPATH,
            using = "/html/body/div[1]/div[1]/section[2]/div/div/div[1]/div[2]/div[1]/div")
    private WebElement EditMessage;

    public CircularList(WebDriver driver, ExtentTest test) {
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

    public void CheckFiltervalidation() throws IOException, InterruptedException {
        ActionClass actionClass = new ActionClass(driver1, extentTest);
        actionClass.clickOnObject(this.SideMenuCircularClick);
        Thread.sleep(5000);
        actionClass.clickOnObject(this.SideMenuCircularListClick);
        actionClass.clickOnObject(this.SelectRoleField);
        actionClass.clickOnObject(this.SelectRoleOption1);
        Thread.sleep(1000);
        actionClass.clickOnObject(this.SelectStatusField);
        actionClass.clickOnObject(this.SelectStatusOption1);
        actionClass.clickOnObject(this.SearchButton);
        actionClass.captureScreen("Circular List Filter Validations1");
        VerificationClass verificationClass = new VerificationClass(driver1, extentTest);
        verificationClass.verifyTextPresent(this.SlectStausFieldValidation, "The Circular Status field is required.");
        verificationClass.verifyTextPresent(this.SelectRoleFieldValidation, "The Circular Status field is required.");
        actionClass.captureScreen("Circular List Filter Validations2");
    }

    public void CheckViewCircular() throws IOException, InterruptedException {
        ActionClass actionClass = new ActionClass(driver1, extentTest);
        //actionClass.clickOnObject(this.SideMenuCircularClick);
        Thread.sleep(1000);
        actionClass.clickOnObject(this.SideMenuCircularListClick);
        actionClass.clickOnObject(this.SelectStatusField);
        actionClass.clickOnObject(this.SlectStausOption3);
        actionClass.clickOnObject(this.SearchButton);
        JavascriptExecutor jsetaskscore = (JavascriptExecutor) driver1;
        jsetaskscore.executeScript("scrollBy(0, 300)");
        actionClass.captureScreen("View Circular1");
        List<WebElement> ListStudent = driver1.findElements(By.xpath("/html/body/div[1]/div[1]/section[2]/div/div/div[2]/div/div/div/table/tbody/tr[1]/td[1]"));
        int ClassStudentsize = ListStudent.size();
        String s = null;
        for (int i = 1; i <= ClassStudentsize; i++) {
            s = driver1.findElement(By.xpath("/html/body/div[1]/div[1]/section[2]/div/div/div[2]/div/div/div/table/tbody/tr[1]/td[1]/a")).getText();
            System.out.println(s);
            actionClass.clickOnObject(this.ViewCircular);
            actionClass.captureScreen("View Circular2");
            WebElement TargetElement = driver1.findElement(By.xpath("//*[@id=\"title\"]"));
            String a = TargetElement.getAttribute("value");
            System.out.println(a);
            actionClass.CompareListandstring(s, a);
        }
    }

    public void MatchlistWithDatabase() throws IOException, InterruptedException, SQLException {
        ActionClass actionClass = new ActionClass(driver1, extentTest);
        //actionClass.clickOnObject(this.SideMenuCircularClick);
        Thread.sleep(5000);
        actionClass.clickOnObject(this.SideMenuCircularListClick);
        actionClass.clickOnObject(this.SelectStatusField);
        actionClass.clickOnObject(this.SlectStausOption3);
        actionClass.clickOnObject(this.SearchButton);
        actionClass.clickOnObject(this.SearchField);
        JavascriptExecutor jsetaskscore = (JavascriptExecutor) driver1;
        jsetaskscore.executeScript("scrollBy(0, 300)");
        actionClass.setValueinTextbox(this.SearchField, "Testing circular");
        actionClass.captureScreen("Search Data");
        List<WebElement> ListStudent = driver1.findElements(By.xpath("/html/body/div[1]/div[1]/section[2]/div/div/div[2]/div/div/div/table/tbody/tr[1]/td[1]"));
        int ClassStudentsize = ListStudent.size();
        String s = null;
        for (int i = 1; i <= ClassStudentsize; i++) {
            s = driver1.findElement(By.xpath("/html/body/div[1]/div[1]/section[2]/div/div/div[2]/div/div/div/table/tbody/tr[1]/td[1]/a")).getText();
            System.out.println(s);
        }
        DatabaseFunctions DAB = new DatabaseFunctions(extentTest);
        conn = DAB.connect();
        statement = conn.createStatement();
        String CircularList = "SELECT title FROM `send_notification` WHERE title='Testing circular'";
        ResultSet queryRs1 = statement.executeQuery(CircularList);
        while (queryRs1.next()) {
            String s1 = null;
            s1 = queryRs1.getString("title");
            actionClass.CompareListandstring(s, s1);
        }
    }

    public void EditList(String AddCirculartitle, String Updatedtitle) throws IOException, InterruptedException, SQLException {
        ActionClass actionClass = new ActionClass(driver1, extentTest);
        //actionClass.clickOnObject(this.SideMenuCircularClick);
        Thread.sleep(1000);
        actionClass.clickOnObject(this.SideMenuCircularListClick);
        actionClass.clickOnObject(this.SelectStatusField);
        actionClass.clickOnObject(this.SlectStausOption3);
        actionClass.clickOnObject(this.SearchButton);
        actionClass.clickOnObject(this.SearchField);
        actionClass.captureScreen("S1");
        JavascriptExecutor jsetaskscore = (JavascriptExecutor) driver1;
        jsetaskscore.executeScript("scrollBy(0, 300)");
        actionClass.setValueinTextbox(this.SearchField, AddCirculartitle);
        actionClass.captureScreen("S2");
        DatabaseFunctions DAB = new DatabaseFunctions(extentTest);
        conn = DAB.connect();
        statement = conn.createStatement();
        String CirculareList1 = "SELECT title ,id FROM `send_notification` WHERE title='"+AddCirculartitle+"'";
        ResultSet queryRs1 = statement.executeQuery(CirculareList1);
        String expectedText = AddCirculartitle;
        ArrayList listNames = new ArrayList();
        int s2 = 0;
        while (queryRs1.next()) {
            String s1 = null;
            s2 = 0;
            s1 = queryRs1.getString("title");
            s2 = queryRs1.getInt("id");
            listNames.add(s1);
            actionClass.CompareListandstring(expectedText, s1);
        }
        actionClass.clickOnObject(this.Editbtn);
        actionClass.clickOnObject(this.ViewCircularTitle);
        actionClass.setValueinTextbox(this.ViewCircularTitle, Updatedtitle);
        actionClass.captureScreen("S3");
        JavascriptExecutor jsetaskscore3 = (JavascriptExecutor) driver1;
        jsetaskscore3.executeScript("scrollBy(0,650)");
        actionClass.clickOnObject(this.SubmitBtn);
        VerificationClass Very = new VerificationClass(driver1,extentTest);
        Very.verifyTextPresent(this.EditMessage,"Circular Updated successfully!");
        actionClass.captureScreen("Update circualr for staff");
        String PostalReceiveupdate = "SELECT title ,id FROM `send_notification` where title='" + AddCirculartitle + "' AND id ='" + s2 + "'";
        ResultSet queryRs2 = statement.executeQuery(PostalReceiveupdate);
        String expectedText1 = Updatedtitle;
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
        extentTest.log(Status.INFO, "Records updated successfully " + AddCirculartitle + "updated with " + Updatedtitle);
        System.out.println("Records updated successfully " + AddCirculartitle + "updated with" + Updatedtitle);
    }

    public void Deletecircular(String AddCirculartitle, String Updatedtitle) throws IOException, InterruptedException, SQLException {
        ActionClass actionClass = new ActionClass(driver1, extentTest);
        //actionClass.clickOnObject(this.SideMenuCircularClick);
        Thread.sleep(5000);
        actionClass.clickOnObject(this.SideMenuCircularListClick);
        actionClass.clickOnObject(this.SelectStatusField);
        actionClass.clickOnObject(this.SlectStausOption3);
        actionClass.clickOnObject(this.SearchButton);
        actionClass.clickOnObject(this.SearchField);
        actionClass.captureScreen("S1");
        JavascriptExecutor jsetaskscore = (JavascriptExecutor) driver1;
        jsetaskscore.executeScript("scrollBy(0, 300)");
        actionClass.setValueinTextbox(this.SearchField, AddCirculartitle);
        actionClass.captureScreen("S4");
        actionClass.clickOnObject(this.DeleteBtn);
        VerificationClass very = new VerificationClass(driver1, extentTest);
        very.verifyTextPresent(this.DeleteMessage, "Circular Deleted Successfully!!!");
        actionClass.captureScreen("Circular Deleted");
    }

    public void matchdate() throws IOException, InterruptedException, ParseException {
        ActionClass actionClass = new ActionClass(driver1, extentTest);
        //actionClass.clickOnObject(this.SideMenuCircularClick);
        Thread.sleep(5000);
        actionClass.clickOnObject(this.SideMenuCircularListClick);
        actionClass.clickOnObject(this.SelectStatusField);
        actionClass.clickOnObject(this.SlectStausOption3);
        actionClass.clickOnObject(this.SearchButton);
        actionClass.captureScreen("s1");
        JavascriptExecutor jsetaskscore = (JavascriptExecutor) driver1;
        jsetaskscore.executeScript("scrollBy(0, 300)");
        actionClass.clickOnObject(this.DateField);
        String InputDate = null;
       InputDate = driver1.findElement(By.xpath("/html/body/div[1]/div[1]/section[2]/div/div/div[2]/div/div/div/table/tbody/tr[1]/td[2]")).getText();
        System.out.println("The Publish date is " + InputDate);
        Select select;
        select = new Select(driver1.findElement(By.xpath("//*[@id=\"type\"]")));
        WebElement option = select.getFirstSelectedOption();
        String defaultItem = option.getText();
        System.out.println(defaultItem);
        actionClass.captureScreen("s2");
        //Create object of SimpleDateFormat class and decide the formatDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy ");
        //get current date time with Date()
        Date Current = new Date();
        SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd");
        // Now format the date
        String date1 = sdf1.format(Current);
        // Print the Date
        System.out.println(Current);
        extentTest.log(Status.INFO,"Current Date is: "+Current);
        System.out.println(InputDate);
        extentTest.log(Status.INFO,"Input Date is: "+InputDate);
        if (InputDate.compareTo(date1) > 0){
            // When Date d1 > Date d2
            System.out.println("Currentdate is after Inputdate");
            extentTest.log(Status.INFO,"Current Date is after Inputdate");
        }else if (InputDate.compareTo(date1) < 0) {
            // When Date d1 < Date d2
            System.out.println("currentdate is before inputdate");
            extentTest.log(Status.INFO,"Current Date is before inputdate");
        }else if (InputDate.compareTo(date1) == 0) {
            // When Date d1 = Date d2
            System.out.println("current is equal to inputdate");
            extentTest.log(Status.INFO,"Current Date is equal to Input Date");
        }
    }
}


