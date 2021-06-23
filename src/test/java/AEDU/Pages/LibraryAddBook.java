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

public class LibraryAddBook {
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

    @FindBy(how = How.XPATH, using = "//*[@id=\"sibe-box\"]/ul[2]/li[10]/ul/li[1]/a")
    private WebElement ClickOnLibraryAddBook;

    @FindBy(how = How.XPATH, using = "//*[@id=\"form1\"]/div[2]/button")
    private WebElement AddBookSaveButton;

    @FindBy(how = How.XPATH, using = "//*[@id=\"form1\"]/div[1]/div[1]/span/p")
    private WebElement BookTitleValidation;

    @FindBy(how = How.XPATH, using = "//*[@id=\"form1\"]/div[1]/div[11]/span/p")
    private WebElement QuantityValidation;

    @FindBy(how = How.XPATH, using = "//*[@id=\"book_title\"]")
    private WebElement BookTitleField;

    @FindBy(how = How.XPATH, using = "//*[@id=\"book_no\"]")
    private WebElement BookNumberField;

    @FindBy(how = How.XPATH, using = "//*[@id=\"isbn_no\"]")
    private WebElement ISBNNumberField;

    @FindBy(how = How.XPATH, using = "//*[@id=\"publish\"]")
    private WebElement PublisherField;

    @FindBy(how = How.XPATH, using = "//*[@id=\"author\"]")
    private WebElement AuthorField;

    @FindBy(how = How.XPATH, using = "//*[@id=\"subject\"]")
    private WebElement SubjectField;

    @FindBy(how = How.XPATH, using = "//*[@id=\"rack_no\"]")
    private WebElement RackNumberField;

    @FindBy(how = How.XPATH, using = "//*[@id=\"qty\"]")
    private WebElement QuantityField;

    @FindBy(how = How.XPATH, using = "//*[@id=\"perunitcost\"]")
    private WebElement BookPriceField;

    @FindBy(how = How.XPATH, using = "//*[@id=\"postdate\"]")
    private WebElement PostDateField;

    @FindBy(how = How.CSS, using = "tr:nth-child(5) > .day:nth-child(5)")
    private WebElement SelectPostDate;

    @FindBy(how = How.XPATH, using = "//*[@id=\"description\"]")
    private WebElement DescriptionField;

    @FindBy(how = How.XPATH, using = "//*[@id=\"form1\"]/div[1]/div[1]")
    private WebElement AddBookSuccessMessage;

    @FindBy(how = How.XPATH, using = "//*[@id=\"sibe-box\"]/ul[2]/li[10]/ul/li[2]/a")
    private WebElement ClickOnBookList;

    @FindBy(how = How.XPATH, using = "//*[@id=\"DataTables_Table_0_filter\"]/label/input")
    private WebElement BookListSearchField;

    @FindBy(how = How.XPATH, using = "//*[@id=\"form1\"]/div[1]/div[1]")
    private WebElement EditBookSuccessMessage;

    @FindBy(how = How.XPATH, using = "//*[@id=\"DataTables_Table_0\"]/tbody/tr/td[11]/a[1]")
    private WebElement EditBookButton;

    @FindBy(how = How.XPATH, using = "(//input[@id='amount'])[3]")
    private WebElement EditQuantityField;

    @FindBy(how = How.XPATH, using = "//*[@id=\"DataTables_Table_0\"]/tbody/tr/td[11]/a[2]")
    private WebElement DeleteButton;

    @FindBy(how = How.XPATH, using = "//*[@id=\"form1\"]/div[1]/div[1]")
    private WebElement DeleteSuccessMessage;

