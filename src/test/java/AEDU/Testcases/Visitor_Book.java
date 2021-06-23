package AEDU.Testcases;
import AEDU.Pages.StudentInformation;
import AEDU.Pages.VisitorBook;
import org.testng.annotations.Test;

import java.io.IOException;
import java.sql.SQLException;
public class Visitor_Book extends Basecase{

    @Test (priority = 1)
    public void checkValidations() throws IOException, InterruptedException, IOException {
        logger = extent.createTest("Check VisitorBook validation");
//        StudentInformation SI = new StudentInformation(driver,logger);
//        SI.loginWithCredentials2("addwebsolution@gmail.com","addweb123");
        VisitorBook Add= new VisitorBook(driver,logger);
        Add.openNewTab();
        VisitorBook Check =new VisitorBook(driver,logger);
        Check.CheckValidation();
    }

    @Test (priority = 2)
    public void addValidData() throws IOException, InterruptedException, SQLException {
        logger = extent.createTest("Check VisitorBook with enter valid data");
//        StudentInformation SI = new StudentInformation(driver,logger);
//        SI.loginWithCredentials2("addwebsolution@gmail.com","addweb123");
        VisitorBook Add= new VisitorBook(driver,logger);
        Add.openNewTab();
        Add.AddValidData("Aanal","7845874587","25140","5");
    }

    @Test (priority = 3)
    public void editData() throws IOException, InterruptedException, SQLException {
        logger = extent.createTest("Check edit functionality for visitorbook ");
//        StudentInformation SI = new StudentInformation(driver,logger);
//        SI.loginWithCredentials2("addwebsolution@gmail.com","addweb123");
        VisitorBook edit= new VisitorBook(driver,logger);
        edit.openNewTab();
        edit.EditData("Riddhi","4578541254","7854","2","78","Riddhi Dave");
    }

    @Test (priority = 4)
    public void Delete()throws IOException,InterruptedException{
        logger = extent.createTest("Delete Visitor entry ");
//        StudentInformation SI = new StudentInformation(driver,logger);
//        SI.loginWithCredentials2("addwebsolution@gmail.com","addweb123");
        VisitorBook  Deletes= new VisitorBook(driver,logger);
        Deletes.openNewTab();
        Deletes.Delete();
    }
}
