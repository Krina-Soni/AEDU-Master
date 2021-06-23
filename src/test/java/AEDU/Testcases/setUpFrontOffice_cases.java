package AEDU.Testcases;

import org.testng.annotations.Test;
import AEDU.Pages.Lead;
import AEDU.Pages.setUp_FrontOffice_pages;
import java.io.IOException;
import java.sql.SQLException;


public class setUpFrontOffice_cases extends Basecase{

    @Test (priority = 1)
    public void Checking_BlankPurpose() throws IOException, InterruptedException {
        logger= extent.createTest("1: Verify if User can Save Blank Purpose");
        Lead lead=new Lead(driver,logger);
        setUp_FrontOffice_pages setUp_FrontOffice_pages=new setUp_FrontOffice_pages(driver,logger);
        setUp_FrontOffice_pages.openNewTab();
        //lead.redirect_to_FrontOffice();
        setUp_FrontOffice_pages.checkBlank_purpose();
    }

    @Test  (priority = 2)
    public void addingPurpose() throws IOException, InterruptedException {
        logger= extent.createTest("2: Verify if User can add Purpose");
        setUp_FrontOffice_pages setUp_frontOffice_pages=new setUp_FrontOffice_pages(driver,logger);
        setUp_frontOffice_pages.openNewTab();
        setUp_frontOffice_pages.adding_Purpose();
    }

    @Test (priority = 3)
    public void Edit_added_listing_purpose() throws IOException, InterruptedException {
        logger= extent.createTest("3: Verify if User can Edit the Record");
        setUp_FrontOffice_pages setUp_frontOffice_pages=new setUp_FrontOffice_pages(driver,logger);
        setUp_frontOffice_pages.openNewTab();
        setUp_frontOffice_pages.edit_addListing();
    }

    @Test (priority = 4)
    public void dataBase_verify() throws InterruptedException, SQLException {
        logger= extent.createTest("4: Verifying the Data from the Database");
        setUp_FrontOffice_pages setUp_frontOffice_pages=new setUp_FrontOffice_pages(driver,logger);
        setUp_frontOffice_pages.openNewTab();
        setUp_frontOffice_pages.verify_fromDB_purpose();
    }

    @Test (priority = 5)
    public void Delete_listRecord_purpose() throws IOException, InterruptedException {
        logger= extent.createTest("5: Verify if User can Delete Purpose");
        setUp_FrontOffice_pages setUp_frontOffice_pages=new setUp_FrontOffice_pages(driver,logger);
        setUp_frontOffice_pages.openNewTab();
        setUp_frontOffice_pages.delete_Record();
    }

   //Complaint
    @Test  (priority = 6)
    public void Checking_BlankComplaint() throws IOException, InterruptedException {
        logger= extent.createTest("6: Check if Allows submit of Blank Complaint");
        setUp_FrontOffice_pages setUp_frontOffice_pages=new setUp_FrontOffice_pages(driver,logger);
        setUp_frontOffice_pages.openNewTab();
        setUp_frontOffice_pages.checkBlank_complaint();
    }

    @Test (priority = 7)
    public void checkAdd__complaint() throws IOException, InterruptedException {
        logger= extent.createTest("7: Verify if User can add Complaint");
        setUp_FrontOffice_pages setUp_frontOffice_pages=new setUp_FrontOffice_pages(driver,logger);
        setUp_frontOffice_pages.openNewTab();
        setUp_frontOffice_pages.adding_Complaint();
    }

    @Test  (priority = 8)
    public void Edit_added_listing_complaint() throws IOException, InterruptedException {
        logger= extent.createTest("8: Verify if User can Edit the Complaint");
        setUp_FrontOffice_pages setUp_frontOffice_pages=new setUp_FrontOffice_pages(driver,logger);
        setUp_frontOffice_pages.openNewTab();
        setUp_frontOffice_pages.edit_addListing_complaint();
    }

    @Test (priority = 9)
    public void verify_fromDB_Complaint() throws InterruptedException, SQLException {
        logger= extent.createTest("9: Verify if User can Search the Complaint");
        setUp_FrontOffice_pages setUp_frontOffice_pages=new setUp_FrontOffice_pages(driver,logger);
        setUp_frontOffice_pages.openNewTab();
        setUp_frontOffice_pages.verify_fromDB_complaint();
    }

