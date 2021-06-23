package AEDU.Testcases;

import AEDU.Pages.AdmissionEnquiry;
import AEDU.Pages.StudentInformation;
import org.testng.annotations.Test;

import java.io.IOException;
import java.sql.SQLException;

public class Admission_Enquiry extends Basecase{
    //Test all active Admission Enquiry List
    @Test (priority = 1)
    public void listActiveAdmissionEnquiry() throws IOException, InterruptedException, SQLException {
        logger = extent.createTest("Test all active Admission Enquiry List matches with database");
//        StudentInformation SI = new StudentInformation(driver, logger);
//        SI.loginWithCredentials2("addwebsolution@gmail.com", "addweb123");
        AdmissionEnquiry admissionEnquiry = new AdmissionEnquiry(driver, logger);
        admissionEnquiry.openNewTab();
        admissionEnquiry.ListofActiveAdmissionEnquiry();
    }

    //Match the inactive list admission enquiry with database
    @Test (priority = 2)
    public void listInactiveAdmissionEnquiry() throws IOException, InterruptedException, SQLException {
        logger = extent.createTest("Test inactive Admission Enquiry List matches with database");
//        StudentInformation SI = new StudentInformation(driver, logger);
//        SI.loginWithCredentials2("addwebsolution@gmail.com", "addweb123");
        AdmissionEnquiry admissionEnquiry = new AdmissionEnquiry(driver, logger);
        admissionEnquiry.openNewTab();
        admissionEnquiry.ListofInactiveAdmissionEnquiry();
    }

    @Test (priority = 3)
    public void listAllAdmissionEnquiry() throws IOException, InterruptedException, SQLException {
        logger = extent.createTest("Test all Admission Enquiry List matches with database");
//        StudentInformation SI = new StudentInformation(driver, logger);
//        SI.loginWithCredentials2("addwebsolution@gmail.com", "addweb123");
        AdmissionEnquiry admissionEnquiry = new AdmissionEnquiry(driver, logger);
        admissionEnquiry.openNewTab();
        admissionEnquiry.ListofAllAdmissionEnquiry();
    }

    @Test (priority = 4)
    public void listbySourceAdmissionEnquiry() throws IOException, InterruptedException, SQLException {
        logger = extent.createTest("Test if selected source Admission Enquiry List matches with database");
//        StudentInformation SI = new StudentInformation(driver, logger);
//        SI.loginWithCredentials2("addwebsolution@gmail.com", "addweb123");
        AdmissionEnquiry admissionEnquiry = new AdmissionEnquiry(driver, logger);
        admissionEnquiry.openNewTab();
        admissionEnquiry.ListSourceAdmissionEnquiry();
    }

    @Test (priority = 5)
    public void listbySourceStatusAdmissionEnquiry() throws IOException, InterruptedException, SQLException {
        logger = extent.createTest("Test if selected source and status Admission Enquiry List matches with database");
//        StudentInformation SI = new StudentInformation(driver, logger);
//        SI.loginWithCredentials2("addwebsolution@gmail.com", "addweb123");
        AdmissionEnquiry admissionEnquiry = new AdmissionEnquiry(driver, logger);
        admissionEnquiry.openNewTab();
        admissionEnquiry.ListSourceStatusAdmissionEnquiry();
    }

    @Test (priority = 6)
    public void listbyEnquiryAdmissionEnquiry() throws IOException, InterruptedException, SQLException {
        logger = extent.createTest("Test if selected enquiry date Admission Enquiry List matches with database");
//        StudentInformation SI = new StudentInformation(driver, logger);
//        SI.loginWithCredentials2("addwebsolution@gmail.com", "addweb123");
        AdmissionEnquiry admissionEnquiry = new AdmissionEnquiry(driver, logger);
        admissionEnquiry.openNewTab();
        admissionEnquiry.ListEnquiryAdmissionEnquiry();
    }

    @Test (priority = 7)
    public void listbyEnquirySourceStatusAdmissionEnquiry() throws IOException, InterruptedException, SQLException {
        logger = extent.createTest("Test if selected source, status and enquiry date Admission Enquiry List matches with database");
//        StudentInformation SI = new StudentInformation(driver, logger);
//        SI.loginWithCredentials2("addwebsolution@gmail.com", "addweb123");
        AdmissionEnquiry admissionEnquiry = new AdmissionEnquiry(driver, logger);
        admissionEnquiry.openNewTab();
        admissionEnquiry.ListEnquirySourceStatusAdmissionEnquiry();
    }

