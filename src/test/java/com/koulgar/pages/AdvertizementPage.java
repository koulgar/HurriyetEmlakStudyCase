package com.koulgar.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class AdvertizementPage {

    private WebDriver driver;
    private Actions actions;

    @FindBy(xpath = "//a[contains(@title,'Telefonu Göster')]")
    private WebElement revealPhoneNumberButton;

    @FindBy(xpath = "//ul[@class='phone-numbers']")
    private WebElement phoneNumberElement;

    public AdvertizementPage(WebDriver driver) {
        System.out.println("Creating \"AdvertizementPage\" Objects..");
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public String getPhoneNumber() {
        actions = new Actions(driver);

        //Moving to element that reveals phone number
        actions.moveToElement(revealPhoneNumberButton);
        this.revealPhoneNumberButton = new WebDriverWait(this.driver, 10).until(ExpectedConditions.visibilityOf(revealPhoneNumberButton));
        actions.click(revealPhoneNumberButton).perform();

        //Get phone number and print it
        actions.moveToElement(phoneNumberElement);
        this.phoneNumberElement = new WebDriverWait(this.driver, 10).until(ExpectedConditions.visibilityOf(phoneNumberElement));
        return phoneNumberElement.getText();

    }
}
