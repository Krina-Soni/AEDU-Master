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

public class AddComplaint {

    String url = "jdbc:mysql://localhost:3306/";
    String dbName = "aedudev1";
    String driver = "com.mysql.jdbc.Driver";
    String userName = "root";
    String password = "root";
    Statement statement;
    ResultSet queryRs;
    ResultSet Qcount;
    WebDriver driver1;
    ExtentTest extentTest;
    Connection conn = null;

    @FindBy(how = How.XPATH,
            using = "//*[@id=\"form-username\"]\n")
    private WebElement username;

    @FindBy(how = How.XPATH,
            using = "//*[@id=\"form-password\"]\n"
    )
    private WebElement password1;

    @FindBy(how = How.XPATH,
            using = "/html/body/div[1]/div/div/div[2]/div/div/div[2]/form[1]/button"
    )
    private WebElement submitbtn;

    @FindBy(how = How.XPATH,
            using = "/html/body/div[1]/aside/div/section/ul[2]/li[6]/a"
    )
    private WebElement FrontOfficeMenuClick;

    @FindBy(how = How.XPATH,
            using = "/html/body/div[1]/aside/div/section/ul[2]/li[6]/ul/li[6]/a"
    )
    private WebElement FrontOffiComplainClick;

    @FindBy(how = How.XPATH,
            using = "/html/body/div[1]/div[1]/section[2]/div/div[1]/div/form/div[2]/button"
    )
    private WebElement SaveComplainbtn;

    @FindBy(how = How.XPATH,
            using = "/html/body/div[1]/div[1]/section[2]/div/div[1]/div/form/div[1]/div[3]/span/p"
    )
    private WebElement ComplainByfeilValidation;

    @FindBy(how = How.XPATH,
            using = "/html/body/div[1]/div[1]/section[2]/div/div[1]/div/form/div[1]/div[1]/select"
    )
    private WebElement ComplainType;

    @FindBy(how = How.XPATH,
            using = "//*[@id=\"form1\"]/div[1]/div[1]/select/option[2]"
    )
    private WebElement ComplaintypeSelect;

    @FindBy(how = How.XPATH,
            using = "/html/body/div[1]/div[1]/section[2]/div/div[1]/div/form/div[1]/div[2]/select"
    )
    private WebElement Source;

    @FindBy(how = How.XPATH,
            using = "//*[@id=\"form1\"]/div[1]/div[2]/select/option[2]"
    )
    private WebElement SourceSelect;

    @FindBy(how = How.NAME,
            using = "name"
    )
    private WebElement ComplainBy;

    @FindBy(how = How.XPATH,
            using = "/html/body/div[1]/div[1]/section[2]/div/div[1]/div/form/div[1]/div[4]/input"
    )
    private WebElement Phonenbfeild;

    @FindBy(how = How.XPATH,
            using = "//*[@id=\"date\"]"
    )
    private WebElement Date;

    @FindBy(how = How.XPATH,
            using = "/html/body/div[4]/div[1]/table/tbody/tr[4]/td[4]"
    )
    private WebElement DateSelect;

    @FindBy(how = How.XPATH,
            using = "//*[@id=\"description\"]"
    )
    private WebElement Description;

    @FindBy(how = How.XPATH,
            using = "/html/body/div[1]/div[1]/section[2]/div/div[1]/div/form/div[1]/div[7]/input"
    )
    private WebElement ActionTaken;

    @FindBy(how = How.XPATH,
            using = "/html/body/div[1]/div[1]/section[2]/div/div[1]/div/form/div[1]/div[8]/input"
    )
    private WebElement Assigned;

    @FindBy(how = How.NAME,
            using = "note"
    )
    private WebElement Note;

    @FindBy(how = How.XPATH,
            using = "/html/body/div[1]/div[1]/section[2]/div/div[1]/div/form/div[1]/div[10]/div/div/input"
    )
    private WebElement Attach;

