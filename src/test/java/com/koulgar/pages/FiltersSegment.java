package com.koulgar.pages;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class FiltersSegment {

    private WebDriver driver;
    private Actions actions;
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

    public FiltersSegment(WebDriver driver) {
        System.out.println("Creating \"SearchResultPage\" Objects..");
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    private void selectRentalType() {
        System.out.println("selecting rental type");
        actions = new Actions(driver);

        //Scroll page down
        js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollBy(0,200);");

        //Select rental Type
        this.rentalType = driverWait(10, rentalType);
        actions.moveToElement(rentalType).click().perform();
    }

    private void selectLocation() {
        System.out.println("selecting location");
        actions = new Actions(driver);

        //Scroll page down
        js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollBy(0,250);");

        //If location box is not active click on it
        this.selectLocationBox = driverWait(10, selectLocationBox);
        if (selectLocationBox.getAttribute("class").equals("facet-box")) {
            actions.moveToElement(selectLocationBox).click().perform();
        }

        //Click on select province box
        this.selectCountyBox = driverWait(10, selectCountyBox);
        actions.moveToElement(selectCountyBox).click().perform();

        //Search for İstanbul
        this.selectCountyBoxText = driverWait(10, selectCountyBoxText);
        actions.moveToElement(selectCountyBoxText).click().sendKeys("İstanbul").perform();

        //Click to selected county
        this.selectCounty = driverWait(10, selectCounty);
        actions.moveToElement(selectCounty).click().perform();
    }

    private void selectArea() {
        System.out.println("selecting area");
        actions = new Actions(driver);

        //If area box is not active click on it
        this.specifyAreaBox = driverWait(4, specifyAreaBox);
        if (specifyAreaBox.getAttribute("class").equals("facet-box")) {
            actions.moveToElement(specifyAreaBox).click().perform();
        }

        //Scroll page down
        js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollBy(0,250);");

        //Specify max area
        this.specifyMaxArea = driverWait(4, specifyMaxArea);
        actions.moveToElement(specifyMaxArea).click().sendKeys("150").perform();

    }

    private void selectPrice() {
        System.out.println("selecting price");
        actions = new Actions(driver);

        //If price range box is not active click on it
        this.specifyPriceBox = driverWait(4, specifyPriceBox);
        if (specifyPriceBox.getAttribute("class").equals("facet-box")) {
            actions.moveToElement(specifyPriceBox).click().perform();
        }

        //Scroll page down
        js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollBy(0,250);");

        //Specify max price
        this.specifyMaxPrice = driverWait(4, specifyMaxPrice);
        actions.moveToElement(specifyMaxPrice).click().sendKeys("4000").perform();
    }

    private void selectApartmentSize() {
        System.out.println("selecting size");
        actions = new Actions(driver);

        //If size box is not active click on it
        this.selectApartmentSizeBox = driverWait(4, selectApartmentSizeBox);

        if (selectApartmentSizeBox.getAttribute("class").equals("facet-box")) {
            actions.moveToElement(selectApartmentSizeBox).click().perform();
        }

        //Specify apartment size
        this.specifyApartmentSize = driverWait(4, specifyApartmentSize);
        actions.moveToElement(specifyApartmentSize).click().perform();
    }

    public void applyFilters() {
        selectRentalType();
        selectLocation();
        selectArea();
        selectPrice();
        selectApartmentSize();
    }

    private WebElement driverWait(Integer seconds, WebElement webElement) {
        return new WebDriverWait(this.driver, seconds).until(ExpectedConditions.visibilityOf(webElement));
    }


}
