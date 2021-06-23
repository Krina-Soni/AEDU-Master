package AEDU.Testcases;

import AEDU.Pages.StudentInformation;
import AEDU.Utilities.DatabaseFunctions;
import org.testng.annotations.Test;
import java.sql.SQLException;
import java.io.IOException;

public class StudentAdmission extends Basecase {
//    @Test
//    public void Sucessful_login() throws InterruptedException, IOException {
//        logger = extent.createTest("Check login functionality");
//        StudentInformation login = new StudentInformation(driver,logger);
//        login.StudentInformation();
//        login.loginWithCredentials("addwebsolution@gmail.com","test");
//        login.loginWithCredentials1("addwebsolutionn@gmail.com","addweb123");
//        login.loginWithCredentials2("addwebsolution@gmail.com","addweb123");
//    }
    @Test (priority = 1)
    public void CheckwithInvalidPassword() throws InterruptedException, IOException {

        StudentInformation login = new StudentInformation(driver,logger);
        logger = extent.createTest("Verify that Login has Invalid Password Entered");
        login.loginWithCredentials("addwebsolution@gmail.com","234242424");
        // Thread.sleep(5000);
    }
    @Test (priority = 2)
    public void CheckwithInvalidEmail() throws InterruptedException, IOException {

        StudentInformation login = new StudentInformation(driver,logger);
        logger = extent.createTest("Verify that Login has Invalid Email Entered");
        login.loginWithCredentials1("addwebsolutionn@gmail.com","addweb123");

    }
    @Test (priority = 3)
    public void Checkwithvalidcredentials() throws InterruptedException, IOException {
        StudentInformation login = new StudentInformation(driver,logger);
        logger = extent.createTest("Verify that user has entered valid Credentials to login");
        login.loginWithCredentials2("addwebsolution@gmail.com","addweb123");
    }

     @Test (priority = 4)
    public void Check_connection() throws InterruptedException, IOException, SQLException {
        logger = extent.createTest("Verify that Project has Database integration");
        DatabaseFunctions DBConnect = new DatabaseFunctions(logger);
        DBConnect.printUserNamePassword();
    }
}