    public LibraryAddBook(WebDriver driver, ExtentTest test) throws InterruptedException, IOException {
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

    //  Test if all the mandatory fields shows validation messages
    public void AddBookValidation() throws InterruptedException, IOException {
        ActionClass actionClass=new ActionClass(driver1, extentTest);
        actionClass.clickOnObject(ClickOnLibraryMenu);
        Thread.sleep(2000);
        actionClass.clickOnObject(ClickOnLibraryAddBook);
        Thread.sleep(2000);
        JavascriptExecutor javascriptExecutor = (JavascriptExecutor) driver1;
        javascriptExecutor.executeScript("arguments[0].scrollIntoView();", AddBookSaveButton);
        Thread.sleep(2000);
        actionClass.clickOnObject(AddBookSaveButton);
        Thread.sleep(2000);
        VerificationClass verificationClass=new VerificationClass(driver1, extentTest);
        verificationClass.verifyTextPresent(BookTitleValidation, "The Book Title field is required.");
        actionClass.captureScreen("LibraryAddBook_1");
        javascriptExecutor.executeScript("scrollBy(0, 400)");
        verificationClass.verifyTextPresent(QuantityValidation, "The Quantity field is required.");
        actionClass.captureScreen("LibraryAddBook_2");
    }

    //  Test if the book is added and compare with database
    public void AddBook(String BookTitle, String BookNumber, String ISBNNo, String Publisher, String Author, String Subject,
                        String RackNumber, String Quantity, String BookPrice, String Description) throws InterruptedException, IOException, SQLException {
        ActionClass actionClass=new ActionClass(driver1, extentTest);
//      actionClass.clickOnObject(ClickOnLibraryMenu);
        Thread.sleep(2000);
        actionClass.clickOnObject(ClickOnLibraryAddBook);
        Thread.sleep(2000);
        actionClass.setValueinTextbox(BookTitleField, BookTitle);
        Thread.sleep(1000);
        actionClass.setValueinTextbox(BookNumberField, BookNumber);
        Thread.sleep(1000);
        actionClass.setValueinTextbox(ISBNNumberField, ISBNNo);
        Thread.sleep(1000);
        actionClass.setValueinTextbox(PublisherField, Publisher);
        Thread.sleep(1000);
        actionClass.setValueinTextbox(AuthorField, Author);
        Thread.sleep(1000);
        actionClass.setValueinTextbox(SubjectField, Subject);
        Thread.sleep(1000);
        actionClass.setValueinTextbox(RackNumberField, RackNumber);
        Thread.sleep(1000);
        actionClass.setValueinTextbox(QuantityField, Quantity);
        Thread.sleep(1000);
        JavascriptExecutor javascriptExecutor = (JavascriptExecutor) driver1;
        javascriptExecutor.executeScript("arguments[0].scrollIntoView();", AddBookSaveButton);
        Thread.sleep(2000);
        actionClass.setValueinTextbox(BookPriceField, BookPrice);
        Thread.sleep(1000);
        actionClass.clickOnObject(PostDateField);
        Thread.sleep(1000);
        actionClass.clickOnObject(SelectPostDate);
        Thread.sleep(1000);
        actionClass.setValueinTextbox(DescriptionField, Description);
        Thread.sleep(1000);
        actionClass.clickOnObject(AddBookSaveButton);
        Thread.sleep(2000);
        VerificationClass verificationClass = new VerificationClass(driver1, extentTest);
        verificationClass.verifyTextPresent(AddBookSuccessMessage, "Book added successfully");
        actionClass.captureScreen("LibraryAddBook_3");
        Thread.sleep(1000);
        actionClass.clickOnObject(ClickOnBookList);
        Thread.sleep(2000);
        actionClass.setValueinTextbox(BookListSearchField, ISBNNo);
        Thread.sleep(3000);
        actionClass.captureScreen("LibraryAddBook_4");

//      Compare the list with db
        Boolean isPresent = driver1.findElements(By.xpath("//*[@id=\"DataTables_Table_0\"]/tbody/tr/td[2]")).size() > 0;
        ArrayList<String> BookListinFront = new ArrayList<String>();
        ArrayList<String> BookNumberinFront = new ArrayList<String>();
        ArrayList<String> ISBNNumberList = new ArrayList<String>();
        ArrayList<String> PublisherList = new ArrayList<String>();
        if(isPresent==true){
            List<WebElement> BookList = driver1.findElements(By.xpath("//*[@id=\"DataTables_Table_0\"]/tbody/tr"));
            Thread.sleep(3000);
            int listsize = BookList.size();
            for (int i = 1; i <= listsize; i++) {
                BookListinFront.add(driver1.findElement(By.xpath("//*[@id=\"DataTables_Table_0\"]/tbody/tr["+i+"]/td[1]")).getText());
                BookNumberinFront.add(driver1.findElement(By.xpath("//*[@id=\"DataTables_Table_0\"]/tbody/tr["+i+"]/td[2]")).getText());
                ISBNNumberList.add(driver1.findElement(By.xpath("//*[@id=\"DataTables_Table_0\"]/tbody/tr["+i+"]/td[3]")).getText());
                PublisherList.add(driver1.findElement(By.xpath("//*[@id=\"DataTables_Table_0\"]/tbody/tr["+i+"]/td[4]")).getText());
            }
            System.out.println(BookListinFront);
            System.out.println(BookNumberinFront);
            System.out.println(ISBNNumberList);
            System.out.println(PublisherList);
        }else{
            System.out.println("No record found");
        }
        DatabaseFunctions DAB = new DatabaseFunctions(extentTest);
        conn = DAB.connect();
        statement = conn.createStatement();
        String students = "SELECT * FROM `books` ORDER BY ID DESC LIMIT 1";
        queryRs = statement.executeQuery(students);
        ArrayList<String> BookListinDB = new ArrayList<String>();
        ArrayList<String> BookNumberinDB = new ArrayList<String>();
        ArrayList<String> ISBNNumberinDB = new ArrayList<String>();
        ArrayList<String> PublisherListinDB = new ArrayList<String>();
        while (queryRs.next()){
            BookListinDB.add(queryRs.getString("book_title"));
            BookNumberinDB.add(queryRs.getString("book_no"));
            ISBNNumberinDB.add(queryRs.getString("isbn_no"));
            PublisherListinDB.add(queryRs.getString("publish"));
        }
        System.out.println(BookListinDB);
        System.out.println(BookNumberinDB);
        System.out.println(ISBNNumberinDB);
        System.out.println(PublisherListinDB);

//      Compare both front and database lists
        if(BookListinFront.equals(BookListinDB) || BookNumberinFront.equals(BookNumberinDB) || ISBNNumberList.equals(ISBNNumberinDB) || PublisherList.equals(PublisherListinDB)){
            System.out.println("The added book in list matches with database");
            extentTest.log(Status.PASS,"The added book in list matches with database");
        }else{
            System.out.println("The added book in list does not match with database");
            extentTest.log(Status.FAIL,"The added book in list does not match with database");
        }
    }

    //  Test by editing book and compare with database
    public void EditBook(String ISBNNumber, String Quantity) throws InterruptedException, IOException, SQLException {
        ActionClass actionClass=new ActionClass(driver1, extentTest);
//      actionClass.clickOnObject(ClickOnLibraryMenu);
        Thread.sleep(2000);
        actionClass.clickOnObject(ClickOnBookList);
        Thread.sleep(2000);
        actionClass.setValueinTextbox(BookListSearchField, ISBNNumber);
        Thread.sleep(2000);
        actionClass.clickOnObject(EditBookButton);
        Thread.sleep(3000);
        JavascriptExecutor javascriptExecutor = (JavascriptExecutor) driver1;
        javascriptExecutor.executeScript("arguments[0].scrollIntoView();", AddBookSaveButton);
        Thread.sleep(1000);
        actionClass.setValueinTextbox(EditQuantityField, Quantity);
        Thread.sleep(2000);
        actionClass.clickOnObject(AddBookSaveButton);
        Thread.sleep(2000);
        VerificationClass verificationClass = new VerificationClass(driver1, extentTest);
        verificationClass.verifyTextPresent(EditBookSuccessMessage, "Book updated successfully");
        actionClass.captureScreen("LibraryAddBook_5");
        actionClass.clickOnObject(ClickOnBookList);
        Thread.sleep(2000);
        actionClass.setValueinTextbox(BookListSearchField, ISBNNumber);
        Thread.sleep(2000);
        actionClass.captureScreen("LibraryAddBook_6");

//      Compare the list with db
        Boolean isPresent = driver1.findElements(By.xpath("//*[@id=\"DataTables_Table_0\"]/tbody/tr/td[2]")).size() > 0;
        ArrayList<String> BookListinFront = new ArrayList<String>();
        ArrayList<String> ISBNNumberList = new ArrayList<String>();
        ArrayList<String> QuantityList = new ArrayList<String>();
        if(isPresent==true){
            List<WebElement> BookList = driver1.findElements(By.xpath("//*[@id=\"DataTables_Table_0\"]/tbody/tr"));
            Thread.sleep(3000);
            int listsize = BookList.size();
            for (int i = 1; i <= listsize; i++) {
                BookListinFront.add(driver1.findElement(By.xpath("//*[@id=\"DataTables_Table_0\"]/tbody/tr["+i+"]/td[1]")).getText());
                ISBNNumberList.add(driver1.findElement(By.xpath("//*[@id=\"DataTables_Table_0\"]/tbody/tr["+i+"]/td[3]")).getText());
                QuantityList.add(driver1.findElement(By.xpath("//*[@id=\"DataTables_Table_0\"]/tbody/tr["+i+"]/td[8]")).getText());
            }
            System.out.println(BookListinFront);
            System.out.println(ISBNNumberList);
            System.out.println(QuantityList);
        }else{
            System.out.println("No record found");
        }
        DatabaseFunctions DAB = new DatabaseFunctions(extentTest);
        conn = DAB.connect();
        statement = conn.createStatement();
        String students = "SELECT * FROM `books` WHERE isbn_no='"+ ISBNNumber +"'";
        queryRs = statement.executeQuery(students);
        ArrayList<String> BookListinDB = new ArrayList<String>();
        ArrayList<String> ISBNNumberinDB = new ArrayList<String>();
        ArrayList<String> QuantityListinDB = new ArrayList<String>();
        while (queryRs.next()){
            BookListinDB.add(queryRs.getString("book_title"));
            ISBNNumberinDB.add(queryRs.getString("isbn_no"));
            QuantityListinDB.add(queryRs.getString("qty"));
        }
        System.out.println(BookListinDB);
        System.out.println(ISBNNumberinDB);
        System.out.println(QuantityListinDB);

//      Compare both front and database lists
        if(BookListinFront.equals(BookListinDB) || ISBNNumberList.equals(ISBNNumberinDB) || QuantityList.equals(QuantityListinDB)){
            System.out.println("The added book in list matches with database");
            extentTest.log(Status.PASS,"The added book in list matches with database");
        }else{
            System.out.println("The added book in list does not match with database");
            extentTest.log(Status.FAIL,"The added book in list does not match with database");
        }
    }

    //  Test by deleting book and compare with database
    public void DeleteBook(String BookName) throws InterruptedException, IOException, SQLException, AWTException {
        ActionClass actionClass=new ActionClass(driver1, extentTest);
        //actionClass.clickOnObject(ClickOnLibraryMenu);
        Thread.sleep(2000);
        actionClass.clickOnObject(ClickOnBookList);
        Thread.sleep(2000);
        actionClass.setValueinTextbox(BookListSearchField, BookName);
        Thread.sleep(2000);
        actionClass.clickOnObject(DeleteButton);
        Thread.sleep(5000);
        Alert alert = driver1.switchTo().alert();
        String alertMessage= driver1.switchTo().alert().getText();
        System.out.println(alertMessage);
        String expectedAlertMessage= "Are you sure you want to delete this item?";
        if(alertMessage.equals(expectedAlertMessage)){
            System.out.println("The expected alert message matches with actual alert message.");
            extentTest.log(Status.PASS, "The expected alert message matches with actual alert message.");
        }else{
            System.out.println("The expected alert message does not match with actual alert message.");
            extentTest.log(Status.FAIL, "The expected alert message does not match with actual alert message.");
        }
        Thread.sleep(3000);
        actionClass.captureScreenUsingRobot("LibraryAddBook_7");
        alert.accept();
        Thread.sleep(3000);
        VerificationClass verificationClass = new VerificationClass(driver1, extentTest);
        verificationClass.verifyTextPresent(DeleteSuccessMessage, "Book deleted successfully");


//      Test if the entry is not in list
        actionClass.setValueinTextbox(BookListSearchField, BookName);
        Thread.sleep(2000);
        actionClass.captureScreen("LibraryAddBook_9");
        Boolean isPresent = driver1.findElements(By.xpath("//*[@id=\"DataTables_Table_0\"]/tbody/tr[1]/td[2]")).size() > 0;
        ArrayList<String> ListBookNameinFront = new ArrayList<String>();
        if(isPresent==true){
            List<WebElement> ListBook = driver1.findElements(By.xpath("//*[@id=\"DataTables_Table_0\"]/tbody/tr"));
            Thread.sleep(3000);
            int listsize = ListBook.size();
            for (int i = 1; i <= listsize; i++) {
                String s = driver1.findElement(By.xpath("//*[@id=\"DataTables_Table_0\"]/tbody/tr[" + i + "]/td[1]")).getText();
                System.out.println("Value in list is: " + s);
                ListBookNameinFront.add(driver1.findElement(By.xpath("//*[@id=\"DataTables_Table_0\"]/tbody/tr[" + i + "]/td[1]")).getText());
            }
            System.out.println(ListBookNameinFront);
            System.out.println("The subject is added.");
            extentTest.log(Status.INFO, "The subject is added and present in the list.");
        }else{
            System.out.println("There is no such entry in the list");
            extentTest.log(Status.PASS, "There is no such entry in the list");
        }

//      The List of teacher in Database
        DatabaseFunctions DAB = new DatabaseFunctions(extentTest);
        conn = DAB.connect();
        statement = conn.createStatement();
        String books = "SELECT * FROM `books` WHERE book_title = '" + BookName + "'";
        queryRs = statement.executeQuery(books);
        ArrayList<String> ListBookNameinDB = new ArrayList<String>();
        if (queryRs.next()) {
            String booknames = null;
            booknames = queryRs.getString("book_title");
            System.out.println("Value in database list: " + booknames);
            ListBookNameinDB.add(queryRs.getString("book_title"));
            System.out.println(ListBookNameinDB);
            extentTest.log(Status.PASS, "The entered data is in database");
        } else{
            System.out.println("There is no such entry in database.");
            extentTest.log(Status.FAIL,"There is no such entry in database.");
        }
        actionClass.CompareStringList(ListBookNameinFront, ListBookNameinDB);
    }

    //  Test if the Book List in front is same as database
    public void CompareBookList() throws InterruptedException, SQLException {
        ActionClass actionClass = new ActionClass(driver1, extentTest);
//      actionClass.clickOnObject(ClickOnLibraryMenu);
        Thread.sleep(2000);
        actionClass.clickOnObject(ClickOnBookList);
        Thread.sleep(4000);

//      Compare the list with db
        Boolean isPresent = driver1.findElements(By.xpath("//*[@id=\"DataTables_Table_0\"]/tbody/tr/td[2]")).size() > 0;
        ArrayList<String> BookListinFront = new ArrayList<String>();
        if(isPresent==true){
            List<WebElement> BookList = driver1.findElements(By.xpath("//*[@id=\"DataTables_Table_0\"]/tbody/tr"));
            Thread.sleep(3000);
            int listsize = BookList.size();
            for (int i = 1; i <= listsize; i++) {
                BookListinFront.add(driver1.findElement(By.xpath("//*[@id=\"DataTables_Table_0\"]/tbody/tr["+i+"]/td[1]")).getText());
            }
            Collections.sort(BookListinFront);
            System.out.println(BookListinFront);
        }else{
            System.out.println("No record found");
        }
        DatabaseFunctions DAB = new DatabaseFunctions(extentTest);
        conn = DAB.connect();
        statement = conn.createStatement();
        String students = "SELECT * FROM `books`";
        queryRs = statement.executeQuery(students);
        ArrayList<String> BookListinDB = new ArrayList<String>();
        while (queryRs.next()){
            BookListinDB.add(queryRs.getString("book_title"));
        }
        Collections.sort(BookListinDB);
        System.out.println(BookListinDB);

//      Compare both front and database lists
        if(BookListinFront.equals(BookListinDB)){
            System.out.println("The added book in list matches with database");
            extentTest.log(Status.PASS,"The added book in list matches with database");
        }else{
            System.out.println("The added book in list does not match with database");
            extentTest.log(Status.FAIL,"The added book in list does not match with database");
        }
    }
}