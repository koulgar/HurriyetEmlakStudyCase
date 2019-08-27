package com.koulgar.mobilePages;

import com.koulgar.helperMethods.HelperMethods;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class SearchPage {

    @FindBy(xpath = "//label[@for=\"kiralik\"]")
    private WebElement rentalButton;

    @FindBy(xpath = "//input[@id=\"detailed-search\"]")
    private WebElement searchBar;

    private WebDriver driver;
    private HelperMethods hp;

    public SearchPage(WebDriver driver) {
        System.out.println("Creating \"SearchPage\" Objects..");
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void searchForAdvert(String type, String searchWord) {
        System.out.println("Clicking on search bar and searching for advert");
        hp = new HelperMethods(driver);

        //Writing word to be searched to search bar and submitting
        searchBar = hp.driverWait(10,searchBar);
        if (type.contains("Kiralık")) {
            hp.clickOnElement(rentalButton);
        }
        hp.clickOnElement(searchBar);
        hp.sendKeysOnElement(searchWord);
        hp.sendKeysOnElement(Keys.ENTER);

    }
}
