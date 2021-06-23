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
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

public class setUp_FrontOffice_pages {

    WebDriver driver;
    ExtentTest test;

    @FindBy(how = How.XPATH, using = "//*[@id=\"form1\"]/div[1]/div[1]/span/p")
    public WebElement lblBlank_Office;

    @FindBy(how = How.XPATH, using = "//*[@id=\"sibe-box\"]/ul[2]/li[6]/ul/li[7]/a")
    public WebElement btnSetUpFrontOffice;

    @FindBy(how = How.XPATH, using = "/html/body/div[1]/div/section[2]/div/div[1]/div/ul/li[1]/a")
    public WebElement btnPurpose;

    @FindBy(how = How.XPATH, using = "/html/body/div[1]/div/section[2]/div/div[1]/div/ul/li[2]/a")
    public WebElement btnComplaint;

    @FindBy(how = How.XPATH, using = "/html/body/div[1]/div/section[2]/div/div[1]/div/ul/li[3]/a")
    public WebElement btnSource;

    @FindBy(how = How.XPATH, using = "/html/body/div[1]/div/section[2]/div/div[1]/div/ul/li[4]/a")
    public WebElement btnReference;

    @FindBy(how = How.NAME, using = "visitors_purpose")
    public WebElement txtAddPurpose;

    @FindBy(how = How.NAME, using = "complaint_type")
    public WebElement txtAddComplaint;

    @FindBy(how = How.NAME, using = "source")
    public WebElement txtAddSource;

    @FindBy(how = How.NAME, using = "reference")
    public WebElement txtAddReference;

    @FindBy(how = How.NAME, using = "description")
    public WebElement txtDescription;

    @FindBy(how = How.XPATH, using = "//*[@id=\"form1\"]/div[2]/button")
    public WebElement btnSave;

    @FindBy(how = How.XPATH, using = "//*[@id=\"form1\"]/div[1]/div[1]")
    public WebElement lblAlert;

    @FindBy(how = How.XPATH, using = "//*[@id=\"DataTables_Table_0_wrapper\"]/div[1]/a[1]")
    public WebElement btnDown_Copy;

    @FindBy(how = How.ID, using = "dt-button-info")
    public WebElement lblbtnDown_Copy;

    @FindBy(how = How.XPATH, using = "//*[@id=\"sibe-box\"]/ul[2]/li[6]/ul/li[7]/a")
    public WebElement btnSetupFrontOffice;

    @FindBy(how = How.XPATH, using = "//*[@id=\"DataTables_Table_0_wrapper\"]/div[1]/a[2]")
    public WebElement btnDown_Excel;

    @FindBy(how = How.XPATH, using = "//*[@id=\"DataTables_Table_0_wrapper\"]/div[1]/a[3]")
    public WebElement btnDown_CSV;

    @FindBy(how = How.XPATH, using = "//*[@id=\"DataTables_Table_0_wrapper\"]/div[1]/a[4]")
    public WebElement btnDown_PDF;

    @FindBy(how = How.XPATH, using = "//*[@id=\"DataTables_Table_0_wrapper\"]/div[1]/a[5]")
    public WebElement btnDown_Print;

    @FindBy(how = How.XPATH, using = "//*[@id=\"headerContainer\"]/h1")
    public WebElement lblbtnDown_Print;

    @FindBy(how = How.XPATH, using = "//*[@id=\"DataTables_Table_0_wrapper\"]/div[1]/a[6]")
    public WebElement btnDown_Columns;

    public setUp_FrontOffice_pages(WebDriver driver, ExtentTest test) {
        this.driver = driver;
        this.test = test;
        PageFactory.initElements(driver, this);
    }

    //Global Variables
    //Global Variables
    Connection conn = null;

    Statement statement;
    ResultSet queryRs;

    String purposeName="Test Demo";
    String purposeDescription="This is a test Description";
    String updatedPurposeName="My Updated Name";
    String updatedPurposeDescription="Updated Test Description";

