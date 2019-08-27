package com.koulgar.testCases;

import com.koulgar.desktopPages.AdvertizementPage;
import com.koulgar.desktopPages.FiltersSegment;
import com.koulgar.desktopPages.HomePage;
import com.koulgar.desktopPages.SearchResultsPage;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
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

public class DesktopStudyCases {

    private WebDriver driver;
    private String nodeUrl;

    private HomePage homePage;
    private SearchResultsPage searchResultsPage;
    private AdvertizementPage advertizementPage;
    private FiltersSegment filtersSegment;

    @Parameters({"testName", "browserName", "platform", "resolution","options"})
    @BeforeTest
    public void setup(String testName, String browserName, String platform, String resolution, String options) throws MalformedURLException {
        nodeUrl = "http://localhost:4444//wd/hub";

        //Zalenium settings
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("browserName", browserName);
        capabilities.setCapability("platform", platform);
        capabilities.setCapability("screenResolution", resolution);
        capabilities.setCapability("name", testName);

        //User-agent settings
        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.addArguments(options);
        capabilities.setCapability(ChromeOptions.CAPABILITY,chromeOptions);

        driver = new RemoteWebDriver(new URL(nodeUrl), capabilities);
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

        //Manage cookies to get consistent pages
        driver.get("http://www.hurriyetemlak.com");
        if (driver.manage().getCookieNamed("desktop2019") != null) {
            driver.manage().deleteCookieNamed("desktop2019");
            Cookie cookie = new Cookie("desktop2019", "0");
            driver.manage().addCookie(cookie);
            driver.navigate().refresh();
        }
        driver.manage().window().maximize();
    }

    @Test(priority = 1)
    public void testCase1() {

        //Create "HomePage" Objects
        homePage = new HomePage(driver);

        //Make a search on home page
        homePage.searchForAdvertizement("NG Residence");

        //Create "SearchResultsPage" Objects
        searchResultsPage = new SearchResultsPage(driver);

        //Select an advertizement
        searchResultsPage.selectAdvert(5);

        //Create "AdvertizementPage" Objects
        advertizementPage = new AdvertizementPage(driver);

        //Reveal and get Phone Number
        List<String> phoneNumbers = advertizementPage.getPhoneNumber();
        Pattern pattern = Pattern.compile("(([\\+]90?)|([0]?))([ ]?)((\\([0-9]{3}\\))|([0-9]{3}))([ ]?)([0-9]{3})(\\s*[\\-]?)([0-9]{2})(\\s*[\\-]?)([0-9]{2})");

        //Check if phoneNumber variable has an actual phone number
        for (String phoneNumber : phoneNumbers) {
            Assert.assertTrue(pattern.matcher(phoneNumber).matches());

            //Print found phone number
            System.out.println("Phone number of advert : " + phoneNumber);
        }

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
        filtersSegment.applyFilters("İstanbul", "150", "4000");

        //Create "SearchResultsPage" Objects
        searchResultsPage = new SearchResultsPage(driver);

        //Check results if suitable with filters
        List<String> resultList = searchResultsPage.getAdvertInfo(5);

        Assert.assertTrue(resultList.get(0).contains("İstanbul"));
        Assert.assertEquals(resultList.get(2), "2+1");
        Assert.assertTrue(Integer.parseInt(resultList.get(3).split(" ")[0]) <= 150);
        Assert.assertTrue(Integer.parseInt(resultList.get(4).split(" ")[0].replace(".", "")) <= 4000);

    }

    @AfterTest
    public void quit() {
        driver.quit();
    }

}
