
package AEDU.Pages;
import AEDU.Actions.ActionClass;
import AEDU.Actions.VerificationClass;
import AEDU.Utilities.DatabaseFunctions;
import AEDU.constants.CommonVar;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

public class AdmissionEnquiry {
    WebDriver driver1;
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


    @FindBy(how = How.XPATH, using = " //*[@id=\"sibe-box\"]/ul[2]/li[6]/a")
    private WebElement ClickOnFrontOffice;

    @FindBy(how = How.XPATH, using = "//*[@id=\"sibe-box\"]/ul[2]/li[6]/ul/li[1]/a")
    private WebElement ClickOnAdmissionEnquiry;

    @FindBy(how = How.XPATH, using = "//*[@id=\"status\"]")
    private WebElement SelectStatus;

    @FindBy(how = How.XPATH, using = "//*[@id=\"status\"]/option[4]")
    private WebElement SelectPassiveStatus;

    @FindBy(how = How.XPATH, using = "//*[@id=\"status\"]/option[2]")
    private WebElement SelectAllStatus;

    @FindBy(how = How.XPATH, using = "//*[@id=\"source\"]")
    private WebElement SelectSource;

    @FindBy(how = How.XPATH, using = "//*[@id=\"source\"]/option[2]")
    private WebElement SelectASource;

    @FindBy(how = How.XPATH, using = "//*[@id=\"source\"]/option[6]")
    private WebElement SelectASource2;

    @FindBy(how = How.XPATH, using = "/html/body/div[1]/div[1]/section[2]/div/div/div[1]/form/div/div[4]/div/button")
    private WebElement ClickOnSearch;

    @FindBy(how = How.XPATH, using = "//*[@id=\"enquiry_date\"]")
    private WebElement ClickInEnquiryDateField;

    @FindBy(how = How.XPATH, using = "/html/body/div[3]/div[2]/div/table/tbody/tr[2]/td[5]")
    private WebElement SelectFromDate;

    @FindBy(how = How.XPATH, using = "/html/body/div[3]/div[1]/div/table/tbody/tr[2]/td[6]")
    private WebElement SelectToDate;

    @FindBy(how = How.XPATH, using = "/html/body/div[3]/div[3]/div/div[3]/button[1]")
    private WebElement ClickOnApply;

    @FindBy(how = How.XPATH, using = "/html/body/div[1]/div[1]/section[2]/div/div/div[2]/div/div[1]/div/button")
    private WebElement ClickAdd;

    @FindBy(how = How.XPATH, using = "//*[@id=\"name_add\"]")
    private WebElement NameField;

    @FindBy(how = How.XPATH, using = "//input[@id='number']")
    private WebElement PhoneField;

    @FindBy(how = How.XPATH, using = "//input[@name='email']")
    private WebElement EmailField;

    @FindBy(how = How.XPATH, using = "//textarea[@name='address']")
    private WebElement AddressField;

    @FindBy(how = How.XPATH, using = "//textarea[@name='description']")
    private WebElement DescriptionField;

    @FindBy(how = How.XPATH, using = "//textarea[@name='note']")
    private WebElement NoteField;

    @FindBy(how = How.XPATH, using = "//*[@id=\"name_add\"]")
    private WebElement DateField;

    @FindBy(how = How.XPATH, using = "//*[@id=\"date_of_call\"]")
    private WebElement NextFollowUpDateField;

    @FindBy(how = How.XPATH, using = "/html/body/div[8]/div[1]/table/tbody/tr[6]/td[3]")
    private WebElement SelectNextFollowUpDateField;

    @FindBy(how = How.XPATH, using = "//input[@name='assigned']")
    private WebElement AssignedField;

    @FindBy(how = How.XPATH, using = "//select[@name='reference']")
    private WebElement ReferenceField;

    @FindBy(how = How.XPATH, using = "//*[@id=\"formadd\"]/div/div[10]/div/select/option[2]")
    private WebElement SelectReferenceField;

    @FindBy(how = How.XPATH, using = "//form[@id='formadd']/div/div[11]/div/select")
    private WebElement SourceField;

    @FindBy(how = How.XPATH, using = "//*[@id=\"formadd\"]/div/div[11]/div/select/option[2]")
    private WebElement SelectSourceField;

    @FindBy(how = How.XPATH, using = "//*[@id=\"formadd\"]/div/div[12]/div/select")
    private WebElement ClassField;

    @FindBy(how = How.XPATH, using = "//*[@id=\"formadd\"]/div/div[12]/div/select/option[2]")
    private WebElement SelectClassField;

    @FindBy(how = How.XPATH, using = "//input[@name='no_of_child']")
    private WebElement NumberOfChildField;

    @FindBy(how = How.XPATH, using = "//input[@name='no_of_child']")
    private WebElement IncreaseNumberOfChild;

    @FindBy(how = How.XPATH, using = "//a[contains(text(),'Save')]")
    private WebElement SaveButton;

    @FindBy(how = How.XPATH, using = "//*[@id=\"myModal\"]/div/div/div[1]/button")
    private WebElement CloseButton;

    @FindBy(how = How.XPATH, using = "//p[contains(.,'The Name field is required.')]")
    private WebElement NameFieldRequiredValidation;

    @FindBy(how = How.XPATH, using = "//p[contains(.,'The Contact field is required.')]")
    private WebElement PhoneFieldRequiredValidation;

    @FindBy(how = How.XPATH, using = "//p[contains(.,'The Source field is required.')]")
    private WebElement SourceFieldRequiredValidation;

    @FindBy(how = How.XPATH, using = "//*[@id=\"formadd\"]/div/div[11]/div/select/option[2]")
    private WebElement AddAdmissionEnquirySourceField;

    @FindBy(how = How.XPATH, using = "/html/body/div[1]/div[1]/section[2]/div/div/div[1]/div[2]/div")
    private WebElement AdmissionEnquirySuccessMessage;

    @FindBy(how = How.XPATH, using = "//*[@id=\"enquirytable\"]/tbody/tr[1]/td[8]/a[2]")
    private WebElement EditAdmissionEnquiry;

    @FindBy(how = How.XPATH, using = "/html/body/div[1]/div[1]/section[2]/div/div/div[1]/div[2]/div")
    private WebElement EditAdmissionEnquirySuccessMessage;

    @FindBy(how = How.XPATH, using = "//*[@id=\"enquirytable_filter\"]/label/input")
    private WebElement SmallSearch;

    @FindBy(how = How.XPATH, using = "/html/body/div[1]/div[1]/section[2]/div/div/div[2]/div/div[2]/div[2]/div/table/tbody/tr/td[8]/a[3]/i")
    private WebElement ClickOnDelete;

    @FindBy(how = How.XPATH, using = "/html/body/div[1]/div[1]/section[2]/div/div/div[1]/div[2]/div")
    private WebElement DeleteAdmissionEnquirySuccessMessage;

    @FindBy(how = How.XPATH, using = "//*[@id=\"enquirytable\"]/tbody/tr/td[8]/a[2]")
    private WebElement ClickOnEdit;

    @FindBy(how = How.XPATH, using = "//*[@id=\"name_value\"]")
    private WebElement EditNameField;

    @FindBy(how = How.XPATH, using = "//*[@id=\"getdetails\"]/div[2]/div/div/a")
    private WebElement EditSaveButton;

    @FindBy(how = How.XPATH, using = "//*[@id=\"date_of_call_edit\"]")
    private WebElement NextFollowUpDateEditField;

    @FindBy(how = How.CSS, using = "tr:nth-child(6) > .day:nth-child(3)")
    private WebElement NextFollowUpSelectDateEditField;

