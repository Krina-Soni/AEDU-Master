package AEDU.Testcases;
import AEDU.Pages.*;
import org.testng.annotations.Test;
import java.awt.*;
import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;

public class CircularLists extends Basecase{

    @Test(priority = 1)
    public void CheckValidations() throws InterruptedException, IOException {
        logger = extent.createTest("Check validation for Circular list page filters are working or not.");
//        StudentInformation SI = new StudentInformation(driver, logger);
//        SI.loginWithCredentials2("addwebsolution@gmail.com", "addweb123");
        CircularList Check = new CircularList(driver, logger);
        Check.openNewTab();
        Check.CheckFiltervalidation();
    }

    @Test(priority = 2)
    public void ViewCircular() throws IOException, InterruptedException {
        logger = extent.createTest("Check view circular functionality is working or not");
//      StudentInformation SI = new StudentInformation(driver, logger);
//      SI.loginWithCredentials2("addwebsolution@gmail.com", "addweb123");
        CircularList View = new CircularList(driver, logger);
        View.openNewTab();
        View.CheckViewCircular();
    }

    @Test(priority = 3)
    public void Checkwithdb() throws IOException, InterruptedException, SQLException {
        logger = extent.createTest("Match Circular data with DataBase");
//        StudentInformation SI = new StudentInformation(driver, logger);
//        SI.loginWithCredentials2("addwebsolution@gmail.com", "addweb123");
        CircularList MATCH = new CircularList(driver, logger);
        MATCH.openNewTab();
        MATCH.MatchlistWithDatabase();
    }

    @Test(priority = 4)
    public void Edit() throws IOException, InterruptedException, SQLException {
        logger = extent.createTest("Check Edit circular functionality is working or not");
//        StudentInformation SI = new StudentInformation(driver, logger);
//        SI.loginWithCredentials2("addwebsolution@gmail.com", "addweb123");
        CircularList edit = new CircularList(driver, logger);
        edit.openNewTab();
        edit.EditList("Testing circular", "Testing circular1");

    }

    @Test(priority = 5)
    public void Delete() throws IOException, InterruptedException, SQLException, AWTException {
        logger = extent.createTest("Check Delete circular functionality is working or not");
//      StudentInformation SI = new StudentInformation(driver, logger);
//      SI.loginWithCredentials2("addwebsolution@gmail.com", "addweb123");
//      CircularForstaff ADD = new CircularForstaff(driver, logger);
//      ADD.AddCircularwithValiddata("Testing circular", "31.03.2020", "Testing for the circular");
        CircularList Delete = new CircularList(driver, logger);
        Delete.openNewTab();
        Delete.Deletecircular("Testing circular1", "Testing circular1");
    }

    @Test(priority = 6)
    public void Comapredate() throws IOException, InterruptedException, ParseException {
        logger = extent.createTest("Check filter is wotking properly or not");
//        StudentInformation SI = new StudentInformation(driver, logger);
//        SI.loginWithCredentials2("addwebsolution@gmail.com", "addweb123");
        CircularList match = new CircularList(driver, logger);
        match.openNewTab();
        match.matchdate();
    }
}