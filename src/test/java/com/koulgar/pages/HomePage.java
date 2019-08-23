package com.koulgar.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class HomePage {

    private WebDriver driver;

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
        this.searchBar = new WebDriverWait(this.driver, 10).until(ExpectedConditions.visibilityOf(searchBar));
        searchBar.sendKeys(searchWord);
        this.searchButton.click();
    }

}
