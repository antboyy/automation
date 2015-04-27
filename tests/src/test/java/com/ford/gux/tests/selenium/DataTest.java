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
        System.out.println("Dealer Name Test");

        this.testResults = new HashMap();
        Boolean disambiguationPresent;
        String resultText = null;
        String currentDealerName = "";
        String currentDealerPostcode = "";
        String currentDealerLocality = "";

        DealerData dealerData = new DealerData("ES");
        DealerLocatorPage dealerLocatorPage = GIVEN.iamOnASpanishDealerLocatorPage();


        dealerLocatorPage.waitForpageToLoad();

        int noOfDealers = dealerData.getNames().length;
        String [] dealerNames = dealerData.getNames();
        String [] dealerLocations = dealerData.getPostcodes();


        for (int i=0 ; i< noOfDealers ;i++) {
//        for (int i=0 ; i< 5;i++) {

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

                    /*if(currentDealerName.)testResults.

*/

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
            System.out.println(testResults.get(currentDealerName).toString().replace(", ","\r\n").replace("{","").replace("}",""));
            System.out.println("------------------------------------------------------------");



        }

        printAllFails(testResults);



    }


    @Test
    public void locationDataNameTest() throws InterruptedException {
        System.out.println("Location Test");

        this.testResults = new HashMap();
        Boolean disambiguationPresent;
        String resultText = null;
        String currentDealerName = "";
        String currentDealerPostcode = "";
        String currentDealerLocality = "";

        DealerData dealerData = new DealerData("ES");
        DealerLocatorPage dealerLocatorPage = GIVEN.iamOnASpanishDealerLocatorPage();
        dealerLocatorPage.onADesktopView();


        dealerLocatorPage.waitForpageToLoad();

        int noOfDealers = dealerData.getNames().length;
        String [] dealerNames = dealerData.getNames();
        String [] dealerLocations = dealerData.getPostcodes();
        dealerLocatorPage.onADesktopView();


        for (int i=0 ; i< noOfDealers ;i++) {
//        for (int i=0 ; i< 5 ;i++) {

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
            if (resultText.contains(currentDealerName)){

                testResults.put(currentDealerName, "Pass");
                dealerLocatorPage.clickDealerRadioButton();
            }
            else {
                // If dealer Name not found on the page THEN .... Check after showing more
                if(dealerLocatorPage.isShowMorePresent()){
                    testResults.put(currentDealerName,dealerLocatorPage.checkTextIsPresentOnListAfterShowingMore(currentDealerName));
                    dealerLocatorPage.clickDealerRadioButton();
                    dealerLocatorPage.clickLocationRadioButton();

                }else {
                    testResults.put(currentDealerName,"Fail : could not find '"+currentDealerPostcode+"' in the results");
                    dealerLocatorPage.clickDealerRadioButton();
                    dealerLocatorPage.clickLocationRadioButton();
                }

            }
            System.out.println(testResults.get(currentDealerName).toString().replace(", ","\r\n").replace("{","").replace("}",""));
            System.out.println("------------------------------------------------------------");



        }

        printAllFails(testResults);



        }

    private void printAllFails(Map<String, String> testResults) {

        boolean hasFails = false;

        for(String result : testResults.keySet()){
            if(result.contains("Fail")) {
                hasFails = true;
            }
        }
        if (hasFails=true){
            System.out.println("-------------------------- FAILS ------------------------------------");

            for ( Map.Entry<String, String> entry : testResults.entrySet()) {
                String dealerName = entry.getKey();
                String testResult = entry.getValue();

                if (testResult.contains("Fail")) {
                    System.out.println(dealerName);


                }
            }


        }


    }




    @Ignore
    @Test
    public void printNumberOfDealersAndLocations() throws InterruptedException {
        this.testResults = new HashMap();
        Boolean disambiguationPresent;
        String resultText = null;
        String currentDealerName = "";
        String currentDealerPostcode = "";
        String currentDealerLocality = "";

        DealerData dealerData = new DealerData("DE");

        int noOfDealers = dealerData.getNames().length;
        String [] dealerNames = dealerData.getNames();
        String [] dealerLocations = dealerData.getPostcodes();

        System.out.println("Number of Dealers: "+ noOfDealers);
        System.out.println("Number of Locations: "+ dealerLocations.length);

    }


    /*@Test
    public void dataLoad() throws InterruptedException {
        System.out.println("Location Test");
        DealerData data = new DealerData("DE");


    }*/



    @AfterClass
    public static void tearDown() {
        GIVEN.tearDown();
        DealerLocatorPage.tearDown();
    }





}


