package AEDU.Pages;
import AEDU.Actions.ActionClass;
import AEDU.Actions.VerificationClass;
import AEDU.Utilities.DatabaseFunctions;
import AEDU.constants.CommonVar;
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

public class VisitorBook {
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

    @FindBy(how = How.XPATH, using = "/html/body/div[1]/aside/div/section/ul[2]/li[6]/a")
    private WebElement FrontOfficeMenuClick;

    @FindBy(how = How.XPATH, using = "/html/body/div[1]/aside/div/section/ul[2]/li[6]/ul/li[2]/a")
    private WebElement VisitorBookClick;

    @FindBy(how = How.XPATH, using = "/html/body/div[1]/div[1]/section[2]/div/div[1]/div/form/div[2]/button")
    private WebElement SaveBtn;

    @FindBy(how = How.XPATH, using = " /html/body/div[1]/div[1]/section[2]/div/div[1]/div/form/div[1]/div[1]/span/p")
    private WebElement PurposeFieldValidation;

    @FindBy(how = How.XPATH, using = " //*[@id=\"form1\"]/div[1]/div[2]/span/p")
    private WebElement NameFieldValidation;

    @FindBy(how = How.XPATH, using = "//*[@id=\"form1\"]/div[1]/div[7]/span/p")
    private WebElement InTimeFieldValidation;

    @FindBy(how = How.XPATH, using = "//*[@id=\"form1\"]/div[1]/div[8]/span/p")
    private WebElement InOutFieldValidation;

    @FindBy(how = How.XPATH, using = "//*[@id=\"form1\"]/div[1]/div[1]/select")
    private WebElement PurposeField;

    @FindBy(how = How.XPATH, using = "//*[@id=\"form1\"]/div[1]/div[1]/select/option[2]")
    private WebElement PurposeSelect;

    @FindBy(how = How.XPATH, using = "//*[@id=\"form1\"]/div[1]/div[2]/input")
    private WebElement NameField;

    @FindBy(how = How.XPATH, using = "//*[@id=\"form1\"]/div[1]/div[3]/input")
    private WebElement PhoneField;

    @FindBy(how = How.XPATH, using = "//*[@id=\"form1\"]/div[1]/div[4]/input")
    private WebElement IdProofField;

    @FindBy(how = How.XPATH, using = "//*[@id=\"form1\"]/div[1]/div[5]/input")
    private WebElement NumberOfPersonField;

    @FindBy(how = How.XPATH, using = "//*[@id=\"date\"]")
    private WebElement DateField;

    @FindBy(how = How.XPATH, using = "/html/body/div[4]/div[1]/table/tbody/tr[4]/td[5]")
    private WebElement DateSelectionField;

    @FindBy(how = How.XPATH, using = "//*[@id=\"stime_\"]")
    private WebElement InTimeField;

    @FindBy(how = How.XPATH, using = "//*[@id=\"form1\"]/div[1]/div[7]/div/div[1]/table/tbody/tr[1]/td[1]/a")
    private WebElement InTimeAccordian;

    @FindBy(how = How.XPATH, using = "/html/body/div[1]/div[1]/section[2]/div/div[1]/div/form/div[1]/div[7]/div/div[1]/table/tbody/tr[1]/td[1]/a")
    private WebElement InTimeAccordian2;

    @FindBy(how = How.XPATH, using = "/html/body/div[1]/div[1]/section[2]/div/div[1]/div/form/div[1]/div[8]/div/div[2]/div/div/i")
    private WebElement OutTimeField;

    @FindBy(how = How.XPATH, using = "/html/body/div[1]/div[1]/section[2]/div/div[1]/div/form/div[1]/div[8]/div/div[1]/table/tbody/tr[1]/td[1]/a/i")
    private WebElement OutTimeAccordian;

    @FindBy(how = How.XPATH, using = "/html/body/div[1]/div[1]/section[2]/div/div[1]/div/form/div[1]/div[8]/div/div[1]/table/tbody/tr[1]/td[5]/a")
    private WebElement OuttimeAcordian2;

