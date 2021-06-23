package AEDU.Pages;


import AEDU.Actions.ActionClass;
import AEDU.Pages.AddStudent;
import AEDU.Actions.VerificationClass;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import AEDU.Utilities.*;

import java.sql.*;
import java.util.*;

import java.io.IOException;
import java.sql.Connection;

public class StudentSearch {
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

    @FindBy(
            how = How.XPATH,
            using = "/html/body/div[1]/aside/div/section/ul[2]/li[1]/a/span"
    )
    private WebElement ClickOnStudentInformation;
    @FindBy(
            how = How.XPATH,
            using = "//*[@id=\"sibe-box\"]/ul[2]/li[1]/ul/li[1]/a"
    )
    private WebElement ClickOnStudentDetails;

    @FindBy(
            how = How.CSS,
            using = "body > div.wrapper > div.content-wrapper > section.content > div > div > div > div.box-body > div > div:nth-child(2) > div > form > div:nth-child(4) > div > button"
    )
    private WebElement ClickOnbtnSearch;

    @FindBy(
            how = How.XPATH,
            using = "/html/body/div[1]/div[1]/section[2]/div/div/div/div[2]/div/div[2]/div/form/div[1]/div/input"

    )
    private WebElement KeywordSearchtxt;


    @FindBy(
            how = How.ID, using = "class_id"
    )
    private WebElement SelectClass;

    @FindBy(
            how = How.XPATH, using = "//*[@id=\"class_id\"]/option[7]"
    )
    private WebElement ClassValue;

    @FindBy(
            how = How.NAME, using = "section_id"
    )
    private WebElement SelectSection;

    @FindBy(
            how = How.XPATH, using = "//*[@id=\"section_id\"]/option[2]"
    )
    private WebElement SectionValue;

    @FindBy(
            how = How.XPATH, using = "/html/body/div[1]/div[1]/section[2]/div/div/div/div[2]/div/div[1]/div/form/div[4]/div/button"
    )
    private WebElement ClickOnSearch1;

    @FindBy(
            how = How.XPATH, using = "/html/body/div[1]/div[1]/section[2]/div/div/div/div[2]/div/div[1]/div/form/div[1]/div/span/p"
    )
    private WebElement ClassValidation;

    @FindBy(
            how = How.CLASS_NAME, using = "dataTables_empty"
    )
    private WebElement NoResultinTable;

    @FindBy(
            how = How.XPATH, using = "//*[@id=\"status\"]"
    )
    private WebElement ClickonStatus;

    @FindBy(
            how = How.XPATH, using = "//*[@id=\"status\"]/option[2]"
    )
    private WebElement ClickonStatusInactive;

