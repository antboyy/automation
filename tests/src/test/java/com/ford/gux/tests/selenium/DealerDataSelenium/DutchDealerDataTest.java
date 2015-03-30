package com.ford.gux.tests.selenium.DealerDataSelenium;

import com.ford.gux.tests.selenium.WaitHelpers;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.ElementNotVisibleException;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;


public class DutchDealerDataTest {
    WebDriver driver = new ChromeDriver();
    DealerNamesHelper dealerNamesObject = new DealerNamesHelper();
    DealerPostcodeHelper dealerPostcodeObject = new DealerPostcodeHelper();
//    DealerLocationHelper dealerLocationObject = new DealerLocationHelper();
    DealerLocalityHelper dealerLocalityHelper = new DealerLocalityHelper();

    private By byAutoCompleteBox = By.xpath("//a[@data-ng-click='locationSearch(location, $event)']");
    private By byDisambiguationBox = By.cssSelector("li.ng-scope");
    private By byAutocompleteList = By.xpath("//ul[@class='dealer-autocomplete-list']");
    private By byResultsList = By.xpath("//div[@class=\"columns large-12 dl-results-list\"]");
    private By byShowMoreDealers = By.xpath("//button[@data-ng-click='showMoreDealers()']");

    int noOfDealers;
    Boolean disambiguationPresent;


    String resultText = null;
    String currentDealerName = "";
    String currentDealerPostcode = "";
    String currentDealerLocality = "";



    @Test
    public void dealerNameAndLocationTest() throws InterruptedException {


        noOfDealers = dealerNamesObject.germanDealerNames1.length;
        Thread.sleep(4000);
        String resultText = null;
        System.out.println("Testing : " + noOfDealers + " dealers");

//        navigate("http://m.intpublish-couk.engine.ford.com/GlobalUXDealerLocator");
//        navigate("http://m.intpublish-fr.engine.ford.com/GUXDealerLocator");

        for (int i=0 ; i< noOfDealers ;i++){
            currentDealerName = dealerNamesObject.germanDealerNames2[i];
            int catchCounter = 1;
            clickDealerRadioButton();

            enterIntoInputBox(currentDealerName);
            Thread.sleep(1000);
            clickSubmit();
            Thread.sleep(4000);

            try {
                resultText = getResultText();
            } catch (NoSuchElementException e) {
                for (int j=1 ; j<3 ;j++) {
                    Thread.sleep(1500);
                    System.out.println(":::: element wasn't present , retrying ::::");
                    resultText = getResultText();
                }

            }


            if (resultText.contains(dealerPostcodeObject.germanDealerPostcode2[i])){
//                System.out.println("Test: " + currentDealerName +" is a PASS");
                clickLocationRadioButton();
            }else {

                System.out.println("FAIL on :: "+ currentDealerName);
                clickLocationRadioButton();
            }
        }

        driver.close();
        driver.quit();
    }
/*
    if

    locality is present in the disambigualtion

    click it

    else

    click first disambiguation element
*/


    @Test
    public void postcodeSearchForDealer() throws InterruptedException {
        noOfDealers = dealerNamesObject.dutchDealerNames.length;
        System.out.println("Testing : " + noOfDealers + " dealers");


//        navigate("http://m.intpublish-couk.engine.ford.com/GlobalUXDealerLocator");
//        navigate("http://m.intpublish-fr.engine.ford.com/GUXDealerLocator");
//        navigate("http://m.intpublish-de.engine.ford.com/GlobalUXDealerLocator");
        navigate("http://intpublish-nl.engine.ford.com/DealerLocatorGUX");

        //Start of test
        Thread.sleep(4000);

        //Test list of dealers
        for (int i = 16 ; i < noOfDealers ; i++) {
//            System.out.println("--> Testing No. " + i +" ::Dealer name : "+ currentDealerName );


            currentDealerName = dealerNamesObject.dutchDealerNames[i];
            currentDealerPostcode = dealerPostcodeObject.dutchDealerPostcode[i];
            currentDealerLocality = dealerLocalityHelper.dutchDealerLocality[i];
//            System.out.println("current dealername: " +currentDealerName );
//            System.out.println("current postcode: " +currentDealerPostcode );


            currentDealerPostcode = addZeroToStripedPostcode(currentDealerPostcode);

            enterIntoInputBox(currentDealerPostcode);
            clickDisambiguationIfPresent();

            if (checkDealerNameIsPresentInList(currentDealerName)) {
                //Then move to next dealer
//                System.out.println("******** PASS ********* " + currentDealerName );

            } else {
//                System.out.println("~~~~~~~~~ not visible on current list ~~~~~~~~~~~~");
                try {
                    int showMoreCounter = 1;

                    while (!(checkDealerNameIsPresentInList(currentDealerName)) && checkIfShowMoreButtonIsPresent() && !(showMoreCounter>4) && checkResultsAreDisplayed()) {
//                        System.out.println("clicking show more No. " + showMoreCounter);
                        try {
                            clickShowMoreDealers();
                        }catch (ElementNotVisibleException e) {
//                            System.out.println("couldn't find show more button to click " + e);

                        }
                        showMoreCounter++;

                    }
                    if (checkDealerNameIsPresentInList(currentDealerName)) {
//                        System.out.println("******** PASS ********* " + currentDealerName + " was on page "+ showMoreCounter);
                    } else {
//                        System.out.println("XXXXXXXX FAIL XXXXXXXX");
                        System.out.println("Test No. " + i +" ::Dealer name : "+ currentDealerName + " :: " + "Dealer postcode : "+ currentDealerPostcode);
//                        System.out.println("XXXXXXXX FAIL XXXXXXXX");

                        if (!checkResultsAreDisplayed()){
//                            System.out.println("Failed as no results are present");
                        }else{
//                            System.out.println("Failed on page : " + showMoreCounter);
                        }

                    }

                } catch (NoSuchElementException e) {
//                    System.out.println("Couldn't find element: "+e);

                }

            }


            clickDealerRadioButton();
            clickLocationRadioButton();


        }

        driver.close();
        driver.quit();

    }

