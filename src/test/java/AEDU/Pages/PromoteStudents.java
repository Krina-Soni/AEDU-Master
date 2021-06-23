package AEDU.Pages;

import AEDU.Utilities.DatabaseFunctions;
import AEDU.constants.CommonVar;
import AEDU.Actions.VerificationClass;
import AEDU.Actions.ActionClass;
import AEDU.Pages.StudentInformation;
import com.aventstack.extentreports.ExtentTest;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
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


public class PromoteStudents {
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
    ResultSet Qcount;

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
    @FindBy(
            how = How.XPATH,
            using = "/html/body/div[1]/aside/div/section/ul[2]/li[1]/a/span"
    )
    private WebElement sidemenustudentinfomenuclick;

    @FindBy(
            how = How.XPATH,
            using = "//*[@id=\"sibe-box\"]/ul[2]/li[1]/ul/li[4]/a"
    )
    private WebElement PromoteStudentMenu;

    @FindBy(
            how = How.XPATH,
            using = "//*[@id=\"sibe-box\"]/ul[2]/li[1]/ul/li[1]/a"
    )
    private WebElement ClickOnStudentDetails;

    @FindBy(
            how = How.XPATH,
            using = "//*[@id=\"class_id\"]"
    )
    private WebElement classfeild;

    @FindBy(
            how = How.XPATH,
            using = "//*[@id=\"section_id\"]"
    )
    private WebElement sectionfeild;

    @FindBy(
            how = How.CSS,
            using = "#form1 > div.box-footer > button"
    )
    private WebElement searchbtn;

    @FindBy(
            how = How.XPATH,
            using = "/html/body/div[1]/div[1]/section[2]/div/div/div/form/div[1]/div/div[1]/div/span/p"
    )
    private WebElement classfeildvalidation;
    @FindBy(
            how = How.XPATH,
            using = "/html/body/div[1]/div[1]/section[2]/div/div/div/form/div[1]/div/div[2]/div/span/p"
    )
    private WebElement sectionfeildvalidation;
    @FindBy(
            how = How.XPATH,
            using = "/html/body/div[1]/aside/div/section/ul[2]/li[1]/a/span"
    )
    private WebElement ClickOnStudentInformation;
    @FindBy(
            how = How.XPATH,
            using = "/html/body/div[1]/aside/div/section/ul[2]/li[1]/ul/li[4]/a"
    )
    private WebElement ClickOnPromoteStudent;

    @FindBy(
            how = How.CSS,
            using = "#class_id > option:nth-child(7)"
    )
    private WebElement selectoption;

    @FindBy(
            how = How.XPATH,
            using = "//*[@id=\"section_id\"]/option[3]"
    )
    private WebElement selectoptionforsection;
    @FindBy(
            how = How.CSS,
            using = "#session_id"
    )
    private WebElement Promoteinsessionclcik;

    //*[@id="session_id"]/option[3]
    @FindBy(
            how = How.XPATH,
            using = "//*[@id=\"session_id\"]/option[3]"
    )
    private WebElement SelectSession;
    @FindBy(
            how = How.XPATH,
            using = "//*[@id=\"class_promote_id\"]"
    )
    private WebElement PromoteClass;
    @FindBy(
            how = How.XPATH,
            using = "//*[@id=\"section_promote_id\"]"
    )
    private WebElement PromoteSession;
    //*[@id="class_promote_id"]/option[7]
    @FindBy(
            how = How.XPATH,
            using = "//*[@id=\"class_promote_id\"]/option[7]"
    )
    private WebElement SelectclassforPromote;
    @FindBy(
            how = How.XPATH,
            using = "//*[@id=\"section_promote_id\"]/option[3]"
    )
    private WebElement SelectsectionforPromote;

    @FindBy(
            how = How.CSS,
            using = "body > div.wrapper > div.content-wrapper > section.content > div > div > div.box.box-info > div.box-footer.clearfix > a"
    )
    private WebElement Promotebtn;

    @FindBy(
            how = How.XPATH,
            using = "/html/body/div[1]/div[1]/section[2]/div/div/div[2]/div[2]/form/div[2]/table/tbody/tr[2]/td"
    )
    private WebElement Nodatabehavormatch;

    @FindBy(
            how = How.CSS,
            using = "#pramoteStudentModal > div > div > div.modal-footer > button.btn.btn-primary.pramote_student"
    )
    private WebElement Savebtnpopup;