    @FindBy(how = How.XPATH, using = "//*[@id=\"enquirytable\"]/tbody/tr/td[8]/a[1]/i")
    private WebElement ClickOnPhoneIcon;

    @FindBy(how = How.XPATH, using = "//*[@id=\"follow_date_of_call\"]")
    private WebElement NextFollowUpDateInPopup;

    @FindBy(how = How.CSS, using = "tr:nth-child(4) > .day:nth-child(6)")
    private WebElement SelectNextFollowUpDateInPopup;


    @FindBy(how = How.XPATH, using = "//textarea[@id='response']")
    private WebElement Response;

    @FindBy(how = How.XPATH, using = "//a[@onclick='follow_save()']")
    private WebElement FollowUpSaveButton;

    @FindBy(how = How.XPATH, using = "//*[@id=\"follow_up\"]/div/div/div[1]/button")
    private WebElement FollowUpCloseButton;

    @FindBy(how = How.XPATH, using = "//*[@id=\"timeline\"]/ul/li[2]/div/div[1]")
    private WebElement FollowUpResponse;

    @FindBy(how = How.XPATH, using = "//*[@id=\"follow_up\"]/div/div/div[1]/button")
    private WebElement FollowUpCloseButton1;

    public String date="2020-03-05";
    public String status="active";
    public String status1="passive";
    public String source="Test 1 Source";
    public String source1="test";
    public String DeleteName="Aanal Shah";
    public String ActualName = "Johnny Shah";
    public String UpdatedName= "Krina Soni";
    public String FollowUpDateName = "Riddhi Dave";
    public String FollowUpEditDateName = "Riddhi Harper";
    public String FollowUpResponseMessage = "Testing Response";

    public AdmissionEnquiry(WebDriver driver, ExtentTest test) {
        this.driver1 = driver;
        this.extentTest = test;
        PageFactory.initElements(driver, this);
    }

    public void openNewTab() throws InterruptedException {
//        String parentWindowHandle=driver.getWindowHandle();
        ActionClass actionClass=new ActionClass(driver1, extentTest);
        Thread.sleep(2000);
        driver1.getCurrentUrl();
        JavascriptExecutor jsetaskscore1 = (JavascriptExecutor) driver1;
        jsetaskscore1.executeScript("scrollBy(0, -300)");
        Thread.sleep(2000);
        for(String childTab : driver1.getWindowHandles()){
//            System.out.println(childTab);
            driver1.switchTo().window(childTab);
        }
        Thread.sleep(1000);
    }

    //Match the active list admission enquiry with database
    public Object[] ListofActiveAdmissionEnquiry() throws SQLException, IOException, InterruptedException {
        ActionClass actionClass = new ActionClass(this.driver1, extentTest);
        Thread.sleep(5000);
        actionClass.clickOnObject(this.ClickOnFrontOffice);
        Thread.sleep(5000);
        actionClass.clickOnObject(this.ClickOnAdmissionEnquiry);
        Thread.sleep(2000);
        JavascriptExecutor jsetaskscore = (JavascriptExecutor) driver1;
        jsetaskscore.executeScript("scrollBy(0, 500)");
        actionClass.captureScreen("List of AdmissionEnquiry");
        List<WebElement> ListAdmissionEnquiry = driver1.findElements(By.xpath("//*[@id='enquirytable']/tbody/tr"));
        Thread.sleep(3000);
        int listsize = ListAdmissionEnquiry.size();
        ArrayList<String> listNames1 = new ArrayList<String>();
        for (int i = 1; i <= listsize; i++) {
            String s = driver1.findElement(By.xpath("//*[@id='enquirytable']/tbody/tr[" + i + "]/td[1]")).getText();
            System.out.println("Value in list is: " + s);
            listNames1.add(driver1.findElement(By.xpath("//*[@id='enquirytable']/tbody/tr[" + i + "]/td[1]")).getText());
        }
        ArrayList<String> admissionEnquiryList= new ArrayList<String>(listNames1.size());
        for(String myInt : listNames1 ){
            admissionEnquiryList.add(String.valueOf(myInt));
        }
        System.out.println(admissionEnquiryList);

        DatabaseFunctions DAB = new DatabaseFunctions(extentTest);
        conn = DAB.connect();
        statement = conn.createStatement();
        String students = "SELECT name FROM enquiry WHERE status='"+status+"'  ORDER BY `enquiry`.`name` ASC";
        queryRs = statement.executeQuery(students);
        ArrayList<String> listNames = new ArrayList<String>();

        while (queryRs.next()) {
            String s1 = null;
            s1 = queryRs.getString("name");
            System.out.println("Value in list database: " + s1);
            listNames.add(queryRs.getString("name"));
        }
        ArrayList<String> newIntegerList= new ArrayList<String>(listNames.size());
        for(String myInt : listNames ){
            newIntegerList.add(String.valueOf(myInt));
        }
        Collections.sort(newIntegerList);
        System.out.println(newIntegerList);
        System.out.println(admissionEnquiryList.equals(newIntegerList));
        actionClass.CompareStringList(newIntegerList,admissionEnquiryList);
        return listNames1.toArray();
    }

    //Match the inactive list admission enquiry with database
    public Object[] ListofInactiveAdmissionEnquiry() throws SQLException, IOException, InterruptedException {
//        Filter using filters
        ActionClass actionClass = new ActionClass(this.driver1, extentTest);
//        actionClass.clickOnObject(this.ClickOnFrontOffice);
        Thread.sleep(5000);
        actionClass.clickOnObject(this.ClickOnAdmissionEnquiry);
        Thread.sleep(2000);
        actionClass.clickOnObject(this.SelectStatus);
        actionClass.clickOnObject(this.SelectPassiveStatus);
        actionClass.clickOnObject(this.ClickOnSearch);
        Thread.sleep(2000);
        actionClass.captureScreen("List of AdmissionEnquiry");
        JavascriptExecutor jsetaskscore = (JavascriptExecutor) driver1;
        jsetaskscore.executeScript("scrollBy(0, 300)");
        actionClass.captureScreen("List of AdmissionEnquiry");
//      Fetch the list
        List<WebElement> ListofInactiveAdmissionEnquiry = driver1.findElements(By.xpath("//*[@id='enquirytable']/tbody/tr"));
        int listsize = ListofInactiveAdmissionEnquiry.size();
        ArrayList<String> listNames1 = new ArrayList<String>();
        for (int i = 1; i <= listsize; i++) {
            String s = driver1.findElement(By.xpath("//*[@id='enquirytable']/tbody/tr[" + i + "]/td[1]")).getText();
            System.out.println("Value in list is: " + s);
            listNames1.add(driver1.findElement(By.xpath("//*[@id='enquirytable']/tbody/tr[" + i + "]/td[1]")).getText());
        }
        ArrayList<String> InactiveadmissionEnquiryList= new ArrayList<String>(listNames1.size());
        for(String myInt : listNames1 ){
            InactiveadmissionEnquiryList.add(String.valueOf(myInt));
        }
        System.out.println(InactiveadmissionEnquiryList);
//      Fetch the list from database
        DatabaseFunctions DAB = new DatabaseFunctions(extentTest);
        conn = DAB.connect();
        statement = conn.createStatement();
        String students = "SELECT * FROM `enquiry`  WHERE status='"+status1+"' ORDER BY `enquiry`.`name` ASC";
        queryRs = statement.executeQuery(students);
        ArrayList<String> listNames = new ArrayList<String>();
        while (queryRs.next()) {
            String s1 = null;
            s1 = queryRs.getString("name");
            System.out.println("Value in list database: " + s1);
            listNames.add(queryRs.getString("name"));
        }
        ArrayList<String> newIntegerList= new ArrayList<String>(listNames.size());
        for(String myInt : listNames ){
            newIntegerList.add(String.valueOf(myInt));
        }
        //Collections.sort(newIntegerList);
        System.out.println(newIntegerList);
        System.out.println(InactiveadmissionEnquiryList.equals(newIntegerList));
        actionClass.CompareStringList(newIntegerList,InactiveadmissionEnquiryList);
        return listNames1.toArray();
    }

