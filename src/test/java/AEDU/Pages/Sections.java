package AEDU.Pages;

import AEDU.Actions.ActionClass;
import AEDU.Actions.VerificationClass;
import AEDU.Utilities.DatabaseFunctions;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

public class Sections {
    WebDriver driver;
    ExtentTest test;
    Connection conn = null;
    Statement statement;
    ResultSet queryRs;

    @FindBy(how = How.XPATH, using = "//*[@id=\"employeeform\"]/div[2]/button")
    public WebElement btnSave;

    @FindBy(how = How.XPATH, using = "//*[@id='employeeform']/div[1]/div/span/p")
    public WebElement lblBlank;

    @FindBy(how = How.XPATH, using = "//*[@id='section']")
    public WebElement txtboxSection;

    @FindBy(how = How.XPATH, using = "alert alert-success text-left")
    public WebElement lblAlert;

    @FindBy(how = How.XPATH,using = "//*[@id=\"sibe-box\"]/ul[2]/li[3]/ul/li[6]/a")
    public WebElement btnSections;

    public Sections(WebDriver driver, ExtentTest test) {
        this.driver = driver;
        this.test = test;
    }

    //Global variables
    String sectionName = "ManualTesting";
    String updatedSectionName = "AutomationTesting";

    public void openNewTab() throws InterruptedException {
        Thread.sleep(2000);
        System.out.println("open new tab function");

        for (String childTab : driver.getWindowHandles()) {
            driver.switchTo().window(childTab);
        }
    }

    public void checkBlank_Section() throws IOException, InterruptedException {
        ActionClass action_Class = new ActionClass(driver, test);
        VerificationClass verificationClass = new VerificationClass(driver, test);
        //Save
        driver.findElement(By.xpath("//*[@id=\"employeeform\"]/div[2]/button")).click();
        test.log(Status.PASS,"Successfully clicked on object");
//        action_Class.clickOnObject(btnSave);
        Thread.sleep(2000);
        action_Class.captureScreen("Blank_Section_validation");
    }

    public void validSectionEntry() throws InterruptedException, IOException {
        ActionClass actionClass = new ActionClass(driver, test);
        VerificationClass verificationClass = new VerificationClass(driver, test);

        //BTN-Sections
        driver.findElement(By.xpath("//*[@id=\"sibe-box\"]/ul[2]/li[3]/ul/li[6]/a")).click();
        test.log(Status.PASS,"Successfully clicked on object");

        Thread.sleep(2000);
        driver.findElement(By.xpath("//*[@id='section']")).sendKeys(sectionName);

        //Save
        driver.findElement(By.xpath("//*[@id=\"employeeform\"]/div[2]/button")).click();
        test.log(Status.PASS,"Successfully clicked on object");
        Thread.sleep(2000);

        actionClass.captureScreen("Section_AddSuccess");
    }

    public void verify_AddFromListing() throws InterruptedException {
        //BTN-Sections
        driver.findElement(By.xpath("//*[@id=\"sibe-box\"]/ul[2]/li[3]/ul/li[6]/a")).click();
        test.log(Status.PASS,"Successfully clicked on object");
        Thread.sleep(2000);
        List<WebElement> ListRecord = driver.findElements(By.xpath("//*[@id='DataTables_Table_0']/tbody/tr"));
        int RecordSize = ListRecord.size();
        int flag=0;


        for (int i = 1; i <= RecordSize; i++) {
            String s = driver.findElement(By.xpath("//*[@id=\"DataTables_Table_0\"]/tbody/tr[" + i + "]/td[1]")).getText();
            System.out.println("Value in list is: " + s);
            if ((s.equals(sectionName))) {
                test.log(Status.PASS, "Section name Found in the Listing");
                flag=1;
                break;
            } else {
                System.out.println("Comment");
            }
        }
        if(flag==0){
            test.log(Status.FAIL,"Section name not found in listing");
        }
    }

    public void verify_AddfromDB() throws SQLException, InterruptedException {
        Thread.sleep(2000);
        DatabaseFunctions DAB = new DatabaseFunctions(test);
        conn = DAB.connect();
        statement = conn.createStatement();
        int flag=0;
        String query = "Select section from sections where section = '" + sectionName + "'";
        System.out.println(query);
        queryRs = statement.executeQuery(query);

        while (queryRs.next()) {
            String s1 = null;
            s1 = queryRs.getString("section");
            System.out.println("Expected name. is " + s1);

            if (s1.equals(sectionName)) {
                test.log(Status.PASS, "Section name present in the Database");
                flag=1;
                break;
            } else {
                test.log(Status.INFO, "Element Search in Process");
            }
        }
        if(flag==0){
            test.log(Status.FAIL, "Element Not Found in Database");
        }
    }

