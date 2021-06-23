package AEDU.Testcases;

import org.testng.annotations.Test;
import AEDU.Pages.Lead;

import java.io.IOException;

public class login_Red extends Basecase {

    @Test
    public void Login() throws IOException, InterruptedException {
        logger= extent.createTest("To test if the user is able to login successfully");
        Lead lead=new Lead(driver,logger);
        lead.login();
        driver.quit();
    }

    @Test
    public void frontOffice() throws IOException, InterruptedException {

        logger= extent.createTest("To test user can redirect to SetUp Front Office successfully");
        Lead lead=new Lead(driver,logger);
        lead.login();
        lead.redirect_to_FrontOffice();
    }

    @Test
    public void receptionist() throws IOException, InterruptedException {

        logger=extent.createTest("To test user can redirect to Reception successfully");
        Lead lead=new Lead(driver,logger);
        lead.login();
        lead.redirect_to_Reception();
    }

    @Test
    public void phoneCall_logs() throws IOException, InterruptedException {
        logger= extent.createTest("To test for redirection to Phone call log directory");
        Lead lead=new Lead(driver,logger);
        lead.login();
        lead.redirect_to_phoneCall();
//        driver.quit();
    }
}