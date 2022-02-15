package com.qa.report;

import java.util.Random;

import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.HTMLReporter;
import com.relevantcodes.extentreports.LogStatus;
import com.aventstack.extentreports.model.Media;
import com.aventstack.extentreports.model.MediaType;

import org.testng.*;

import org.testng.ITestResult;

public class reporting {
    public ExtentHtmlReporter htmlreporter;
    public com.aventstack.extentreports.ExtentReports extent;
    public com.aventstack.extentreports.ExtentTest test;
    public Random r;

    

    public void setupreport(){
        r=new Random();
        htmlreporter = new ExtentHtmlReporter("src/main/java/com/qa/report/"+r.nextInt()+"myreport.html");
        htmlreporter.config().setDocumentTitle("Automation Report");
        htmlreporter.config().setReportName("Functional Report");
        //htmlreporter.config().setAutoCreateRelativePathMedia(true);
        extent=new com.aventstack.extentreports.ExtentReports();
        extent.attachReporter(htmlreporter);

    }


    public void createtest(String name){
        test= extent.createTest(name);
    }

    public void reportteardown(){
        extent.flush();
    }

}