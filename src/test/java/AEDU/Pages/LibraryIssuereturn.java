package AEDU.Pages;

import AEDU.Actions.ActionClass;
import AEDU.Actions.VerificationClass;
import AEDU.Utilities.DatabaseFunctions;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import org.junit.experimental.theories.Theories;
import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

import java.io.IOException;
import java.sql.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class LibraryIssuereturn {
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

    @FindBy(how = How.XPATH, using = "//*[@id=\"sibe-box\"]/ul[2]/li[10]/ul/li[3]/a")
    private WebElement ClickOnIssueReturn;

    @FindBy(how = How.XPATH, using = "//*[@id=\"members_filter\"]/label/input")
    private WebElement SearchField;

    @FindBy(how = How.XPATH, using = "//*[@id=\"members\"]/tbody/tr/td[7]/a")
    private WebElement IssueButton;

    @FindBy(how = How.XPATH, using = "//*[@id=\"dateto\"]")
    private WebElement ReturnDateField;

    @FindBy(how = How.XPATH, using = "//*[@id=\"form1\"]/div[2]/button")
    private WebElement IssueAddButton;

    @FindBy(how = How.XPATH, using = "//*[@id=\"form1\"]/div[1]/div[1]")
    private WebElement IssueSuccessMessage;

    @FindBy(how = How.XPATH, using = "//*[@id=\"form1\"]/div[1]/div[1]")
    private WebElement IssueReturnMessage;

    @FindBy(how = How.XPATH, using = "//*[@id=\"DataTables_Table_0\"]/tbody/tr[1]/td[5]/a")
    private WebElement ReturnButton;


    public LibraryIssuereturn(WebDriver driver, ExtentTest test) throws InterruptedException, IOException {
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

    //  Test if all the teachers and students in the list are same in the database.
    public void ComparetheMembersList() throws InterruptedException, IOException, SQLException {
        ActionClass actionClass = new ActionClass(driver1, extentTest);
        WebElement view = driver1.findElement(By.xpath("//*[@id=\"sibe-box\"]/ul[2]/li[6]/a"));
        JavascriptExecutor javascriptExecutor = (JavascriptExecutor) driver1;
        javascriptExecutor.executeScript("arguments[0].scrollIntoView();", view);
        //actionClass.clickOnObject(ClickOnLibraryMenu);
        Thread.sleep(2000);
        actionClass.clickOnObject(ClickOnIssueReturn);
        Thread.sleep(3000);
//      Compare teacher member list with database
        actionClass.setValueinTextbox(SearchField, "teacher");
        Thread.sleep(1000);
        actionClass.captureScreen("LibraryIssueReturn_1");
        JavascriptExecutor javascriptExecutor1 = (JavascriptExecutor) driver1;
        javascriptExecutor1.executeScript("scrollBy(0, 300)");
        actionClass.captureScreen("LibraryIssueReturn_2");
        Thread.sleep(2000);
        Boolean isPresent = driver1.findElements(By.xpath("//*[@id=\"members\"]/tbody/tr[1]/td[2]")).size() > 0;
        if(isPresent==true){
            ArrayList<String> ListLCNinFront = new ArrayList<>();
            List<WebElement> ListMember = driver1.findElements(By.xpath("//*[@id=\"members\"]/tbody/tr"));
            Thread.sleep(3000);
            int listsize = ListMember.size();
            for (int i = 1; i <= listsize; i++) {
                ListLCNinFront.add(driver1.findElement(By.xpath("//*[@id=\"members\"]/tbody/tr["+i+"]/td[2]")).getText());
            }
            Collections.sort(ListLCNinFront);
            System.out.println(ListLCNinFront);
            //Database list
            DatabaseFunctions DAB = new DatabaseFunctions(extentTest);
            conn = DAB.connect();
            statement = conn.createStatement();
            String librarians = "SELECT * FROM `libarary_members` WHERE member_type='teacher'  \n" +
                    "ORDER BY `libarary_members`.`id` ASC";
            queryRs = statement.executeQuery(librarians);
            ArrayList<String> ListLCNinDB = new ArrayList<String>();
            while(queryRs.next()) {
                ListLCNinDB.add(queryRs.getString("library_card_no"));
            }
            Collections.sort(ListLCNinDB);
            System.out.println(ListLCNinDB);
            actionClass.CompareStringList(ListLCNinDB, ListLCNinFront);
        }else{
            System.out.println("There is no such entry in the list");
            extentTest.log(Status.PASS, "There is no such entry in the list");
        }

//      Compare student member list with database
        actionClass.setValueinTextbox(SearchField, "student");
        Thread.sleep(1000);
        actionClass.captureScreen("LibraryIssueReturn_3");
        javascriptExecutor1.executeScript("scrollBy(0, 300)");
        actionClass.captureScreen("LibraryIssueReturn_4");
        Thread.sleep(2000);
        Boolean isPresent1 = driver1.findElements(By.xpath("//*[@id=\"members\"]/tbody/tr[1]/td[2]")).size() > 0;
        if(isPresent1==true){
            ArrayList<String> ListLCNinFront1 = new ArrayList<>();
            List<WebElement> ListMember1 = driver1.findElements(By.xpath("//*[@id=\"members\"]/tbody/tr"));
            Thread.sleep(3000);
            int listsize = ListMember1.size();
            for (int i = 1; i <= listsize; i++) {
                ListLCNinFront1.add(driver1.findElement(By.xpath("//*[@id=\"members\"]/tbody/tr["+i+"]/td[2]")).getText());
            }
            Collections.sort(ListLCNinFront1);
            System.out.println(ListLCNinFront1);
            //Database list
            DatabaseFunctions DAB = new DatabaseFunctions(extentTest);
            conn = DAB.connect();
            statement = conn.createStatement();
            String librarians = "SELECT * FROM `libarary_members` WHERE member_type='student'  \n" +
                    "ORDER BY `libarary_members`.`id` ASC";
            queryRs = statement.executeQuery(librarians);
            ArrayList<String> ListLCNinDB1 = new ArrayList<String>();
            while(queryRs.next()) {
                ListLCNinDB1.add(queryRs.getString("library_card_no"));
            }
            Collections.sort(ListLCNinDB1);
            System.out.println(ListLCNinDB1);
            actionClass.CompareStringList(ListLCNinDB1, ListLCNinFront1);
        }else{
            System.out.println("There is no such entry in the list");
            extentTest.log(Status.PASS, "There is no such entry in the list");
        }
    }

    //  Test the issued book logs.
    public void IssueBookLogs(String LCNumber, String BookName, String ReturnDate) throws InterruptedException, IOException, SQLException {
        ActionClass actionClass = new ActionClass(driver1, extentTest);
        WebElement view = driver1.findElement(By.xpath("//*[@id=\"sibe-box\"]/ul[2]/li[6]/a"));
        JavascriptExecutor javascriptExecutor = (JavascriptExecutor) driver1;
        javascriptExecutor.executeScript("arguments[0].scrollIntoView();", view);
//        actionClass.clickOnObject(ClickOnLibraryMenu);
        Thread.sleep(2000);
        actionClass.clickOnObject(ClickOnIssueReturn);
        Thread.sleep(3000);
        actionClass.setValueinTextbox(SearchField, LCNumber);
        Thread.sleep(2000);
        Boolean isPresent1 = driver1.findElements(By.xpath("//*[@id=\"members\"]/tbody/tr/td[2]")).size() > 0;
        if(isPresent1==true) {
            actionClass.clickOnObject(IssueButton);
            Thread.sleep(2000);
            WebElement SelectBook = driver1.findElement(By.xpath("//*[@name='book_id']//option[contains(text(),'"+BookName+"')]"));
            System.out.println(SelectBook);
            actionClass.clickOnObject(SelectBook);
            Thread.sleep(2000);
            actionClass.setValueinTextbox(ReturnDateField, ReturnDate);
            Thread.sleep(1000);
            actionClass.clickOnObject(IssueAddButton);
            Thread.sleep(2000);
            VerificationClass verificationClass = new VerificationClass(driver1, extentTest);
            verificationClass.verifyTextPresent(IssueSuccessMessage, "Book issued successfully.");
            WebElement view1 = driver1.findElement(By.xpath("//*[contains(text(), \"Records\")]"));
            JavascriptExecutor javascriptExecutor1 = (JavascriptExecutor) driver1;
            javascriptExecutor1.executeScript("arguments[0].scrollIntoView();",view1);
            Thread.sleep(1000);
            actionClass.captureScreen("LibraryIssueReturn_5");
            Boolean isPresent = driver1.findElements(By.xpath("//*[@id=\"DataTables_Table_0\"]/tbody/tr/td[2]")).size() > 0;
            ArrayList<String> BookTitleinLog = new ArrayList<String>();
            ArrayList<String> BookReturnDate = new ArrayList<String>();
            if(isPresent==true){
                List<WebElement> ListLog = driver1.findElements(By.xpath("//*[@id=\"DataTables_Table_0\"]/tbody/tr"));
                Thread.sleep(3000);
                String memberid = null;
                int listsize = ListLog.size();
                for (int i = 1; i <= listsize; i++) {
                    memberid = driver1.findElement(By.xpath("/html/body/div[1]/div[1]/section[2]/div/div[1]/div/div/ul/li[1]/a")).getText();
                    BookTitleinLog.add(driver1.findElement(By.xpath("//*[@id=\"DataTables_Table_0\"]/tbody/tr["+i+"]/td[1]")).getText());
                    BookReturnDate.add(driver1.findElement(By.xpath("//*[@id=\"DataTables_Table_0\"]/tbody/tr["+i+"]/td[4]")).getText());
                }
                Collections.sort(BookTitleinLog);
                Collections.sort(BookReturnDate);
                System.out.println(BookTitleinLog);
                System.out.println(BookReturnDate);
                DatabaseFunctions DAB = new DatabaseFunctions(extentTest);
                conn = DAB.connect();
                statement = conn.createStatement();
                String librarians = "SELECT * FROM `book_issues` \n" +
                        "JOIN books ON books.id=book_issues.book_id WHERE member_id="+memberid+"";
                queryRs = statement.executeQuery(librarians);
                ArrayList<String> BookTitleinDB = new ArrayList<String>();
                ArrayList<String> BookReturnDateinDB = new ArrayList<String>();
                Date returndate = null;
                while(queryRs.next()) {
                    BookTitleinDB.add(queryRs.getString("books.book_title"));
                    returndate = queryRs.getDate("book_issues.return_date");
                    DateFormat formatter = new SimpleDateFormat("dd.MM.yyyy");
                    String returndate1= formatter.format(returndate);
                    BookReturnDateinDB.add(returndate1);
                }
                Collections.sort(BookTitleinDB);
                Collections.sort(BookReturnDateinDB);
                System.out.println(BookTitleinDB);
                System.out.println(BookReturnDateinDB);
                actionClass.CompareStringList(BookTitleinDB, BookTitleinLog);
                actionClass.CompareStringList(BookReturnDateinDB, BookReturnDate);
            }
            else{
                System.out.println("There is no book issued logs");
                extentTest.log(Status.PASS, "There is no book issued logs");
            }
        }else{
            System.out.println("There is no such member");
            extentTest.log(Status.FAIL,"There is no such member");
        }
    }
    //  Test book logs after returning.
    public void ReturnBookLogs(String LCNumber) throws InterruptedException, IOException, SQLException {
        ActionClass actionClass = new ActionClass(driver1, extentTest);
        WebElement view = driver1.findElement(By.xpath("//*[@id=\"sibe-box\"]/ul[2]/li[6]/a"));
        JavascriptExecutor javascriptExecutor = (JavascriptExecutor) driver1;
        javascriptExecutor.executeScript("arguments[0].scrollIntoView();", view);
//        actionClass.clickOnObject(ClickOnLibraryMenu);
        Thread.sleep(2000);
        actionClass.clickOnObject(ClickOnIssueReturn);
        Thread.sleep(3000);
        actionClass.setValueinTextbox(SearchField, LCNumber);
        Thread.sleep(2000);
        Boolean isPresent1 = driver1.findElements(By.xpath("//*[@id=\"members\"]/tbody/tr/td[2]")).size() > 0;
        if(isPresent1==true) {
            actionClass.clickOnObject(IssueButton);
            Thread.sleep(2000);
            WebElement view1 = driver1.findElement(By.xpath("//*[contains(text(), \"Records\")]"));
            JavascriptExecutor javascriptExecutor1 = (JavascriptExecutor) driver1;
            javascriptExecutor1.executeScript("arguments[0].scrollIntoView();",view1);
            Thread.sleep(2000);
            actionClass.clickOnObject(ReturnButton);
            Thread.sleep(1000);
            Alert alert = driver1.switchTo().alert();
            Thread.sleep(1000);
            alert.accept();
            Thread.sleep(2000);
            WebElement view2 = driver1.findElement(By.xpath("//*[contains(text(), \"Records\")]"));
            javascriptExecutor1.executeScript("arguments[0].scrollIntoView();",view2);
            Thread.sleep(1000);
            actionClass.captureScreen("LibraryIssueReturn_6");
            Boolean isPresent = driver1.findElements(By.xpath("//*[@id=\"DataTables_Table_0\"]/tbody/tr/td[2]")).size() > 0;
            ArrayList<String> BookTitleinLog = new ArrayList<String>();
            ArrayList<String> BookReturnDate = new ArrayList<String>();
            if(isPresent==true){
                List<WebElement> ListLog = driver1.findElements(By.xpath("//*[@id=\"DataTables_Table_0\"]/tbody/tr"));
                Thread.sleep(3000);
                String memberid = null;
                int listsize = ListLog.size();
                for (int i = 1; i <= listsize; i++) {
                    memberid = driver1.findElement(By.xpath("/html/body/div[1]/div[1]/section[2]/div/div[1]/div/div/ul/li[1]/a")).getText();
                    BookTitleinLog.add(driver1.findElement(By.xpath("//*[@id=\"DataTables_Table_0\"]/tbody/tr["+i+"]/td[1]")).getText());
                    BookReturnDate.add(driver1.findElement(By.xpath("//*[@id=\"DataTables_Table_0\"]/tbody/tr["+i+"]/td[4]")).getText());
                }
                Collections.sort(BookTitleinLog);
                Collections.sort(BookReturnDate);
                System.out.println(BookTitleinLog);
                System.out.println(BookReturnDate);
                DatabaseFunctions DAB = new DatabaseFunctions(extentTest);
                conn = DAB.connect();
                statement = conn.createStatement();
                String librarians = "SELECT * FROM `book_issues` \n" +
                        "JOIN books ON books.id=book_issues.book_id WHERE member_id="+memberid+"";
                queryRs = statement.executeQuery(librarians);
                ArrayList<String> BookTitleinDB = new ArrayList<String>();
                ArrayList<String> BookReturnDateinDB = new ArrayList<String>();
                Date returndate = null;
                while(queryRs.next()) {
                    BookTitleinDB.add(queryRs.getString("books.book_title"));
                    returndate = queryRs.getDate("book_issues.return_date");
                    DateFormat formatter = new SimpleDateFormat("dd.MM.yyyy");
                    String returndate1= formatter.format(returndate);
                    BookReturnDateinDB.add(returndate1);
                }
                Collections.sort(BookTitleinDB);
                Collections.sort(BookReturnDateinDB);
                System.out.println(BookTitleinDB);
                System.out.println(BookReturnDateinDB);
                actionClass.CompareStringList(BookTitleinDB, BookTitleinLog);
                actionClass.CompareStringList(BookReturnDateinDB, BookReturnDate);
            }
            else{
                System.out.println("There is no book issued logs");
                extentTest.log(Status.PASS, "There is no book issued logs");
            }
        }else{
            System.out.println("There is no such member");
            extentTest.log(Status.FAIL,"There is no such member");
        }
    }


}