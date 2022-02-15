package com.qa.testcases;

import com.qa.basetestpackage.Basetest;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class Smoketest extends Basetest {
    @BeforeMethod
    public void setup(){
        initialization();
    }

@Test
public void tc1(){

}

@Test
public void tc2(){

}






    @AfterMethod
    public void tearDown(){
        driver.quit();
    }
}