    @Test (priority = 10)
    public void Delete_listRecord_complaint() throws IOException, InterruptedException {
        logger= extent.createTest("10: Verify if User can Delete Complaint");
        setUp_FrontOffice_pages setUp_frontOffice_pages=new setUp_FrontOffice_pages(driver,logger);
        setUp_frontOffice_pages.openNewTab();
        setUp_frontOffice_pages.delete_Record_complaint();
    }

    // Source..............
    @Test (priority = 11)
    public void Checking_BlankSource() throws IOException, InterruptedException {
        logger= extent.createTest("11: Check if Allows submit of Blank Source");
        setUp_FrontOffice_pages setUp_frontOffice_pages=new setUp_FrontOffice_pages(driver,logger);
        setUp_frontOffice_pages.openNewTab();
        setUp_frontOffice_pages.checkBlank_Source();
    }

    @Test (priority = 12)
    public void checkAdd__Source() throws IOException, InterruptedException {
        logger= extent.createTest("12: Verify if User can add Source");
        setUp_FrontOffice_pages setUp_frontOffice_pages=new setUp_FrontOffice_pages(driver,logger);
        setUp_frontOffice_pages.openNewTab();
        setUp_frontOffice_pages.adding_Source();
    }

    @Test (priority = 13)
    public void Edit_added_listing_Source() throws IOException, InterruptedException {
        logger= extent.createTest("13: Verify if User can Edit the Source");
        setUp_FrontOffice_pages setUp_frontOffice_pages=new setUp_FrontOffice_pages(driver,logger);
        setUp_frontOffice_pages.openNewTab();
        setUp_frontOffice_pages.edit_addListing_Source();
    }

    @Test (priority = 14)
    public void verify_fromDB_Source() throws InterruptedException, SQLException {
        logger= extent.createTest("14: Verifying the Records from the Database");
        setUp_FrontOffice_pages setUp_frontOffice_pages=new setUp_FrontOffice_pages(driver,logger);
        setUp_frontOffice_pages.openNewTab();
        setUp_frontOffice_pages.verify_fromDB_source();
    }

    @Test (priority = 15)
    public void Delete_listRecord_Source() throws IOException, InterruptedException {
        logger= extent.createTest("15: Verify if User can Delete Source");
        setUp_FrontOffice_pages setUp_frontOffice_pages=new setUp_FrontOffice_pages(driver,logger);
        setUp_frontOffice_pages.openNewTab();
        setUp_frontOffice_pages.delete_Record_Source();
    }

    //Reference..............
    @Test (priority = 16)
    public void Checking_BlankReference() throws IOException, InterruptedException {
        logger= extent.createTest("16: Check if Allows submit of Blank Reference");
        setUp_FrontOffice_pages setUp_frontOffice_pages=new setUp_FrontOffice_pages(driver,logger);
        setUp_frontOffice_pages.openNewTab();
        setUp_frontOffice_pages.checkBlank_Reference();
    }

    @Test (priority = 17)
    public void checkAdd__Reference() throws IOException, InterruptedException {
        logger= extent.createTest("17: Verify if User can add Reference");
        setUp_FrontOffice_pages setUp_frontOffice_pages=new setUp_FrontOffice_pages(driver,logger);
        setUp_frontOffice_pages.openNewTab();
        setUp_frontOffice_pages.adding_Reference();
    }

    @Test (priority = 18)
    public void Edit_added_listing_Reference() throws IOException, InterruptedException {
        logger= extent.createTest("18: Verify if User can Edit the Reference");
        setUp_FrontOffice_pages setUp_frontOffice_pages=new setUp_FrontOffice_pages(driver,logger);
        setUp_frontOffice_pages.openNewTab();
        setUp_frontOffice_pages.edit_addListing_Reference();
    }

    @Test (priority = 19)
    public void verify_fromDB_Reference() throws InterruptedException, SQLException {
        logger= extent.createTest("19: Verifying Reference Data from the Database");
        setUp_FrontOffice_pages setUp_frontOffice_pages=new setUp_FrontOffice_pages(driver,logger);
        setUp_frontOffice_pages.openNewTab();
        setUp_frontOffice_pages.verify_fromDB_reference();
    }

    @Test (priority = 20)
    public void Delete_listRecord_Reference() throws IOException, InterruptedException {
        logger= extent.createTest("20: Verify if User can Delete Reference");
        setUp_FrontOffice_pages setUp_frontOffice_pages=new setUp_FrontOffice_pages(driver,logger);
        setUp_frontOffice_pages.openNewTab();
        setUp_frontOffice_pages.delete_Record_Reference();
    }
}