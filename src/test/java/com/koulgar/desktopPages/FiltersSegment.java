package com.koulgar.desktopPages;

import com.koulgar.helperMethods.HelperMethods;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class FiltersSegment {

    @FindBy(xpath = "//div//a[@title=\"Konum\"]")
    private WebElement selectLocationBox;

    @FindBy(xpath = "//button/span[contains(text(),\"İl Seçiniz\")]")
    private WebElement selectCountyBox;

    @FindBy(xpath = "//input[@value=\"34\"]/parent::label")
    private WebElement selectCounty;

    @FindBy(xpath = "//a[contains(text(),\"Brüt Metrekare\")]")
    private WebElement specifyAreaBox;

    @FindBy(xpath = "//*[@id=\"numSqmGapEnd\"]")
    private WebElement specifyMaxArea;

    @FindBy(xpath = "//a[contains(text(),\"Fiyat Aralığı\")]")
    private WebElement specifyPriceBox;

    @FindBy(xpath = "//*[@id=\"numPriceGapEnd\"]")
    private WebElement specifyMaxPrice;

    @FindBy(xpath = "//a[@title=\"Oda + Salon\"]")
    private WebElement selectApartmentSizeBox;

    @FindBy(xpath = "//button[text()=\"Ara\" and @class=\"btn btn-lg btn-green mobile-search-button w100p\"]")
    private WebElement filteredSearchSubmitButton;

    private WebDriver driver;
    private HelperMethods hp;

    public FiltersSegment(WebDriver driver) {
        System.out.println("Creating \"SearchResultPage\" Objects..");
        this.driver = driver;
        PageFactory.initElements(driver, this);
        this.hp = new HelperMethods(driver);
    }

    private void selectRentalType(String rentalType) {
        System.out.println("selecting rental type");

        //Select rental Type
        WebElement rentalTypeEl = driver.findElement(By.xpath("//li//a[@title='" + rentalType + "']"));
        hp.waitScrollAndClickOnElement(rentalTypeEl);
    }

    private void selectLocation(String location) {
        System.out.println("selecting location");

        //Scroll down to element
        hp.waitForVisibilityAndScrollToElement(selectLocationBox);

        //If location box is not active click on it
        if (selectLocationBox.getAttribute("class").equals("facet-box")) {
            hp.clickOnElement(selectLocationBox);
        }

        //Click on select county box
        hp.waitScrollAndClickOnElement(selectCountyBox);

        //Search county
        hp.sendKeysOnElement(location);

        //Click to selected county
        hp.waitScrollAndClickOnElement(selectCounty);
    }

    private void selectArea(String area) {
        System.out.println("selecting area");

        //If area box is not active click on it
        hp.waitForVisibilityAndScrollToElement(specifyAreaBox);
        if (specifyAreaBox.getAttribute("class").equals("facet-box")) {
            hp.clickOnElement(specifyAreaBox);
        }

        //Specify max area
        hp.waitScrollAndClickOnElement(specifyMaxArea);
        hp.sendKeysOnElement(area);

    }

    private void selectPrice(String maxPrice) {
        System.out.println("selecting price");

        //If price range box is not active click on it
        hp.waitForVisibilityAndScrollToElement(specifyPriceBox);
        if (specifyPriceBox.getAttribute("class").equals("facet-box")) {
            hp.clickOnElement(specifyPriceBox);
        }

        //Specify max price
        hp.waitScrollAndClickOnElement(specifyMaxPrice);
        hp.sendKeysOnElement(maxPrice);
    }

    private void selectApartmentSize(String apartmentSize) {
        System.out.println("selecting size");

        //If size box is not active click on it
        hp.waitForVisibilityAndScrollToElement(selectApartmentSizeBox);
        WebElement selectApartmentSizeBoxParent = selectApartmentSizeBox.findElement(By.xpath("./parent::div"));
        if (selectApartmentSizeBoxParent.getAttribute("class").equals("facet-box")) {
            hp.clickOnElement(selectApartmentSizeBoxParent);
        }

        //Specify apartment size
        WebElement specifyApartmentSize = driver.findElement(By.xpath("//input[following-sibling::text()[contains(.,'" + apartmentSize + "')]]"));
        hp.waitScrollAndClickOnElement(specifyApartmentSize);
    }

    private void submitFilters() {
        System.out.println("submitting filters");

        //Click on submit button
        hp.clickOnElement(filteredSearchSubmitButton);
    }

    public void applyFilters(String rentalType, String locationCounty, String maxArea, String maxPrice, String apartmentSize) throws InterruptedException {
        System.out.println("Looking for filters to apply..");
        selectRentalType(rentalType);
        Thread.sleep(500);
        selectLocation(locationCounty);
        Thread.sleep(500);
        selectArea(maxArea);
        Thread.sleep(500);
        selectPrice(maxPrice);
        Thread.sleep(500);
        selectApartmentSize(apartmentSize);
        Thread.sleep(500);
        submitFilters();
        Thread.sleep(500);
        System.out.println("Filters applied..");
    }

}
