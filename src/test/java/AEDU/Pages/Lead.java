package AEDU.Pages;

import AEDU.Actions.ActionClass;
import AEDU.Actions.VerificationClass;
import AEDU.constants.CommonVar;
import com.aventstack.extentreports.ExtentTest;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

import java.io.IOException;

public class Lead {

    WebDriver driver;
    ExtentTest test;

    @FindBy(how = How.XPATH, using = "//*[@id=\"form-username\"]")
    public WebElement txtBox_login_username;

    @FindBy(how = How.XPATH, using = "//*[@id=\"form-password\"]")
    public WebElement txtBox_login_password;

    @FindBy(how = How.XPATH, using = "/html/body/div[1]/div/div/div[2]/div/div/div[2]/form[1]/button")
    public WebElement btnLogin;

    @FindBy(how = How.XPATH, using = "/html/body/div[1]/div/section/div[1]/div/h3")
    public WebElement element_after_login;

    @FindBy(how = How.CSS, using = "bg-img-admin > .right-side")
    public WebElement btnAdmin;

    @FindBy(how = How.XPATH, using = "//*[@id=\"sibe-box\"]/ul[2]/li[6]/a")
    public WebElement btnFrontOffice;

    @FindBy(how = How.XPATH, using = "//*[@id=\"sibe-box\"]/ul[2]/li[6]/ul/li[7]/a")
    public WebElement btnSetUpFrontOffice;

    @FindBy(how = How.XPATH, using = "/html/body/div[1]/div/section[1]/h1")
    public WebElement txtFrontOffice;

    @FindBy(how = How.XPATH, using = "/html/body/div[1]/div[1]/section[1]/h1")
    public WebElement lblReception;

    @FindBy(how = How.XPATH, using = "//*[@id=\"sibe-box\"]/ul[2]/li[6]/ul/li[8]/a")
    public WebElement btnReceptionist;

    @FindBy(how = How.XPATH, using = "//*[@id=\"sibe-box\"]/ul[2]/li[6]/ul/li[3]/a")
    public WebElement btnPhoneCall;

    @FindBy(how = How.XPATH, using = "/html/body/div[1]/div[1]/section[1]/h1")
    public WebElement lblPhoneCalllog;

    @FindBy(how = How.XPATH, using = "//*[@id=\"sibe-box\"]/ul[2]/li[3]/a")
    public WebElement btnAcademics;

   @FindBy(how = How.XPATH, using = "//*[@id=\"sibe-box\"]/ul[2]/li[3]/ul/li[5]/a")
    public WebElement btnClass;

    @FindBy(how = How.XPATH,using = "//*[@id=\"sibe-box\"]/ul[2]/li[3]/ul/li[6]/a")
    public WebElement btnSections;

    @FindBy(how = How.XPATH, using = "//*[@id=\"sibe-box\"]/ul[2]/li[11]/a")
    public WebElement btnExamination;

    public Lead(WebDriver driver, ExtentTest test) {
        this.driver = driver;
        this.test = test;
        PageFactory.initElements(driver, this);
    }

    public void login() throws IOException, InterruptedException {

        ActionClass action_class = new ActionClass(driver, test);
        VerificationClass verification_class = new VerificationClass(driver, test);
        CommonVar commonVar = new CommonVar();
        action_class.setValueinTextbox(txtBox_login_username, commonVar.uname);
        action_class.setValueinTextbox(txtBox_login_password, commonVar.pass);
        action_class.clickOnObject(btnLogin);
        verification_class.verifyTextPresent(element_after_login, "Dashboard statistics and more");
        Thread.sleep(2000);
        action_class.captureScreen("Successfull LOGIN");
    }

    public void redirect_to_FrontOffice() throws IOException, InterruptedException {

        ActionClass action_class = new ActionClass(driver, test);
        VerificationClass verification_class = new VerificationClass(driver, test);
        action_class.clickOnObject(btnFrontOffice);
        Thread.sleep(2000);
        action_class.clickOnObject(btnSetUpFrontOffice);
        verification_class.verifyTextPresent(txtFrontOffice, "Front Office");
        Thread.sleep(2000);
        action_class.captureScreen("Redirected to Front Office");

    }

    public void redirect_to_Reception() throws InterruptedException, IOException {

        ActionClass action_class = new ActionClass(driver, test);
        VerificationClass verification_class = new VerificationClass(driver, test);
        action_class.clickOnObject(btnFrontOffice);
        Thread.sleep(2000);
        action_class.clickOnObject(btnReceptionist);
        verification_class.verifyTextPresent(lblReception, "Receptionist");
        action_class.captureScreen("Redirected_to_Reception");

    }

    public void redirect_to_phoneCall() throws IOException, InterruptedException {

        ActionClass action_class = new ActionClass(driver, test);
        VerificationClass verification_class = new VerificationClass(driver, test);
        action_class.clickOnObject(btnFrontOffice);
        Thread.sleep(1000);
        action_class.clickOnObject(btnPhoneCall);
        verification_class.verifyTextPresent(lblPhoneCalllog, "Phone Call Log");
        Thread.sleep(2000);
        action_class.captureScreen("Redirected to Phone call Log");
    }

    public void redirect_to_Class() throws InterruptedException {
        ActionClass actionClass = new ActionClass(driver, test);
        VerificationClass verificationClass=new VerificationClass(driver,test);
        actionClass.clickOnObject(btnAcademics);
        Thread.sleep(2000);
        actionClass.clickOnObject(btnClass);
        Thread.sleep(3000);
    }

    public void redirection_to_Section() throws InterruptedException {
        ActionClass actionClass = new ActionClass(driver, test);
        actionClass.clickOnObject(btnAcademics);
        Thread.sleep(2000);
        actionClass.clickOnObject(btnSections);
        Thread.sleep(3000);
    }
    public void redirect_to_Examination() throws InterruptedException {
        ActionClass actionClass = new ActionClass(driver, test);
        actionClass.clickOnObject(btnExamination);
        Thread.sleep(2000);
    }
}