package AEDU.Pages;

import AEDU.Utilities.DatabaseFunctions;
import AEDU.constants.CommonVar;
import AEDU.Actions.VerificationClass;
import AEDU.Actions.ActionClass;
import AEDU.Pages.StudentInformation;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
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
import java.util.List;

public class AddStudent{
    WebDriver driver;
    ExtentTest extentTest;
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
            using = "/html/body/div[1]/aside/div/section/ul[2]/li[1]/ul/li[2]/a"
    )
    private WebElement SidemenuStudentAddmissionclick;
    @FindBy(
            how = How.XPATH,
            using = "/html/body/div[1]/div[1]/section[2]/div/div/div/form/div/div[1]/h4"
    )
    private WebElement VerifyStudentAddmissionTitle;
    @FindBy(
            how = How.XPATH,
            using = "/html/body/div[1]/div[1]/section[2]/div/div/div/form/div/div[4]/button"
    )
    private WebElement Savebtn;
    @FindBy(
            how = How.XPATH,
            using = "/html/body/div[1]/div[1]/section[2]/div/div/div/form/div/div[1]/div/div[1]/div[1]/div/span/p"
    )
    private WebElement Addmissionfeildvalidation;
    @FindBy(
            how = How.XPATH,
            using = "/html/body/div[1]/div[1]/section[2]/div/div/div/form/div/div[1]/div/div[1]/div[3]/div/span/p"
    )
    private WebElement Classfeildvalidation;
    @FindBy(
            how = How.XPATH,
            using = "/html/body/div[1]/div[1]/section[2]/div/div/div/form/div/div[1]/div/div[1]/div[4]/div/span/p"
    )
    private WebElement Sectionfeildvalidation;
    @FindBy(
            how = How.XPATH,
            using = "/html/body/div[1]/div[1]/section[2]/div/div/div/form/div/div[1]/div/div[2]/div[1]/div/span/p"
    )
    private WebElement Fristnamefeildvalidation;
    @FindBy(
            how = How.XPATH,
            using = "/html/body/div[1]/div[1]/section[2]/div/div/div/form/div/div[2]/div/div[1]/div[1]/div/span/p"
    )
    private WebElement FatherNamefeildvalidation;
    @FindBy(
            how = How.XPATH,
            using = "/html/body/div[1]/div[1]/section[2]/div/div/div/form/div/div[2]/div/div[1]/div[2]/div/span/p"
    )
    private WebElement FatherPhonevalidation;
    @FindBy(
            how = How.XPATH,
            using = "/html/body/div[1]/div[1]/section[2]/div/div/div/form/div/div[2]/div/div[6]/div[1]/div[1]/div[1]/div/span/p"
    )
    private WebElement GardianNamefeildvalidation;
    @FindBy(
            how = How.XPATH,
            using = "/html/body/div[1]/div[1]/section[2]/div/div/div/form/div/div[2]/div/div[6]/div[1]/div[2]/div[1]/div/span/p"
    )
    private WebElement Gardianphonefeildvalidation;
    @FindBy(
            how = How.XPATH,
            using = "/html/body/div[1]/div[1]/section[2]/div/div/div/form/div/div[1]/div/div[2]/div[3]/div/span/p"
    )
    private WebElement Genderfeildvalidation;
    @FindBy(
            how = How.XPATH,
            using = "/html/body/div[1]/aside/div/section/ul[2]/li[1]/ul/li[5]/a"
    )
    private WebElement Trasfercertificate;
    @FindBy(
            how = How.XPATH,
            using = "//*[@id=\"admission_no\"]"
    )
    private WebElement Addmissionnumber;
    //*[@id="class_id"]
    @FindBy(
            how = How.XPATH,
            using = "//*[@id=\"class_id\"]"
    )
    private WebElement SelectClassfeild;
    //*[@id="class_id"]/option[2]
    @FindBy(
            how = How.XPATH,
            using = "//*[@id=\"class_id\"]/option[7]"
    )
    private WebElement SelectClassOption;
    @FindBy(
            how = How.XPATH,
            using = " //*[@id=\"section_id\"]"
    )
    private WebElement SelectSectionFeild;
    @FindBy(
            how = How.XPATH,
            using = "//*[@id=\"section_id\"]/option[3]"
    )
    private WebElement SelectSectionOption;
    //*[@id="roll_no"]
    @FindBy(
            how = How.XPATH,
            using = "//*[@id=\"roll_no\"]"
    )
    private WebElement RollnbFeild;
    @FindBy(
            how = How.XPATH,
            using = "//*[@id=\"firstname\"]"
    )
    private WebElement FristNamefeild;
    @FindBy(
            how = How.XPATH,
            using = "//*[@id=\"form1\"]/div/div[1]/div/div[2]/div[3]/div/select"
    )
    private WebElement GenderFeild;

    @FindBy(
            how = How.XPATH,
            using = "//*[@id=\"form1\"]/div/div[1]/div/div[2]/div[3]/div/select/option[2]"
    )
    private WebElement SelectGender;
    @FindBy(
            how = How.XPATH,
            using = "//*[@id=\"father_name\"]"
    )
    private WebElement FatherNamefeild;
    @FindBy(
            how = How.XPATH,
            using = "//*[@id=\"father_phone\"]"
    )
    private WebElement FatherPhonenumber;
    @FindBy(
            how = How.XPATH,
            using = "//*[@id=\"form1\"]/div/div[2]/div/div[5]/div/label[2]/input"
    )
    private WebElement GardiunSelection;
    @FindBy(
            how = How.XPATH,
            using = "/html/body/div[1]/div[1]/section[2]/div/div/div/form/div/div[1]/div/div[6]/div[1]/div/div/div/input"
    )
    private WebElement Uploadfile;

    @FindBy(
            how = How.XPATH,
            using = "/html/body/div[1]/div[1]/section[2]/div/div/div/form/div/div[1]/div/div[1]\n"
    )
    private WebElement StudentAddedmessage;
    @FindBy(
            how = How.XPATH,
            using = "/html/body/div[1]/div[1]/section[2]/div/div/div/div/a/button"
    )
    private WebElement ImportCsvbButton;
    @FindBy(
            how = How.XPATH,
            using = "//*[@id=\"class_id\"]"
    )
    private WebElement ImportCsvbSelectclass;
    @FindBy(
            how = How.XPATH,
            using = "//*[@id=\"class_id\"]/option[7]"
    )
    private WebElement ImportCsvbSelectclassOption;

    @FindBy(
            how = How.XPATH,
            using = "//*[@id=\"section_id\"]"
    )
    private WebElement ImportCsvbSelectSection;

    @FindBy(
            how = How.XPATH,
            using = "//*[@id=\"section_id\"]/option[3]"
    )
    private WebElement ImportCsvbSelectSectionOption;
    @FindBy(
            how = How.XPATH,
            using = "/html/body/div[1]/div[1]/section[2]/div/div/div/form/div[1]/div/div[3]/div/div/div/input"
    )
    private WebElement SelectCsvFile;
    @FindBy(
            how = How.XPATH,
            using = "//*[@id=\"employeeform\"]/div[2]/button"
    )
    private WebElement ImportStudentbutton;
    @FindBy(
            how = How.XPATH,
            using = " /html/body/div[1]/div[1]/section[2]/div/div/div/form/div[2]/button[1]"
    )
    private WebElement ComfimmessageYes;
    @FindBy(
            how = How.XPATH,
            using = "/html/body/div[1]/div[1]/section[2]/div/div/div/form/div[1]/div[1]"
    )
    private WebElement SuccessMegforCsv;

    @FindBy(
            how = How.XPATH,
            using = "//*[@id=\"form1\"]/div/div[1]/div/div[1]"
    )
    private WebElement succes;
    @FindBy(
            how = How.XPATH,
            using = "//*[@id=\"form1\"]/div/div[2]/div/div[5]/div/label[3]/input"
    )
    private WebElement clickOnGuardianMother;
    @FindBy(
            how = How.XPATH,
            using = " //*[@id=\"sibe-box\"]/ul[2]/li[1]/ul/li[1]/a"
    )
    private WebElement ckickOnSideMenuStudentDetails;
    @FindBy(
            how = How.XPATH,
            using = "//*[@id=\"class_id\"]"
    )
    private WebElement clickOnClass;
    @FindBy(
            how = How.XPATH,
            using = "//*[@id=\"class_id\"]/option[7]"
    )
    private WebElement selectClass;
    @FindBy(
            how = How.XPATH,
            using = "//*[@id=\"section_id\"]"
    )
    private WebElement clickOnSection;
    @FindBy(
            how = How.XPATH,
            using = "//*[@id=\"section_id\"]/option[3]"
    )
    private WebElement selectSection;
    @FindBy(
            how = How.XPATH,
            using = "/html/body/div[1]/div[1]/section[2]/div/div/div/div[2]/div/div[1]/div/form/div[4]/div/button"
    )
    private WebElement clickOnSearch;
    @FindBy(
            how = How.XPATH,
            using = "//*[@id=\"DataTables_Table_0_filter\"]/label/input"
    )
    private WebElement smallSearchfield;
    @FindBy(
            how = How.XPATH,
            using = "//*[@id=\"DataTables_Table_0\"]/tbody/tr/td[1]"
    )
    private WebElement findadmissionnumber;

    public AddStudent(WebDriver driver,ExtentTest test){
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

    //    This script will take us to admission page
    public void AddStudent(String username1,String pwd)throws IOException,InterruptedException {
        ActionClass actionClass = new ActionClass(driver, extentTest);
        actionClass.clickOnObject(this.sidemenustudentinfomenuclick);
        Thread.sleep(3000);
        actionClass.clickOnObject(this.SidemenuStudentAddmissionclick);
        VerificationClass verificationClass = new VerificationClass(this.driver, extentTest);
        verificationClass.verifyTextPresent(this.VerifyStudentAddmissionTitle, "Student Admission");
        actionClass.captureScreen("Student Admission Page");

    }

    //    Test if all the mandatory field shows validations
    public void VeryFeildValidation()throws IOException,InterruptedException{
        ActionClass actionClass=new ActionClass(driver,extentTest);
//        actionClass.clickOnObject(this.sidemenustudentinfomenuclick);
//        Thread.sleep(3000);
        actionClass.clickOnObject(this.SidemenuStudentAddmissionclick);
        JavascriptExecutor jsetaskscore = (JavascriptExecutor) driver;
        jsetaskscore.executeScript("scrollBy(0, 500)");
        actionClass.clickOnObject(this.Savebtn);
        VerificationClass verificationClass=new VerificationClass(this.driver,extentTest);
        verificationClass.verifyTextPresent(this.Addmissionfeildvalidation,"The Admission No. field is required.");
        verificationClass.verifyTextPresent(this.Sectionfeildvalidation,"The Section field is required.");
        verificationClass.verifyTextPresent(this.Fristnamefeildvalidation,"The First Name field is required.");
        verificationClass.verifyTextPresent(this.Genderfeildvalidation,"The Gender field is required.");
        verificationClass.verifyTextPresent(this.FatherNamefeildvalidation,"The Father Name field is required.");
        verificationClass.verifyTextPresent(this.FatherPhonevalidation,"The Father Phone field is required.");
        verificationClass.verifyTextPresent(this.GardianNamefeildvalidation,"The Guardian Name field is required.");
        verificationClass.verifyTextPresent(this.Gardianphonefeildvalidation,"The Guardian Phone field is required.");
        actionClass.captureScreen("VeryFeildValidation");
    }


    //    Add a student
    public void SetudentAddmissionform(String Addmissionnb,String Rollnumber,String Fristname,String Fathername,String FatherPhone)throws IOException ,InterruptedException {
        ActionClass actionClass = new ActionClass(driver, extentTest);
        Thread.sleep(5000);
        actionClass.clickOnObject(this.sidemenustudentinfomenuclick);
        Thread.sleep(5000);
        actionClass.clickOnObject(this.SidemenuStudentAddmissionclick);
        actionClass.setValueinTextbox(this.Addmissionnumber, Addmissionnb);
        actionClass.setValueinTextbox(this.RollnbFeild, Rollnumber);
        actionClass.clickOnObject(this.SelectClassfeild);
        actionClass.clickOnObject(this.SelectClassOption);
        actionClass.clickOnObject(this.SelectSectionFeild);
        actionClass.clickOnObject(this.SelectSectionOption);
        actionClass.clickOnObject(this.FristNamefeild);
        actionClass.setValueinTextbox(this.FristNamefeild, Fristname);
        actionClass.clickOnObject(this.GenderFeild);
        actionClass.clickOnObject(this.SelectGender);
        JavascriptExecutor jsetaskscore = (JavascriptExecutor) driver;
        jsetaskscore.executeScript("scrollBy(0, 500)");
//        WebElement uploadElement = driver.findElement(By.id("file"));
        //uploadElement.sendKeys("/home/addweb/Desktop/testing/download.jpeg");
        actionClass.clickOnObject(this.FatherNamefeild);
        actionClass.setValueinTextbox(this.FatherNamefeild, Fathername);
        actionClass.clickOnObject(this.FatherPhonenumber);
        actionClass.setValueinTextbox(this.FatherPhonenumber, FatherPhone);
//        actionClass.clickOnObject(this.clickOnGuardianMother);
//        actionClass.clickOnObject(this.GardiunSelection);
        actionClass.clickOnObject(this.Savebtn);
        VerificationClass verificationClass = new VerificationClass(driver, extentTest);
//        verificationClass.verifyTextPresent(this.succes, "Student added Successfully");
//        actionClass.captureScreen("SetudentAddmissionform1");
        actionClass.clickOnObject(this.ckickOnSideMenuStudentDetails);
        actionClass.clickOnObject(this.clickOnClass);
        actionClass.clickOnObject(this.selectClass);
        actionClass.clickOnObject(this.clickOnSection);
        actionClass.clickOnObject(this.selectSection);
        actionClass.clickOnObject(this.clickOnSearch);
        Thread.sleep(3000);
        actionClass.setValueinTextbox(this.smallSearchfield, Addmissionnb);
        Thread.sleep(3000);
        verificationClass.verifyTextPresent(this.findadmissionnumber, Addmissionnb);
        JavascriptExecutor jsetaskscore1 = (JavascriptExecutor) driver;
        jsetaskscore1.executeScript("scrollBy(0, 500)");
        actionClass.captureScreen("SetudentAddmissionform2");
    }


    //    Import Student using CSV
    public void Importstudentcsv()throws InterruptedException,IOException{
        ActionClass actionClass=new ActionClass(driver,extentTest);
        actionClass.clickOnObject(this.sidemenustudentinfomenuclick);
        Thread.sleep(5000);
        actionClass.clickOnObject(this.SidemenuStudentAddmissionclick);
        actionClass.clickOnObject(this.ImportCsvbButton);
        actionClass.clickOnObject(this.ImportCsvbSelectclass);
        actionClass.clickOnObject(this.ImportCsvbSelectclassOption);
        actionClass.clickOnObject(this.ImportCsvbSelectSection);
        actionClass.clickOnObject(this.ImportCsvbSelectSection);
        actionClass.clickOnObject(this.ImportCsvbSelectSectionOption);
        actionClass.clickOnObject(this.SelectCsvFile);
        WebElement uploadElement = driver.findElement(By.id("file"));
        uploadElement.sendKeys("/home/addweb/IdeaProjects/aeduu/test-output/dob1 - Riddhi - dob1 - Riddhi (2).csv");
        actionClass.captureScreen("Importstudentcsv1");
        extentTest.log(Status.PASS,"Csv Uploaded Now");
        Thread.sleep(5000);
        JavascriptExecutor jsetaskscore1 = (JavascriptExecutor) driver;
        jsetaskscore1.executeScript("scrollBy(0, 500)");
        actionClass.clickOnObject(this.ImportStudentbutton);
        Thread.sleep(5000);
        actionClass.clickOnObject(this.ComfimmessageYes);
//        VerificationClass very=new VerificationClass(driver,extentTest);
//        very.verifyTextPresent(this.SuccessMegforCsv,"Students imported successfully.");
        extentTest.log(Status.PASS,"Students imported Successfully");
        actionClass.captureScreen("Importstudentcsv2");
    }

    //Import Student validations
    public void ImportCsvAddmissionFeildValidation()throws IOException,InterruptedException{
        ActionClass actionClass=new ActionClass(driver,extentTest);
//        Thread.sleep(3000);
//        actionClass.clickOnObject(this.sidemenustudentinfomenuclick);
        Thread.sleep(3000);
        actionClass.clickOnObject(this.SidemenuStudentAddmissionclick);
        actionClass.clickOnObject(this.ImportCsvbButton);
        actionClass.clickOnObject(this.ImportCsvbSelectclass);
        actionClass.clickOnObject(this.ImportCsvbSelectclassOption);
        actionClass.clickOnObject(this.ImportCsvbSelectSection);
        actionClass.clickOnObject(this.ImportCsvbSelectSection);
        actionClass.clickOnObject(this.ImportCsvbSelectSectionOption);
        actionClass.clickOnObject(this.SelectCsvFile);
        WebElement uploadElement = driver.findElement(By.id("file"));
        uploadElement.sendKeys("/home/addweb/IdeaProjects/aeduu/test-output/dob1 - Addmission feild validation.csv");
        extentTest.log(Status.PASS,"Csv Uploaded Now");
        JavascriptExecutor jsetaskscore1 = (JavascriptExecutor) driver;
        jsetaskscore1.executeScript("scrollBy(0, 500)");
        Thread.sleep(5000);
        actionClass.clickOnObject(this.ImportStudentbutton);
        Thread.sleep(5000);
        actionClass.clickOnObject(this.ComfimmessageYes);
        VerificationClass very=new VerificationClass(driver,extentTest);
        extentTest.log(Status.PASS,"Please enter admission number for the Roll no. 135");
        actionClass.captureScreen("ImportCsvAddmissionFeildValidation3");
        //unique value
        actionClass.clickOnObject(this.ImportCsvbSelectclass);
        actionClass.clickOnObject(this.ImportCsvbSelectclassOption);
        actionClass.clickOnObject(this.ImportCsvbSelectSection);
        actionClass.clickOnObject(this.ImportCsvbSelectSection);
        actionClass.clickOnObject(this.ImportCsvbSelectSectionOption);
        actionClass.clickOnObject(this.SelectCsvFile);
        WebElement uploadElement1 = driver.findElement(By.id("file"));
        uploadElement1.sendKeys("/home/addweb/IdeaProjects/aeduu/test-output/dob1 - Fristname feild validation.csv");
        extentTest.log(Status.PASS,"Csv Uploaded Now");
        actionClass.captureScreen("ImportCsvAddmissionFeildValidation1");

        Thread.sleep(5000);
        actionClass.clickOnObject(this.ImportStudentbutton);
        Thread.sleep(5000);
        actionClass.clickOnObject(this.ComfimmessageYes);
        extentTest.log(Status.PASS,"Please Enter Unique Admission No.For the Roll no.135");
        actionClass.captureScreen("ImportCsvAddmissionFeildValidation2");
    }

    //Frist Name feild Validation
    public void FristNameCsv() throws InterruptedException, InterruptedException, IOException {
        ActionClass actionClass=new ActionClass(driver,extentTest);
        //actionClass.clickOnObject(this.sidemenustudentinfomenuclick);
        Thread.sleep(3000);
        actionClass.clickOnObject(this.SidemenuStudentAddmissionclick);
        actionClass.clickOnObject(this.ImportCsvbButton);
        actionClass.clickOnObject(this.ImportCsvbSelectclass);
        actionClass.clickOnObject(this.ImportCsvbSelectclassOption);
        actionClass.clickOnObject(this.ImportCsvbSelectSection);
        actionClass.clickOnObject(this.ImportCsvbSelectSection);
        actionClass.clickOnObject(this.ImportCsvbSelectSectionOption);
        actionClass.clickOnObject(this.SelectCsvFile);
        WebElement uploadElement = driver.findElement(By.id("file"));
        uploadElement.sendKeys("/home/addweb/IdeaProjects/aeduu/test-output/dob1 - Fristname feild validation.csv");
        extentTest.log(Status.PASS,"Csv Uploaded Now");
        actionClass.captureScreen("FristNameCsv1");
        Thread.sleep(5000);
        actionClass.clickOnObject(this.ImportStudentbutton);
        Thread.sleep(5000);
        actionClass.clickOnObject(this.ComfimmessageYes);
        VerificationClass very=new VerificationClass(driver,extentTest);
        extentTest.log(Status.PASS,"Please enter firstname for the Roll no. 136");
        actionClass.captureScreen("FristNameCsv2");
    }

    //Father's name feild validation
    public void ImportCsvFathername()throws InterruptedException,IOException{
        ActionClass actionClass=new ActionClass(driver,extentTest);
//        Thread.sleep(5000);
//        actionClass.clickOnObject(this.sidemenustudentinfomenuclick);
        Thread.sleep(3000);
        actionClass.clickOnObject(this.SidemenuStudentAddmissionclick);
        actionClass.clickOnObject(this.ImportCsvbButton);
        actionClass.clickOnObject(this.ImportCsvbSelectclass);
        actionClass.clickOnObject(this.ImportCsvbSelectclassOption);
        actionClass.clickOnObject(this.ImportCsvbSelectSection);
        actionClass.clickOnObject(this.ImportCsvbSelectSection);
        actionClass.clickOnObject(this.ImportCsvbSelectSectionOption);
        actionClass.clickOnObject(this.SelectCsvFile);
        WebElement uploadElement = driver.findElement(By.id("file"));
        uploadElement.sendKeys("/home/addweb/IdeaProjects/aeduu/test-output/dob1 - Unique father's phone number .csv");
        extentTest.log(Status.PASS,"Csv Uploaded Now");
        actionClass.captureScreen("ImportCsvFathername1");
        Thread.sleep(5000);
        actionClass.clickOnObject(this.ImportStudentbutton);
        Thread.sleep(5000);
        actionClass.clickOnObject(this.ComfimmessageYes);
        VerificationClass very=new VerificationClass(driver,extentTest);
        very.verifyTextPresent(this.SuccessMegforCsv,"Please Enter Unique Admission No.For the Roll no.136");
        actionClass.captureScreen("ImportCsvFathername2");
    }

    //Father'phone number
    public void ImportCsvPhoneNumber()throws InterruptedException,IOException{
        ActionClass actionClass=new ActionClass(driver,extentTest);
//        Thread.sleep(3000);
//        actionClass.clickOnObject(this.sidemenustudentinfomenuclick);
        Thread.sleep(3000);
        actionClass.clickOnObject(this.SidemenuStudentAddmissionclick);
        actionClass.clickOnObject(this.ImportCsvbButton);
        actionClass.clickOnObject(this.ImportCsvbSelectclass);
        actionClass.clickOnObject(this.ImportCsvbSelectclassOption);
        actionClass.clickOnObject(this.ImportCsvbSelectSection);
        actionClass.clickOnObject(this.ImportCsvbSelectSection);
        actionClass.clickOnObject(this.ImportCsvbSelectSectionOption);
        actionClass.clickOnObject(this.SelectCsvFile);
        WebElement uploadElement = driver.findElement(By.id("file"));
        uploadElement.sendKeys("/home/addweb/IdeaProjects/aeduu/test-output/dob1 - Unique father's phone numbermissing.csv");
        extentTest.log(Status.PASS,"Csv Uploaded Now");
        actionClass.captureScreen("ImportCsvFatherphone");
        Thread.sleep(5000);
        actionClass.clickOnObject(this.ImportStudentbutton);
        Thread.sleep(5000);
        actionClass.clickOnObject(this.ComfimmessageYes);
        VerificationClass very=new VerificationClass(driver,extentTest);
        extentTest.log(Status.PASS,"Please enter father's phone for the Roll no . 136");
        //Phone number validation
        actionClass.clickOnObject(this.ImportCsvbSelectclass);
        actionClass.clickOnObject(this.ImportCsvbSelectclassOption);
        actionClass.clickOnObject(this.ImportCsvbSelectSection);
        actionClass.clickOnObject(this.ImportCsvbSelectSection);
        actionClass.clickOnObject(this.ImportCsvbSelectSectionOption);
        actionClass.clickOnObject(this.SelectCsvFile);
        WebElement uploadElement1 = driver.findElement(By.id("file"));
        uploadElement1.sendKeys("/home/addweb/IdeaProjects/aeduu/test-output/dob1 - Unique father's phone number  (4).csv");
        extentTest.log(Status.PASS,"Csv Uploaded Now");
        actionClass.captureScreen("ImportCsvFatherphone1");
        JavascriptExecutor jsetaskscore1 = (JavascriptExecutor) driver;
        jsetaskscore1.executeScript("scrollBy(0, 500)");
        Thread.sleep(5000);
        actionClass.clickOnObject(this.ImportStudentbutton);
        Thread.sleep(5000);
        actionClass.clickOnObject(this.ComfimmessageYes);
        VerificationClass very1=new VerificationClass(driver,extentTest);
        extentTest.log(Status.PASS, "Father's phone is not valid for the Roll no. 136");
        actionClass.captureScreen("ImportCsvFatherphone2");
    }
}

