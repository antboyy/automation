package com.ford.gux.tests.selenium;

import org.openqa.selenium.*;
import org.openqa.selenium.interactions.touch.TouchActions;

import java.util.ArrayList;
import java.util.HashMap;
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
            return driver.findElement(byResultsList).getText();
        } catch (NoSuchElementException e) {
           System.out.println(e.toString());

            return e.toString();
        }
    }

    public void clickShowMoreDealers() throws InterruptedException {
        driver.findElement(byShowMoreDealers).click();
        Thread.sleep(500);
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

    public void enterIntoInputBoxWithoutSubmitting(String inputText) throws InterruptedException {
        WaitHelpers.waitForElementToDisplayOnScreen(driver, By.cssSelector("div.columns.premium-filters"));
        driver.findElement(bySearchBox).sendKeys(inputText);
        Thread.sleep(500);
        driver.findElement(bySearchBox).sendKeys(Keys.ARROW_RIGHT);

        Thread.sleep(1000);
}


    public void onAMobileView() {
        driver.manage().window().setSize(new Dimension(320, 568));
    }

    public void onADesktopView() {
        driver.manage().window().setSize(new Dimension(1600, 1100));
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

    public void waitForResultsToBeDisplayed() throws InterruptedException {
        try {
            WaitHelpers.waitForElementToBeDisplayed(driver, byResultsList);
        }catch (TimeoutException e){
//            System.out.println("TimeoutException");
            Thread.sleep(1500);
        }
    }

    public String getNameOfFirstDealerInResultList() {
        return driver.findElement(byFirstDealerName).getText();
    }

    public String getNameOfDealerInDetailsView() {
        return driver.findElement(byDealerNameOnDetailsView).getText();
    }

    public String getPostcodeofFirstDealerInResultsList() {
        return driver.findElement(byFirstDealerPostcode).getText();
    }
    public String getPostcodeofDealerInDetailsView() {
        return driver.findElement(byDealerDetailsPostCode).getText();
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
        Thread.sleep(2000);
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
        WaitHelpers.waitForElementToDisplayOnScreen(driver,byMustangFilter);
        driver.findElement(byMustangFilter).click();

    }

    public void touchAutocompleteOption(String s) {}

    private void touch(By byCSSSelctor){

            JavascriptExecutor executor = (JavascriptExecutor) driver;
            executor.executeScript("");

        }

    public int getNumberOfMapPins() {
        return driver.findElements(byMapPin).size();

    }

    public void clearInputBox() {
        driver.findElement(bySearchBox).clear();

    }

    public void waitForMapPinsToRender() throws InterruptedException {
        Thread.sleep(2500);
        WaitHelpers.waitForElementToDisplayOnScreen(driver, byMapPin);
        WaitHelpers.waitForElementToDisplayOnScreen(driver,byMapZoomOut);
        driver.findElement(byMapZoomOut).click();
        Thread.sleep(1000);
        driver.findElement(byMapZoomOut).click();
        Thread.sleep(1000);
    }

    public void swipeMap() {

        WebElement pages = driver.findElement(byMap);
        TouchActions flick = new TouchActions(driver).flick(pages, 500, 50, 50);
        flick.perform();

    }

    public void swipe() {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        HashMap< String, Double > swipeObject = new HashMap < String, Double > ();
        swipeObject.put("startX", 500.95);
        swipeObject.put("startY", 200.5);
        swipeObject.put("endX", 0.05);
        swipeObject.put("endY", 0.5);
        swipeObject.put("duration", 1.0);
        js.executeScript("emulator: swipe", swipeObject);
    }

    public void clickPinIndexNumber(int indexOfNumberOfPin) {
        String xpath = "(//div[@class='map-marker'])["+indexOfNumberOfPin+"]";
        driver.findElement(By.xpath(xpath)).click();

    }

    public String getInputBoxText() {
        JavascriptExecutor executor = (JavascriptExecutor) driver;
        String inputBoxValue = (String) executor.executeScript("return $('input#search-field.ng-valid.ng-dirty').val();");
        return inputBoxValue;

    }

    public void clickSearchGeoLocation() {
        WaitHelpers.waitForElementToDisplayOnScreen(driver,byCurrentLocationButton);
        driver.findElement(byCurrentLocationButton).click();
    }

    public void clickPinExitButton() {
        WaitHelpers.waitForElementToDisplayOnScreen(driver,byPinExitButton);
        driver.findElement(byPinExitButton).click();
    }

    public void clickResultsHeader() throws InterruptedException {
        WaitHelpers.waitForElementToDisplayOnScreen(driver,byResultsHeader);
        driver.findElement(byResultsHeader).click();
        waitForResultsToBeDisplayed();
    }

    public void clickDealerIndexNumber(int indexOfDealer) {
        String xpath = "(//span[@class='icon-details'])[" + indexOfDealer + "]";
        driver.findElement(By.xpath(xpath)).click();
    }

    public void clickHighlightedMapPin() {
        WebElement element = driver.findElement(byHighlightedMapPin);
        element.click();

    }

    public boolean mapPinExitButtonIsDisplayed() throws InterruptedException {
        Thread.sleep(1500);
        try {
            return driver.findElement(byPinExitButton).isDisplayed();
        }catch(NoSuchElementException e){
            return false;
        }
    }

    public void waitForResultsToChangeAfterClickingSubmit() {

        try {
            if (driver.findElement(byResultsList).isDisplayed()) {

                WebDriverAction action = new WebDriverAction() {
                    @Override
                    public void perform() {
                        clickSubmit();
                    }
                };
                WaitHelpers.waitForElementTextToChangeAfter(action, byResultsList, driver);

            }
        }catch(NoSuchElementException e){
            clickSubmit();
            WaitHelpers.waitForElementToBeDisplayed(driver, byResultsList);

        }

    }

    public void waitForSubmitSpinnerToStop() throws InterruptedException {
        try {
            WaitHelpers.waitForElementToBeHidden(bySpinnerGIF, driver);
        }catch (NoSuchElementException e){
            Thread.sleep(1000);
            System.out.println("No GIF Spinner -- waiting instead");
        }
    }

    public String checkTextIsPresentOnListAfterShowingMore(String textToCheck) throws InterruptedException {
        try {
            int showMoreCounter = 1;

            while (!(checkTextIsPresentInList(textToCheck)) && isShowMorePresent() /*&& !(showMoreCounter > 5)*/ && checkResultsAreDisplayed()) {
//                        System.out.println("clicking show more No. " + showMoreCounter);
                try {
                    clickShowMoreDealers();
                } catch (ElementNotVisibleException e) {
//                            System.out.println("couldn't find show more button to click " + e);

                }
                showMoreCounter++;

            }
            if (checkTextIsPresentInList(textToCheck)) {
                return "Pass";
            } else {

//                        System.out.println("XXXXXXXX FAIL XXXXXXXX");
//                System.out.println("Test No. " + i +" ::Dealer name : "+ currentDealerName + " :: " + "Dealer postcode : "+ currentDealerLocationInfo);
//                        System.out.println("XXXXXXXX FAIL XXXXXXXX");

                if (!checkResultsAreDisplayed()) {
                    return "No Results";
                } else {
                    return "Fail";
//                            System.out.println("Failed on page : " + showMoreCounter);
                }

            }

        } catch (NoSuchElementException e) {
//                    System.out.println("Couldn't find element: "+e);

        } return "null";
    }




    public boolean checkTextIsPresentInList(String textToCheck) {
        try {
            return driver.findElement(byResultsList).getText().contains(textToCheck);
        }catch (NoSuchElementException e){
            return false;
        }
    }


    public boolean isShowMorePresent() {

            try {
                return driver.findElement(byShowMoreDealers).isDisplayed();
            }catch (NoSuchElementException e){
                return false;
            }

        }
    public boolean checkResultsAreDisplayed() {
        try {
            return driver.findElement(byResultsList).isDisplayed();
        }catch (NoSuchElementException e){
            return false;
        }
    }

}
