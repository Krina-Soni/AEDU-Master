package AEDU.Testcases;
import AEDU.Pages.StudentInformation;
import AEDU.Pages.StudentSearch;
import AEDU.Pages.Studentcategory;
import AEDU.Utilities.DatabaseFunctions;
import org.testng.annotations.Test;

import java.io.IOException;
import java.sql.SQLException;

public class Student_Search extends Basecase {

//  Test if all the active students are listed. DONE
    @Test (priority = 1)
    public void searchStudent() throws InterruptedException, IOException, SQLException{
        logger = extent.createTest("Verify that Default Keyword Search details are matched with Database or not");
//        StudentInformation SI = new StudentInformation(driver,logger);
//        SI.loginWithCredentials2("addwebsolution@gmail.com","addweb123");
        StudentSearch searchStudent= new StudentSearch(driver,logger);
        searchStudent.openNewTab();
        searchStudent.ListStudentDetail();
        Thread.sleep(5000);
    }

//    Test if all the active students of selected class are displayed. DONE
    @Test (priority = 2)
    public void SelectClassActive() throws InterruptedException, IOException, SQLException{
        logger = extent.createTest("Check If Students are Active And We filtered By Class only, Is records match with DB or not?");
//        StudentInformation SI = new StudentInformation(driver,logger);
//        SI.loginWithCredentials2("addwebsolution@gmail.com","addweb123");
        StudentSearch searchStudent= new StudentSearch(driver,logger);
        searchStudent.openNewTab();
        searchStudent.CheckDataByClassActive();
    }

//    Test if all the active students of selected class and section are displayed. DONE
    @Test (priority = 3)
    public void SelectClassSectionActive() throws InterruptedException, IOException, SQLException{
        logger = extent.createTest("Check If Students are Active And We filtered By Class with section & that records match with DB or not");
//        StudentInformation SI = new StudentInformation(driver,logger);
//        SI.loginWithCredentials2("addwebsolution@gmail.com","addweb123");
        StudentSearch searchStudent= new StudentSearch(driver,logger);
        searchStudent.openNewTab();
        searchStudent.CheckDataByClassAndSectionActive();
        Thread.sleep(5000);
    }

//    Test if all the inactive students of selected class and section are displayed. NOT DONE
    @Test (priority = 4)
    public void SelectClassSectionInactive() throws InterruptedException, IOException, SQLException{
        logger = extent.createTest("Check If InActive Students are filtered By Class with section & that records match with DB or not");
        StudentInformation SI = new StudentInformation(driver,logger);
        SI.loginWithCredentials2("addwebsolution@gmail.com","addweb123");
        StudentSearch searchStudent= new StudentSearch(driver,logger);
        //searchStudent.openNewTab();
        searchStudent.CheckDataByClassAndSectionInctive();
        Thread.sleep(5000);
    }

//    Test if all the active students of selected class are displayed. DONE
    @Test (priority = 5)
    public void SelectClassInactive() throws InterruptedException, IOException, SQLException{
        logger = extent.createTest("Check If InActive Students are filtered By Class & that records match with DB or not");
//        StudentInformation SI = new StudentInformation(driver,logger);
//        SI.loginWithCredentials2("addwebsolution@gmail.com","addweb123");
        StudentSearch searchStudent= new StudentSearch(driver,logger);
        searchStudent.openNewTab();
        searchStudent.CheckDataByClassInactive();
        Thread.sleep(5000);
    }

//    Test if the searched keyword data are listed. DONE
    @Test (priority = 6)
    public void SearchKeywordInAllReference() throws InterruptedException, IOException, SQLException{
        logger = extent.createTest("Check If InActive Students are filtered By Class & that records match with DB or not");
//        StudentInformation SI = new StudentInformation(driver,logger);
//        SI.loginWithCredentials2("addwebsolution@gmail.com","addweb123");
        StudentSearch searchStudent= new StudentSearch(driver,logger);
        searchStudent.openNewTab();
        searchStudent.CheckForKeyWordSearch();
        Thread.sleep(5000);
    }
}