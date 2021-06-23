package AEDU.Testcases;

import AEDU.Pages.ClassFile;
import AEDU.Pages.Lead;
import org.testng.annotations.Test;

import java.io.IOException;
import java.sql.SQLException;


public class ClassT extends Basecase {

    @Test(priority = 1)
    public void check_Blank_Class() throws IOException, InterruptedException {
        logger=extent.createTest("1: Check for Blank Save__Checking Mandatory fields");
        Lead lead=new Lead(driver,logger);
        ClassFile aClass=new ClassFile(driver,logger);
        //lead.login();
        //lead.redirect_to_Class();
        aClass.check_BlankCall();

    }

    @Test(priority = 2)
    public void check_ValidEntry() throws InterruptedException, IOException {
        logger=extent.createTest("2: Check if user can add a valid entry");
        ClassFile aclass=new ClassFile(driver,logger);
        Lead lead=new Lead(driver,logger);
        aclass.openNewTab();
        aclass.checkValidEntry();
    }

    @Test(priority = 3)
    public void check_EntryonListing_Class() throws InterruptedException, IOException {
        logger=extent.createTest("3: Check entry on listing Class on valid entry");
        ClassFile aclass=new ClassFile(driver,logger);
        aclass.openNewTab();
        aclass.checkListingonRecord_Class();
    }

    @Test(priority = 4)
    public void check_EntryonListing_Section() throws InterruptedException {
        logger=extent.createTest("4: Check entry on listing section on valid entry");
        ClassFile aclass=new ClassFile(driver,logger);
        aclass.openNewTab();
        aclass.checkListingonRecord_Section();
    }

    @Test(priority = 5)
    public void verify_FromDB() throws InterruptedException, SQLException {
        logger=extent.createTest("5: Verify the Class data from the Database");
        ClassFile aclass=new ClassFile(driver,logger);
        aclass.openNewTab();
        aclass.verify_Class_fromDB();
    }

    @Test(priority = 6)
    public void Verify_sectionFromDB() throws InterruptedException, SQLException {
        logger=extent.createTest("6: Verify the Section data from the Database");
        ClassFile aclass=new ClassFile(driver,logger);
        aclass.openNewTab();
        aclass.verify_Section_fromDB();
    }

    @Test(priority = 7)
    public void Edit_Class() throws InterruptedException, IOException {
        logger=extent.createTest("7: Edit/Update the Class Details");
        ClassFile aclass=new ClassFile(driver,logger);
        aclass.openNewTab();
        aclass.edit_fromListing();

    }
    @Test(priority = 8)
    public void Delete_fromListing() throws InterruptedException, IOException {
        logger=extent.createTest("8: Delete Class/Section From Listing");
        ClassFile aclass=new ClassFile(driver,logger);
        aclass.openNewTab();
        aclass.delete_fromListing();
    }

    @Test(priority = 9)
    public void Verify_deleteFromDB() throws InterruptedException, SQLException {
        logger=extent.createTest("9: Verify Delete From the Database");
        ClassFile aclass=new ClassFile(driver,logger);
        aclass.openNewTab();
        aclass.verify_Delete_fromDB();
    }
}