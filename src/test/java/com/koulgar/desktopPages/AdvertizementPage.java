package com.koulgar.desktopPages;

import com.koulgar.helperMethods.HelperMethods;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.ArrayList;
import java.util.List;

public class AdvertizementPage {

    @FindBy(xpath = "//a[contains(@title,'Telefonu Göster')]")
    private WebElement revealPhoneNumberButton;

    @FindBy(xpath = "//ul[@class='phone-numbers']/li")
    private List<WebElement> phoneNumberList;

    private WebDriver driver;
    private Actions actions;

    private HelperMethods helperMethods;

    public AdvertizementPage(WebDriver driver) {
        System.out.println("Creating \"AdvertizementPage\" Objects..");
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public List<String> getPhoneNumber() {
        System.out.println("Getting phone number");
        actions = new Actions(driver);
        helperMethods = new HelperMethods(driver);

        //Moving to element that reveals phone number
        helperMethods.waitForVisibilityAndScrollToElement(revealPhoneNumberButton);
        actions.click(revealPhoneNumberButton).perform();

        //Get phone number and print it
        List<String> phoneNumbers = new ArrayList<>();
        phoneNumberList = helperMethods.driverWait(4, phoneNumberList);
        for (WebElement element : phoneNumberList) {
            phoneNumbers.add(element.getText());
        }
        return phoneNumbers;
    }

}
