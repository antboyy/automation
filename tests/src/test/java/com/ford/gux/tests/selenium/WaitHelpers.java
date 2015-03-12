package com.ford.gux.tests.selenium;


import com.google.common.base.Function;
import com.google.common.base.Predicate;
import org.apache.commons.collections.CollectionUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

import static java.util.Arrays.asList;

public class WaitHelpers
{

    private static final boolean SHOW_PAGE_SOURCE_IN_ERRORS = false;
    private static final long CHECK_OUT_WAIT_TIME = 25;
    private static final int WAIT_TIME_IN_SEC = 25;
    private static final int WAIT_TIME_FOR_ANIMATION = 25;

    public static class ErrorElementFoundException extends Exception
    {
        By errorElement;

        private ErrorElementFoundException(By errorElement)
        {
            this.errorElement = errorElement;
        }
    }



    //TODO - only one usage of this - clean up when possible
    public static WebElement waitForSuccessOrErrorElementToBeDisplayed(final By successElement, final By errorElement, final WebDriver driver)
            throws ErrorElementFoundException
    {
        // reusable mechanism to find either elements
        final Function<WebDriver, By> successOrError = new Function<WebDriver, By>()
        {

            @Override
            public By apply(WebDriver webDriver)
            {
                try
                {
                    webDriver.findElement(successElement);
                    return successElement;
                }
                catch (NoSuchElementException e)
                {
                    webDriver.findElement(errorElement);
                    return errorElement;
                }
            }

            @Override
            public String toString() {

                return driver.getCurrentUrl() + " " + successElement + " or " + errorElement + pageSource(driver);
            }
        };

        // wait for either elements to be displayed
        WebDriverWait wait = new WebDriverWait(driver, CHECK_OUT_WAIT_TIME);

        wait.until(new Predicate<WebDriver>()
        {
            @Override
            public boolean apply(WebDriver webDriver)
            {
                // may throw NoSuchElementException
                successOrError.apply(webDriver);
                // only if either success or error element found
                return true;
            }

            @Override
            public String toString() {
                return successOrError.toString();
            }
        });

        if (successElement.equals(successOrError.apply(driver))) return driver.findElement(successElement);
        else throw new ErrorElementFoundException(errorElement);
    }

    //if-else on exception thanks to selenium. atm there is no way to check if element exist with time out.
    //find elements returns strait away

