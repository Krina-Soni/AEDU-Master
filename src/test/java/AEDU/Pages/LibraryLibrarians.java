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
import java.sql.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class LibraryLibrarians {
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

    @FindBy(how = How.XPATH, using = "//*[@id=\"sibe-box\"]/ul[2]/li[10]/ul/li[6]/a")
    private WebElement ClickOnLibrarian;

    @FindBy(how = How.CSS, using = ".form-group:nth-child(2) > #category")
    private WebElement LibrarianNameField;

    @FindBy(how = How.CSS, using = ".form-group:nth-child(3) > #category")
    private WebElement EmailField;

    @FindBy(how = How.XPATH, using = "//*[@id=\"form1\"]/div[1]/div[3]/select")
    private WebElement ClickGender;

    @FindBy(how = How.XPATH, using = "//*[@id=\"form1\"]/div[1]/div[3]/select/option[3]")
    private WebElement SelectFemaleGender;

    @FindBy(how = How.XPATH, using = "//*[@id=\"form1\"]/div[1]/div[3]/select/option[2]")
    private WebElement SelectMaleGender;

    @FindBy(how = How.XPATH, using = "//form[@id='form1']/div/div[4]/input")
    private WebElement DateOfBirthField;

    @FindBy(how = How.CSS, using = ".datepicker-days .datepicker-switch")
    private WebElement SwitchToMonth;

    @FindBy(how = How.CSS, using = ".datepicker-months .datepicker-switch")
    private WebElement SwitchToYear;

    @FindBy(how = How.CSS, using = ".datepicker-years .prev")
    private WebElement ChangeYear1;

    @FindBy(how = How.CSS, using = ".datepicker-years .prev")
    private WebElement ChangeYear2;

    @FindBy(how = How.CSS, using = ".datepicker-years .prev")
    private WebElement ChangeYear3;

    @FindBy(how = How.CSS, using = ".year:nth-child(7)")
    private WebElement SelectYear;

    @FindBy(how = How.CSS, using = ".month:nth-child(7)")
    private WebElement SelectMonth;

    @FindBy(how = How.CSS, using = "tr:nth-child(3) > .day:nth-child(5)")
    private WebElement SelectDay;

    @FindBy(how = How.XPATH, using = "//textarea[@id='address']")
    private WebElement AddressField;

    @FindBy(how = How.XPATH, using = "//input[@id='phone']")
    private WebElement PhoneField;

    @FindBy(how = How.XPATH, using = "//*[@id=\"form1\"]/div[1]/div[8]/div/input")
    private WebElement PhotoField;

    @FindBy(how = How.XPATH, using = "//input[@id='punch_id']")
    private WebElement PunchIdField;

    @FindBy(how = How.XPATH, using = "//form[@id='form1']/div/div[8]/div/input")
    private WebElement ChooseFileField;

    @FindBy(how = How.XPATH, using = "//*[@id=\"form1\"]/div[1]/div[8]/div/label")
    private WebElement ChooseFileButton;

    @FindBy(how = How.XPATH, using = "//*[@id=\"form1\"]/div[2]/button")
    private WebElement AddSaveButton;

    @FindBy(how = How.CSS, using = ".form-group:nth-child(2) p")
    private WebElement LibrarianNameValidation;

    @FindBy(how = How.CSS, using = ".form-group:nth-child(3) p")
    private WebElement EmailValidation;

    @FindBy(how = How.CSS, using = ".form-group:nth-child(4) p")
    private WebElement GenderValidation;

    @FindBy(how = How.CSS, using = ".form-group:nth-child(5) p")
    private WebElement DateOFBirthValidation;

    @FindBy(how = How.CSS, using = ".form-group:nth-child(7) p")
    private WebElement PhoneValidation;

    @FindBy(how = How.XPATH, using = "//*[@id=\"form1\"]/div[1]/div[1]")
    private WebElement LibrarianAddSuccess;

    @FindBy(how = How.XPATH, using = "//*[@id=\"DataTables_Table_0_filter\"]/label/input")
    private WebElement SearchField;

    @FindBy(how = How.XPATH, using = "//*[@id=\"form1\"]/div[1]/div[1]")
    private WebElement LibrarianEditSuccess;

    @FindBy(how = How.XPATH, using = "//*[@id=\"DataTables_Table_0\"]/tbody/tr/td[6]/a[2]")
    private WebElement EditButton;

    @FindBy(how = How.XPATH, using = "//*[@id=\"employeeform\"]/div[2]/button")
    private WebElement EditSaveButton;

    @FindBy(how = How.XPATH, using = "//*[@id=\"DataTables_Table_0\"]/tbody/tr/td[6]/a[3]")
    private WebElement ClickonDeleteLibrarian;

    @FindBy(how = How.XPATH, using = "//*[@id=\"form1\"]/div[1]/div[1]")
    private WebElement DeleteSuccessMessage;

    @FindBy(how = How.XPATH, using = "//*[@id=\"DataTables_Table_0\"]/tbody/tr/td[6]/a[1]")
    private WebElement ViewButton;

    @FindBy(how = How.XPATH, using = "//*[@id=\"sibe-box\"]/ul[2]/li[3]/ul/li[2]/a")
    private WebElement ClickOnAssignSubjects;

    @FindBy(how = How.XPATH, using = "//*[@id=\"class_id\"]")
    private WebElement ClickOnClass;

    @FindBy(how = How.XPATH, using = "//*[@id=\"class_id\"]/option[18]")
    private WebElement SelectClass;

    @FindBy(how = How.XPATH, using = "//*[@id=\"section_id\"]")
    private WebElement ClickOnSection;

    @FindBy(how = How.XPATH, using = "//*[@id=\"section_id\"]/option[2]")
    private WebElement SelectSection;

    @FindBy(how = How.XPATH, using = "//*[@id=\"search_filter\"]")
    private WebElement ClickOnSearchofAssign;

    @FindBy(how = How.XPATH, using = "//*[@id=\"subject_id_0\"]")
    private WebElement ClickOnSubject;

    @FindBy(how = How.XPATH, using = "//*[@id=\"subject_id_0\"]/option[2]")
    private WebElement SelectSubject;

    @FindBy(how = How.XPATH, using = "//*[@id=\"submit\"]")
    private WebElement AssignSubjectsSaveButton;

    @FindBy(how = How.XPATH, using = "//*[@id=\"DataTables_Table_0\"]/tbody/tr/td[6]/a[1]")
    private WebElement ClickOnViewLibrarian;

    public LibraryLibrarians(WebDriver driver, ExtentTest test) throws InterruptedException, IOException {
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

    //  Test all mandatory field validations.
    public void LibrariansValidation() throws IOException, InterruptedException {
        ActionClass actionClass = new ActionClass(driver1, extentTest);
        WebElement view = driver1.findElement(By.xpath("//*[@id=\"sibe-box\"]/ul[2]/li[6]/a"));
        JavascriptExecutor javascriptExecutor = (JavascriptExecutor) driver1;
        javascriptExecutor.executeScript("arguments[0].scrollIntoView();",view);
     // actionClass.clickOnObject(ClickOnLibraryMenu);
        Thread.sleep(2000);
        actionClass.clickOnObject(ClickOnLibrarian);
        Thread.sleep(3000);
        JavascriptExecutor jsetaskscore1 = (JavascriptExecutor) driver1;
        jsetaskscore1.executeScript("scrollBy(0, 600)");
        Thread.sleep(3000);
        actionClass.clickOnObject(AddSaveButton);
        Thread.sleep(3000);
        actionClass.captureScreen("Librarian_1");
        VerificationClass verificationClass = new VerificationClass(this.driver1, extentTest);
        verificationClass.verifyTextPresent(this.LibrarianNameValidation, "The Librarian field is required.");
        verificationClass.verifyTextPresent(this.EmailValidation, "The Email field is required.");
        verificationClass.verifyTextPresent(this.GenderValidation, "The Gender field is required.");
        jsetaskscore1.executeScript("scrollBy(0, 300)");
        Thread.sleep(3000);
        actionClass.captureScreen("Librarian_2");
        verificationClass.verifyTextPresent(this.DateOFBirthValidation, "The Date of Birth field is required.");
        verificationClass.verifyTextPresent(this.PhoneValidation, "The Phone field is required.");
    }

    //  Test by adding librarians and compare with database.
    public void AddLibrarians(String LibrarianName, String Email, String Address, String Phone, String FileLocation) throws InterruptedException, IOException, SQLException, AWTException {
        ActionClass actionClass = new ActionClass(driver1, extentTest);
        WebElement view = driver1.findElement(By.xpath("//*[@id=\"sibe-box\"]/ul[2]/li[6]/a"));
        JavascriptExecutor javascriptExecutor = (JavascriptExecutor) driver1;
        javascriptExecutor.executeScript("arguments[0].scrollIntoView();",view);
//      actionClass.clickOnObject(ClickOnLibraryMenu);
        Thread.sleep(2000);
        actionClass.clickOnObject(ClickOnLibrarian);
        Thread.sleep(3000);
        actionClass.setValueinTextbox(LibrarianNameField, LibrarianName);
        Thread.sleep(2000);
        actionClass.setValueinTextbox(EmailField, Email);
        Thread.sleep(2000);
        actionClass.clickOnObject(ClickGender);
        Thread.sleep(2000);
        actionClass.clickOnObject(SelectFemaleGender);
        Thread.sleep(2000);
        actionClass.captureScreen("Librarian_1");
        JavascriptExecutor jsetaskscore1 = (JavascriptExecutor) driver1;
        jsetaskscore1.executeScript("scrollBy(0, 300)");
        actionClass.clickOnObject(DateOfBirthField);
        Thread.sleep(2000);
        actionClass.clickOnObject(SwitchToMonth);
        Thread.sleep(2000);
        actionClass.clickOnObject(SwitchToYear);
        Thread.sleep(2000);
        actionClass.clickOnObject(ChangeYear1);
        Thread.sleep(2000);
        actionClass.clickOnObject(ChangeYear2);
        Thread.sleep(2000);
        actionClass.clickOnObject(ChangeYear3);
        Thread.sleep(2000);
        actionClass.clickOnObject(SelectYear);
        Thread.sleep(2000);
        actionClass.clickOnObject(SelectMonth);
        Thread.sleep(2000);
        actionClass.clickOnObject(SelectDay);
        Thread.sleep(2000);
        actionClass.setValueinTextbox(AddressField, Address);
        Thread.sleep(2000);
        actionClass.setValueinTextbox(PhoneField, Phone);
        Thread.sleep(2000);
        actionClass.captureScreen("Librarian_2");
        jsetaskscore1.executeScript("scrollBy(0, 300)");
        actionClass.setValueinTextbox(PhoneField, Phone);
        Thread.sleep(2000);
        actionClass.clickOnObject(PhotoField);
        Thread.sleep(1000);
        WebElement uploadElement = driver1.findElement(By.id("file"));
        uploadElement.sendKeys("/home/addweb/Desktop/mam.png");
        Thread.sleep(4000);
        actionClass.clickOnObject(AddSaveButton);
        Thread.sleep(2000);
        actionClass.captureScreen("Librarian_3");
        VerificationClass verificationClass = new VerificationClass(this.driver1, extentTest);
        verificationClass.verifyTextPresent(this.LibrarianAddSuccess, "Librarian added successfully");
        actionClass.setValueinTextbox(SearchField, LibrarianName);
        Thread.sleep(2000);
        actionClass.captureScreen("Librarian_4");

//      Check if the list is empty or not.
        Boolean isPresent = driver1.findElements(By.xpath("//*[@id=\"DataTables_Table_0\"]/tbody/tr[1]/td[2]")).size() > 0;
        ArrayList<String> ListLibrarianNameinFront = new ArrayList<String>();
        ArrayList<String> ListLibrarianEmailinFront = new ArrayList<String>();
        ArrayList<String> ListLibrarianDOBinFront = new ArrayList<String>();
        ArrayList<String> ListLibrarianPhoneinFront = new ArrayList<String>();
        if(isPresent==true){
            List<WebElement> ListSubject = driver1.findElements(By.xpath("//*[@id=\"DataTables_Table_0\"]/tbody/tr"));
            Thread.sleep(3000);
            int listsize = ListSubject.size();
            for (int i = 1; i <= listsize; i++) {
                String s = driver1.findElement(By.xpath("//*[@id=\"DataTables_Table_0\"]/tbody/tr[" + i + "]/td[1]")).getText();
                String s1 = driver1.findElement(By.xpath("//*[@id=\"DataTables_Table_0\"]/tbody/tr[" + i + "]/td[2]")).getText();
                String s2 = driver1.findElement(By.xpath("//*[@id=\"DataTables_Table_0\"]/tbody/tr[" + i + "]/td[3]")).getText();
                String s3 = driver1.findElement(By.xpath("//*[@id=\"DataTables_Table_0\"]/tbody/tr[" + i + "]/td[5]")).getText();
                System.out.println("Value in list is: " + s);
                System.out.println("Value in list is: " + s1);
                System.out.println("Value in list is: " + s2);
                System.out.println("Value in list is: " + s3);
                ListLibrarianNameinFront.add(driver1.findElement(By.xpath("//*[@id=\"DataTables_Table_0\"]/tbody/tr[" + i + "]/td[1]")).getText());
                ListLibrarianEmailinFront.add(driver1.findElement(By.xpath("//*[@id=\"DataTables_Table_0\"]/tbody/tr[" + i + "]/td[2]")).getText());
                ListLibrarianDOBinFront.add(driver1.findElement(By.xpath("//*[@id=\"DataTables_Table_0\"]/tbody/tr[" + i + "]/td[3]")).getText());
                ListLibrarianPhoneinFront.add(driver1.findElement(By.xpath("//*[@id=\"DataTables_Table_0\"]/tbody/tr[" + i + "]/td[5]")).getText());
            }
            System.out.println(ListLibrarianNameinFront);
            System.out.println(ListLibrarianEmailinFront);
            System.out.println(ListLibrarianDOBinFront);
            System.out.println(ListLibrarianPhoneinFront);
            System.out.println("The librarian is added.");
            extentTest.log(Status.PASS, "The librarian is added and present in the list.");
        }else{
            System.out.println("There is no such entry in the list");
            extentTest.log(Status.FAIL, "There is no such entry in the list");
        }

//      The List of librarian in Database
        DatabaseFunctions DAB = new DatabaseFunctions(extentTest);
        conn = DAB.connect();
        statement = conn.createStatement();
        String librarians = "SELECT * FROM `librarians` WHERE name = '" + LibrarianName + "'";
        queryRs = statement.executeQuery(librarians);
        ArrayList<String> LibrarianNameListinDB = new ArrayList<String>();
        ArrayList<String> LibrarianEmailListinDB = new ArrayList<String>();
        ArrayList<String> LibrarianDOBListinDB = new ArrayList<String>();
        ArrayList<String> LibrarianPhoneListinDB = new ArrayList<String>();
        if (queryRs.next()) {
            String librarianname = null;
            String librarianemail = null;
            Date librariandob = null;
            String librarianphone = null;
            librarianname = queryRs.getString("name");
            librarianemail = queryRs.getString("email");
            librariandob = queryRs.getDate("dob");
            DateFormat formatter = new SimpleDateFormat("dd.MM.yyyy");
            String strDate= formatter.format(librariandob);
            librarianphone = queryRs.getString("phone");
            System.out.println("Value in database list: " + librarianname);
            System.out.println("Value in database list: " + librarianemail);
            System.out.println("Value in database list: " + librariandob);
            System.out.println("Value in database list: " + librarianphone);
            LibrarianNameListinDB.add(librarianname);
            LibrarianEmailListinDB.add(librarianemail);
            LibrarianDOBListinDB.add(strDate);
            LibrarianPhoneListinDB.add(librarianphone);
            System.out.println(LibrarianNameListinDB);
            System.out.println(LibrarianEmailListinDB);
            System.out.println(LibrarianDOBListinDB);
            System.out.println(LibrarianPhoneListinDB);
            extentTest.log(Status.PASS, "The entered data is in database");
        }else{
            System.out.println("There is no such entry in database.");
            extentTest.log(Status.FAIL,"There is no such entry in database.");
        }

//      Compare front and db lists
        actionClass.CompareStringList(ListLibrarianNameinFront, LibrarianNameListinDB);
        actionClass.CompareStringList(ListLibrarianEmailinFront, LibrarianEmailListinDB);
        actionClass.CompareStringList(ListLibrarianDOBinFront, LibrarianDOBListinDB);
        actionClass.CompareStringList(ListLibrarianPhoneinFront, LibrarianPhoneListinDB);
    }

    //  Test by editing librarian and compare with database.
    public void EditLibrarians(String LibrarianName, String EditLibrarianName, String PunchID, String EditEmail) throws InterruptedException, IOException, SQLException {
        ActionClass actionClass = new ActionClass(driver1, extentTest);
        WebElement view = driver1.findElement(By.xpath("//*[@id=\"sibe-box\"]/ul[2]/li[6]/a"));
        JavascriptExecutor javascriptExecutor = (JavascriptExecutor) driver1;
        javascriptExecutor.executeScript("arguments[0].scrollIntoView();",view);
//      actionClass.clickOnObject(ClickOnLibraryMenu);
        Thread.sleep(2000);
        actionClass.clickOnObject(ClickOnLibrarian);
        Thread.sleep(3000);
        actionClass.setValueinTextbox(SearchField, LibrarianName);
        Thread.sleep(2000);
        actionClass.clickOnObject(EditButton);
        Thread.sleep(4000);
        actionClass.setValueinTextbox(LibrarianNameField, EditLibrarianName);
        Thread.sleep(2000);
        actionClass.setValueinTextbox(EmailField, EditEmail);
        Thread.sleep(2000);
        JavascriptExecutor jsetaskscore1 = (JavascriptExecutor) driver1;
        jsetaskscore1.executeScript("scrollBy(0, 800)");
        Thread.sleep(2000);
        actionClass.setValueinTextbox(PunchIdField, PunchID);
        Thread.sleep(2000);
        actionClass.clickOnObject(EditSaveButton);
        Thread.sleep(2000);
        actionClass.captureScreen("Librarian_5");
        VerificationClass verificationClass = new VerificationClass(this.driver1, extentTest);
        verificationClass.verifyTextPresent(this.LibrarianEditSuccess, "Librarian updated successfully");
        actionClass.setValueinTextbox(SearchField, EditLibrarianName);
        Thread.sleep(2000);
        actionClass.captureScreen("Librarian_6");

//      Check if the list is empty or not.
        Boolean isPresent = driver1.findElements(By.xpath("//*[@id=\"DataTables_Table_0\"]/tbody/tr[1]/td[2]")).size() > 0;
        ArrayList<String> ListLibrarianNameinFront = new ArrayList<String>();
        ArrayList<String> ListLibrarianEmailinFront = new ArrayList<String>();
        if(isPresent==true){
            List<WebElement> ListSubject = driver1.findElements(By.xpath("//*[@id=\"DataTables_Table_0\"]/tbody/tr"));
            Thread.sleep(3000);
            int listsize = ListSubject.size();
            for (int i = 1; i <= listsize; i++) {
                String s = driver1.findElement(By.xpath("//*[@id=\"DataTables_Table_0\"]/tbody/tr[" + i + "]/td[1]")).getText();
                String s1 = driver1.findElement(By.xpath("//*[@id=\"DataTables_Table_0\"]/tbody/tr[" + i + "]/td[2]")).getText();
                System.out.println("Value in list is: " + s);
                System.out.println("Value in list is: " + s1);
                ListLibrarianNameinFront.add(driver1.findElement(By.xpath("//*[@id=\"DataTables_Table_0\"]/tbody/tr[" + i + "]/td[1]")).getText());
                ListLibrarianEmailinFront.add(driver1.findElement(By.xpath("//*[@id=\"DataTables_Table_0\"]/tbody/tr[" + i + "]/td[2]")).getText());
            }
            System.out.println(ListLibrarianNameinFront);
            System.out.println(ListLibrarianEmailinFront);
            System.out.println("The librarian is edited.");
            extentTest.log(Status.PASS, "The librarian is edited and present in the list.");
        }else{
            System.out.println("There is no such entry in the list");
            extentTest.log(Status.FAIL, "There is no such entry in the list");
        }

//      The List of librarian in Database
        DatabaseFunctions DAB = new DatabaseFunctions(extentTest);
        conn = DAB.connect();
        statement = conn.createStatement();
        String librarians = "SELECT * FROM `librarians` WHERE name = '" + EditLibrarianName + "'";
        queryRs = statement.executeQuery(librarians);
        ArrayList<String> LibrarianNameListinDB = new ArrayList<String>();
        ArrayList<String> LibrarianEmailListinDB = new ArrayList<String>();
        if (queryRs.next()) {
            String librarianname = null;
            String librarianemail = null;
            librarianname = queryRs.getString("name");
            librarianemail = queryRs.getString("email");
            System.out.println("Value in database list: " + librarianname);
            System.out.println("Value in database list: " + librarianemail);
            LibrarianNameListinDB.add(librarianname);
            LibrarianEmailListinDB.add(librarianemail);
            System.out.println(LibrarianNameListinDB);
            System.out.println(LibrarianEmailListinDB);
            extentTest.log(Status.PASS, "The entered data is in database");
        }else{
            System.out.println("There is no such entry in database.");
            extentTest.log(Status.FAIL,"There is no such entry in database.");
        }

//      Compare front and db lists
        actionClass.CompareStringList(ListLibrarianNameinFront, LibrarianNameListinDB);
        actionClass.CompareStringList(ListLibrarianEmailinFront, LibrarianEmailListinDB);
    }

    //  Test by viewing librarian and compare with database.
    public void ViewLibrarians(String EditLibrarianName) throws InterruptedException, SQLException, IOException {
        ActionClass actionClass = new ActionClass(driver1, extentTest);
        WebElement view = driver1.findElement(By.xpath("//*[@id=\"sibe-box\"]/ul[2]/li[6]/a"));
        JavascriptExecutor javascriptExecutor = (JavascriptExecutor) driver1;
        javascriptExecutor.executeScript("arguments[0].scrollIntoView();",view);
//      actionClass.clickOnObject(ClickOnLibraryMenu);
        Thread.sleep(2000);
        actionClass.clickOnObject(ClickOnLibrarian);
        Thread.sleep(3000);
        actionClass.setValueinTextbox(SearchField, EditLibrarianName);
        Thread.sleep(3000);
        actionClass.clickOnObject(ClickOnViewLibrarian);
        Thread.sleep(3000);
        WebElement view1 = driver1.findElement(By.xpath("//b[contains(text(),'Address')]"));
        JavascriptExecutor javascriptExecutor1 = (JavascriptExecutor) driver1;
        javascriptExecutor1.executeScript("arguments[0].scrollIntoView();",view1);
        actionClass.captureScreen("Librarian_7");
//      Compare the librarian details with database
        String ViewGender = driver1.findElement(By.xpath("/html/body/div[1]/div[1]/section[2]/div/div/div/div/ul/li[1]/a")).getText();
        String ViewDOB = driver1.findElement(By.xpath("/html/body/div[1]/div[1]/section[2]/div/div/div/div/ul/li[2]/a")).getText();
        String ViewPhone = driver1.findElement(By.xpath("/html/body/div[1]/div[1]/section[2]/div/div/div/div/ul/li[3]/a")).getText();
        String ViewEmail = driver1.findElement(By.xpath("/html/body/div[1]/div[1]/section[2]/div/div/div/div/ul/li[4]/a")).getText();
        String ViewAddress = driver1.findElement(By.xpath("/html/body/div[1]/div[1]/section[2]/div/div/div/div/ul/li[5]/a")).getText();
        String LibrarianViewInfo = ViewGender+ ", "+ViewDOB+", "+ViewPhone+", "+ViewEmail+", "+ViewAddress;
        System.out.println(LibrarianViewInfo);
        DatabaseFunctions DAB = new DatabaseFunctions(extentTest);
        conn = DAB.connect();
        statement = conn.createStatement();
        String librarians = "SELECT * FROM `librarians` WHERE name = '" + EditLibrarianName + "'";
        queryRs = statement.executeQuery(librarians);
        String LibrarianDBInfo=null;
        if (queryRs.next()) {
            String DBGender = queryRs.getString("sex");
            Date librariandob = queryRs.getDate("dob");
            DateFormat formatter = new SimpleDateFormat("dd.MM.yyyy");
            String DBDOB= formatter.format(librariandob);
            String DBPhone =queryRs.getString("phone");
            String DBEmail = queryRs.getString("email");
            String DBAddress = queryRs.getString("address");
            LibrarianDBInfo = DBGender+ ", "+DBDOB+", "+DBPhone+", "+DBEmail+", "+DBAddress;
            System.out.println(LibrarianDBInfo);
            extentTest.log(Status.PASS, "The entered data is in database");
        } else{
            System.out.println("There is no such entry in database.");
            extentTest.log(Status.FAIL,"There is no such entry in database.");
        }
        if(LibrarianDBInfo.equals(LibrarianViewInfo)){
            System.out.println("The librarian info matches with database");
            extentTest.log(Status.PASS, "The librarian info matches with database");
        }
        else{
            System.out.println("The librarian info does not match with database");
            extentTest.log(Status.FAIL, "The librarian info does not match with database");
        }
    }

    //  Test by deleting librarian and compare with database.
    public void DeleteLibrarian(String LibrarianName) throws InterruptedException, IOException, SQLException, AWTException {
        ActionClass actionClass = new ActionClass(driver1, extentTest);
        WebElement view = driver1.findElement(By.xpath("//*[@id=\"sibe-box\"]/ul[2]/li[6]/a"));
        JavascriptExecutor javascriptExecutor = (JavascriptExecutor) driver1;
        javascriptExecutor.executeScript("arguments[0].scrollIntoView();",view);
//      actionClass.clickOnObject(ClickOnLibraryMenu);
        Thread.sleep(2000);
        actionClass.clickOnObject(ClickOnLibrarian);
        Thread.sleep(3000);
        actionClass.setValueinTextbox(SearchField, LibrarianName);
        Thread.sleep(3000);
        JavascriptExecutor jse = (JavascriptExecutor) driver1;
        WebElement deletebutton = driver1.findElement(By.xpath("//*[@id=\"DataTables_Table_0\"]/tbody/tr/td[6]/a[3]"));
        jse.executeScript("arguments[0].scrollIntoView();",deletebutton);
        JavascriptExecutor jsetaskscore1 = (JavascriptExecutor) driver1;
        jsetaskscore1.executeScript("scrollBy(0, -600)");
        Thread.sleep(3000);
        actionClass.clickOnObject(ClickonDeleteLibrarian);
        Thread.sleep(2000);
        Alert alert = driver1.switchTo().alert();
        String alertMessage= driver1.switchTo().alert().getText();
        actionClass.captureScreen("Delete Message");
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
        alert.accept();
        Thread.sleep(3000);
        actionClass.captureScreenUsingRobot("Librarian_8");
        VerificationClass verificationClass = new VerificationClass(driver1, extentTest);
        verificationClass.verifyTextPresent(DeleteSuccessMessage, "Librarian deleted successfully");
        Thread.sleep(1000);
        actionClass.captureScreen("Librarian_9");

//      Test if the entry is not in list
        actionClass.setValueinTextbox(SearchField, LibrarianName);
        Thread.sleep(2000);
        actionClass.captureScreen("Librarian_10");
        Boolean isPresent = driver1.findElements(By.xpath("//*[@id=\"DataTables_Table_0\"]/tbody/tr[1]/td[2]")).size() > 0;
        ArrayList<String> ListLibrariranNameinFront = new ArrayList<String>();
        if(isPresent==true){
            List<WebElement> ListLibrarian = driver1.findElements(By.xpath("//*[@id=\"DataTables_Table_0\"]/tbody/tr"));
            Thread.sleep(3000);
            int listsize = ListLibrarian.size();
            for (int i = 1; i <= listsize; i++) {
                String s = driver1.findElement(By.xpath("//*[@id=\"DataTables_Table_0\"]/tbody/tr[" + i + "]/td[1]")).getText();
                System.out.println("Value in list is: " + s);
                ListLibrariranNameinFront.add(driver1.findElement(By.xpath("//*[@id=\"DataTables_Table_0\"]/tbody/tr[" + i + "]/td[1]")).getText());
            }
            System.out.println(ListLibrariranNameinFront);
            System.out.println("The librarian is present in the list.");
            extentTest.log(Status.FAIL, "The librarian is present in the list.");
        }else{
            System.out.println("There is no such entry in the list");
            extentTest.log(Status.PASS, "There is no such entry in the list");
        }

//      The List of librarian in Database
        DatabaseFunctions DAB = new DatabaseFunctions(extentTest);
        conn = DAB.connect();
        statement = conn.createStatement();
        String librarians = "SELECT * FROM `librarians` WHERE name = '" + LibrarianName + "'";
        queryRs = statement.executeQuery(librarians);
        ArrayList<String> ListLibrariranNameinDB = new ArrayList<String>();
        if (queryRs.next()) {
            String librariansnames = null;
            librariansnames = queryRs.getString("name");
            System.out.println("Value in database list: " + librariansnames);
            ListLibrariranNameinDB.add(queryRs.getString("name"));
            System.out.println(ListLibrariranNameinDB);
            extentTest.log(Status.FAIL, "The entered data is in database");
        } else{
            System.out.println("There is no such entry in database.");
            extentTest.log(Status.PASS,"There is no such entry in database.");
        }
        actionClass.CompareStringList(ListLibrariranNameinFront, ListLibrariranNameinDB);
    }
}