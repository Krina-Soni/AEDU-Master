package AEDU.Testcases;
import AEDU.Pages.AssignSubject;
import AEDU.Pages.StudentInformation;
import org.testng.annotations.Test;
import java.io.IOException;
import java.sql.SQLException;

public class AssignSubjects extends Basecase {
    @Test
    public void CheckFilterValidations() throws IOException, InterruptedException {
        logger = extent.createTest("Check the Assign Subject filter validation are working or not.");
//        StudentInformation SI = new StudentInformation(driver, logger);
//        SI.loginWithCredentials2("addwebsolution@gmail.com", "addweb123");
        AssignSubject Check = new AssignSubject(driver, logger);
        Check.openNewTab();
        Check.CheckFiltervalidation();
    }

    @Test
    public void CheckAssignSubject() throws IOException, InterruptedException {
        logger = extent.createTest("Check the Assign Subject Functionality is working fine or not.");
//        StudentInformation SI = new StudentInformation(driver, logger);
//        SI.loginWithCredentials2("addwebsolution@gmail.com", "addweb123");
        AssignSubject Add = new AssignSubject(driver, logger);
        Add.openNewTab();
        Add.CheckAssignSubjectFunctionality();
    }

    @Test
    public void DbMatch() throws InterruptedException, IOException, SQLException {
        logger = extent.createTest("Check the value in front is match with the database or not");
//        StudentInformation SI = new StudentInformation(driver, logger);
//        SI.loginWithCredentials2("addwebsolution@gmail.com", "addweb123");
        AssignSubject DbCheck = new AssignSubject(driver, logger);
        DbCheck.openNewTab();
        DbCheck.CheckDataWithDatabase();
    }
    @Test
    public void SubjectlistMatchWithDb() throws InterruptedException, IOException, SQLException {
        logger = extent.createTest("Check all the subject shown in the dropdown is correct or not");
//        StudentInformation SI = new StudentInformation(driver, logger);
//        SI.loginWithCredentials2("addwebsolution@gmail.com", "addweb123");
        AssignSubject subject = new AssignSubject(driver, logger);
        subject.openNewTab();
        subject.CompareSubjectlistwithDatabase();
    }
}
