package AEDU.Testcases;

import AEDU.Pages.AcademicsTeachers;
import AEDU.Pages.AttendanceReport;
import AEDU.Pages.StudentInformation;
import org.testng.annotations.Test;

import java.awt.*;
import java.io.IOException;
import java.sql.SQLException;

public class Attendance_Report extends Basecase{
    @Test(priority = 1)
    public void attendenceValidation() throws IOException, InterruptedException {
        logger = extent.createTest("Test if all the mandatory fields shows validation messages");
//        StudentInformation SI = new StudentInformation(driver, logger);
//        SI.loginWithCredentials2("addwebsolution@gmail.com", "addweb123");
        AttendanceReport report = new AttendanceReport(driver, logger);
        report.openNewTab();
        report.AttendenceValidation();
    }

    @Test(priority = 2)
    public void attendenceReportofActive() throws IOException, InterruptedException, SQLException, AWTException {
        logger = extent.createTest("Test if active student attendance matches with database.");
//        StudentInformation SI = new StudentInformation(driver, logger);
//        SI.loginWithCredentials2("addwebsolution@gmail.com", "addweb123");
        AttendanceReport report = new AttendanceReport(driver, logger);
        report.openNewTab();
        report.AttendenceReportofActive("11th","A", "2020-03-01", "2020-03-31");
    }

    @Test(priority = 3)
    public void attendenceReportofInActive() throws IOException, InterruptedException, SQLException, AWTException {
        logger = extent.createTest("Test if inactive student attendance matches with database.");
//        StudentInformation SI = new StudentInformation(driver, logger);
//        SI.loginWithCredentials2("addwebsolution@gmail.com", "addweb123");
        AttendanceReport report = new AttendanceReport(driver, logger);
        report.openNewTab();
        report.AttendenceReportofInActive("11th","A", "2020-03-01", "2020-03-31");
    }
}