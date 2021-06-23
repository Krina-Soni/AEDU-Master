package AEDU.Testcases;


import AEDU.Pages.Postalreceive;
import AEDU.Pages.StudentInformation;
import org.testng.annotations.Test;

import java.io.IOException;
import java.sql.SQLException;

public class PostalReceive extends Basecase {

    @Test(priority = 1)
    public void CheckValidations() throws IOException, InterruptedException {
        logger = extent.createTest("Check add Postal Receive validation");
//        StudentInformation SI = new StudentInformation(driver, logger);
//        SI.loginWithCredentials2("addwebsolution@gmail.com", "addweb123");
        Postalreceive check = new Postalreceive(driver, logger);
        check.openNewTab();
        check.Checkwithcredential();
    }

    @Test(priority = 2)
    public void Adddata() throws IOException, InterruptedException, SQLException {
        logger = extent.createTest("Add valid data in to postal receive form");
//        StudentInformation SI = new StudentInformation(driver, logger);
//        SI.loginWithCredentials2("addwebsolution@gmail.com", "addweb123");
        Postalreceive add = new Postalreceive(driver, logger);
        add.openNewTab();
        add.AddvalidData("Test Riddhi");
    }


    @Test(priority = 3)
    public void Viewreceive() throws IOException, InterruptedException {
        logger = extent.createTest("Check View Postal Receive is working fine or not");
        Postalreceive view = new Postalreceive(driver, logger);
        view.openNewTab();
        view.View("Demo riddhi");
    }

    @Test (priority = 4)
    public void Edit() throws IOException, InterruptedException, SQLException {
        logger = extent.createTest("Check Edit Postal Receive is working fine or not");
        Postalreceive view =new Postalreceive(driver,logger);
        view.openNewTab();
        view.EDITData("riddhi dave","Testing Riddhi");
    }

    @Test (priority = 5)
    public void Deletes()throws IOException, InterruptedException {
        logger = extent.createTest("Check Delete Postal Receive is working fine or not");
        Postalreceive Delete =new Postalreceive(driver,logger);
        Delete.openNewTab();
        Delete.Deletes();
    }
}
