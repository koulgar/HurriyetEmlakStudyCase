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

    @FindBy(xpath = "//*[@id=\"listview\"]/div[3]")
    private WebElement firstAdvertOnList;

    public SearchResultsPage(WebDriver driver) {
        System.out.println("Creating \"SearchResultPage\" Objects..");
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void selectAdvert() {
        System.out.println("Selecting proper advert");
        actions = new Actions(driver);
        js = (JavascriptExecutor) driver;

        //Selecting proper advert
        this.firstAdvertOnList = new WebDriverWait(this.driver, 10).until(ExpectedConditions.visibilityOf(firstAdvertOnList));
        js.executeScript("arguments[0].scrollIntoView(true); window.scrollBy(0, -window.innerHeight / 3 );", firstAdvertOnList);
        actions.click(firstAdvertOnList).perform();
    }

    public List<String> getAdvertInfo() {
        System.out.println("Getting advert info");
        helperMethods = new HelperMethods(driver);
        actions = new Actions(driver);
        js = (JavascriptExecutor) driver;

        //Selecting filtered advert result
        this.firstAdvertOnList = helperMethods.driverWait(10, firstAdvertOnList);
        js.executeScript("arguments[0].scrollIntoView(true); window.scrollBy(0, -window.innerHeight / 3 );", firstAdvertOnList);

        //Getting texts from web elements of advert
        List<WebElement> results = firstAdvertOnList.findElements(By.xpath("./div/div/ul//li"));
        List<String> resultList = new ArrayList<>();

        for (WebElement element : results) {
            resultList.add(element.getText());
        }

        //Printing out advert info
        System.out.println(resultList.toString());

        return resultList;
    }

}