    @FindBy(how = How.XPATH, using = "/html/body/div[1]/div[1]/section[2]/div/div[1]/div")
    private WebElement RuffClick;

    @FindBy(how = How.XPATH, using = " /html/body/div[1]/div[1]/section[2]/div/div[1]/div/form/div[1]/div[8]/div/div[2]/div/div")
    private WebElement OutTestClick;

    @FindBy(how = How.XPATH, using = " /html/body/div[1]/div[1]/section[2]/div/div[1]/div/form/div[1]/div[1]")
    private WebElement VeyMessage;

    @FindBy(how = How.XPATH, using = "/html/body/div[1]/div[1]/section[2]/div/div[2]/div/div[2]/div[2]/div/div[2]/label/input")
    private WebElement SearchField;

    @FindBy(how = How.XPATH, using = "//*[@id=\"DataTables_Table_0\"]/tbody/tr/td[7]/a[2]")
    private WebElement EditBtn;

    @FindBy(how = How.XPATH, using = "//*[@id=\"form1\"]/div[1]/div[2]/input")
    private WebElement EditNameField;

    @FindBy(how = How.XPATH, using = "//*[@id=\"form1\"]/div[2]/button")
    private WebElement EditSave;

    @FindBy(how = How.XPATH, using = "/html/body/div[1]/div[1]/section[2]/div/div[2]/div/div[2]/div[2]/div/table/tbody/tr[1]/td[7]/a[1]")
    private WebElement ViewBtn;

    @FindBy(how = How.XPATH, using = "/html/body/div[1]/div[2]/div/div/div[1]/h4")
    private WebElement ViewPopup;

    @FindBy(how = How.XPATH, using = "/html/body/div[1]/div[2]/div/div/div[1]/button")
    private WebElement ClosePopup;

    @FindBy(how = How.XPATH, using = "/html/body/div[1]/div[1]/section[2]/div/div[2]/div/div[2]/div[2]/div/table/tbody/tr[1]/td[7]/a[3]/i")
    private WebElement DeleteBtn;

    @FindBy(how = How.XPATH, using = "/html/body/div[1]/div[1]/section[2]/div/div[1]/div/form/div[1]/div[1]")
    private WebElement DeleteMessage;

    @FindBy(how = How.XPATH, using = "//*[@id=\"DataTables_Table_0_filter\"]/label/input")
    private WebElement SearchVisitor;

    @FindBy(how = How.XPATH, using = "//*[@id=\"DataTables_Table_0\"]/tbody/tr/td[7]/a[1]")
    private WebElement ViewAddedVisitor;

    @FindBy(how = How.XPATH, using = "//*[@id=\"visitordetails\"]/div/div/div[1]/button")
    private WebElement DetailsClose ;

    @FindBy(how = How.XPATH, using = "//*[@id=\"visitordetails\"]/div/div/div[1]/button")
    private WebElement EditDetailsClose ;


    public VisitorBook(WebDriver driver, ExtentTest test) {
        this.driver = driver;
        this.extentTest = test;
        PageFactory.initElements(driver, this);
    }

    public void openNewTab() throws InterruptedException {
//       CommonVar commonVar = new CommonVar(driver);
        Thread.sleep(2000);
        for (String childTab : driver.getWindowHandles()) {
            driver.switchTo().window(childTab);
        }
//        ((JavascriptExecutor) driver).executeScript("window.open('"+commonVar.url+"')");
        Thread.sleep(1000);
    }