    @FindBy(how = How.XPATH,
            using = "/html/body/div[1]/div[1]/section[2]/div/div[1]/div/form/div[1]/div[1]"
    )
    private WebElement ComplainaddedMessage;

    @FindBy(how = How.XPATH,
            using = " //*[@id=\"DataTables_Table_0\"]/tbody/tr[1]/td[6]/a[2]"
    )
    private WebElement Editbn;

    @FindBy(how = How.XPATH,
            using = " /html/body/div[1]/div[1]/section[2]/div/div[1]/div/form/div[1]/div[1]"
    )
    private WebElement SucessMesaageforedit;

    @FindBy(how = How.XPATH,
            using = " /html/body/div[1]/div[1]/section[2]/div/div[2]/div/div[2]/div[2]/div/table/tbody/tr[1]/td[6]/a[1]/i"
    )
    private WebElement Viewbtn;

    @FindBy(how = How.XPATH,
            using = " /html/body/div[1]/div[2]/div/div/div[1]/button"
    )
    private WebElement Closeview;

    @FindBy(how = How.XPATH,
            using = " /html/body/div[1]/div[2]/div/div/div[1]/button"
    )
    private WebElement DeleteBtn;

    @FindBy(how = How.XPATH,
            using = " /html/body/div[1]/div[1]/section[2]/div/div[1]/div/form/div[1]/div[1]"
    )
    private WebElement DeleteMessage;

    @FindBy(how = How.XPATH,
            using = " /html/body/div[1]/div[1]/section[2]/div/div[2]/div/div[2]/div[2]/div/div[2]/label/input"
    )
    private WebElement Searchfeild;
    @FindBy(how = How.XPATH,
            using = "  /html/body/div[1]/div[2]/div/div/div[1]/h4"
    )
    private WebElement detailpop;

    public AddComplaint(WebDriver driver, ExtentTest test) {
        this.driver1 = driver;
        this.extentTest = test;
        PageFactory.initElements(driver, this);
    }
    public void openNewTab() throws InterruptedException {
        ActionClass actionClass=new ActionClass(driver1, extentTest);
        Thread.sleep(2000);
        driver1.getCurrentUrl();
        JavascriptExecutor jsetaskscore1 = (JavascriptExecutor) driver1;
        jsetaskscore1.executeScript("scrollBy(0, -300)");
        Thread.sleep(2000);

        for(String childTab : driver1.getWindowHandles()){
            driver1.switchTo().window(childTab);
        }

        Thread.sleep(1000);
    }
    public void ChecktheMEndatoryFEildValidationa() throws IOException, InterruptedException {
        ActionClass actionClass = new ActionClass(driver1, extentTest);
//        Thread.sleep(5000);
//        actionClass.clickOnObject(this.FrontOfficeMenuClick);
        Thread.sleep(5000);
        actionClass.clickOnObject(this.FrontOffiComplainClick);
        JavascriptExecutor jsetaskscore = (JavascriptExecutor) driver1;
        jsetaskscore.executeScript("scrollBy(0, 150)");
        actionClass.clickOnObject(this.SaveComplainbtn);
        VerificationClass Match = new VerificationClass(driver1, extentTest);
        Match.verifyTextPresent(this.ComplainByfeilValidation, "The Complaint By field is required.");
        System.out.println("Mendetory feild validations check");
        actionClass.captureScreen("Mendetory feild validations check");
    }