    public StudentSearch(WebDriver driver, ExtentTest test) {
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
    //Check By Using Only Class Filter "Active"
    public Object[] CheckDataByClassActive() throws IOException, SQLException, InterruptedException {

        ActionClass actionClass = new ActionClass(this.driver1, extentTest);
        //actionClass.clickOnObject(this.ClickOnStudentInformation);
        Thread.sleep(5000);
        actionClass.clickOnObject(this.ClickOnStudentDetails);
        actionClass.clickOnObject(this.SelectClass);
        Thread.sleep(5000);
        actionClass.clickOnObject(this.ClassValue);
        Thread.sleep(1000);
        if (ClassValue.isSelected() == true) {
            //actionClass.clickOnObject(this.SectionValue);
            actionClass.clickOnObject(this.ClickOnSearch1);
            List<WebElement> ListStudent = driver1.findElements(By.xpath("//*[@id=\"DataTables_Table_0\"]/tbody/tr"));
            int ClassStudentsize = ListStudent.size();
            ArrayList<String> listNames1 = new ArrayList<>();
            ArrayList listNames2 = new ArrayList();
            String s2 = "No data available in table";

            for (int i = 1; i <= ClassStudentsize; i++) {
//                String s = driver1.findElement(By.xpath("//*[@id=\"DataTables_Table_0\"]/tbody/tr[" + i + "]/td[1]")).getText();
//                System.out.println("Value in list is: " + s);
                listNames1.add(driver1.findElement(By.xpath("//*[@id=\"DataTables_Table_0\"]/tbody/tr[" + i + "]/td[1]")).getText());
            }
            ArrayList<Integer> CheckDataByClassActiveList1= new ArrayList<Integer>(listNames1.size());
            for(String myInt : listNames1 ){
                CheckDataByClassActiveList1.add(Integer.valueOf(myInt));
            }
            System.out.println(CheckDataByClassActiveList1);
//            System.out.println(listNames1);

            DatabaseFunctions DAB = new DatabaseFunctions(extentTest);
            conn = DAB.connect();
            statement = conn.createStatement();
            System.out.println(ClassValue.getText());
            String B1 = ClassValue.getText();
            String searchstudentbyclass = "SELECT student_session.id, student_session.session_id, students.firstname, students.lastname, students.is_active, students.is_inactive, classes.class, students.admission_no FROM `student_session` INNER JOIN students ON student_session.student_id = students.id INNER JOIN classes ON student_session.class_id=classes.id WHERE student_session.session_id='15' AND classes.class='" + B1 + "' AND student_session.is_inactive='no' ORDER BY `students`.`admission_no`";
            System.out.println(searchstudentbyclass);
            ResultSet queryRs1 = statement.executeQuery(searchstudentbyclass);
            ArrayList<String> listNames = new ArrayList<>();
            while (queryRs1.next()) {
                String s1 = null;
                s1 = queryRs1.getString("students.admission_no");
                System.out.println("Admission no. is " + s1);
                listNames.add(queryRs1.getString("students.admission_no"));

            }
            ArrayList<Integer> CheckDataByClassActiveList= new ArrayList<Integer>(listNames.size());
            for(String myInt : listNames ){
                CheckDataByClassActiveList.add(Integer.valueOf(myInt));
            }
            Collections.sort(CheckDataByClassActiveList);
            System.out.println(CheckDataByClassActiveList.equals(CheckDataByClassActiveList1));
            actionClass.CompareList(CheckDataByClassActiveList, CheckDataByClassActiveList1);

            actionClass.captureScreen("Default Keyword search");

            return listNames.toArray();
        } else {
            VerificationClass VerifyClass = new VerificationClass(driver1, extentTest);
            VerifyClass.verifyTextPresent(ClassValidation, "The Class field is required.");
        }
        return new Object[0];
    }

    //Check by Class & section Filter "Active"

    public Object[] CheckDataByClassAndSectionActive() throws IOException, SQLException, InterruptedException {

        ActionClass actionClass = new ActionClass(this.driver1, extentTest);
        //actionClass.clickOnObject(this.ClickOnStudentInformation);
        Thread.sleep(5000);
        actionClass.clickOnObject(this.ClickOnStudentDetails);
        actionClass.clickOnObject(this.SelectClass);
        Thread.sleep(5000);
        actionClass.clickOnObject(this.ClassValue);
        Thread.sleep(1000);

        if (ClassValue.isSelected() == true) {
            actionClass.clickOnObject(this.SelectSection);
            actionClass.clickOnObject(this.SectionValue);
            actionClass.clickOnObject(this.ClickOnSearch1);
            List<WebElement> ListStudent = driver1.findElements(By.xpath("//*[@id=\"DataTables_Table_0\"]/tbody/tr"));
            int ClassStudentsize = ListStudent.size();
            ArrayList<String> listNames1 = new ArrayList<>();
            for (int i = 1; i <= ClassStudentsize; i++) {
                String s = driver1.findElement(By.xpath("//*[@id=\"DataTables_Table_0\"]/tbody/tr[" + i + "]/td[1]")).getText();
                System.out.println("Value in list is: " + s);
                listNames1.add(driver1.findElement(By.xpath("//*[@id=\"DataTables_Table_0\"]/tbody/tr[" + i + "]/td[1]")).getText());
            }
            ArrayList<Integer> CheckDataByClassAndSectionActiveList1= new ArrayList<Integer>(listNames1.size());
            for(String myInt : listNames1 ){
                CheckDataByClassAndSectionActiveList1.add(Integer.valueOf(myInt));
            }
            System.out.println(CheckDataByClassAndSectionActiveList1);
            DatabaseFunctions DAB = new DatabaseFunctions(extentTest);
            conn = DAB.connect();
            statement = conn.createStatement();
            System.out.println(ClassValue.getText());
            System.out.println(SectionValue.getText());
            String B1 = ClassValue.getText();
            String B2 = SectionValue.getText();
            String searchstudentbyclass = "SELECT student_session.id, student_session.session_id, students.firstname, students.lastname, students.is_active, students.is_inactive,sections.id,sections.section, classes.class, students.admission_no FROM `student_session` INNER JOIN sections ON student_session.section_id=sections.id INNER JOIN students ON student_session.student_id = students.id INNER JOIN classes ON student_session.class_id=classes.id WHERE student_session.session_id='15' AND classes.class='" + B1 + "' AND sections.section='" + B2 + "' AND student_session.is_inactive='no' ORDER BY `students`.`admission_no`";
            ResultSet queryRs2 = statement.executeQuery(searchstudentbyclass);
            ArrayList<String> listNames = new ArrayList<>();
            while (queryRs2.next()) {
                String s1 = null;
                s1 = queryRs2.getString("students.admission_no");
                System.out.println("Admission no. is " + s1);
                listNames.add(queryRs2.getString("students.admission_no"));
            }
            ArrayList<Integer> CheckDataByClassAndSectionActiveList= new ArrayList<Integer>(listNames.size());
            for(String myInt : listNames ){
                CheckDataByClassAndSectionActiveList.add(Integer.valueOf(myInt));
            }
            Collections.sort(CheckDataByClassAndSectionActiveList);
            System.out.println(CheckDataByClassAndSectionActiveList.equals(CheckDataByClassAndSectionActiveList1));
            actionClass.CompareList(CheckDataByClassAndSectionActiveList, CheckDataByClassAndSectionActiveList1);

            actionClass.captureScreen("Default Keyword search");

            return listNames.toArray();
        } else {
            VerificationClass VerifyClass = new VerificationClass(driver1, extentTest);
            VerifyClass.verifyTextPresent(ClassValidation, "The Class field is required.");
        }
        return new Object[0];
    }

    public Object[] CheckDataByClassAndSectionInctive() throws IOException, SQLException, InterruptedException {

        ActionClass actionClass = new ActionClass(this.driver1, extentTest);
        //actionClass.clickOnObject(this.ClickOnStudentInformation);
        Thread.sleep(5000);
        actionClass.clickOnObject(this.ClickOnStudentDetails);
        actionClass.clickOnObject(this.SelectClass);
        Thread.sleep(5000);
        actionClass.clickOnObject(this.ClassValue);
        Thread.sleep(1000);
        if (ClassValue.isSelected() == true) {
            actionClass.clickOnObject(this.SelectSection);
            actionClass.clickOnObject(this.SectionValue);
            actionClass.clickOnObject(this.ClickonStatus);
            actionClass.clickOnObject(this.ClickonStatusInactive);
            actionClass.clickOnObject(this.ClickOnSearch1);
            boolean Checktable = NoResultinTable.isDisplayed();
            DatabaseFunctions DAB = new DatabaseFunctions(extentTest);
            conn = DAB.connect();
            statement = conn.createStatement();
            String B1 = ClassValue.getText();
            String B2 = SectionValue.getText();
            String searchstudentbyclass = "SELECT student_session.id, student_session.session_id, students.firstname, students.lastname, students.is_active, students.is_inactive,sections.id,sections.section, classes.class, students.admission_no FROM `student_session` INNER JOIN sections ON student_session.section_id=sections.id INNER JOIN students ON student_session.student_id = students.id INNER JOIN classes ON student_session.class_id=classes.id WHERE student_session.session_id='15' AND classes.class='" + B1 + "' AND sections.section='" + B2 + "' AND student_session.is_inactive='yes' ORDER BY `students`.`admission_no`";
            ResultSet queryRs3 = statement.executeQuery(searchstudentbyclass);
            ArrayList<String> listNames = new ArrayList<String>();

            if(Checktable==true)
            {
                while (queryRs3.next()) {
                    listNames.add(queryRs3.getString("students.admission_no"));
                }
                int Querysize = listNames.size();
                if (Querysize==0)
                {
                    System.out.println("DB table Also doesn't have the Match Record");
                }
                else
                {
                    System.out.println("DB table has the Match Records");
                }
            }
            else
            {
                List<WebElement> ListStudent = driver1.findElements(By.xpath("//*[@id=\"DataTables_Table_0\"]/tbody/tr"));
                int ClassStudentsize = ListStudent.size();
                ArrayList<String> listNames1 = new ArrayList<String>();
                for (int i = 1; i <= ClassStudentsize; i++) {
                    String s = driver1.findElement(By.xpath("//*[@id=\"DataTables_Table_0\"]/tbody/tr[" + i + "]/td[1]")).getText();
                    System.out.println("Value in list is: " + s);
                    listNames1.add(driver1.findElement(By.xpath("//*[@id=\"DataTables_Table_0\"]/tbody/tr[" + i + "]/td[1]")).getText());
                }

                ArrayList<Integer> CheckDataByClassAndSectionInctive = new ArrayList<Integer>(listNames1.size());
                for (String myInt : listNames1) {
                    CheckDataByClassAndSectionInctive.add(Integer.valueOf(myInt));
                }
                System.out.println(CheckDataByClassAndSectionInctive);
                while (queryRs3.next()) {
                    listNames.add(queryRs3.getString("students.admission_no"));
                }
                ArrayList<Integer> CheckDataByClassAndSectionInctiveList = new ArrayList<Integer>(listNames.size());
                for (String myInt : listNames) {
                    CheckDataByClassAndSectionInctiveList.add(Integer.valueOf(myInt));
                }
                Collections.sort(CheckDataByClassAndSectionInctiveList);
                System.out.println(CheckDataByClassAndSectionInctiveList);
                System.out.println(listNames.equals(listNames1));
                actionClass.CompareList(CheckDataByClassAndSectionInctiveList, CheckDataByClassAndSectionInctiveList);
                actionClass.captureScreen("Default Keyword search");
                return listNames.toArray();
            }
        }
        else {
            VerificationClass VerifyClass = new VerificationClass(driver1, extentTest);
            VerifyClass.verifyTextPresent(ClassValidation, "The Class field is required.");
        }
        return new Object[0];
    }

    //Check by Class filter only and "Inactive"

    public Object[] CheckDataByClassInactive() throws IOException, SQLException, InterruptedException {

        ActionClass actionClass = new ActionClass(this.driver1, extentTest);
        //actionClass.clickOnObject(this.ClickOnStudentInformation);
        Thread.sleep(5000);
        actionClass.clickOnObject(this.ClickOnStudentDetails);
        actionClass.clickOnObject(this.SelectClass);
        Thread.sleep(5000);
        actionClass.clickOnObject(this.ClassValue);
        Thread.sleep(1000);
        if (ClassValue.isSelected() == true) {

            actionClass.clickOnObject(this.ClickonStatus);
            actionClass.clickOnObject(this.ClickonStatusInactive);
            actionClass.clickOnObject(this.ClickOnSearch1);
            List<WebElement> ListStudent = driver1.findElements(By.xpath("//*[@id=\"DataTables_Table_0\"]/tbody/tr"));
            int ClassStudentsize = ListStudent.size();
            ArrayList<String> listNames1 = new ArrayList<String>();
            for (int i = 1; i <= ClassStudentsize; i++) {
                String s = driver1.findElement(By.xpath("//*[@id=\"DataTables_Table_0\"]/tbody/tr[" + i + "]/td[1]")).getText();
                System.out.println("Value in list is: " + s);
                listNames1.add(driver1.findElement(By.xpath("//*[@id=\"DataTables_Table_0\"]/tbody/tr[" + i + "]/td[1]")).getText());
            }

            ArrayList<Integer> CheckDataByClassInactiveList1= new ArrayList<Integer>(listNames1.size());
            for(String myInt : listNames1 ){
                CheckDataByClassInactiveList1.add(Integer.valueOf(myInt));
            }
            System.out.println(CheckDataByClassInactiveList1);
            DatabaseFunctions DAB = new DatabaseFunctions(extentTest);
            conn = DAB.connect();
            statement = conn.createStatement();
            System.out.println(ClassValue.getText());
            String B1 = ClassValue.getText();
            String searchstudentbyclass = "SELECT student_session.id, student_session.session_id, students.firstname, students.lastname, students.is_active, students.is_inactive, classes.class, students.admission_no FROM `student_session` INNER JOIN students ON student_session.student_id = students.id INNER JOIN classes ON student_session.class_id=classes.id WHERE student_session.session_id='15' AND classes.class='" + B1 + "' AND student_session.is_inactive='yes' ORDER BY `students`.`admission_no`";
            System.out.println(searchstudentbyclass);
            ResultSet queryRs4 = statement.executeQuery(searchstudentbyclass);
            ArrayList<String> listNames = new ArrayList<String>();
            while (queryRs4.next()) {
                String s1 = null;
                s1 = queryRs4.getString("students.admission_no");
                System.out.println("Admission no. is " + s1);
                listNames.add(queryRs4.getString("students.admission_no"));
            }
            ArrayList<Integer> CheckDataByClassInactiveList= new ArrayList<Integer>(listNames.size());
            for(String myInt : listNames ){
                CheckDataByClassInactiveList.add(Integer.valueOf(myInt));
            }
            Collections.sort(CheckDataByClassInactiveList);
            System.out.println(CheckDataByClassInactiveList);
            System.out.println(CheckDataByClassInactiveList1.equals(CheckDataByClassInactiveList));
            actionClass.CompareList(CheckDataByClassInactiveList, CheckDataByClassInactiveList1);

            actionClass.captureScreen("Default Keyword search");

            return listNames.toArray();
        } else {
            VerificationClass VerifyClass = new VerificationClass(driver1, extentTest);
            VerifyClass.verifyTextPresent(ClassValidation, "The Class field is required.");
        }
        return new Object[0];
    }

    //Check By searching data in different field

    public Object[] CheckForKeyWordSearch() throws IOException, SQLException, InterruptedException{

        ActionClass actionClass = new ActionClass(this.driver1, extentTest);
        //actionClass.clickOnObject(this.ClickOnStudentInformation);
        Thread.sleep(5000);
        actionClass.clickOnObject(this.ClickOnStudentDetails);
        Thread.sleep(5000);
        actionClass.clickOnObject(this.KeywordSearchtxt);
        actionClass.setValueinTextbox(KeywordSearchtxt,"Riddhi");
        actionClass.clickOnObject(this.ClickOnbtnSearch);

        List<WebElement> ListStudent1 = driver1.findElements(By.xpath("//*[@id=\"DataTables_Table_0\"]/tbody/tr"));
        int listsize = ListStudent1.size();
        ArrayList<String> KeywordListF = new ArrayList<String>();
        for (int i = 1; i <= listsize; i++) {
            String s = driver1.findElement(By.xpath("//table[@id='DataTables_Table_0']/tbody/tr[" + i + "]/td[1]")).getText();
            System.out.println("Value in list is: " + s);
            KeywordListF.add(driver1.findElement(By.xpath("//table[@id='DataTables_Table_0']/tbody/tr[" + i + "]/td[1]")).getText());
        }
        ArrayList<Integer> KeywordSearchIntegerList1= new ArrayList<Integer>(KeywordListF.size());
        for(String myInt : KeywordListF ){
            KeywordSearchIntegerList1.add(Integer.valueOf(myInt));
        }
        System.out.println(KeywordSearchIntegerList1);
        DatabaseFunctions DAB = new DatabaseFunctions(extentTest);
        conn = DAB.connect();
        statement = conn.createStatement();
        System.out.println(KeywordSearchtxt.getText());
        String B22 = KeywordSearchtxt.getText();
        String searchstudentbykeyword = "SELECT student_session.id, student_session.session_id, students.firstname, students.lastname, students.is_active, students.is_inactive, students.admission_no FROM `student_session` INNER JOIN students ON student_session.student_id = students.id WHERE (students.firstname = 'riddhi' OR students.lastname = 'riddhi' OR students.guardian_name = 'riddhi' OR students.adhar_no = 'riddhi' OR students.samagra_id = 'riddhi' OR students.roll_no = 'riddhi' OR students.admission_no = 'riddhi') AND student_session.session_id='15' ORDER BY students.admission_no ASC";
        ResultSet queryRs7 = statement.executeQuery(searchstudentbykeyword);
        ArrayList<String> KeywordList = new ArrayList<String>();
        while (queryRs7.next()) {
            String s1 = null;
            s1 = queryRs7.getString("students.admission_no");
            System.out.println("Admission no. is " + s1);
            KeywordList.add(queryRs7.getString("students.admission_no"));
        }
        ArrayList<Integer> KeywordSearchIntegerList= new ArrayList<Integer>(KeywordList.size());
        for(String myInt : KeywordList ){
            KeywordSearchIntegerList.add(Integer.valueOf(myInt));
        }
        Collections.sort(KeywordSearchIntegerList);
        System.out.println(KeywordSearchIntegerList);
        System.out.println(KeywordList.equals(KeywordListF));
        actionClass.CompareList(KeywordSearchIntegerList, KeywordSearchIntegerList1);

        actionClass.captureScreen("Default Keyword search");

        return KeywordListF.toArray();
    }

    public Object[] ListStudentDetail() throws IOException, SQLException, InterruptedException {

        ActionClass actionClass = new ActionClass(this.driver1, extentTest);
       actionClass.clickOnObject(this.ClickOnStudentInformation);
        Thread.sleep(5000);
        actionClass.clickOnObject(this.ClickOnStudentDetails);
        actionClass.clickOnObject(this.ClickOnbtnSearch);
        JavascriptExecutor jsetaskscore1 = (JavascriptExecutor) driver1;
        jsetaskscore1.executeScript("scrollBy(0, 500)");
        Thread.sleep(2000);
        List<WebElement> ListStudent = driver1.findElements(By.xpath("//*[@id=\"DataTables_Table_0\"]/tbody/tr"));

        int listsize = ListStudent.size();
        ArrayList<String> listNames1 = new ArrayList<String>();
        for (int i = 1; i <= listsize; i++) {
            String s = driver1.findElement(By.xpath("//table[@id='DataTables_Table_0']/tbody/tr[" + i + "]/td[1]")).getText();
            System.out.println("Value in list is: " + s);
            listNames1.add(driver1.findElement(By.xpath("//table[@id='DataTables_Table_0']/tbody/tr[" + i + "]/td[1]")).getText());
        }
        ArrayList<Integer> newIntegerList1= new ArrayList<Integer>(listNames1.size());
        for(String myInt : listNames1 ){
            newIntegerList1.add(Integer.valueOf(myInt));
        }
        System.out.println(newIntegerList1);
        DatabaseFunctions DAB = new DatabaseFunctions(extentTest);
        conn = DAB.connect();
        statement = conn.createStatement();
        String students = "SELECT student_session.id, student_session.session_id, students.firstname, students.lastname, students.is_active, students.is_inactive, classes.class, students.admission_no FROM `student_session` INNER JOIN students ON student_session.student_id = students.id INNER JOIN classes ON student_session.class_id=classes.id WHERE student_session.session_id='15' AND students.is_inactive='no' ORDER BY `students`.`admission_no` ASC";
        queryRs = statement.executeQuery(students);
        ArrayList<String> listNames = new ArrayList<String>();

        while (queryRs.next()) {
            String s1 = null;
            s1 = queryRs.getString("students.admission_no");
            System.out.println("Admission no. is " + s1);
            listNames.add(queryRs.getString("students.admission_no"));
        }
        ArrayList<Integer> newIntegerList= new ArrayList<Integer>(listNames.size());
        for(String myInt : listNames ){
            newIntegerList.add(Integer.valueOf(myInt));
        }
        Collections.sort(newIntegerList);
        System.out.println(newIntegerList);
        System.out.println(newIntegerList1.equals(newIntegerList));
//        System.out.println(listNames.equals(listNames1));
        actionClass.CompareList(newIntegerList,newIntegerList1);
        JavascriptExecutor jsetaskscore2 = (JavascriptExecutor) driver1;
        jsetaskscore2.executeScript("scrollBy(0, 300)");

        actionClass.captureScreen("Default Keyword search");

        return listNames.toArray();
    }
}





