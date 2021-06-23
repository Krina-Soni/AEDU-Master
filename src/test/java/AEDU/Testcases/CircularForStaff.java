package AEDU.Testcases;
import AEDU.Pages.CircularForstaff;
import org.testng.annotations.Test;
import java.awt.*;
import java.io.IOException;
import java.sql.SQLException;

public class CircularForStaff extends Basecase{

    @Test(priority = 1)
    public void CheckVAlidations()throws IOException,InterruptedException{
        logger = extent.createTest("Check validation for Add circular for staff.");
//        StudentInformation SI = new StudentInformation(driver, logger);
//        SI.loginWithCredentials2("addwebsolution@gmail.com", "addweb123");
        CircularForstaff check = new CircularForstaff(driver,logger);
        check.openNewTab();
        check.CheckAddCircularValidation();
    }

    @Test(priority = 2)
    public void AddValidData() throws IOException, InterruptedException, AWTException, SQLException {
        logger = extent.createTest("Check user is able to add circular for staff or not.");
//        StudentInformation SI = new StudentInformation(driver, logger);
//        SI.loginWithCredentials2("addwebsolution@gmail.com", "addweb123");
        CircularForstaff add = new CircularForstaff(driver,logger);
        add.openNewTab();
        add.AddCircularwithValiddata("Testing circular","31.03.2020","Testing for the circular");
    }
}
