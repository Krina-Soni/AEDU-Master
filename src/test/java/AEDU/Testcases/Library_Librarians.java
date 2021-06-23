package AEDU.Testcases;
import AEDU.Pages.LibraryLibrarians;
import AEDU.Pages.StudentInformation;
import org.testng.annotations.Test;
import java.awt.*;
import java.io.IOException;
import java.sql.SQLException;

public class Library_Librarians extends  Basecase{
    @Test(priority = 1)
//  Test if all the mandatory fields shows validation messages
    public void librariansValidation() throws IOException, InterruptedException {
        logger = extent.createTest("Test if all the mandatory fields shows validation messages");
//      StudentInformation SI = new StudentInformation(driver, logger);
//      SI.loginWithCredentials2("addwebsolution@gmail.com", "addweb123");
        LibraryLibrarians libraryLibrarians = new LibraryLibrarians(driver, logger);
        libraryLibrarians.openNewTab();
        libraryLibrarians.LibrariansValidation();
    }

    @Test(priority = 2)
//  Test by adding librarian and compare with database.
    public void addLibrarians() throws IOException, InterruptedException, SQLException, AWTException {
        logger = extent.createTest("Test by adding librarian and compare with database.");
//      StudentInformation SI = new StudentInformation(driver, logger);
//      SI.loginWithCredentials2("addwebsolution@gmail.com", "addweb123");
        LibraryLibrarians libraryLibrarians = new LibraryLibrarians(driver, logger);
        libraryLibrarians.openNewTab();
        libraryLibrarians.AddLibrarians("TestLibrarian", "librarian@test.com", "Lorem Ipsum Address", "0987654321", "/Users/addweb/Desktop/download.png");
    }

    @Test (priority = 3)
//  Test by editing librarian and compare with database.
    public void editLibrarians() throws IOException, InterruptedException, SQLException {
        logger = extent.createTest("Test by editing librarian and compare with database.");
//      StudentInformation SI = new StudentInformation(driver, logger);
//      SI.loginWithCredentials2("addwebsolution@gmail.com", "addweb123");
        LibraryLibrarians libraryLibrarians = new LibraryLibrarians(driver, logger);
        libraryLibrarians.openNewTab();
        libraryLibrarians.EditLibrarians("TestLibrarian", "EditTestLibrarian","987", "editlibrarian@test.com");
    }

    @Test (priority = 4)
//  Test by viewing librarian and compare with database.
    public void viewLibrarians() throws IOException, InterruptedException, SQLException, AWTException {
        logger = extent.createTest("Test by viewing librarian and compare with database.");
//      StudentInformation SI = new StudentInformation(driver, logger);
//      SI.loginWithCredentials2("addwebsolution@gmail.com", "addweb123");
        LibraryLibrarians libraryLibrarians = new LibraryLibrarians(driver, logger);
        libraryLibrarians.openNewTab();
        libraryLibrarians.ViewLibrarians("EditTestLibrarian");
    }

    @Test (priority = 5)
//  Test by deleting librarian and compare with database.
    public void deleteLibrarians() throws IOException, InterruptedException, SQLException, AWTException {
        logger = extent.createTest("Test by deleting librarian and compare with database.");
//      StudentInformation SI = new StudentInformation(driver, logger);
//      SI.loginWithCredentials2("addwebsolution@gmail.com", "addweb123");
        LibraryLibrarians libraryLibrarians = new LibraryLibrarians(driver, logger);
        libraryLibrarians.openNewTab();
        libraryLibrarians.DeleteLibrarian("EditTestLibrarian");
    }
}
