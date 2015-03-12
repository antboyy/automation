package com.ford.gux.tests.selenium;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.remote.SessionNotFoundException;

import java.util.ArrayList;
import java.util.List;


public class DealerLocatorPage {


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

    public DealerLocatorPage(WebDriver driver) {
        this.driver = driver;
    }

    public void go(String url) throws InterruptedException {
        driver.navigate().to(url);
//        WaitHelpers.waitForElementToBeDisplayed(driver, byCarouselNext);

    }


    public void clickDealerRadioButton() {
        driver.findElement(By.xpath("(//span[@class='radio-button'])[2]")).click();
    }

    public void clickLocationRadioButton() {
        driver.findElement(By.xpath("//span[@class='radio-button']")).click();
    }

    public String getResultText() {
        try {
            return driver.findElement(By.xpath("//div[@class='row result ng-scope']")).getText();
        } catch (NoSuchElementException e) {
            return e.toString();
        }
    }

    public void clickShowMoreDealers() {
        driver.findElement(byShowMoreDealers).click();
    }


    public void clickSubmit() {
        driver.findElement(By.id("search-field")).submit();
    }

    public void enterIntoInputBox(String inputText) throws InterruptedException {
        WaitHelpers.waitForElementToDisplayOnScreen(driver, By.cssSelector("div.columns.premium-filters"));
        driver.findElement(bySearchBox).sendKeys(inputText);
        Thread.sleep(200);
        driver.findElement(bySearchBox).submit();
        driver.findElement(By.cssSelector("button.search-submit")).submit();

    }


    public void onAMobileView() {
        driver.manage().window().setSize(new Dimension(320, 568));
    }

    public void onADesktopView() {
        driver.manage().window().setSize(new Dimension(1500, 1000));
    }

    public static void tearDown() {
        try{
        if(driver!=null) {
            driver.close();
            driver.quit();
        }}catch(Exception e){
            System.out.print(e);

        }
    }

    public int countNumberOfMobileElementsVisible() {
        int numberOfMobileElementsVisible = 0;
        try {
            if (driver.findElement(byMyLocationButton).isDisplayed()) {
                numberOfMobileElementsVisible++;
            }
        } catch (NoSuchElementException e) {
        }
        return numberOfMobileElementsVisible;
    }

    public void waitForResultsToBeDisplayed() {


        WaitHelpers.waitForElementToBeDisplayed(driver, byResultsList);

    }

    public String getNameOfFirstDealer() {
        return driver.findElement(byFirstDealerName).getText();
    }

    public String getPostcodeofFirstDealer() {
        return driver.findElement(byFirstDealerPostcode).getText();
    }

    public void waitForpageToLoad() {
        WaitHelpers.waitForElementToDisplayOnScreen(driver, bySearchBox);

    }

    public void waitForNotSupportedPageToLoad() {
        WaitHelpers.waitForElementToDisplayOnScreen(driver, byLogo);

    }

    public void clickDisambiguationWithName(String locationName) {
        WaitHelpers.waitForElementToDisplayOnScreen(driver, byAutocompleteList);
        if (driver.findElement(byAutocompleteList).getText().contains(locationName)) {
            String xpath = "//a[contains(text(),\"" + locationName + "\")]";
            driver.findElement(By.xpath(xpath)).click();
            WaitHelpers.waitForElementToBeDisplayed(driver, byResultsList);
        }


    }

    public void clickFilter() throws InterruptedException {
        Thread.sleep(700);
        driver.findElement(byFilterButton).click();
        Thread.sleep(700);

    }

    public void clickServicesFilter() throws InterruptedException {
        WaitHelpers.waitForElementToBeDisplayed(driver, byServicesFilter);
        driver.findElement(byServicesFilter).click();
    }

    public void selectFilterOption(String filterName) throws InterruptedException {
        String xpath = "//label[contains(text(),\"" + filterName + "\")]";
        Thread.sleep(1500);

        driver.findElement(By.xpath(xpath)).click();

    }

    public void clickFilterSubmit() throws InterruptedException {
        Thread.sleep(1500);
        driver.findElement(byFilterSubmit).click();
//        WaitHelpers.waitForElementToBeHidden(byFilterSubmit,driver);

        Thread.sleep(3000);

    }

    public List<String> getListOfDealersText() throws InterruptedException {
        //Aim to get all of the dealer cards text into an array
        Thread.sleep(1500);
        List<WebElement> cards = new ArrayList<WebElement>();
        ArrayList<String> cardsText = new ArrayList<String>();
        cardsText.add(0, "");
        waitForResultsToBeDisplayed();
        cards = driver.findElements(byDealerCard);
        int numberOfCards = cards.size();
        for (int i = 1; i < numberOfCards + 1; i++) {
            Thread.sleep(500);

            String xpath = "//div[@data-ng-attr-id=\"{{ 'dealer-' + dealer.EntityID }}\"][" + i + "]";
            cardsText.add(i, driver.findElement(By.xpath(xpath)).getText());
        }
        return cardsText;
    }

