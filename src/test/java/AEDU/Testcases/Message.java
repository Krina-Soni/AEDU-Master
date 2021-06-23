package AEDU.Testcases;

import AEDU.Pages.Messages;
import AEDU.Pages.StudentInformation;
import org.testng.annotations.Test;
import java.io.IOException;
import java.sql.SQLException;

public class Message  extends Basecase{
    @Test(priority = 1)
    public void Validations()throws IOException,InterruptedException{
        logger = extent.createTest("Check the Add Message validation are working or not.");
//        StudentInformation SI = new StudentInformation(driver, logger);
//        SI.loginWithCredentials2("addwebsolution@gmail.com", "addweb123");
        Messages Check = new Messages(driver,logger);
        Check.CheckAddMessageValidations();
    }
    @Test(priority = 2)
        public void Addmessages() throws IOException, InterruptedException, SQLException {
        logger = extent.createTest("Check Add-Message functionality is working or not");
//        StudentInformation SI = new StudentInformation(driver, logger);
//        SI.loginWithCredentials2("addwebsolution@gmail.com", "addweb123");
        Messages add = new Messages(driver,logger);
        add.AddMessage("Testing for message","This message for demo");
    }
    @Test(priority = 3)
    public void MatchLists()throws IOException,InterruptedException,SQLException{
        logger = extent.createTest("Check the Message list is match with the Database or not.");
//        StudentInformation SI = new StudentInformation(driver, logger);
//        SI.loginWithCredentials2("addwebsolution@gmail.com", "addweb123");
        Messages check = new Messages(driver,logger);
        check.MatchTheList();
    }

    @Test(priority = 4)
    public void Chatwindow()throws IOException,InterruptedException,SQLException{
        logger = extent.createTest("Check user is able to add message from the chatwindow or not.");
//        StudentInformation SI = new StudentInformation(driver, logger);
//        SI.loginWithCredentials2("addwebsolution@gmail.com", "addweb123");
        Messages add = new Messages(driver,logger);
        add.MatchChatwindowMessage("Testing for message","This message for demo","Window Testing");
    }
}
