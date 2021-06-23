package AEDU.Testcases;
import AEDU.Pages.LibraryAddTeacher;
import AEDU.Pages.StudentInformation;
import org.testng.annotations.Test;
import java.io.IOException;
import java.sql.SQLException;

public class Library_AddTeacher extends Basecase{
    @Test(priority = 1)
//  Test if all the teacher are listed in the list and matches with teacher database
    public void compareTeacherList() throws IOException, InterruptedException, SQLException {
        logger = extent.createTest("Test if all the teacher are listed in the list and matches with teacher database");
//        StudentInformation SI = new StudentInformation(driver, logger);
//        SI.loginWithCredentials2("addwebsolution@gmail.com", "addweb123");
        LibraryAddTeacher libraryAddTeacher = new LibraryAddTeacher(driver, logger);
        libraryAddTeacher.openNewTab();
        libraryAddTeacher.CompareTeacherList();
    }

    @Test(priority = 2)
//  Test if all the teacher are listed in the list and matches with teacher database
    public void compareTeacherMemberList() throws IOException, InterruptedException, SQLException {
        logger = extent.createTest("Test if all the member teachers are listed in the list and matches with teacher database");
//        StudentInformation SI = new StudentInformation(driver, logger);
//        SI.loginWithCredentials2("addwebsolution@gmail.com", "addweb123");
        LibraryAddTeacher libraryAddTeacher = new LibraryAddTeacher(driver, logger);
        libraryAddTeacher.openNewTab();
        libraryAddTeacher.CompareTeacherMemberList("teacher");
    }

    @Test(priority = 3)
//  Test by assigning membership to teacher and match with database
    public void assignTeacherMemberShip() throws IOException, InterruptedException, SQLException {
        logger = extent.createTest("Test if all the member teachers are listed in the list and matches with teacher database");
//        StudentInformation SI = new StudentInformation(driver, logger);
//        SI.loginWithCredentials2("addwebsolution@gmail.com", "addweb123");
        LibraryAddTeacher libraryAddTeacher = new LibraryAddTeacher(driver, logger);
        libraryAddTeacher.openNewTab();
        libraryAddTeacher.AssignTeacherMemberShip("krinaaddweb@gmail.com", "LCN12");
    }

}
