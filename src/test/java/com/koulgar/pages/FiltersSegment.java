package com.koulgar.pages;

import com.koulgar.helperMethods.HelperMethods;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class FiltersSegment {

    private WebDriver driver;
    private Actions actions;
    private HelperMethods helperMethods;
    private JavascriptExecutor js;

    @FindBy(xpath = "//*[@id=\"mCSB_1_container\"]/li[1]/a")
    private WebElement rentalType;

    @FindBy(xpath = "//*[@id=\"facet-container\"]/div[2]/div[2]")
    private WebElement selectLocationBox;

    @FindBy(xpath = "//*[@id=\"facet-container\"]/div[2]/div[2]/div/div/div[1]/button")
    private WebElement selectCountyBox;

    @FindBy(xpath = "//*[@id=\"facet-container\"]/div[2]/div[2]/div/div/div[1]/div/div/input")
    private WebElement selectCountyBoxText;

    @FindBy(xpath = "//*[@id=\"facet-container\"]/div[2]/div[2]/div/div/div[1]/div/ul/li[2]/label")
    private WebElement selectCounty;

    @FindBy(xpath = "//*[@id=\"realty-facet\"]/div[1]")
    private WebElement specifyAreaBox;

    @FindBy(xpath = "//*[@id=\"numSqmGapEnd\"]")
    private WebElement specifyMaxArea;

    @FindBy(xpath = "//*[@id=\"realty-facet\"]/div[2]")
    private WebElement specifyPriceBox;

    @FindBy(xpath = "//*[@id=\"numPriceGapEnd\"]")
    private WebElement specifyMaxPrice;

    @FindBy(xpath = "//*[@id=\"realty-facet\"]/div[3]")
    private WebElement selectApartmentSizeBox;

    @FindBy(xpath = "//*[@id=\"mCSB_2_container\"]/li[4]/label")
    private WebElement specifyApartmentSize;

    @FindBy(xpath = "//*[@id=\"facet-container\"]/div[2]/div[5]/div/button")
    private WebElement filteredSearchSubmitButton;

    public FiltersSegment(WebDriver driver) {
        System.out.println("Creating \"SearchResultPage\" Objects..");
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    private void selectRentalType() {
        System.out.println("selecting rental type");
        actions = new Actions(driver);
        helperMethods = new HelperMethods(driver);
        js = (JavascriptExecutor) driver;

        //Scroll page down
        js.executeScript("window.scrollBy(0,100)");

        //Select rental Type
        this.rentalType = helperMethods.driverWait(10, rentalType);
        actions.moveToElement(rentalType).click().perform();
    }

    private void selectLocation(String location) {
        System.out.println("selecting location");
        actions = new Actions(driver);
        helperMethods = new HelperMethods(driver);
        js = (JavascriptExecutor) driver;

        //Scroll page down
        js.executeScript("window.scrollBy(0,300);");

        //If location box is not active click on it
        this.selectLocationBox = helperMethods.driverWait(10, selectLocationBox);
        if (selectLocationBox.getAttribute("class").equals("facet-box")) {
            actions.moveToElement(selectLocationBox).click().perform();
        }

        //Click on select province box
        this.selectCountyBox = helperMethods.driverWait(10, selectCountyBox);
        actions.moveToElement(selectCountyBox).click().perform();

        //Search county
        this.selectCountyBoxText = helperMethods.driverWait(10, selectCountyBoxText);
        actions.moveToElement(selectCountyBoxText).click().sendKeys(location).perform();

        //Click to selected county
        this.selectCounty = helperMethods.driverWait(10, selectCounty);
        actions.moveToElement(selectCounty).click().perform();
    }

    private void selectArea(String area) {
        System.out.println("selecting area");
        actions = new Actions(driver);
        helperMethods = new HelperMethods(driver);
        js = (JavascriptExecutor) driver;

        //Scroll page down
        js.executeScript("window.scrollBy(0,150);");

        //If area box is not active click on it
        this.specifyAreaBox = helperMethods.driverWait(4, specifyAreaBox);
        if (specifyAreaBox.getAttribute("class").equals("facet-box")) {
            actions.moveToElement(specifyAreaBox).click().perform();
        }

        //Specify max area
        this.specifyMaxArea = helperMethods.driverWait(4, specifyMaxArea);
        actions.moveToElement(specifyMaxArea).click().sendKeys(area).perform();

    }

    private void selectPrice(String price) {
        System.out.println("selecting price");
        actions = new Actions(driver);
        helperMethods = new HelperMethods(driver);
        js = (JavascriptExecutor) driver;

        //Scroll page down
        js.executeScript("window.scrollBy(0,150);");

        //If price range box is not active click on it
        this.specifyPriceBox = helperMethods.driverWait(4, specifyPriceBox);
        if (specifyPriceBox.getAttribute("class").equals("facet-box")) {
            actions.moveToElement(specifyPriceBox).click().perform();
        }

        //Specify max price
        this.specifyMaxPrice = helperMethods.driverWait(4, specifyMaxPrice);
        actions.moveToElement(specifyMaxPrice).click().sendKeys(price).perform();
    }

    private void selectApartmentSize() {
        System.out.println("selecting size");
        actions = new Actions(driver);
        helperMethods = new HelperMethods(driver);
        js = (JavascriptExecutor) driver;

        //Scroll page down
        js.executeScript("window.scrollBy(0,150);");

        //If size box is not active click on it
        this.selectApartmentSizeBox = helperMethods.driverWait(4, selectApartmentSizeBox);
        if (selectApartmentSizeBox.getAttribute("class").equals("facet-box")) {
            actions.moveToElement(selectApartmentSizeBox).click().perform();
        }

        //Specify apartment size
        this.specifyApartmentSize = helperMethods.driverWait(4, specifyApartmentSize);
        actions.moveToElement(specifyApartmentSize).click().perform();
    }

    private void submitFilters() {
        System.out.println("submitting filters");
        actions = new Actions(driver);
        helperMethods = new HelperMethods(driver);

        //Click on submit button
        this.filteredSearchSubmitButton = helperMethods.driverWait(4, filteredSearchSubmitButton);
        actions.moveToElement(filteredSearchSubmitButton).click().perform();
    }

    public void applyFilters(String locationCounty,String maxArea, String maxPrice) throws InterruptedException {
        System.out.println("Looking for filters to apply..");
        selectRentalType();
        Thread.sleep(500);
        selectLocation(locationCounty);
        Thread.sleep(500);
        selectArea(maxArea);
        Thread.sleep(500);
        selectPrice(maxPrice);
        Thread.sleep(500);
        selectApartmentSize();
        Thread.sleep(500);
        submitFilters();
        Thread.sleep(500);
        System.out.println("Filters applied..");
    }

}
