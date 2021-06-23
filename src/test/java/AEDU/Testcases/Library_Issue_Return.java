package AEDU.Testcases;

import AEDU.Pages.StudentInformation;
import AEDU.Pages.LibraryIssuereturn;
import org.testng.annotations.Test;
import java.io.IOException;
import java.sql.SQLException;

public class Library_Issue_Return extends Basecase {
    @Test(priority = 1)
//  Test if all the teachers and students in the list are same in the database.
    public void comparetheMembersList() throws IOException, InterruptedException, SQLException {
        logger = extent.createTest("Test if all the teachers and students in the list are same in the database.");
//        StudentInformation SI = new StudentInformation(driver, logger);
//        SI.loginWithCredentials2("addwebsolution@gmail.com", "addweb123");
        LibraryIssuereturn libraryIssueReturn = new LibraryIssuereturn(driver, logger);
        libraryIssueReturn.openNewTab();
        libraryIssueReturn.ComparetheMembersList();
    }

    @Test(priority = 2)
//  Test the issued book logs.
    public void issueBookLogs() throws IOException, InterruptedException, SQLException {
        logger = extent.createTest("Test the issued book logs.");
//        StudentInformation SI = new StudentInformation(driver, logger);
//        SI.loginWithCredentials2("addwebsolution@gmail.com", "addweb123");
        LibraryIssuereturn libraryIssueReturn = new LibraryIssuereturn(driver, logger);
        libraryIssueReturn.openNewTab();
        libraryIssueReturn.IssueBookLogs("LCN12","just maths", "30.04.2020");
    }

    @Test(priority = 3)
//  Test book logs after returning.
    public void returnBookLogs() throws IOException, InterruptedException, SQLException {
        logger = extent.createTest("Test book logs after returning.");
//        StudentInformation SI = new StudentInformation(driver, logger);
//        SI.loginWithCredentials2("addwebsolution@gmail.com", "addweb123");
        LibraryIssuereturn libraryIssueReturn = new LibraryIssuereturn(driver, logger);
        libraryIssueReturn.openNewTab();
        libraryIssueReturn.ReturnBookLogs("LCN12");
    }

}
