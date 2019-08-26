package com.koulgar.pages;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class HomePage {

    private WebDriver driver;
    private Actions actions;
    private JavascriptExecutor js;

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

        //Writing word to be searched to search bar and submitting
        this.searchBar = new WebDriverWait(this.driver, 10).until(ExpectedConditions.visibilityOf(searchBar));
        actions.moveToElement(searchBar).click().sendKeys(searchWord).perform();
        actions.moveToElement(searchButton).click().perform();
    }

}
