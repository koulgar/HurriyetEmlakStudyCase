package com.koulgar.mobilePages;

import com.koulgar.helperMethods.HelperMethods;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.ArrayList;
import java.util.List;

public class SearchResultsPage {

    private WebDriver driver;
    private Actions actions;
    private JavascriptExecutor js;
    private HelperMethods helperMethods;

    @FindBy(xpath = "//div[@class=\"list-item jsListItem jsListAllContents jsListNumber-5\"]")
    private WebElement firstAdvertOnList;

    @FindBy(xpath = "//*[@id=\"list-detail-search\"]")
    private WebElement detailedSearchButton;

    @FindBy(xpath = "//li[contains(text(),\"Konum\")]")
    private WebElement selectLocationBox;

    @FindBy(xpath = "//span[text()=\"İl Seçiniz\"]")
    private WebElement specifyCountyButton;

    @FindBy(xpath = "//span[text()=\"İstanbul\"]")
    private WebElement specifyCounty;

    @FindBy(xpath = "//span[text()=\"İstanbul\"]//ancestor::div[contains(@class,\"detail-search-item\")]/div[3]/a")
    private WebElement okLocation;

    @FindBy(xpath = "//li[contains(text(),\"Fiyat Aralığı\")]")
    private WebElement priceRangeBox;

    @FindBy(xpath = "//input[@id=\"maxfiyatAraligi\"]")
    private WebElement specifyMaxPrice;

    @FindBy(xpath = "//input[@id=\"maxfiyatAraligi\"]//ancestor::div[contains(@class,\"detail-search-item\")]/div[3]/a")
    private WebElement okPriceRange;

    @FindBy(xpath = "//li[contains(text(),\"Oda Sayısı\")]")
    private WebElement apartmentSizeBox;

    @FindBy(xpath = "//label[contains(text(),\"2+1\")]")
    private WebElement specifyApartmentSize;

    @FindBy(xpath = "//label[contains(text(),\"2+1\")]//ancestor::div[contains(@class,\"detail-search-item\")]/div[3]/a")
    private WebElement okApartmentSize;

    @FindBy(xpath = "//li[contains(text(),\"Brüt Metrekare\")]")
    private WebElement apartmentAreaBox;

    @FindBy(xpath = "//*[@id=\"maxMetrekare\"]")
    private WebElement maxApartmentArea;

    @FindBy(xpath = "//*[@id=\"maxMetrekare\"]//ancestor::div[contains(@class,\"detail-search-item\")]/div[3]/a")
    private WebElement okApartmentArea;

    @FindBy(xpath = "//*[@id=\"show-more-search-results\"]")
    private WebElement submitFiltersButton;

