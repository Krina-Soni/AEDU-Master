package AEDU.Testcases;

import AEDU.Pages.AcademicsSubjects;
import AEDU.Pages.AcademicsTeachers;
import AEDU.Pages.StudentInformation;
import org.testng.annotations.Test;

import java.awt.*;
import java.io.IOException;
import java.sql.SQLException;

public class Academics_Teachers extends  Basecase {
    @Test(priority = 1)
    public void teacherValidation() throws IOException, InterruptedException {
        logger = extent.createTest("Test if all the mandatory fields shows validation messages");
//        StudentInformation SI = new StudentInformation(driver, logger);
//        SI.loginWithCredentials2("addwebsolution@gmail.com", "addweb123");
        AcademicsTeachers teachers = new AcademicsTeachers(driver, logger);
        teachers.openNewTab();
        teachers.TeacherValidation();
    }

    @Test(priority = 2)
    public void addTeacher() throws IOException, InterruptedException, SQLException {
        logger = extent.createTest("Test by adding teacher and compare with database.");
//        StudentInformation SI = new StudentInformation(driver, logger);
//        SI.loginWithCredentials2("addwebsolution@gmail.com", "addweb123");
        AcademicsTeachers teachers = new AcademicsTeachers(driver, logger);
        teachers.openNewTab();
        teachers.AddTeacher("TestTeachers", "teacher@testing.com", "Lorem Ipsum Address", "0986654341", "0989566");
    }

    @Test (priority = 3)
    public void editTeacher() throws IOException, InterruptedException, SQLException {
        logger = extent.createTest(" Test by editing teacher and compare with database.");
//        StudentInformation SI = new StudentInformation(driver, logger);
//        SI.loginWithCredentials2("addwebsolution@gmail.com", "addweb123");
        AcademicsTeachers teachers = new AcademicsTeachers(driver, logger);
        teachers.openNewTab();
        teachers.EditTeacher("TestTeacher", "EditaAayushi", "editaaayushi.addweb@gmail.com","0987644441", "0098789");
    }

    @Test (priority = 4)
    public void deleteTeacher() throws IOException, InterruptedException, SQLException, AWTException {
        logger = extent.createTest("Delete teacher and compare with database.");
//        StudentInformation SI = new StudentInformation(driver, logger);
//        SI.loginWithCredentials2("addwebsolution@gmail.com", "addweb123");
        AcademicsTeachers teachers = new AcademicsTeachers(driver, logger);
        teachers.openNewTab();
        teachers.DeleteTeacher("Bhatt");
    }
}