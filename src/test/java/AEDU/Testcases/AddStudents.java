package AEDU.Testcases;

import AEDU.Pages.AddStudent;
import AEDU.Pages.StudentInformation;
import org.testng.annotations.Test;

import java.io.IOException;
import java.sql.SQLException;

public class AddStudents extends Basecase {

    //To test if the mandatory fields show validations over Student admission page.
    @Test (priority = 1)
    public void Checkallvalidations() throws InterruptedException, IOException, SQLException {
        logger = extent.createTest("To test if the mandatory fields show validations over Student admission page");
//        StudentInformation SI = new StudentInformation(driver, logger);
//        SI.loginWithCredentials2("addwebsolution@gmail.com", "addweb123");
        AddStudent check = new AddStudent(driver, logger);
        check.openNewTab();
        check.VeryFeildValidation();
    }
//To test if the student is added
    @Test (priority = 2)
    public void Studentadd() throws IOException, InterruptedException, SQLException {
        logger = extent.createTest("To test if the student is added ");
        StudentInformation SI = new StudentInformation(driver, logger);
        SI.loginWithCredentials2("addwebsolution@gmail.com", "addweb123");
        AddStudent Add = new AddStudent(driver, logger);
        //Add.openNewTab();
        Add.SetudentAddmissionform("43224", "0069", "Aanal", "Shah", "8253685412");
    }
//To check if the student csv is imported
    @Test (priority = 3)
    public void ImortCsv() throws IOException, InterruptedException, SQLException {
        logger = extent.createTest("To check if the student csv is imported");
//        StudentInformation SI = new StudentInformation(driver, logger);
//        SI.loginWithCredentials2("addwebsolution@gmail.com", "addweb123");
        AddStudent Import = new AddStudent(driver, logger);
        Import.openNewTab();
        Import.Importstudentcsv();
    }
//To check admission field validation on importing csv
    @Test (priority = 4)
    public void ImportCsvAddmission()throws IOException,InterruptedException,SQLException{
        logger=extent.createTest("To check admission field validation on importing csv ");
//        StudentInformation SI = new StudentInformation(driver, logger);
//        SI.loginWithCredentials2("addwebsolution@gmail.com", "addweb123");
        AddStudent Addmission=new AddStudent(driver,logger);
        Addmission.openNewTab();
        Addmission.ImportCsvAddmissionFeildValidation();

    }
//To check firstname field validations
    @Test (priority = 5)
    public void ImportCsvFristname()throws IOException,InterruptedException{
        logger=extent.createTest("To check firstname field validations");
//        StudentInformation SI = new StudentInformation(driver, logger);
//        SI.loginWithCredentials2("addwebsolution@gmail.com", "addweb123");
        AddStudent Fristname=new AddStudent(driver,logger);
        Fristname.openNewTab();
        Fristname.FristNameCsv();

    }
//    To check father's name field validations
    @Test (priority = 6)
    public void ImportCsvFathersname()throws IOException,InterruptedException{
        logger=extent.createTest("To check father's name field validations ");
//        StudentInformation SI = new StudentInformation(driver, logger);
//        SI.loginWithCredentials2("addwebsolution@gmail.com", "addweb123");
        AddStudent Fathername=new AddStudent(driver,logger);
        Fathername.openNewTab();
        Fathername.ImportCsvFathername();
    }
//    To check phone number field validations
    @Test (priority = 7)
    public void ImportCsvPhonenumber()throws IOException,InterruptedException{
        logger=extent.createTest("To check Father's phone number field validations ");
//        StudentInformation SI = new StudentInformation(driver, logger);
//        SI.loginWithCredentials2("addwebsolution@gmail.com", "addweb123");
        AddStudent Phonenumber=new AddStudent(driver,logger);
        Phonenumber.openNewTab();
        Phonenumber.ImportCsvPhoneNumber();
    }
}