    public void CheckValidation() throws InterruptedException, IOException {
        ActionClass actionClass = new ActionClass(driver, extentTest);
        /*Thread.sleep(5000);
        actionClass.clickOnObject((this.FrontOfficeMenuClick));*/
        Thread.sleep(5000);
        actionClass.clickOnObject(this.VisitorBookClick);
        JavascriptExecutor jsetaskscore = (JavascriptExecutor) driver;
        jsetaskscore.executeScript("scrollBy(0, 450)");
        Thread.sleep(2000);
        actionClass.clickOnObject(this.SaveBtn);
        VerificationClass verificationClass = new VerificationClass(driver, extentTest);
        Thread.sleep(2000);
        actionClass.captureScreen("Visitor Book Valiation");
        verificationClass.verifyTextPresent(this.PurposeFieldValidation, "The Purpose field is required.");
        verificationClass.verifyTextPresent(this.NameFieldValidation, "The Name field is required.");
        jsetaskscore.executeScript("scrollBy(0, 450)");
        Thread.sleep(2000);
        verificationClass.verifyTextPresent(this.InTimeFieldValidation, "The In Time field is required.");
        verificationClass.verifyTextPresent(this.InOutFieldValidation, "The Out Time field is required.");
        Thread.sleep(2000);
        actionClass.captureScreen("Visitor Book Valiation");
    }

    public void AddValidData(String AddName, String AddphoneNumber, String AddId, String Nbofperson) throws IOException, InterruptedException, SQLException {
        ActionClass actionClass = new ActionClass(driver, extentTest);
//        actionClass.clickOnObject(this.FrontOfficeMenuClick);
       Thread.sleep(5000);
        actionClass.clickOnObject(this.VisitorBookClick);
        Thread.sleep(2000);
        actionClass.clickOnObject(this.PurposeField);
        Thread.sleep(2000);
        actionClass.clickOnObject(this.PurposeSelect);
        actionClass.setValueinTextbox(this.NameField, AddName);
        actionClass.setValueinTextbox(this.PhoneField, AddphoneNumber);
        actionClass.setValueinTextbox(this.IdProofField, AddId);
        JavascriptExecutor jsetaskscore = (JavascriptExecutor) driver;
        jsetaskscore.executeScript("scrollBy(0,300)");
        actionClass.setValueinTextbox(this.NumberOfPersonField, Nbofperson);
        Thread.sleep(2000);
        actionClass.clickOnObject(this.DateField);
        Thread.sleep(2000);
        actionClass.clickOnObject(this.DateSelectionField);
        jsetaskscore.executeScript("scrollBy(0,300)");
        actionClass.clickOnObject(this.InTimeField);
        Thread.sleep(1000);
        actionClass.clickOnObject(this.InTimeAccordian);
        Thread.sleep(1000);
        actionClass.clickOnObject(this.InTimeAccordian);
        Thread.sleep(1000);
        actionClass.clickOnObject(this.InTimeAccordian2);
        WebElement textbox = driver.findElement(By.name("time"));
        textbox.sendKeys(Keys.TAB);
        Thread.sleep(1000);
        actionClass.clickOnObject(this.OutTimeAccordian);
        Thread.sleep(1000);
        actionClass.clickOnObject(this.OutTimeAccordian);
        Thread.sleep(1000);
        actionClass.clickOnObject(this.OutTimeAccordian);
        Thread.sleep(1000);
        actionClass.clickOnObject(this.OutTimeAccordian);
        Thread.sleep(1000);
        actionClass.clickOnObject(this.OuttimeAcordian2);
        Thread.sleep(1000);
        jsetaskscore.executeScript("scrollBy(0,100)");
        actionClass.clickOnObject(this.SaveBtn);
        VerificationClass verificationClass = new VerificationClass(driver, extentTest);
        verificationClass.verifyTextPresent(this.VeyMessage, "Visitors added successfully");
        actionClass.captureScreen("Visitor added successfully");

        DatabaseFunctions DAB = new DatabaseFunctions(extentTest);
        conn = DAB.connect();
        statement = conn.createStatement();
        String visitors = "SELECT * FROM `visitors_book` WHERE name = '" + AddName + "'";
        queryRs = statement.executeQuery(visitors);
        ArrayList<String> listNames = new ArrayList<String>();
        String s1 = null;
        while (queryRs.next()) {
            s1 = queryRs.getString("name");
            System.out.println("Value in database list: " + s1);
        }
//      Search the added visitor and Go to View page of that visitor
        Thread.sleep(2000);
        actionClass.setValueinTextbox(SearchVisitor, AddName);
        actionClass.clickOnObject(ViewAddedVisitor);
        Thread.sleep(2000);
        actionClass.captureScreen("View added Visitor");
        String visitorDetails= driver.findElement(By.xpath("//*[@id=\"getdetails\"]/table/tbody/tr[1]/td[2]")).getText();
        System.out.println(visitorDetails);
//      Compare the added values with the DB and View values
        if(visitorDetails.contains(AddName) &&  s1.contains(AddName)){
            System.out.println("Visitor added is matching with DB and View");
            extentTest.log(Status.PASS, "Visitor added is matching with DB and View");
        }
        else{
            System.out.println("Visitor added is not matching with DB and View");
            extentTest.log(Status.FAIL, "Visitor added is not matching with DB and View");
        }
        actionClass.clickOnObject(DetailsClose);
    }