    String complaintName="Complaint name";
    String complaintDescription="This is a test description";
    String updatedComplaintName="Updated Complaint Name";
    String updatedComplaintDescription="";

    String sourceName="Test Source name";
    String updatedSourceName="Updated Test source name";
    String updatedSourceDescription="Updated Test Source Description";

    String referenceName="Test Demo Reference";
    String updatedReferenceName="Updated Test Reference";
    String updatedReferenceDescription="Updated Test Reference Description";

    public void openNewTab() throws InterruptedException {
        Thread.sleep(2000);
        ActionClass action_class = new ActionClass(driver,test);
        action_class.clickOnObject(btnSetupFrontOffice);
        System.out.println("open new tab function");
        for(String chTab : driver.getWindowHandles()){
            driver.switchTo().window(chTab);
        }
    }
    //Purpose
    public void checkBlank_purpose() throws InterruptedException, IOException {
        Thread.sleep(3000);
        ActionClass action_class= new ActionClass(driver,test);
        VerificationClass verification_class= new VerificationClass(driver,test);
        Thread.sleep(5000);
        action_class.clickOnObject(btnSetUpFrontOffice);
        action_class.clickOnObject(btnPurpose);
        Thread.sleep(2000);
        action_class.clickOnObject(btnSave);
        Thread.sleep(1000);
        verification_class.verifyTextPresent(lblBlank_Office,"The Visitors Purpose field is required.");
        Thread.sleep(2000);
        action_class.captureScreen("Blank_purpose");
    }

    public void adding_Purpose() throws InterruptedException, IOException {

        ActionClass action_class= new ActionClass(driver,test);
        VerificationClass verification_class= new VerificationClass(driver,test);
        Thread.sleep(1500);
        action_class.clickOnObject(btnPurpose);
        action_class.setValueinTextbox(txtAddPurpose,purposeName);
        Thread.sleep(2000);
        action_class.setValueinTextbox(txtDescription,purposeDescription);
        action_class.clickOnObject(btnSave);

        verification_class.verifyTextPresent(lblAlert,"Visitors Purpose added successfully");
        Thread.sleep(2000);
        action_class.captureScreen("Purpose Successfully added");
    }

    public void edit_addListing() throws IOException, InterruptedException {

        ActionClass action_class= new ActionClass(driver,test);
        VerificationClass verification_class= new VerificationClass(driver,test);

        List<WebElement> ListRecords = driver.findElements(By.xpath("//*[@id=\"DataTables_Table_0\"]/tbody/tr"));
        int RecordsSize = ListRecords.size();

        for (int i = 1; i <= RecordsSize; i++) {
            String s = driver.findElement(By.xpath("//*[@id=\"DataTables_Table_0\"]/tbody/tr["+i+"]/td[1]/a")).getText();

            if (s.equals(purposeName)) {
                System.out.println("Element Found for Edit in Row[" +i+1+ "]");
                Thread.sleep(2000);
                driver.findElement(By.xpath("//*[@id=\"DataTables_Table_0\"]/tbody/tr["+i+"]/td[2]/a[1]")).click();
                Thread.sleep(2000);
                action_class.captureScreen("Fields Filled for update");
                break;
            }else
            {
                System.out.println("Element not found in Row[" +i+1+ "]");
            }
        }
        action_class.setValueinTextbox_update(txtAddPurpose,updatedPurposeName);
        action_class.setValueinTextbox_update(txtDescription,updatedPurposeDescription);
        action_class.clickOnObject(btnSave);

        verification_class.verifyTextPresent(lblAlert,"Visitors Purpose updated successfully");
        Thread.sleep(2000);
        action_class.captureScreen("Updated/Edited Purpose");
    }

