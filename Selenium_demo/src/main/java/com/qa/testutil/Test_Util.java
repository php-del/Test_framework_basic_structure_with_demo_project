package com.qa.testutil;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.aventstack.extentreports.utils.FileUtil;
import com.qa.basetestpackage.Basetest;

import org.apache.commons.io.FileUtils;
// import org.apache.poi.hssf.usermodel.HSSFCell;
// import org.apache.poi.hssf.usermodel.HSSFRow;
// import org.apache.poi.hssf.usermodel.HSSFSheet;
// import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
//import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.decorators.WebDriverDecorator;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


public class Test_Util extends Basetest {

    static FileInputStream fis;
    static String pd;
    static int sheetnum;
    

    public Test_Util() {
        super();
        pd = p.getProperty("excelfilepath");

    }

    public Object[][] excelutil(int sheetnum) throws Exception {

        // FileInputStream fis= new FileInputStream(p.getProperty("excelfilepath"));

        fis = new FileInputStream(pd);

        XSSFWorkbook w = new XSSFWorkbook(fis);

        XSSFSheet sheet = w.getSheetAt(sheetnum);

        int rownum = sheet.getPhysicalNumberOfRows();
        System.out.println(rownum);
        XSSFRow row = sheet.getRow(0);
        int colnum = row.getLastCellNum();

        Object data[][] = new Object[rownum - 1][colnum];

        for (int i = 0; i < rownum - 1; i++) {
            row = sheet.getRow(i + 1);

            for (int j = 0; j < colnum; j++) {
                XSSFCell cell = row.getCell(j);

                data[i][j] = cell.getStringCellValue();
                // System.out.println(data[i][j]+" ");

            }
        }

        return data;

    }

    public static void forcedwait(int secs) {
        try {
            Thread.sleep(secs * 1000);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public static void explicitwait(WebElement element, int secs) {
        new WebDriverWait(driver, secs).until(ExpectedConditions.elementToBeClickable(element));
    }

    public static String screenshotutil(WebDriver driver, String screenshotname) throws IOException {
        String datename = new SimpleDateFormat("yyyyMMddhhmmss").format(new Date());

        TakesScreenshot ts=(TakesScreenshot)driver;
        File source = ts.getScreenshotAs(OutputType.FILE);
        String sdp=System.getProperty("user.dir");
        System.out.println(sdp);
        String destination=sdp+"/src/main/java/com/qa/report/Screenshots/"+screenshotname+datename+".png";

        

        File finaldestination=new File(destination);

        FileUtils.copyFile(source, finaldestination);
        return destination;


    }

    // public static void main(String[] args) throws Exception {

    // Test_Util ts=new Test_Util();
    // Object[][] data1= excelutil(0);
    // System.out.println();
    // System.out.println(data1);

    // }

}
