package AEDU.Testcases;

import AEDU.Pages.StudentInformation;
import org.testng.annotations.Test;
import AEDU.Pages.Lead;
import AEDU.Pages.reception_page;
import java.io.IOException;
import java.sql.SQLException;

public class receptionist extends Basecase {

    @Test(priority = 1)
    public void checkBlank_AddReception() throws IOException, InterruptedException {
        logger = extent.createTest("1: Check if user can submit Blank Form (Add Reception)");
        StudentInformation SI = new StudentInformation(driver,logger);
        SI.loginWithCredentials2("addwebsolution@gmail.com","addweb123");
        reception_page receptionist = new reception_page(driver, logger);
        //receptionist.openNewTab();
        receptionist.blankSave_Receptionist();
    }

    @Test(priority = 2)
    public void check_Mandatory() throws IOException, InterruptedException {
        logger = extent.createTest("2: Check for all the mandatory fields in the Form");
        reception_page receptionist = new reception_page(driver, logger);
//        StudentInformation SI = new StudentInformation(driver,logger);
//        SI.loginWithCredentials2("addwebsolution@gmail.com","addweb123");
        Lead lead = new Lead(driver, logger);
        receptionist.openNewTab();
        receptionist.Mandatory_fields();
    }

    @Test(priority = 3)
    public void Add_validData() throws InterruptedException, IOException {
        logger = extent.createTest("3: Verify if user can add a Valid entry record");
        reception_page receptionist = new reception_page(driver, logger);
        receptionist.openNewTab();
        Lead lead = new Lead(driver, logger);
        receptionist.addValidRecord();
    }

    @Test(priority = 4)
    public void check_Duplicate_email() throws InterruptedException, IOException {
        logger = extent.createTest("4: Check for duplicate Email entry");
        reception_page receptionist = new reception_page(driver, logger);
        receptionist.openNewTab();
        Lead lead = new Lead(driver, logger);
        receptionist.duplicateEmail();
    }

    @Test(priority = 5)
    public void check_Duplicate_phone() throws InterruptedException, IOException {
        logger = extent.createTest("5: Check for duplicate Phone entry");
        reception_page receptionist = new reception_page(driver, logger);
        receptionist.openNewTab();
        receptionist.duplicatePhone();
    }

    @Test(priority = 6)
    public void view_Record() throws IOException, InterruptedException, SQLException {
        logger = extent.createTest("6: Check if User is able to view the records in the created user");
        reception_page receptionist = new reception_page(driver, logger);
        receptionist.openNewTab();
        receptionist.Viewing_record();
    }

    @Test(priority = 7)
    public void updating_fields() throws IOException, InterruptedException {
        logger = extent.createTest("7: Check if user can update the fields");
        reception_page receptionist = new reception_page(driver, logger);
//        StudentInformation SI = new StudentInformation(driver,logger);
//        SI.loginWithCredentials2("addwebsolution@gmail.com","addweb123");
        receptionist.openNewTab();
        receptionist.Update_fields();
    }

    @Test(priority = 8)
    public void verify_DeleteFromDB() throws IOException, InterruptedException, SQLException {
        logger = extent.createTest("7: Verify the Data from the Database");
        reception_page receptionist = new reception_page(driver, logger);
        receptionist.openNewTab();
        receptionist.verifying_DatafromDB();
    }
    @Test(priority = 9)
    public void delete_record() throws IOException, InterruptedException, SQLException {
        logger = extent.createTest("11: Check if user can delete the created records");
        reception_page receptionist = new reception_page(driver, logger);
        receptionist.delete_new_record();
    }

    @Test(priority = 10)
    public void verify_Delete_fromDB () throws InterruptedException, SQLException {
        logger = extent.createTest("12: Verify if the Record is Deleted from the Database");
        reception_page receptionist = new reception_page(driver, logger);
        receptionist.verifying_DeletefromDB();
        driver.quit();
    }

//    @Test(priority = 9)
//    public void login_with_new_record() throws InterruptedException, SQLException {
//        logger = extent.createTest("8: Verify if the new receptionist can login with its credentials");
//        reception_page receptionist = new reception_page(driver, logger);
//        receptionist.openNewTab();
//        receptionist.Login_with_Receptionist();
//    }
//
//    @Test(priority = 10)
//    public void check_new_record_valid() throws IOException, InterruptedException, SQLException {
//        logger = extent.createTest("10: Check if the logged in user is the same as per the new record");
//        reception_page receptionist = new reception_page(driver, logger);
//        receptionist.openNewTab();
//        receptionist.check_username();
//    }
//
}