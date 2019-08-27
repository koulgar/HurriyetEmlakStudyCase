package com.koulgar.mobilePages;

import com.koulgar.helperMethods.HelperMethods;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class HomePage {

    private WebDriver driver;
    private Actions actions;
    private JavascriptExecutor js;
    private HelperMethods helperMethods;

    @FindBy(xpath = "//a[@href=\"/ilan-ara\"]")
    private WebElement searchBar;

    public HomePage(WebDriver driver) {
        System.out.println("Creating \"HomePage\" Objects..");
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void clickOnSearchBar() {
        System.out.println("Clicking on search bar");
        actions = new Actions(driver);
        helperMethods = new HelperMethods(driver);

        //Writing word to be searched to search bar and submitting
        this.searchBar = helperMethods.driverWait(10, searchBar);
        actions.moveToElement(searchBar).click().perform();
    }
}