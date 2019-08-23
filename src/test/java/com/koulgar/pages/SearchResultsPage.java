package com.koulgar.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class SearchResultsPage {

    private WebDriver driver;
    private Actions actions;

    @FindBy(xpath = "//a[contains(@title,\"NG\")]")
    private WebElement ngResidenceAdvertLink;

    public SearchResultsPage(WebDriver driver) {
        System.out.println("Creating \"SearchResultPage\" Objects..");
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void selectAdvertizement() {
        actions = new Actions(driver);
        actions.moveToElement(ngResidenceAdvertLink).perform();
        this.ngResidenceAdvertLink = new WebDriverWait(this.driver, 10).until(ExpectedConditions.visibilityOf(ngResidenceAdvertLink));
        ngResidenceAdvertLink.click();
    }

}
