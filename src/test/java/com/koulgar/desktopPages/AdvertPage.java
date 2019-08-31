package com.koulgar.desktopPages;

import com.koulgar.helperMethods.HelperMethods;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.ArrayList;
import java.util.List;

public class AdvertPage {

    @FindBy(xpath = "//a[contains(@title,'Telefonu Göster')]")
    private WebElement revealPhoneNumberButton;

    @FindBy(xpath = "//ul[@class='phone-numbers']/li")
    private List<WebElement> phoneNumberList;

    private WebDriver driver;

    private HelperMethods hp;

    public AdvertPage(WebDriver driver) {
        System.out.println("Creating \"AdvertizementPage\" Objects..");
        this.driver = driver;
        PageFactory.initElements(driver, this);
        this.hp = new HelperMethods(driver);
    }

    public List<String> getPhoneNumber() {
        System.out.println("Getting phone number");

        //Moving to element that reveals phone number
        hp.waitScrollAndClickOnElement(revealPhoneNumberButton);

        //Get phone number and print it
        List<String> phoneNumbers = new ArrayList<>();
        phoneNumberList = hp.driverWait(4, phoneNumberList);
        for (WebElement element : phoneNumberList) {
            phoneNumbers.add(element.getText());
        }

        return phoneNumbers;
    }

}
