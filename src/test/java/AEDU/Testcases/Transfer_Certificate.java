package AEDU.Testcases;
import AEDU.Pages.StudentInformation;
import AEDU.Pages.StudentSearch;
import AEDU.Pages.Studentcategory;
import AEDU.Pages.TransferCertificate;
import AEDU.Utilities.DatabaseFunctions;
import org.testng.annotations.Test;

import java.io.IOException;
import java.sql.SQLException;
public class Transfer_Certificate extends Basecase  {
    @Test
    public void TransferList() throws InterruptedException, IOException, SQLException{
        logger = extent.createTest("Verify that Default Keyword Search details are matched with Database or not");
        StudentInformation SI = new StudentInformation(driver,logger);
        SI.loginWithCredentials2("addwebsolution@gmail.com","addweb123");
        TransferCertificate TC = new TransferCertificate(driver,logger);
        TC.Checklist();
        driver.quit();
    }
}
