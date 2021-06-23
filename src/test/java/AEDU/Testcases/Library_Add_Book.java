package AEDU.Testcases;
import AEDU.Pages.LibraryAddBook;
import AEDU.Pages.StudentInformation;
import org.testng.annotations.Test;

import java.awt.*;
import java.io.IOException;
import java.sql.SQLException;

public class Library_Add_Book extends Basecase {

//  Test if all the mandatory fields shows validation messages
    @Test (priority = 1)
    public void addBookValidation() throws IOException, InterruptedException {
        logger = extent.createTest("Test if all the mandatory fields shows validation messages");
//        StudentInformation S1 = new StudentInformation(driver, logger);
//        S1.loginWithCredentials2("addwebsolution@gmail.com", "addweb123");
        LibraryAddBook libraryAddBook = new LibraryAddBook(driver, logger);
        libraryAddBook.AddBookValidation();
    }

//  Test if the book is added and compare with database
    @Test (priority = 2)
    public void addBook() throws IOException, InterruptedException, SQLException {
        logger = extent.createTest("Test if the book is added and compare with database");
//      StudentInformation S1 = new StudentInformation(driver, logger);
//      S1.loginWithCredentials2("addwebsolution@gmail.com", "addweb123");
        LibraryAddBook libraryAddBook = new LibraryAddBook(driver, logger);
        libraryAddBook.openNewTab();
        libraryAddBook.AddBook("Rich Dad Poor Dad", "876", "ISBN876",
                "Warner Books Ed", "Robert Kiyosaki Sharon L. Lechter", "Economics",
                "A15", "10", "502", "The international runaway bestseller");
    }

//  Test by editing book and compare with database
    @Test (priority = 3)
    public void editBook() throws IOException, InterruptedException, SQLException {
        logger = extent.createTest("Test by editing book and compare with database");
//      StudentInformation S1 = new StudentInformation(driver, logger);
//      S1.loginWithCredentials2("addwebsolution@gmail.com", "addweb123");
        LibraryAddBook libraryAddBook = new LibraryAddBook(driver, logger);
        libraryAddBook.openNewTab();
        libraryAddBook.EditBook("ISBN876", "20");
    }

//  Test by deleting book and compare with database
    @Test (priority = 4)
    public void deleteBook() throws IOException, InterruptedException, SQLException, AWTException {
        logger = extent.createTest("Test by deleting book and compare with database");
//      StudentInformation S1 = new StudentInformation(driver, logger);
//      S1.loginWithCredentials2("addwebsolution@gmail.com", "addweb123");
        LibraryAddBook libraryAddBook = new LibraryAddBook(driver, logger);
        libraryAddBook.openNewTab();
        libraryAddBook.DeleteBook("Rich Dad Poor Dad");
    }

//  Test if the Book List matches with database
    @Test (priority = 5)
    public void compareBookList() throws IOException, InterruptedException, SQLException {
        logger = extent.createTest("Test if the Book List matches with database");
//      StudentInformation S1 = new StudentInformation(driver, logger);
//      S1.loginWithCredentials2("addwebsolution@gmail.com", "addweb123");
        LibraryAddBook libraryAddBook = new LibraryAddBook(driver, logger);
        libraryAddBook.openNewTab();
        libraryAddBook.CompareBookList();
    }
}