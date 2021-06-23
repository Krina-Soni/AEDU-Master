package AEDU.Testcases;
import AEDU.constants.CommonVar;
import org.junit.Before;
import org.testng.annotations.*;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
//import org.openqa.selenium.chrome.ChromeOptions;
import AEDU.Reports.ReportClass;

import java.sql.SQLException;

public class Basecase extends ReportClass {

    public static WebDriver driver;

    @BeforeTest
    public void initialize() {
        System.setProperty("Webdriver.chrome.driver", System.getProperty("user.dir") + "chromedriver");
        driver = new ChromeDriver();
        CommonVar constantVars = new CommonVar();
        driver.get(constantVars.url);
       // driver.manage().window().maximize();
    }
}