    public void EditData(String AddName2, String AddphoneNumber, String AddId, String Nbofperson, String PersonCount, String updatedname) throws IOException, InterruptedException, SQLException {
        ActionClass actionClass = new ActionClass(driver, extentTest);
//        actionClass.clickOnObject(this.FrontOfficeMenuClick);
        Thread.sleep(5000);
        actionClass.clickOnObject(this.VisitorBookClick);
        Thread.sleep(2000);
        actionClass.clickOnObject(this.PurposeField);
        Thread.sleep(2000);
        actionClass.clickOnObject(this.PurposeSelect);
        actionClass.setValueinTextbox(this.NameField, AddName2);
        actionClass.setValueinTextbox(this.PhoneField, AddphoneNumber);
        actionClass.setValueinTextbox(this.IdProofField, AddId);
        JavascriptExecutor jsetaskscore = (JavascriptExecutor) driver;
        jsetaskscore.executeScript("scrollBy(0,300)");
        actionClass.setValueinTextbox(this.NumberOfPersonField, Nbofperson);
        Thread.sleep(2000);
        actionClass.clickOnObject(this.DateField);
        Thread.sleep(2000);
        actionClass.clickOnObject(this.DateSelectionField);
        jsetaskscore.executeScript("scrollBy(0,300)");
        actionClass.clickOnObject(this.InTimeField);
        Thread.sleep(1000);
        actionClass.clickOnObject(this.InTimeAccordian);
        Thread.sleep(1000);
        actionClass.clickOnObject(this.InTimeAccordian);
        Thread.sleep(1000);
        actionClass.clickOnObject(this.InTimeAccordian2);
        WebElement textbox = driver.findElement(By.name("time"));
        textbox.sendKeys(Keys.TAB);
        Thread.sleep(1000);
        actionClass.clickOnObject(this.OutTimeAccordian);
        Thread.sleep(1000);
        actionClass.clickOnObject(this.OutTimeAccordian);
        Thread.sleep(1000);
        actionClass.clickOnObject(this.OutTimeAccordian);
        Thread.sleep(1000);
        actionClass.clickOnObject(this.OutTimeAccordian);
        Thread.sleep(1000);
        actionClass.clickOnObject(this.OuttimeAcordian2);
        Thread.sleep(1000);
        jsetaskscore.executeScript("scrollBy(0,100)");
        actionClass.clickOnObject(this.SaveBtn);
        VerificationClass verificationClass = new VerificationClass(driver, extentTest);
        verificationClass.verifyTextPresent(this.VeyMessage, "Visitors added successfully");
        actionClass.captureScreen("Visitor added successfully");

        DatabaseFunctions DAB = new DatabaseFunctions(extentTest);
        conn = DAB.connect();
        statement = conn.createStatement();
        String visitors = "SELECT * FROM `visitors_book` WHERE name = '" + AddName2 + "'";
        queryRs = statement.executeQuery(visitors);
        ArrayList<String> listNames = new ArrayList<String>();
        String s1 = null;
        while (queryRs.next()) {
            s1 = queryRs.getString("name");
            System.out.println("Value in database list: " + s1);
        }
//      Search the added visitor and Go to View page of that visitor
        Thread.sleep(2000);
        actionClass.setValueinTextbox(SearchVisitor, AddName2);
        actionClass.clickOnObject(ViewAddedVisitor);
        Thread.sleep(2000);
        actionClass.captureScreen("View added Visitor");
        String visitorDetails= driver.findElement(By.xpath("//*[@id=\"getdetails\"]/table/tbody/tr[1]/td[2]")).getText();
        System.out.println(visitorDetails);
//      Compare the added values with the DB and View values
        if(visitorDetails.contains(AddName2) &&  s1.contains(AddName2)){
            System.out.println("Visitor added is matching with DB and View");
            extentTest.log(Status.PASS, "Visitor added is matching with DB and View");
        }
        else{
            System.out.println("Visitor added is not matching with DB and View");
            extentTest.log(Status.FAIL, "Visitor added is not matching with DB and View");
        }
        actionClass.clickOnObject(DetailsClose);
        Thread.sleep(2000);
//      Edit the value and compare with db
        actionClass.clickOnObject(EditBtn);
        Thread.sleep(4000);
        EditNameField.clear();
        actionClass.setValueinTextbox(EditNameField, updatedname);
        Thread.sleep(2000);
        jsetaskscore.executeScript("scrollBy(0,500)");
        actionClass.clickOnObject(EditSave);
        VerificationClass verificationClass1 = new VerificationClass(driver, extentTest);
        verificationClass1.verifyTextPresent(this.VeyMessage, "Visitor Updated successfully");
        actionClass.captureScreen("Visitor updated successfully");
        DatabaseFunctions DAB1 = new DatabaseFunctions(extentTest);
        conn = DAB1.connect();
        statement = conn.createStatement();
        String visitors1 = "SELECT * FROM `visitors_book` WHERE name = '" + updatedname + "'";
        queryRs = statement.executeQuery(visitors1);
        ArrayList<String> listNames1 = new ArrayList<String>();
        String s2 = null;
        while (queryRs.next()) {

            s2 = queryRs.getString("name");
            System.out.println("Value in database list: " + s2);
        }
//      Search the edited visitor and Go to View page of that visitor
        Thread.sleep(2000);
        actionClass.setValueinTextbox(SearchVisitor, updatedname);
        actionClass.clickOnObject(ViewAddedVisitor);
        Thread.sleep(2000);
        actionClass.captureScreen("View edited Visitor");
        String visitorDetails1= driver.findElement(By.xpath("//*[@id=\"getdetails\"]/table/tbody/tr[1]/td[2]")).getText();
        System.out.println(visitorDetails1);
//      Compare the edited values with the DB and View values
        if(visitorDetails1.contains(updatedname) &&  s2.contains(updatedname)){
            System.out.println("Visitor edited is matching with DB and View");
            extentTest.log(Status.PASS, "Visitor edited is matching with DB and View");
        }
        else{
            System.out.println("Visitor edited is not matching with DB and View");
            extentTest.log(Status.FAIL, "Visitor edited is not matching with DB and View");
        }
        actionClass.clickOnObject(EditDetailsClose);
        Thread.sleep(2000);
    }

    public void Delete()throws InterruptedException,IOException{
        ActionClass actionClass = new ActionClass(driver, extentTest);
//        actionClass.clickOnObject(FrontOfficeMenuClick);
//        Thread.sleep(2000);
        Thread.sleep(5000);
        actionClass.clickOnObject(this.VisitorBookClick);
        Thread.sleep(1000);
        actionClass.clickOnObject(this.DeleteBtn);
        Alert alert = driver.switchTo().alert();
        alert.accept();
        VerificationClass very1 = new VerificationClass(driver, extentTest);
        very1.verifyTextPresent(this.DeleteMessage, "Visitor deleted successfully");
        actionClass.captureScreen("Visitor entry  Deleted ");
    }
}
