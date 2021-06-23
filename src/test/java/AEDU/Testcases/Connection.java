package AEDU.Testcases;

import AEDU.Utilities.DatabaseFunctions;
import org.testng.annotations.Test;

import java.io.IOException;
import java.sql.SQLException;

public class Connection extends Basecase{
    @Test
    public void Check_connection() throws InterruptedException, IOException, SQLException {
        logger = extent.createTest("Verify that Project has Database integration");
        DatabaseFunctions DBConnect = new DatabaseFunctions(logger);
        DBConnect.printUserNamePassword();
        driver.quit();
    }
}
