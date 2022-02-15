package com.qa.testcases;

import java.util.Random;

import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.Markup;
import com.qa.basetestpackage.Basetest;
import com.qa.pagelib.Homepage;
import com.qa.pagelib.Loginpage;
import com.qa.report.reporting;
import com.qa.testutil.Test_Util;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class Logintest extends Basetest {

    Loginpage ls;
    Homepage hpg;

    reporting r = new reporting();

    @BeforeTest
    public void beforetest() {
        r.setupreport();
    }

    @BeforeMethod
    public void setup() {
        initialization();
        ls = new Loginpage();
        hpg = new Homepage();

    }

    @DataProvider
    public Object[][] validcredentialsdataprovider() throws Exception {
        Test_Util ts = new Test_Util();
        return ts.excelutil(0);
    }

    @DataProvider
    public Object[][] invalidcredentialsdataprovider() throws Exception {
        Test_Util ts = new Test_Util();
        return ts.excelutil(1);
    }

    // @Test(dataProvider="demodataprovider")
    // public void tc1(String name,String company){
    // System.out.println(name+" "+company);
    // }

    @Test(priority = 1)
    public void verifysuccessfullogin() throws Exception {
        r.createtest("verifysuccessfullogin");
        hpg = ls.clickonloginbtn();

        String actualtitle = hpg.gettitle();
        String expectedtitle = "Guru99 Bank Manager HomePage";
        Assert.assertEquals(actualtitle, expectedtitle);

    }

    @Test(priority = 2, dataProvider = "validcredentialsdataprovider")
    public void verifyloginwithvaliddata(String un, String pword) throws Exception {
        r.createtest("verifyloginwithvaliddata");
        ls.verifyloginthroughdatadrivenapproach(un, pword);
        Assert.assertEquals(hpg.gettitle(), "Guru99 Bank Manager HomePage");

    }

    @Test(priority = 3, dataProvider = "invalidcredentialsdataprovider")
    public void verifyloginwithinvaliddata(String un, String pword) throws Exception {
        r.createtest("verifyloginwithinvaliddata");
        ls.verifyloginthroughdatadrivenapproach(un, pword);
        Test_Util.forcedwait(5);
        String s = driver.switchTo().alert().getText();
        Assert.assertEquals(s, "User or Password is not valid");
        driver.switchTo().alert().accept();
        Test_Util.forcedwait(2);

    }

    @Test(priority = 4)
    public void verifymanageridonlogin(){
        r.createtest("verifymanageridonlogin");
        hpg=ls.clickonloginbtn();
        Assert.assertEquals(hpg.getmanageridmethod(), "Manger Id : mngr38353");
        
    }

    @AfterMethod
    public void tearDown(ITestResult testResult) throws Exception {
        if (testResult.getStatus() == ITestResult.SUCCESS) {
            r.test.log(Status.PASS, "Test case passed is" + testResult.getName());
        } 
        
        else if (testResult.getStatus() == ITestResult.FAILURE) {
            r.test.log(Status.FAIL, "Test case failed is" + testResult.getName());
            r.test.log(Status.FAIL, "TEST CASE FAILED BECAUSE OF" + testResult.getThrowable());

            String screenshotpath=Test_Util.screenshotutil(driver, testResult.getName());
            System.out.println(screenshotpath);
            r.test.addScreenCaptureFromPath(screenshotpath);
            System.out.println("Screenshot added to Extent report");
            
        }

        else if (testResult.getStatus() == ITestResult.SKIP) {
            r.test.log(Status.SKIP, "Test Case SKIPPED IS " + testResult.getName());
        }

        driver.quit();
    }

    @AfterTest
    public void aftertest() {
        r.reportteardown();
    }

}