    //Match all list admission enquiry with database
    public Object[] ListofAllAdmissionEnquiry() throws SQLException, IOException, InterruptedException {
//      Filter using filters
        ActionClass actionClass = new ActionClass(this.driver1, extentTest);
//        actionClass.clickOnObject(this.ClickOnFrontOffice);
        Thread.sleep(5000);
        actionClass.clickOnObject(this.ClickOnAdmissionEnquiry);
        Thread.sleep(2000);
        actionClass.clickOnObject(this.SelectStatus);
        actionClass.clickOnObject(this.SelectAllStatus);
        actionClass.clickOnObject(this.ClickOnSearch);
        Thread.sleep(2000);
        actionClass.captureScreen("List of AdmissionEnquiry");
        JavascriptExecutor jsetaskscore = (JavascriptExecutor) driver1;
        jsetaskscore.executeScript("scrollBy(0, 500)");
        actionClass.captureScreen("List of AdmissionEnquiry");
//        Fetch the list
        List<WebElement> ListofAllAdmissionEnquiry = driver1.findElements(By.xpath("//*[@id='enquirytable']/tbody/tr"));
        int listsize = ListofAllAdmissionEnquiry.size();
        ArrayList<String> listNames1 = new ArrayList<String>();
        for (int i = 1; i <= listsize; i++) {
            String s = driver1.findElement(By.xpath("//*[@id='enquirytable']/tbody/tr[" + i + "]/td[1]")).getText();
            System.out.println("Value in list is: " + s);
            listNames1.add(driver1.findElement(By.xpath("//*[@id='enquirytable']/tbody/tr[" + i + "]/td[1]")).getText());
        }
        ArrayList<String> AlladmissionEnquiryList= new ArrayList<String>(listNames1.size());
        for(String myInt : listNames1 ){
            AlladmissionEnquiryList.add(String.valueOf(myInt));
        }
        System.out.println(AlladmissionEnquiryList);

        DatabaseFunctions DAB = new DatabaseFunctions(extentTest);
        conn = DAB.connect();
        statement = conn.createStatement();
        String students = "SELECT * FROM `enquiry`  ORDER BY `enquiry`.`name`  ASC";
        queryRs = statement.executeQuery(students);
        ArrayList<String> listNames = new ArrayList<String>();
        while (queryRs.next()) {
            String s1 = null;
            s1 = queryRs.getString("name");
            System.out.println("Value in list database: " + s1);
            listNames.add(queryRs.getString("name"));
        }
        ArrayList<String> newIntegerList= new ArrayList<String>(listNames.size());
        for(String myInt : listNames ){
            newIntegerList.add(String.valueOf(myInt));
        }
        // Collections.sort(newIntegerList);
        System.out.println(newIntegerList);
        System.out.println(AlladmissionEnquiryList.equals(newIntegerList));
        actionClass.CompareStringList(newIntegerList,AlladmissionEnquiryList);
        return listNames1.toArray();
    }
    //Match selected source admission enquiry with database
    public Object[] ListSourceAdmissionEnquiry() throws SQLException, IOException, InterruptedException {
//        Filter using filters
        ActionClass actionClass = new ActionClass(this.driver1, extentTest);
//        actionClass.clickOnObject(this.ClickOnFrontOffice);
        Thread.sleep(5000);
        actionClass.clickOnObject(this.ClickOnAdmissionEnquiry);
        Thread.sleep(2000);
        actionClass.clickOnObject(this.SelectSource);
        actionClass.clickOnObject(this.SelectASource);
        actionClass.clickOnObject(this.ClickOnSearch);
        Thread.sleep(2000);
        actionClass.captureScreen("List of AdmissionEnquiry");
        JavascriptExecutor jsetaskscore = (JavascriptExecutor) driver1;
        jsetaskscore.executeScript("scrollBy(0, 500)");
        actionClass.captureScreen("List of AdmissionEnquiry");

        List<WebElement> ListSourceAdmissionEnquiry = driver1.findElements(By.xpath("//*[@id='enquirytable']/tbody/tr"));
        int listsize = ListSourceAdmissionEnquiry.size();
        ArrayList<String> listNames1 = new ArrayList<String>();
        for (int i = 1; i <= listsize; i++) {
            String s = driver1.findElement(By.xpath("//*[@id='enquirytable']/tbody/tr[" + i + "]/td[1]")).getText();
            System.out.println("Value in list is: " + s);
            listNames1.add(driver1.findElement(By.xpath("//*[@id='enquirytable']/tbody/tr[" + i + "]/td[1]")).getText());
        }
        ArrayList<String> SourceadmissionEnquiryList= new ArrayList<String>(listNames1.size());
        for(String myInt : listNames1 ){
            SourceadmissionEnquiryList.add(String.valueOf(myInt));
        }
        System.out.println(SourceadmissionEnquiryList);

        DatabaseFunctions DAB = new DatabaseFunctions(extentTest);
        conn = DAB.connect();
        statement = conn.createStatement();
        String students = "SELECT * FROM `enquiry` WHERE source='"+source+"'  ORDER BY `enquiry`.`name` ASC";
        queryRs = statement.executeQuery(students);
        ArrayList<String> listNames = new ArrayList<String>();
        while (queryRs.next()) {
            String s1 = null;
            s1 = queryRs.getString("name");
            System.out.println("Value in list database: " + s1);
            listNames.add(queryRs.getString("name"));
        }
        ArrayList<String> newIntegerList= new ArrayList<String>(listNames.size());
        for(String myInt : listNames ){
            newIntegerList.add(String.valueOf(myInt));
        }
        // Collections.sort(newIntegerList);
        System.out.println(newIntegerList);
        System.out.println(SourceadmissionEnquiryList.equals(newIntegerList));
        actionClass.CompareStringList(newIntegerList,SourceadmissionEnquiryList);
        return listNames1.toArray();
    }