    public void AddComplainwithValiddata(String Nameofcomplainby, String Phone, String AddDescription, String ActionStatus, String AssignedStatus, String AddNote) throws IOException, InterruptedException, SQLException {
        ActionClass actionClass = new ActionClass(driver1, extentTest);
//        actionClass.clickOnObject(this.FrontOfficeMenuClick);
        Thread.sleep(5000);
       actionClass.clickOnObject(this.FrontOffiComplainClick);
        actionClass.clickOnObject(this.ComplainType);
        actionClass.clickOnObject(this.ComplaintypeSelect);
        actionClass.clickOnObject(this.Source);
        actionClass.clickOnObject(this.SourceSelect);
        actionClass.clickOnObject(this.ComplainBy);
        actionClass.setValueinTextbox(this.ComplainBy, Nameofcomplainby);
        actionClass.clickOnObject(this.Phonenbfeild);
        actionClass.setValueinTextbox(this.Phonenbfeild, Phone);
        actionClass.clickOnObject(this.Description);
        actionClass.setValueinTextbox(this.Description, AddDescription);
        actionClass.clickOnObject(this.ActionTaken);
        actionClass.setValueinTextbox(this.ActionTaken, ActionStatus);
        JavascriptExecutor jsetaskscore = (JavascriptExecutor) driver1;
        jsetaskscore.executeScript("scrollBy(0, 500)");
        actionClass.clickOnObject(this.Assigned);
        actionClass.setValueinTextbox(this.Assigned, AssignedStatus);
        actionClass.clickOnObject(this.Note);
        actionClass.setValueinTextbox(this.Note, AddNote);
        Thread.sleep(1000);
        actionClass.clickOnObject(SaveComplainbtn);
        VerificationClass match = new VerificationClass(driver1, extentTest);
        match.verifyTextPresent(this.ComplainaddedMessage, "Complaint added successfully");
        actionClass.captureScreen("Check user is able to add complain properly or not");
        DatabaseFunctions DAB = new DatabaseFunctions(extentTest);
        conn = DAB.connect();
        statement = conn.createStatement();
        String Complain = "SELECT * FROM `complaint` WHERE name='" + Nameofcomplainby + "'";
        ResultSet queryRs1 = statement.executeQuery(Complain);
        String expectedText = Nameofcomplainby;
        ArrayList listNames = new ArrayList();

        while (queryRs1.next()) {
            String s1 = null;
            s1 = queryRs1.getString("name");
            actionClass.CompareListandstring(expectedText, s1);
        }

    }

    public void Search( String Searchvalue) throws IOException, InterruptedException {
        ActionClass actionClass = new ActionClass(driver1, extentTest);
//        actionClass.clickOnObject(this.FrontOfficeMenuClick);
        Thread.sleep(5000);
        actionClass.clickOnObject(this.FrontOffiComplainClick);
        actionClass.clickOnObject(this.Searchfeild);
        actionClass.setValueinTextbox(this.Searchfeild, Searchvalue);
        actionClass.captureScreen("Search complain result");
    }

