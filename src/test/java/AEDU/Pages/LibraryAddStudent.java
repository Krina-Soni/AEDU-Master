package AEDU.Pages;
import AEDU.Actions.ActionClass;
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

public class LibraryAddStudent {
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

    @FindBy(how = How.XPATH, using = "//*[@id=\"sibe-box\"]/ul[2]/li[10]/a")
    private WebElement ClickOnLibraryMenu;

    @FindBy(how = How.XPATH, using = "//*[@id=\"sibe-box\"]/ul[2]/li[10]/ul/li[4]/a")
    private WebElement ClickOnLibraryAddStudent;

    @FindBy(how = How.XPATH, using = "//*[@id=\"sibe-box\"]/ul[2]/li[10]/ul/li[6]/a")
    private WebElement ClickOnLibrarians;

    @FindBy(how = How.XPATH, using = "/html/body/div[1]/div[1]/section[2]/div/div/div/div[2]/div/div/form/div[3]/div/button")
    private WebElement ClickOnSearch;

    @FindBy(how = How.XPATH, using = "//*[@id=\"DataTables_Table_0_filter\"]/label/input")
    private WebElement SearchField;

    @FindBy(how = How.XPATH, using = "//*[@id=\"DataTables_Table_0\"]/tbody/tr/td[10]/button")
    private WebElement ClickOnAdd;

    @FindBy(how = How.XPATH, using = "//*[@id=\"library_card_no\"]")
    private WebElement LibraryCarNumberField;

    @FindBy(how = How.XPATH, using = "//*[@id=\"add_member\"]/button")
    private WebElement LibraryCarNumberAddButton;

    @FindBy(how = How.XPATH, using = "//*[@id=\"DataTables_Table_0\"]/tbody/tr/td[10]/button")
    private WebElement SurrenderMembershipButton;

