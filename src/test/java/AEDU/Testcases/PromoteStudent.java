package AEDU.Testcases;

import AEDU.Actions.VerificationClass;
import AEDU.Pages.*;
import org.testng.annotations.Test;

import java.io.IOException;
import java.sql.SQLException;

public class PromoteStudent extends Basecase {

    @Test (priority = 1)
    public void promoteStudentList() throws InterruptedException, IOException, SQLException {
        logger = extent.createTest("Check class field and section feild validation");
        StudentInformation SI = new StudentInformation(driver, logger);
        SI.loginWithCredentials2("addwebsolution@gmail.com", "addweb123");
        AddStudent add = new AddStudent(driver,logger);
       add.Importstudentcsv();
        PromoteStudents promoteStudents3 = new PromoteStudents(driver, logger);
//        promoteStudents3.openNewTab();
        promoteStudents3.listdata();
    }


    @Test (priority = 2)
    public void validations() throws InterruptedException, IOException {
        logger = extent.createTest("Verify with the no filter value ");
        PromoteStudents promoteStudents = new PromoteStudents(driver, logger);
//        StudentInformation SI = new StudentInformation(driver, logger);
//        SI.loginWithCredentials2("addwebsolution@gmail.com", "addweb123");
        promoteStudents.openNewTab();
        promoteStudents.validation();
    }
    @Test (priority = 3)
    public void checkvalidations() throws InterruptedException, IOException {
        logger = extent.createTest("Check class field and section field validation");
        PromoteStudents promoteStudents1 = new PromoteStudents(driver, logger);
//        StudentInformation SI = new StudentInformation(driver, logger);
//        SI.loginWithCredentials2("addwebsolution@gmail.com", "addweb123");
        promoteStudents1.openNewTab();
        promoteStudents1.feildvalidations();
    }
    @Test (priority = 4)
    public void checksectionfeildvalidation()throws InterruptedException,IOException {
        logger = extent.createTest("Check class field validation");
//        StudentInformation SI = new StudentInformation(driver, logger);
//        SI.loginWithCredentials2("addwebsolution@gmail.com", "addweb123");
        PromoteStudents promoteStudents2 = new PromoteStudents(driver, logger);
        promoteStudents2.openNewTab();
        promoteStudents2.classfeildvalidations();
    }

    @Test (priority = 5)
    public void PromoteinNextsession() throws InterruptedException, IOException, SQLException {
        logger=extent.createTest("Promte student in next session Student in the next Session");
//        StudentInformation SI = new StudentInformation(driver, logger);
//        SI.loginWithCredentials2("addwebsolution@gmail.com", "addweb123");
//        AddStudent add = new AddStudent(driver,logger);
//        add.SetudentAddmissionform("458745","676","riddhi","dave","'9898787678");
        PromoteStudents Nextsession=new PromoteStudents(driver,logger);
        Nextsession.openNewTab();
        Nextsession.Promoteinsession();
    }

    @Test (priority = 6)
    public  void leavestudent()throws IOException,InterruptedException{
        logger=extent.createTest("Leave student from school");
        PromoteStudents leave =new PromoteStudents(driver,logger);
        leave.openNewTab();
        leave.LeaveStudent();
    }
}