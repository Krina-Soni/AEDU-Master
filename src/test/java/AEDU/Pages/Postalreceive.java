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

public class Postalreceive {

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
            using = "/html/body/div[1]/div/div/div[2]/div/div/div[2]/form[1]/button"
    )
    private WebElement submitbtn;

    @FindBy(how = How.XPATH,
            using = "  /html/body/div[1]/aside/div/section/ul[2]/li[6]/a/span"
    )
    private WebElement FrontOfficeCLick;
    @FindBy(how = How.XPATH,
            using = "/html/body/div[1]/aside/div/section/ul[2]/li[6]/ul/li[5]/a"
    )
    private WebElement Postalreceiveclcik;
    @FindBy(how = How.XPATH,
            using = " /html/body/div[1]/div[1]/section[2]/div/div[1]/div/div/h3"
    )
    private WebElement Postalreceivetext;
    @FindBy(how = How.XPATH,
            using = "/html/body/div[1]/div[1]/section[2]/div/div[1]/div/form/div[2]/button"
    )
    private WebElement Savebtn;
    @FindBy(how = How.XPATH,
            using = "/html/body/div[1]/div[1]/section[2]/div/div[1]/div/form/div[1]/div[1]/span/p"
    )
    private WebElement TitleFeildvalidation;
    @FindBy(how = How.NAME,
            using = "from_title"
    )
    private WebElement Fromtitle;

    @FindBy(how = How.NAME,
            using = "from_title"
    )
    private WebElement Fromtitle1;
    @FindBy(how = How.NAME,
            using = "to_title"
    )
    private WebElement totile1;
    @FindBy(how = How.XPATH,
            using = " /html/body/div[1]/div[1]/section[2]/div/div[1]/div/form/div[1]/div[2]/input"
    )
    private WebElement Refranceno;
    @FindBy(how = How.XPATH,
            using = " //*[@id=\"description\"]"
    )
    private WebElement Address;

    @FindBy(how = How.NAME,
            using = "to_title"
    )
    private WebElement Totile;

    @FindBy(how = How.XPATH,
            using = "//*[@id=\"date\"]"
    )
    private WebElement DateFeild;
    @FindBy(how = How.XPATH,
            using = "/html/body/div[4]/div[1]/table/tbody/tr[1]/td[3]"
    )
    private WebElement DateSelect;

    @FindBy(how = How.XPATH,
            using = " /html/body/div[1]/div[1]/section[2]/div/div[1]/div/form/div[2]/button"
            )
    private WebElement Savebtn1;
    @FindBy(how = How.XPATH,
            using = "/html/body/div[1]/div[1]/section[2]/div/div[1]/div/form/div[1]/div[1]"
    )
    private WebElement Sucssesmsg;
    @FindBy(how = How.XPATH,
            using = " /html/body/div[1]/div[1]/section[2]/div/div[2]/div/div[2]/div[2]/div/table/tbody/tr/td[5]/a[2]/i"
    )
    private WebElement ReceiveEdit;
    @FindBy(how = How.XPATH,
            using = "/html/body/div[1]/div[1]/section[2]/div/div[1]/div/form/div[1]/div[1]"
    )
    private WebElement UpdateMessage;
    @FindBy(how = How.XPATH,
            using = " /html/body/div[1]/div[1]/section[2]/div/div[2]/div/div[2]/div[2]/div/table/tbody/tr[1]/td[5]/a[1]"
    )

    private WebElement ViewReceive;
    @FindBy(how = How.XPATH,
            using = "/html/body/div[1]/div[2]/div/div/div[1]/button"
    )
    private WebElement Closepopup;
    @FindBy(how = How.XPATH,
            using = "/html/body/div[1]/div[1]/section[2]/div/div[2]/div/div[2]/div[2]/div/table/tbody/tr[1]/td[5]/a[3]"
    )
    private WebElement Delete;
    @FindBy(how = How.XPATH,
            using = "/html/body/div[1]/div[1]/section[2]/div/div[1]/div/form/div[1]/div[1]"
    )
    private WebElement DeleteMessage;
    @FindBy(how = How.XPATH,
            using = " //*[@id='DataTables_Table_0_filter']/label/input"
    )
    private WebElement Search;
    @FindBy(how = How.XPATH,
            using = " //*[@id=\"getdetails\"]"
    )
    private WebElement DetailsSearch;
    @FindBy(how = How.XPATH,
            using = " /html/body/div[1]/aside/div/section/ul[2]/li[6]/ul/li[5]/a"
    )
    private WebElement test;

    public Postalreceive(WebDriver driver, ExtentTest test) {
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

     public void Checkwithcredential()throws IOException, InterruptedException {
        ActionClass actionClass = new ActionClass(driver, extentTest);
//         Thread.sleep(5000);
//        actionClass.clickOnObject((this.FrontOfficeCLick));
        Thread.sleep(5000);
        actionClass.clickOnObject(this.Postalreceiveclcik);
        VerificationClass very=new VerificationClass(driver,extentTest);
        very.verifyTextPresent(this.Postalreceivetext,"Add Postal Receive");
        JavascriptExecutor jsetaskscore = (JavascriptExecutor) driver;
        jsetaskscore.executeScript("scrollBy(0, 500)");
        actionClass.clickOnObject(this.Savebtn1);
        VerificationClass very1=new VerificationClass(driver,extentTest);
        very.verifyTextPresent(this.TitleFeildvalidation,"The From Title field is required.");
        actionClass.captureScreen("Postal receive form title field validation ");
    }

     public void AddvalidData(String Name) throws IOException, InterruptedException, SQLException {
         ActionClass actionClass = new ActionClass(driver, extentTest);
//         Thread.sleep(5000);
//         actionClass.clickOnObject((this.FrontOfficeCLick));
         Thread.sleep(5000);
         actionClass.clickOnObject(this.Postalreceiveclcik);
         Thread.sleep(1000);
         actionClass.clickOnObject(this.Postalreceiveclcik);
         actionClass.clickOnObject(this.Fromtitle1);
        actionClass.setValueinTextbox(this.Fromtitle1,Name);
        actionClass.clickOnObject(this.Refranceno);
        actionClass.setValueinTextbox(this.Refranceno,"54354793");
        actionClass.clickOnObject(this.Address);
        actionClass.setValueinTextbox(this.Address,"maninager ahemedabad");
        JavascriptExecutor jsetaskscore = (JavascriptExecutor) driver;
        jsetaskscore.executeScript("scrollBy(0, 300)");
        actionClass.clickOnObject(this.totile1);
        actionClass.setValueinTextbox(this.totile1,"riddhi dave");
        actionClass.clickOnObject(this.Savebtn1);
        VerificationClass very1=new VerificationClass(driver,extentTest);
        very1.verifyTextPresent(this.Sucssesmsg,"Receive added successfully");
        actionClass.captureScreen("Postal receive added");
        DatabaseFunctions DAB = new DatabaseFunctions(extentTest);
        conn = DAB.connect();
        statement = conn.createStatement();
        String PostalReceive = "SELECT from_title FROM `dispatch_receive` where from_title='" + Name + "'";
        ResultSet queryRs1 = statement.executeQuery(PostalReceive);
        String expectedText = Name;
        ArrayList listNames = new ArrayList();

        while (queryRs1.next()) {
            String s1 = null;
            s1 = queryRs1.getString("from_title");
            actionClass.CompareListandstring(expectedText, s1);
        }
    }

    public void EDITData(String Name, String Name1) throws IOException, InterruptedException, SQLException {
        ActionClass actionClass = new ActionClass(driver, extentTest);
//        actionClass.clickOnObject((this.FrontOfficeCLick));
        Thread.sleep(5000);
       actionClass.clickOnObject(this.Postalreceiveclcik);
        Thread.sleep(1000);
        actionClass.clickOnObject(this.Fromtitle);
        actionClass.setValueinTextbox(this.Fromtitle,Name);
        actionClass.clickOnObject(this.Refranceno);
        actionClass.setValueinTextbox(this.Refranceno,"54354793");
        actionClass.clickOnObject(this.Address);
        actionClass.setValueinTextbox(this.Address,"maninager ahemedabad");
        JavascriptExecutor jsetaskscore = (JavascriptExecutor) driver;
        jsetaskscore.executeScript("scrollBy(0, 300)");
        actionClass.clickOnObject(this.Totile);
        actionClass.setValueinTextbox(this.Totile,"riddhi dave");
        actionClass.clickOnObject(this.Savebtn1);
        VerificationClass very1=new VerificationClass(driver,extentTest);
        very1.verifyTextPresent(this.Sucssesmsg,"Receive added successfully");
        actionClass.captureScreen("Postal receive added ");
        DatabaseFunctions DAB = new DatabaseFunctions(extentTest);
        conn = DAB.connect();
        statement = conn.createStatement();
        String PostalReceive = "SELECT id, from_title FROM `dispatch_receive` where from_title='" + Name + "'";
        ResultSet queryRs1 = statement.executeQuery(PostalReceive);
        String expectedText = Name;
         int S2 = 0;
        ArrayList listNames = new ArrayList();

        while (queryRs1.next()) {
          String s1 = null;
          s1 = queryRs1.getString("from_title");
          S2 = queryRs1.getInt("id");
          listNames.add(s1);
          actionClass.CompareListandstring(expectedText, s1);
        }
        actionClass.setValueinTextbox(Search,Name);
        actionClass.clickOnObject(this.ReceiveEdit);
        actionClass.clickOnObject(this.Fromtitle);
        Thread.sleep(1000);
        actionClass.setValueinTextbox(this.Fromtitle,Name1);
        JavascriptExecutor jsetaskscore2 = (JavascriptExecutor) driver;
        jsetaskscore2.executeScript("scrollBy(0, 300)");
        actionClass.clickOnObject(this.Savebtn1);
        VerificationClass very2 = new VerificationClass(driver,extentTest);
        very2.verifyTextPresent(this.UpdateMessage,"Receive updated successfully");
        actionClass.captureScreen("Receive updated");
        String PostalReceiveupdate = "SELECT id, from_title FROM `dispatch_receive` where from_title='" + Name1 + "' AND id ='" + S2 + "'";
        ResultSet queryRs2 = statement.executeQuery(PostalReceiveupdate);
        String expectedText1 = Name1;
        int S3 = 0;
        ArrayList listNames3 = new ArrayList();

        while (queryRs2.next()) {
              String s1 = null;
              s1 = queryRs2.getString("from_title");
              S3 = queryRs2.getInt("id");
              listNames3.add(s1);
              actionClass.CompareListandstring(expectedText, s1);
        }

        actionClass.CompareList(listNames,listNames3);
        extentTest.log(Status.INFO, "Records updated successfully "+ Name + "updated with " + Name1 );
        System.out.println("Records updated successfully "+ Name + "updated with" + Name1 );
    }

    public void View (String Name)throws IOException, InterruptedException {
        ActionClass actionClass = new ActionClass(driver, extentTest);
        List<WebElement> ListStudent = driver.findElements(By.xpath("/html/body/div[1]/div[1]/section[2]/div/div[2]/div/div[2]/div[2]/div/table/tbody/tr[1]/td[1]"));
        int ClassStudentsize = ListStudent.size();
        String s = null;

            for (int i = 1; i <= ClassStudentsize; i++) {
            s = driver.findElement(By.xpath("/html/body/div[1]/div[1]/section[2]/div/div[2]/div/div[2]/div[2]/div/table/tbody/tr[1]/td[1]")).getText();
            System.out.println("Value in list is: " + s);
            }

        actionClass.clickOnObject(this.ViewReceive);
        Thread.sleep(3000);
        actionClass.clickOnObject((this.DetailsSearch));
        String s2 = driver.findElement(By.xpath("/html/body/div[1]/div[2]/div/div/div[2]/table/tbody/tr[3]/td[1]")).getText();
        System.out.println(s2);
        actionClass.CompareListandstring(s,s2);
        actionClass.captureScreen(" View Receive");
        actionClass.clickOnObject(this.Closepopup);
    }

    public void Deletes()throws IOException, InterruptedException {
        ActionClass actionClass = new ActionClass(driver, extentTest);
//        actionClass.clickOnObject((this.FrontOfficeCLick));
       Thread.sleep(5000);
       actionClass.clickOnObject(this.Postalreceiveclcik);
        Thread.sleep(1000);
        actionClass.clickOnObject(this.Delete);
        Thread.sleep(5000);
        Alert alert = driver.switchTo().alert();
        alert.accept();
        VerificationClass very1 = new VerificationClass(driver, extentTest);
        very1.verifyTextPresent(this.DeleteMessage, "Receive deleted successfully");
        actionClass.captureScreen("Receive Deleted");
    }
}


