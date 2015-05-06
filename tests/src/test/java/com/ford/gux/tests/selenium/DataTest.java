package com.ford.gux.tests.selenium;


import com.ford.gux.tests.selenium.DealerDataSelenium.DealerData;
import com.ford.gux.tests.selenium.Utils.ReadXMLFile;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class DataTest {

    private static Given GIVEN;
    private static Map<String, ArrayList<String>> testResults;




    @BeforeClass
    public static void setup() throws FileNotFoundException {

        GIVEN = new Given();


    }



    @Test
    public void locationDataNameTest() throws InterruptedException, FileNotFoundException {
        printConsoleToFile("locationDataNameTest");

        System.out.println("Location Test");

        this.testResults = new HashMap();
        String resultText = null;
       String currentDealerName = "";
        String currentDealerPostcode = "";
        String currentEntityID = "";



        String countryToTest = "Germany";
        DealerLocatorPage dealerLocatorPage = GIVEN.iamOnAGermanDealerLocatorPage();
        dealerLocatorPage.onADesktopView();
        String postcode;

        dealerLocatorPage.waitForpageToLoad();

        NodeList dealerList = ReadXMLFile.readXML(System.getProperty("user.dir")+ "/Resources/DealerData/bingmapsDataloadTransition_Thursday30-all-markets.xml");


        dealerLocatorPage.onADesktopView();

        if(null != dealerList) {

            for (int temp = 0 ; temp < dealerList.getLength(); temp++) {

                Node nNode = dealerList.item(temp);
                if (nNode.getNodeType() == Node.ELEMENT_NODE) {
                    Element eElement = (Element) nNode;

                    String country = eElement.getElementsByTagName("Country").item(0).getTextContent();


                    if (countryToTest.equalsIgnoreCase(country)) {

                        try {
                            currentEntityID = eElement.getElementsByTagName("EntityID").item(0).getTextContent();
                            currentDealerName = eElement.getElementsByTagName("DealerName").item(0).getTextContent();
                            currentDealerPostcode = eElement.getElementsByTagName("PostCode").item(0).getTextContent();
                        } catch (NullPointerException e){
                            currentDealerPostcode = "";
                            addValues(currentDealerName, "Fail : Postcode is empty for '" + currentDealerPostcode + "' in the results");

                        }



                        System.out.println("Test No. " + temp + " : EntityId: " + currentEntityID + " : Location : " + currentDealerPostcode + "\t" + ": Dealer : " + currentDealerName + " : Country : "+country);

                        dealerLocatorPage.clickLocationRadioButton();
                        dealerLocatorPage.enterIntoInputBoxWithoutSubmitting(currentDealerPostcode);

                        try {
                            dealerLocatorPage.clickSubmit();
                            dealerLocatorPage.waitForSubmitSpinnerToStop();
                            dealerLocatorPage.waitForResultsToBeDisplayed();

                            resultText = dealerLocatorPage.getResultText();

                        } catch (Exception e) {
                            for (int j = 1; j < 3; j++) {
                                Thread.sleep(1500);
                                resultText = dealerLocatorPage.getResultText();
                                addValues(currentDealerPostcode, "Fail: Results NOT present");
                                dealerLocatorPage.clickDealerRadioButton();
                                dealerLocatorPage.clickLocationRadioButton();


                            }

                        }
                        if (resultText.contains(currentDealerName)) {

                            addValues(currentDealerName, "Pass");
                            dealerLocatorPage.clickDealerRadioButton();
                        } else {
                            // If dealer Name not found on the page THEN .... Check after showing more
                            if (dealerLocatorPage.isShowMorePresent()) {
                                addValues(currentDealerName, dealerLocatorPage.checkTextIsPresentOnListAfterShowingMore(currentDealerName));
                                dealerLocatorPage.clickDealerRadioButton();
                                dealerLocatorPage.clickLocationRadioButton();

                            } else {
                                addValues(currentDealerName, "Fail : could not find '" + currentDealerPostcode + "' in the results");
                                dealerLocatorPage.clickDealerRadioButton();
                                dealerLocatorPage.clickLocationRadioButton();
                            }

                        }
                        System.out.println(testResults.get(currentDealerName).toString().replace(", ", "\r\n").replace("{", "").replace("}", ""));
                        System.out.println("------------------------------------------------------------");


                    }

                }
            }
        }else{
            System.out.println("Null dealer list from xml");
        }
        printAllFails(testResults);


    }


    @Test
    public void dealerDataNameTest() throws InterruptedException, FileNotFoundException {
        printConsoleToFile("dealerDataNameTest");
        System.out.println("Dealer Name Test");


        this.testResults = new HashMap();
        String resultText = null;
        String currentDealerName = "";
        String currentDealerPostcode = "";
        String currentEntityID = "";

        String countryToTest = "Germany";
        DealerLocatorPage dealerLocatorPage = GIVEN.iamOnAGermanDealerLocatorPage();

        dealerLocatorPage.waitForpageToLoad();
        NodeList dealerList = ReadXMLFile.readXML(System.getProperty("user.dir")+ "/Resources/DealerData/bingmapsDataloadTransition_Thursday30-all-markets.xml");



        if (null != dealerList) {
            for (int temp = 0 ; temp < dealerList.getLength(); temp++) {

                Node nNode = dealerList.item(temp);
                if (nNode.getNodeType() == Node.ELEMENT_NODE) {
                    Element eElement = (Element) nNode;

                    String country = eElement.getElementsByTagName("Country").item(0).getTextContent();

                    if (countryToTest.equalsIgnoreCase(country)) {
                        try {
                            currentEntityID = eElement.getElementsByTagName("EntityID").item(0).getTextContent();
                            currentDealerName = eElement.getElementsByTagName("DealerName").item(0).getTextContent();
                            currentDealerPostcode = eElement.getElementsByTagName("PostCode").item(0).getTextContent();
                        } catch (NullPointerException e){
                            currentDealerPostcode = "";
                            addValues(currentDealerName, "Fail : Postcode is empty for '" + currentDealerPostcode + "' in the results");

                        }


                        System.out.println("Test No. " + temp + " : EntityId: " + currentEntityID + " : Dealer : " + currentDealerName + "\t" + "\t" + " : Location : " + currentDealerPostcode + " : Country : " + country);

                        dealerLocatorPage.clickDealerRadioButton();
                        dealerLocatorPage.enterIntoInputBoxWithoutSubmitting(currentDealerName);

                        try {
                            dealerLocatorPage.clickSubmit();
                            dealerLocatorPage.waitForSubmitSpinnerToStop();
                            dealerLocatorPage.waitForResultsToBeDisplayed();

                            resultText = dealerLocatorPage.getResultText();
                        } catch (Exception e) {
                            for (int j = 1; j < 3; j++) {
                                Thread.sleep(1500);
                                resultText = dealerLocatorPage.getResultText();

                                addValues(currentDealerName, "Fail: Results NOT present");

                                dealerLocatorPage.clickLocationRadioButton();
                                dealerLocatorPage.clickLocationRadioButton();


                            }

                        }
                        if (resultText.contains(currentDealerPostcode)) {

                            addValues(currentDealerName, "Pass");
                            dealerLocatorPage.clickLocationRadioButton();
                        } else {
                            // If dealer postcode not found on the page THEN .... Check after showing more
                            if (dealerLocatorPage.isShowMorePresent()) {
                                addValues(currentDealerName, dealerLocatorPage.checkTextIsPresentOnListAfterShowingMore(currentDealerPostcode));
                                dealerLocatorPage.clickLocationRadioButton();
                                dealerLocatorPage.clickLocationRadioButton();

                            } else {
                                addValues(currentDealerName, "Fail : could not find '" + currentDealerPostcode + "' in the results");
                                dealerLocatorPage.clickLocationRadioButton();
                                dealerLocatorPage.clickLocationRadioButton();
                            }

                        }
                        System.out.println(testResults.get(currentDealerName).toString().replace(", ", "\r\n").replace("{", "").replace("}", ""));
                        System.out.println("------------------------------------------------------------");


                    }



                }
            }
        }else {
            System.out.println("Null dealer list from xml");
        }
        printAllFails(testResults);
    }

    private void printAllFails(Map<String, ArrayList<String>> testResults) {

        boolean hasFails = false;

        for (String result : testResults.keySet()) {
            if (result.contains("Fail")) {
                hasFails = true;
            }
        }
        if (hasFails = true) {
            System.out.println("-------------------------- FAILS ------------------------------------");

            for (Map.Entry<String, ArrayList<String>> entry : testResults.entrySet()) {
                String dealerName = entry.getKey();
                String testResult = String.valueOf(entry.getValue());

                if (testResult.contains("Fail")) {
                    System.out.println(dealerName);

                }
            }


        }


    }


    @Test
    public void printNumberOfDealersAndLocations() throws InterruptedException, FileNotFoundException {

        String countryToTest = "France";
        DealerLocatorPage dealerLocatorPage = GIVEN.iamOnAFrenchDealerLocatorPage();

        dealerLocatorPage.waitForpageToLoad();
        NodeList dealerList = ReadXMLFile.readXML("C:/Users/ALEWI143/Desktop/data-test/04 -Thu 17 Apr/googleDataLoad_Thursday/bingmapsDataloadTransition_Thursday.xml");


        if (null != dealerList) {
            for (int temp = 0; temp < dealerList.getLength(); temp++) {

                Node nNode = dealerList.item(temp);
                if (nNode.getNodeType() == Node.ELEMENT_NODE) {
                    Element eElement = (Element) nNode;

                    String country = eElement.getElementsByTagName("Country").item(0).getTextContent();

                    if (countryToTest.equalsIgnoreCase(country)) {
                        String currentEntityID = eElement.getElementsByTagName("EntityID").item(0).getTextContent();
                        String currentDealerName = eElement.getElementsByTagName("DealerName").item(0).getTextContent();
                        String currentDealerPostcode = eElement.getElementsByTagName("PostCode").item(0).getTextContent();

                    }
                }
            }
        }


        printConsoleToFile("printNumberOfDealersAndLocations");
        this.testResults = new HashMap();
        Boolean disambiguationPresent;
        String resultText = null;
        String currentDealerName = "";
        String currentDealerPostcode = "";
        String currentDealerLocality = "";

        DealerData dealerData = new DealerData("IT");

        int noOfDealers = dealerData.getNames().length;
        String[] dealerNames = dealerData.getNames();
        String[] dealerLocations = dealerData.getPostcodes();
        String[] entityIds = dealerData.getEntityID();

        System.out.println("EntityIds: " + entityIds.length);
        System.out.println("Number of Dealers: " + noOfDealers);
        System.out.println("Number of Locations: " + dealerLocations.length);


    }


    @AfterClass
    public static void tearDown() {
        GIVEN.tearDown();
        DealerLocatorPage.tearDown();
        System.setOut(null);
        System.setErr(null);
    }


    private void addValues(String key, String value) {
        ArrayList tempList = null;
        if (testResults.containsKey(key)) {
            tempList = testResults.get(key);
            if (tempList == null)
                tempList = new ArrayList();
            tempList.add(value);
        } else {
            tempList = new ArrayList();
            tempList.add(value);
        }
        testResults.put(key, tempList);
    }

    public void printConsoleToFile(String testName) throws FileNotFoundException {

        PrintStream out = new PrintStream(new FileOutputStream(System.getProperty("user.dir") + "/target/test-results/test-results-" + testName + ".txt"));
        System.setOut(out);


    }

}


