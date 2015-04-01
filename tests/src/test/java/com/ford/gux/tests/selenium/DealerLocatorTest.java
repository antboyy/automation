package com.ford.gux.tests.selenium;


import com.ford.gux.tests.selenium.DealerDataSelenium.DealerData;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.not;
import static org.junit.Assert.assertThat;


public class DealerLocatorTest {

    private static Given GIVEN;
    private static Map <String, String> testResults;

    @BeforeClass
    public static void setup() {
        GIVEN = new Given();
    }
@Test
    public void searchForDealer() throws InterruptedException {


        DealerLocatorPage dealerLocatorPage  = GIVEN.iamOnAUKDealerLocatorPage();
        dealerLocatorPage.waitForpageToLoad();
        dealerLocatorPage.onADesktopView();
        dealerLocatorPage.clickDealerRadioButton();
        dealerLocatorPage.enterIntoInputBox("Lookers Ford - colchester");
        dealerLocatorPage.waitForResultsToBeDisplayed();
        assertThat(dealerLocatorPage.getNumberOfResults(),not(0));
        assertThat(dealerLocatorPage.getNameOfFirstDealerInResultList(), is("Lookers Ford - Colchester"));
        assertThat(dealerLocatorPage.getPostcodeofFirstDealerInResultsList(),is("CO1 2LT"));
    }

    @Test
    public void searchDealerThroughLocation() throws InterruptedException {

        DealerLocatorPage dealerLocatorPage  = GIVEN.iamOnAUKDealerLocatorPage();
        dealerLocatorPage.waitForpageToLoad();
        dealerLocatorPage.onADesktopView();
        dealerLocatorPage.enterIntoInputBox("Stevenage, United Kingdom");
        dealerLocatorPage.waitForResultsToBeDisplayed();
        assertThat(dealerLocatorPage.getNumberOfResults(), not(0));
        assertThat(dealerLocatorPage.getNameOfFirstDealerInResultList(), is("Gates of Stevenage"));
        assertThat(dealerLocatorPage.getPostcodeofFirstDealerInResultsList(),is("SG1 2BT"));
    }

    @Test
    public void locationDisambiguationTest() throws InterruptedException {

        DealerLocatorPage dealerLocatorPage  = GIVEN.iamOnAUKDealerLocatorPage();
        dealerLocatorPage.waitForpageToLoad();
        dealerLocatorPage.enterIntoInputBox("Basildon");
        dealerLocatorPage.clickDisambiguationWithName("Basildon, Essex");
        dealerLocatorPage.waitForResultsToBeDisplayed();
        assertThat(dealerLocatorPage.getNameOfFirstDealerInResultList(), is("Essex Ford - Basildon"));
        assertThat(dealerLocatorPage.getPostcodeofFirstDealerInResultsList(),is("SS14 3WF"));
    }

    @Test
    public void dealerAutocompleteTest() throws InterruptedException {

        DealerLocatorPage dealerLocatorPage  = GIVEN.iamOnAUKDealerLocatorPage();
        dealerLocatorPage.waitForpageToLoad();
        dealerLocatorPage.clickDealerRadioButton();
        dealerLocatorPage.enterIntoInputBoxWithoutSubmitting("trust ford - guern");
        dealerLocatorPage.clickDisambiguationWithName("Trust Ford - Guernsey");
        dealerLocatorPage.waitForResultsToBeDisplayed();
        assertThat(dealerLocatorPage.getNameOfDealerInDetailsView(), is("Trust Ford - Guernsey"));
        assertThat(dealerLocatorPage.getPostcodeofDealerInDetailsView(),is("GY1 3LB"));
    }

    @Test
    public void locationAutocompleteTest() throws InterruptedException {

        DealerLocatorPage dealerLocatorPage  = GIVEN.iamOnAUKDealerLocatorPage();
        dealerLocatorPage.waitForpageToLoad();
        dealerLocatorPage.enterIntoInputBoxWithoutSubmitting("lond");
        dealerLocatorPage.clickDisambiguationWithName("London, United Kingdom");
        dealerLocatorPage.waitForResultsToBeDisplayed();
        assertThat(dealerLocatorPage.getNumberOfResults(), not(0));
    }