    public static boolean checkIfDisplayedWithTimeout(WebDriver driver, By by)
    {
        try {
            waitForElementToBeDisplayed(by, driver, WAIT_TIME_IN_SEC);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public static WebElement waitForElementToBeDisplayed(WebDriver driver, By by)
    {
        return waitForElementToBeDisplayed(by, driver, WAIT_TIME_IN_SEC);
    }

    public static void waitForElementToDisplayedAndClick(By by, WebDriver driver)
    {
        waitForElementToBeDisplayed(driver, by);
        driver.findElement(by).click();
    }

    public static WebElement waitForElementToBeDisplayed(By by, WebDriver driver, int numberOfSeconds)
    {
        waitForDisplay(by, driver, numberOfSeconds);
        return driver.findElement(by);
    }


    public static List<WebElement> waitForElementsToBeDisplayed(By by, WebDriver driver, int numberOfSeconds)
    {
        waitForDisplay(by, driver, numberOfSeconds);
        return driver.findElements(by);
    }

    public static List<WebElement> waitForElementsToBeDisplayed(By by, WebDriver driver)
    {
        return waitForElementsToBeDisplayed(by, driver, 30);
    }

    public static void waitForUrlToChangeTo(final String urlToChangeToo, final WebDriver driver)
    {
        WebDriverWait wait = new WebDriverWait(driver, WAIT_TIME_IN_SEC);
        wait.until(new Predicate<WebDriver>()
        {
            @Override
            public boolean apply(WebDriver webDriver)
            {
                return webDriver.getCurrentUrl().contains(urlToChangeToo);
            }

            @Override
            public String toString() {
                return driver.getCurrentUrl() + " to change to " + urlToChangeToo;
            }
        });
    }

    public static WebElement waitForAnyElementToBeDisplayed(WebDriver driver, By... bys)
    {
        return waitForElement(anyDisplayed(driver, bys), driver, WAIT_TIME_IN_SEC);
    }

    private static void waitForDisplay(By by, WebDriver driver, int numberOfSeconds)
    {
        waitForElement(isDisplayed(driver, by), driver, numberOfSeconds);
    }

    private static void waitForHide(By by, WebDriver driver, int numberOfSeconds)
    {
        waitForElement(isHidden(by), driver, numberOfSeconds);
    }

    private static void waitForElement(Predicate<WebDriver> predicate, WebDriver driver, int numberOfSeconds)
    {
        WebDriverWait wait = new WebDriverWait(driver, numberOfSeconds);
        wait.until(predicate);
    }

    private static WebElement waitForElement(Function<WebDriver, WebElement> function, WebDriver driver, int numberOfSeconds)
    {
        WebDriverWait wait = new WebDriverWait(driver, numberOfSeconds);
        return wait.until(function);
    }


    private static Predicate<WebDriver> isDisplayed(final WebDriver driver, final By by)
    {
        return new Predicate<WebDriver>()
        {
            @Override
            public boolean apply(WebDriver webDriver)
            {
                WebElement element = webDriver.findElement(by);
                try
                {
                    return element.isDisplayed();
                }
                catch (StaleElementReferenceException e)
                {
                    return false;
                }
            }

            @Override
            public String toString() {
                return driver.getCurrentUrl() + " '" + by + pageSource(driver);
            }
        };
    }

    private static Function<WebDriver, WebElement> anyDisplayed(final WebDriver webDriver, final By... bys)
    {
        return new Function<WebDriver, WebElement>()
        {
            @Override
            public WebElement apply(WebDriver webDriver)
            {
                for(By by: bys){
                    List<WebElement> elements = webDriver.findElements(by);
                    if(CollectionUtils.isNotEmpty(elements)){
                        if(elements.size()==1){
                            return elements.get(0);
                        }else{
                            throw new IllegalStateException("Found more than one element but expected only one");
                        }
                    }
                }
                return null;
            }

            @Override
            public String toString() {
                return webDriver.getCurrentUrl() + " '" + asList(bys) + pageSource(webDriver);
            }
        };
    }


    private static Predicate<WebDriver> isHidden(final By by)
    {
        return new Predicate<WebDriver>()
        {
            @Override
            public boolean apply(WebDriver webDriver)
            {
                WebElement element = webDriver.findElement(by);
                return !element.isDisplayed();
            }
        };
    }

    public static void waitForUrlToChangeFrom(final String currentUrl, final WebDriver driver)
    {
        WebDriverWait wait = new WebDriverWait(driver, WAIT_TIME_IN_SEC);
        wait.until(new Predicate<WebDriver>()
        {
            @Override
            public boolean apply(WebDriver webDriver)
            {
                return !driver.getCurrentUrl().equals(currentUrl);
            }
        });
    }

    public static void waitForUrlToChangeAfterAction(WebDriverAction actionToPerform, final WebDriver driver)
    {
        final String currentUrl = driver.getCurrentUrl();
        actionToPerform.perform();
        WebDriverWait wait = new WebDriverWait(driver, WAIT_TIME_IN_SEC);
        wait.until(new Predicate<WebDriver>()
        {
            @Override
            public boolean apply(WebDriver webDriver)
            {
                return !driver.getCurrentUrl().equals(currentUrl);
            }
        });
    }

    public static void waitForElementToBeHidden(By by, WebDriver driver)
    {
        waitForHide(by, driver, WAIT_TIME_FOR_ANIMATION);
    }

    public static void waitForElementTextToChangeAfter(WebDriverAction actionToPerform, final By elementToWaitFor, final WebDriver driver)
    {
        final String text = driver.findElement(elementToWaitFor).getText();
        actionToPerform.perform();
        WebDriverWait wait = new WebDriverWait(driver, WAIT_TIME_FOR_ANIMATION);
        wait.until(new Predicate<WebDriver>()
        {
            @Override
            public boolean apply(WebDriver webDriver)
            {
                return !text.equals(driver.findElement(elementToWaitFor).getText());
            }

            @Override
            public String toString() {
                return driver.getCurrentUrl() + " '" + elementToWaitFor + pageSource(driver);
            }
        });
    }

    public static void waitForElementToChangeFrom(final String currentValue, final WebElement element, final WebDriver driver)
    {
        WebDriverWait wait = new WebDriverWait(driver, WAIT_TIME_FOR_ANIMATION);
        wait.until(new Predicate<WebDriver>()
        {
            @Override
            public boolean apply(WebDriver webDriver)
            {
                return !element.getText().equals(currentValue);
            }

            @Override
            public String toString() {
                return driver.getCurrentUrl() + " NOT '" + element + pageSource(driver);
            }
        });
    }

    public static void waitForMoreThanOneElement(final WebDriver driver, final By by)
    {
        WebDriverWait wait = new WebDriverWait(driver, WAIT_TIME_FOR_ANIMATION);
        wait.until(new Predicate<WebDriver>()
        {
            @Override
            public boolean apply(WebDriver webDriver)
            {
                List<WebElement> elements = driver.findElements(by);
                return (elements.size() > 1);
            }

            @Override
            public String toString() {
                return driver.getCurrentUrl() + " '" + by + pageSource(driver);
            }
        });
    }

    public static void waitForAnimation()
    {
        // default jQurey animation duration: 400ms
        waitFor(500);
    }

    public static void waitFor(int milliSeconds)
    {
        try
        {
            Thread.sleep(milliSeconds);
        }
        catch (InterruptedException e)
        {
           Thread.currentThread().interrupt();
        }
    }

    private static String pageSource(WebDriver driver) {

        if(SHOW_PAGE_SOURCE_IN_ERRORS) {
            return "'.\nPage source: [------------------------------------------------------\n" + driver.getPageSource() + "\n-----------------------------------------------------\n\n]";
        }

        return "";
    }

    static public void waitForElementToDisplayOnScreen(WebDriver driver, By by){
        WebElement myDynamicElement = (new WebDriverWait(driver, 25))
                .until(ExpectedConditions.presenceOfElementLocated(by));

        WebDriverWait wait = new WebDriverWait(driver, 25);
        WebElement element = wait.until(ExpectedConditions.elementToBeClickable(by));


    }

}

