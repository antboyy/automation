package com.ford.gux.tests.selenium;

import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * Created by alewi143 on 06/03/2015.
 */
public class Given {

    ChromeDriver driver;
    WebDriver driverIE;
    private List<String> switches;



    public Given() {

        driver = createChromeDriver();
    }

    public Given(String userAgent) {

        this.switches = new ArrayList<String>();
//        this.switches.add("--user-agent=iPhone");
        this.switches.add("--accept-language=en-GB");
        this.switches.add("--disable-logging");

        this.driver = createChromeDriver();


        createDriverWithUserAgent(userAgent);
    }




    private ChromeDriver createChromeDriver(){
        String userDir = System.getProperty("user.dir");
//        String userProfile = "C:\\tests\\tests\\Resources\\ChromeDriver\\Profile";
//        String userProfile = "C:\\Users\\ALEWI143\\AppData\\Local\\Google\\Chrome\\User Data";
//        String userProfile = "C:\\Users\\ALEWI143\\AppData\\Local\\Google\\Chrome\\User Data\\Profile 2";
        System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir")+ "/Resources/ChromeDriver/chromedriver.exe" );

/*
        Doesnt fully work
        DesiredCapabilities capabilities = DesiredCapabilities.chrome();
        ChromeOptions options = new ChromeOptions();
        options.addArguments("user-data-dir="+userProfile);
        options.addExtensions(new File(System.getProperty("user.dir")+ "/Resources/ChromeDriver/Extensions/cnmciclhnghalnpfhhleggldniplelbg-1.33-www.Crx4Chrome.com.crx"));
        capabilities.setCapability(ChromeOptions.CAPABILITY, options);
        this.driver = new ChromeDriver(capabilities);
*/

/*        ChromeOptions options = new ChromeOptions();
        options.addExtensions(new File(System.getProperty("user.dir")+ "/Resources/ChromeDriver/Extensions/cnmciclhnghalnpfhhleggldniplelbg-1.33-www.Crx4Chrome.com.crx"));
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability(ChromeOptions.CAPABILITY, options);
        ChromeDriver driver = new ChromeDriver(capabilities);*/


        /*
        this.driver = new ChromeDriver();
        return driver;
*/
        ChromeOptions options = new ChromeOptions();
//        options.addArguments(this.switches);
        return new ChromeDriver(options);
    }

    public DealerLocatorPage iamOnAUKDealerLocatorPage() throws InterruptedException {

        DealerLocatorPage dealerLocatorPage = new DealerLocatorPage(driver);
//        dealerLocatorPage.go("http://m.intpublish-couk.engine.ford.com/GlobalUXDealerLocator");
        dealerLocatorPage.go("http://m.ford.co.uk/DealerLocator");

//        dealerLocatorPage.go("http://m.wwwprod-couk.engine.ford.com/Hidden/GUX_TestPages/GUXDealerLocatorMobile");
//        dealerLocatorPage.go("http://m.wwwedu-couk.engine.ford.com/Hidden/GUX_TestPages/GUXDealerLocatorMobile");
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

    public DealerLocatorPage iamOnAGreekDealerLocatorPage() throws InterruptedException {

        DealerLocatorPage dealerLocatorPage = new DealerLocatorPage(driver);
        dealerLocatorPage.go("http://intpublish-gr.engine.ford.com/DealerLocatorGux");

        return dealerLocatorPage;
    }

    public DealerLocatorPage iamOnACzechDealerLocatorPage() throws InterruptedException {

        DealerLocatorPage dealerLocatorPage = new DealerLocatorPage(driver);
//        dealerLocatorPage.go("http://m.wwwedu-cz.engine.ford.com/Hidden/GUXDealerLocatorMobile");
        dealerLocatorPage.go("http://intpublish-cz.engine.ford.com/Footer/DealerLocatorGux");

        return dealerLocatorPage;
    }

    public HomePage iamOnTheHomePage() throws InterruptedException {

        HomePage homePage = new HomePage(driver);
//        homePage.go("http://ahivas02987.ahi.ford.com:4503/content/ford-gux/gbr/en/index.html");
//        homePage.go("http://m.intpublish-couk.engine.ford.com");
        homePage.go("http://m.wwwedu-couk.engine.ford.com");
//        homePage.go("http://m.wwwprod-couk.engine.ford.com/");
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

    public DealerLocatorPage iamOnAnAustrianDealerLocatorPage() throws InterruptedException {

        DealerLocatorPage dealerLocatorPage = new DealerLocatorPage(driver);
        dealerLocatorPage.go("http://intpublish-at.engine.ford.com/OwnerServices/DealerLocatorGux");

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


    public DealerLocatorPage iamOnAPortugeseDealerLocator() throws InterruptedException {

        DealerLocatorPage dealerLocatorPage = new DealerLocatorPage(driver);
        dealerLocatorPage.go("http://intpublish-pt.engine.ford.com/GlobalUXDealerLocator");

        return dealerLocatorPage;
    }
        public boolean doesUserAgentRedirect(String userAgent, String startURL, String endURL) throws InterruptedException {

            this.driver=createDriverWithUserAgent(userAgent);
            AbstractPage page = new AbstractPage(driver);
            try {
                page.go(startURL);
                page.waitForURLToChangeFrom(startURL);

            }catch (TimeoutException e){
                return false;
            }
            page.getCurrentURL();
            if (page.getCurrentURL().toString().equals(endURL)){
                return true;
            }else return false;


        }

    private ChromeDriver createDriverWithUserAgent(String userAgent){
        this.driver.close();

        /*Can use 'windows' or 'iPhone' or 'Android'*/

        this.switches = new ArrayList<String>();
        this.switches.add("--user-agent="+userAgent+"\"");
        this.switches.add("--accept-language=en-GB");
        this.switches.add("--disable-logging");

        ChromeOptions options = new ChromeOptions();
        options.addArguments(this.switches);
        return new ChromeDriver(options);

/*        Map<String, String> mobileEmulation = new HashMap<String, String>();
        mobileEmulation.put("deviceName", userAgent);

        Map<String, Object> chromeOptions = new HashMap<String, Object>();
        chromeOptions.put("mobileEmulation", mobileEmulation);
        DesiredCapabilities capabilities = DesiredCapabilities.chrome();
        capabilities.setCapability(ChromeOptions.CAPABILITY, chromeOptions);
        return this.driver = new ChromeDriver(capabilities);*/

    }

    }