    public void updatecomplain(String complainby, String updatename)throws IOException, InterruptedException, SQLException {
        ActionClass actionClass = new ActionClass(driver1, extentTest);
//        actionClass.clickOnObject(this.FrontOfficeMenuClick);
        Thread.sleep(5000);
       actionClass.clickOnObject(this.FrontOffiComplainClick);
        actionClass.clickOnObject(this.ComplainBy);
        actionClass.setValueinTextbox(this.ComplainBy, complainby);
        JavascriptExecutor jsetaskscore = (JavascriptExecutor) driver1;
        jsetaskscore.executeScript("scrollBy(0, 850)");
        actionClass.clickOnObject(SaveComplainbtn);
        VerificationClass match = new VerificationClass(driver1, extentTest);
        match.verifyTextPresent(this.ComplainaddedMessage, "Complaint added successfully");
        DatabaseFunctions DAB = new DatabaseFunctions(extentTest);
        conn = DAB.connect();
        statement = conn.createStatement();
        String PostalReceive = "SELECT id,name FROM `complaint` WHERE name='" + complainby + "'";
        ResultSet queryRs1 = statement.executeQuery(PostalReceive);
        String expectedText = complainby;
        int S2 = 0;
        String s1 = null;
        ArrayList listNames = new ArrayList();

        while (queryRs1.next()) {

            s1 = queryRs1.getString("name");
            S2 = queryRs1.getInt("id");
            listNames.add(s1);
        }

        actionClass.CompareListandstring(expectedText, s1);
        actionClass.clickOnObject(this.Searchfeild);
        actionClass.setValueinTextbox(this.Searchfeild,complainby);
        actionClass.clickOnObject(this.Editbn);
        actionClass.clickOnObject(this.ComplainBy);
        actionClass.setValueinTextbox(this.ComplainBy,updatename);
        JavascriptExecutor jsetaskscore1 = (JavascriptExecutor) driver1;
        jsetaskscore.executeScript("scrollBy(0, 850)");
        actionClass.clickOnObject(SaveComplainbtn);
        VerificationClass match1 = new VerificationClass(driver1, extentTest);
        match1.verifyTextPresent(this.ComplainaddedMessage, "Complaint updated successfully");
        actionClass.captureScreen("Update complain");
        DatabaseFunctions DAB1 = new DatabaseFunctions(extentTest);
        conn = DAB.connect();
        statement = conn.createStatement();
        String complainupdate = "SELECT id,name FROM `complaint` WHERE  name='"+updatename+"' AND id ='" + S2 + "'";
        ResultSet queryRs2 = statement.executeQuery(complainupdate);
        String expectedText1 = updatename;
        int S3 = 0;
        ArrayList listNames3 = new ArrayList();

        while (queryRs2.next()) {
            s1 = queryRs2.getString("name");
            S3 = queryRs2.getInt("id");
            listNames3.add(s1);
            actionClass.CompareListandstring(expectedText, s1);
        }

        actionClass.CompareList(listNames, listNames3);
        extentTest.log(Status.INFO, "Records updated successfully " + complainby + "updated with " + updatename);
        System.out.println("Records updated successfully " +complainby + "updated with" + updatename);
    }

    public void Viewcomplain()throws IOException, InterruptedException {
        ActionClass actionClass = new ActionClass(driver1, extentTest);
//        actionClass.clickOnObject(this.FrontOfficeMenuClick);
        Thread.sleep(5000);
        actionClass.clickOnObject(this.FrontOffiComplainClick);
        Thread.sleep(5000);
        List<WebElement> ListStudent = driver1.findElements(By.xpath("//*[@id=\"DataTables_Table_0\"]/tbody/tr[1]"));
        int ClassStudentsize = ListStudent.size();
        String s = null;

        for (int i = 1; i <= ClassStudentsize; i++) {
            s = driver1.findElement(By.xpath("/html/body/div[1]/div[1]/section[2]/div/div[2]/div/div[2]/div[2]/div/table/tbody/tr[1]/td[3]")).getText();
            System.out.println("Value in list is: " + s);
        }

        actionClass.clickOnObject(this.Viewbtn);
        Thread.sleep(1000);
        actionClass.clickOnObject((this.detailpop));
        String s2 = driver1.findElement(By.xpath("/html/body/div[1]/div[2]/div/div/div[2]/table/tbody/tr[2]/td[2]")).getText();
        System.out.println(s2);
        actionClass.CompareListandstring(s, s2);
        actionClass.captureScreen("View Complain popup");
        Thread.sleep(1000);
        actionClass.clickOnObject(this.Closeview);
    }

    public void DeleteComplain()throws IOException, InterruptedException {
        ActionClass actionClass = new ActionClass(driver1, extentTest);
       //actionClass.clickOnObject(this.FrontOfficeMenuClick);
        Thread.sleep(5000);
        actionClass.clickOnObject(this.FrontOffiComplainClick);
        Thread.sleep(5000);
        driver1.findElement(By.xpath("//*[@id=\"DataTables_Table_0\"]/tbody/tr[1]/td[6]/a[3]")).click();
        Thread.sleep(5000);
        Alert alert = driver1.switchTo().alert();
        alert.accept();
        Thread.sleep(5000);
        VerificationClass verificationClass=new VerificationClass(driver1,extentTest);
        verificationClass.verifyTextPresent(this.DeleteMessage,"Complaint deleted successfully");
        actionClass.captureScreen("Delete Complain");
    }
}
