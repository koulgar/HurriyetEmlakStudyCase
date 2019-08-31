package com.koulgar.mobilePages;

import com.koulgar.helperMethods.HelperMethods;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.ArrayList;
import java.util.List;

public class SearchResultsPage {

    @FindBy(xpath = "//div[@class=\"list-page\"]//div[contains(@class,\"jsListNumber\")]")
    private List<WebElement> advertList;

    private WebDriver driver;
    private HelperMethods hp;
    private FiltersSegment filtersSegment;

    public SearchResultsPage(WebDriver driver) {
        System.out.println("Creating \"SearchResultsPage\" Objects..");
        this.driver = driver;
        PageFactory.initElements(driver, this);
        this.hp = new HelperMethods(driver);
        this.filtersSegment = new FiltersSegment(driver);
    }

    public void selectAdvert(Integer advertOnRow) {
        System.out.println("Selecting proper advert");

        //Getting advert list
        WebElement desiredAdvertToSelect = getAdvertFromList(advertList, advertOnRow);

        //Selecting proper advert
        hp.waitScrollAndClickOnElement(desiredAdvertToSelect);
    }

    public void applyFilters(String county, String maxPrice, String maxArea, String apartmentSize) throws InterruptedException {
        System.out.println("Applying filters");

        //Applying filters
        filtersSegment.clickOnDetailedSearch();
        Thread.sleep(500);
        filtersSegment.specifyLocation(county);
        Thread.sleep(500);
        filtersSegment.specifyPriceRange(maxPrice);
        Thread.sleep(500);
        filtersSegment.specifyApartmentSize(apartmentSize);
        Thread.sleep(500);
        filtersSegment.specifyApartmentArea(maxArea);
        Thread.sleep(500);
        filtersSegment.submitFilters();
    }

    public List<String> getAdvertInfo(Integer advertOnRow) {
        System.out.println("Getting advert info");

        //Refresh page
        driver.navigate().refresh();

        //Select proper advert
        WebElement desiredAdvertToSelect = getAdvertFromList(advertList, advertOnRow);

        //Selecting filtered advert result
        hp.waitForVisibilityAndScrollToElement(desiredAdvertToSelect);

        //Getting texts from web elements of advert
        List<String> resultList = new ArrayList<>();
        resultList.add(desiredAdvertToSelect.getAttribute("data-user-city"));
        resultList.add(desiredAdvertToSelect.getAttribute("data-room"));
        resultList.add(desiredAdvertToSelect.getAttribute("data-meter"));
        resultList.add(desiredAdvertToSelect.getAttribute("data-price"));

        //Printing out advert info
        System.out.println(resultList.toString());

        return resultList;
    }

    private WebElement getAdvertFromList(List<WebElement> webElements, Integer advertOnRow) {
        return webElements.get(advertOnRow - 1);
    }
}