    public void verify_fromDB_purpose() throws InterruptedException, SQLException {
        Thread.sleep(2000);

        DatabaseFunctions DAB = new DatabaseFunctions(test);
        conn = DAB.connect();
        statement = conn.createStatement();

        String query = "SELECT * FROM visitors_purpose WHERE visitors_purpose='"+updatedPurposeName+"'";
        System.out.println(query);
        queryRs = statement.executeQuery(query);
        while (queryRs.next()) {
            String s1, s2 = null;
            s1 = queryRs.getString("visitors_purpose");
            s2 = queryRs.getString("description");
            System.out.println("Expected name. is " + s1);
            if (updatedPurposeName.equals(s1)) {
                test.log(Status.PASS, "Visitors purpose Matched");
                if (s2.equals(updatedPurposeDescription)) {
                    test.log(Status.PASS, "Description Matched");
                } else {
                    System.out.println("Description not match");
                    test.log(Status.FAIL, "Description not Matched");
                }
            } else {
                test.log(Status.FAIL, "Visitors prupose not Matched");
            }
        }
    }

    public void delete_Record() throws InterruptedException, IOException {
        ActionClass action_class= new ActionClass(driver,test);
        VerificationClass verification_class= new VerificationClass(driver,test);

        Thread.sleep(1000);
        List<WebElement> ListRecord = driver.findElements(By.xpath("//*[@id=\"DataTables_Table_0\"]/tbody/tr"));
        int Recordsize = ListRecord.size();
        for (int i = 1; i <= Recordsize; i++) {
            String s = driver.findElement(By.xpath("//*[@id=\"DataTables_Table_0\"]/tbody/tr["+i+"]/td[1]/a")).getText();
            System.out.println("Value in list is: " + s);

            if (s.equals(updatedPurposeName)) {
                System.out.println("Element Found for Delete");
                Thread.sleep(2000);
                driver.findElement(By.xpath("//*[@id=\"DataTables_Table_0\"]/tbody/tr["+i+"]/td[2]/a[2]")).click();
                Thread.sleep(2000);
                driver.switchTo().alert().accept();
                Thread.sleep(2000);
                verification_class.verifyTextPresent(lblAlert, "Visitors Purpose deleted successfully");
                action_class.captureScreen("User_Deleted");
                Thread.sleep(3000);
                break;
            } else {
                System.out.println("Element not found");
            }
        }
        verification_class.verifyTextPresent(lblAlert,"Visitors Purpose deleted successfully");
        Thread.sleep(2000);
        action_class.captureScreen("Deleted Purpose");
    }

    public void check_DownloadButtons() throws IOException, InterruptedException {
        ActionClass action_class = new ActionClass(driver, test);
        VerificationClass verification_class = new VerificationClass(driver, test);

        //Check Copied File
        action_class.clickOnObject(btnDown_Copy);
        Thread.sleep(2000);
        verification_class.verifyTextPresent(lblbtnDown_Copy, "Copy to clipboard");
        action_class.captureScreen("Records Copied");
        Thread.sleep(3000);

        //Check Downloaded XLSX
        action_class.clickOnObject(btnDown_Excel);
        Thread.sleep(3000);
        WebDriverWait wait = new WebDriverWait(driver, 5);
        String tmpFolderPath_xls = System.getProperty("/Users/Addweb-24/Downloads");
        String expectedFileName_xls = "xlsx.xlsx";
        File file_xls = new File(tmpFolderPath_xls,expectedFileName_xls);
        if (file_xls.exists())
        {
            System.out.println("File exists");
        }else
        {
            System.out.println("File not found");
        }

        //Check Downloaded CSV
        action_class.clickOnObject(btnDown_CSV);
        Thread.sleep(3000);
        WebDriverWait wait_csv = new WebDriverWait(driver, 5);
        String tmpFolderPath_csv = System.getProperty("/Users/Addweb-24/Downloads");
        String expectedFileName_csv = "csv.csv";
        File file_csv = new File(tmpFolderPath_csv,expectedFileName_csv);
        if (file_csv.exists())
        {
            System.out.println("File exists");
        }else
        {
            System.out.println("File not found");
        }

        //Check Downloaded CSV
        action_class.clickOnObject(btnDown_CSV);
        Thread.sleep(3000);
        WebDriverWait wait_pdf = new WebDriverWait(driver, 5);
        String tmpFolderPath_pdf = System.getProperty("/Users/Addweb-24/Downloads");
        String expectedFileName_pdf = "pdf.pdf";
        File file_pdf = new File(tmpFolderPath_pdf,expectedFileName_pdf);
        if (file_pdf.exists())
        {
            System.out.println("File exists");
        }else
        {
            System.out.println("File not found");
        }


        //Check Print
        action_class.clickOnObject(btnDown_Print);
        Thread.sleep(3000);
        verification_class.verifyTextPresent(lblbtnDown_Print,"Print");
        action_class.captureScreen("Purpose_Print Function");


    }

