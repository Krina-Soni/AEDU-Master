//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package AEDU.Actions;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class ActionClass {
    public static WebDriver driver;
    public static ExtentTest test;

    public ActionClass(WebDriver driver, ExtentTest test)
    {
        this.driver=driver;
        this.test=test;
    }

    public void clickOnObject(WebElement element)   {
        try {
            if(element.isDisplayed())
            {
                element.click();
                test.log(Status.INFO,"Sucessfully clicked on object");
                System.out.println("Sucessfully clicked on object");
                //element.getAttribute("name")
            }
            else
            {
                System.out.println("Element Not Found");
                test.log(Status.FAIL,"Element Not Found");
            }
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
            test.log(Status.FAIL,e.getMessage());
        }
    }

    public void setValueinTextbox( WebElement element, String value) {
        {
            try {
                if (element.isDisplayed()) {
                    element.clear();
                    element.sendKeys(value);
                    System.out.println("Succesfully entered '" + value + "' in object");
                    test.log(Status.PASS, "Succesfully entered '" + value + "' in object :");
                } else {
                    System.out.println("Element Not Found to Enter Anything");
                    test.log(Status.FAIL, "Element Not Found to Enter Anything");

                }
            } catch (Exception e) {
                System.out.println(e.getMessage());
                test.log(Status.FAIL, e.getMessage());
            }
        }
    }
    public void CompareList(ArrayList<Integer> listNames, ArrayList<Integer> listNames1)   {
        try {
            if(listNames.equals(listNames1)==true)
            {
                test.log(Status.INFO,"Result Matched with DB");
                System.out.println("Result Matched with DB");

            }
            else
            {
                System.out.println("Result Doesn't match with DB");
                test.log(Status.INFO,"Result Doesn't match with DB");
            }
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
            test.log(Status.FAIL,e.getMessage());
        }
    }
    public void CompareListandstring(String s, String s1)
    {
        try {
            if(s.equals(s1)==true)
            {
                test.log(Status.PASS, "View opened for valid record " + s + " And Match with View record for " + s1);
                System.out.println("View opened for valid record " + s + " And Match with View record for " + s1);

            }
            else
            {
                System.out.println("View record for " + s + " is not correct");
                test.log(Status.INFO, "View record for " + s + " is not correct");
            }
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
            test.log(Status.FAIL,e.getMessage());
        }
    }

    public void CompareStringList(ArrayList<String> listNames, ArrayList<String> listNames1)
    {
        try {
            if(listNames.equals(listNames1)==true)
            {
                test.log(Status.PASS,"Result Matched with DB");
                System.out.println("Result Matched with DB");

            }
            else
            {
                System.out.println("Result Doesn't match with DB");
                test.log(Status.INFO,"Result Doesn't match with DB");
            }
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
            test.log(Status.FAIL,e.getMessage());
        }
    }
    public void CompareEditedString(ArrayList<String> listNames, ArrayList<String> listNames1)
    {
        try {
            if(listNames.equals(listNames1)==false)
            {
                test.log(Status.PASS,"Record edited successfully and before and after database values does not match");
                System.out.println("Record edited successfully and before and after database values does not match");

            }
            else
            {
                System.out.println("Record edited successfully and before and after database values matches");
                test.log(Status.INFO,"Record did not edit successfully and before and after database values matches");
            }
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
            test.log(Status.FAIL,e.getMessage());
        }
    }

    public void setValueinPortalField(WebElement element) {

        try{
            if(element.isDisplayed()){
                element.click();
                element.sendKeys("Elance");
                element.sendKeys(Keys.ENTER);

                element.click();
                element.sendKeys("Freelancer");
                element.sendKeys(Keys.ENTER);
            }
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }

    public static void captureScreen(String testcaseName) throws IOException {
        DateFormat dateFormat = new SimpleDateFormat("yyyy_MM_dd_HH_MM_SS");
        Date date = new Date();
        String datetextName = dateFormat.format(date);
        String screenshotPath = System.getProperty("user.dir") + "/test-output/screenshot/" +testcaseName + "_"+datetextName + ".png" ;
        TakesScreenshot scrShot = ((TakesScreenshot) driver);
        File SrcFile = scrShot.getScreenshotAs(OutputType.FILE);
        File DestFile = new File(screenshotPath);
        FileUtils.copyFile(SrcFile, DestFile);
        test.addScreenCaptureFromPath(screenshotPath);
    }

    public void updateValueinTextbox( WebElement element, String value)    {
        try {
            if(element.isDisplayed())
            {
                element.click();
                element.clear();
                element.sendKeys(value);
                System.out.println("Succesfully entered '"+value+"' in object");
                test.log(Status.PASS,"Succesfully entered '"+value+"' in object :");
            }
            else
            {
                System.out.println("Unable to find object");
                test.log(Status.FAIL,"Unable to find object");
            }
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
            test.log(Status.FAIL,e.getMessage());
        }
    }

    public void setValueinTextbox_update( WebElement element, String value)  {
        try {
            if(element.isDisplayed())
            {
                element.click();
                element.clear();
                element.sendKeys(value);
                System.out.println("Succesfully entered '"+value+"' in object");
                test.log(Status.PASS,"Succesfully entered '"+value+"' in object :");
            }
            else
            {
                System.out.println("Unable to find object");
                test.log(Status.FAIL,"Unable to find object");
            }
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
            test.log(Status.FAIL,e.getMessage());
        }
    }

    public void captureScreenUsingRobot(String testcaseName) throws IOException, AWTException {
        DateFormat dateFormat = new SimpleDateFormat("yyyy_MM_dd_HH_MM_SS");
        Date date = new Date();
        String datetextName = dateFormat.format(date);
        String screenshotPath = System.getProperty("user.dir") + "/test-output/screenshot/" +testcaseName + "_"+datetextName + ".png" ;
        BufferedImage image = new Robot().createScreenCapture(new Rectangle(Toolkit.getDefaultToolkit().getScreenSize()));
        ImageIO.write(image, "png", new File(screenshotPath));
        test.addScreenCaptureFromPath(screenshotPath);
    }

}