    public int numberOfDealersWithServicesPresent(String service) throws InterruptedException {
        int servicesPresent = 0;
        int servicesNotPresent = 0;
        for (int i = 1; i < driver.findElements(By.xpath("(//span[@class='icon-details'])")).size()+1; i++) {

            String xpath = "(//span[@class='icon-details'])[" + i + "]";
            WaitHelpers.waitForElementToDisplayOnScreen(driver, By.xpath(xpath));
            Thread.sleep(1000);
            driver.findElement(By.xpath(xpath)).click();


            for (int j = 1; j < driver.findElements(byServicesContent).size()+1; j++) {
                String xpathServices = "(//p[@class='dealer-service ng-scope ng-binding'])[" + j + "]";
                if (driver.findElement(By.xpath(xpathServices)).getText().contains(service)) {
                    servicesPresent++;
                }


            }
            Thread.sleep(1000);
            clickBackToResults();
            Thread.sleep(1000);
        }
        return servicesPresent;
    }

    public void selectDistanceOption(String distance) throws InterruptedException {
        Thread.sleep(500);

        driver.findElement(byDistanceDropDown).sendKeys(distance);
        driver.findElement(byDistanceDropDown).sendKeys(Keys.TAB);
        }

    public void clickBackToResults() {
        WaitHelpers.waitForElementToDisplayOnScreen(driver,byBackToResults);
        driver.findElement(byBackToResults).click();
        WaitHelpers.waitForElementToBeHidden(By.cssSelector("img.search-loading"), driver);
    }

    public void clickDistanceFilter() throws InterruptedException {
//        WaitHelpers.waitForElementToDisplayOnScreen(driver,byDistanceFilter);
        WaitHelpers.waitForElementToBeDisplayed(driver,byDistanceFilter);
//        WaitHelpers.waitForElementToBeHidden(byDealerName,driver);
        Thread.sleep(2000);
        driver.findElement(byDistanceFilter).click();
    }

    public boolean areAllDealersCloserThanSpecifiedDistance(int distance) throws InterruptedException {
        int numberOfDealersCloserThanSpecifiedDistance = 0;
        WaitHelpers.waitForElementToBeDisplayed(driver, By.xpath("(//span[@class='icon-details'])"));
        for (int i = 1; i < driver.findElements(By.xpath("(//span[@class='icon-details'])")).size()+1; i++) {

            String xpath = "(//span[@class='icon-details'])[" + i + "]";
            Thread.sleep(500);
            driver.findElement(By.xpath(xpath)).click();

            //inside the dealer details this is the testing bit
                String xpathDistance = "p.dl-distance.ng-binding";
                double milesOnScreen = Double.parseDouble(driver.findElement(By.cssSelector(xpathDistance)).getText().replace("miles","").trim());
                if (milesOnScreen<distance){
                    numberOfDealersCloserThanSpecifiedDistance++;
                }

            Thread.sleep(500);
            clickBackToResults();
            Thread.sleep(500);
                }
        if(numberOfDealersCloserThanSpecifiedDistance==driver.findElements(By.xpath("(//span[@class='icon-details'])")).size()){
            return true;
        }else return false;
    }

    public int getNumberOfResults() {
        return driver.findElements(By.xpath("(//span[@class='icon-details'])")).size();

    }


    public int countNumberOfDesktopElementsVisible() {
        int numberOfDesktopElementsVisible = 0;

        try {if(driver.findElement(By.cssSelector("div.dl-heading")).isDisplayed()){
            numberOfDesktopElementsVisible++;
        }}catch (NoSuchElementException e){}


        return numberOfDesktopElementsVisible;

    }

/*    public void setBrowserType() {

        this.driver = setInternetExplorerUserAgent();
    }

    private ChromeDriver setInternetExplorerUserAgent() {

        List<String> switchesList = new ArrayList<String>();

        switchesList.add("--User-Agent=Mozilla/4.0 (compatible; MSIE 8.0; Windows NT 6.0; Trident/4.0)");
        switchesList.add("--Accept-Language=en-GB");
        switchesList.add("--disable-logging");


        ChromeOptions options = new ChromeOptions();
        options.addArguments(switchesList);
        return new ChromeDriver(options);

    }*/


    public String getHeadingText() {
        return driver.findElement(By.xpath("//div[@class='columns']/h1")).getText();
    }

    public String getErrorText() throws InterruptedException {

        WaitHelpers.waitForElementToDisplayOnScreen(driver,byErrorMessage);
        return driver.findElement(byErrorMessage).getText();
    }

    public void selectMustangFilter() {
        driver.findElement(byMustangFilter).click();

    }
}
