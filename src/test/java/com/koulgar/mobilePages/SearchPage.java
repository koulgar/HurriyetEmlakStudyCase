package com.koulgar.mobilePages;

import com.koulgar.helperMethods.HelperMethods;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class SearchPage {

    private WebDriver driver;
    private Actions actions;

    private HelperMethods helperMethods;

    @FindBy(xpath = "//label[@for=\"kiralik\"]")
    private WebElement rentalButton;

    @FindBy(xpath = "//input[@id=\"detailed-search\"]")
    private WebElement searchBar;

    public SearchPage(WebDriver driver) {
        System.out.println("Creating \"SearchPage\" Objects..");
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void searchForAdvert(String searchWord) {
        System.out.println("Clicking on search bar and searching for advert");
        actions = new Actions(driver);
        helperMethods = new HelperMethods(driver);

        //Writing word to be searched to search bar and submitting
        this.searchBar = helperMethods.driverWait(10,searchBar);
        actions.click(rentalButton).perform();
        actions.moveToElement(searchBar).click().sendKeys(searchWord).sendKeys(Keys.ENTER).perform();
    }
}
