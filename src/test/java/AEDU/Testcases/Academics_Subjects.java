package AEDU.Testcases;

import AEDU.Pages.AcademicsSubjects;
import AEDU.Pages.StudentInformation;
import org.testng.annotations.Test;

import java.awt.*;
import java.io.IOException;
import java.sql.SQLException;

public class Academics_Subjects extends Basecase{
    @Test (priority = 1)
    public void subjectValidation() throws IOException, InterruptedException, SQLException {
        logger = extent.createTest("Test if all the mandatory fields shows validation messages");
        StudentInformation SI = new StudentInformation(driver, logger);
        SI.loginWithCredentials2("addwebsolution@gmail.com", "addweb123");
        AcademicsSubjects subjects = new AcademicsSubjects(driver, logger);
        //subjects.openNewTab();
        subjects.SubjectValidation();
    }

    @Test (priority = 2)
    public void addSubject() throws IOException, InterruptedException, SQLException {
        logger = extent.createTest(" Test by adding subject and compare with database.");
//        StudentInformation SI = new StudentInformation(driver, logger);
//        SI.loginWithCredentials2("addwebsolution@gmail.com", "addweb123");
        AcademicsSubjects subjects = new AcademicsSubjects(driver, logger);
        subjects.openNewTab();
        subjects.AddSubject("Subject12", "103");
    }

    @Test (priority = 3)
    public void editSubject() throws IOException, InterruptedException, SQLException {
        logger = extent.createTest(" Test by editing subject and compare with database.");
//        StudentInformation SI = new StudentInformation(driver, logger);
//        SI.loginWithCredentials2("addwebsolution@gmail.com", "addweb123");
        AcademicsSubjects subjects = new AcademicsSubjects(driver, logger);
        subjects.openNewTab();
        subjects.EditSubject("Acting", "EditActing", "007");
    }

    @Test (priority = 4)
    public void deleteSubject() throws IOException, InterruptedException, SQLException, AWTException {
        logger = extent.createTest(" Test by deleting subject and compare with database.");
        StudentInformation SI = new StudentInformation(driver, logger);
        SI.loginWithCredentials2("addwebsolution@gmail.com", "addweb123");
        AcademicsSubjects subjects = new AcademicsSubjects(driver, logger);
        subjects.openNewTab();
        subjects.DeleteSubject("computer");
    }
}
