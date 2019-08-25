package com.koulgar.testCases;

import com.koulgar.pages.AdvertizementPage;
import com.koulgar.pages.FiltersSegment;
import com.koulgar.pages.HomePage;
import com.koulgar.pages.SearchResultsPage;
import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.regex.Pattern;

public class StudyCases {

    private WebDriver driver;
    private String nodeUrl;
    private Actions actions;

    HomePage homePage;
    SearchResultsPage searchResultsPage;
    AdvertizementPage advertizementPage;
    FiltersSegment filtersSegment;

    @Parameters({"resolution"})
    @BeforeTest
    public void setup(String resolution) throws MalformedURLException {
        nodeUrl = "http://localhost:4444//wd/hub";
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("browserName",   "chrome");
        capabilities.setCapability("platform","linux");
        if (resolution.equals("1920x1080")) {
            capabilities.setCapability("screenResolution", "1920x1080");
        } else if (resolution.equals("1366x768")) {
            capabilities.setCapability("screenResolution", "1366x768");
        }
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
        searchResultsPage.selectAdvert();

        //Create "AdvertizementPage" Objects
        advertizementPage = new AdvertizementPage(driver);

        //Reveal and get Phone Number
        String phoneNumber = advertizementPage.getPhoneNumber();
        Pattern pattern = Pattern.compile("(([\\+]90?)|([0]?))([ ]?)((\\([0-9]{3}\\))|([0-9]{3}))([ ]?)([0-9]{3})(\\s*[\\-]?)([0-9]{2})(\\s*[\\-]?)([0-9]{2})");

        //Check if phoneNumber variable has an actual phone number
        Assert.assertTrue(pattern.matcher(phoneNumber).matches());

        //Print found phone number
        System.out.println("Phone number of advert : " + phoneNumber);
    }

    @Test(priority = 2)
    public void testCase11() throws InterruptedException {
        //Navigate to main page
        driver.navigate().to("https://www.hurriyetemlak.com/");

        //Create "HomePage" Objects
        homePage = new HomePage(driver);

        //Search for any word
        homePage.searchForAdvertizement("Kiralık Konut");

        //Create "FiltersSegment" Objects
        filtersSegment = new FiltersSegment(driver);

        //Apply filters
        filtersSegment.applyFilters("İstanbul","150","4000");

        //Create "SearchResultsPage" Objects
        searchResultsPage = new SearchResultsPage(driver);

        //Check results if suitable with filters
        List<String> resultList = searchResultsPage.getAdvertInfo();

        Assert.assertTrue(resultList.get(0).contains("İstanbul"));
        Assert.assertEquals(resultList.get(2),"2+1");
        Assert.assertTrue(Integer.parseInt(resultList.get(3).split(" ")[0])<=150);
        Assert.assertTrue(Integer.parseInt(resultList.get(4).split(" ")[0].replace(".", "")) <= 4000);

    }

    @AfterTest
    public void quit() {
        driver.quit();
    }

}
