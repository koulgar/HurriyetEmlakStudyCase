package com.koulgar.mobilePages;

import com.koulgar.helperMethods.HelperMethods;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class FiltersSegment {

    @FindBy(xpath = "//*[@id=\"list-detail-search\"]")
    private WebElement detailedSearchButton;

    @FindBy(xpath = "//li[contains(text(),\"Konum\")]")
    private WebElement selectLocationBox;

    @FindBy(xpath = "//span[text()=\"İl Seçiniz\"]")
    private WebElement specifyCountyButton;

    @FindBy(xpath = "//li[contains(text(),\"Fiyat Aralığı\")]")
    private WebElement priceRangeBox;

    @FindBy(xpath = "//input[@id=\"maxfiyatAraligi\"]")
    private WebElement specifyMaxPrice;

    @FindBy(xpath = "//li[contains(text(),\"Oda Sayısı\")]")
    private WebElement apartmentSizeBox;

    @FindBy(xpath = "//li[contains(text(),\"Brüt Metrekare\")]")
    private WebElement apartmentAreaBox;

    @FindBy(xpath = "//*[@id=\"maxMetrekare\"]")
    private WebElement maxApartmentArea;

    @FindBy(xpath = "//*[@id=\"show-more-search-results\"]")
    private WebElement submitFiltersButton;

    private WebDriver driver;
    private Actions actions;
    private JavascriptExecutor js;
    private HelperMethods hp;

    public FiltersSegment(WebDriver driver) {
        System.out.println("Creating \"SearchResultsPage\" Objects..");
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void clickOnDetailedSearch() {
        System.out.println("Starting detailed search");
        hp = new HelperMethods(driver);

        //clicking on detailed search
        hp.waitScrollAndClickOnElement(detailedSearchButton);
    }

    public void specifyLocation(String county) {
        System.out.println("Specifying county");
        hp = new HelperMethods(driver);

        //clicking on select county box
        hp.waitScrollAndClickOnElement(selectLocationBox);

        //specifying county
        hp.waitScrollAndClickOnElement(specifyCountyButton);

        //searching for desired county
        hp.sendKeysOnElement(county);

        //selecting desired county
        WebElement specifyCounty = driver.findElement(By.xpath("//span[text()=\"" + county + "\"]"));
        hp.waitScrollAndClickOnElement(specifyCounty);

        //Press Ok button
        WebElement okLocation = getOkButton(specifyCounty);
        hp.waitScrollAndClickOnElement(okLocation);
    }

    public void specifyPriceRange(String maxPrice) {
        System.out.println("Specifying price range");
        hp = new HelperMethods(driver);

        //Clicking on price range button
        hp.waitScrollAndClickOnElement(priceRangeBox);

        //Specifying max price
        hp.waitScrollAndClickOnElement(specifyMaxPrice);
        hp.sendKeysOnElement(maxPrice);

        //Press Ok button
        WebElement okPriceRange = getOkButton(specifyMaxPrice);
        hp.waitScrollAndClickOnElement(okPriceRange);
    }

    public void specifyApartmentSize(String apartmentSize) {
        System.out.println("Specifying apartment size");
        hp = new HelperMethods(driver);

        //clicking on apartment size button
        hp.waitScrollAndClickOnElement(apartmentSizeBox);

        //Specifying apartment size
        WebElement specifyApartmentSize = driver.findElement(By.xpath("//label[contains(text(),'" + apartmentSize + "')]"));
        hp.waitScrollAndClickOnElement(specifyApartmentSize);

        //Press Ok button
        WebElement okApartmentSize = getOkButton(specifyApartmentSize);
        hp.waitScrollAndClickOnElement(okApartmentSize);
    }

    public void specifyApartmentArea(String maxArea) {
        System.out.println("Specifying apartment area");
        hp = new HelperMethods(driver);

        //clicking on aparment size button
        hp.waitScrollAndClickOnElement(apartmentAreaBox);

        //Specifying max price
        hp.waitScrollAndClickOnElement(maxApartmentArea);
        hp.sendKeysOnElement(maxArea);

        //Press Ok button
        WebElement okApartmentArea = getOkButton(maxApartmentArea);
        hp.waitScrollAndClickOnElement(okApartmentArea);
    }

    public void submitFilters() {
        System.out.println("Submitting filters");
        hp = new HelperMethods(driver);
        actions = new Actions(driver);

        hp.waitScrollAndClickOnElement(submitFiltersButton);
    }

    public WebElement getOkButton(WebElement element) {
        return element.findElement(By.xpath(".//ancestor::div[contains(@class,\"detail-search-item\")]/div[3]/a"));
    }
}
