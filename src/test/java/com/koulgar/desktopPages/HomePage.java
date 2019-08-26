package com.koulgar.desktopPages;

import com.koulgar.helperMethods.HelperMethods;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class HomePage {

    private WebDriver driver;
    private Actions actions;

    private HelperMethods helperMethods;

    @FindBy(xpath = "//*[@id=\"txtSearch\"]")
    private WebElement searchBar;

    @FindBy(xpath = "//*[@id=\"btnTextSearch\"]")
    private WebElement searchButton;

    public HomePage(WebDriver driver) {
        System.out.println("Creating \"HomePage\" Objects..");
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void searchForAdvertizement(String searchWord) {
        System.out.println("Searching for adverts");
        actions = new Actions(driver);
        helperMethods = new HelperMethods(driver);

        //Writing word to be searched to search bar and submitting
        this.searchBar = helperMethods.driverWait(10, searchBar);
        actions.moveToElement(searchBar).click().sendKeys(searchWord).perform();
        actions.moveToElement(searchButton).click().perform();
    }

}
