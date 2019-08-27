package com.koulgar.mobilePages;

import com.koulgar.helperMethods.HelperMethods;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class HomePage {

    private WebDriver driver;
    private HelperMethods hp;

    @FindBy(xpath = "//a[@href=\"/ilan-ara\"]")
    private WebElement searchBar;

    public HomePage(WebDriver driver) {
        System.out.println("Creating \"HomePage\" Objects..");
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void clickOnSearchBar() {
        System.out.println("Clicking on search bar");
        hp = new HelperMethods(driver);

        //Writing word to be searched to search bar and submitting
        hp.waitScrollAndClickOnElement(searchBar);
    }
}
