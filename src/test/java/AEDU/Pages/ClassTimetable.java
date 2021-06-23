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
public class ClassTimetable {
    Connection conn = null;
    //Docker
    String url = "jdbc:mysql://localhost:6603/";
    //localDB
    //String url = "jdbc:mysql://localhost:3306/";
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
            using = "/html/body/div[1]/aside/div/section/ul[2]/li[3]/a/span")
    private WebElement SideMenuAcadamics;

    @FindBy(how = How.XPATH,
            using = "/html/body/div[1]/aside/div/section/ul[2]/li[3]/ul/li[1]/a")
    private WebElement ClassTimeTable;

    @FindBy(how = How.XPATH,
            using = "//*[@id=\"class_id\"]")
    private WebElement SelectClass;

    @FindBy(how = How.XPATH,
            using = "//*[@id=\"class_id\"]/option[2]")
    private WebElement SelectClassOption;

    @FindBy(how = How.XPATH,
            using = "//*[@id=\"section_id\"]")
    private WebElement SelectSection;

    @FindBy(how = How.XPATH,
            using = "//*[@id=\"section_id\"]/option[2]")
    private WebElement SelectSectionOption;

    @FindBy(how = How.XPATH,
            using = "/html/body/div[1]/div[1]/section[2]/div/div/div/form/div[2]/button")
    private WebElement SearchBtn;

    @FindBy(how = How.XPATH,
            using = "/html/body/div[1]/div[1]/section[2]/div/div/div/div/div/a")
    private WebElement AddBtn;

    @FindBy(how = How.XPATH,
            using = "//*[@id=\"subject_id\"]")
    private WebElement SubjectSelect;

    @FindBy(how = How.XPATH,
            using = "//*[@id=\"subject_id\"]/option[2]")
    private WebElement SubjectSelectOption;

    @FindBy(how = How.XPATH,
            using = "/html/body/div[1]/div[1]/section[2]/div/div/div/form/div[2]/button\n")
    private WebElement Savebtn1;

    @FindBy(how = How.XPATH,
            using = "//*[@id=\"stime_Monday_1\"]")
    private WebElement MondayStartTime;

    @FindBy(how = How.XPATH,
            using = "/html/body/div[1]/div[1]/section[2]/div/div/div[2]/div[2]/form/div/table/tbody/tr[1]/th[2]/div/div[1]/table/tbody/tr[1]/td[1]/a")
    private WebElement MondayStartTimeuparrow;

    @FindBy(how = How.XPATH,
            using = "/html/body/div[1]/div[1]/section[2]/div/div/div[2]/div[2]/form/div/table/tbody/tr[1]/th[2]/div/div[1]/table/tbody/tr[1]/td[3]/a/i")
    private WebElement MondayStartTimeuparrow2;

    @FindBy(how = How.XPATH,
            using = "//*[@id=\"etime_Monday_1\"]")
    private WebElement MondayEndTime;

    @FindBy(how = How.XPATH,
            using = "/html/body/div[1]/div[1]/section[2]/div/div/div[2]/div[2]/form/div/table/tbody/tr[1]/th[3]/div/div[1]/table/tbody/tr[1]/td[1]/a/i")
    private WebElement MondayEndTimearrow1;

    @FindBy(how = How.XPATH,
            using = "/html/body/div[1]/div[1]/section[2]/div/div/div[2]/div[2]/form/div/table/tbody/tr[1]/th[3]/div/div[1]/table/tbody/tr[1]/td[3]/a")
    private WebElement MondayEndTimearrow2;

    @FindBy(how = How.XPATH,
            using = "//*[@id=\"save_exam\"]")
    private WebElement Savebtn;



    @FindBy(how = How.XPATH,
            using = "//*[@id=\"stime_Monday_2\"]")

    private WebElement AddStartTime2Monday;

    @FindBy(how = How.XPATH,
            using = "//*[@id=\"btnAdd_Monday\"]")
    private WebElement AddAnotherShedulebtn;

    @FindBy(how = How.XPATH,
            using = "//*[@id=\"room_\"]")
    private WebElement RoomNumbnerfield;

    @FindBy(how = How.XPATH,
            using = "//*[@id=\"etime_Monday_2\"]")
    private WebElement EndTimeMonday2;

    @FindBy(how = How.XPATH,
            using = "/html/body/div[1]/div[1]/section[2]/div/div/div/form/div[1]/div[1]")
    private WebElement validationmessageForTeachet;

    @FindBy(how = How.XPATH,
            using = "//*[@id=\"class_id\"]/option[2]")
    private WebElement SewlectClassOption2;

    @FindBy(how = How.XPATH,
            using = "//*[@id=\"section_id\"]/option[2]")
    private WebElement SelectSectionOption2;

    @FindBy(how = How.XPATH,
            using ="//*[@id=\"subject_id\"]/option[3]")
    private WebElement SelectSubjectOption2;

    @FindBy(how = How.XPATH,
            using ="/html/body/div[1]/div[1]/section[2]/div/div/div/form/div[1]/div/div[1]/div/span/p")
    private WebElement ClassFieldValidationMessage;

    @FindBy(how = How.XPATH,
            using ="/html/body/div[1]/div[1]/section[2]/div/div/div/form/div[1]/div/div[2]/div/span/p")
    private WebElement SectionFieldValidationMessage;

    @FindBy(how = How.XPATH,
            using ="/html/body/div[1]/div[1]/section[2]/div/div/div/form/div[1]/div/div[3]/div/span/p")
    private WebElement SubjectFieldValidationMessage;

    @FindBy(how = How.XPATH,
            using ="/html/body/div[1]/div[1]/section[2]/div/div/div[2]/div[2]/form/div/table/tbody/tr[1]/th[3]/div/div[2]/div[1]/span[1]")
    private WebElement EndtimeFieldValidation;

    @FindBy(how = How.XPATH,
            using ="/html/body/div[1]/div[1]/section[2]/div/div/div[2]/div[2]/form/div/table/tbody/tr[2]/th[2]/div/div[2]/div/span[1]")
    private WebElement StartTimeFieldValidation;

    @FindBy(how = How.XPATH,
            using ="//*[@id=\"class_id\"]/option[5]")
    private WebElement SelectClass3;

    @FindBy(how = How.XPATH,
            using ="//*[@id=\"section_id\"]/option[2]")
    private WebElement SelectSection3;

    public ClassTimetable(WebDriver driver, ExtentTest test) {
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
    public void ChecktheTimetablewithDatabase() throws IOException, InterruptedException, SQLException {
        ActionClass actionClass = new ActionClass(driver,extentTest);
        actionClass.clickOnObject(this.SideMenuAcadamics);
        Thread.sleep(1000);
        actionClass.clickOnObject(this.ClassTimeTable);
        actionClass.clickOnObject(this.SelectClass);
        actionClass.clickOnObject(this.SelectClass3);
        Thread.sleep(1000);
        String subject = "Hindi";
        String Teachername = "Ronak";
        String teachername;
        actionClass.clickOnObject(this.SelectSection);
        actionClass.clickOnObject(this.SelectSection3);
        actionClass.clickOnObject(this.SearchBtn);
        JavascriptExecutor jsetaskscore = (JavascriptExecutor) driver;
        jsetaskscore.executeScript("scrollBy(0,350)");
        actionClass.captureScreen("Class-Timetable");
        String s = driver.findElement(By.xpath("/html/body/div[1]/div[1]/section[2]/div/div/div[2]/div[2]/div[2]/div[2]/table/tbody/tr/td[1]/div/strong[1]")).getText();
        System.out.println("Start time is: " + s);
        String s1 = driver.findElement(By.xpath("/html/body/div[1]/div[1]/section[2]/div/div/div[2]/div[2]/div[2]/div[2]/table/tbody/tr/td[1]/div/strong[2]")).getText();
        System.out.println("End time is: " + s1);
        String s2 = driver.findElement(By.xpath("/html/body/div[1]/div[1]/section[2]/div/div/div[2]/div[2]/div[2]/div[2]/table/tbody/tr/th")).getText();
        System.out.println("Subject  is: " + s2);
        String s3 = driver.findElement(By.xpath("/html/body/div[1]/div[1]/section[2]/div/div/div[2]/div[2]/div[2]/div[2]/table/thead/tr/th[2]")).getText();
        System.out.println("Day is: " + s3);
        DatabaseFunctions DAB = new DatabaseFunctions(extentTest);
        conn = DAB.connect();
        statement = conn.createStatement();
        String TimeTable = "SELECT timetables.* , teacher_subjects.id, subjects.name FROM timetables JOIN teacher_subjects ON teacher_subjects.id=teacher_subject_id JOIN subjects ON subjects.id=teacher_subjects.subject_id JOIN teachers ON teachers.id= teacher_subjects.teacher_id WHERE subjects.name='" + subject + "' AND timetables.day_name='Monday' AND teachers.name='" + Teachername + "'";
        ResultSet queryRs1 = statement.executeQuery(TimeTable);
        String a = null;
        String a1 = null;
        String a2 = null;
        String a3 = null;
        ArrayList listNames = new ArrayList();

        while (queryRs1.next()) {
            a = queryRs1.getString("start_time");
            a1 = queryRs1.getString("end_time");
            a2 = queryRs1.getString("name");
            a3 = queryRs1.getString("day_name");

            listNames.add(s1);
        }

 actionClass.CompareListandstring(a,s);
 actionClass.CompareListandstring(a1,s1);
 actionClass.CompareListandstring(a2,s2);
actionClass.CompareListandstring(a3,s3);

    }

    public void AddTimeTable(String StartTime,String EndTime)throws IOException,InterruptedException{
        ActionClass actionClass = new ActionClass(driver,extentTest);
//        actionClass.clickOnObject(this.SideMenuAcadamics);
      Thread.sleep(5000);
        actionClass.clickOnObject(this.ClassTimeTable);
        actionClass.clickOnObject(this.AddBtn);
        actionClass.clickOnObject(this.SelectClass);
        actionClass.clickOnObject(this.SelectClassOption);
        actionClass.clickOnObject(this.SelectSection);
        actionClass.clickOnObject(this.SelectSectionOption);
        actionClass.clickOnObject(this.SubjectSelect);
        Thread.sleep(1000);
        actionClass.clickOnObject(this.SubjectSelectOption);
        actionClass.clickOnObject(this.Savebtn1);
        JavascriptExecutor jsetaskscore1 = (JavascriptExecutor) driver;
        jsetaskscore1.executeScript("scrollBy(0, 300)");
        actionClass.clickOnObject(this.MondayStartTime);
        actionClass.setValueinTextbox(this.MondayStartTime,StartTime);
        Thread.sleep(1000);
        actionClass.clickOnObject(this.MondayEndTime);
        actionClass.setValueinTextbox(this.MondayEndTime,EndTime);
        Thread.sleep(1000);
        actionClass.clickOnObject(this.RoomNumbnerfield);
        JavascriptExecutor jsetaskscore = (JavascriptExecutor) driver;
        jsetaskscore.executeScript("scrollBy(0, 500)");
        actionClass.clickOnObject(this.Savebtn);
    }
    public void CheckifTeacherisOccupied(String StartTime,String Endtime,String Rommnb)throws IOException,InterruptedException{
        ActionClass actionClass = new ActionClass(driver,extentTest);
//       actionClass.clickOnObject(this.SideMenuAcadamics);
        Thread.sleep(5000);
        actionClass.clickOnObject(this.ClassTimeTable);
        actionClass.clickOnObject(this.AddBtn);
        actionClass.clickOnObject(this.SelectClass);
        actionClass.clickOnObject(this.SewlectClassOption2);
        actionClass.clickOnObject(this.SelectSection);
        actionClass.clickOnObject(this.SelectSectionOption2);
        actionClass.clickOnObject(this.SubjectSelect);
        actionClass.clickOnObject(this.SubjectSelectOption);
        actionClass.clickOnObject(this.Savebtn1);
        JavascriptExecutor jsetaskscore1 = (JavascriptExecutor) driver;
        jsetaskscore1.executeScript("scrollBy(0, 300)");
        actionClass.clickOnObject(this.MondayStartTime);
        actionClass.setValueinTextbox(this.MondayStartTime,StartTime);
        actionClass.clickOnObject(this.MondayEndTime);
        actionClass.setValueinTextbox(this.MondayEndTime,Endtime);
        actionClass.clickOnObject(this.RoomNumbnerfield);
        actionClass.setValueinTextbox(this.RoomNumbnerfield,Rommnb);
        Thread.sleep(1000);
        actionClass.clickOnObject(this.AddAnotherShedulebtn);
        actionClass.clickOnObject(this.AddStartTime2Monday);
        actionClass.clickOnObject(this.AddStartTime2Monday);
        actionClass.setValueinTextbox(this.AddStartTime2Monday,StartTime);
        actionClass.clickOnObject(this.EndTimeMonday2);
        actionClass.setValueinTextbox(this.EndTimeMonday2,Endtime);
        actionClass.clickOnObject(this.RoomNumbnerfield);
        actionClass.setValueinTextbox(this.RoomNumbnerfield,Rommnb);
        JavascriptExecutor jsetaskscore = (JavascriptExecutor) driver;
        jsetaskscore.executeScript("scrollBy(0, 600)");
        actionClass.clickOnObject(this.Savebtn);
        Thread.sleep(1000);
        VerificationClass very =  new  VerificationClass(driver,extentTest);
        very.verifyTextPresent(this.validationmessageForTeachet,"Teacher is already occupied. Please enter another time.");
        actionClass.captureScreen("Theacher is already occupaid.");
    }

    public void CheckIfTimeISOccupied(String StartTime,String Endtime,String Rommnb)throws IOException,InterruptedException{
        ActionClass actionClass = new ActionClass(driver,extentTest);
//     actionClass.clickOnObject(this.SideMenuAcadamics);
        Thread.sleep(5000);
        actionClass.clickOnObject(this.ClassTimeTable);
        actionClass.clickOnObject(this.AddBtn);
        actionClass.clickOnObject(this.SelectClass);
        actionClass.clickOnObject(this.SelectClassOption);
        actionClass.clickOnObject(this.SelectSection);
        actionClass.clickOnObject(this.SelectSectionOption);
        Thread.sleep(1000);
        actionClass.clickOnObject(this.SubjectSelect);
        actionClass.clickOnObject(this.SelectSubjectOption2);
        actionClass.clickOnObject(this.Savebtn1);
        actionClass.clickOnObject(this.MondayStartTime);
        actionClass.setValueinTextbox(this.MondayStartTime,StartTime);
        actionClass.clickOnObject(this.MondayEndTime);
        actionClass.setValueinTextbox(this.MondayEndTime,Endtime);
        actionClass.clickOnObject(this.RoomNumbnerfield);
        actionClass.setValueinTextbox(this.RoomNumbnerfield,Rommnb);
        Thread.sleep(1000);
        actionClass.clickOnObject(this.AddAnotherShedulebtn);
        actionClass.clickOnObject(this.AddStartTime2Monday);
        actionClass.clickOnObject(this.AddStartTime2Monday);
        actionClass.setValueinTextbox(this.AddStartTime2Monday,StartTime);
        actionClass.clickOnObject(this.EndTimeMonday2);
        actionClass.setValueinTextbox(this.EndTimeMonday2,Endtime);
        actionClass.clickOnObject(this.RoomNumbnerfield);
        actionClass.setValueinTextbox(this.RoomNumbnerfield,Rommnb);
        JavascriptExecutor jsetaskscore = (JavascriptExecutor) driver;
        jsetaskscore.executeScript("scrollBy(0, 600)");
        actionClass.clickOnObject(this.Savebtn);
        VerificationClass very =  new  VerificationClass(driver,extentTest);
        very.verifyTextPresent(this.validationmessageForTeachet,"Time is already occupied. Please enter another time.");
        actionClass.captureScreen("Time is already occupaid.");

    }
    public void CheckBasicValidations (String StartTime,String Endtime,String Rommnb,String Endtime2)throws IOException,InterruptedException{
        ActionClass actionClass = new ActionClass(driver,extentTest);
//       actionClass.clickOnObject(this.SideMenuAcadamics);
        Thread.sleep(5000);
        actionClass.clickOnObject(this.ClassTimeTable);
        actionClass.clickOnObject(this.AddBtn);
        actionClass.clickOnObject(this.Savebtn1);
        VerificationClass very = new VerificationClass(driver,extentTest);
        very.verifyTextPresent(this.ClassFieldValidationMessage,"The Class field is required.");
        very.verifyTextPresent(this.SectionFieldValidationMessage,"The Section field is required.");
        very.verifyTextPresent(this.SubjectFieldValidationMessage,"The Subject field is required.");
        actionClass.captureScreen("Filter validation");
        actionClass.clickOnObject(this.ClassTimeTable);
        actionClass.clickOnObject(this.AddBtn);
        actionClass.clickOnObject(this.SelectClass);
        actionClass.clickOnObject(this.SelectClassOption);
        actionClass.clickOnObject(this.SelectSection);
        actionClass.clickOnObject(this.SelectSectionOption);
        Thread.sleep(1000);
        actionClass.clickOnObject(this.SubjectSelect);
        actionClass.clickOnObject(this.SelectSubjectOption2);
        actionClass.clickOnObject(this.Savebtn1);
        actionClass.clickOnObject(this.SubjectSelect);
        actionClass.clickOnObject(this.SelectSubjectOption2);
        actionClass.clickOnObject(this.Savebtn1);
        actionClass.clickOnObject(this.MondayStartTime);
        actionClass.setValueinTextbox(this.MondayStartTime,StartTime);
        actionClass.clickOnObject(this.MondayEndTime);
        actionClass.setValueinTextbox(this.MondayEndTime,Endtime);
        actionClass.clickOnObject(this.RoomNumbnerfield);
        actionClass.setValueinTextbox(this.RoomNumbnerfield,Rommnb);
        JavascriptExecutor jsetaskscore1 = (JavascriptExecutor) driver;
        jsetaskscore1.executeScript("scrollBy(0, 300)");
        very.verifyTextPresent(this.EndtimeFieldValidation,"End time must be larger than Start time");
        actionClass.captureScreen("EndTime field validation");
        actionClass.clickOnObject(this.MondayEndTime);
        actionClass.setValueinTextbox(this.MondayEndTime,Endtime2);
        actionClass.clickOnObject(this.RoomNumbnerfield);
        actionClass.clickOnObject(this.AddAnotherShedulebtn);
        actionClass.clickOnObject(this.AddStartTime2Monday);
        actionClass.setValueinTextbox(AddStartTime2Monday,StartTime);
        actionClass.clickOnObject(this.EndTimeMonday2);
        very.verifyTextPresent(this.StartTimeFieldValidation,"Start time should be greater than previous end time.");
        actionClass.captureScreen("Start-time field validation");
    }
}