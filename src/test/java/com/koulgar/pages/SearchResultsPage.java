package com.koulgar.pages;

import com.koulgar.helperMethods.HelperMethods;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.ArrayList;
import java.util.List;

public class SearchResultsPage {

    private WebDriver driver;
    private Actions actions;
    private HelperMethods helperMethods;
    private JavascriptExecutor js;

    @FindBy(xpath = "//a[contains(@title,\"NG\")]")
    private WebElement ngResidenceAdvertLink;

    @FindBy(xpath = "//*[@id=\"listview\"]/div[3]")
    private WebElement filteredSearchAdvertLink;

    public SearchResultsPage(WebDriver driver) {
        System.out.println("Creating \"SearchResultPage\" Objects..");
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void selectAdvertizement() {
        System.out.println("Selecting proper advert");
        actions = new Actions(driver);

        //Selecting proper advert
        actions.moveToElement(ngResidenceAdvertLink).perform();
        this.ngResidenceAdvertLink = new WebDriverWait(this.driver, 10).until(ExpectedConditions.visibilityOf(ngResidenceAdvertLink));
        actions.click(ngResidenceAdvertLink).perform();
    }

    public List<String> getAdvertInfo() {
        System.out.println("Getting advert info");
        helperMethods = new HelperMethods(driver);
        actions = new Actions(driver);
        js = (JavascriptExecutor) driver;

        //Scroll page down
        js.executeScript("window.scrollBy(0,300);");

        //Selecting filtered advert result
        this.filteredSearchAdvertLink = helperMethods.driverWait(10, filteredSearchAdvertLink);
        actions.moveToElement(filteredSearchAdvertLink).perform();

        //Getting texts from web elements of advert
        List<WebElement> results = filteredSearchAdvertLink.findElements(By.xpath("./div/div/ul//li"));
        List<String> resultList = new ArrayList<>();

        for (WebElement element : results) {
            resultList.add(element.getText());
        }

        //Printing out advert info
        System.out.println(resultList.toString());

        return resultList;
    }

}
