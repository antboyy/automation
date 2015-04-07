package com.ford.gux.tests.selenium;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.SessionNotFoundException;

/**
 * Created by alewi143 on 06/03/2015.
 */
public class Given {

    ChromeDriver driver = createChromeDriver();
    WebDriver driverIE;

    private ChromeDriver createChromeDriver(){
            String userDir = System.getProperty("user.dir");

        System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir")+ "/Resources/ChromeDriver/chromedriver.exe" );
        ChromeOptions options = new ChromeOptions();
         this.driver = new ChromeDriver(options);
        return driver;
    }

    public DealerLocatorPage iamOnAUKDealerLocatorPage() throws InterruptedException {

        DealerLocatorPage dealerLocatorPage = new DealerLocatorPage(driver);
//        dealerLocatorPage.go("http://m.intpublish-couk.engine.ford.com/GlobalUXDealerLocator");
        dealerLocatorPage.go("http://m.wwwprod-couk.engine.ford.com/Hidden/GUX_TestPages/GUXDealerLocatorMobile");
//        dealerLocatorPage.go("http://wwwedu-couk.engine.ford.com/Hidden/GUX_TestPages/GUXDealerLocatorDesktop");
//        dealerLocatorPage.go("http://wwwprod-couk.engine.ford.com/Hidden/GUX_TestPages/GUXDealerLocatorDesktop");
        return dealerLocatorPage;
    }

    public DealerLocatorPage iamOnAFrenchDealerLocatorPage() throws InterruptedException {

        DealerLocatorPage dealerLocatorPage = new DealerLocatorPage(driver);
        dealerLocatorPage.go("http://m.intpublish-fr.engine.ford.com/GUXDealerLocator");

        return dealerLocatorPage;
    }

    public DealerLocatorPage iamOnAGermanDealerLocatorPage() throws InterruptedException {

        DealerLocatorPage dealerLocatorPage = new DealerLocatorPage(driver);
        dealerLocatorPage.go("http://m.intpublish-de.engine.ford.com/GlobalUXDealerLocator");

        return dealerLocatorPage;
    }

    public DealerLocatorPage iamOnADutchDealerLocatorPage() throws InterruptedException {

        DealerLocatorPage dealerLocatorPage = new DealerLocatorPage(driver);
        dealerLocatorPage.go("http://intpublish-nl.engine.ford.com/DealerLocatorGUX");

        return dealerLocatorPage;
    }

    public DealerLocatorPage iamOnAnItalianDealerLocatorPage() throws InterruptedException {

        DealerLocatorPage dealerLocatorPage = new DealerLocatorPage(driver);
        dealerLocatorPage.go("http://intpublish-it.engine.ford.com/DealerLocatorGux");
//        dealerLocatorPage.go("http://m.wwwedu-it.engine.ford.com/Hidden/GUXDealerLocatorMobile");

        return dealerLocatorPage;
    }

    public DealerLocatorPage iamOnASpanishDealerLocatorPage() throws InterruptedException {

        DealerLocatorPage dealerLocatorPage = new DealerLocatorPage(driver);
        dealerLocatorPage.go("http://intpublish-es.engine.ford.com/OwnerServicesMike/DealerLocatorGux");
//        dealerLocatorPage.go("http://m.wwwedu-es.engine.ford.com/Hidden/GUXDealerLocatorMobile");

        return dealerLocatorPage;
    }

    public DealerLocatorPage iamOnACzechDealerLocatorPage() throws InterruptedException {

        DealerLocatorPage dealerLocatorPage = new DealerLocatorPage(driver);
        dealerLocatorPage.go("http://m.wwwedu-cz.engine.ford.com/Hidden/GUXDealerLocatorMobile");

        return dealerLocatorPage;
    }

    public HomePage iamOnTheHomePage() throws InterruptedException {

        HomePage homePage = new HomePage(driver);
//        homePage.go("http://ahivas02987.ahi.ford.com:4503/content/ford-gux/gbr/en/index.html");
//        homePage.go("http://m.intpublish-couk.engine.ford.com");
//        homePage.go("http://m.wwwedu-couk.engine.ford.com");
        homePage.go("http://m.wwwprod-couk.engine.ford.com/");
        return homePage;
    }



    public DealerLocatorPage iamOnADealerLocatorPageUsingInternetExplorer() throws InterruptedException {

        System.setProperty("webdriver.ie.driver", System.getProperty("user.dir")+ "/Resources/IEDriver/IEDriverServer.exe");
        driverIE = new InternetExplorerDriver();
        DealerLocatorPage dealerLocatorPage = new DealerLocatorPage(driverIE);
//        dealerLocatorPage.go("http://m.intpublish-couk.engine.ford.com/GlobalUXDealerLocator");
        dealerLocatorPage.go("http://wwwedu-couk.engine.ford.com/Hidden/GUX_TestPages/GUXDealerLocatorMobile");
        return dealerLocatorPage;

    }

    public void tearDown() {
        try{
            if(driver!=null) {
                driver.close();
                driver.quit();
            }}catch(Exception e){
            System.out.print(e);
        }
    }
    public void tearDownIE() {
            driverIE.close();
            driverIE.quit();
        }
		
		    public void test() {
            
        }


}
