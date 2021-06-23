package AEDU.Testcases;
import AEDU.Pages.LibraryAddBook;
import AEDU.Pages.LibraryAddStudent;
import AEDU.Pages.StudentInformation;
import org.testng.annotations.Test;
import java.awt.*;
import java.io.IOException;
import java.sql.SQLException;

public class Library_Add_Student extends Basecase{

//  Compare students in the list of selected class, assign library card number
    @Test(priority = 1)
    public void addBook() throws IOException, InterruptedException, SQLException {
        logger = extent.createTest("Compare students in the list of selected class, assign library card number");
//      StudentInformation S1 = new StudentInformation(driver, logger);
//      S1.loginWithCredentials2("addwebsolution@gmail.com", "addweb123");
        LibraryAddStudent libraryAddStudent = new LibraryAddStudent(driver, logger);
        libraryAddStudent.openNewTab();
        libraryAddStudent.AddStudent("9th", "A", "RIDDHI DAVE", "LCN77", "2019-20");
    }

//  Compare the list by surrendering the membership and compare with database.
    @Test(priority = 2)
    public void surrenderMembershipStudent() throws IOException, InterruptedException, SQLException, AWTException {
        logger = extent.createTest("Compare the list by surrendering the membership and compare with database");
//      StudentInformation S1 = new StudentInformation(driver, logger);
//      S1.loginWithCredentials2("addwebsolution@gmail.com", "addweb123");
        LibraryAddStudent libraryAddStudent = new LibraryAddStudent(driver, logger);
        libraryAddStudent.openNewTab();
        libraryAddStudent.SurrenderMembershipStudent("9th", "A", "RIDDHI DAVE", "LCN77", "2019-20");
    }
}