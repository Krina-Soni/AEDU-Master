package AEDU.Testcases;

import AEDU.Pages.Lead;
import AEDU.Pages.StudentAttendance;
import org.testng.annotations.Test;

import java.io.IOException;
import java.sql.SQLException;

public class StudentAttendanceTest extends Basecase {

    @Test(priority = 1)
    public void CheckBlank() throws IOException, InterruptedException {

        logger = extent.createTest("1.To check for the blank data");
        Lead lead = new Lead(driver, logger);
        StudentAttendance stuAtt = new StudentAttendance(driver, logger);
        //lead.login();
        stuAtt.checkBlankStudent();
    }

    @Test(priority = 2)
    public void CheckValidEntry() throws InterruptedException, IOException {

        logger = extent.createTest("2.To check for Valid Search Entry");
        StudentAttendance stuAtt = new StudentAttendance(driver, logger);
        stuAtt.openNewTab();
        stuAtt.check_validEntry();
    }


    @Test(priority = 3)
    public void verify_validResultDB() throws InterruptedException, SQLException, IOException {

        logger = extent.createTest("3. Verifying valid Results from the Database");
        StudentAttendance stuAtt = new StudentAttendance(driver, logger);
        stuAtt.openNewTab();
        stuAtt.verifyValidResultsfromDB();
    }

    @Test(priority = 4)
    public void savingAttendance() throws InterruptedException, IOException {
        logger = extent.createTest("4. Verify if allows the attendance to be saved");
        StudentAttendance stuAtt = new StudentAttendance(driver, logger);
        stuAtt.openNewTab();
        stuAtt.saving_Attendance();
    }


    @Test(priority = 5)
    public void VerifySaveAttendance() throws InterruptedException, IOException, SQLException {
        logger = extent.createTest("5. Verify Save attendance");
        StudentAttendance stuAtt = new StudentAttendance(driver, logger);
        stuAtt.openNewTab();
        stuAtt.verifySave_Attendance();
    }

    @Test(priority = 6)
    public void UpdateAttendance() throws IOException, InterruptedException {

        logger = extent.createTest("6. Check if allows Updating attendance");
        StudentAttendance stuAtt = new StudentAttendance(driver, logger);
        stuAtt.openNewTab();
        stuAtt.update_Attendance();
    }

    @Test(priority = 7)
    public void MarkasHoliday() throws IOException, InterruptedException {
        logger = extent.createTest("7. Check if the day can be marked as Holiday");
        StudentAttendance stuAtt = new StudentAttendance(driver, logger);
        stuAtt.openNewTab();
        stuAtt.Mark_asHoliday();
    }


    @Test(priority = 8)
    public void Verify_MarkasHoliday() throws IOException, InterruptedException {
        logger = extent.createTest("9. Verifying the day has been marked as holiday");
        StudentAttendance stuAtt = new StudentAttendance(driver, logger);
        stuAtt.openNewTab();
        stuAtt.Verify_Mark_asHoliday();
    }
}