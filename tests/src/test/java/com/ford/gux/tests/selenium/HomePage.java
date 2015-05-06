package com.ford.gux.tests.selenium;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;


public class HomePage extends AbstractPage{

    public HomePage(WebDriver driver){
        this.driver = driver;
    }

    static WebDriver driver ;
    private By byCarouselNext = By.xpath("//a[@class='controls next']");
    private By byCarouselPrev = By.xpath("//a[@class='controls prev']");
    private By byCurrentImageURL = By.cssSelector("li.active-slide img");
    private By byShowroomSubtext = By.cssSelector("h2 ~ h3");
    private By byViewMoreCampaigns = By.cssSelector("div.expandTabs");

    public void go(String url)throws InterruptedException {


        driver.navigate().to(url);
        WaitHelpers.waitForElementToDisplayOnScreen(driver, byCarouselNext);

}

    public void clickCarousellNext() throws InterruptedException {
        driver.findElement((byCarouselNext)).click();
        Thread.sleep(2000);


    }

    public String getImageURL() {
        WaitHelpers.waitForElementToDisplayOnScreen(driver,byCurrentImageURL);
        return driver.findElement(byCurrentImageURL).getAttribute("src");

    }

    public void onAMobileView() {
//        driver.manage().window().setPosition(new Point(0,0));
        driver.manage().window().setSize(new Dimension(320,568));
    }

    public void onADesktopView() {
        driver.manage().window().setSize(new Dimension(1200,850));
    }

    public static void tearDown() {
        driver.close();
        driver.quit();
    }

    public int countNumberOfDesktopElementsVisible() {
        int numberOfDesktopElementsVisible = 0;
//        for ()
        try {if(driver.findElement(byCarouselNext).isDisplayed()){
            numberOfDesktopElementsVisible++;
        }}catch (NoSuchElementException e){}
        try {if(driver.findElement(byCarouselPrev).isDisplayed()){
            numberOfDesktopElementsVisible++;
        }}catch (NoSuchElementException e){}
        try {if(driver.findElement(byShowroomSubtext).getText().length()>1){
            numberOfDesktopElementsVisible++;
        }}catch (NoSuchElementException e){}

        return numberOfDesktopElementsVisible;

    }

    public int countNumberOfMobileElementsVisible() {
        int numberOfMobileElementsVisible = 0;
        try {if (driver.findElement(byViewMoreCampaigns).isDisplayed()){
            numberOfMobileElementsVisible++;
        }}catch (NoSuchElementException e){}
        return numberOfMobileElementsVisible;
    }

    public void setPersonalisation(String nameplate) {
/*
        Need to use Switcheroo extension to spoof Akamai:
        http://m.intpublish-couk.engine.ford.com/fps/   --> http://eu.fpsqa.ford.com/fps/
*/

        JavascriptExecutor executor = (JavascriptExecutor) driver;
        executor.executeScript("FPS.set([ { 'ViewedVehicle': { name: '"+nameplate+"', url: '/Cars/"+nameplate+"' }} ]);");

    }

    public void refresh() {
        driver.navigate().refresh();
    }

    public int getNumberOfBillboardImages() {
        if(driver.findElements(By.cssSelector("li.slides > img")).size()>1){
            return driver.findElements(By.cssSelector("li.slides > img")).size()-2;
        }else return driver.findElements(By.cssSelector("li.slides > img")).size();

    }

    public static class DealerLocatorPage {

        static WebDriver driver = new FirefoxDriver();
        private By inputBox = By.cssSelector("#search-field");
        private By locationRadioButton = By.cssSelector("span.radio-button[0]");
        private By dealerRadioButton = By.cssSelector("span.radio-button[1]");
        private By submitSearch = By.cssSelector("button.search-submit");





    }
}
