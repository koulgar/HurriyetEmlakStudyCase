package com.koulgar.mobilePages;

import com.koulgar.helperMethods.HelperMethods;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class AdvertizementPage {

    private WebDriver driver;
    private Actions actions;
    private JavascriptExecutor js;

    private HelperMethods helperMethods;

    @FindBy(xpath = "//*[@id=\"ownerPhoneNumbersTitle\"]")
    private WebElement revealPhoneNumberButton;

    @FindBy(xpath = "//*[@id=\"ownerPhoneNumbers\"]/ul/li/a")
    private WebElement phoneNumberElement;

    public AdvertizementPage(WebDriver driver) {
        System.out.println("Creating \"AdvertizementPage\" Objects..");
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public String getPhoneNumber() {
        System.out.println("Getting phone number");
        actions = new Actions(driver);
        helperMethods = new HelperMethods(driver);
        js = (JavascriptExecutor) driver;

        //Moving to element that reveals phone number
        this.revealPhoneNumberButton = helperMethods.driverWait(10, revealPhoneNumberButton);
        js.executeScript("arguments[0].scrollIntoView(true); window.scrollBy(0, -window.innerHeight / 3);", revealPhoneNumberButton);
        actions.click(revealPhoneNumberButton).perform();

        //Get phone number and print it
        this.phoneNumberElement = helperMethods.driverWait(10, phoneNumberElement);
        return phoneNumberElement.getText();
    }
}
