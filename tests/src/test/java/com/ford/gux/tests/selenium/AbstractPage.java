package com.ford.gux.tests.selenium;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

/**
 * Created by alewi143 on 28/04/2015.
 */
public class AbstractPage {

    static WebDriver driver ;

    private By byShowMoreDealers = By.xpath("//button[@data-ng-click='showMoreDealers()']");
    private By byResultsList = By.xpath("//div[@class=\"columns large-12 dl-results-list\"]");
    private By byFirstDealerName = By.xpath("(//h3[@class='dl-dealer-name'])[1]");
    private By byDealerName = By.xpath("//h3[@class='dl-dealer-name']");
    private By byFirstDealerPostcode = By.xpath("(//p[@data-ng-show='dealer.PostCode'])[1]");
    private By bySearchBox = By.id("search-field");
    private By byAutocompleteList = By.xpath("//ul[@class='dealer-autocomplete-list']");
    private By byFilterButton = By.xpath("//span[@class='icon-filter']");
    private By byDistanceFilter = By.xpath("(//div/h5)[1]");
    private By byServicesFilter = By.xpath("(//div/h5)[2]");
    private By byFilterSubmit = By.xpath("//button[@data-ng-click='updateFilters()']");
    private By byDealerCard = By.xpath("//div[@class='row result ng-scope']");
    private By byServicesContent = By.xpath("//p[@class='dealer-service ng-scope ng-binding']");
    private By byBackToResults = By.xpath("//span[@class='icon-chevron-thin-left']");
    private By byDistanceDropDown = By.id("distances");
    private By byMyLocationButton = By.className("icon-get-location");
    private By byDesktopPageHeading = By.xpath("//div[@class='columns']/h1");
    private By byLogo = By.id("logo");
    private By byErrorMessage = By.xpath("//p[@data-ng-bind='dealerlocator.errorMessage']");
    private By byMustangFilter = By.xpath("//label[@for='premium-filter']");
    private By byDealerNameOnDetailsView = By.xpath("//h3[@class='dl-dealer-name dl-dealer-name-details ng-binding']");
    private By byDealerDetailsPostCode = By.xpath("//p[@class='dl-address-line ng-binding'][4]");
    private By byMapPin = By.xpath("//div[@class='map-marker']");
    private By byMap = By.xpath("//*[@id=\"map\"]/div/div[1]/div[2]");
    private By byCurrentLocationButton = By.xpath("//button[@data-ng-click='useCurrentLocation($event)']");
    private By byPinExitButton = By.xpath("//a[@class='close-info-window']");
    private By byResultsHeader = By.cssSelector("div.dl-results-header");
    private By byHighlightedMapPin = By.xpath("//img[@src='/cs/globalux/img/map-marker-active.png']/..");
    private By byMapZoomOut = By.xpath("//*[@id=\"map\"]/div/div[8]/div[3]/div[4]");
    private By bySpinnerGIF = By.cssSelector("img.search-loading");

    public AbstractPage() {
        this.driver = driver;
    }

    public AbstractPage(WebDriver driver) {
        this.driver = driver;
    }


    public void go(String url) throws InterruptedException {
        driver.navigate().to(url);
//        WaitHelpers.waitForElementToBeDisplayed(driver, byCarouselNext);

    }


    public void waitForURLToChangeFrom(String startURL) {
        WaitHelpers.waitForUrlToChangeFrom(startURL,driver);
    }

    public String getCurrentURL() {
        return driver.getCurrentUrl();
    }

    public DealerLocatorPage clickOnLinkThatContainsText(String textInLink) throws InterruptedException {
        driver.findElement(By.xpath("//*[contains(text(),'"+textInLink+"')]")).click();
        Thread.sleep(2000);
        return new DealerLocatorPage(driver);
    }
}