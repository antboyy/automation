package com.ford.gux.tests.selenium;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.not;
import static org.junit.Assert.assertThat;


public class DealerLocatorTest {

    private static Given GIVEN;

    @BeforeClass
    public static void setup() {
        GIVEN = new Given();
    }

    @Test
    public void searchForDealer() throws InterruptedException {


        DealerLocatorPage dealerLocatorPage  = GIVEN.iamOnADealerLocatorPage();
        dealerLocatorPage.waitForpageToLoad();
        dealerLocatorPage.onADesktopView();
        dealerLocatorPage.clickDealerRadioButton();
        dealerLocatorPage.enterIntoInputBox("Lookers Ford - colchester");
        dealerLocatorPage.waitForResultsToBeDisplayed();
        assertThat(dealerLocatorPage.getNumberOfResults(),not(0));
        assertThat(dealerLocatorPage.getNameOfFirstDealer(), is("Lookers Ford - Colchester"));
        assertThat(dealerLocatorPage.getPostcodeofFirstDealer(),is("CO1 2LT"));
    }

    @Test
    public void searchDealerThroughLocation() throws InterruptedException {

        DealerLocatorPage dealerLocatorPage  = GIVEN.iamOnADealerLocatorPage();
        dealerLocatorPage.waitForpageToLoad();
        dealerLocatorPage.onADesktopView();
        dealerLocatorPage.enterIntoInputBox("Stevenage, United Kingdom");
        dealerLocatorPage.waitForResultsToBeDisplayed();
        assertThat(dealerLocatorPage.getNumberOfResults(), not(0));
        assertThat(dealerLocatorPage.getNameOfFirstDealer(), is("Gates of Stevenage"));
        assertThat(dealerLocatorPage.getPostcodeofFirstDealer(),is("SG1 2BT"));
    }

    @Test
    public void disambiguationTest() throws InterruptedException {

        DealerLocatorPage dealerLocatorPage  = GIVEN.iamOnADealerLocatorPage();
        dealerLocatorPage.waitForpageToLoad();
        dealerLocatorPage.enterIntoInputBox("Basildon");
        dealerLocatorPage.clickDisambiguationWithName("Basildon, Essex");
        dealerLocatorPage.waitForResultsToBeDisplayed();
        assertThat(dealerLocatorPage.getNameOfFirstDealer(), is("Essex Ford - Basildon"));
        assertThat(dealerLocatorPage.getPostcodeofFirstDealer(),is("SS14 3WF"));
    }

    @Test
    public void filterServicesResultsTest() throws InterruptedException {

        DealerLocatorPage dealerLocatorPage  = GIVEN.iamOnADealerLocatorPage();
        dealerLocatorPage.waitForpageToLoad();
        dealerLocatorPage.enterIntoInputBox("Braintree");
        dealerLocatorPage.waitForResultsToBeDisplayed();
        dealerLocatorPage.clickFilter();
        dealerLocatorPage.clickServicesFilter();
        dealerLocatorPage.selectFilterOption("Motability");
        dealerLocatorPage.clickFilterSubmit();
        assertThat(dealerLocatorPage.numberOfDealersWithServicesPresent("Motability"), is(5));
    }

    @Test
    public void filterDistanceResultsTest() throws InterruptedException {

        DealerLocatorPage dealerLocatorPage  = GIVEN.iamOnADealerLocatorPage();
        dealerLocatorPage.waitForpageToLoad();
        dealerLocatorPage.enterIntoInputBox("Manchester");
        dealerLocatorPage.waitForResultsToBeDisplayed();
        dealerLocatorPage.clickFilter();
        dealerLocatorPage.clickDistanceFilter();
        dealerLocatorPage.selectDistanceOption("10miles");
        dealerLocatorPage.clickFilterSubmit();
        dealerLocatorPage.waitForResultsToBeDisplayed();
        assertThat(dealerLocatorPage.areAllDealersCloserThanSpecifiedDistance(10), is(true));
    }

    @Test
    public void dealerLocatorResponsiveComponentsTest() throws InterruptedException {
        DealerLocatorPage dealerLocatorPage  = GIVEN.iamOnADealerLocatorPage();
        dealerLocatorPage.waitForpageToLoad();
        dealerLocatorPage.onADesktopView();
        assertThat(dealerLocatorPage.countNumberOfDesktopElementsVisible(), is(1));
        assertThat(dealerLocatorPage.countNumberOfMobileElementsVisible(), is(0));
        dealerLocatorPage.onAMobileView();
        assertThat(dealerLocatorPage.countNumberOfDesktopElementsVisible(), is(0));
        assertThat(dealerLocatorPage.countNumberOfMobileElementsVisible(), is(1));
    }

    @Test
    public void unsupportedBrowserTest() throws InterruptedException {
        DealerLocatorPage dealerLocatorPage = GIVEN.iamOnADealerLocatorPageUsingInternetExplorer();
        dealerLocatorPage.waitForNotSupportedPageToLoad();
        assertThat(dealerLocatorPage.getHeadingText(), is("Your browser is not supported"));
        assertThat(dealerLocatorPage.countNumberOfDesktopElementsVisible(),is(0));
        assertThat(dealerLocatorPage.countNumberOfMobileElementsVisible(),is(0));
        GIVEN.tearDownIE();
    }

    @Test
    public void invalidLocationSearch() throws InterruptedException {
        DealerLocatorPage dealerLocatorPage  = GIVEN.iamOnADealerLocatorPage();
        dealerLocatorPage.waitForpageToLoad();
        dealerLocatorPage.enterIntoInputBox("sfksdklsklsdf");
        assertThat(dealerLocatorPage.getErrorText(), is("Unable to find any locations"));
    }

    @Test
    public void showMoreDealersRevealsMore() throws InterruptedException {
        DealerLocatorPage dealerLocatorPage  = GIVEN.iamOnADealerLocatorPage();
        dealerLocatorPage.waitForpageToLoad();
        dealerLocatorPage.enterIntoInputBox("London");
        dealerLocatorPage.waitForResultsToBeDisplayed();
        dealerLocatorPage.clickShowMoreDealers();
        assertThat(dealerLocatorPage.getNumberOfResults(),is(10));

    }

    @Test
    public void fordStorePresearchFilter() throws InterruptedException {
        DealerLocatorPage dealerLocatorPage = GIVEN.iamOnADealerLocatorPage();
        dealerLocatorPage.waitForpageToLoad();
        dealerLocatorPage.selectMustangFilter();
        dealerLocatorPage.enterIntoInputBox("Birmingham");
        dealerLocatorPage.waitForResultsToBeDisplayed();
        assertThat(dealerLocatorPage.numberOfDealersWithServicesPresent("FordStore"), is(5));

    }
    @AfterClass
    public static void tearDown() {
        GIVEN.tearDown();
        DealerLocatorPage.tearDown();
    }





}
