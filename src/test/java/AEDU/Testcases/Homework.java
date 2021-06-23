package AEDU.Testcases;
import AEDU.Pages.HomeWork;
import AEDU.Pages.StudentInformation;
import org.testng.annotations.Test;
import java.io.IOException;
import java.sql.SQLException;

public class Homework extends Basecase {
    @Test(priority = 2)
    public void AddHomeworks() throws InterruptedException, InterruptedException, SQLException, IOException {
        logger = extent.createTest("Check add Homework functionality is working or not");
//        StudentInformation SI = new StudentInformation(driver, logger);
//        SI.loginWithCredentials2("addwebsolution@gmail.com", "addweb123");
        HomeWork Add = new HomeWork(driver, logger);
        Add.AddHomeWork("Test for the Homework", "This is for testing", "05.04.2020");
    }

    @Test(priority = 1)
    public void CheckValidations()throws IOException,InterruptedException{
        logger = extent.createTest("Check Validations are working or not for Add Homework functionality");
//        StudentInformation SI = new StudentInformation(driver, logger);
//        SI.loginWithCredentials2("addwebsolution@gmail.com", "addweb123");
        HomeWork Check = new HomeWork(driver, logger);
        Check.openNewTab();
        Check.Validations();
    }

    @Test(priority = 3)
    public void Matchlist() throws IOException, InterruptedException, SQLException {
        logger = extent.createTest("Check the given list is match with the Database or not");
//        StudentInformation SI = new StudentInformation(driver, logger);
//        SI.loginWithCredentials2("addwebsolution@gmail.com", "addweb123");
        HomeWork Check = new HomeWork(driver, logger);
        Check.openNewTab();
        Check.MAtchListWithdb();
    }

    @Test(priority = 4)
    public void Views() throws IOException, InterruptedException, SQLException {
        logger = extent.createTest("Check View Homework functionality is working or not");
//        StudentInformation SI = new StudentInformation(driver, logger);
//        SI.loginWithCredentials2("addwebsolution@gmail.com", "addweb123");
        HomeWork add = new HomeWork(driver,logger);
        add.openNewTab();
        HomeWork Check = new HomeWork(driver, logger);
        Check.ViewHomework("Test for the Homework");

    }
    @Test(priority = 5)
    public void Edit ()throws IOException,InterruptedException,SQLException{
        logger = extent.createTest("Check edit Homework functionality is working or not.");
//        StudentInformation SI = new StudentInformation(driver, logger);
//        SI.loginWithCredentials2("addwebsolution@gmail.com", "addweb123");
        HomeWork add = new HomeWork(driver,logger);
        add.openNewTab();
        HomeWork edit = new HomeWork(driver, logger);
        edit.EditHomeWork("Test for the Homework","Test for the Homework1");

    }
    @Test(priority = 6)
    private void Delete()throws IOException,InterruptedException,SQLException{
        logger = extent.createTest("Check Delete functionality for the homework is working or not.");
//         StudentInformation SI = new StudentInformation(driver, logger);
//         SI.loginWithCredentials2("addwebsolution@gmail.com", "addweb123");
        HomeWork add = new HomeWork(driver,logger);
        add.openNewTab();
        HomeWork edit = new HomeWork(driver, logger);
        edit.Delete("Test for the Homework1");
    }
}