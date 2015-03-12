package com.ford.gux.tests.selenium;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.SessionNotFoundException;

/**
 * Created by alewi143 on 06/03/2015.
 */
public class Given {

         WebDriver driver = new ChromeDriver();
         WebDriver driverIE;


    public DealerLocatorPage iamOnADealerLocatorPage() throws InterruptedException {

        DealerLocatorPage dealerLocatorPage = new DealerLocatorPage(driver);
        dealerLocatorPage.go("http://m.intpublish-couk.engine.ford.com/GlobalUXDealerLocator");
        return dealerLocatorPage;
    }

    public HomePage iamOnTheHomePage() throws InterruptedException {

        HomePage homePage = new HomePage(driver);
        homePage.go("http://ahivas02987.ahi.ford.com:4503/content/ford-gux/gbr/en/index.html");
        return homePage;
    }

    public DealerLocatorPage iamOnADealerLocatorPageUsingInternetExplorer() throws InterruptedException {

//            File file = new File("C:\\selenium\\IEDriverServer.exe");
        System.setProperty("webdriver.ie.driver", "C:\\IEDriver\\IEDriverServer.exe");
        driverIE = new InternetExplorerDriver();
        DealerLocatorPage dealerLocatorPage = new DealerLocatorPage(driverIE);
        dealerLocatorPage.go("http://m.intpublish-couk.engine.ford.com/GlobalUXDealerLocator");
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
