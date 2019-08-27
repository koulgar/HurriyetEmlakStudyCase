package com.koulgar.desktopPages;

import com.koulgar.helperMethods.HelperMethods;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.ArrayList;
import java.util.List;

public class SearchResultsPage {

    private WebDriver driver;
    private Actions actions;
    private HelperMethods helperMethods;
    private JavascriptExecutor js;

    @FindBy(xpath = "//div[@id=\"listview\"]/div[contains(@class,\"list-container-projects\")]")
    private List<WebElement> advertList;

    public SearchResultsPage(WebDriver driver) {
        System.out.println("Creating \"SearchResultPage\" Objects..");
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void selectAdvert(Integer advertOnRow) {
        System.out.println("Selecting proper advert");
        actions = new Actions(driver);
        helperMethods = new HelperMethods(driver);

        //Selecting proper advert
        WebElement desiredAdvertToSelect = getAdvertFromList(advertList,advertOnRow);
        helperMethods.waitForVisibilityAndScrollToElement(desiredAdvertToSelect);
        actions.click(desiredAdvertToSelect).perform();
    }

    public List<String> getAdvertInfo(Integer advertOnRow) {
        System.out.println("Getting advert info");
        helperMethods = new HelperMethods(driver);
        actions = new Actions(driver);

        //Selecting filtered advert result
        WebElement desiredAdvertToGetInfo = getAdvertFromList(advertList, advertOnRow);
        helperMethods.waitForVisibilityAndScrollToElement(desiredAdvertToGetInfo);

        //Getting texts from web elements of advert
        List<WebElement> results = desiredAdvertToGetInfo.findElements(By.xpath(".//ul//li"));
        List<String> resultList = new ArrayList<>();

        //Getting texts from inner elements
        for (WebElement element : results) {
            resultList.add(element.getText());
        }

        //Printing out advert info
        System.out.println(resultList.toString());

        return resultList;
    }

    private WebElement getAdvertFromList(List<WebElement> webElements,Integer advertOnRow) {
        return webElements.get(advertOnRow-1);
    }


}
