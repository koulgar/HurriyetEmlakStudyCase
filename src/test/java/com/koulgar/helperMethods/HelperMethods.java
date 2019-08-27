package com.koulgar.helperMethods;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

public class HelperMethods {

    private WebDriver driver;

    public HelperMethods(WebDriver driver) {
        this.driver = driver;
    }

    public WebElement driverWait(Integer seconds, WebElement webElement) {
        return new WebDriverWait(this.driver, seconds).until(ExpectedConditions.visibilityOf(webElement));
    }

    public List<WebElement> driverWait(Integer seconds, List<WebElement> webElements) {
        return new WebDriverWait(this.driver, seconds).until(ExpectedConditions.visibilityOfAllElements(webElements));
    }

    public void waitForVisibilityAndScrollToElement(WebElement element) {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        element = driverWait(10,element);
        js.executeScript("arguments[0].scrollIntoView(true); window.scrollBy(0, -window.innerHeight / 3);", element);
    }

    public void clickOnElement(WebElement element) {
        Actions actions = new Actions(driver);
        actions.click(element).perform();
    }

    public void waitScrollAndClickOnElement(WebElement element) {
        waitForVisibilityAndScrollToElement(element);
        clickOnElement(element);
    }

    public void sendKeysOnElement(String keysToSend) {
        Actions actions = new Actions(driver);
        actions.sendKeys(keysToSend).perform();
    }

    public void sendKeysOnElement(Keys keysToSend) {
        Actions actions = new Actions(driver);
        actions.sendKeys(keysToSend).perform();
    }

}