    @FindBy(
            how = How.XPATH,
            using = "/html/body/div[1]/div[1]/section[2]/div/div/div[2]/div[2]/form/div[2]/table/tbody/tr[2]/td[6]/div[2]/label/input"
    )
    private WebElement Leavbtn;
    @FindBy(
            how = How.XPATH,
            using = "/html/body/div[1]/aside/div/section/ul[2]/li[1]/ul/li[6]/a"
    )
    private WebElement LeavingStudentSidemenu;
    @FindBy(
            how = How.XPATH,
            using = "//*[@id=\"class_id\"]"
    )
    private WebElement LeavingStudentclass;
    @FindBy(
            how = How.XPATH,
            using = "//*[@id=\"section_id\"]"
    )
    private WebElement LeavingStudentSection;
    @FindBy(
            how = How.XPATH,
            using = "//*[@id=\"class_id\"]/option[3]"
    )
    private WebElement LeavingStudentSelectclass;
    @FindBy(
            how = How.XPATH,
            using = " //*[@id=\"section_id\"]/option[2]"
    )
    private WebElement LeavingStudentSelectSection;
    @FindBy(
            how = How.XPATH,
            using = "/html/body/div[1]/div[1]/section[2]/div/div[1]/div/div[2]/div/div[1]/div/form/div[3]/div/button"
    )
    private WebElement LeavingSearch;
    @FindBy(
            how = How.XPATH,
            using = "/html/body/div[1]/div[1]/section[2]/div/div[2]/div/div[2]/div/div[2]/label/input"
    )
    private WebElement SearchFeild;

    public PromoteStudents(WebDriver driver, ExtentTest test) {

        this.driver1 = driver;
        this.extentTest = test;
        PageFactory.initElements(driver, this);

    }
    public void openNewTab() throws InterruptedException {
        ActionClass actionClass=new ActionClass(driver1, extentTest);
        Thread.sleep(2000);
        driver1.getCurrentUrl();
        JavascriptExecutor jsetaskscore1 = (JavascriptExecutor) driver1;
        jsetaskscore1.executeScript("scrollBy(0, -300)");
        Thread.sleep(2000);

        for(String childTab : driver1.getWindowHandles()){
            driver1.switchTo().window(childTab);
        }
        Thread.sleep(1000);
    }

    public void Promote() throws IOException, InterruptedException {

        ActionClass actionClass = new ActionClass(driver1, extentTest);
        Thread.sleep(2000);
        actionClass.clickOnObject(this.ClickOnPromoteStudent);
    }

    public void validation() throws IOException, InterruptedException {
        ActionClass actionClass = new ActionClass(driver1, extentTest);
//        actionClass.clickOnObject(this.ClickOnStudentInformation);
//        Thread.sleep(3000);
        actionClass.clickOnObject(this.ClickOnPromoteStudent);
        Thread.sleep(3000);
        actionClass.clickOnObject(this.searchbtn);
        VerificationClass very = new VerificationClass(driver1, extentTest);
        very.verifyTextPresent(this.classfeildvalidation, "The Class field is required.");
        very.verifyTextPresent(this.sectionfeildvalidation, "The Section field is required.");
        //section feild validation
    }

    public void feildvalidations() throws IOException, IOException, InterruptedException {
        ActionClass actionClass1 = new ActionClass(this.driver1, extentTest);
//        actionClass1.clickOnObject(this.ClickOnStudentInformation);
//        Thread.sleep(3000);
        actionClass1.clickOnObject(this.ClickOnPromoteStudent);
        Thread.sleep(3000);
        actionClass1.clickOnObject(this.classfeild);
        actionClass1.clickOnObject(this.selectoption);
        actionClass1.clickOnObject(this.searchbtn);
        VerificationClass very1 = new VerificationClass(driver1, extentTest);
        very1.verifyTextPresent(this.sectionfeildvalidation, "The Section field is required.");
    }

    public void classfeildvalidations() throws IOException, IOException, InterruptedException {
        ActionClass actionClass2 = new ActionClass(this.driver1, extentTest);
//        Thread.sleep(5000);
//        actionClass2.clickOnObject(this.ClickOnStudentInformation);
        Thread.sleep(5000);
        actionClass2.clickOnObject(this.ClickOnPromoteStudent);
        Thread.sleep(3000);
        actionClass2.clickOnObject(this.searchbtn);
        VerificationClass very3 = new VerificationClass(driver1, extentTest);
        very3.verifyTextPresent(this.classfeildvalidation, "The Class field is required.");
        actionClass2.captureScreen("Class Validation");
    }


