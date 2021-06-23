package AEDU.Pages;

import AEDU.constants.CommonVar;
import AEDU.Actions.VerificationClass;
import AEDU.Actions.ActionClass;
import com.aventstack.extentreports.ExtentTest;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

import java.io.IOException;

public class StudentInformation {
    WebDriver driver;
    ExtentTest extentTest;

    @FindBy(how = How.XPATH, using = "/html/body/div[1]/div/div/div[2]/div/div/div[2]/form[1]/button")
    private WebElement btn_submit;

    @FindBy(
            how = How.XPATH,
            using = "/html/body/div[1]/div/div/div[2]/div/div/div[2]/form[1]/div[1]/span/p\n"
    )
    private WebElement Vldusername;
    @FindBy(
            how = How.XPATH,
            using = "/html/body/div[1]/div/div/div[2]/div/div/div[2]/form[1]/div[2]/span/p\n"


    )
    private WebElement vldpassword;

    @FindBy(
            how = How.ID,
            using = "form-username"
    )
    private WebElement username;

    @FindBy(
            how = How.XPATH,
            using = "/html/body/div[1]/div/div/div[2]/div/div/div[2]/div"
    )
    private WebElement Msginvalidforusername;

    @FindBy(
            how = How.ID,
            using = "form-password"
    )
    private WebElement Fieldpassword;

    @FindBy(
            how = How.XPATH,
            using = "/html/body/div[1]/header/div[1]/nav/div[2]/div/div[1]/ul/li/a\n"
    )
    private WebElement NEXTPAGETITLE;
    @FindBy(
            how = How.XPATH,
            using = "//*[@id=\"form-password\"]"
    )
    private WebElement PASSFEILD;

    public StudentInformation(WebDriver driver,ExtentTest test) {

        this.driver = driver;
        this.extentTest = test;
        PageFactory.initElements(driver, this);

    }

    public void openNewTab() throws InterruptedException {
        ActionClass actionClass=new ActionClass(driver, extentTest);
        Thread.sleep(2000);
        driver.getCurrentUrl();
        JavascriptExecutor jsetaskscore1 = (JavascriptExecutor) driver;
        jsetaskscore1.executeScript("scrollBy(0, -300)");
        Thread.sleep(2000);
        for(String childTab : driver.getWindowHandles()){
            driver.switchTo().window(childTab);
        }
        Thread.sleep(1000);
    }
    public void StudentInformation() throws InterruptedException, IOException {
        Actions actions = new Actions(this.driver);
        ActionClass actionClass = new ActionClass(this.driver,extentTest);
        System.out.println("hello");
        actionClass.clickOnObject(btn_submit);
        VerificationClass verifyClass = new VerificationClass(this.driver,extentTest);
        verifyClass.verifyTextPresent(this.Vldusername, "The Username field is required." );
        verifyClass.verifyTextPresent(this.vldpassword, "The Password field is required." );
        ActionClass actionClass2=new ActionClass(driver, extentTest);
        actionClass2.captureScreen("StudentInformation");
    }
    public void loginWithCredentials(String username, String password) throws InterruptedException, IOException {
        ActionClass actionclass = new ActionClass(this.driver,extentTest);
        this.username.click();
        Thread.sleep(1000);
        this.username.sendKeys(username);
        this.Fieldpassword.click();
        actionclass.setValueinTextbox(this.Fieldpassword, password);
        actionclass.clickOnObject(this.btn_submit);
        VerificationClass verifyClass = new VerificationClass(this.driver,extentTest);
        verifyClass.verifyTextPresent(this.Msginvalidforusername, "Invalid Username or Password");
        System.out.println("entered invalid credentials");
        ActionClass actionclass2 = new ActionClass(this.driver,extentTest);
        actionclass2.captureScreen("loginWithCredentials1");

    }
    public void loginWithCredentials1(String username1, String password2) throws InterruptedException, IOException {
        ActionClass actionclass = new ActionClass(this.driver,extentTest);
        this.username.click();
        Thread.sleep(1000);
        this.username.sendKeys(username1);
        this.Fieldpassword.click();
        this.Fieldpassword.sendKeys(password2);
        actionclass.clickOnObject(this.btn_submit);
        VerificationClass verifyClass = new VerificationClass(this.driver,extentTest);
        verifyClass.verifyTextPresent(this.Msginvalidforusername, "Invalid Username or Password");
        System.out.println("entered invalid credentials");
        ActionClass actionclass2 = new ActionClass(this.driver,extentTest);
        actionclass2.captureScreen("loginWithCredentials1");
    }
    public void loginWithCredentials2(String username2, String password3) throws InterruptedException, IOException {
        ActionClass actionclass = new ActionClass(this.driver,extentTest);
        this.username.click();
        Thread.sleep(1000);
        actionclass.setValueinTextbox(this.username, username2);
        this.Fieldpassword.click();
        actionclass.setValueinTextbox(this.Fieldpassword, password3);
        actionclass.clickOnObject(this.btn_submit);
        VerificationClass verifyClass = new VerificationClass(this.driver,extentTest);
        verifyClass.verifyTextPresent(this.NEXTPAGETITLE, "Admin");
        ActionClass actionClass=new ActionClass(driver, extentTest);
        actionClass.captureScreen("loginWithCredentials1");
    }
}