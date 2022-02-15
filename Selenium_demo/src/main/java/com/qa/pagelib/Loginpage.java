package com.qa.pagelib;

import com.qa.basetestpackage.Basetest;

import org.apache.log4j.lf5.util.LogFileParser;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class Loginpage extends Basetest {

    @FindBy(xpath = "//*[@name='uid']")
    @CacheLookup
    WebElement uid;

    @FindBy(xpath = "//*[@name='password']")
    @CacheLookup
    WebElement password;

    @FindBy(xpath = "//*[@name='btnLogin']")
    @CacheLookup
    WebElement loginbtn;

    String psp=p.getProperty("userid");

    public Loginpage() {
        PageFactory.initElements(driver, this);
    }

    public Homepage clickonloginbtn() {
        uid.sendKeys(p.getProperty("userid"));
        password.sendKeys(p.getProperty("password"));
        loginbtn.click();
        return new Homepage();

    }





    public void verifyloginthroughdatadrivenapproach(String userid,String userpassword){
        uid.sendKeys(userid);
        password.sendKeys(userpassword);
        loginbtn.click();
    
    }

    
}
