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
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

public class DataTest {

    private static Given GIVEN;
    private static Map<String, ArrayList<String>> testResults;
    private LADMarket marketTested = LADMarket.AUSTRIAN;



    @BeforeClass
    public static void setup() throws FileNotFoundException {
        GIVEN = new Given();       
                
    }

    @Test
    public void locationDataNameTest() throws InterruptedException, FileNotFoundException, UnsupportedEncodingException {
            	
    	printConsoleToFile("locationDataNameTest", marketTested.getMarketName());
        System.out.println("EntityID,DealerName,Dealer Number,Location,TestResult");

        this.testResults = new HashMap();
        String resultText = null;
        String currentDealerName = "";
        String currentDealerPostcode = "";
        String currentEntityID = "";


        String countryToTest = marketTested.getMarketName(); 
        DealerLocatorPage dealerLocatorPage = GIVEN.iamOnAMarketDealerLocatorPage(marketTested);
        dealerLocatorPage.onADesktopView();
        String postcode;

        dealerLocatorPage.waitForpageToLoad();

        NodeList dealerList = ReadXMLFile.readXML(System.getProperty("user.dir")+ "/Resources/DealerData/bingmapsDataloadTransition_Thursday30-all-markets.xml");


        dealerLocatorPage.onADesktopView();

        if(dealerList != null) {

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
                            addValues(currentDealerName, "Fail : Postcode is empty");
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
                                addValues(currentDealerName, "Fail : could not find dealer in the results");
                                dealerLocatorPage.clickDealerRadioButton();
                                dealerLocatorPage.clickLocationRadioButton();
                            }
                        }
                       
                        //TODO: Replace with regex matching to avoid the .replace calls?
                        System.out.println("\"" + testResults.get(currentDealerName).toString().replace(", ", "\r\n").replace("{", "").replace("}", "").replace("[", "").replace("]", "") + "\"");
                    }
                }
            }
        }else{
            System.out.println("Null dealer list from xml");
        }
//        printAllFails(testResults);


    }


    @Test
    public void dealerDataNameTest() throws InterruptedException, FileNotFoundException, UnsupportedEncodingException {
    	    	
        printConsoleToFile("dealerDataNameTest", marketTested.getMarketName());
        System.out.println("EntityID,DealerName,Dealer Number,Location,TestResult");


        this.testResults = new HashMap();
        String resultText = null;
        String currentDealerName = "";
        String currentDealerPostcode = "";
        String currentEntityID = "";

        String countryToTest = marketTested.getMarketName(); 
        
        System.out.println(countryToTest);
        DealerLocatorPage dealerLocatorPage = GIVEN.iamOnAMarketDealerLocatorPage(marketTested);
 
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
                            addValues(currentDealerName, "Fail : Postcode is empty");
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
                                addValues(currentDealerName, "Fail : could not find dealer in the results");
                                dealerLocatorPage.clickLocationRadioButton();
                                dealerLocatorPage.clickLocationRadioButton();
                            }

                        }
                        System.out.println("\"" + testResults.get(currentDealerName).toString().replace(", ", "\r\n").replace("{", "").replace("}", "").replace("[", "").replace("]", "") + "\"");
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
        printConsoleToFile("telNumberTest", LADMarket.SPANISH.getMarketName());
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
    	
        ArrayList<String> tempList;
        
        if (testResults.containsKey(key)) {
        	
            tempList = testResults.get(key);
            
            if (tempList == null){
                tempList = new ArrayList<String>();
            }
            
            tempList.add(value);
        
        } else {
        
        	tempList = new ArrayList<String>();
            tempList.add(value);
            
        }
        testResults.put(key, tempList);
    }

    public void printConsoleToFile(String testName, String marketName) throws FileNotFoundException, UnsupportedEncodingException {
    	
    	DateFormat dateFormat = new SimpleDateFormat("dd_MM_yyyy");
    	Calendar cal = Calendar.getInstance();
    	String testDate = (dateFormat.format(cal.getTime()));
    	String outputDir = System.getProperty("user.dir") + "/target/test-results/test-results-" + marketName + "-" + testName + "-" + testDate + ".txt";
    	
        PrintStream out = new PrintStream(new FileOutputStream(outputDir));
        Writer write = new OutputStreamWriter(out, "UTF-8");
        System.setOut(out);
    }
}