    @Test
    public void filterServicesResultsTest() throws InterruptedException {

        DealerLocatorPage dealerLocatorPage  = GIVEN.iamOnAUKDealerLocatorPage();
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

        DealerLocatorPage dealerLocatorPage  = GIVEN.iamOnAUKDealerLocatorPage();
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
        DealerLocatorPage dealerLocatorPage  = GIVEN.iamOnAUKDealerLocatorPage();
        dealerLocatorPage.waitForpageToLoad();
        dealerLocatorPage.onADesktopView();
        assertThat(dealerLocatorPage.countNumberOfDesktopElementsVisible(), is(1));
        assertThat(dealerLocatorPage.countNumberOfMobileElementsVisible(), is(0));
        dealerLocatorPage.onAMobileView();
        assertThat(dealerLocatorPage.countNumberOfDesktopElementsVisible(), is(0));
        assertThat(dealerLocatorPage.countNumberOfMobileElementsVisible(), is(1));
     }
    @Ignore
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
        DealerLocatorPage dealerLocatorPage  = GIVEN.iamOnAUKDealerLocatorPage();
        dealerLocatorPage.waitForpageToLoad();
        dealerLocatorPage.enterIntoInputBox("sfksdklsklsdf");
        assertThat(dealerLocatorPage.getErrorText(), is("Unable to find any locations"));
    }

    @Test
    public void showMoreDealersRevealsMore() throws InterruptedException {
        DealerLocatorPage dealerLocatorPage  = GIVEN.iamOnAUKDealerLocatorPage();
        dealerLocatorPage.waitForpageToLoad();
        dealerLocatorPage.enterIntoInputBox("London");
        dealerLocatorPage.waitForResultsToBeDisplayed();
        dealerLocatorPage.clickShowMoreDealers();
        assertThat(dealerLocatorPage.getNumberOfResults(),is(10));

    }


    @Test
    public void ukIslandsSearches() throws InterruptedException {
        DealerLocatorPage dealerLocatorPage  = GIVEN.iamOnAUKDealerLocatorPage();
        dealerLocatorPage.waitForpageToLoad();
        dealerLocatorPage.enterIntoInputBox("st helier");
        dealerLocatorPage.waitForResultsToBeDisplayed();
        assertThat(dealerLocatorPage.getNumberOfResults(), not(0));

    }

    @Test
    public void fordStorePresearchFilter() throws InterruptedException {
        DealerLocatorPage dealerLocatorPage = GIVEN.iamOnAUKDealerLocatorPage();
        dealerLocatorPage.waitForpageToLoad();
        dealerLocatorPage.selectMustangFilter();
        dealerLocatorPage.enterIntoInputBox("Birmingham");
        dealerLocatorPage.waitForResultsToBeDisplayed();
        assertThat(dealerLocatorPage.numberOfDealersWithServicesPresent("FordStore"), is(5));

    }

    @Test
    public void numberOfMapPinsAreCorrect() throws InterruptedException {
        DealerLocatorPage dealerLocatorPage = GIVEN.iamOnAUKDealerLocatorPage();
        dealerLocatorPage.waitForpageToLoad();
        dealerLocatorPage.onADesktopView();
        dealerLocatorPage.clickDealerRadioButton();
        dealerLocatorPage.enterIntoInputBox("essex ford");
        dealerLocatorPage.waitForResultsToBeDisplayed();
        dealerLocatorPage.waitForMapPinsToRender();
        dealerLocatorPage.clearInputBox();
        assertThat(dealerLocatorPage.getNumberOfMapPins(), is(dealerLocatorPage.getNumberOfResults()));
        dealerLocatorPage.enterIntoInputBoxWithoutSubmitting("essex ford ray");
        dealerLocatorPage.clickDisambiguationWithName("Essex Ford - Rayleigh");
        dealerLocatorPage.waitForResultsToBeDisplayed();
        dealerLocatorPage.waitForMapPinsToRender();
        assertThat(dealerLocatorPage.getNumberOfMapPins(), is(dealerLocatorPage.getNumberOfResults()));
        assertThat(dealerLocatorPage.getInputBoxText(),is("Essex Ford - Rayleigh"));
    }

    @Test
    public void pinExitButtonsPresentOnMapPopups() throws InterruptedException {
        DealerLocatorPage dealerLocatorPage = GIVEN.iamOnAUKDealerLocatorPage();
        dealerLocatorPage.onADesktopView();
        dealerLocatorPage.waitForpageToLoad();
        dealerLocatorPage.enterIntoInputBox("Swansea");
        dealerLocatorPage.waitForResultsToBeDisplayed();
        dealerLocatorPage.waitForMapPinsToRender();
        dealerLocatorPage.clickPinIndexNumber(1);
        assertThat(dealerLocatorPage.mapPinExitButtonIsDisplayed(),is(true));

        dealerLocatorPage.clickPinExitButton();
        assertThat(dealerLocatorPage.mapPinExitButtonIsDisplayed(),is(false));

        dealerLocatorPage.clickResultsHeader();
        dealerLocatorPage.clickDealerIndexNumber(2);
        dealerLocatorPage.clickHighlightedMapPin();
        assertThat(dealerLocatorPage.mapPinExitButtonIsDisplayed(),is(true));

        dealerLocatorPage.clickPinExitButton();
        assertThat(dealerLocatorPage.mapPinExitButtonIsDisplayed(),is(false));

    }
    @Ignore
    @Test
    public void tabThroughAutocomplete() throws InterruptedException {}

    @Ignore
    @Test
    public void mustangPreFilterTest() throws InterruptedException {}

    @Test
    public void currentLocationSearch() throws InterruptedException {
        DealerLocatorPage dealerLocatorPage = GIVEN.iamOnAGermanDealerLocatorPage();
        dealerLocatorPage.waitForpageToLoad();
        dealerLocatorPage.onAMobileView();
        dealerLocatorPage.clickSearchGeoLocation();
        dealerLocatorPage.waitForResultsToBeDisplayed();
        assertThat(dealerLocatorPage.getNumberOfResults(),not(0));


    }

    @Ignore
    @Test
    public void hyphenDoesNotBreakServices() throws InterruptedException {
        /*DealerLocatorPage dealerLocatorPage = GIVEN.iamOnAUKDealerLocatorPage();
        dealerLocatorPage.waitForpageToLoad();
        dealerLocatorPage.selectMustangFilter();
        dealerLocatorPage.enterIntoInputBox("Birmingham");
        dealerLocatorPage.waitForResultsToBeDisplayed();
        assertThat(dealerLocatorPage.numberOfDealersWithServicesPresent("FordStore"), is(5));
*/
        // search Autohaus Dinnebier GmbH
        // go to details page and see the services: "Neuwagen-Händler" and "Ford-Service" present
        // Go to auto complete : Type "Autohaus Dinnebier GmbH"
        // assert that "Neuwagen-Händler" and "Ford-Service" is present


        //UK search "Trust Ford - Bradford
        //Small Business, Fleet is present
    }

    @Test
    public void locationSearchForSpecificDealer() throws InterruptedException {

        DealerLocatorPage dealerLocatorPage  = GIVEN.iamOnAFrenchDealerLocatorPage();
        dealerLocatorPage.waitForpageToLoad();
        dealerLocatorPage.onADesktopView();
        dealerLocatorPage.enterIntoInputBox("73210");
        dealerLocatorPage.waitForResultsToBeDisplayed();
        String result = dealerLocatorPage.checkTextIsPresentOnListAfterShowingMore("Tarentaise Automobiles SARL");
        System.out.print(result);
    }


    @AfterClass
    public static void tearDown() {
        GIVEN.tearDown();
        DealerLocatorPage.tearDown();
    }





}