    public SearchResultsPage(WebDriver driver) {
        System.out.println("Creating \"SearchResultsPage\" Objects..");
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void selectAdvert() {
        System.out.println("Selecting proper advert");
        actions = new Actions(driver);
        helperMethods = new HelperMethods(driver);
        js = (JavascriptExecutor) driver;

        //Selecting proper advert
        this.firstAdvertOnList = helperMethods.driverWait(10, firstAdvertOnList);
        js.executeScript("arguments[0].scrollIntoView(true); window.scrollBy(0, -window.innerHeight / 3 );", firstAdvertOnList);
        actions.click(firstAdvertOnList).perform();
    }

    private void clickOnDetailedSearch() {
        System.out.println("Starting detailed search");
        helperMethods = new HelperMethods(driver);
        actions = new Actions(driver);
        js = (JavascriptExecutor) driver;

        //clicking on detailed search
        this.detailedSearchButton = helperMethods.driverWait(4, detailedSearchButton);
        actions.click(detailedSearchButton).perform();
    }

    private void specifyLocation(String county) {
        System.out.println("Specifying county");
        helperMethods = new HelperMethods(driver);
        actions = new Actions(driver);
        js = (JavascriptExecutor) driver;

        //clicking on select county box
        this.selectLocationBox = helperMethods.driverWait(4, selectLocationBox);
        actions.click(selectLocationBox).perform();

        //specifying county
        this.specifyCountyButton = helperMethods.driverWait(4, specifyCountyButton);
        actions.click(specifyCountyButton).perform();

        //searching for desired county
        actions.sendKeys(county).perform();

        //selecting desired county
        this.specifyCounty = helperMethods.driverWait(4, specifyCounty);
        actions.click(specifyCounty).perform();

        //Press Ok button
        this.okLocation = helperMethods.driverWait(4, okLocation);
        actions.moveToElement(okLocation).click().perform();
    }

    private void specifyPriceRange(String maxPrice) {
        System.out.println("Specifying price range");
        actions = new Actions(driver);
        helperMethods = new HelperMethods(driver);
        js = (JavascriptExecutor) driver;

        //Clicking on price range button
        this.priceRangeBox = helperMethods.driverWait(4, priceRangeBox);
        js.executeScript("arguments[0].scrollIntoView(true); window.scrollBy(0, -window.innerHeight / 3 );", priceRangeBox);
        actions.click(priceRangeBox).perform();

        //Specifying max price
        this.specifyMaxPrice = helperMethods.driverWait(4, specifyMaxPrice);
        actions.click(specifyMaxPrice).sendKeys(maxPrice).perform();

        //Press Ok button
        this.okPriceRange = helperMethods.driverWait(4, okPriceRange);
        actions.click(okPriceRange).perform();
    }

    private void specifyApartmentSize() {
        System.out.println("Specifying apartment size");
        helperMethods = new HelperMethods(driver);
        actions = new Actions(driver);
        js = (JavascriptExecutor) driver;

        //clicking on aparment size button
        this.apartmentSizeBox = helperMethods.driverWait(4, apartmentSizeBox);
        js.executeScript("arguments[0].scrollIntoView(true); window.scrollBy(0, -window.innerHeight / 3 );", apartmentSizeBox);
        actions.click(apartmentSizeBox).perform();

        //Specifying apartment size
        this.specifyApartmentSize = helperMethods.driverWait(4, specifyApartmentSize);
        actions.click(specifyApartmentSize).perform();

        //Press Ok button
        this.okApartmentSize = helperMethods.driverWait(4, okApartmentSize);
        actions.click(okApartmentSize).perform();
    }

    public void specifyApartmentArea(String maxArea) {
        System.out.println("Specifying apartment area");
        helperMethods = new HelperMethods(driver);
        actions = new Actions(driver);
        js = (JavascriptExecutor) driver;

        //clicking on aparment size button
        this.apartmentAreaBox = helperMethods.driverWait(4, apartmentAreaBox);
        js.executeScript("arguments[0].scrollIntoView(true); window.scrollBy(0, -window.innerHeight / 3 );", apartmentAreaBox);
        actions.click(apartmentAreaBox).perform();

        //Specifying max price
        this.maxApartmentArea = helperMethods.driverWait(4, maxApartmentArea);
        actions.click(maxApartmentArea).sendKeys(maxArea).perform();

        //Press Ok button
        this.okApartmentArea = helperMethods.driverWait(4, okApartmentArea);
        actions.click(okApartmentArea).perform();
    }

    public void submitFilters() {
        System.out.println("Submitting filters");
        helperMethods = new HelperMethods(driver);
        actions = new Actions(driver);

        this.submitFiltersButton = helperMethods.driverWait(4,submitFiltersButton);
        actions.click(submitFiltersButton).perform();
    }

    public void applyFilters(String county, String maxPrice, String maxArea) throws InterruptedException {
        System.out.println("Applying filters");
        clickOnDetailedSearch();
        Thread.sleep(500);
        specifyLocation(county);
        Thread.sleep(500);
        specifyPriceRange(maxPrice);
        Thread.sleep(500);
        specifyApartmentSize();
        Thread.sleep(500);
        specifyApartmentArea(maxArea);
        Thread.sleep(500);
        submitFilters();
    }

    public List<String> getAdvertInfo() {
        System.out.println("Getting advert info");
        helperMethods = new HelperMethods(driver);
        js = (JavascriptExecutor) driver;

        driver.navigate().refresh();

        //Selecting filtered advert result
        this.firstAdvertOnList = helperMethods.driverWait(10, firstAdvertOnList);
        js.executeScript("arguments[0].scrollIntoView(true); window.scrollBy(0, -window.innerHeight / 3 );", firstAdvertOnList);


        //Getting texts from web elements of advert
        List<String> resultList = new ArrayList<>();
        resultList.add(firstAdvertOnList.getAttribute("data-user-city"));
        resultList.add(firstAdvertOnList.getAttribute("data-room"));
        resultList.add(firstAdvertOnList.getAttribute("data-meter"));
        resultList.add(firstAdvertOnList.getAttribute("data-price"));

        //Printing out advert info
        System.out.println(resultList.toString());

        return resultList;
    }
}
