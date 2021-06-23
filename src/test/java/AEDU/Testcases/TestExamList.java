package AEDU.Testcases;

import AEDU.Pages.ExamList;
import AEDU.Pages.Lead;
import org.testng.annotations.Test;
import java.io.IOException;
import java.sql.SQLException;

public class TestExamList extends Basecase {

    @Test(priority = 1)
    public void checkBlanksubmit() throws InterruptedException, IOException {
        logger = extent.createTest("1: Check for Blank Save__Checking Mandatory fields");
        Lead lead = new Lead(driver, logger);
        ExamList examList = new ExamList(driver, logger);
        //lead.login();
        lead.redirect_to_Examination();
        examList.checkBlanksubmission();
    }

    @Test(priority = 2)
    public void Check_valid_AddExam() throws InterruptedException, IOException {
        logger = extent.createTest("2: Check for Valid Entry in The Exam List");
        ExamList examList=new ExamList(driver,logger);
        examList.openNewTab();
        examList.check_Add();
    }

    @Test(priority = 3)
    public void verify_Add_DB() throws InterruptedException, SQLException {
        logger = extent.createTest("3: Verifying the Added Exam list from Backend");
        ExamList examList=new ExamList(driver,logger);
        examList.openNewTab();
        examList.verify_addDB();
    }

/*    @Test(priority = 4)
    public void viewExamStatus_NotAlloted() throws InterruptedException, IOException {
        logger = extent.createTest("4: Check the Status of the Exam Not Allotted");
        ExamList examList=new ExamList(driver,logger);
        examList.openNewTab();
        examList.examStatus_notAllot();
    }

    @Test(priority = 5)
    public void viewExamStatus_Alloted() throws InterruptedException, IOException {
        logger = extent.createTest("5: Check the Status of the Exam Allotted");
        ExamList examList=new ExamList(driver,logger);
        examList.openNewTab();
        examList.examStatus_Allot();
    }

    @Test(priority = 6)
    public void viewExamSchedule() throws InterruptedException, IOException {
        logger = extent.createTest("6: Checking the Exam Schedule");
        ExamList examList=new ExamList(driver,logger);
        examList.openNewTab();
        examList.view_ExamSchedule();
    }*/

    @Test(priority = 7)
    public void check_Edit_Entry() throws InterruptedException, IOException {
        logger = extent.createTest("7: Check for Blank Save__Checking Mandatory fields");
        ExamList examList=new ExamList(driver,logger);
        examList.openNewTab();
        examList.editing_entry();
    }

    @Test(priority = 8)
    public void delete_entry() throws InterruptedException, SQLException, IOException {
        logger = extent.createTest("8: Check if User is able to Delete the Exam list Entry");
        ExamList examList=new ExamList(driver,logger);
        examList.openNewTab();
        examList.deleting_entry();
    }

    @Test(priority = 9)
    public void verify_delete_listing() throws InterruptedException, SQLException, IOException {
        logger = extent.createTest("9: Check if User is able to Delete the Exam list Entry");
        ExamList examList=new ExamList(driver,logger);
        examList.openNewTab();
        examList.verify_delete_listing();
    }

    @Test(priority = 10)
    public void verify_delete_DB() throws InterruptedException, SQLException {
        logger = extent.createTest("10: Verifying if the Deleted Entry is Present in Database");
        ExamList examList=new ExamList(driver,logger);
        examList.openNewTab();
        examList.verify_Delete_DB();
    }
}