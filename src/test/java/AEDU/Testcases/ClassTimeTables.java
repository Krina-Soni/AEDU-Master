package AEDU.Testcases;
import AEDU.Pages.ClassTimetable;
import AEDU.Pages.StudentInformation;
import AEDU.Pages.Studentcategory;
import org.testng.annotations.Test;
import java.io.IOException;
import java.sql.SQLException;

public class ClassTimeTables extends Basecase{
    @Test(priority = 1)
    public void MatchTimetableWithDb()throws IOException,InterruptedException, SQLException {
        logger = extent.createTest("Match Time-table with the Database");
//        StudentInformation SI = new StudentInformation(driver, logger);
//        SI.loginWithCredentials2("addwebsolution@gmail.com", "addweb123");
        ClassTimetable View = new ClassTimetable(driver,logger);
        View.ChecktheTimetablewithDatabase();
    }
    @Test(priority = 2)
    public void AddTimeTable()throws IOException,InterruptedException{
        logger = extent.createTest("Check user is able to add timetable or not.");
//        StudentInformation SI = new StudentInformation(driver, logger);
//        SI.loginWithCredentials2("addwebsolution@gmail.com", "addweb123");
        ClassTimetable add = new ClassTimetable(driver,logger);
        add.openNewTab();
        add.AddTimeTable("03:00 PM","04:00 PM");

    }
    @Test(priority = 3)
    public void CheckWiththeSameTime()throws IOException,InterruptedException{
        logger = extent.createTest("Check if Teacher is occupaid for the class");
//        StudentInformation SI = new StudentInformation(driver, logger);
//        SI.loginWithCredentials2("addwebsolution@gmail.com", "addweb123");
        ClassTimetable same = new ClassTimetable(driver,logger);
        same.openNewTab();
        same.CheckifTeacherisOccupied("03:00 PM","04:00 PM","2");
    }
    @Test(priority = 4)
    public void CheckIfTimeIsOcupied()throws IOException,InterruptedException{
        logger = extent.createTest("Check if Time is occupaid for the class");
//        StudentInformation SI = new StudentInformation(driver, logger);
//        SI.loginWithCredentials2("addwebsolution@gmail.com", "addweb123");
        ClassTimetable Time = new ClassTimetable(driver,logger);
        Time.openNewTab();
        Time.CheckIfTimeISOccupied("03:00 PM","04:00 PM","3");
    }
    @Test(priority = 5)
    public void BasicValidations()throws  IOException,InterruptedException{
        logger = extent.createTest("Check if Time is occupaid for the class");
//        StudentInformation SI = new StudentInformation(driver, logger);
//        SI.loginWithCredentials2("addwebsolution@gmail.com", "addweb123");
        ClassTimetable Validation = new ClassTimetable(driver,logger);
        Validation.openNewTab();
        Validation.CheckBasicValidations("03:00 PM","02:00 PM","2","04:00 PM");
    }
}