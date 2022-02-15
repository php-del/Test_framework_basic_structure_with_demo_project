package com.qa.pagelib;

import com.qa.basetestpackage.Basetest;

import org.openqa.selenium.By;

public class Homepage extends Basetest {

    public String gettitle() {
        String homepagetitle = driver.getTitle();
        return homepagetitle;
    }

    public String getmanageridmethod(){
        return driver.findElement(By.xpath("//*[contains(text(),'Manger')]")).getText();
         
     }

}