    public void update_Section() throws InterruptedException, IOException {

        ActionClass actionClass = new ActionClass(driver, test);
        VerificationClass verificationClass = new VerificationClass(driver, test);

        driver.findElement(By.xpath("//*[@id=\"sibe-box\"]/ul[2]/li[3]/ul/li[6]/a")).click();
        test.log(Status.PASS,"Successfully clicked on object");
        Thread.sleep(2000);

        List<WebElement> ListRecord = driver.findElements(By.xpath("//*[@id='DataTables_Table_0']/tbody/tr"));
        int Recordsize = ListRecord.size();
        int flag=0;
        for (int i = 1; i <= Recordsize; i++) {
            String s = driver.findElement(By.xpath("//*[@id=\"DataTables_Table_0\"]/tbody/tr[" + i + "]/td[1]")).getText();
            if (s.equals(sectionName)) {
                System.out.println("Element found for update");
                test.log(Status.PASS, "Section name found to Edit/Update");
                driver.findElement(By.xpath("//*[@id=\"DataTables_Table_0\"]/tbody/tr["+i+"]/td[2]/a[1]")).click();
                test.log(Status.PASS,"Successfully clicked on object to Edit Details");
                Thread.sleep(2000);
                actionClass.captureScreen("UpdateElement found");
                flag=1;
                break;
            } else {
                System.out.println("Element not found for Update");

            }
        }
        if(flag==0){
            test.log(Status.FAIL, "Section name Not Found to Update");
        }

        Thread.sleep(2000);
        driver.findElement(By.xpath("//*[@id='section']")).clear();
        driver.findElement(By.xpath("//*[@id='section']")).sendKeys(updatedSectionName);

        //Save
        driver.findElement(By.xpath("//*[@id=\"employeeform\"]/div[2]/button")).click();
        test.log(Status.PASS,"Successfully clicked on object");
        Thread.sleep(2000);
        actionClass.captureScreen("SectionName_Updated");
    }

    public void verify_UpdateFromListing() throws InterruptedException {
        driver.findElement(By.xpath("//*[@id=\"sibe-box\"]/ul[2]/li[3]/ul/li[6]/a")).click();
        test.log(Status.PASS,"Successfully clicked on object");
        Thread.sleep(2000);

        List<WebElement> ListRecord = driver.findElements(By.xpath("//*[@id='DataTables_Table_0']/tbody/tr"));
        int RecordSize = ListRecord.size();
        int flag=0;

        for (int i = 1; i <= RecordSize; i++) {
            String s = driver.findElement(By.xpath("//*[@id=\"DataTables_Table_0\"]/tbody/tr[" + i + "]/td[1]")).getText();
            System.out.println("Value in list is: " + s);
            if ((s.equals(updatedSectionName))) {
                test.log(Status.PASS, "Updated Section name Found in the Listing");
                flag=1;
                break;
            } else {
                System.out.println("Element is not found");
            }
        }
        if(flag==0){
            test.log(Status.FAIL,"Updated Section name not found in listing");
        }
    }

    public void verify_UpdatefromDB() throws SQLException, InterruptedException {
        Thread.sleep(2000);
        DatabaseFunctions DAB = new DatabaseFunctions(test);
        conn = DAB.connect();
        statement = conn.createStatement();

        String query = "Select section from sections where section='" + updatedSectionName + "'";
        System.out.println(query);
        queryRs = statement.executeQuery(query);
        int flag=0;
        while (queryRs.next()) {
            String s1 = null;
            s1 = queryRs.getString("section");
            System.out.println("Expected name. is " + s1);

            if (s1.equals(updatedSectionName)) {
                test.log(Status.PASS, "Updated Section name present in the Database");
                flag=1;
                break;
            } else {
                System.out.println("Section not found");
            }
        }
        if(flag==0){
            test.log(Status.FAIL,"Updated Section name not found in listing");
        }
    }

    public void delete_section() throws InterruptedException, IOException   {

        ActionClass actionClass= new ActionClass(driver,test);
        driver.findElement(By.xpath("//*[@id=\"sibe-box\"]/ul[2]/li[3]/ul/li[6]/a")).click();
        test.log(Status.PASS,"Successfully clicked on object");
        Thread.sleep(2000);
        List<WebElement> ListRecord = driver.findElements(By.xpath("//*[@id='DataTables_Table_0']/tbody/tr"));
        int Recordsize = ListRecord.size();
        int flag=0;

        for (int i = 1; i <= Recordsize; i++) {

            String s = driver.findElement(By.xpath("//*[@id=\"DataTables_Table_0\"]/tbody/tr[" + i + "]/td[1]")).getText();
            System.out.println(s);

            if (s.equals(updatedSectionName)) {
                System.out.println("Element found for Delete");
                test.log(Status.PASS, "Section name found to Delete");
                driver.findElement(By.xpath("//*[@id=\"DataTables_Table_0\"]/tbody/tr["+i+"]/td[2]/a[2]")).click();
                test.log(Status.PASS,"Successfully Clicked on Object to Delete");
                Thread.sleep(2000);
                driver.switchTo().alert().accept();
                Thread.sleep(1000);
                actionClass.captureScreen("Section Deleted");
                flag=1;
                break;
            } else {
                System.out.println("Element not found for Update");
            }
        }
        if(flag==0){
            test.log(Status.FAIL, "Section name Not Found to Delete");
        }
    }

    public void verify_DeleteFromDB() throws SQLException {

        DatabaseFunctions DAB = new DatabaseFunctions(test);
        conn = DAB.connect();
        statement = conn.createStatement();

        String query = "Select section from sections where section='" + updatedSectionName + "'";
        System.out.println(query);
        queryRs = statement.executeQuery(query);
        int flag=0;

        while (queryRs.next()) {
            String s1 = null;
            s1 = queryRs.getString("section");
            System.out.println("Expected name. is " + s1);

            if (s1.equals(updatedSectionName)) {
                test.log(Status.FAIL, "Updated Section Present in Database");
                flag=1;
                break;
            } else {
                System.out.println("Element Still present in database");

            }
        }
        if(flag==0){
            test.log(Status.PASS, "Updated Section name Not Present in the Database");
        }
    }

}