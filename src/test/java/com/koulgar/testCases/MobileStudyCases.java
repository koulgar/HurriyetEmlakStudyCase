package com.koulgar.testCases;

import com.koulgar.mobilePages.HomePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

public class MobileStudyCases {

    private WebDriver driver;
    private String nodeUrl;

    private HomePage homePage;

    @Parameters({"testName", "browserName", "platform", "resolution","options"})
    @BeforeTest
    public void setup(String testName, String browserName, String platform, String resolution, String options) throws MalformedURLException {
        nodeUrl = "http://localhost:4444//wd/hub";
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("browserName", browserName);
        capabilities.setCapability("platform", platform);
        capabilities.setCapability("screenResolution", resolution);
        capabilities.setCapability("name", testName);
        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.addArguments(options);
        capabilities.setCapability(ChromeOptions.CAPABILITY,chromeOptions);
        driver = new RemoteWebDriver(new URL(nodeUrl), capabilities);
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        driver.get("https://www.hurriyetemlak.com/");
        driver.manage().window().maximize();
    }

    @Test
    public void testCase1() {

        //Create "HomePage" Objects
        homePage = new HomePage(driver);

        //Click on search bar
        homePage.clickOnSearchBar();

    }

    @AfterTest
    public void quit() {
        driver.quit();
    }

}
