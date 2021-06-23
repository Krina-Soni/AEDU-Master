package AEDU.Pages;

import AEDU.Actions.VerificationClass;
import AEDU.Actions.ActionClass;
import com.aventstack.extentreports.ExtentTest;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import AEDU.Utilities.*;
import java.sql.*;
import java.util.HashMap;

import java.io.IOException;
import java.sql.Connection;
import java.util.List;

import static java.lang.Thread.sleep;

public class Studentcategory {
    WebDriver driver;
    ExtentTest extentTest;

    @FindBy(
            how = How.XPATH,
            using = "//*[@id=\"form-username\"]\n"
    )
    private WebElement addusername;

    @FindBy(
            how = How.XPATH,
            using = "//*[@id=\"form-password\"]\n"
    )
    private WebElement addpassword;
    @FindBy(
            how = How.XPATH,
            using = "/html/body/div[1]/aside/div/section/ul[2]/li[1]/a/span"
    )
    private WebElement sidemenustudentinfomenuclick;
    @FindBy(
            how = How.XPATH,
            using = "/html/body/div[1]/aside/div/section/ul[2]/li[1]/ul/li[3]/a"
    )
    private WebElement sidemenustudentcatogorymenuclick;

    @FindBy(
            how = How.XPATH,
            using = "//*[@id=\"category\"]"
    )
    private WebElement categoryfeild;
    @FindBy(
            how = How.XPATH,
            using = "/html/body/div[1]/div/div/div[2]/div/div/div[2]/form[1]/button"
    )
    private WebElement subtn;
    @FindBy(
            how = How.XPATH,
            using = "/html/body/div[1]/div[1]/section[2]/div/div[1]/div/form/div[2]/button"
    )
    private WebElement savebtn;
    @FindBy(
            how = How.XPATH,
            using = "/html/body/div[1]/div[1]/section[2]/div/div[1]/div/form/div[1]/div[1]"
    )
    private WebElement veryfysave;
    @FindBy(
            how = How.XPATH,
            using = "/html/body/div[1]/div[1]/section[2]/div/div[1]/div/form/div[1]/div/span/p"
    )
    private WebElement Errormsg;
    @FindBy(
            how = How.XPATH,
            using = "/html/body/div[1]/div[1]/section[2]/div/div[2]/div/div[2]/div[2]/div/table/tbody/tr/td[2]/a[1]"


    )
    private WebElement Editbtn;
    @FindBy(
            how = How.XPATH,
            using = "/html/body/div[1]/div[1]/section[2]/div/div[1]/div/div/h3"
    )
    private WebElement Edipage;
    @FindBy(
            how = How.XPATH,
            using = "/html/body/div[1]/div[1]/section[2]/div/div[1]/div/form/div[1]/div[1]"
    )
    private WebElement updatetext;
    @FindBy(
            how = How.XPATH,
            using = "/html/body/div[1]/div[1]/section[2]/div/div[2]/div/div[2]/div[2]/div/table/tbody/tr/td[2]/a[2]/i"
    )
    private WebElement Deletecategory;
    @FindBy(
            how = How.XPATH,
            using="/html/body/div[1]/aside/div/section/ul[2]/li[1]/ul/li[4]/a"
    )
    private WebElement promotestudent;

    public Studentcategory(WebDriver driver, ExtentTest test) {
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

    public void addstudnetcategory(String Catname, String catname1, String cat3) throws IOException, InterruptedException {

        ActionClass actionClass = new ActionClass(this.driver,extentTest);
//        actionClass.clickOnObject(this.sidemenustudentinfomenuclick);
        Thread.sleep(5000);
        actionClass.clickOnObject(this.sidemenustudentcatogorymenuclick);
        Thread.sleep(5000);
        actionClass.clickOnObject(this.categoryfeild);
        actionClass.setValueinTextbox(this.categoryfeild, Catname);
        actionClass.clickOnObject(this.savebtn);
        VerificationClass verifyClass = new VerificationClass(this.driver,extentTest);
        verifyClass.verifyTextPresent(this.veryfysave, "Category added successfully");
        System.out.println("Category added");
        actionClass.captureScreen("addstudnetcategory");
//            ActionClass.captureScreen("Successfully added category");
    }

    public void Blankvalidations() throws IOException, InterruptedException {
        ActionClass actionClass = new ActionClass(this.driver,extentTest);
        Thread.sleep(5000);
        actionClass.clickOnObject(this.sidemenustudentcatogorymenuclick);
        Thread.sleep(5000);
        actionClass.clickOnObject(this.categoryfeild);
        actionClass.clickOnObject(this.savebtn);
        VerificationClass verifyClass1 = new VerificationClass(this.driver,extentTest);
        verifyClass1.verifyTextPresent(this.Errormsg, "The Category field is required.");
//      ActionClass.captureScreen("Blank added  category validation");
        System.out.println("Not accepting blank value");
        actionClass.captureScreen("Blankvalidations");

    }

    public void validationforspacevalue(String Catname2) throws IOException, InterruptedException {
        ActionClass actionClass = new ActionClass(this.driver,extentTest);
        Thread.sleep(5000);
        actionClass.clickOnObject(this.sidemenustudentcatogorymenuclick);
        Thread.sleep(5000);
        actionClass.clickOnObject(this.categoryfeild);
        actionClass.setValueinTextbox(this.categoryfeild,Catname2);
        actionClass.clickOnObject((this.savebtn));
        VerificationClass verifyClass2 = new VerificationClass(this.driver,extentTest);
        verifyClass2.verifyTextPresent(this.Errormsg, "The Category field is required.");
//                ActionClass.captureScreen("value as space validation ");
        System.out.println("Not accepting value as space");
        actionClass.captureScreen("validationforspacevalue");
    }

    public void editcategory(String cat3) throws IOException, InterruptedException {
        ActionClass actionClass = new ActionClass(this.driver,extentTest);
        Thread.sleep(5000);
        actionClass.clickOnObject(this.sidemenustudentcatogorymenuclick);
        Thread.sleep(5000);
        actionClass.clickOnObject(this.Editbtn);
        actionClass.clickOnObject(this.categoryfeild);
        VerificationClass verifyClass3 = new VerificationClass(this.driver,extentTest);
        verifyClass3.verifyTextPresent(this.Edipage, "Edit Category");
        actionClass.setValueinTextbox(this.categoryfeild, cat3);
        actionClass.clickOnObject(this.savebtn);
        verifyClass3.verifyTextPresent(this.updatetext, "Category updated successfully");
//      ActionClass.captureScreen("updated category");
        actionClass.clickOnObject(this.Deletecategory);
        Alert alert = driver.switchTo().alert();
        alert.accept();
        List<WebElement> Category = driver.findElements(By.id("DataTables_Table_0_wrapper"));
        for (WebElement nameTitle : Category) {
            String PrintAuthAsc = nameTitle.getText();
            System.out.println("Category is " + PrintAuthAsc);
        }
        actionClass.captureScreen("editcategory");
    }
}



