package AEDU.Pages;
import AEDU.Actions.ActionClass;
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
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class LibraryAddTeacher {
    WebDriver driver1;
    ExtentTest extentTest;
    Connection conn = null;
    //  Docker
//  String url = "jdbc:mysql://localhost:6603/";
//  localDB
    String url = "jdbc:mysql://localhost:3306/";
    String dbName = "aedu-dev1";
    String driver = "com.mysql.jdbc.Driver";
    String userName = "root";
    String password = "";
    Statement statement;
    ResultSet queryRs;

    @FindBy(how = How.XPATH, using = "//*[@id=\"sibe-box\"]/ul[2]/li[10]/a")
    private WebElement ClickOnLibraryMenu;

    @FindBy(how = How.XPATH, using = "//*[@id=\"sibe-box\"]/ul[2]/li[10]/ul/li[5]/a")
    private WebElement ClickOnAddTeacher;

    @FindBy(how = How.XPATH, using = "//*[@id=\"DataTables_Table_0_filter\"]/label/input")
    private WebElement SearchField;

    @FindBy(how = How.XPATH, using = "//*[@id=\"DataTables_Table_0\"]/tbody/tr/td[7]/button")
    private WebElement ClickOnAddButton;

    @FindBy(how = How.XPATH, using = "//*[@id=\"library_card_no\"]")
    private WebElement LibraryCardNumberField;

    @FindBy(how = How.XPATH, using = "//*[@id=\"add_member\"]/button")
    private WebElement AddLibraryCardNumberButton;

    public LibraryAddTeacher(WebDriver driver, ExtentTest test) throws InterruptedException, IOException {
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

    //  Test if all the teacher are listed in the list and matches with teacher database.
    public void  CompareTeacherList() throws InterruptedException, SQLException, IOException {
        ActionClass actionClass = new ActionClass(driver1, extentTest);
        WebElement view = driver1.findElement(By.xpath("//*[@id=\"sibe-box\"]/ul[2]/li[6]/a"));
        JavascriptExecutor javascriptExecutor = (JavascriptExecutor) driver1;
        javascriptExecutor.executeScript("arguments[0].scrollIntoView();", view);
        //actionClass.clickOnObject(ClickOnLibraryMenu);
        Thread.sleep(2000);
        actionClass.clickOnObject(ClickOnAddTeacher);
        Thread.sleep(3000);
        actionClass.captureScreen("LibraryAddTeacher_1");
        Boolean isPresent = driver1.findElements(By.xpath("//*[@id=\"DataTables_Table_0\"]/tbody/tr[2]")).size() > 0;
        ArrayList<String> ListTeacherNameinFront = new ArrayList<String>();
        if(isPresent==true){
            List<WebElement> ListTeacher = driver1.findElements(By.xpath("//*[@id=\"DataTables_Table_0\"]/tbody/tr"));
            Thread.sleep(3000);
            int listsize = ListTeacher.size();
            for (int i = 1; i <= listsize; i++) {
                ListTeacherNameinFront.add(driver1.findElement(By.xpath("//*[@id=\"DataTables_Table_0\"]/tbody/tr["+i+"]/td[3]")).getText());
            }
            Collections.sort(ListTeacherNameinFront);
            System.out.println(ListTeacherNameinFront);
        }else{
            System.out.println("There is no such entry in the list");
            extentTest.log(Status.PASS, "The list is empty.");
        }
//      The List of teachers in Database
        DatabaseFunctions DAB = new DatabaseFunctions(extentTest);
        conn = DAB.connect();
        statement = conn.createStatement();
        String librarians = "SELECT * FROM `teachers`";
        queryRs = statement.executeQuery(librarians);
        ArrayList<String> ListTeacherNameinDB = new ArrayList<String>();
        if (queryRs.next()) {
            while(queryRs.next()) {
                ListTeacherNameinDB.add(queryRs.getString("name"));
            }
            Collections.sort(ListTeacherNameinDB);
            System.out.println(ListTeacherNameinDB);
        } else{
            System.out.println("There is no such entry in database.");
        }
        if(ListTeacherNameinDB.equals(ListTeacherNameinFront)){
            System.out.println("All the teachers are listed in front list.");
            extentTest.log(Status.PASS, "All the teachers are listed in front list.");
        }
        else{
            System.out.println("All the teachers are not listed in front list or some teachers might not get deleted.");
            extentTest.log(Status.FAIL, "All the teachers are not listed in front list or some teachers might not get deleted.");
        }
    }

    //  Test if all the member teacher are listed in the list and matches with teacher database
    public void CompareTeacherMemberList(String membertype) throws InterruptedException, SQLException, IOException {
        ActionClass actionClass = new ActionClass(driver1, extentTest);
        WebElement view = driver1.findElement(By.xpath("//*[@id=\"sibe-box\"]/ul[2]/li[6]/a"));
        JavascriptExecutor javascriptExecutor = (JavascriptExecutor) driver1;
        javascriptExecutor.executeScript("arguments[0].scrollIntoView();", view);
//      actionClass.clickOnObject(ClickOnLibraryMenu);
        Thread.sleep(2000);
        actionClass.clickOnObject(ClickOnAddTeacher);
        Thread.sleep(3000);
        ArrayList<String> TeacherNameMemberList = new ArrayList<String>();
        WebElement view1 = driver1.findElement(By.xpath("//*[@id=\"DataTables_Table_0\"]/tbody/tr[25]/td[3]"));
        JavascriptExecutor javascriptExecutor1 = (JavascriptExecutor) driver1;
        javascriptExecutor1.executeScript("arguments[0].scrollIntoView();",view1);
        actionClass.captureScreen("LibraryAddTeacher_2");
        Boolean isPresent = driver1.findElements(By.xpath("//*[@id=\"DataTables_Table_0\"]/tbody/tr[@class='success odd']/td[@class=\"text text-right\"]")).size() > 0;
        if (isPresent == true) {
            int i = 29;
            int j = 41;
            while(i<=j){
                String s = driver1.findElement(By.xpath("//*[@id=\"DataTables_Table_0\"]/tbody/tr[" + i + "]/td[3]")).getText();
                TeacherNameMemberList.add(s);
                i++;
            }
            Collections.sort(TeacherNameMemberList);
            System.out.println(TeacherNameMemberList);
            System.out.println("There are teacher with assigned membership in the list");
            extentTest.log(Status.PASS, "There are teacher with assigned membership in the list");
        }
        else {
            System.out.println("There are no teachers with assigned membership in the list");
            extentTest.log(Status.FAIL, "There are no teachers with assigned membership in the list");

        }
//      The Teacher List with assigned membership in database.
        DatabaseFunctions DAB = new DatabaseFunctions(extentTest);
        conn = DAB.connect();
        statement = conn.createStatement();
        String librarians = "SELECT teachers.name, libarary_members.member_id, libarary_members.member_type FROM teachers \n" +
                "JOIN libarary_members ON libarary_members.member_id=teachers.id  \n" +
                "WHERE member_type='"+membertype+"'";
        queryRs = statement.executeQuery(librarians);
        ArrayList<String> TeacherNameMemberListinDB = new ArrayList<String>();
        if (queryRs.next()) {
            while (queryRs.next())
                TeacherNameMemberListinDB.add(queryRs.getString("teachers.name"));
            Collections.sort(TeacherNameMemberListinDB);
            System.out.println(TeacherNameMemberListinDB);
            extentTest.log(Status.PASS, "There are teacher with assigned membership in the list");
        } else{
            System.out.println("There are no teacher with assigned membership in the list");
            extentTest.log(Status.FAIL,"There are no teacher with assigned membership in the list");
        }
        actionClass.CompareStringList(TeacherNameMemberList, TeacherNameMemberListinDB);
    }

    //
    public void AssignTeacherMemberShip(String Email, String LibraryCardNumber) throws InterruptedException, IOException, SQLException {
        ActionClass actionClass = new ActionClass(driver1, extentTest);
        WebElement view = driver1.findElement(By.xpath("//*[@id=\"sibe-box\"]/ul[2]/li[6]/a"));
        JavascriptExecutor javascriptExecutor = (JavascriptExecutor) driver1;
        javascriptExecutor.executeScript("arguments[0].scrollIntoView();", view);
//      actionClass.clickOnObject(ClickOnLibraryMenu);
        Thread.sleep(2000);
        actionClass.clickOnObject(ClickOnAddTeacher);
        Thread.sleep(3000);
        actionClass.setValueinTextbox(SearchField, Email);
        Thread.sleep(2000);
        Boolean isPresent1 = driver1.findElements(By.xpath("//*[@id=\"DataTables_Table_0\"]/tbody/tr/td[7]/button[@class=\"btn btn-default btn-xs add-teacher\"]")).size() > 0;
        if(isPresent1==true) {
            actionClass.clickOnObject(ClickOnAddButton);
            Thread.sleep(2000);
            actionClass.setValueinTextbox(LibraryCardNumberField, LibraryCardNumber);
            Thread.sleep(1000);
            actionClass.captureScreen("LibraryAddTeacher_2");
            actionClass.clickOnObject(AddLibraryCardNumberButton);
            Thread.sleep(2000);
            Boolean isPresent = driver1.findElements(By.xpath("//*[@id=\"DataTables_Table_0\"]/tbody/tr/td[2]")).size() > 0;
            ArrayList<String> MemberIdListinFront = new ArrayList<>();
            ArrayList<String> LCNListinFront = new ArrayList<>();
            ArrayList<String> ListTeacherNameinFront = new ArrayList<String>();
            if (isPresent == true) {
                List<WebElement> ListTeacher = driver1.findElements(By.xpath("//*[@id=\"DataTables_Table_0\"]/tbody/tr"));
                Thread.sleep(3000);
                int listsize = ListTeacher.size();
                for (int i = 1; i <= listsize; i++) {
                    MemberIdListinFront.add(driver1.findElement(By.xpath("//*[@id=\"DataTables_Table_0\"]/tbody/tr[" + i + "]/td[1]")).getText());
                    LCNListinFront.add(driver1.findElement(By.xpath("//*[@id=\"DataTables_Table_0\"]/tbody/tr[" + i + "]/td[2]")).getText());
                    ListTeacherNameinFront.add(driver1.findElement(By.xpath("//*[@id=\"DataTables_Table_0\"]/tbody/tr[" + i + "]/td[3]")).getText());
                }
                System.out.println(MemberIdListinFront);
                System.out.println(LCNListinFront);
                System.out.println(ListTeacherNameinFront);

            } else {
                System.out.println("There is no such entry in the list");
                extentTest.log(Status.PASS, "The list is empty.");
            }
//      The List of teachers in Database
            DatabaseFunctions DAB = new DatabaseFunctions(extentTest);
            conn = DAB.connect();
            statement = conn.createStatement();
            String members = "SELECT libarary_members.*, teachers.name, teachers.email FROM `libarary_members` \n" +
                    "JOIN teachers ON teachers.id= libarary_members.member_id\n" +
                    "WHERE teachers.email='"+Email+"'";
            System.out.println(members);
            queryRs = statement.executeQuery(members);
            ArrayList<String> MemberIdListinDB = new ArrayList<String>();
            ArrayList<String> LCNListinDB = new ArrayList<String>();
            ArrayList<String> ListTeacherNameinDB = new ArrayList<String>();
            if (queryRs.next()) {
                System.out.println("In while loop");
                MemberIdListinDB.add(queryRs.getString("libarary_members.id"));
                LCNListinDB.add(queryRs.getString("libarary_members.library_card_no"));
                ListTeacherNameinDB.add(queryRs.getString("teachers.name"));
                System.out.println(MemberIdListinDB);
                System.out.println(LCNListinDB);
                System.out.println(ListTeacherNameinDB);
            } else {
                System.out.println("There is no such entry in database.");
            }
            if (ListTeacherNameinDB.equals(ListTeacherNameinFront) || MemberIdListinFront.equals(MemberIdListinDB) || LCNListinFront.equals(LCNListinDB)) {
                System.out.println("The teacher name, member id and library card number matches with database");
                extentTest.log(Status.PASS, "The teacher name, member id and library card number matches with database");
            } else {
                System.out.println("The  teacher name, member id and library card number does not match with database");
                extentTest.log(Status.FAIL, "The  teacher name, member id and library card number does not match with database");
            }
        }
        else{
            System.out.println("The teacher is already assigned membership.");
            extentTest.log(Status.FAIL, "The user is already assigned membership.");
        }
    }
}