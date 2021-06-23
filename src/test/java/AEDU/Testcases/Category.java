package AEDU.Testcases;

import AEDU.Pages.StudentInformation;
import AEDU.Pages.Studentcategory;
import org.testng.annotations.Test;
import java.io.IOException;

public class Category extends Basecase {
    @Test (priority = 1)
    public void addcategory() throws InterruptedException, IOException {
        logger = extent.createTest("Check the add category functionality");
//        StudentInformation SI = new StudentInformation(driver, logger);
//        SI.loginWithCredentials2("addwebsolution@gmail.com", "addweb123");
        Studentcategory add = new Studentcategory(driver,logger);
        add.openNewTab();
        add.addstudnetcategory("testin234","","Testedit");
    }

    @Test (priority = 2)
    public void Blankdatavalidation()throws IOException,InterruptedException{
        logger = extent.createTest("Check with the blank validation");
        Studentcategory blank=new Studentcategory(driver,logger);
        blank.openNewTab();
        blank.Blankvalidations();
    }

    @Test (priority = 3)
    public void ValidationforspaceVAlues() throws InterruptedException, InterruptedException, IOException {
        logger = extent.createTest("Check with the value as space validation");
        Studentcategory Space=new Studentcategory(driver,logger);
        Space.openNewTab();
        Space.validationforspacevalue(" ");
    }

    @Test (priority = 4)
    public void Editcategorys() throws InterruptedException, InterruptedException, IOException {
        logger = extent.createTest("Check with the Edit category");
        Studentcategory edit=new Studentcategory(driver,logger);
        edit.openNewTab();
        edit.editcategory("Test");
    }
}