package AEDU.Testcases;

import AEDU.Pages.AttendanceReport;
import AEDU.Pages.CircularParent;
import AEDU.Pages.StudentInformation;
import org.testng.annotations.Test;

import java.io.IOException;
import java.sql.SQLException;

public class Circular_Parent extends Basecase{
    @Test (priority = 1)
    public void addCircularValidation() throws IOException, InterruptedException {
        logger = extent.createTest("Test if all the mandatory fields shows validation messages");
//        StudentInformation SI = new StudentInformation(driver, logger);
//        SI.loginWithCredentials2("addwebsolution@gmail.com", "addweb123");
        CircularParent circularParent = new CircularParent(driver, logger);
        circularParent.openNewTab();
        circularParent.AddCircularValidation();
    }

    @Test (priority = 2)
    public void addCircular() throws IOException, InterruptedException, SQLException {
        logger = extent.createTest("Test if the circular is added and the added circular data matches with database");
//        StudentInformation SI = new StudentInformation(driver, logger);
//        SI.loginWithCredentials2("addwebsolution@gmail.com", "addweb123");
        CircularParent circularParent = new CircularParent(driver, logger);
        circularParent.openNewTab();
        circularParent.AddCircular("Lorem ipsum dolor sit amet",
                "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.",
                "/home/addweb/Desktop/dummy.pdf");
    }
}