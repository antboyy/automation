package com.ford.gux.tests.selenium;

import org.junit.*;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import static org.junit.Assert.assertThat;
import static org.hamcrest.Matchers.*;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;


public class HomepageTest {

    private static Given GIVEN;


    @BeforeClass
    public static void setup() {
        GIVEN = new Given();
    }

    @Test
    public void homepageCarousellClick() throws InterruptedException {


    HomePage homepage = GIVEN.iamOnTheHomePage();
    homepage.onADesktopView();
    homepage.go("http://ahivas02987.ahi.ford.com:4503/content/ford-gux/gbr/en/index.html");

    homepage.clickCarousellNext();
    String firstImageURL = homepage.getImageURL();
    homepage.clickCarousellNext();
    String secondImageURL = homepage.getImageURL();

    assertThat(firstImageURL,not(secondImageURL));
    }

    @Test
    public void homepageMobileTest() throws InterruptedException {

        HomePage homepage = GIVEN.iamOnTheHomePage();

        homepage.onAMobileView();
        assertThat(homepage.countNumberOfDesktopElementsVisible(),is(0));
        assertThat(homepage.countNumberOfMobileElementsVisible(), is(1));

        homepage.onADesktopView();
        assertThat(homepage.countNumberOfDesktopElementsVisible(), is(3));
        assertThat(homepage.countNumberOfMobileElementsVisible(), is(0));


    }

//    @Test
//    public void excelTest() throws Exception {
//        String [][] xTSdata = ExcelHelper.xlTSRead("C:\\excelTestData\\excell.xlsx");
//
//
//    }
        @AfterClass
    public static void tearDown() {

            HomePage.tearDown();
    }



}
