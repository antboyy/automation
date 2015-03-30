package com.ford.gux.tests.selenium;


import com.ford.gux.tests.selenium.DealerDataSelenium.DealerData;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

public class DataTest {

        private static Given GIVEN;
        private static Map <String, String> testResults;

        @BeforeClass
        public static void setup() {
            GIVEN = new Given();
        }


    @Test
    public void dealerDataNameTest() throws InterruptedException {
        this.testResults = new HashMap();
        Boolean disambiguationPresent;
        String resultText = null;
        String currentDealerName = "";
        String currentDealerPostcode = "";
        String currentDealerLocality = "";

        DealerData dealerData = new DealerData("FR");
        DealerLocatorPage dealerLocatorPage = GIVEN.iamOnAFrenchDealerLocatorPage();


        dealerLocatorPage.waitForpageToLoad();

        int noOfDealers = dealerData.getNames().length;
        String [] dealerNames = dealerData.getNames();
        String [] dealerLocations = dealerData.getPostcodes();


        for (int i=0 ; i< noOfDealers ;i++) {
//        for (int i=500 ; i< 510 ;i++) {

            currentDealerName = dealerNames[i];
            currentDealerPostcode = dealerLocations[i];

            System.out.println("Test No. "+i+ " : Dealer : "+currentDealerName + "\t" + "\t" + " : Location : "+currentDealerPostcode);

            dealerLocatorPage.clickDealerRadioButton();
            dealerLocatorPage.enterIntoInputBoxWithoutSubmitting(currentDealerName);

            try {
                dealerLocatorPage.clickSubmit();
                dealerLocatorPage.waitForSubmitSpinnerToStop();
                dealerLocatorPage.waitForResultsToBeDisplayed();

                resultText = dealerLocatorPage.getResultText();
//                System.out.println(resultText);
            } catch (Exception e) {
                for (int j=1 ; j<3 ;j++) {
                    Thread.sleep(1500);
                    resultText = dealerLocatorPage.getResultText();
                    testResults.put(currentDealerName, "Fail: Results NOT present");
                    dealerLocatorPage.clickLocationRadioButton();
                    dealerLocatorPage.clickLocationRadioButton();


                }

            }
            if (resultText.contains(currentDealerPostcode)){

                testResults.put(currentDealerName, "Pass");
                dealerLocatorPage.clickLocationRadioButton();
            }
            else {
                // If dealer postcode not found on the page THEN .... Check after showing more
                if(dealerLocatorPage.isShowMorePresent()){
                    testResults.put(currentDealerName,dealerLocatorPage.checkTextIsPresentOnListAfterShowingMore(currentDealerPostcode));
                    dealerLocatorPage.clickLocationRadioButton();
                    dealerLocatorPage.clickLocationRadioButton();

                }else {
                    testResults.put(currentDealerName,"Fail : could not find '"+currentDealerPostcode+"' in the results");
                    dealerLocatorPage.clickLocationRadioButton();
                    dealerLocatorPage.clickLocationRadioButton();
                }

            }
        }

    }


    @Test
    public void locationDataNameTest() throws InterruptedException {
        this.testResults = new HashMap();
        Boolean disambiguationPresent;
        String resultText = null;
        String currentDealerName = "";
        String currentDealerPostcode = "";
        String currentDealerLocality = "";

        DealerData dealerData = new DealerData("FR");
        DealerLocatorPage dealerLocatorPage = GIVEN.iamOnAFrenchDealerLocatorPage();


        dealerLocatorPage.waitForpageToLoad();

        int noOfDealers = dealerData.getNames().length;
        String [] dealerNames = dealerData.getNames();
        String [] dealerLocations = dealerData.getPostcodes();


        for (int i=0 ; i< noOfDealers ;i++) {

            currentDealerName = dealerNames[i];
            currentDealerPostcode = dealerLocations[i];
//            currentDealerName = dealerNamesObject.ukDealerNames[i];
//            currentDealerPostcode = dealerPostcodeObject.ukDealerPostcode[i];

            System.out.println("Test No. "+i+ " : Location : "+currentDealerPostcode + "\t" +": Dealer : "+currentDealerName);

            dealerLocatorPage.clickLocationRadioButton();
            dealerLocatorPage.enterIntoInputBoxWithoutSubmitting(currentDealerPostcode);

            try {
//                dealerLocatorPage.waitForResultsToChangeAfterClickingSubmit();
                dealerLocatorPage.clickSubmit();
                dealerLocatorPage.waitForSubmitSpinnerToStop();
                dealerLocatorPage.waitForResultsToBeDisplayed();

                resultText = dealerLocatorPage.getResultText();

            } catch (Exception e) {
                for (int j=1 ; j<3 ;j++) {
                    Thread.sleep(1500);
                    resultText = dealerLocatorPage.getResultText();
                    testResults.put(currentDealerPostcode, "Fail: Results NOT present");
                    dealerLocatorPage.clickDealerRadioButton();
                    dealerLocatorPage.clickLocationRadioButton();


                }

            }
            if (resultText.contains(currentDealerPostcode)){

                testResults.put(currentDealerName, "Pass");
                dealerLocatorPage.clickDealerRadioButton();
            }
            else {
                // If dealer Name not found on the page THEN .... Check after showing more
                if(dealerLocatorPage.isShowMorePresent()){
                    testResults.put(currentDealerName,dealerLocatorPage.checkTextIsPresentOnListAfterShowingMore(currentDealerPostcode));
                    dealerLocatorPage.clickDealerRadioButton();
                    dealerLocatorPage.clickLocationRadioButton();

                }else {
                    testResults.put(currentDealerName,"Fail : could not find '"+currentDealerPostcode+"' in the results");
                    dealerLocatorPage.clickDealerRadioButton();
                    dealerLocatorPage.clickLocationRadioButton();
                }

            }
        }

    }






    @AfterClass
    public static void tearDown() {
        System.out.println("--------Test Results--------");
        System.out.println(testResults.toString().replace(", ","\r\n").replace("{","").replace("}",""));
        System.out.println("----------------------------");

        GIVEN.tearDown();
        DealerLocatorPage.tearDown();
    }





}