    //Complaint...
    public void checkBlank_complaint() throws InterruptedException, IOException {

        ActionClass action_class= new ActionClass(driver,test);
        VerificationClass verification_class= new VerificationClass(driver,test);

        action_class.clickOnObject(btnComplaint);
        Thread.sleep(2000);
        action_class.clickOnObject(btnSave);
        Thread.sleep(5000);
        verification_class.verifyTextPresent(lblBlank_Office,"The Complaint Type field is required.");
        Thread.sleep(2000);
        action_class.captureScreen("Blank_Complaint");
    }

    public void adding_Complaint() throws InterruptedException, IOException {

        ActionClass action_class= new ActionClass(driver,test);
        VerificationClass verification_class= new VerificationClass(driver,test);
        action_class.clickOnObject(btnComplaint);
        action_class.setValueinTextbox(txtAddComplaint,complaintName);
        Thread.sleep(2000);
        action_class.setValueinTextbox(txtDescription,complaintDescription);
        action_class.clickOnObject(btnSave);
        verification_class.verifyTextPresent(lblAlert,"Complaint Type added successfully");
        Thread.sleep(2000);
        action_class.captureScreen("Complaint Successfully added");
    }

    public void edit_addListing_complaint() throws IOException, InterruptedException {

        ActionClass action_class= new ActionClass(driver,test);
        VerificationClass verification_class= new VerificationClass(driver,test);
        action_class.clickOnObject(btnComplaint);

        List<WebElement> ListRecords = driver.findElements(By.xpath("//*[@id=\"DataTables_Table_0\"]/tbody/tr"));
        int RecordsSize = ListRecords.size();
        for (int i = 1; i <= RecordsSize; i++) {
            String s = driver.findElement(By.xpath("//*[@id=\"DataTables_Table_0\"]/tbody/tr["+i+"]/td[1]/a")).getText();
            if (s.equals(complaintName)) {
                System.out.println("Element Found for Edit in Row[" +i+1+ "]");
                Thread.sleep(2000);
                driver.findElement(By.xpath("//*[@id=\"DataTables_Table_0\"]/tbody/tr["+i+"]/td[2]/a[1]")).click();
                Thread.sleep(2000);
                action_class.captureScreen("Fields Filled for update");
                break;
            }else
            {
                System.out.println("Element not found in Row[" +i+1+ "]");
            }
        }

        action_class.setValueinTextbox_update(txtAddComplaint,updatedComplaintName);
        action_class.setValueinTextbox_update(txtDescription,"Updated Test Complaint Description");
        action_class.clickOnObject(btnSave);
        verification_class.verifyTextPresent(lblAlert,"Complaint Type updated successfully");
        Thread.sleep(2000);
        action_class.captureScreen("Updated/Edited Complaint");
    }

    public void verify_fromDB_complaint() throws InterruptedException, SQLException {

        Thread.sleep(2000);
        DatabaseFunctions DAB = new DatabaseFunctions(test);
        conn = DAB.connect();
        statement = conn.createStatement();

        String query = "SELECT * FROM complaint WHERE complaint_type='"+updatedComplaintName+"'";
        System.out.println(query);
        queryRs = statement.executeQuery(query);
        while (queryRs.next()) {
            String s1, s2 = null;
            s1 = queryRs.getString("complaint_type");
            s2 = queryRs.getString("description");
            System.out.println("Expected name. is " + s1);

            if (updatedComplaintName.equals(s1)) {
                test.log(Status.PASS, "Complaint Type Matched");
                if (s2.equals(updatedComplaintDescription)) {
                    test.log(Status.PASS, "Description Matched");
                } else {
                    test.log(Status.FAIL, "Description not Matched");
                }
            } else {
                test.log(Status.FAIL, "Complaint Type not Matched");
            }
        }
    }