    //Match selected source and selected status admission enquiry with database
    public Object[] ListSourceStatusAdmissionEnquiry() throws SQLException, IOException, InterruptedException {
//        Filter using filters
        ActionClass actionClass = new ActionClass(this.driver1, extentTest);
        actionClass.clickOnObject(this.ClickOnFrontOffice);
        Thread.sleep(5000);
        actionClass.clickOnObject(this.ClickOnAdmissionEnquiry);
        Thread.sleep(2000);
        actionClass.clickOnObject(this.SelectSource);
        actionClass.clickOnObject(this.SelectASource);
        actionClass.clickOnObject(this.SelectStatus);
        actionClass.clickOnObject(this.SelectPassiveStatus);
        actionClass.clickOnObject(this.ClickOnSearch);
        Thread.sleep(2000);
        actionClass.captureScreen("List of AdmissionEnquiry");
        JavascriptExecutor jsetaskscore = (JavascriptExecutor) driver1;
        jsetaskscore.executeScript("scrollBy(0, 500)");
        actionClass.captureScreen("List of AdmissionEnquiry");
        List<WebElement> ListSourceStatusAdmissionEnquiry = driver1.findElements(By.xpath("//*[@id='enquirytable']/tbody/tr"));

        int listsize = ListSourceStatusAdmissionEnquiry.size();
        ArrayList<String> listNames1 = new ArrayList<String>();
        for (int i = 1; i <= listsize; i++) {
            String s = driver1.findElement(By.xpath("//*[@id='enquirytable']/tbody/tr[" + i + "]/td[1]")).getText();
            System.out.println("Value in list is: " + s);
            listNames1.add(driver1.findElement(By.xpath("//*[@id='enquirytable']/tbody/tr[" + i + "]/td[1]")).getText());
        }
        ArrayList<String> SourceStatusadmissionEnquiryList= new ArrayList<String>(listNames1.size());
        for(String myInt : listNames1 ){
            SourceStatusadmissionEnquiryList.add(String.valueOf(myInt));
        }
        System.out.println(SourceStatusadmissionEnquiryList);

        DatabaseFunctions DAB = new DatabaseFunctions(extentTest);
        conn = DAB.connect();
        statement = conn.createStatement();
        String students = "SELECT * FROM `enquiry` WHERE source='"+source+"'  AND status='"+status1+"' ORDER BY `enquiry`.`name` ASC";
        queryRs = statement.executeQuery(students);
        ArrayList<String> listNames = new ArrayList<String>();
        while (queryRs.next()) {
            String s1 = null;
            s1 = queryRs.getString("name");
            System.out.println("Value in list database: " + s1);
            listNames.add(queryRs.getString("name"));
        }
        ArrayList<String> newIntegerList= new ArrayList<String>(listNames.size());
        for(String myInt : listNames ){
            newIntegerList.add(String.valueOf(myInt));
        }
        // Collections.sort(newIntegerList);
        System.out.println(newIntegerList);
        System.out.println(SourceStatusadmissionEnquiryList.equals(newIntegerList));
        actionClass.CompareStringList(newIntegerList,SourceStatusadmissionEnquiryList);
        return listNames1.toArray();
    }
    //Match enquiry date admission enquiry list with database
    public Object[] ListEnquiryAdmissionEnquiry() throws SQLException, IOException, InterruptedException {
//        Filter using filters
        ActionClass actionClass = new ActionClass(this.driver1, extentTest);
//        actionClass.clickOnObject(this.ClickOnFrontOffice);
        Thread.sleep(5000);
        actionClass.clickOnObject(this.ClickOnAdmissionEnquiry);
        Thread.sleep(2000);
        actionClass.clickOnObject(this.ClickInEnquiryDateField);
        actionClass.clickOnObject(this.SelectFromDate);
        actionClass.clickOnObject(this.SelectToDate);
        actionClass.clickOnObject(this.ClickOnApply);
        actionClass.clickOnObject(this.ClickOnSearch);
        Thread.sleep(2000);
        actionClass.captureScreen("List of AdmissionEnquiry");
        JavascriptExecutor jsetaskscore = (JavascriptExecutor) driver1;
        jsetaskscore.executeScript("scrollBy(0, 500)");
        actionClass.captureScreen("List of AdmissionEnquiry");
        List<WebElement> ListEnquiryAdmissionEnquiry = driver1.findElements(By.xpath("//*[@id='enquirytable']/tbody/tr"));
        int listsize = ListEnquiryAdmissionEnquiry.size();
        ArrayList<String> listNames1 = new ArrayList<String>();
        for (int i = 1; i <= listsize; i++) {
            String s = driver1.findElement(By.xpath("//*[@id='enquirytable']/tbody/tr[" + i + "]/td[1]")).getText();
            System.out.println("Value in list is: " + s);
            listNames1.add(driver1.findElement(By.xpath("//*[@id='enquirytable']/tbody/tr[" + i + "]/td[1]")).getText());
        }
        ArrayList<String> EnquiryadmissionEnquiryList= new ArrayList<String>(listNames1.size());
        for(String myInt : listNames1 ){
            EnquiryadmissionEnquiryList.add(String.valueOf(myInt));
        }
        System.out.println(EnquiryadmissionEnquiryList);

        DatabaseFunctions DAB = new DatabaseFunctions(extentTest);
        conn = DAB.connect();
        statement = conn.createStatement();
        String students = "SELECT * FROM `enquiry` WHERE date='"+ date +"' ORDER BY `name`  ASC";
        queryRs = statement.executeQuery(students);
        ArrayList<String> listNames = new ArrayList<String>();

        while (queryRs.next()) {
            String s1 = null;
            s1 = queryRs.getString("name");
            System.out.println("Value in list database: " + s1);
            listNames.add(queryRs.getString("name"));
        }
        ArrayList<String> newIntegerList= new ArrayList<String>(listNames.size());
        for(String myInt : listNames ){
            newIntegerList.add(String.valueOf(myInt));
        }
        // Collections.sort(newIntegerList);
        System.out.println(newIntegerList);
        System.out.println(EnquiryadmissionEnquiryList.equals(newIntegerList));
        actionClass.CompareStringList(newIntegerList,EnquiryadmissionEnquiryList);
        return listNames1.toArray();
    }
    //Match enquiry date, source and status admission enquiry list with database
    public Object[] ListEnquirySourceStatusAdmissionEnquiry() throws SQLException, IOException, InterruptedException {
//        Filter using filters
        ActionClass actionClass = new ActionClass(this.driver1, extentTest);
//        actionClass.clickOnObject(this.ClickOnFrontOffice);
        actionClass.clickOnObject(this.ClickOnAdmissionEnquiry);
        Thread.sleep(2000);
        actionClass.clickOnObject(this.ClickInEnquiryDateField);
        actionClass.clickOnObject(this.SelectFromDate);
        actionClass.clickOnObject(this.SelectToDate);
        actionClass.clickOnObject(this.ClickOnApply);
        actionClass.clickOnObject(this.SelectSource);
        actionClass.clickOnObject(this.SelectASource);
        actionClass.clickOnObject(this.SelectStatus);
        actionClass.clickOnObject(this.SelectAllStatus);
        actionClass.clickOnObject(this.ClickOnSearch);
        Thread.sleep(2000);
        actionClass.captureScreen("List of AdmissionEnquiry");
        JavascriptExecutor jsetaskscore = (JavascriptExecutor) driver1;
        jsetaskscore.executeScript("scrollBy(0, 500)");
        actionClass.captureScreen("List of AdmissionEnquiry");
        List<WebElement> ListEnquiryAdmissionEnquiry = driver1.findElements(By.xpath("//*[@id='enquirytable']/tbody/tr"));
        int listsize = ListEnquiryAdmissionEnquiry.size();
        ArrayList<String> listNames1 = new ArrayList<String>();
        for (int i = 1; i <= listsize; i++) {
            String s = driver1.findElement(By.xpath("//*[@id='enquirytable']/tbody/tr[" + i + "]/td[1]")).getText();
            System.out.println("Value in list is: " + s);
            listNames1.add(driver1.findElement(By.xpath("//*[@id='enquirytable']/tbody/tr[" + i + "]/td[1]")).getText());
        }
        ArrayList<String> EnquiryadmissionEnquiryList= new ArrayList<String>(listNames1.size());
        for(String myInt : listNames1 ){
            EnquiryadmissionEnquiryList.add(String.valueOf(myInt));
        }
        System.out.println(EnquiryadmissionEnquiryList);

        DatabaseFunctions DAB = new DatabaseFunctions(extentTest);
        conn = DAB.connect();
        statement = conn.createStatement();
        String students = "SELECT * FROM `enquiry` WHERE date='"+date+"' AND source='"+source+"' ORDER BY `name`  ASC";
        queryRs = statement.executeQuery(students);
        ArrayList<String> listNames = new ArrayList<String>();

        while (queryRs.next()) {
            String s1 = null;
            s1 = queryRs.getString("name");
            System.out.println("Value in list database: " + s1);
            listNames.add(queryRs.getString("name"));
        }
        ArrayList<String> newIntegerList= new ArrayList<String>(listNames.size());
        for(String myInt : listNames ){
            newIntegerList.add(String.valueOf(myInt));
        }
        // Collections.sort(newIntegerList);
        System.out.println(newIntegerList);
        System.out.println(EnquiryadmissionEnquiryList.equals(newIntegerList));
        actionClass.CompareStringList(newIntegerList,EnquiryadmissionEnquiryList);

        return listNames1.toArray();
    }
    // Match the no data behavior with database
    public void NoDataBehavior() throws SQLException, InterruptedException, IOException {
//        Filter using filters
        ActionClass actionClass = new ActionClass(this.driver1, extentTest);
//        actionClass.clickOnObject(this.ClickOnFrontOffice);
        Thread.sleep(5000);
        actionClass.clickOnObject(this.ClickOnAdmissionEnquiry);
        Thread.sleep(2000);
        actionClass.clickOnObject(this.ClickInEnquiryDateField);
        actionClass.clickOnObject(this.SelectFromDate);
        actionClass.clickOnObject(this.SelectToDate);
        actionClass.clickOnObject(this.ClickOnApply);
        actionClass.clickOnObject(this.SelectSource);
        actionClass.clickOnObject(this.SelectASource2);
        actionClass.clickOnObject(this.SelectStatus);
        actionClass.clickOnObject(this.SelectPassiveStatus);
        actionClass.clickOnObject(this.ClickOnSearch);
        Thread.sleep(2000);
        actionClass.captureScreen("List of AdmissionEnquiry");
        JavascriptExecutor jsetaskscore = (JavascriptExecutor) driver1;
        jsetaskscore.executeScript("scrollBy(0, 500)");
        actionClass.captureScreen("List of AdmissionEnquiry");
        String s = driver1.findElement(By.xpath("//*[@id=\"enquirytable\"]/tbody/tr/td")).getText();
//        System.out.println(s);

        DatabaseFunctions DAB = new DatabaseFunctions(extentTest);
        conn = DAB.connect();
        statement = conn.createStatement();
        String students = "SELECT * FROM `enquiry` WHERE date='"+date+"' AND source='"+source1+"' AND status='"+status1+"' ORDER BY `name`  ASC";
        queryRs = statement.executeQuery(students);

        if(s.contains("No data available in table") && queryRs.next()==false){
            System.out.println("No data behaviour");
            extentTest.log(Status.PASS, "No data available in table");
        }
        else{
            System.out.println("Result is not matched with DB");
            extentTest.log(Status.PASS, "Result is not matched with DB");
        }
    }
    //    Test name field validation on adding Admission Enquiry
    public void NameFieldRequired() throws SQLException, InterruptedException, IOException {
        ActionClass actionClass = new ActionClass(this.driver1, extentTest);
//        actionClass.clickOnObject(this.ClickOnFrontOffice);
        Thread.sleep(5000);
        actionClass.clickOnObject(this.ClickOnAdmissionEnquiry);
        Thread.sleep(2000);
        actionClass.clickOnObject(this.ClickAdd);
        Thread.sleep(2000);
        actionClass.setValueinTextbox(this.PhoneField, "7894561230");
        actionClass.setValueinTextbox(EmailField, "johnnyharpertesting@gmail.com");
        actionClass.setValueinTextbox(DescriptionField, "Lorem ipsum");
        actionClass.setValueinTextbox(NoteField, "Lorem Ipsum");
        actionClass.clickOnObject(SourceField);
        actionClass.clickOnObject(AddAdmissionEnquirySourceField);
        actionClass.clickOnObject(this.SaveButton);
        Thread.sleep(2000);
        actionClass.captureScreen("Name field validation");
        VerificationClass verificationClass= new VerificationClass(this.driver1, extentTest);
        verificationClass.verifyTextPresent(this.NameFieldRequiredValidation, "The Name field is required.");
        actionClass.clickOnObject(CloseButton);
    }

