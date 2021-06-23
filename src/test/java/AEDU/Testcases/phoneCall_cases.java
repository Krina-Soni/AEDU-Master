package AEDU.Testcases;

import AEDU.Pages.AttendanceReport;
import AEDU.Pages.StudentInformation;
import org.testng.annotations.Test;
import AEDU.Pages.Lead;
import AEDU.Pages.phoneCall;

import java.awt.*;
import java.io.IOException;
import java.sql.SQLException;

public class phoneCall_cases extends Basecase{

    @Test (priority = 1)
    public void checkBlank_Phonecall() throws IOException, InterruptedException {
        logger=extent.createTest("1: Check for Blank Save__Checking Mandatory fields");
        Lead lead=new Lead(driver,logger);
        phoneCall phoneCall=new phoneCall(driver,logger);
        phoneCall.openNewTab();
        phoneCall.checkMandatoryFields();
    }

    @Test (priority = 2)
    public void savewithMandatory_phoneCall() throws IOException, InterruptedException {
        logger=extent.createTest("2: Check if allows record save with only mandatory fields");
        phoneCall phoneCall=new phoneCall(driver,logger);
        phoneCall.openNewTab();
        phoneCall.saveMandatory_phoneCall();
    }

    @Test (priority = 3)
    public void adding_phoneCall() throws IOException, InterruptedException {
        logger=extent.createTest("3: Check if system allows creating a new record with all fields filled");
//        StudentInformation SI = new StudentInformation(driver,logger);
//        SI.loginWithCredentials2("addwebsolution@gmail.com","addweb123");
        phoneCall phoneCall=new phoneCall(driver,logger);
        phoneCall.openNewTab();
        phoneCall.addingPhoneCallrec();
    }

    @Test (priority = 4)
    public void Verify_phoneCall() throws InterruptedException, SQLException, IOException {
        logger=extent.createTest("4: Verify if the record entered matched with Database");
        phoneCall phoneCall=new phoneCall(driver,logger);
        phoneCall.openNewTab();
        phoneCall.verifyingPhoneCallrec();
    }

    @Test (priority = 5)
    public void viewing_phoneCall() throws IOException, InterruptedException, SQLException, AWTException {
        logger=extent.createTest("5: Check if system allows Viewing of the created record");
        phoneCall phoneCall=new phoneCall(driver,logger);
//        StudentInformation SI = new StudentInformation(driver,logger);
//        SI.loginWithCredentials2("addwebsolution@gmail.com","addweb123");
        phoneCall.openNewTab();
        phoneCall.viewing_phoneCallrec();
//        AttendanceReport AR = new AttendanceReport(driver,logger);
//        AR.AttendenceReportofActive("9th","A", "2020-01-01", "2020-01-30");

     }

    @Test (priority = 6)
    public void update_phoneCall() throws IOException, InterruptedException {
        logger=extent.createTest("6: Check if the system allows Updating the record");
        phoneCall phoneCall=new phoneCall(driver,logger);
//        StudentInformation SI = new StudentInformation(driver,logger);
//        SI.loginWithCredentials2("addwebsolution@gmail.com","addweb123");
        phoneCall.openNewTab();
        phoneCall.updating_phoneCall();
      }

    @Test (priority = 7)
    public void verify_updated_Call() throws InterruptedException, SQLException, IOException {
        logger=extent.createTest("7: Verify if the Updated record entered matches with Database");
//        StudentInformation SI = new StudentInformation(driver,logger);
//        SI.loginWithCredentials2("addwebsolution@gmail.com","addweb123");
        phoneCall phoneCall=new phoneCall(driver,logger);
        phoneCall.openNewTab();
        phoneCall.verify_updated_phoneCall();
    }

    @Test (priority = 8)
    public void delete_phoneCall() throws IOException, InterruptedException {
        logger=extent.createTest("8: Check if the system allows Deletion of the record");
//        StudentInformation SI = new StudentInformation(driver,logger);
//        SI.loginWithCredentials2("addwebsolution@gmail.com","addweb123");
        phoneCall phoneCall=new phoneCall(driver,logger);
        phoneCall.openNewTab();
        phoneCall.delete_phoneCall();
    }

    @Test (priority = 9)
    public void verify_Delete_FromDB() throws InterruptedException, SQLException {
        logger=extent.createTest("9: Verify that the Data is Deleted from the DB");
        phoneCall phoneCall=new phoneCall(driver,logger);
        phoneCall.openNewTab();
        phoneCall.verifying_DeletefromDB();
    }
}