    public void delete_Record_complaint() throws InterruptedException, IOException {
        ActionClass action_class= new ActionClass(driver,test);
        VerificationClass verification_class= new VerificationClass(driver,test);
        action_class.clickOnObject(btnComplaint);
        Thread.sleep(1000);
        List<WebElement> ListRecord = driver.findElements(By.xpath("//*[@id=\"DataTables_Table_0\"]/tbody/tr"));
        int Recordsize = ListRecord.size();
        for (int i = 1; i <= Recordsize; i++) {
            String s = driver.findElement(By.xpath("//*[@id=\"DataTables_Table_0\"]/tbody/tr["+i+"]/td[1]/a")).getText();
            System.out.println("Value in list is: " + s);
            if (s.equals(updatedComplaintName)) {
                System.out.println("Element Found for Delete");
                Thread.sleep(2000);
                driver.findElement(By.xpath("//*[@id=\"DataTables_Table_0\"]/tbody/tr["+i+"]/td[2]/a[2]")).click();
                Thread.sleep(2000);
                driver.switchTo().alert().accept();
                verification_class.verifyTextPresent(lblAlert, "Complaint Type deleted successfully");
                action_class.captureScreen("User_Deleted");
                Thread.sleep(3000);
                break;
            } else {
                System.out.println("Element not found");
            }
        }
        verification_class.verifyTextPresent(lblAlert,"Complaint Type deleted successfully");
        Thread.sleep(2000);
        action_class.captureScreen("Deleted Complaint");
    }

    //Source..........
    public void checkBlank_Source() throws InterruptedException, IOException {

        ActionClass action_class= new ActionClass(driver,test);
        VerificationClass verification_class= new VerificationClass(driver,test);

        action_class.clickOnObject(btnSource);
        Thread.sleep(2000);
        action_class.clickOnObject(btnSave);
        Thread.sleep(1000);
        verification_class.verifyTextPresent(lblBlank_Office,"The Source field is required.");
        Thread.sleep(2000);
        action_class.captureScreen("Blank_Source");
    }

    public void adding_Source() throws InterruptedException, IOException {

        ActionClass action_class= new ActionClass(driver,test);
        VerificationClass verification_class= new VerificationClass(driver,test);
        action_class.clickOnObject(btnSource);
        action_class.setValueinTextbox(txtAddSource,sourceName);
        Thread.sleep(2000);
        action_class.setValueinTextbox(txtDescription,"this is a test description");
        action_class.clickOnObject(btnSave);
        verification_class.verifyTextPresent(lblAlert,"Source added successfully");
        Thread.sleep(2000);
        action_class.captureScreen("Source Successfully added");
    }

    public void edit_addListing_Source() throws IOException, InterruptedException {

        ActionClass action_class= new ActionClass(driver,test);
        VerificationClass verification_class= new VerificationClass(driver,test);
        action_class.clickOnObject(btnSource);

        List<WebElement> ListRecords = driver.findElements(By.xpath("//*[@id=\"DataTables_Table_0\"]/tbody/tr"));
        int RecordsSize = ListRecords.size();
        for (int i = 1; i <= RecordsSize; i++) {
            String s = driver.findElement(By.xpath("//*[@id=\"DataTables_Table_0\"]/tbody/tr["+i+"]/td[1]/a")).getText();
            if (s.equals(sourceName)) {
                System.out.println("Element Found for Edit in Row[" +i+1+ "]");
                Thread.sleep(2000);
                driver.findElement(By.xpath("//*[@id=\"DataTables_Table_0\"]/tbody/tr["+i+"]/td[2]/a[1]")).click();
                Thread.sleep(2000);
                action_class.captureScreen("Fields Filled for update");
                break;
            }else
            {
                System.out.println("Element not found in Row[" +i+1+ "]");
            }
        }
        action_class.setValueinTextbox_update(txtAddSource,updatedSourceName);
        action_class.setValueinTextbox_update(txtDescription,"Updated Test Source Description");
        action_class.clickOnObject(btnSave);
        verification_class.verifyTextPresent(lblAlert,"Source updated successfully");
        Thread.sleep(3000);
        action_class.captureScreen("Updated/Edited Source");
    }

