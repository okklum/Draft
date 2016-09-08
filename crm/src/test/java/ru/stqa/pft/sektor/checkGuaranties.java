package ru.stqa.pft.sektor;

import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import java.io.FileReader;
import java.util.Properties;
import java.util.concurrent.TimeUnit;
import java.io.File;

import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.*;

public class checkGuaranties {
    ChromeDriver wd;
    private Properties properties;

    public checkGuaranties() {
        properties = new Properties();
    }

    
    @BeforeMethod
    public void setUp() throws Exception {
        //properties from file "local.properties"
        String target = System.getProperty("target", "local");
        properties.load(new FileReader(new File(String.format("src/test/resources/%s.properties", target))));

        wd = new ChromeDriver();
        wd.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);
    }
    
    @Test
    public void checkGuaranties() {
        wd.get(properties.getProperty("web.baseUrl"));
        wd.findElement(By.id("login[email]")).click();
        wd.findElement(By.id("login[email]")).clear();
        wd.findElement(By.id("login[email]")).sendKeys(properties.getProperty("web.adminLogin"));

        wd.findElement(By.id("login[password]")).click();
        wd.findElement(By.id("login[password]")).clear();
        wd.findElement(By.id("login[password]")).sendKeys(properties.getProperty("web.adminPassword"));

        wd.findElement(By.linkText("Реестр БГ")).click();
        wd.findElement(By.xpath("//html")).getText().contains("Реестровый номер");
    }
    
    @AfterMethod
    public void tearDown() {
        wd.quit();
    }
    
    public static boolean isAlertPresent(FirefoxDriver wd) {
        try {
            wd.switchTo().alert();
            return true;
        } catch (NoAlertPresentException e) {
            return false;
        }
    }
}
