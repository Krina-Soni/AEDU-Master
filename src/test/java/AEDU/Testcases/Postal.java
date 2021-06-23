package AEDU.Testcases;
import AEDU.Pages.PostalDispatch;
import AEDU.Pages.StudentInformation;
import org.testng.annotations.Test;

import java.io.IOException;
import java.sql.SQLException;

public class Postal extends Basecase {

    @Test (priority = 1)
    public void CheckValidation()throws IOException, InterruptedException {
        logger = extent.createTest("Check add Postal validation");
//        StudentInformation SI = new StudentInformation(driver,logger);
//        SI.loginWithCredentials2("addwebsolution@gmail.com","addweb123");
        PostalDispatch check=new PostalDispatch(driver,logger);
        check.openNewTab();
        check.CheckvalidationforPostal();
    }

    @Test (priority = 2)
    public void AddDatainpostal() throws IOException, InterruptedException, SQLException {
        logger = extent.createTest("Check Add functionality for postal Dispatch");
        PostalDispatch add=new PostalDispatch(driver,logger);
        add.openNewTab();
        add.AddpostalwithValidDAta("addwebsolution@gmail.com","addweb123","riddhi dave","Dave riddhi3","8788789","maninager ahemedabad","test");
    }

    @Test (priority = 3)
    public void edit() throws IOException, InterruptedException, SQLException {
        logger = extent.createTest("Check Edit, delete and view operation for PostalDispatch");
        PostalDispatch Edit=new PostalDispatch(driver,logger);
        Edit.openNewTab();
        Edit.Edit("riddhi","test riddhi","test","457854","ahemedabad maninager","testing1234");
    }

    @Test (priority = 4)
    public void Views()throws IOException, InterruptedException, SQLException {
        logger = extent.createTest("Check view dispatch functionality");
        PostalDispatch View=new PostalDispatch(driver,logger);
        View.openNewTab();
        View.View();
    }

    @Test (priority = 5)
    public void Deletes()throws IOException, InterruptedException, SQLException {
        logger = extent.createTest("Check delete functionality for Postal");
        PostalDispatch Delete=new PostalDispatch(driver,logger);
        Delete.openNewTab();
        Delete.DeletePostal("test riddhi","riddhi 123","test delete","4578698545","maninager ahemedabad","Testing 123");
    }
}