    //    Test if phone field validation on adding Admission Enquiry matches with expected value
    public void PhoneFieldRequired() throws SQLException, InterruptedException, IOException {
        ActionClass actionClass = new ActionClass(this.driver1, extentTest);
//        Thread.sleep(5000);
//        actionClass.clickOnObject(this.ClickOnFrontOffice);
        Thread.sleep(5000);
        actionClass.clickOnObject(this.ClickOnAdmissionEnquiry);
        Thread.sleep(5000);
        actionClass.clickOnObject(this.ClickAdd);
        Thread.sleep(5000);
        actionClass.setValueinTextbox(this.NameField, "Johnny Harper");
        actionClass.setValueinTextbox(EmailField, "johnnyharpertesting@gmail.com");
        actionClass.setValueinTextbox(DescriptionField, "Lorem ipsum");
        actionClass.setValueinTextbox(NoteField, "Lorem Ipsum");
        actionClass.clickOnObject(SourceField);
        actionClass.clickOnObject(AddAdmissionEnquirySourceField);
        actionClass.clickOnObject(this.SaveButton);
        Thread.sleep(2000);
        actionClass.captureScreen("Phone field validation");
        VerificationClass verificationClass= new VerificationClass(this.driver1, extentTest);
        verificationClass.verifyTextPresent(this.PhoneFieldRequiredValidation, "The Contact field is required.");
        actionClass.clickOnObject(CloseButton);
    }

    //    Test if source field validation on adding Admission Enquiry matches with expected value
    public void SourceFieldRequired() throws SQLException, InterruptedException, IOException {
        ActionClass actionClass = new ActionClass(this.driver1, extentTest);
//        actionClass.clickOnObject(this.ClickOnFrontOffice);
        Thread.sleep(5000);
        actionClass.clickOnObject(this.ClickOnAdmissionEnquiry);
        Thread.sleep(2000);
        JavascriptExecutor jsetaskscore = (JavascriptExecutor) driver1;
        jsetaskscore.executeScript("scrollBy(0, 200)");
        actionClass.clickOnObject(this.ClickAdd);
        Thread.sleep(2000);
        actionClass.setValueinTextbox(this.NameField, "Johnny Harper");
        actionClass.setValueinTextbox(this.PhoneField, "7894561230");
        actionClass.setValueinTextbox(EmailField, "johnnyharpertesting@gmail.com");
        actionClass.setValueinTextbox(DescriptionField, "Lorem ipsum");
        actionClass.setValueinTextbox(NoteField, "Lorem Ipsum");
        Thread.sleep(2000);
        actionClass.clickOnObject(this.SaveButton);
        Thread.sleep(2000);
        actionClass.captureScreen("Source field validation");
        VerificationClass verificationClass1= new VerificationClass(this.driver1, extentTest);
        verificationClass1.verifyTextPresent(this.SourceFieldRequiredValidation, "The Source field is required.");
        actionClass.clickOnObject(CloseButton);
    }