    public Object[] listdata() throws IOException, InterruptedException, SQLException {

        ActionClass actionClass4 = new ActionClass(this.driver1, extentTest);
//        Thread.sleep(5000);
//        actionClass4.clickOnObject(this.ClickOnStudentInformation);
        Thread.sleep(5000);
        actionClass4.clickOnObject(this.ClickOnPromoteStudent);
        Thread.sleep(5000);
        actionClass4.clickOnObject(this.classfeild);
        actionClass4.clickOnObject(this.selectoption);
        if (selectoption.isSelected() == true) {
            System.out.println("hi");
            actionClass4.clickOnObject(this.sectionfeild);
            actionClass4.clickOnObject(this.selectoptionforsection);
            actionClass4.clickOnObject(this.searchbtn);
            JavascriptExecutor jsetaskscore = (JavascriptExecutor) driver1;
            jsetaskscore.executeScript("scrollBy(0, 600)");
            List<WebElement> ListStudent = driver1.findElements(By.xpath("/html/body/div[1]/div[1]/section[2]/div/div/div[2]/div[2]/form/div[2]/table/tbody/tr"));

            int listsize = ListStudent.size();
            System.out.println(listsize);
            ArrayList listNames1 = new ArrayList();
            for (int i = 2; i <= listsize; i++) {
                String s = driver1.findElement(By.xpath("/html/body/div[1]/div[1]/section[2]/div/div/div[2]/div[2]/form/div[2]/table/tbody/tr[" + i + "]/td[1]")).getText();
                System.out.println("Value in list is: " + s);
                listNames1.add(driver1.findElement(By.xpath("/html/body/div[1]/div[1]/section[2]/div/div/div[2]/div[2]/form/div[2]/table/tbody/tr[\" + i + \"]/td[1]")).getText());
            }
            DatabaseFunctions DAB = new DatabaseFunctions(extentTest);
            conn = DAB.connect();
            statement = conn.createStatement();
            String students = "SELECT student_session.id, student_session.session_id, students.firstname, students.lastname, students.is_active, students.is_inactive, classes.class, students.admission_no FROM `student_session` INNER JOIN students ON student_session.student_id = students.id INNER JOIN classes ON student_session.class_id=classes.id \n" +
                    "WHERE student_session.session_id='15' AND classes.class='9th' AND student_session.is_inactive='no' AND students.id  NOT IN (SELECT student_id FROM promot_student)";
            queryRs = statement.executeQuery(students);
            ArrayList listNames = new ArrayList();

            while (queryRs.next()) {
                String s1 = null;
                s1 = queryRs.getString("students.admission_no");
                System.out.println("Admission no. is " + s1);
                listNames.add(queryRs.getString("students.admission_no"));

            }
            System.out.println(listNames.equals(listNames1));

            actionClass4.CompareList(listNames, listNames1);

            actionClass4.captureScreen("Default Keyword search");
            return listNames1.toArray();
        } else {
            VerificationClass very = new VerificationClass(driver1, extentTest);
            very.verifyTextPresent(this.classfeildvalidation, "The Class field is required");
        }
//        return listNames1.toArray();

        return new Object[0];
    }


//    Promote the student into a different class
    public void Promoteinsession() throws InterruptedException, IOException, SQLException {
        ActionClass actionClass=new ActionClass(this.driver1,extentTest);
//        Thread.sleep(5000);
//        actionClass.clickOnObject(this.ClickOnStudentInformation);
        Thread.sleep(5000);
        actionClass.clickOnObject(this.ClickOnPromoteStudent);
        Thread.sleep(3000);
        actionClass.clickOnObject(this.classfeild);
        actionClass.clickOnObject(this.selectoption);
        actionClass.clickOnObject(this.sectionfeild);
        actionClass.clickOnObject(this.selectoptionforsection);
        actionClass.clickOnObject(this.searchbtn);
        JavascriptExecutor jsetaskscore = (JavascriptExecutor) driver1;
        jsetaskscore.executeScript("scrollBy(0, 750)");
        actionClass.clickOnObject(this.Promoteinsessionclcik);
        actionClass.clickOnObject(this.SelectSession);
        actionClass.clickOnObject(this.PromoteClass);
        actionClass.clickOnObject(this.SelectclassforPromote);
        actionClass.clickOnObject(this.PromoteSession);
        actionClass.clickOnObject(this.SelectsectionforPromote);
        Thread.sleep(3000);
        List<WebElement> ListPromoteStudent = driver1.findElements(By.xpath("/html/body/div[1]/div[1]/section[2]/div/div/div[2]/div[2]/form/div[2]/table/tbody/tr"));
        int listsize = ListPromoteStudent.size();
        ArrayList <String>  PromoteStudentList = new ArrayList<String>();
        for(int i=2; i<=listsize; i++){
            String s = driver1.findElement(By.xpath("/html/body/div[1]/div[1]/section[2]/div/div/div[2]/div[2]/form/div[2]/table/tbody/tr[" + i +"]/td[1]")).getText();
            System.out.println("Values in the list: "+ s);
            PromoteStudentList.add(driver1.findElement(By.xpath("/html/body/div[1]/div[1]/section[2]/div/div/div[2]/div[2]/form/div[2]/table/tbody/tr[" + i +"]/td[1]")).getText());
        }
        ArrayList<Integer> PromoteStudentListIntegerList= new ArrayList<Integer>(PromoteStudentList.size());
        for(String myInt : PromoteStudentList ){
            PromoteStudentListIntegerList.add(Integer.valueOf(myInt));
        }
        System.out.println(PromoteStudentListIntegerList);
        Thread.sleep(3000);

        actionClass.clickOnObject(this.Promotebtn);
        Thread.sleep(3000);
        actionClass.clickOnObject(this.Savebtnpopup);
        Thread.sleep(3000);
//
//        VerificationClass very = new VerificationClass(driver1, extentTest);
//        very.verifyTextPresent(this.classfeildvalidation, "No Record Found");
        DatabaseFunctions DAB = new DatabaseFunctions(extentTest);
        conn = DAB.connect();
        statement = conn.createStatement();
        String students = "SELECT student_session.id, student_session.session_id, students.firstname, students.lastname, students.is_active, students.is_inactive, classes.class, students.admission_no, sections.id FROM `student_session` INNER JOIN students ON student_session.student_id = students.id INNER JOIN classes ON student_session.class_id = classes.id INNER JOIN sections ON student_session.section_id=sections.id WHERE student_session.session_id = '15' AND classes.class = '9th' AND sections.id= 10 AND student_session.is_inactive = 'no' AND students.id NOT IN( SELECT student_id FROM promot_student )";
        queryRs = statement.executeQuery(students);
        ArrayList<String> ListPromoteStudent1 = new ArrayList<String>();

        while (queryRs.next()) {
            String s1 = null;
            s1 = queryRs.getString("students.admission_no");
            System.out.println("Admission no. is " + s1);
            ListPromoteStudent1.add(queryRs.getString("students.admission_no"));
        }
        ArrayList<Integer> PromoteStudentListIntegerList1= new ArrayList<Integer>(ListPromoteStudent1.size());
        for(String myInt : ListPromoteStudent1 ){
            PromoteStudentListIntegerList1.add(Integer.valueOf(myInt));
        }
//        Collections.sort(PromoteStudentListIntegerList1);
        System.out.println(PromoteStudentListIntegerList1);
        System.out.println(PromoteStudentListIntegerList1.equals(PromoteStudentListIntegerList));
//        System.out.println(listNames.equals(listNames1));
        actionClass.CompareList(PromoteStudentListIntegerList,PromoteStudentListIntegerList1);

    }
    public void LeaveStudent()throws InterruptedException,IOException{
        ActionClass actionClass=new ActionClass(this.driver1,extentTest);
//        Thread.sleep(5000);
//        actionClass.clickOnObject(this.sidemenustudentinfomenuclick);
        actionClass.clickOnObject(this.ClickOnPromoteStudent);
        Thread.sleep(5000);
        actionClass.clickOnObject(this.classfeild);
        actionClass.clickOnObject(this.selectoption);
        actionClass.clickOnObject(this.sectionfeild);
        actionClass.clickOnObject(this.selectoptionforsection);
        actionClass.clickOnObject(this.searchbtn);
        JavascriptExecutor jsetaskscore = (JavascriptExecutor) driver1;
        jsetaskscore.executeScript("scrollBy(0, 900)");
        actionClass.clickOnObject(this.Promoteinsessionclcik);
        actionClass.clickOnObject(this.SelectSession);
        actionClass.clickOnObject(this.PromoteClass);
        actionClass.clickOnObject(this.SelectclassforPromote);
        actionClass.clickOnObject(this.PromoteSession);
        actionClass.clickOnObject(this.SelectsectionforPromote);
        Thread.sleep(3000);
        JavascriptExecutor jsetaskscore1 = (JavascriptExecutor) driver1;
        jsetaskscore1.executeScript("scrollBy(0, 150)");
        actionClass.clickOnObject(this.Leavbtn);
         actionClass.clickOnObject(this.Promotebtn);
         actionClass.clickOnObject(this.Savebtnpopup);
         Thread.sleep(5000);
         actionClass.clickOnObject(this.LeavingStudentSidemenu);
         actionClass.clickOnObject(this.LeavingStudentclass);
         actionClass.clickOnObject(this.LeavingStudentSelectclass);
         actionClass.clickOnObject(this.LeavingStudentSection);
         actionClass.clickOnObject(this.LeavingStudentSelectSection);
         actionClass.clickOnObject(this.LeavingSearch);
        // actionClass.clickOnObject(this.SearchFeild);
         actionClass.setValueinTextbox(this.SearchFeild,"123");
        JavascriptExecutor jsetaskscore3 = (JavascriptExecutor) driver1;
        jsetaskscore3.executeScript("scrollBy(0, 150)");
        actionClass.captureScreen("Leaving Student List");
    }
}