    public LibraryAddStudent(WebDriver driver, ExtentTest test) throws InterruptedException, IOException {
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

    //  Compare students in the list of selected class, assign library card number
    public void AddStudent(String classname, String sectionname, String StudentName, String LibraryCardNumber, String Session) throws InterruptedException, IOException, SQLException {
        ActionClass actionClass = new ActionClass(driver1, extentTest);
        WebElement view = driver1.findElement(By.xpath("//*[@id=\"sibe-box\"]/ul[2]/li[6]/a"));
        JavascriptExecutor javascriptExecutor = (JavascriptExecutor) driver1;
        javascriptExecutor.executeScript("arguments[0].scrollIntoView();",view);
        //actionClass.clickOnObject(ClickOnLibraryMenu);
        Thread.sleep(2000);
        actionClass.clickOnObject(ClickOnLibraryAddStudent);
        Thread.sleep(2000);
        WebElement SelectClass = driver1.findElement(By.xpath("//*[@name='class_id']//option[contains(text(),'"+classname+"')]"));
        actionClass.clickOnObject(SelectClass);
        Thread.sleep(2000);
        WebElement SelectSection = driver1.findElement(By.xpath("//*[@name='section_id']//option[contains(text(),'"+sectionname+"')]"));
        actionClass.clickOnObject(SelectSection);
        Thread.sleep(2000);
        actionClass.captureScreen("LibraryAddStudent_1");
        actionClass.clickOnObject(ClickOnSearch);
        WebElement view1 = driver1.findElement(By.xpath("//*[contains(text(),'Records')]"));
        javascriptExecutor.executeScript("arguments[0].scrollIntoView();",view1);
        Thread.sleep(1000);
        actionClass.captureScreen("LibraryAddStudent_2");

//      Compare the students listed with the database
        Boolean isPresent = driver1.findElements(By.xpath("//*[@id=\"DataTables_Table_0\"]/tbody/tr[1]")).size() > 0;
        ArrayList<String> ListStudentNameinFront = new ArrayList<String>();
        if(isPresent==true){
            List<WebElement> ListSubject = driver1.findElements(By.xpath("//*[@id=\"DataTables_Table_0\"]/tbody/tr"));
            Thread.sleep(3000);
            int listsize = ListSubject.size();
            for (int i = 1; i <= listsize; i++) {
                String s = driver1.findElement(By.xpath("//*[@id=\"DataTables_Table_0\"]/tbody/tr["+i+"]/td[4]")).getText();
                ListStudentNameinFront.add(s.trim());
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
                "    students.lastname\n" +
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
            studentNamesListinDB.add(firstname.trim()+" "+lastname.trim());
        }
        Collections.sort(studentNamesListinDB);
        System.out.println(studentNamesListinDB);
        extentTest.log(Status.PASS, "The entered data is in database");
//      Compare both front and database students lists
        if(studentNamesListinDB.equals(ListStudentNameinFront)){
            System.out.println("The students in list matches with database");
            extentTest.log(Status.PASS,"The students in list matches with database");
        }else{
            System.out.println("The students in list does not match with database");
            extentTest.log(Status.FAIL,"The students in list does not match with database");
        }
        Thread.sleep(2000);
//      Compare after assigning library card number with database
        actionClass.setValueinTextbox(SearchField, StudentName);
        Thread.sleep(2000);
        actionClass.captureScreen("LibraryAddStudent_3");
        Boolean isPresent1 = driver1.findElements(By.xpath("//td[contains(text(),'"+StudentName+"')]")).size() > 0;
        if(isPresent1==true){
            actionClass.clickOnObject(ClickOnAdd);
            Thread.sleep(2000);
            actionClass.setValueinTextbox(LibraryCarNumberField, LibraryCardNumber);
            Thread.sleep(2000);
            actionClass.clickOnObject(LibraryCarNumberAddButton);
            Thread.sleep(3000);
            actionClass.captureScreen("LibraryAddStudent_4");
            Thread.sleep(2000);
            WebElement element = driver1.findElement(By.xpath("//*[@id=\"DataTables_Table_0\"]/tbody/tr"));
            String successclassname= element.getAttribute("class");
            if(successclassname.contains("success")){
                Boolean member1 = driver1.findElements(By.xpath("//*[@id=\"DataTables_Table_0\"]/tbody/tr/td[2]")).size() > 0;
                ArrayList<String> LCNListinFront = new ArrayList<String>();
                ArrayList<String> MemberListinFront = new ArrayList<String>();
                if(member1==true){
                    List<WebElement> ListSubject = driver1.findElements(By.xpath("//*[@id=\"DataTables_Table_0\"]/tbody/tr"));
                    Thread.sleep(3000);
                    int listsize = ListSubject.size();
                    for (int i = 1; i <= listsize; i++) {
                        LCNListinFront.add(driver1.findElement(By.xpath("//*[@id=\"DataTables_Table_0\"]/tbody/tr["+i+"]/td[2]")).getText());
                        MemberListinFront.add(driver1.findElement(By.xpath("//*[@id=\"DataTables_Table_0\"]/tbody/tr["+i+"]/td[3]")).getText());
                    }
                    System.out.println(LCNListinFront);
                    System.out.println(MemberListinFront);
                    String member = "SELECT\n" +
                            "    students.firstname,\n" +
                            "    students.lastname,\n" +
                            "    student_session.class_id,\n" +
                            "    student_session.session_id,\n" +
                            "    classes.class,\n" +
                            "    sections.section,\n" +
                            "    CONCAT(firstname,' ',lastname) AS fullname,\n" +
                            "    libarary_members.library_card_no\n" +
                            "FROM\n" +
                            "    `student_session`\n" +
                            "JOIN sessions ON sessions.id=student_session.session_id\n"+
                            "JOIN students ON students.id = student_session.student_id\n" +
                            "JOIN libarary_members ON libarary_members.member_id=student_session.student_id\n" +
                            "INNER JOIN classes ON student_session.class_id=classes.id \n" +
                            "INNER JOIN sections ON student_session.section_id=sections.id \n" +
                            "WHERE classes.class ='"+classname+"' AND sections.section ='"+sectionname+"'  AND libarary_members.library_card_no='"+LibraryCardNumber+"'AND sessions.session='"+Session+"'";
                    queryRs = statement.executeQuery(member);
                    ArrayList<String> memberListinDB = new ArrayList<String>();
                    ArrayList<String> LCNListinDB = new ArrayList<String>();
                    while (queryRs.next()){
                        memberListinDB.add(queryRs.getString("fullname"));
                        LCNListinDB.add(queryRs.getString("library_card_no"));
                    }
                    System.out.println(memberListinDB);
                    System.out.println(LCNListinDB);
                    // Compare both front and database students lists
                    if(LCNListinDB.equals(LCNListinFront) || memberListinDB.equals(MemberListinFront)){
                        System.out.println("The student name and library card number matches with database");
                        extentTest.log(Status.PASS,"The  student name and library card number matches with database");
                    }else{
                        System.out.println("The  student name and library card number does not match with database");
                        extentTest.log(Status.FAIL,"The  student name and library card number does not match with database");
                    }
                }else{
                    System.out.println("No record found");
                    extentTest.log(Status.FAIL,"No record found");
                }
                Thread.sleep(2000);
                System.out.println("Member added successfully");
                extentTest.log(Status.PASS,"Member added successfully");
            }
            else{
                System.out.println("There is some error on adding member");
                extentTest.log(Status.FAIL,"There is some error on adding member");
            }
        }
        else{
            System.out.println("The searched student is not found");
            extentTest.log(Status.FAIL,"The searched student is not found");
        }
    }

    //  Compare the list by surrendering the membership and compare with database.
    public void SurrenderMembershipStudent(String classname, String sectionname, String StudentName, String LibraryCardNumber, String Session) throws InterruptedException, IOException, SQLException, AWTException {
        ActionClass actionClass = new ActionClass(driver1, extentTest);
        WebElement view = driver1.findElement(By.xpath("//*[@id=\"sibe-box\"]/ul[2]/li[6]/a"));
        JavascriptExecutor javascriptExecutor = (JavascriptExecutor) driver1;
        javascriptExecutor.executeScript("arguments[0].scrollIntoView();",view);
//      actionClass.clickOnObject(ClickOnLibraryMenu);
        Thread.sleep(2000);
        actionClass.clickOnObject(ClickOnLibraryAddStudent);
        Thread.sleep(2000);
        WebElement SelectClass = driver1.findElement(By.xpath("//*[@name='class_id']//option[contains(text(),'"+classname+"')]"));
        actionClass.clickOnObject(SelectClass);
        Thread.sleep(2000);
        WebElement SelectSection = driver1.findElement(By.xpath("//*[@name='section_id']//option[contains(text(),'"+sectionname+"')]"));
        actionClass.clickOnObject(SelectSection);
        Thread.sleep(2000);
        actionClass.captureScreen("LibraryAddStudent_5");
        actionClass.clickOnObject(ClickOnSearch);
        WebElement view1 = driver1.findElement(By.xpath("//*[contains(text(),'Records')]"));
        javascriptExecutor.executeScript("arguments[0].scrollIntoView();",view1);
        Thread.sleep(1000);
        actionClass.captureScreen("LibraryAddStudent_6");
        actionClass.setValueinTextbox(SearchField, StudentName);
        Thread.sleep(2000);
        actionClass.captureScreen("LibraryAddStudent_7");
        Boolean isPresent1 = driver1.findElements(By.xpath("//td[contains(text(),'"+StudentName+"')]")).size() > 0;
        if(isPresent1==true){
            String xpathprint = driver1.findElement(By.xpath("//td[contains(text(),'"+StudentName+"')]")).getText();
            System.out.println(xpathprint);
            Thread.sleep(2000);
            actionClass.clickOnObject(SurrenderMembershipButton);
            Thread.sleep(2000);
            Alert alert = driver1.switchTo().alert();
            String alertMessage= driver1.switchTo().alert().getText();
            System.out.println(alertMessage);
            String expectedAlertMessage= "Are you sure you want to surrender membership?";
            if(alertMessage.equals(expectedAlertMessage)){
                System.out.println("The expected alert message matches with actual alert message.");
                extentTest.log(Status.PASS, "The expected alert message matches with actual alert message.");
            }else{
                System.out.println("The expected alert message does not match with actual alert message.");
                extentTest.log(Status.FAIL, "The expected alert message does not match with actual alert message.");
            }
            Thread.sleep(3000);
            actionClass.captureScreenUsingRobot("LibraryAddStudent_8");
            alert.accept();
            Thread.sleep(2000);
            actionClass.captureScreen("LibraryAddStudent_9");
            Thread.sleep(4000);
            WebElement view2 = driver1.findElement(By.xpath("//*[contains(text(),'Records')]"));
            javascriptExecutor.executeScript("arguments[0].scrollIntoView();",view2);
            actionClass.captureScreen("LibraryAddStudent_10");
//          Test if the student's membership is surrendered
            DatabaseFunctions DAB = new DatabaseFunctions(extentTest);
            conn = DAB.connect();
            statement = conn.createStatement();
            String students = "SELECT * FROM `libarary_members`";
            queryRs = statement.executeQuery(students);
            ArrayList<String> librarycardnumberListinDB = new ArrayList<String>();
            while (queryRs.next()){
                librarycardnumberListinDB.add(queryRs.getString("library_card_no"));
            }
            System.out.println(librarycardnumberListinDB);
            if(librarycardnumberListinDB.contains(LibraryCardNumber)==false){
                System.out.println("The student's membership is surrendered");
                extentTest.log(Status.PASS,"The student's membership is surrendered");
            }
            else{
                System.out.println("The student's membership is not surrendered");
                extentTest.log(Status.FAIL,"The student's membership is not surrendered");
            }
        }
        else{
            System.out.println("The searched student is not found");
            extentTest.log(Status.FAIL,"The searched student is not found");
        }
    }
}