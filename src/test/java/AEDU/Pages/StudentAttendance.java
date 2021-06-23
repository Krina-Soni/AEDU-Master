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
import org.openqa.selenium.support.ui.Select;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

public class StudentAttendance {
    WebDriver driver;
    ExtentTest test;
    Connection conn = null;
    Statement statement;
    ResultSet queryRs;
    @FindBy(how = How.XPATH, using = "//*[@id=\"sibe-box\"]/ul[2]/li[4]/a")
    public WebElement btnAttendance;

    @FindBy(how = How.XPATH, using = "//*[@id=\"sibe-box\"]/ul[2]/li[4]/ul/li[1]/a")
    public WebElement btnStudAttendance;

    @FindBy(how = How.XPATH, using = "//*[@id=\"form1\"]/div[2]/button")
    public WebElement btnSearch;

    @FindBy(how = How.XPATH, using = "//*[@id=\"form1\"]/div[1]/div/div[1]/div/span/p")
    public WebElement lblClass;

    @FindBy(how = How.XPATH, using = "//*[@id=\"form1\"]/div[1]/div[1]")
    public WebElement lblAttendanceSaved;

    @FindBy(how = How.XPATH, using = "/html/body/div[1]/div[1]/section[2]/div/div/div[2]/div[2]/div")
    public WebElement lblHolidayAttendance;

    @FindBy(how = How.CSS, using = ".radio:nth-child(2) > label")
    public WebElement btnLateWithExcuse;

    @FindBy(how = How.XPATH, using = "/html/body/div[1]/div[1]/section[2]/div/div/div[2]/div[2]/form/div[2]/table/tbody/tr[1]/td[5]/div[2]/label")
    public WebElement lblBtnWithExcuse;

    @FindBy(how = How.XPATH, using = "/html/body/div[3]/div[1]/table/thead/tr[1]/th[1]")
    public WebElement btnPrevCalendar;

    @FindBy(how = How.XPATH, using = "//*[@id=\"date\"]")
    public WebElement btnCalendar;

    @FindBy(how = How.XPATH, using = "/html/body/div[3]/div[1]/table/tbody/tr[2]/td[7]")
    public WebElement btnCalendarDate;

    @FindBy(how = How.XPATH, using = "/html/body/div[1]/div[1]/section[2]/div/div/div[2]/div[2]/form/div[1]/div/button")
    public WebElement btnSaveAttendance;

    @FindBy(how = How.XPATH, using = "//*[@id='attendencetype204-3']")
    public WebElement btnAbsent;

    @FindBy(how = How.XPATH, using = "/html/body/div[1]/div[1]/section[2]/div/div/div[2]/div[2]/form/div[1]/span/button")
    public WebElement btnMarkasHoliday;

    //Global Variables
    String expectedname="Johnny Harper";

    public StudentAttendance(WebDriver driver, ExtentTest test) {
        this.driver = driver;
        this.test = test;
        PageFactory.initElements(driver, this);
    }

    public void openNewTab() throws InterruptedException {
        Thread.sleep(2000);
        System.out.println("open new tab function");
        for (String childTab : driver.getWindowHandles()) {
            driver.switchTo().window(childTab);
            Thread.sleep(2000);
        }
    }

    public void checkBlankStudent() throws InterruptedException, IOException {
        ActionClass actionClass = new ActionClass(driver, test);
        VerificationClass verificationClass= new VerificationClass(driver,test);

        Thread.sleep(3000);
       actionClass.clickOnObject(btnAttendance);
//      driver.findElement(By.xpath("//*[@id=\"sibe-box\"]/ul[2]/li[4]/a")).click();

        Thread.sleep(2000);
        actionClass.clickOnObject(btnStudAttendance);
//      driver.findElement(By.xpath("//*[@id=\"sibe-box\"]/ul[2]/li[4]/ul/li[1]/a")).click();

        Thread.sleep(2000);
        actionClass.clickOnObject(btnSearch);
//      driver.findElement(By.xpath("//*[@id=\"form1\"]/div[2]/button")).click();
        verificationClass.verifyTextPresent(lblClass,"The Class field is required.");

        actionClass.captureScreen("Blank Validation messages");

    }

    public void check_validEntry() throws InterruptedException, IOException {
        ActionClass actionClass = new ActionClass(driver, test);

        Thread.sleep(3000);
        //Student Attendance Link
        actionClass.clickOnObject(btnStudAttendance);

        Thread.sleep(3000);
        Select stuclass = new Select(driver.findElement(By.xpath("//*[@id=\"class_id\"]")));
        stuclass.selectByVisibleText("Nursury");

        Thread.sleep(3000);
        Select stuSection = new Select(driver.findElement(By.xpath("//*[@id=\"section_id\"]")));
        stuSection.selectByVisibleText("A");

        actionClass.clickOnObject(btnCalendar);
        Thread.sleep(2000);
        actionClass.clickOnObject(btnPrevCalendar);
        Thread.sleep(2000);
        actionClass.clickOnObject(btnCalendarDate);
        Thread.sleep(3000);
        actionClass.clickOnObject(btnSearch);
        Thread.sleep(3000);
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollBy(0,600)");
        test.log(Status.PASS,"All results are successfully searched");
        actionClass.captureScreen("All Results loaded");
    }

