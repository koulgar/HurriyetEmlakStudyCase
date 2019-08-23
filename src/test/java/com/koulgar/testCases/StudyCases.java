package com.koulgar.testCases;

import com.koulgar.pages.HomePage;
import com.koulgar.pages.SearchResultsPage;
import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
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
    private Actions actions;

    HomePage homePage;
    SearchResultsPage searchResultsPage;

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

    @Test(priority = 1)
    public void testCase1() throws InterruptedException {
        //Create "HomePage" Objects
        homePage = new HomePage(driver);

        //Make a search on home page
        homePage.searchForAdvertizement("NG Residence");

        //Create "SearchResultsPage" Objects
        searchResultsPage = new SearchResultsPage(driver);

        //Select an advertizement that contains "NG"
        searchResultsPage.selectAdvertizement();

    }

    @AfterTest
    public void quit() {
        driver.quit();
    }
}
