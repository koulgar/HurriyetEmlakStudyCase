package com.koulgar.testCases;

import com.koulgar.mobilePages.AdvertizementPage;
import com.koulgar.mobilePages.HomePage;
import com.koulgar.mobilePages.SearchPage;
import com.koulgar.mobilePages.SearchResultsPage;
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

public class MobileStudyCases {

    private WebDriver driver;
    private String nodeUrl;

    private HomePage homePage;
    private SearchPage searchPage;
    private SearchResultsPage searchResultsPage;
    private AdvertizementPage advertizementPage;

    @Parameters({"testName", "browserName", "platform", "resolution", "options"})
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
        capabilities.setCapability(ChromeOptions.CAPABILITY, chromeOptions);

        driver = new RemoteWebDriver(new URL(nodeUrl), capabilities);
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

        //Manage cookies to get consistent pages
        driver.get("http://www.hurriyetemlak.com");
        driver.manage().deleteCookieNamed("desktop2019");
        Cookie cookie = new Cookie("desktop2019", "0");
        driver.manage().addCookie(cookie);
        driver.navigate().refresh();
        driver.manage().window().maximize();
    }

    @Test(priority = 1)
    public void testCase1() {

        //Create "HomePage" Objects
        homePage = new HomePage(driver);

        //Click on search bar
        homePage.clickOnSearchBar();

        //Create "SearchPage" Objects
        searchPage = new SearchPage(driver);

        //Click search bar and search for advert
        searchPage.searchForAdvert("NG Residence");

        //Create "searchResultsPage" Objects
        searchResultsPage = new SearchResultsPage(driver);

        //Select an advertizement
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

        //Click on search bar
        homePage.clickOnSearchBar();

        //Create "SearchPage" Objects
        searchPage = new SearchPage(driver);

        //Search for any word
        searchPage.searchForAdvert("Kiralık Konut");

        //Create "searchResultsPage" Objects
        searchResultsPage = new SearchResultsPage(driver);

        //Apply filters
        searchResultsPage.applyFilters("İstanbul","4000","150");

        //Check results is suitable with filters
        List<String> resultList = searchResultsPage.getAdvertInfo();

        Assert.assertTrue(resultList.get(0).contains("İstanbul"));
        Assert.assertEquals(resultList.get(1), "2+1");
        Assert.assertTrue(Integer.parseInt(resultList.get(2)) <= 150);
        Assert.assertTrue(Integer.parseInt(resultList.get(3).replace(".","")) <= 4000);
    }

    @AfterTest
    public void quit() {
        driver.quit();
    }

}
