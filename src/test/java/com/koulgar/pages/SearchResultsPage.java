package com.koulgar.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class SearchResultsPage {

    private WebDriver driver;

    @FindBy(xpath = "//a[contains(@title,\"NG\")]")
    private WebElement searchButton;

    public SearchResultsPage(WebDriver driver) {
        System.out.println("Creating \"HomePage\" Objects..");
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void selectAdvertizement() {
        this.searchButton = new WebDriverWait(this.driver, 10).until(ExpectedConditions.visibilityOf(searchButton));
        searchButton.click();
    }

}
