package com.ford.gux.tests.selenium;


import com.ford.gux.tests.selenium.DealerDataSelenium.DealerData;
import com.ford.gux.tests.selenium.Utils.ReadXMLFile;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import java.io.*;
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
    public void locationDataNameTest() throws InterruptedException, FileNotFoundException, UnsupportedEncodingException {
        printConsoleToFile("locationDataNameTest");
        System.out.println("EntityID,DealerName,Dealer Number,Location,TestResult");

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
                            System.out.println("\"Fail : Postcode is empty\"");

                        }



                        System.out.print("\"" + currentEntityID + "\",\"" + currentDealerName + "\",\"" +  temp + "\",\"" + currentDealerPostcode + "\",");

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
                                System.out.println("\"Fail: Results NOT present\"");
                                dealerLocatorPage.clickDealerRadioButton();
                                dealerLocatorPage.clickLocationRadioButton();


                            }

                        }
                        if (resultText.contains(currentDealerName)) {

                            System.out.println("\"Pass\"");
                            dealerLocatorPage.clickDealerRadioButton();
                        } else {
                            // If dealer Name not found on the page THEN .... Check after showing more
                            if (dealerLocatorPage.isShowMorePresent()) {

                                if(dealerLocatorPage.checkTextIsPresentOnListAfterShowingMore(currentDealerName)!="Pass"){
                                    System.out.println("\"Fail\"");
                                }else{
                                    System.out.println("\"Pass\"");
                                }
                                dealerLocatorPage.clickDealerRadioButton();
                                dealerLocatorPage.clickLocationRadioButton();

                            } else {
                                System.out.println("\"Fail : could not find dealer in the results\"");
                                dealerLocatorPage.clickDealerRadioButton();
                                dealerLocatorPage.clickLocationRadioButton();
                            }

                        }


                    }

                }
            }
        }else{
            System.out.println("Null dealer list from xml");
        }


    }


    @Test
    public void dealerDataNameTest() throws InterruptedException, FileNotFoundException, UnsupportedEncodingException {
        printConsoleToFile("dealerDataNameTest");
        System.out.println("EntityID,DealerName,Dealer Number,Location,TestResult");


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
                            System.out.println("\"Fail : Postcode is empty\"");

                        }


                        System.out.print("\"" + currentEntityID + "\",\"" + currentDealerName + "\",\"" +  temp + "\",\"" + currentDealerPostcode + "\",");

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

                                System.out.println("\"Fail: Results NOT present\"");

                                dealerLocatorPage.clickLocationRadioButton();
                                dealerLocatorPage.clickLocationRadioButton();


                            }

                        }
                        if (resultText.contains(currentDealerPostcode)) {

                            System.out.println("\"Pass\"");
                            dealerLocatorPage.clickLocationRadioButton();
                        } else {
                            // If dealer postcode not found on the page THEN .... Check after showing more
                            if (dealerLocatorPage.isShowMorePresent()) {
                                if(dealerLocatorPage.checkTextIsPresentOnListAfterShowingMore(currentDealerName)!="Pass"){
                                    System.out.println("\"Fail\"");
                                }else{
                                    System.out.println("\"Pass\"");
                                }
                                dealerLocatorPage.clickLocationRadioButton();
                                dealerLocatorPage.clickLocationRadioButton();

                            } else {
                                System.out.println("\"Fail : could not find dealer in the results\"");
                                dealerLocatorPage.clickLocationRadioButton();
                                dealerLocatorPage.clickLocationRadioButton();
                            }

                        }


                    }



                }
            }
        }else {
            System.out.println("Null dealer list from xml");
        }
//        printAllFails(testResults);
    }
@Ignore
    @Test
    public void telNumberTest() throws InterruptedException, FileNotFoundException, UnsupportedEncodingException {
        printConsoleToFile("telNumberTest");
        System.out.println("EntityID,DealerName,Phone Number,TestResult");


        NodeList dealerList = ReadXMLFile.readXML(System.getProperty("user.dir")+ "/Resources/DealerData/bingmapsDataloadTransition_Thursday30-all-markets.xml");

        String resultText = null;
        String currentDealerName = "";
        String currentDealerPostcode = "";
        String currentEntityID = "";
        String currentPhone = "";

        String countryToTest = "Spain";

        if (null != dealerList) {
            for (int temp = 0; temp < dealerList.getLength(); temp++) {
                resultText = null;
                currentDealerName = "";
                currentDealerPostcode = "";
                currentEntityID = "";
                currentPhone = "";

                Node nNode = dealerList.item(temp);

                if (nNode.getNodeType() == Node.ELEMENT_NODE) {
                    Element eElement = (Element) nNode;

                    String country = eElement.getElementsByTagName("Country").item(0).getTextContent();

                    if (countryToTest.equalsIgnoreCase(country)) {
                        try {

                            currentEntityID = eElement.getElementsByTagName("EntityID").item(0).getTextContent();
                            currentDealerName = eElement.getElementsByTagName("DealerName").item(0).getTextContent();
                            currentDealerPostcode = eElement.getElementsByTagName("PostCode").item(0).getTextContent();
                            currentPhone = eElement.getElementsByTagName("PrimaryPhone").item(0).getTextContent();

                        } catch (NullPointerException e) {
                            currentDealerPostcode = "";

                        }
                    }
                }

                if(currentDealerName.equals("")||currentDealerPostcode.equals("")||currentPhone.equals("")){
                 System.out.print("\"" + currentEntityID + "\",\"" + currentDealerName + "\",\"" +  temp + "\",\"" + currentDealerPostcode + "\",");



                }

            }
        }
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

    public void printConsoleToFile(String testName) throws FileNotFoundException, UnsupportedEncodingException {


        PrintStream out = new PrintStream(new FileOutputStream(System.getProperty("user.dir") + "/target/test-results/test-results-" + testName + ".txt"));
        Writer write = new OutputStreamWriter(out, "UTF-8");
        System.setOut(out);


    }

}

