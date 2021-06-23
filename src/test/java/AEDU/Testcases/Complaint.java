package AEDU.Testcases;

import AEDU.Pages.AddComplaint;
import AEDU.Pages.StudentInformation;
import org.testng.annotations.Test;

import java.io.IOException;
import java.sql.SQLException;


public class Complaint extends Basecase {

    @Test (priority = 1)
    public void Checkvalidations() throws IOException, InterruptedException {
        logger = extent.createTest("Check Add Complain validation");
//        StudentInformation SI = new StudentInformation(driver,logger);
//        SI.loginWithCredentials2("addwebsolution@gmail.com","addweb123");
        AddComplaint Check = new AddComplaint(driver, logger);
        Check.openNewTab();
        Check.ChecktheMEndatoryFEildValidationa();
    }

    @Test (priority = 2)
    public void AddComplain() throws IOException, InterruptedException, SQLException {
        logger = extent.createTest("Check Add Complain functionality with all valid data");
        AddComplaint Add = new AddComplaint(driver, logger);
        Add.openNewTab();
        Add.AddComplainwithValiddata("krina soni","7845254785","test for demo","dpne","dobe","test add");
    }

    @Test (priority = 3)
    public void Edit() throws IOException, InterruptedException, SQLException {
        logger = extent.createTest("Check user is able to edit complain or not");
       AddComplaint update = new AddComplaint(driver,logger);
        update.openNewTab();
       update.updatecomplain("riddhi dave","test riddhi dave");
    }

    @Test (priority = 4)
    public void View() throws IOException, InterruptedException {
        logger = extent.createTest("Check View Complain is working fine or not");
        AddComplaint view = new AddComplaint(driver, logger);
        view.openNewTab();
        view.Viewcomplain();
    }

    @Test (priority = 5)
    public void Search() throws IOException, InterruptedException {
        logger = extent.createTest("Check Search Complain is working fine or not");
        AddComplaint find = new AddComplaint(driver, logger);
        find.openNewTab();
        find.Search("riddhi");
    }

    @Test (priority = 6)
    public void deleteComplain() throws IOException, InterruptedException {
        logger = extent.createTest("Check Delete Complain is working fine or not");
//        StudentInformation SI = new StudentInformation(driver,logger);
//        SI.loginWithCredentials2("addwebsolution@gmail.com","addweb123");
        AddComplaint find = new AddComplaint(driver, logger);
        find.openNewTab();
        find.DeleteComplain();
    }
}