    @Test (priority = 8)
    public void noDataBehavior() throws IOException, InterruptedException, SQLException {
        logger = extent.createTest("Test if the no data behavior matches with database");
//        StudentInformation SI = new StudentInformation(driver, logger);
//        SI.loginWithCredentials2("addwebsolution@gmail.com", "addweb123");
        AdmissionEnquiry admissionEnquiry = new AdmissionEnquiry(driver, logger);
        admissionEnquiry.openNewTab();
        admissionEnquiry.NoDataBehavior();
    }

    @Test (priority = 9)
    public void nameFieldRequired() throws IOException, InterruptedException, SQLException {
        logger = extent.createTest("Test if name field validation on adding Admission Enquiry matches with expected value");
//        StudentInformation SI = new StudentInformation(driver, logger);
//        SI.loginWithCredentials2("addwebsolution@gmail.com", "addweb123");
        AdmissionEnquiry admissionEnquiry = new AdmissionEnquiry(driver, logger);
        admissionEnquiry.openNewTab();
        admissionEnquiry.NameFieldRequired();
    }

    @Test (priority = 10)
    public void phoneFieldRequired() throws IOException, InterruptedException, SQLException {
        logger = extent.createTest("Test if phone field validation on adding Admission Enquiry matches with expected value");
//        StudentInformation SI = new StudentInformation(driver, logger);
//        SI.loginWithCredentials2("addwebsolution@gmail.com", "addweb123");
        AdmissionEnquiry admissionEnquiry = new AdmissionEnquiry(driver, logger);
        admissionEnquiry.openNewTab();
        admissionEnquiry.PhoneFieldRequired();
    }

    @Test (priority = 11)
    public void sourceFieldRequired() throws IOException, InterruptedException, SQLException {
        logger = extent.createTest("Test if source field validation on adding Admission Enquiry matches with expected value");
//        StudentInformation SI = new StudentInformation(driver, logger);
//        SI.loginWithCredentials2("addwebsolution@gmail.com", "addweb123");
        AdmissionEnquiry admissionEnquiry = new AdmissionEnquiry(driver, logger);
        admissionEnquiry.openNewTab();
        admissionEnquiry.SourceFieldRequired();
    }

    @Test (priority = 12)
    public void addAdmissionEnquiry() throws IOException, InterruptedException, SQLException {
        logger = extent.createTest("Test if new Admission Enquiry is added and compare to database");
//        StudentInformation SI = new StudentInformation(driver, logger);
//        SI.loginWithCredentials2("addwebsolution@gmail.com", "addweb123");
        AdmissionEnquiry admissionEnquiry = new AdmissionEnquiry(driver, logger);
        admissionEnquiry.openNewTab();
        admissionEnquiry.AddAdmissionEnquiry();
    }

   //    Test if new  Admission Enquiry is edited and compare to database
    @Test (priority = 13)
    public void editAdmissionEnquiry() throws IOException, InterruptedException, SQLException {
        logger = extent.createTest("Test if new Admission Enquiry is edited and is present in database");
//        StudentInformation SI = new StudentInformation(driver, logger);
//        SI.loginWithCredentials2("addwebsolution@gmail.com", "addweb123");
        AdmissionEnquiry admissionEnquiry = new AdmissionEnquiry(driver, logger);
        admissionEnquiry.openNewTab();
        admissionEnquiry.EditAdmissionEnquiry();
    }

//    Test if follow up enquiry is updated and matches with database and front end
    @Test (priority = 14)
    public void editFollowUpAdmissionEnquiry() throws IOException, InterruptedException, SQLException {
        logger = extent.createTest("Test if follow up enquiry is updated  and is present in database");
//        StudentInformation SI = new StudentInformation(driver, logger);
//        SI.loginWithCredentials2("addwebsolution@gmail.com", "addweb123");
        AdmissionEnquiry admissionEnquiry = new AdmissionEnquiry(driver, logger);
        admissionEnquiry.openNewTab();
        admissionEnquiry.EditFollowUpDateAdmissionEnquiry();
    }

//    Test if record is deleted and is not present in database
    @Test (priority = 15)
    public void deleteAdmissionEnquiry() throws IOException, InterruptedException, SQLException {
        logger = extent.createTest("Test if record is deleted and is not present in database");
//        StudentInformation SI = new StudentInformation(driver, logger);
//        SI.loginWithCredentials2("addwebsolution@gmail.com", "addweb123");
        AdmissionEnquiry admissionEnquiry = new AdmissionEnquiry(driver, logger);
        admissionEnquiry.openNewTab();
        admissionEnquiry.DeleteAdmissionEnquiry();
    }
}
