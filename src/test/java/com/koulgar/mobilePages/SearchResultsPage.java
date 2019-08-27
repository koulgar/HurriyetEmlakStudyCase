package com.koulgar.mobilePages;

import com.koulgar.helperMethods.HelperMethods;
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
    private JavascriptExecutor js;
    private HelperMethods helperMethods;
    private FiltersSegment filtersSegment;

    @FindBy(xpath = "//div[@class=\"list-item jsListItem jsListAllContents jsListNumber-5\"]")
    private WebElement firstAdvertOnList;

    public SearchResultsPage(WebDriver driver) {
        System.out.println("Creating \"SearchResultsPage\" Objects..");
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void selectAdvert() {
        System.out.println("Selecting proper advert");
        actions = new Actions(driver);
        helperMethods = new HelperMethods(driver);
        js = (JavascriptExecutor) driver;

        //Selecting proper advert
        this.firstAdvertOnList = helperMethods.driverWait(10, firstAdvertOnList);
        js.executeScript("arguments[0].scrollIntoView(true); window.scrollBy(0, -window.innerHeight / 3 );", firstAdvertOnList);
        actions.click(firstAdvertOnList).perform();
    }

    public void applyFilters(String county, String maxPrice, String maxArea) throws InterruptedException {
        System.out.println("Applying filters");

        //Create "FiltersSegment" Objects
        filtersSegment = new FiltersSegment(driver);

        //Applying filters
        filtersSegment.clickOnDetailedSearch();
        Thread.sleep(500);
        filtersSegment.specifyLocation(county);
        Thread.sleep(500);
        filtersSegment.specifyPriceRange(maxPrice);
        Thread.sleep(500);
        filtersSegment.specifyApartmentSize();
        Thread.sleep(500);
        filtersSegment.specifyApartmentArea(maxArea);
        Thread.sleep(500);
        filtersSegment.submitFilters();
    }

    public List<String> getAdvertInfo() {
        System.out.println("Getting advert info");
        helperMethods = new HelperMethods(driver);
        js = (JavascriptExecutor) driver;

        driver.navigate().refresh();

        //Selecting filtered advert result
        this.firstAdvertOnList = helperMethods.driverWait(10, firstAdvertOnList);
        js.executeScript("arguments[0].scrollIntoView(true); window.scrollBy(0, -window.innerHeight / 3 );", firstAdvertOnList);


        //Getting texts from web elements of advert
        List<String> resultList = new ArrayList<>();
        resultList.add(firstAdvertOnList.getAttribute("data-user-city"));
        resultList.add(firstAdvertOnList.getAttribute("data-room"));
        resultList.add(firstAdvertOnList.getAttribute("data-meter"));
        resultList.add(firstAdvertOnList.getAttribute("data-price"));

        //Printing out advert info
        System.out.println(resultList.toString());

        return resultList;
    }
}
