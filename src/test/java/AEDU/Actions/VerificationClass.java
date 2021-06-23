package AEDU.Actions;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class VerificationClass {
    private static WebDriver driver;
    private static ExtentTest test;

    public VerificationClass(WebDriver driver, ExtentTest test) {
        this.driver = driver;
        this.test = test;
    }
    public void verifyTextPresent(WebElement element, String expectedText) {
        try {
            if (element.isDisplayed()) {
                String text = element.getText();
                if (text.equals(expectedText)) {
                    test.log(Status.PASS, "Expected value '" + expectedText + "' matches with actual value '" + text
                            + "' for the object : " + element.getAttribute("name"));
                    System.out.println("Expected value '" + expectedText + "' matches with actual value '" + text
                            + "' for the object : " + element.getAttribute("name"));
                } else {
                    test.log(Status.FAIL, "Expected value '" + expectedText + "' did not matche with actual value '"
                            + text + "' for the objectverifyLoggedInUser : " + element.getAttribute("name"));
                    System.out.println("Expected value '" + expectedText + "' did not matche with actual value '"
                            + text + "' for the object : " + element.getAttribute("name"));
                }
            } else {

                test.log(Status.FAIL, "Unable to find object : " + element.getAttribute("name"));
            }

        } catch (Exception e) {

            // TODO: handle exception
            test.log(Status.ERROR, "Unable to find the element on the page");
            System.out.println("Unable to find the element on the page");
        }
    }
    public void verifyElementNotPresent(WebElement element) {
        try {
            if (!element.isDisplayed()) {
                test.log(Status.PASS, "Element is not present for object : " + element.getAttribute("name"));
                System.out.println("Element is not present for object : " + element.getAttribute("name"));
            } else {
                test.log(Status.FAIL, "Element is  present for object : " + element.getAttribute("name"));
                System.out.println("Element is present for object : " + element.getAttribute("name"));
            }
        } catch (Exception var3) {
            System.out.println(var3.getStackTrace());
        }

    }
}
