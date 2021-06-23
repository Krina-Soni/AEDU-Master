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

public class PostalDispatch {
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
            using = "//*[@id=\"form-username\"]\n")
    private WebElement username;

    @FindBy(how = How.XPATH,
            using = "//*[@id=\"form-password\"]\n"
    )
    private WebElement password1;

    @FindBy(how = How.XPATH,
            using ="//*[@id=\"form1\"]/div[2]/button"
    )
    private WebElement submitbtn;

    @FindBy(how = How.XPATH,
            using = "  /html/body/div[1]/aside/div/section/ul[2]/li[6]/a/span"
    )
    private WebElement FrontOfficeCLick;

    @FindBy(how = How.XPATH,
            using = "/html/body/div[1]/aside/div/section/ul[2]/li[6]/ul/li[4]/a"
    )
    private WebElement PostalDidpatchClick;

    @FindBy(how = How.XPATH,
            using = "/html/body/div[1]/div[1]/section[2]/div/div[1]/div/form/div[2]/button"
    )
    private WebElement PostalSave;
    @FindBy(how = How.XPATH,
            using = "/html/body/div[1]/div[1]/section[2]/div/div[1]/div/form/div[1]/div[1]/span/p"
    )
    private WebElement validationfotsave;

    @FindBy(how = How.NAME,
            using = "to_title"
    )
    private WebElement AddTitle;

    @FindBy(how = How.XPATH,
            using = "/html/body/div[1]/div[1]/section[2]/div/div[1]/div/form/div[1]/div[2]/input"
    )
    private WebElement refno;

    @FindBy(how = How.XPATH,
            using = "//*[@id=\"description\"]"
    )
    private WebElement Address;

    @FindBy(how = How.XPATH,
            using = "//*[@id='description']"
    )
    private WebElement Note;

    @FindBy(how = How.NAME,
            using = "from"
    )
    private WebElement Fromtitle;
    @FindBy(how = How.XPATH,
            using = "/html/body/div[1]/div[1]/section[2]/div/div[1]/div/form/div[1]/div[1]"
    )
    private WebElement Addedsussefullymessage;
    @FindBy(how = How.XPATH,
            using = " /html/body/div[1]/div[1]/section[2]/div/div[2]/div/div[2]/div[2]/div/table/tbody/tr[1]/td[5]/a[2]/i"
    )
    private WebElement EditBtn;
    @FindBy(how = How.XPATH,
            using = "/html/body/div[1]/div[1]/section[2]/div/div[1]/div/form/div[1]/div[1]"
    )
    private WebElement Updatemessage;
    @FindBy(how = How.XPATH,
            using = "/html/body/div[1]/div[1]/section[2]/div/div[2]/div/div[2]/div[2]/div/table/tbody/tr[1]/td[5]/a[1]"
    )
    private WebElement Viewbtn;
    @FindBy(how = How.XPATH,
            using = " /html/body/div[1]/div[2]/div/div/div[1]/button"
    )
    private WebElement Closebtn;
    @FindBy(how = How.XPATH,
            using = "  /html/body/div[1]/div[1]/section[2]/div/div[2]/div/div[2]/div[2]/div/div[2]/label/input"
    )
    private WebElement searchfeild;
    @FindBy(how = How.XPATH,
            using = "/html/body/div[1]/div[1]/section[2]/div/div[2]/div/div[2]/div[2]/div/table/tbody/tr[1]/td[5]/a[3]"
    )
    private WebElement Deletebtn;
    @FindBy(how = How.XPATH,
            using = " /html/body/div[1]/div[1]/section[2]/div/div[1]/div/form/div[1]/div[1]"
    )
    private WebElement Deletemessage;
    @FindBy(how = How.XPATH,
            using = " /html/body/div[1]/div[1]/section[2]/div/div[2]/div/div[2]/div[2]/div/div[2]/label/input"
    )
    private WebElement Searchfeild;
    @FindBy(how = How.XPATH,
            using = "  /html/body/div[1]/div[2]/div/div/div[1]/h4"
    )
    private WebElement DetailsSearch;
    @FindBy(how = How.XPATH,
            using = "/html/body/div[1]/div[1]/section[2]/div/div[2]/div/div[2]/div[2]/div/table/tbody/tr[1]/td[5]/a[1]/i"
    )
    private WebElement View;

    public PostalDispatch(WebDriver driver, ExtentTest test) {
        this.driver = driver;
        this.extentTest = test;
        PageFactory.initElements(driver, this);
    }

    public void openNewTab() throws InterruptedException {
        ActionClass actionClass=new ActionClass(driver, extentTest);
        Thread.sleep(2000);
        driver.getCurrentUrl();
        JavascriptExecutor jsetaskscore1 = (JavascriptExecutor) driver;
        jsetaskscore1.executeScript("scrollBy(0, -300)");
        Thread.sleep(2000);

        for(String childTab : driver.getWindowHandles()){
            driver.switchTo().window(childTab);
        }

        Thread.sleep(1000);
    }

    public void CheckvalidationforPostal() throws IOException, InterruptedException {
        ActionClass actionClass = new ActionClass(driver, extentTest);
        //actionClass.clickOnObject(this.submitbtn);
//        Thread.sleep(5000);
//        actionClass.clickOnObject((this.FrontOfficeCLick));
        Thread.sleep(5000);
        actionClass.clickOnObject(this.PostalDidpatchClick);
        Thread.sleep(1000);
        JavascriptExecutor jsetaskscore = (JavascriptExecutor) driver;
        jsetaskscore.executeScript("scrollBy(0, 550)");
        actionClass.clickOnObject(this.PostalSave);
    }

    public void AddpostalwithValidDAta(String username1, String pwd, String Title, String Name, String Rfno, String address, String Addnote) throws IOException, InterruptedException, SQLException {
        ActionClass actionClass = new ActionClass(driver, extentTest);
//        actionClass.clickOnObject((this.FrontOfficeCLick));
//        Thread.sleep(1000);
        actionClass.clickOnObject(this.PostalDidpatchClick);
        actionClass.clickOnObject(this.AddTitle);
        actionClass.setValueinTextbox(this.AddTitle, Title);
        actionClass.setValueinTextbox(this.AddTitle, Name);
        actionClass.clickOnObject(this.refno);
        actionClass.setValueinTextbox(this.refno, Rfno);
        actionClass.clickOnObject(this.Address);
        actionClass.setValueinTextbox(this.Address, address);
        JavascriptExecutor jsetaskscore = (JavascriptExecutor) driver;
        jsetaskscore.executeScript("scrollBy(0, 150)");
        actionClass.clickOnObject(this.Note);
        actionClass.setValueinTextbox(this.Note, Addnote);
        actionClass.clickOnObject(this.PostalSave);
        VerificationClass very = new VerificationClass(driver, extentTest);
        very.verifyTextPresent(this.Addedsussefullymessage, "Dispatch added successfully");
        actionClass.captureScreen("Dispatch add");
        DatabaseFunctions DAB = new DatabaseFunctions(extentTest);
        conn = DAB.connect();
        statement = conn.createStatement();
        String PostalDisapatch = "SELECT to_title FROM `dispatch_receive` WHERE type = 'dispatch' AND to_title='" + Name + "'";
        ResultSet queryRs1 = statement.executeQuery(PostalDisapatch);
        String expectedText = Name;
        ArrayList listNames = new ArrayList();
        while (queryRs1.next()) {
            String s1 = null;
            s1 = queryRs1.getString("to_title");
            actionClass.CompareListandstring(expectedText, s1);
        }
    }

    public void Edit(String Name, String Name1, String Title, String Rfno, String address, String Addnote) throws IOException, InterruptedException, SQLException {
        ActionClass actionClass = new ActionClass(driver, extentTest);
        Thread.sleep(5000);
        actionClass.clickOnObject(this.PostalDidpatchClick);
        actionClass.clickOnObject(this.AddTitle);
        actionClass.setValueinTextbox(this.AddTitle, Title);
        actionClass.setValueinTextbox(this.AddTitle, Name);
        actionClass.clickOnObject(this.refno);
        actionClass.setValueinTextbox(this.refno, Rfno);
        actionClass.clickOnObject(this.Address);
        actionClass.setValueinTextbox(this.Address, address);
        JavascriptExecutor jsetaskscore = (JavascriptExecutor) driver;
        jsetaskscore.executeScript("scrollBy(0, 150)");
        actionClass.clickOnObject(this.Note);
        actionClass.setValueinTextbox(this.Note, Addnote);
        actionClass.clickOnObject(this.PostalSave);
        VerificationClass very = new VerificationClass(driver, extentTest);
        very.verifyTextPresent(this.Addedsussefullymessage, "Dispatch added successfully");
        actionClass.captureScreen("Dispatch add");
        DatabaseFunctions DAB = new DatabaseFunctions(extentTest);
        conn = DAB.connect();
        statement = conn.createStatement();
        String PostalReceive = "SELECT id,to_title FROM `dispatch_receive` WHERE type = 'dispatch' AND to_title='" + Name + "'";
        ResultSet queryRs1 = statement.executeQuery(PostalReceive);
        String expectedText = Name;
        String s1 = null;
        int S2 = 0;
        ArrayList listNames = new ArrayList();

        while (queryRs1.next()) {
            s1 = queryRs1.getString("to_title");
            S2 = queryRs1.getInt("id");
            listNames.add(s1);
        }

        actionClass.CompareListandstring(expectedText, s1);
        actionClass.clickOnObject(this.Searchfeild);
        actionClass.setValueinTextbox(this.Searchfeild, Name);
        actionClass.clickOnObject(this.EditBtn);
        actionClass.clickOnObject(this.AddTitle);
        actionClass.setValueinTextbox(this.AddTitle, Name1);
        JavascriptExecutor jsetaskscore1 = (JavascriptExecutor) driver;
        jsetaskscore.executeScript("scrollBy(0, 150)");
        actionClass.clickOnObject(this.PostalSave);
        VerificationClass very1 = new VerificationClass(driver, extentTest);
        very.verifyTextPresent(this.Updatemessage, "Dispatch updated successfully");
        String PostalReceiveupdate = "SELECT id,to_title FROM `dispatch_receive` WHERE type = 'dispatch' AND to_title='" + Name + "' AND id ='" + S2 + "'";
        ResultSet queryRs2 = statement.executeQuery(PostalReceiveupdate);
        String expectedText1 = Name;
        int S3 = 0;
        ArrayList listNames3 = new ArrayList();

        while (queryRs2.next()) {
            String s3 = queryRs2.getString("to_title");
            S3 = queryRs2.getInt("id");
            listNames3.add(s3);
            actionClass.CompareListandstring(expectedText, s3);
        }
        actionClass.CompareList(listNames, listNames3);
        extentTest.log(Status.INFO, "Records updated successfully " + Name + " updated with " + Name1);
        System.out.println("Records updated successfully " + Name + " updated with " + Name1);
    }

    public void View() throws IOException, InterruptedException, SQLException {
        ActionClass actionClass = new ActionClass(driver, extentTest);
//        actionClass.clickOnObject((this.FrontOfficeCLick));
        Thread.sleep(5000);
        actionClass.clickOnObject(this.PostalDidpatchClick);
        Thread.sleep(1000);
        List<WebElement> ListStudent = driver.findElements(By.xpath("/html/body/div[1]/div[1]/section[2]/div/div[2]/div/div[2]/div[2]/div/table/tbody/tr[1]/td[1]"));
        int ClassStudentsize = ListStudent.size();
        String s = null;

        for (int i = 1; i <= ClassStudentsize; i++) {
            s = driver.findElement(By.xpath("/html/body/div[1]/div[1]/section[2]/div/div[2]/div/div[2]/div[2]/div/table/tbody/tr[1]/td[1]")).getText();
            System.out.println("Value in list is: " + s);
        }

        Thread.sleep(100);
        actionClass.captureScreen("View postal");
        actionClass.clickOnObject(this.View);
        Thread.sleep(3000);
        actionClass.captureScreen("View Dispatch");
        actionClass.clickOnObject(this.Closebtn);
    }

    public void DeletePostal(String Name, String Name1, String Title, String Rfno, String address, String Addnote) throws InterruptedException, IOException, SQLException {
        ActionClass actionClass = new ActionClass(driver, extentTest);
//        actionClass.clickOnObject((this.FrontOfficeCLick));
        Thread.sleep(5000);
        actionClass.clickOnObject(this.PostalDidpatchClick);
        actionClass.clickOnObject(this.AddTitle);
        actionClass.setValueinTextbox(this.AddTitle, Title);
        actionClass.setValueinTextbox(this.AddTitle, Name);
        actionClass.clickOnObject(this.refno);
        actionClass.setValueinTextbox(this.refno, Rfno);
        actionClass.clickOnObject(this.Address);
        actionClass.setValueinTextbox(this.Address, address);
        JavascriptExecutor jsetaskscore = (JavascriptExecutor) driver;
        jsetaskscore.executeScript("scrollBy(0, 150)");
        actionClass.clickOnObject(this.Note);
        actionClass.setValueinTextbox(this.Note, Addnote);
        actionClass.clickOnObject(this.PostalSave);
        VerificationClass very = new VerificationClass(driver, extentTest);
        very.verifyTextPresent(this.Addedsussefullymessage, "Dispatch added successfully");
        actionClass.captureScreen("Dispatch add");
        DatabaseFunctions DAB = new DatabaseFunctions(extentTest);
        conn = DAB.connect();
        statement = conn.createStatement();
        String PostalReceive = "SELECT id,to_title FROM `dispatch_receive` WHERE type = 'dispatch' AND to_title='" + Name + "'";
        ResultSet queryRs1 = statement.executeQuery(PostalReceive);
        String expectedText = Name;
        int S2 = 0;
        String s1 = null;
        ArrayList listNames = new ArrayList();

        while (queryRs1.next()) {
            s1 = queryRs1.getString("to_title");
            S2 = queryRs1.getInt("id");
            listNames.add(s1);
        }

        actionClass.CompareListandstring(expectedText, s1);
        actionClass.clickOnObject(this.Deletebtn);
        Thread.sleep(5000);
        Alert alert = driver.switchTo().alert();
        alert.accept();
        String PostalDis = "SELECT id,to_title FROM `dispatch_receive` WHERE type = 'dispatch' AND to_title='" + expectedText + "'";
        ResultSet queryRs2 = statement.executeQuery(PostalDis);
        String expectedText2 = Name;
        int S3 = 0;
        ArrayList listNames2 = new ArrayList();
        while (queryRs2.next()) {
            String s4 = null;
            s1 = queryRs2.getString("to_title");
            S2 = queryRs2.getInt("id");
            if (queryRs2.next() == false) {
                extentTest.log(Status.PASS, "Records Deleted successfully");
                System.out.println("Records Deleted successfully");
            }
            listNames2.add(s1);

            }
        }
    }