    public void verify_fromDB_source() throws SQLException {

        DatabaseFunctions DAB = new DatabaseFunctions(test);
        conn = DAB.connect();
        statement = conn.createStatement();

        String query = "SELECT * FROM source WHERE source='"+updatedSourceName+ "'";
        System.out.println(query);
        queryRs = statement.executeQuery(query);
        while (queryRs.next()) {
            String s1, s2 = null;
            s1 = queryRs.getString("source");
            s2 = queryRs.getString("description");
            System.out.println("Expected name. is " + s1);

            if (updatedSourceName.equals(s1)) {
                test.log(Status.PASS, "Source Type Matched");
                if (s2.equals(updatedSourceDescription)) {
                    test.log(Status.PASS, "Description Matched");
                } else {
                    System.out.println("Description not match");
                    test.log(Status.FAIL, "Description not Matched");
                }
            } else {
                test.log(Status.FAIL, "Source Type not Matched");
            }
        }
    }

    public void delete_Record_Source() throws InterruptedException, IOException {
        ActionClass action_class= new ActionClass(driver,test);
        VerificationClass verification_class= new VerificationClass(driver,test);

        action_class.clickOnObject(btnSource);
        Thread.sleep(1000);
        List<WebElement> ListRecord = driver.findElements(By.xpath("//*[@id=\"DataTables_Table_0\"]/tbody/tr"));
        int Recordsize = ListRecord.size();
        for (int i = 1; i <= Recordsize; i++) {
            String s = driver.findElement(By.xpath("//*[@id=\"DataTables_Table_0\"]/tbody/tr["+i+"]/td[1]/a")).getText();
            System.out.println("Value in list is: " + s);
            if (s.equals(updatedSourceName)) {
                System.out.println("Element Found for Delete");
                Thread.sleep(2000);
                JavascriptExecutor jsetaskscore1 = (JavascriptExecutor) driver;
                jsetaskscore1.executeScript("scrollBy(0, -700)");
                driver.findElement(By.xpath("//*[@id=\"DataTables_Table_0\"]/tbody/tr["+i+"]/td[2]/a[2]")).click();
                Thread.sleep(2000);
                driver.switchTo().alert().accept();
                Thread.sleep(2000);
                verification_class.verifyTextPresent(lblAlert, "Source deleted successfully");
                action_class.captureScreen("Source_Deleted");
                Thread.sleep(1000);
                break;
            } else {
                System.out.println("Element not found");
            }
        }
    }

    //Reference..........
    public void checkBlank_Reference() throws InterruptedException, IOException {

        ActionClass action_class= new ActionClass(driver,test);
        VerificationClass verification_class= new VerificationClass(driver,test);
        action_class.clickOnObject(btnReference);
        Thread.sleep(2000);
        action_class.clickOnObject(btnSave);
        Thread.sleep(1000);
        verification_class.verifyTextPresent(lblBlank_Office,"The Reference field is required.");
        Thread.sleep(2000);
        action_class.captureScreen("Blank_Reference");
    }

    public void adding_Reference() throws InterruptedException, IOException {

        ActionClass action_class= new ActionClass(driver,test);
        VerificationClass verification_class= new VerificationClass(driver,test);

        action_class.clickOnObject(btnReference);
        action_class.setValueinTextbox(txtAddReference,referenceName);
        Thread.sleep(2000);
        action_class.setValueinTextbox(txtDescription,"this is a test description");
        action_class.clickOnObject(btnSave);
        verification_class.verifyTextPresent(lblAlert,"Reference added successfully");
        Thread.sleep(2000);
        action_class.captureScreen("Reference Successfully added");
    }

