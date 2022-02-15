package com.qa.basetestpackage;

//import java.io.File;
import java.io.FileInputStream;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import com.qa.testutil.WebEventListener;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.openqa.selenium.support.events.WebDriverEventListener;

public class Basetest {
    public static Properties p;
    public static WebDriver driver;
    public static EventFiringWebDriver e;
    public static WebEventListener eventlistenervar;
    public Basetest(){
        try{
            FileInputStream f = new FileInputStream("src/main/java/com/qa/properties/config.properties");
            p = new Properties();
            p.load(f);
        }catch(Exception e){
            e.printStackTrace();
        }
       
    }

    public void initialization(){
        if(p.getProperty("browser").equals("chrome")){
            System.setProperty("webdriver.chrome.driver", "/opt/homebrew/bin/chromedriver");
            driver=new ChromeDriver();
           
        }

        if(p.getProperty("browser").equals("firefox")){
            System.setProperty("webdriver.gecko.driver", "/opt/homebrew/bin/geckodriver");
            driver=new FirefoxDriver();
           
        }

        EventFiringWebDriver e = new EventFiringWebDriver(driver);
        eventlistenervar=new WebEventListener();
        e.register(eventlistenervar);
        driver=e;
         

        driver.get(p.getProperty("url"));
        driver.manage().window().maximize();
        driver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
        driver.manage().deleteAllCookies();
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

        
    }
}