    public void verifyValidResultsfromDB() throws SQLException, IOException, InterruptedException {

        DatabaseFunctions DAB = new DatabaseFunctions(test);
        conn = DAB.connect();
        statement = conn.createStatement();

        String query = "SELECT students.firstname, students.lastname, student_session.class_id, student_session.session_id FROM student_session JOIN students ON students.id = student_session.student_id INNER JOIN classes ON student_session.class_id=classes.id INNER JOIN sections ON student_session.section_id=sections.id WHERE (classes.class ='Nursury' AND sections.section ='A' AND student_session.session_id = 15 AND student_session.is_inactive='no')";
        System.out.println(query);
        queryRs = statement.executeQuery(query);

        List<WebElement> Records = driver.findElements(By.xpath("/html/body/div[1]/div[1]/section[2]/div/div/div[2]/div[2]/form/div[2]/table/tbody/tr"));
        int RecordSize = Records.size();

        for (int i = 1; i<=RecordSize; i++) {

            while (queryRs.next()) {
                String s1 = null;
                s1 = queryRs.getString("firstname");
                System.out.println(s1);

                String s = driver.findElement(By.xpath("/html/body/div[1]/div[1]/section[2]/div/div/div[2]/div[2]/form/div[2]/table/tbody/tr["+i+"]/td[4]")).getText();
                System.out.println("Value in list is: " + s);

                if ((s.equals(s1))) {
                    test.log(Status.PASS, "Section name: "+s1+" Found in the Listing");
                    break;

                } else {
                    System.out.println("Comment");
                }
            }
        }
    }

    public void saving_Attendance() throws IOException, InterruptedException {
        ActionClass actionClass = new ActionClass(driver, test);
        VerificationClass verificationClass = new VerificationClass(driver, test);
//        actionClass.clickOnObject(btnAttendance);
        check_validEntry();
        Thread.sleep(2000);
        driver.findElement(By.cssSelector(".radio:nth-child(2) > label")).click();
        Thread.sleep(2000);
        actionClass.clickOnObject(btnSaveAttendance);
        test.log(Status.PASS,"Attendance Successfully saved");
        Thread.sleep(2000);
        verificationClass.verifyTextPresent(lblAttendanceSaved, "Attendance Saved Successfully");
        actionClass.captureScreen("Attendance Saved successfully");
    }

    public void verifySave_Attendance() throws IOException, InterruptedException {

        check_validEntry();

        if (btnLateWithExcuse.isSelected()) {
            test.log(Status.PASS, "Attendance Saved Verified");
        }
        else
        {
            test.log(Status.FAIL,"Attendance not Saved successfully");
        }
    }

    public void update_Attendance() throws IOException, InterruptedException {
        ActionClass actionClass=new ActionClass(driver,test);
        VerificationClass verificationClass=new VerificationClass(driver,test);
        check_validEntry();

        if (btnLateWithExcuse.isSelected()) {
            driver.findElement(By.cssSelector(".radio:nth-child(3) > label")).click();
            verificationClass.verifyTextPresent(lblAttendanceSaved, "Attendance Saved Successfully");
            actionClass.captureScreen("Attendance Updated successfully");
        }
    }

    public void Mark_asHoliday() throws IOException, InterruptedException {
        ActionClass actionClass=new ActionClass(driver,test);
        VerificationClass verificationClass=new VerificationClass(driver,test);
        check_validEntry();

        //Click on Mark as Holiday
        actionClass.clickOnObject(btnMarkasHoliday);
        //Save attendance
        actionClass.clickOnObject(btnSaveAttendance);
        Thread.sleep(2000);
        verificationClass.verifyTextPresent(lblAttendanceSaved, "Attendance Saved Successfully");
        actionClass.captureScreen("Holiday Marked successfully");
    }

    public void Verify_Mark_asHoliday() throws IOException, InterruptedException {
        check_validEntry();
        ActionClass actionClass=new ActionClass(driver,test);
        VerificationClass verificationClass=new VerificationClass(driver,test);
        actionClass.captureScreen("Holiday Verified successfully");
        verificationClass.verifyTextPresent(lblHolidayAttendance,"Today is a Holiday. You Can Edit Record");
    }

}