    public void edit_addListing_Reference() throws IOException, InterruptedException {
        ActionClass action_class= new ActionClass(driver,test);
        VerificationClass verification_class= new VerificationClass(driver,test);

        action_class.clickOnObject(btnReference);
        List<WebElement> ListRecords = driver.findElements(By.xpath("//*[@id=\"DataTables_Table_0\"]/tbody/tr"));
        int RecordsSize = ListRecords.size();
        for (int i = 1; i <= RecordsSize; i++) {
            String s = driver.findElement(By.xpath("//*[@id=\"DataTables_Table_0\"]/tbody/tr["+i+"]/td[1]/a")).getText();
            if (s.equals(referenceName)) {
                System.out.println("Element Found for Edit in Row[" +i+1+ "]");
                Thread.sleep(2000);
                driver.findElement(By.xpath("//*[@id=\"DataTables_Table_0\"]/tbody/tr["+i+"]/td[2]/a[1]")).click();
                Thread.sleep(2000);
                action_class.captureScreen("Fields Filled for update");
                break;
            }else
            {
                System.out.println("Element not found in Row[" +i+1+ "]");
            }
        }

        action_class.setValueinTextbox_update(txtAddReference,updatedReferenceName);
        action_class.setValueinTextbox_update(txtDescription,"Updated Test Reference Description");
        action_class.clickOnObject(btnSave);
        verification_class.verifyTextPresent(lblAlert,"Reference updated successfully");
        Thread.sleep(2000);
        action_class.captureScreen("Updated/Edited Reference");
    }

    public void verify_fromDB_reference() throws SQLException {

        DatabaseFunctions DAB = new DatabaseFunctions(test);
        conn = DAB.connect();
        statement = conn.createStatement();

        String query = "SELECT * FROM reference WHERE reference='"+updatedReferenceName+"'";
        System.out.println(query);
        queryRs = statement.executeQuery(query);
        while (queryRs.next()) {
            String s1, s2 = null;
            s1 = queryRs.getString("reference");
            s2 = queryRs.getString("description");
            System.out.println("Expected name. is " + s1);
            if (updatedReferenceName.equals(s1)) {
                test.log(Status.PASS, "Reference Matched");
                if (updatedReferenceDescription.equals(s2)) {
                    test.log(Status.PASS, "Description Matched");
                } else {
                    System.out.println("Description not match");
                    test.log(Status.FAIL, "Description not Matched");
                }
            } else {
                test.log(Status.FAIL, "Reference not Matched");
            }
        }
    }

    public void delete_Record_Reference() throws InterruptedException, IOException {
        ActionClass action_class= new ActionClass(driver,test);
        VerificationClass verification_class= new VerificationClass(driver,test);

        action_class.clickOnObject(btnReference);

        List<WebElement> ListRecord = driver.findElements(By.xpath("//*[@id=\"DataTables_Table_0\"]/tbody/tr"));
        int Recordsize = ListRecord.size();
        for (int i = 1; i <= Recordsize; i++) {
            String s = driver.findElement(By.xpath("//*[@id=\"DataTables_Table_0\"]/tbody/tr["+i+"]/td[1]/a")).getText();
            System.out.println("Value in list is: " + s);
            if (s.equals(updatedReferenceName)) {
                System.out.println("Element Found for Delete");
                Thread.sleep(2000);
                driver.findElement(By.xpath("//*[@id=\"DataTables_Table_0\"]/tbody/tr["+i+"]/td[2]/a[2]")).click();
                Thread.sleep(2000);
                driver.switchTo().alert().accept();
                Thread.sleep(1000);
                verification_class.verifyTextPresent(lblAlert,"Reference deleted successfully");
                Thread.sleep(2000);
                action_class.captureScreen("Deleted Reference");
                break;
            } else {
                System.out.println("Element not found");
            }
        }
    }
}