    //    Test if new  Admission Enquiry is added and compare to database
    public Object[] AddAdmissionEnquiry() throws SQLException, InterruptedException, IOException {
        ActionClass actionClass = new ActionClass(this.driver1, extentTest);
//        Thread.sleep(5000);
//        actionClass.clickOnObject(this.ClickOnFrontOffice);
        Thread.sleep(5000);
        actionClass.clickOnObject(this.ClickOnAdmissionEnquiry);
        Thread.sleep(5000);
        JavascriptExecutor jsetaskscore = (JavascriptExecutor) driver1;
        jsetaskscore.executeScript("scrollBy(0, 200)");
        actionClass.clickOnObject(this.ClickAdd);
        Thread.sleep(5000);
        actionClass.setValueinTextbox(this.NameField, "Johnny Harper");
        actionClass.setValueinTextbox(this.PhoneField, "7894561230");
        actionClass.setValueinTextbox(EmailField, "johnnyharpertesting@gmail.com");
        actionClass.setValueinTextbox(AddressField, "Lorem ipsum");
        actionClass.setValueinTextbox(DescriptionField, "Lorem ipsum");
        actionClass.setValueinTextbox(NoteField, "Lorem Ipsum");
        actionClass.setValueinTextbox(AssignedField, "Lorem Ipsum");
        actionClass.clickOnObject(ReferenceField);
        actionClass.clickOnObject(SelectReferenceField);
        actionClass.clickOnObject(SourceField);
        actionClass.clickOnObject(AddAdmissionEnquirySourceField);
        actionClass.clickOnObject(ClassField);
        actionClass.clickOnObject(SelectClassField);
        actionClass.setValueinTextbox(NumberOfChildField, "2");
        Thread.sleep(2000);
        actionClass.clickOnObject(this.SaveButton);
        Thread.sleep(3000);
        //actionClass.captureScreen("Admission Enquiry Success");
        //actionClass.clickOnObject(ClickOnAdmissionEnquiry);
        JavascriptExecutor jsetaskscore1 = (JavascriptExecutor) driver1;
        jsetaskscore1.executeScript("scrollBy(0, -400)");
        Thread.sleep(3000);
        actionClass.captureScreen("Admission Enquiry Success");
        VerificationClass verificationClass1= new VerificationClass(this.driver1, extentTest);
        verificationClass1.verifyTextPresent(this.AdmissionEnquirySuccessMessage, "Enquiry Saved successfully");
        List<WebElement> ListofAllAdmissionEnquiry = driver1.findElements(By.xpath("//*[@id=\"enquirytable\"]/tbody/tr"));
        int listsize = ListofAllAdmissionEnquiry.size();
        ArrayList<String> listNames1 = new ArrayList<String>();
        for (int i = 1; i <= listsize; i++) {
            String s = driver1.findElement(By.xpath("//*[@id=\"enquirytable\"]/tbody/tr[" + i + "]/td[1]")).getText();
            System.out.println("Value in list is: " + s);
            listNames1.add(driver1.findElement(By.xpath("//*[@id=\"enquirytable\"]/tbody/tr[" + i + "]/td[1]")).getText());
        }
        ArrayList<String> AlladmissionEnquiryList= new ArrayList<String>(listNames1.size());
        for(String myInt : listNames1 ){
            AlladmissionEnquiryList.add(String.valueOf(myInt));
        }
        System.out.println(AlladmissionEnquiryList);
        DatabaseFunctions DAB = new DatabaseFunctions(extentTest);
        conn = DAB.connect();
        statement = conn.createStatement();
        String students = "SELECT * FROM `enquiry` WHERE status='active' ORDER BY `name`  DESC";
        queryRs = statement.executeQuery(students);
        ArrayList<String> listNames = new ArrayList<String>();
        while (queryRs.next()) {
            String s1 = null;
            s1 = queryRs.getString("name");
            System.out.println("Value in database list: " + s1);
            listNames.add(queryRs.getString("name"));
        }
        ArrayList<String> newIntegerList= new ArrayList<String>(listNames.size());
        for(String myInt : listNames ){
            newIntegerList.add(String.valueOf(myInt));
        }
        Collections.sort(newIntegerList);
        System.out.println(newIntegerList);
        System.out.println(AlladmissionEnquiryList.equals(newIntegerList));
        actionClass.CompareStringList(newIntegerList,AlladmissionEnquiryList);
        return listNames1.toArray();
    }
    //    Test if new  Admission Enquiry is edited and compare to database
    public Object[] EditAdmissionEnquiry() throws SQLException, InterruptedException, IOException {
        ActionClass actionClass = new ActionClass(driver1, extentTest);
//        Thread.sleep(5000);
//        actionClass.clickOnObject(this.ClickOnFrontOffice);
        Thread.sleep(5000);
        actionClass.clickOnObject(this.ClickOnAdmissionEnquiry);
        Thread.sleep(2000);
        actionClass.clickOnObject(this.ClickAdd);
        Thread.sleep(2000);
        actionClass.setValueinTextbox(this.NameField, ActualName);
        actionClass.setValueinTextbox(this.PhoneField, "7894561237");
        actionClass.setValueinTextbox(EmailField, "johnnyl@gmail.com");
        actionClass.setValueinTextbox(AddressField, "Lorem ipsum");
        actionClass.setValueinTextbox(DescriptionField, "Lorem ipsum");
        actionClass.setValueinTextbox(NoteField, "Lorem Ipsum");
        actionClass.setValueinTextbox(AssignedField, "Lorem Ipsum");
        actionClass.clickOnObject(ReferenceField);
        actionClass.clickOnObject(SelectReferenceField);
        actionClass.clickOnObject(SourceField);
        actionClass.clickOnObject(AddAdmissionEnquirySourceField);
        actionClass.clickOnObject(ClassField);
        actionClass.clickOnObject(SelectClassField);
        actionClass.setValueinTextbox(NumberOfChildField, "2");
        Thread.sleep(2000);
        actionClass.clickOnObject(this.SaveButton);
        Thread.sleep(3000);
        VerificationClass verificationClass1= new VerificationClass(this.driver1, extentTest);
        verificationClass1.verifyTextPresent(this.AdmissionEnquirySuccessMessage, "Enquiry Saved successfully");
        actionClass.clickOnObject(ClickOnAdmissionEnquiry);

        DatabaseFunctions DAB = new DatabaseFunctions(extentTest);
        conn = DAB.connect();
        statement = conn.createStatement();
        String students = "SELECT id, name FROM `enquiry` WHERE name='"+ActualName+"' ORDER BY `name`  DESC";
        queryRs = statement.executeQuery(students);
        ArrayList<String> listNames = new ArrayList<String>();
        String s1 = null;
        String s2 = null;
        while (queryRs.next()) {
            s1 = queryRs.getString("name");
            s2 = queryRs.getString("id");
            System.out.println("Value in database list: " + s1);
            listNames.add(queryRs.getString("name"));
        }
//Edit
        System.out.println("\n");
        System.out.println("Now edit the name of the added record");
        JavascriptExecutor jsetaskscore = (JavascriptExecutor) driver1;
        jsetaskscore.executeScript("scrollBy(0, 400)");
        actionClass.setValueinTextbox(SmallSearch, "Johnny Shah");
        Thread.sleep(2000);
        actionClass.clickOnObject(ClickOnEdit);
        Thread.sleep(2000);
        actionClass.setValueinTextbox(EditNameField, UpdatedName);
        actionClass.clickOnObject(EditSaveButton);
        Thread.sleep(2000);
        VerificationClass verificationClass2= new VerificationClass(this.driver1, extentTest);
        verificationClass2.verifyTextPresent(this.AdmissionEnquirySuccessMessage, "Admission Enquiry edited successfully.");
        actionClass.captureScreen("Admission Enquiry Edit Success");
        Thread.sleep(2000);

        DatabaseFunctions DAB1 = new DatabaseFunctions(extentTest);
        conn = DAB1.connect();
        statement = conn.createStatement();
        String students1 = "SELECT id, name FROM `enquiry` WHERE name='"+UpdatedName+"' AND id='" + s2 +"' ORDER BY `name`  DESC";
        queryRs = statement.executeQuery(students1);
        ArrayList<String> listNames1 = new ArrayList<String>();
        while (queryRs.next()) {
            String s12 = null;
            s12 = queryRs.getString("name");
            System.out.println("Value in database list: " + s12);
            listNames1.add(queryRs.getString("name"));
        }
        actionClass.CompareEditedString(listNames,listNames1);
        return listNames1.toArray();
    }
    //    Test if record is deleted and is not present in database
    public Object[] DeleteAdmissionEnquiry() throws InterruptedException, IOException, SQLException {
        ActionClass actionClass = new ActionClass(this.driver1, extentTest);
//        Thread.sleep(5000);
//        actionClass.clickOnObject(this.ClickOnFrontOffice);
        Thread.sleep(5000);
        actionClass.clickOnObject(this.ClickOnAdmissionEnquiry);
        Thread.sleep(2000);
        actionClass.clickOnObject(this.ClickAdd);
        Thread.sleep(2000);
        actionClass.setValueinTextbox(this.NameField, "Aanal Shah");
        actionClass.setValueinTextbox(this.PhoneField, "7894561237");
        actionClass.setValueinTextbox(EmailField, "aanal@gmail.com");
        actionClass.setValueinTextbox(AddressField, "Lorem ipsum");
        actionClass.setValueinTextbox(DescriptionField, "Lorem ipsum");
        actionClass.setValueinTextbox(NoteField, "Lorem Ipsum");
        actionClass.setValueinTextbox(AssignedField, "Lorem Ipsum");
        actionClass.clickOnObject(ReferenceField);
        actionClass.clickOnObject(SelectReferenceField);
        actionClass.clickOnObject(SourceField);
        actionClass.clickOnObject(AddAdmissionEnquirySourceField);
        actionClass.clickOnObject(ClassField);
        actionClass.clickOnObject(SelectClassField);
        actionClass.setValueinTextbox(NumberOfChildField, "2");
        Thread.sleep(2000);
        actionClass.clickOnObject(this.SaveButton);
        Thread.sleep(3000);
        VerificationClass verificationClass1= new VerificationClass(this.driver1, extentTest);
        verificationClass1.verifyTextPresent(this.AdmissionEnquirySuccessMessage, "Enquiry Saved successfully");
        actionClass.clickOnObject(ClickOnAdmissionEnquiry);

        List<WebElement> ListofAllAdmissionEnquiry = driver1.findElements(By.xpath("//*[@id=\"enquirytable\"]/tbody/tr"));
        int listsize = ListofAllAdmissionEnquiry.size();
        ArrayList<String> listNames1 = new ArrayList<String>();
        for (int i = 1; i <= listsize; i++) {
            String s = driver1.findElement(By.xpath("//*[@id=\"enquirytable\"]/tbody/tr[" + i + "]/td[1]")).getText();
            System.out.println("Value in list is: " + s);
            listNames1.add(driver1.findElement(By.xpath("//*[@id=\"enquirytable\"]/tbody/tr[" + i + "]/td[1]")).getText());
        }
        ArrayList<String> AlladmissionEnquiryList= new ArrayList<String>(listNames1.size());
        for(String myInt : listNames1 ){
            AlladmissionEnquiryList.add(String.valueOf(myInt));
        }
        System.out.println(AlladmissionEnquiryList);

        DatabaseFunctions DAB = new DatabaseFunctions(extentTest);
        conn = DAB.connect();
        statement = conn.createStatement();
        String students = "SELECT * FROM `enquiry` WHERE status='active' ORDER BY `name`  DESC";
        queryRs = statement.executeQuery(students);
        ArrayList<String> listNames = new ArrayList<String>();
        while (queryRs.next()) {
            String s1 = null;
            s1 = queryRs.getString("name");
            System.out.println("Value in database list: " + s1);
            listNames.add(queryRs.getString("name"));
        }
        ArrayList<String> newIntegerList= new ArrayList<String>(listNames.size());
        for(String myInt : listNames ){
            newIntegerList.add(String.valueOf(myInt));
        }
        Collections.sort(newIntegerList);
        System.out.println(newIntegerList);
        System.out.println(AlladmissionEnquiryList.equals(newIntegerList));
        //actionClass.CompareStringList(newIntegerList,AlladmissionEnquiryList);
        Thread.sleep(3000);
        System.out.println("Now delete the added record");
        actionClass.setValueinTextbox(SmallSearch, DeleteName);
        JavascriptExecutor jsetaskscore = (JavascriptExecutor) driver1;
        jsetaskscore.executeScript("scrollBy(0, 400)");
        Thread.sleep(2000);
        actionClass.clickOnObject(ClickOnDelete);
        // Switching to Alert
        Alert alert = driver1.switchTo().alert();
        // Capturing alert message.
        String alertMessage= driver1.switchTo().alert().getText();
        // Displaying alert message
        System.out.println(alertMessage);
        Thread.sleep(5000);
        // Accepting alert
        alert.accept();

        Thread.sleep(2000);
        actionClass.captureScreen("Admission Enquiry Success");
        VerificationClass verificationClass2= new VerificationClass(this.driver1, extentTest);
        verificationClass2.verifyTextPresent(this.DeleteAdmissionEnquirySuccessMessage, "Enquiry deleted successfully");
        Thread.sleep(2000);
        JavascriptExecutor jsetaskscore1 = (JavascriptExecutor) driver1;
        jsetaskscore1.executeScript("scrollBy(0, 400)");
        actionClass.setValueinTextbox(SmallSearch, DeleteName);
        Thread.sleep(2000);
        Thread.sleep(2000);
        String s = driver1.findElement(By.xpath("//*[@id=\"enquirytable\"]/tbody/tr/td")).getText();
        System.out.println(s);

        DatabaseFunctions DAB1 = new DatabaseFunctions(extentTest);
        conn = DAB1.connect();
        statement = conn.createStatement();
        String students1 = "SELECT * FROM `enquiry` WHERE name='"+ DeleteName +"' ORDER BY `name`  ASC";
        queryRs = statement.executeQuery(students);
        while (queryRs.next()){
            String s2= queryRs.getString("name");
            System.out.println("Value in database: " +s2);
        }
        if(s.contains("No matching records found") && queryRs.next()==false){
            System.out.println("Record deleted and it is not in database");
            extentTest.log(Status.PASS,"Record deleted and it is not in database");
        }
        else{
            System.out.println("Record might not deleted and it may be in database");
            extentTest.log(Status.FAIL, "Record might not be deleted and it may be in database");
        }
        return listNames1.toArray();
    }
    //    Test if follow up enquiry date is updated and matches with database and front end
    public void EditFollowUpDateAdmissionEnquiry() throws InterruptedException, SQLException, IOException {
        ActionClass actionClass = new ActionClass(driver1, extentTest);
        //actionClass.clickOnObject(this.ClickOnFrontOffice);
        Thread.sleep(5000);
        actionClass.clickOnObject(this.ClickOnAdmissionEnquiry);
        Thread.sleep(2000);
        actionClass.clickOnObject(this.ClickAdd);
        Thread.sleep(2000);
        actionClass.setValueinTextbox(this.NameField, FollowUpDateName);
        actionClass.setValueinTextbox(this.PhoneField, "7894561236");
        actionClass.setValueinTextbox(EmailField, "riddhi@gmail.com");
        actionClass.setValueinTextbox(AddressField, "Lorem ipsum");
        actionClass.setValueinTextbox(DescriptionField, "Lorem ipsum");
        actionClass.setValueinTextbox(NoteField, "Lorem Ipsum");
        actionClass.setValueinTextbox(AssignedField, "Lorem Ipsum");
        actionClass.clickOnObject(ReferenceField);
        actionClass.clickOnObject(SelectReferenceField);
        actionClass.clickOnObject(SourceField);
        actionClass.clickOnObject(AddAdmissionEnquirySourceField);
        actionClass.clickOnObject(ClassField);
        actionClass.clickOnObject(SelectClassField);
        actionClass.setValueinTextbox(NumberOfChildField, "2");
        Thread.sleep(2000);
        actionClass.clickOnObject(this.SaveButton);
        Thread.sleep(3000);
        VerificationClass verificationClass1= new VerificationClass(this.driver1, extentTest);
        verificationClass1.verifyTextPresent(this.AdmissionEnquirySuccessMessage, "Enquiry Saved successfully");
        actionClass.clickOnObject(ClickOnAdmissionEnquiry);

        DatabaseFunctions DAB = new DatabaseFunctions(extentTest);
        conn = DAB.connect();
        statement = conn.createStatement();
        String students = "SELECT id, name, date, follow_up_date FROM `enquiry` WHERE name='"+FollowUpDateName+"' ORDER BY `name`  DESC";
        queryRs = statement.executeQuery(students);
        ArrayList<String> listNames = new ArrayList<String>();
        String name = null;
        String id = null;
        Date date = null;
        Date follow_up_date= null;
        while (queryRs.next()) {
            name = queryRs.getString("name");
            id = queryRs.getString("id");
            date=queryRs.getDate("date");
            follow_up_date=queryRs.getDate("follow_up_date");
            System.out.println("Id of added student: " + id);
            System.out.println("Name of added student: " + name);
            System.out.println("Enquiry date of added student: " + date);
            System.out.println("Follow up date of added student: " + follow_up_date);
        }
        //Edit
        System.out.println("\n");
        System.out.println("Now edit the follow up date of the added record");
        JavascriptExecutor jsetaskscore = (JavascriptExecutor) driver1;
        jsetaskscore.executeScript("scrollBy(0, 400)");
        actionClass.setValueinTextbox(SmallSearch, FollowUpDateName);
        Thread.sleep(2000);
        actionClass.clickOnObject(ClickOnEdit);
        Thread.sleep(2000);
        actionClass.clickOnObject(NextFollowUpDateEditField);
        Thread.sleep(2000);
        actionClass.clickOnObject(NextFollowUpSelectDateEditField);
        Thread.sleep(2000);
        actionClass.clickOnObject(EditSaveButton);
        Thread.sleep(2000);
        VerificationClass verificationClass2= new VerificationClass(this.driver1, extentTest);
        verificationClass2.verifyTextPresent(this.AdmissionEnquirySuccessMessage, "Admission Enquiry edited successfully.");
        actionClass.captureScreen("Admission Enquiry Follow Up Date Edit Success");
        Thread.sleep(2000);

        DatabaseFunctions DAB1 = new DatabaseFunctions(extentTest);
        conn = DAB1.connect();
        statement = conn.createStatement();
        String students1 = "SELECT id, name, date, follow_up_date FROM `enquiry` WHERE name='"+FollowUpDateName+"' AND id='"+id+"'ORDER BY `name`  DESC";
        queryRs = statement.executeQuery(students1);
        ArrayList<String> listNames1 = new ArrayList<String>();
        String name1 = null;
        String id1 = null;
        Date date1 = null;
        Date follow_up_date1=null;
        while (queryRs.next()) {
            name1 = queryRs.getString("name");
            id1 = queryRs.getString("id");
            date1=queryRs.getDate("date");
            follow_up_date1=queryRs.getDate("follow_up_date");
            System.out.println("Id of edited student: " + id1);
            System.out.println("Name of edited student: " + name1);
            System.out.println("Enquiry date of edited student: " + date1);
            System.out.println("Follow up date of edited student: " + follow_up_date1);
        }
        if(!(follow_up_date.equals(follow_up_date1))){
            System.out.println("Follow up enquiry date is updated and before and after database values does not match");
            extentTest.log(Status.PASS, "Follow up enquiry date is updated and before and after database values does not match");
        }
        else{
            System.out.println("Follow up enquiry date is not updated and before and after database values matches");
            extentTest.log(Status.FAIL, "Follow up enquiry date is not updated and before and after database values matches");
        }
        DateFormat formatter = new SimpleDateFormat("dd.MM.yyyy");
        String strDate= formatter.format(follow_up_date1);
//        System.out.println(strDate);
        JavascriptExecutor jsetaskscore1 = (JavascriptExecutor) driver1;
        jsetaskscore1.executeScript("scrollBy(0, 400)");
        actionClass.setValueinTextbox(SmallSearch, FollowUpDateName);
        Thread.sleep(2000);
        actionClass.clickOnObject(ClickOnPhoneIcon);
        Thread.sleep(2000);
        actionClass.captureScreen("Next Follow Up Date displayed");
        String phonefollowupdate = driver1.findElement(By.xpath("/html/body/div[1]/div[1]/div[3]/div/div/div[2]/div/div[2]/div/div[3]/h5")).getText();
        System.out.println(phonefollowupdate);
        if(phonefollowupdate.contains(strDate)){
            System.out.println("The follow up date is updated over Follow Up Enquiry Popup.");
            extentTest.log(Status.PASS, "The follow up date is updated over Follow Up Enquiry Popup.");
        }
        else {
            System.out.println("The follow up date is not updated over Follow Up Enquiry Popup.");
            extentTest.log(Status.FAIL, "The follow up date is not updated over Follow Up Enquiry Popup.");
        }
//        Date getFollowUpDate = (Date) driver1.findElement(By.xpath("//input[@id='follow_date']"));
        actionClass.clickOnObject(NextFollowUpDateInPopup);
        Thread.sleep(1000);
        actionClass.clickOnObject(NextFollowUpSelectDateEditField);
        Thread.sleep(1000);
        actionClass.setValueinTextbox(Response, FollowUpResponseMessage);
        Thread.sleep(1000);
        actionClass.clickOnObject(FollowUpSaveButton);
        Thread.sleep(1000);
        actionClass.captureScreen("Next Follow Up Date displayed");
        VerificationClass verificationClass=new VerificationClass(driver1, extentTest);
        verificationClass.verifyTextPresent(FollowUpResponse, FollowUpResponseMessage);
        DatabaseFunctions DAB2 = new DatabaseFunctions(extentTest);
        conn = DAB2.connect();
        statement = conn.createStatement();
        String students2 = "SELECT * FROM `follow_up` WHERE enquiry_id = '"+id+"' order by ID desc limit 1";
        queryRs = statement.executeQuery(students2);
        String id2 = null;
        String enquiry_id=null;
        Date date2 = null;
        Date nextdate=null;
        String response=null;
        while (queryRs.next()) {
            id2 = queryRs.getString("id");
            date2=queryRs.getDate("date");
            enquiry_id=queryRs.getString("enquiry_id");
            nextdate=queryRs.getDate("next_date");
            response=queryRs.getString("response");
            System.out.println("Id of edited student: " + id2);
            System.out.println("Enquiry ID: " + enquiry_id);
            System.out.println("Enquiry date of edited student: " + date2);
            System.out.println("Follow up date of edited student: " + nextdate);
            System.out.println("Response " + response);
        }
        DateFormat formatter1 = new SimpleDateFormat("dd.MM.yyyy");
        String nextdate1= formatter1.format(nextdate);
        if(response.equals(FollowUpResponseMessage) && phonefollowupdate.contains(nextdate1) ){
            System.out.println("Response message is edited and matching with database.");
            extentTest.log(Status.PASS, "Response message is edited and matching with database.");
        }
        else{
            System.out.println("Response message is edited but not matching with database.");
            extentTest.log(Status.PASS, "Response message is edited but not matching with database.");
        }
        actionClass.clickOnObject(FollowUpCloseButton1);
    }
}