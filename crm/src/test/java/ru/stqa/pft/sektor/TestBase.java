package ru.stqa.pft.sektor;

import org.openqa.selenium.By;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import java.io.File;
import java.io.FileReader;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

/**
 * Created by alisa on 08.09.2016.
 */
public class TestBase {

  protected Properties properties;
  ChromeDriver wd;

  public TestBase() {
    properties = new Properties();
  }

  public static boolean isAlertPresent(FirefoxDriver wd) {
    try {
      wd.switchTo().alert();
      return true;
    } catch (NoAlertPresentException e) {
      return false;
    }
  }

  @BeforeMethod
  public void setUp() throws Exception {
    //properties from file "local.properties"
    String target = System.getProperty("target", "local");
    properties.load(new FileReader(new File(String.format("src/test/resources/%s.properties", target))));

    wd = new ChromeDriver();
    wd.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);

    wd.get(properties.getProperty("web.baseUrl"));
    login();
  }

  private void login() {
    wd.findElement(By.id("login[email]")).click();
    wd.findElement(By.id("login[email]")).clear();
    wd.findElement(By.id("login[email]")).sendKeys(properties.getProperty("web.adminLogin"));
    wd.findElement(By.id("login[password]")).click();
    wd.findElement(By.id("login[password]")).clear();
    wd.findElement(By.id("login[password]")).sendKeys(properties.getProperty("web.adminPassword"));
  }

  protected void checkGoTo() {
    wd.findElement(By.xpath("//html")).getText().contains("Реестровый номер");
  }

  protected void goToGuarantees() {
    wd.findElement(By.linkText("Реестр БГ")).click();
  }

  @AfterMethod
  public void tearDown() {
    wd.quit();
  }
}
