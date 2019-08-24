package com.koulgar.helperMethods;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class HelperMethods {
    WebDriver driver;

    public HelperMethods(WebDriver driver) {
        this.driver = driver;
    }

    public WebElement driverWait(Integer seconds, WebElement webElement) {
        return new WebDriverWait(this.driver, seconds).until(ExpectedConditions.visibilityOf(webElement));
    }

}
