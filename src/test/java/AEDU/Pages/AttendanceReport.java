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
import java.awt.*;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class AttendanceReport {
    WebDriver driver1;
    ExtentTest extentTest;
    Connection conn = null;
    String url = "jdbc:mysql://localhost:3306/";
    String dbName = "aedudev1";
    String driver = "com.mysql.jdbc.Driver";
    String userName = "root";
    String password = "root";
    Statement statement;
    ResultSet queryRs;

    @FindBy(how = How.XPATH, using = "//*[@id=\"sibe-box\"]/ul[2]/li[4]/a")
    private WebElement ClickOnAttendence;

    @FindBy(how = How.XPATH, using = "//*[@id=\"sibe-box\"]/ul[2]/li[4]/ul/li[3]/a")
    private WebElement ClickOnAttendenceReport;

    @FindBy(how = How.XPATH, using = "//*[@id=\"form1\"]/div[2]/button")
    private WebElement ClickOnSearch;

    @FindBy(how = How.XPATH, using = "//*[@id=\"form1\"]/div[1]/div/div[1]/div/span/p")
    private WebElement ClassValidation;

    @FindBy(how = How.XPATH, using = "//*[@id=\"form1\"]/div[1]/div/div[2]/div/span/p")
    private WebElement SectionValidation;

    @FindBy(how = How.XPATH, using = "//*[@id=\"form1\"]/div[1]/div/div[3]/div/span/p")
    private WebElement MonthValidation;

    @FindBy(how = How.XPATH, using = "//*[@id=\"class_id\"]")
    private WebElement ClassField;

    @FindBy(how = How.XPATH, using = "//*[@id=\"class_id\"]/option[17]")
    private WebElement SelectClassField;

    @FindBy(how = How.XPATH, using = "//*[@id=\"section_id\"]")
    private WebElement SectionField;

    @FindBy(how = How.XPATH, using = "//*[@id=\"section_id\"]/option[2]")
    private WebElement SelectSectionField;

    @FindBy(how = How.XPATH, using = "//*[@id=\"month\"]")
    private WebElement MonthField;

    @FindBy(how = How.XPATH, using = "//*[@id=\"month\"]/option[4]")
    private WebElement SelectMonthField;

    @FindBy(how = How.XPATH, using = "//*[@id=\"status\"]")
    private WebElement StatusField;

    @FindBy(how = How.XPATH, using = "//*[@id=\"status\"]/option[2]")
    private WebElement SelectStatusInactive;

    public AttendanceReport(WebDriver driver, ExtentTest test) throws InterruptedException, IOException {
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

    public void AttendenceValidation() throws InterruptedException, IOException {
        ActionClass actionClass = new ActionClass(driver1, extentTest);
        //actionClass.clickOnObject(ClickOnAttendence);
        Thread.sleep(3000);
        actionClass.clickOnObject(ClickOnAttendenceReport);
        Thread.sleep(3000);
        actionClass.clickOnObject(ClickOnSearch);
        Thread.sleep(3000);
        actionClass.captureScreen("Attendence_Report_1");
        VerificationClass verificationClass = new VerificationClass(driver1, extentTest);
        verificationClass.verifyTextPresent(ClassValidation, "The Class field is required.");
        verificationClass.verifyTextPresent(SectionValidation, "The Section field is required.");
        verificationClass.verifyTextPresent(MonthValidation, "The Month field is required.");
    }

//  Test if active student attendance matches with database.
    public void AttendenceReportofActive(String classname, String sectionname, String start_date, String end_date) throws InterruptedException, IOException, SQLException, AWTException {
        ActionClass actionClass = new ActionClass(driver1, extentTest);
     // actionClass.clickOnObject(ClickOnAttendence);
        Thread.sleep(3000);
        actionClass.clickOnObject(ClickOnAttendenceReport);
        Thread.sleep(3000);
        actionClass.clickOnObject(ClassField);
        Thread.sleep(2000);
        actionClass.clickOnObject(SelectClassField);
        Thread.sleep(2000);
        actionClass.clickOnObject(SectionField);
        Thread.sleep(2000);
        actionClass.clickOnObject(SelectSectionField);
        Thread.sleep(2000);
        actionClass.clickOnObject(MonthField);
        Thread.sleep(2000);
        actionClass.clickOnObject(SelectMonthField);
        Thread.sleep(2000);
        actionClass.captureScreen("Attendence_Report_1");
        actionClass.clickOnObject(ClickOnSearch);
        Thread.sleep(2000);
        JavascriptExecutor jsetaskscore = (JavascriptExecutor) driver1;
        jsetaskscore.executeScript("scrollBy(0, 600)");
        Thread.sleep(3000);
        actionClass.captureScreen("Attendance_Report_3");
        WebElement date = driver1.findElement(By.xpath("//*[@id=\"DataTables_Table_0\"]/thead/tr/th[32]"));
        jsetaskscore.executeScript("arguments[0].scrollIntoView();",date);
        Thread.sleep(3000);
        actionClass.captureScreen("Attendance_Report_4");
        Thread.sleep(3000);

//      List of students in front in attendance report
        Boolean isPresent = driver1.findElements(By.xpath("//*[@id=\"DataTables_Table_0\"]/tbody/tr[1]/th[1]")).size() > 0;
        ArrayList<String> ListStudentNameinFront = new ArrayList<String>();
        if(isPresent==true){
            List<WebElement> ListSubject = driver1.findElements(By.xpath("//*[@id=\"DataTables_Table_0\"]/tbody/tr"));
            Thread.sleep(3000);
            int listsize = ListSubject.size();
            for (int i = 1; i <= listsize; i++) {
                ListStudentNameinFront.add(driver1.findElement(By.xpath("//*[@id=\"DataTables_Table_0\"]/tbody/tr["+i+"]/th[1]")).getText());
            }
            Collections.sort(ListStudentNameinFront);
            System.out.println(ListStudentNameinFront);
        }else{
            System.out.println("No record found");
            extentTest.log(Status.INFO,"No record found");
        }
        Thread.sleep(2000);

//      The List of students in attendance report Database
        DatabaseFunctions DAB = new DatabaseFunctions(extentTest);
        conn = DAB.connect();
        statement = conn.createStatement();
        String students = "SELECT\n" +
                "    students.firstname,\n" +
                "    students.lastname,\n" +
                "    student_session.class_id,\n" +
                "    student_session.session_id\n" +
                "FROM\n" +
                "    `student_session`\n" +
                "JOIN students ON students.id = student_session.student_id\n" +
                "INNER JOIN classes ON student_session.class_id=classes.id \n" +
                "INNER JOIN sections ON student_session.section_id=sections.id \n" +
                "WHERE (classes.class ='"+classname+"' AND sections.section ='"+sectionname+"' AND student_session.session_id = 15 AND student_session.is_inactive='no')";
        queryRs = statement.executeQuery(students);
        ArrayList<String> studentNamesListinDB = new ArrayList<String>();
        while (queryRs.next()){
            String firstname= queryRs.getString("students.firstname");
            String lastname = queryRs.getString("students.lastname");
            studentNamesListinDB.add(firstname+" "+lastname);
        }
        Collections.sort(studentNamesListinDB);
        System.out.println(studentNamesListinDB);
        extentTest.log(Status.PASS, "The entered data is in database");

//      Compare both front and database students lists
        if(studentNamesListinDB.equals(ListStudentNameinFront)){
            System.out.println("The students in report matches with database");
            extentTest.log(Status.PASS,"The students in report matches with database");
        }else{
            System.out.println("The students in report does not match with database");
            extentTest.log(Status.FAIL,"The students in report does not match with database");
        }
        ;

//      The List of attendance in report
        statement = conn.createStatement();
        String report = "SELECT\n" +
                "    students.firstname,\n" +
                "    students.lastname,\n" +
                "    attendence_type.key_value,\n" +
                "    student_session.class_id,\n" +
                "    student_session.session_id,\n" +
                "    student_attendences.date\n" +
                "FROM\n" +
                "    `student_session`\n" +
                "JOIN students ON students.id = student_session.student_id\n" +
                "LEFT JOIN student_attendences ON student_attendences.student_session_id = student_session.student_id\n" +
                "LEFT JOIN attendence_type ON attendence_type.id = student_attendences.attendence_type_id\n" +
                "INNER JOIN classes ON student_session.class_id=classes.id \n" +
                "INNER JOIN sections ON student_session.section_id=sections.id \n" +
                "WHERE (classes.class ='"+classname+"' AND sections.section ='"+sectionname+"' AND student_session.session_id = 15 AND student_session.is_inactive='no') AND (student_attendences.date>='"+start_date+"' AND student_attendences.date<='"+end_date+"')";
        queryRs = statement.executeQuery(report);
        ArrayList<String> AttendanceListinDB = new ArrayList<String>();
        while (queryRs.next()){
            String attendenceNames = null;
            attendenceNames=queryRs.getString("attendence_type.key_value");
            String trimattendenceName = attendenceNames.replaceAll("\\<.*?\\>", "");
            AttendanceListinDB.add(trimattendenceName);
        }
        Collections.sort(AttendanceListinDB);
        System.out.println(AttendanceListinDB);

//      List of Attendance in front
        Boolean isPresent1 = driver1.findElements(By.xpath("//*[@id=\"DataTables_Table_0\"]/tbody/tr[1]/th[1]")).size() > 0;
        ArrayList<String> ListAttendanceinFront = new ArrayList<String>();
        ArrayList<String> ListStudentAttendanceinFront =new ArrayList<>();
        if(isPresent1==true){
            List<WebElement> ListAttendance = driver1.findElements(By.xpath("//*[@id=\"DataTables_Table_0\"]/tbody/tr"));
            Thread.sleep(3000);
            int listsize = ListAttendance.size();
            for (int i = 1; i <= listsize; i++) {
                List<WebElement> ListAttendance1 = driver1.findElements(By.xpath("//*[@id=\"DataTables_Table_0\"]/tbody/tr["+i+"]/th"));
                int columnsize=ListAttendance1.size();
                for(int j=2; j<=columnsize; j++){
                    ListStudentAttendanceinFront.add(driver1.findElement(By.xpath("//*[@id=\"DataTables_Table_0\"]/tbody/tr["+i+"]/th["+j+"]")).getText());
                }
            }
            Collections.sort(ListStudentAttendanceinFront);
            System.out.println(ListStudentAttendanceinFront);
        }else{
            System.out.println("No record found");
        }
        Collections.sort(ListStudentAttendanceinFront);


//      Compare both front and database attendance lists
        if(ListStudentAttendanceinFront.equals(AttendanceListinDB)){
            System.out.println("The attendance in front for all students matches with database");
            extentTest.log(Status.PASS,"The attendance in front for all students matches with database");
        }else{
            System.out.println("The attendance in front for all students does not match with database");
            extentTest.log(Status.FAIL,"The attendance in front for all students does not match with database");
        }
    }

//  Test if inactive student attendance matches with database.
    public void AttendenceReportofInActive(String classname, String sectionname, String start_date, String end_date) throws InterruptedException, IOException, SQLException, AWTException {
        ActionClass actionClass = new ActionClass(driver1, extentTest);
      //actionClass.clickOnObject(ClickOnAttendence);
        Thread.sleep(3000);
        actionClass.clickOnObject(ClickOnAttendenceReport);
        Thread.sleep(3000);
        actionClass.clickOnObject(ClassField);
        Thread.sleep(2000);
        actionClass.clickOnObject(SelectClassField);
        Thread.sleep(2000);
        actionClass.clickOnObject(SectionField);
        Thread.sleep(2000);
        actionClass.clickOnObject(SelectSectionField);
        Thread.sleep(2000);
        actionClass.clickOnObject(MonthField);
        Thread.sleep(2000);
        actionClass.clickOnObject(SelectMonthField);
        Thread.sleep(2000);
        actionClass.clickOnObject(StatusField);
        Thread.sleep(2000);
        actionClass.clickOnObject(SelectStatusInactive);
        Thread.sleep(2000);
        actionClass.clickOnObject(ClickOnSearch);
        Thread.sleep(2000);
        actionClass.captureScreen("Attendance_Report_5");
        JavascriptExecutor jsetaskscore = (JavascriptExecutor) driver1;
        jsetaskscore.executeScript("scrollBy(0, 600)");
        Thread.sleep(3000);
        actionClass.captureScreen("Attendance_Report_6");
        WebElement date = driver1.findElement(By.xpath("//*[@id=\"DataTables_Table_0\"]/thead/tr/th[32]"));
        jsetaskscore.executeScript("arguments[0].scrollIntoView();",date);
        Thread.sleep(3000);
        actionClass.captureScreen("Attendance_Report_7");
        Thread.sleep(3000);


//      List of students in front in attendance report
        Boolean isPresent = driver1.findElements(By.xpath("//*[@id=\"DataTables_Table_0\"]/tbody/tr[1]/th[1]")).size() > 0;
        ArrayList<String> ListStudentNameinFront = new ArrayList<String>();
        if(isPresent==true){
            List<WebElement> ListSubject = driver1.findElements(By.xpath("//*[@id=\"DataTables_Table_0\"]/tbody/tr"));
            Thread.sleep(3000);
            int listsize = ListSubject.size();
            for (int i = 1; i <= listsize; i++) {
                ListStudentNameinFront.add(driver1.findElement(By.xpath("//*[@id=\"DataTables_Table_0\"]/tbody/tr["+i+"]/th[1]")).getText());
            }
            Collections.sort(ListStudentNameinFront);
            System.out.println(ListStudentNameinFront);
        }else{
            System.out.println("No record found");
        }
        Thread.sleep(2000);

//      The List of students in attendance report Database
        DatabaseFunctions DAB = new DatabaseFunctions(extentTest);
        conn = DAB.connect();
        statement = conn.createStatement();
        String students = "SELECT\n" +
                "    students.firstname,\n" +
                "    students.lastname,\n" +
                "    student_session.class_id,\n" +
                "    student_session.session_id\n" +
                "FROM\n" +
                "    `student_session`\n" +
                "JOIN students ON students.id = student_session.student_id\n" +
                "INNER JOIN classes ON student_session.class_id=classes.id \n" +
                "INNER JOIN sections ON student_session.section_id=sections.id \n" +
                "WHERE (classes.class ='"+classname+"' AND sections.section ='"+sectionname+"' AND student_session.session_id = 15 AND student_session.is_inactive='yes')";
        queryRs = statement.executeQuery(students);
        ArrayList<String> studentNamesListinDB = new ArrayList<String>();
        while (queryRs.next()){
            String firstname= queryRs.getString("students.firstname");
            String lastname = queryRs.getString("students.lastname");
            studentNamesListinDB.add(firstname+" "+lastname);
        }
        Collections.sort(studentNamesListinDB);
        System.out.println(studentNamesListinDB);
        extentTest.log(Status.PASS, "The entered data is in database");

//      Compare both front and database students lists
        if(ListStudentNameinFront.equals(studentNamesListinDB)){
            System.out.println("The students in report matches with database");
            extentTest.log(Status.PASS,"The students in report matches with database");
        }else{
            System.out.println("The students in report does not match with database");
            extentTest.log(Status.FAIL,"The students in report does not match with database");
        }

//      The List of attendance in report
        statement = conn.createStatement();
        String report = "SELECT\n" +
                "    students.firstname,\n" +
                "    students.lastname,\n" +
                "    attendence_type.key_value,\n" +
                "    student_session.class_id,\n" +
                "    student_session.session_id,\n" +
                "    student_attendences.date\n" +
                "FROM\n" +
                "    `student_session`\n" +
                "JOIN students ON students.id = student_session.student_id\n" +
                "LEFT JOIN student_attendences ON student_attendences.student_session_id = student_session.student_id\n" +
                "LEFT JOIN attendence_type ON attendence_type.id = student_attendences.attendence_type_id\n" +
                "INNER JOIN classes ON student_session.class_id=classes.id \n" +
                "INNER JOIN sections ON student_session.section_id=sections.id \n" +
                "WHERE (classes.class ='"+classname+"' AND sections.section ='"+sectionname+"' AND student_session.session_id = 15 AND student_session.is_inactive='yes') AND (student_attendences.date>='"+start_date+"' AND student_attendences.date<='"+end_date+"')";
        queryRs = statement.executeQuery(report);
        ArrayList<String> AttendanceListinDB = new ArrayList<String>();
        while (queryRs.next()){
            String attendenceNames = null;
            attendenceNames=queryRs.getString("attendence_type.key_value");
            String trimattendenceName = attendenceNames.replaceAll("\\<.*?\\>", "");
            AttendanceListinDB.add(trimattendenceName);
        }
        Collections.sort(AttendanceListinDB);
        System.out.println(AttendanceListinDB);
        extentTest.log(Status.PASS, "The entered data is in database");

//      List of Attendance in front
        Boolean isPresent1 = driver1.findElements(By.xpath("//*[@id=\"DataTables_Table_0\"]/tbody/tr[1]/th[1]")).size() > 0;
        ArrayList<String> ListStudentAttendanceinFront =new ArrayList<>();
        if(isPresent1==true){
            List<WebElement> ListAttendance = driver1.findElements(By.xpath("//*[@id=\"DataTables_Table_0\"]/tbody/tr"));
            Thread.sleep(3000);
            int listsize = ListAttendance.size();
            for (int i = 1; i <= listsize; i++) {
                List<WebElement> ListAttendance1 = driver1.findElements(By.xpath("//*[@id=\"DataTables_Table_0\"]/tbody/tr["+i+"]/th"));
                int columnsize=ListAttendance1.size();
                for(int j=2; j<=columnsize; j++){
                    ListStudentAttendanceinFront.add(driver1.findElement(By.xpath("//*[@id=\"DataTables_Table_0\"]/tbody/tr["+i+"]/th["+j+"]")).getText());
                }
            }
            Collections.sort(ListStudentAttendanceinFront);
            System.out.println(ListStudentAttendanceinFront);
        }else{
            System.out.println("No record found");
        }

        Thread.sleep(2000);

//      Compare both front and database attendance lists
        if(ListStudentAttendanceinFront.equals(AttendanceListinDB)){
            System.out.println("The attendance in front for all students matches with database");
            extentTest.log(Status.PASS,"The attendance in front for all students matches with database");
        }else{
            System.out.println("The attendance in front for all students does not match with database");
            extentTest.log(Status.FAIL,"The attendance in front for all students does not match with database");
        }
    }
}