package com.koulgar.desktopPages;

import com.koulgar.helperMethods.HelperMethods;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class HomePage {

    @FindBy(xpath = "//*[@id=\"txtSearch\"]")
    private WebElement searchBar;

    @FindBy(xpath = "//*[@id=\"btnTextSearch\"]")
    private WebElement searchButton;

    private WebDriver driver;
    private HelperMethods hp;

    public HomePage(WebDriver driver) {
        System.out.println("Creating \"HomePage\" Objects..");
        this.driver = driver;
        PageFactory.initElements(driver, this);
        this.hp = new HelperMethods(driver);
    }

    public void searchForAdvertizement(String searchWord) {
        System.out.println("Searching for adverts");

        //Writing word to be searched to search bar and submitting
        hp.waitScrollAndClickOnElement(searchBar);
        hp.sendKeysOnElement(searchWord);
        hp.clickOnElement(searchButton);
    }

}