    private void clickDisambiguationIfPresent() throws InterruptedException {
        Thread.sleep(2500);
        disambiguationPresent = checkIfDisambiguationIsPresent();
        boolean localityInDisambiguation = false;
        int i = 0;
        while (disambiguationPresent && i < 5) {
            if (disambiguationPresent) {
                localityInDisambiguation = checkIfLocalityPresentInDisambiguation(currentDealerLocality);
                if (localityInDisambiguation) {
                    clickDisambiguationWithCorrectLocality(currentDealerLocality);
                } else

                    clickFirstElementOfDisambigaution();
                i++;
                localityInDisambiguation = checkIfLocalityPresentInDisambiguation(currentDealerLocality);
                disambiguationPresent = checkIfDisambiguationIsPresent();
                if (disambiguationPresent) {
                    clickFirstElementOfDisambigaution();
                    disambiguationPresent = false;
                    }
                }


            }
        }
//        clickSubmit();




    private void clickDisambiguationWithCorrectLocality(String locality) {
        if(driver.findElement(byAutocompleteList).getText().contains(locality)){
            String xpath = "//a[contains(text(),\""+locality+"\")]";
            driver.findElement(By.xpath(xpath)).click();
            WaitHelpers.waitForElementToBeDisplayed(driver, byResultsList);
        }
    }

    private boolean checkIfLocalityPresentInDisambiguation(String locality) {
        try {
            return driver.findElement(byAutocompleteList).getText().contains(locality);
        }catch (NoSuchElementException e){
            return false;
        }
    }

    private String addZeroToStripedPostcode(String currentDealerPostcode ) {
        if(currentDealerPostcode.length()<5){
            return "0"+ currentDealerPostcode;
        }else return currentDealerPostcode;

    }

    private boolean checkResultsAreDisplayed() {
        try {
            return driver.findElement(byResultsList).isDisplayed();
        }catch (NoSuchElementException e){
            return false;
        }
    }

    private boolean checkIfShowMoreButtonIsPresent() {
        try {
            return driver.findElement(byShowMoreDealers).isDisplayed();
        }catch (NoSuchElementException e){
            return false;
        }

    }



    private void clickFirstElementOfDisambigaution()throws InterruptedException{
        try {
            driver.findElement(byAutoCompleteBox).click();
             Thread.sleep(2500);
        } catch (NoSuchElementException e) {
            driver.findElement(byDisambiguationBox).click();
            Thread.sleep(2500);

        }

    }

    private boolean checkIfDisambiguationIsPresent() {

boolean disambiguationPresent = false;
        try {
            if (driver.findElement(byAutoCompleteBox).isDisplayed()){
                return true;
        }
        }catch (NoSuchElementException e) {
            disambiguationPresent = false;
        }
        try {
     if (driver.findElement(byDisambiguationBox).isDisplayed()){
         return true;
     }}catch (NoSuchElementException f ){
            return false;
            }
        return disambiguationPresent;
    }


    private boolean checkDealerNameIsPresentInList(String dealerName) {
        try {
//            return driver.findElement(By.xpath("//div[@class=\"columns large-12 dl-results-list\"]")).getText().contains(dealerName);
            return driver.findElement(byResultsList).getText().contains(dealerName);
        }catch (NoSuchElementException e){
            return false;
        }
    }


    public void clickDealerRadioButton() {
        driver.findElement(By.xpath("(//span[@class='radio-button'])[2]")).click();
    }

    public void clickLocationRadioButton() {
        driver.findElement(By.xpath("//span[@class='radio-button']")).click();
    }


    public void enterIntoInputBox(String inputText) {
        driver.findElement(By.id("search-field")).sendKeys(inputText);
        driver.findElement(By.id("search-field")).submit();
    }

    public void clickSubmit(){
        driver.findElement(By.id("search-field")).submit();
    }
    public void navigate(String url){
        driver.navigate().to(url);
    }
    public String getResultText(){
        try {
            return driver.findElement(By.xpath("//div[@class='row result ng-scope']")).getText();
        }catch (NoSuchElementException e) {
            return e.toString();
        }
    }

    public void clickShowMoreDealers() {
        driver.findElement(byShowMoreDealers).click();
    }





}
