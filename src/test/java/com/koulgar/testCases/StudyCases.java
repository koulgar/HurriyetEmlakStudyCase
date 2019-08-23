package com.koulgar.testCases;

import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

public class StudyCases {

    private WebDriver driver;
    private String nodeUrl;

    @BeforeTest
    public void setup() throws MalformedURLException {
        nodeUrl = "http://localhost:4444//wd/hub";
        DesiredCapabilities capabilities = DesiredCapabilities.chrome();
        capabilities.setBrowserName("chrome");
        capabilities.setPlatform(Platform.LINUX);
        capabilities.setCapability("screenResolution", "1920x1080");
        driver = new RemoteWebDriver(new URL(nodeUrl), capabilities);
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        driver.get("https://www.hurriyetemlak.com/");
        driver.manage().window().maximize();
    }

    @Test
    public void simpleTest() {
        Assert.assertTrue(driver.getTitle().contains("Hürriyet Emlak"));
    }

    @AfterTest
    public void quit() {
        driver.quit();
    }
}
