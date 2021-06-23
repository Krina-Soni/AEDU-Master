package AEDU.Testcases;

import AEDU.Pages.Lead;
import AEDU.Pages.Sections;
import org.testng.annotations.Test;
import java.io.IOException;
import java.sql.SQLException;

public class SectionTest extends Basecase {

    @Test(priority = 1)
    public void Check_Blank_Section() throws IOException, InterruptedException {
        logger = extent.createTest("1. Check for the Blank submission");
        Lead lead = new Lead(driver, logger);
        //lead.login();
        //lead.redirection_to_Section();
        Sections sections = new Sections(driver, logger);
        sections.checkBlank_Section();
    }

    @Test(priority = 2)
    public void Check_ValidEntry() throws IOException, InterruptedException {
        logger = extent.createTest("2. Check if User can make Valid Section Entry");
        Sections sections = new Sections(driver, logger);
        sections.openNewTab();
        sections.validSectionEntry();
    }


    @Test(priority = 3)
    public void VerifyAdd_FromListing() throws InterruptedException {
        logger = extent.createTest("3. Verifying Added Data from the Listing");
        Sections sections = new Sections(driver, logger);
        sections.openNewTab();
        sections.verify_AddFromListing();

    }


    @Test(priority = 4)
    public void VerifyingAddFrom_Database() throws InterruptedException, SQLException {
        logger = extent.createTest("4. Verifying Added Data from the Database");
        Sections sections = new Sections(driver, logger);
        sections.openNewTab();
        sections.verify_AddfromDB();

    }


    @Test(priority = 5)
    public void Update_Section() throws IOException, InterruptedException {
        logger = extent.createTest("5. Check if user can update the Section");
        Sections sections = new Sections(driver, logger);
        sections.openNewTab();
        sections.update_Section();
    }


    @Test(priority = 6)
    public void Verify_update_FromListing() throws InterruptedException {
        logger = extent.createTest("6. Verifying Updated Section from the Listing");
        Sections sections = new Sections(driver, logger);
        sections.openNewTab();
        sections.verify_UpdateFromListing();
    }

    @Test(priority = 7)
    public void VerifyUpdate_FromDB() throws InterruptedException, SQLException {
        logger = extent.createTest("7. Verifying Updated Section from the Database");
        Sections sections = new Sections(driver, logger);
        sections.openNewTab();
        sections.verify_UpdatefromDB();
    }

    @Test(priority = 8)
    public void Delete_Section() throws InterruptedException, IOException {
        logger=extent.createTest("8. Check if User can Delete the Section");
        Sections sections=new Sections(driver,logger);
        sections.openNewTab();
        sections.delete_section();
    }

    @Test(priority = 9)
    public void verify_DeletefromDB() throws InterruptedException, SQLException {
        logger=extent.createTest("9. Verifying Deleted Data from the Database");
        Sections sections=new Sections(driver,logger);
        sections.openNewTab();
        sections.verify_DeleteFromDB();
    }
}