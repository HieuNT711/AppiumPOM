package com.qa.pages;

import static io.appium.java_client.touch.TapOptions.tapOptions;
import static io.appium.java_client.touch.WaitOptions.waitOptions;
import static io.appium.java_client.touch.offset.ElementOption.element;
import static io.appium.java_client.touch.offset.PointOption.point;

import static org.openqa.selenium.support.ui.ExpectedConditions.visibilityOfElementLocated;

import static java.time.Duration.ofMillis;
import static java.time.Duration.ofSeconds;

import com.utils.RandomUtil;
import com.utils.TestUtils;

import core.DriverManager;
import core.GlobalParams;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;

import org.openqa.selenium.*;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;
import java.util.stream.Collectors;

public class BasePage {
    private final AppiumDriver<?> driver;
    TestUtils utils = new TestUtils();

    protected static final String NOT_DISPLAY_MESSAGE = "Text [%s] is not display!";
    protected static final String NOT_FOUND_MESSAGE = "Text [%s] is not found";

    public BasePage() {
        this.driver = new DriverManager().getDriver();
        if (this.driver == null) {
            throw new RuntimeException("Driver is not initialized.");
        }
        System.out.println("Initializing PageFactory for BasePage...");
        PageFactory.initElements(new AppiumFieldDecorator(this.driver), this);
    }

    public AndroidDriver getAndroidDriver() {
        return (AndroidDriver) driver;
    }

    public IOSDriver getIOSDriver() {
        return (IOSDriver) driver;
    }

    public WebDriverWait createExplicitWait(long timeout) {
        return new WebDriverWait(this.driver, timeout);
    }

    public void waitForVisibility(MobileElement e) {
        WebDriverWait wait = new WebDriverWait(driver, TestUtils.WAIT);
        wait.until(ExpectedConditions.visibilityOf(e));
    }

    public void waitForVisibility(MobileElement e, long timeOUt) {
        WebDriverWait wait = new WebDriverWait(driver, timeOUt);
        wait.until(ExpectedConditions.visibilityOf(e));
    }

    public boolean waitForElementDisplayed(MobileElement e) {
        WebDriverWait wait = new WebDriverWait(driver, 10);
        try {
            wait.until(ExpectedConditions.visibilityOf(e));
        } catch (TimeoutException | NoSuchElementException ex) {
            return false;
        }
        return e.isDisplayed();
    }

    public boolean waitForElementDisplayed(By locator) {
        WebDriverWait wait = new WebDriverWait(driver, 5);
        try {
            MobileElement element =
                    (MobileElement)
                            wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
            return element.isDisplayed();
        } catch (TimeoutException | NoSuchElementException ex) {
            return false;
        }
    }

    public void waitForAllElementPresence(By by) {
        WebDriverWait wait = new WebDriverWait(driver, TestUtils.WAIT);
        wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(by));
    }

    public void waitForVisibility(By e) {
        WebDriverWait wait = new WebDriverWait(driver, TestUtils.WAIT);
        wait.until(visibilityOfElementLocated(e));
    }

    public void waitForVisibility(By e, long timeOut) {
        WebDriverWait wait = new WebDriverWait(driver, timeOut);
        wait.until(visibilityOfElementLocated(e));
    }

    public void clear(MobileElement e) {
        tryWaitElementClickable(e);
        e.clear();
    }

    public void click(MobileElement e) {
        utils.log().info("waitForElementClickable " + e);
        waitForElementClickable(e);
        utils.log().info("click " + e);
        e.click();
    }

    public void click(MobileElement e, String msg) {
        tryWaitElementClickable(e);
        utils.log().info(msg);
        e.click();
    }

    public void click(By e) {
        waitForVisibility(e);
        driver.findElement(e).click();
    }

    public void sendKeys(MobileElement e, String txt) {
        utils.log().info("waitForVisibility " + e);
        waitForVisibility(e);
        if (new GlobalParams().getPlatformName().equalsIgnoreCase("iOS")) {
            e.sendKeys(Keys.DELETE);
            e.sendKeys(Keys.DELETE);
            e.sendKeys(Keys.DELETE);
            e.sendKeys(Keys.DELETE);
            e.sendKeys(Keys.DELETE);
            e.sendKeys(Keys.DELETE);
            e.sendKeys(Keys.DELETE);
            e.sendKeys(Keys.DELETE);
            e.sendKeys(Keys.DELETE);
            e.sendKeys(Keys.DELETE);
        } else e.clear();
        utils.log().info("sendKeys " + e);
        e.sendKeys(txt);
    }

    public void sendKeys(MobileElement e, String txt, String msg) {
        waitForElementClickable(e);
        utils.log().info(msg);
        e.sendKeys(txt);
    }

    public String getAttribute(MobileElement e, String attribute) {
        waitForVisibility(e);
        return e.getAttribute(attribute);
    }

    public String getAttribute(By e, String attribute) {
        waitForVisibility(e);
        return driver.findElement(e).getAttribute(attribute);
    }

    public boolean verifyButtonClickable(MobileElement element) {
        try {
            waitForElementClickable(element);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public void closeApp() {
        driver.closeApp();
    }

    public void launchApp() {
        driver.launchApp();
    }

    //    @SneakyThrows
    //    public MobileElement scrollToElement(MobileElement element, String direction) {
    //        Dimension size = driver.manage().window().getSize();
    //        int startX = (int) (size.width * 0.5);
    //        int endX = (int) (size.width * 0.5);
    //        int startY = 0;
    //        int endY = 0;
    //        boolean isFound = false;
    //
    //        switch (direction) {
    //            case "up" -> {
    //                endY = (int) (size.height * 0.4);
    //                startY = (int) (size.height * 0.6);
    //            }
    //            case "down" -> {
    //                endY = (int) (size.height * 0.6);
    //                startY = (int) (size.height * 0.4);
    //            }
    //        }
    //
    //        for (int i = 0; i < 3; i++) {
    //            if (find(element, 1)) {
    //                isFound = true;
    //                break;
    //            } else {
    //                swipe(startX, startY, endX, endY, 10);
    //            }
    //        }
    //        if (!isFound) {
    //            throw new Exception("Element not found");
    //        }
    //        return element;
    //    }

    //    @SneakyThrows
    //    public void scroll(String direction) {
    //        Thread.sleep(3000);
    //        Dimension size = driver.manage().window().getSize();
    //        int startX = (int) (size.width * 0.5);
    //        int endX = (int) (size.width * 0.5);
    //        int startY;
    //        int endY;
    //
    //        switch (direction) {
    //            case "up" -> {
    //                endY = (int) (size.height * 0.4);
    //                startY = (int) (size.height * 0.6);
    //                swipe(startX, startY, endX, endY, 200);
    //            }
    //            case "down" -> {
    //                endY = (int) (size.height * 0.6);
    //                startY = (int) (size.height * 0.4);
    //                swipe(startX, startY, endX, endY, 200);
    //            }
    //        }
    //    }

    //    public By scrollToElement(By element, String direction) throws Exception {
    //        Dimension size = driver.manage().window().getSize();
    //        int startX = (int) (size.width * 0.5);
    //        int endX = (int) (size.width * 0.5);
    //        int startY = 0;
    //        int endY = 0;
    //        boolean isFound = false;
    //
    //        switch (direction) {
    //            case "up" -> {
    //                endY = (int) (size.height * 0.4);
    //                startY = (int) (size.height * 0.6);
    //            }
    //            case "down" -> {
    //                endY = (int) (size.height * 0.6);
    //                startY = (int) (size.height * 0.4);
    //            }
    //        }
    //
    //        for (int i = 0; i < 3; i++) {
    //            if (find(element, 1)) {
    //                isFound = true;
    //                break;
    //            } else {
    //                swipe(startX, startY, endX, endY, 10);
    //            }
    //        }
    //        if (!isFound) {
    //            throw new Exception("Element not found");
    //        }
    //        return element;
    //    }

    public boolean find(final MobileElement element, int timeout) {
        try {
            WebDriverWait wait = new WebDriverWait(driver, timeout);
            return wait.until((ExpectedCondition<Boolean>) driver -> element.isDisplayed());
        } catch (Exception e) {
            return false;
        }
    }

    public boolean find(final By element, int timeout) {
        try {
            WebDriverWait wait = new WebDriverWait(driver, timeout);
            return wait.until(
                    new ExpectedCondition<Boolean>() {
                        @Override
                        public Boolean apply(WebDriver driver) {
                            return driver.findElement(element).isDisplayed();
                        }
                    });
        } catch (Exception e) {
            return false;
        }
    }

    public void swipe(int startX, int startY, int endX, int endY, int millis)
            throws InterruptedException {
        TouchAction t = new TouchAction(driver);
        t.press(point(startX, startY))
                .waitAction(waitOptions(ofMillis(millis)))
                .moveTo(point(endX, endY))
                .release()
                .perform();
    }

    public MobileElement findElementContainText(List<MobileElement> mobileElements, String text) {
        List<MobileElement> elements =
                mobileElements.stream()
                        .filter(mobileElement -> mobileElement.getText().contains(text))
                        .collect(Collectors.toList());
        System.out.println(elements.size());
        if (elements.size() <= 1) return elements.get(0);
        else return elements.get(RandomUtil.getRandomNumber(elements.size() - 1));
    }

    public void waitForInvisibility(MobileElement e) {
        WebDriverWait wait = new WebDriverWait(driver, 30);
        wait.until(ExpectedConditions.invisibilityOf(e));
    }

    // Tap to an element for 250 milliseconds
    public void tapByElement(MobileElement e) {
        new TouchAction(driver)
                .tap(tapOptions().withElement(element(e)))
                .waitAction(waitOptions(ofMillis(250)))
                .perform();
    }

    public void tapByElementWithText(String text) {
        MobileElement element =
                (MobileElement)
                        driver.findElement(
                                By.xpath("//XCUIElementTypeStaticText[@name=\"" + text + "\"]"));
        new TouchAction(driver)
                .tap(tapOptions().withElement(element(element)))
                .waitAction(waitOptions(ofMillis(250)))
                .perform();
    }

    // Tap by coordinates
    public void tapByCoordinates(int x, int y) {
        if (new GlobalParams().getPlatformName().equalsIgnoreCase("iOS")) {
            new TouchAction(driver)
                    .tap(point(x, y))
                    .waitAction(waitOptions(ofMillis(250)))
                    .release()
                    .perform();
        } else {
            new TouchAction(driver)
                    .tap(point(x, y))
                    .waitAction(waitOptions(ofMillis(250)))
                    .perform();
        }
    }

    // Press by element
    public void pressByElement(MobileElement e, long seconds) {
        new TouchAction(driver)
                .press(element(e))
                .waitAction(waitOptions(ofSeconds(seconds)))
                .release()
                .perform();
    }

    public void waitForElementClickable(MobileElement elm) {
        WebDriverWait wait = new WebDriverWait(driver, 30);
        wait.until(ExpectedConditions.elementToBeClickable(elm));
    }

    public void waitForElementClickable(By locator) {
        WebDriverWait wait = new WebDriverWait(driver, TestUtils.WAIT);
        wait.until(ExpectedConditions.elementToBeClickable(locator));
    }
    //
    //    public void waitAllElementDisplay(By xpath) {
    //        WebDriverWait wait = new WebDriverWait(driver, 100);
    //        wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(xpath));
    //    }
    //
    //    public MobileElement scrollToElementWithText(String text) {
    //        MobileElement element =
    //                (MobileElement)
    //                        driver.findElement(
    //                                MobileBy.AndroidUIAutomator(
    //                                        "new UiScrollable(new"
    //                                                + "
    // UiSelector().scrollable(true)).scrollIntoView(new"
    //                                                + " UiSelector().text(\""
    //                                                + text
    //                                                + "\"))"));
    //        return element;
    //    }
    //
    //    public void swipe(int startX, int endX, int startY, int endY) {
    //        new TouchAction(driver)
    //                .longPress(PointOption.point(startX, startY))
    //                .moveTo(PointOption.point(endX, endY))
    //                .release()
    //                .perform();
    //    }
    //
    //    public void swipeUpDown(String status) {
    //        Dimension size = driver.manage().window().getSize();
    //        int startX, endX, startY, endY;
    //        if (status.equalsIgnoreCase("down")) {
    //            startX = endX = (size.width) / 2;
    //            startY = (int) ((size.height) * 0.8);
    //            endY = (int) ((size.height) * 0.2);
    //            swipe(startX, endX, startY, endY);
    //        } else if (status.equalsIgnoreCase("up")) {
    //            startX = endX = (size.width) / 2;
    //            startY = (int) ((size.height) * 0.2);
    //            endY = (int) ((size.height) * 0.8);
    //            swipe(startX, endX, startY, endY);
    //        }
    //    }
    //
    //    public void swipeLeftRight(String status) {
    //        Dimension size = driver.manage().window().getSize();
    //        int startX, endX, startY, endY;
    //        if (status.equalsIgnoreCase("right")) {
    //            endX = (int) ((size.width) * 0.2);
    //            startX = (int) ((size.width) * 0.8);
    //            startY = endY = size.height / 2;
    //            swipe(startX, endX, startY, endY);
    //        } else if (status.equalsIgnoreCase("left")) {
    //            endX = (int) ((size.width) * 0.2);
    //            startX = (int) ((size.width) * 0.8);
    //            startY = endY = size.height / 2;
    //            swipe(endX, startX, startY, endY);
    //        }
    //    }
    //
    //    public void scrollToElement(String status, By locator) {
    //        MobileElement element = null;
    //        for (; ; ) {
    //            try {
    //                element = (MobileElement) driver.findElement(locator);
    //            } catch (Exception e) {
    //            }
    //            if (element == null) {
    //                swipeUpDown(status);
    //            } else {
    //                verifyButtonClickable(element);
    //                break;
    //            }
    //        }
    //    }
    //
    //    public void scrollLeftRightToElement(String status, By locator) {
    //        MobileElement element = null;
    //        for (; ; ) {
    //            try {
    //                element = (MobileElement) driver.findElement(locator);
    //
    //            } catch (Exception e) {
    //            }
    //            if (element == null) {
    //                swipeLeftRight(status);
    //            } else {
    //                break;
    //            }
    //        }
    //    }
    //
    //    public void swipeMobileRightToLeft() {
    //        Dimension size = driver.manage().window().getSize();
    //        int startY = (int) size.height / 2;
    //        int startX = (int) (size.width * 0.8);
    //        int endX = (int) (size.width * 0.2);
    //        swipe(startX, endX, startY, startY);
    //    }
    //
    //    public void back() {
    //        driver.navigate().back();
    //    }
    //
    public void tryWaitElementClickable(MobileElement element) {
        try {
            waitForElementClickable(element);
        } catch (NoSuchElementException | StaleElementReferenceException | TimeoutException e) {
            waitForElementClickable(element);
        }
    }

    public MobileElement findElementNotContainText(
            List<MobileElement> mobileElements, String text) {
        List<MobileElement> elementNotContains =
                mobileElements.stream()
                        .filter(
                                mobileElement ->
                                        !mobileElement.getText().contains(text)
                                                && !mobileElement.getText().contains("G95 Apparel")
                                                && !mobileElement.getText().contains("Berrylook"))
                        .collect(Collectors.toList());
        return elementNotContains.get(RandomUtil.getRandomNumber(elementNotContains.size() - 1));
    }
    //
    //    public MobileElement snackBar() {
    //        FluentWait<WebDriver> wait =
    //                new WebDriverWait(driver, 10, 100).ignoring(NoSuchElementException.class);
    //        return (MobileElement) wait.until(visibilityOfElementLocated(By.id("snackbar_text")));
    //    }
    //
    //    public void refreshApp() {
    //        driver.resetApp();
    //    }
    //
    //    public void searchFromKeyboard() {
    //        driver.executeScript("mobile: performEditorAction", ImmutableMap.of("action",
    // "search"));
    //    }
    //
    //    public void sendKeyEvent(String key) {
    //        for (String c : key.split("(?!^)")) {
    //            click(By.xpath(String.format("//XCUIElementTypeKey[@name=\"%s\"]", c)));
    //        }
    //    }
    //
    //    public void scrollUntilElementWithText_iOS(String text) {
    //        int maxScroll = 10;
    //        By elementLocator =
    //                By.xpath(String.format("//XCUIElementTypeStaticText[@name=\"%s\"]", text));
    //        List<WebElement> elements = (List<WebElement>) driver.findElements(elementLocator);
    //        boolean isPresent = !elements.isEmpty();
    //        boolean isVisible = false;
    //        // If found elements, check if it is visible to avoid index out of bound
    //        if (isPresent) {
    //            isVisible = elements.get(0).isDisplayed();
    //        }
    //
    //        while ((!isPresent || !isVisible) && maxScroll > 0) {
    //
    //            Dimension dimension = driver.manage().window().getSize();
    //            int scrollStart = (int) (dimension.getHeight() * 0.4);
    //            int scrollEnd = (int) (dimension.getHeight() * 0.3);
    //
    //            new IOSTouchAction(driver)
    //                    .press(PointOption.point(0, scrollStart))
    //                    .waitAction(WaitOptions.waitOptions(Duration.ofMillis(100)))
    //                    .moveTo(PointOption.point(0, scrollEnd))
    //                    .release()
    //                    .perform();
    //
    //            maxScroll--;
    //
    //            elements = (List<WebElement>) driver.findElements(elementLocator);
    //            isPresent = !elements.isEmpty();
    //            if (isPresent) {
    //                isVisible = elements.get(0).isDisplayed();
    //            }
    //        }
    //    }
    //
    //    public void scrollUntilElementWithText_Android(String text) {
    //        int maxScroll = 10;
    //        By elementLocator = By.xpath(String.format("//*[@text=\"%s\"]", text));
    //        try {
    //            driver.findElements(elementLocator);
    //        } catch (Exception e) {
    //        }
    //        List<WebElement> elements = (List<WebElement>) driver.findElements(elementLocator);
    //        boolean isPresent = !elements.isEmpty();
    //        boolean isVisible = false;
    //        // If found elements, check if it is visible to avoid index out of bound
    //        if (isPresent) {
    //            isVisible = elements.get(0).isDisplayed();
    //        }
    //
    //        while ((!isPresent || !isVisible) && maxScroll > 0) {
    //
    //            Dimension dimension = driver.manage().window().getSize();
    //            int scrollStart = (int) (dimension.getHeight() * 0.8);
    //            int scrollEnd = (int) (dimension.getHeight() * 0.5);
    //
    //            new AndroidTouchAction(driver)
    //                    .press(PointOption.point(200, scrollStart))
    //                    .waitAction(WaitOptions.waitOptions(Duration.ofMillis(500)))
    //                    .moveTo(PointOption.point(200, scrollEnd))
    //                    .release()
    //                    .perform();
    //
    //            maxScroll--;
    //
    //            elements = (List<WebElement>) driver.findElements(elementLocator);
    //            isPresent = !elements.isEmpty();
    //            if (isPresent) {
    //                isVisible = elements.get(0).isDisplayed();
    //            }
    //        }
    //    }
    //
    //    public void scrollUntilElementWithAndroidWidgetText(String text) {
    //        int maxScroll = 10;
    //        By elementLocator =
    //                By.xpath(String.format("//android.widget.TextView[@text=\"%s\"]", text));
    //        List<WebElement> elements = (List<WebElement>) driver.findElements(elementLocator);
    //        boolean isPresent = !elements.isEmpty();
    //        boolean isVisible = false;
    //        // If found elements, check if it is visible to avoid index out of bound
    //        if (isPresent) {
    //            isVisible = elements.get(0).isDisplayed();
    //        }
    //
    //        while ((!isPresent || !isVisible) && maxScroll > 0) {
    //
    //            Dimension dimension = driver.manage().window().getSize();
    //            int scrollStart = (int) (dimension.getHeight() * 0.8);
    //            int scrollEnd = (int) (dimension.getHeight() * 0.5);
    //
    //            new AndroidTouchAction(driver)
    //                    .press(PointOption.point(200, scrollStart))
    //                    .waitAction(WaitOptions.waitOptions(Duration.ofMillis(500)))
    //                    .moveTo(PointOption.point(200, scrollEnd))
    //                    .release()
    //                    .perform();
    //
    //            maxScroll--;
    //
    //            elements = (List<WebElement>) driver.findElements(elementLocator);
    //            isPresent = !elements.isEmpty();
    //            if (isPresent) {
    //                isVisible = elements.get(0).isDisplayed();
    //            }
    //        }
    //    }
    //
    //    public void backUntilElementWithText_iOS(String text) {
    //        int maxScroll = 10;
    //        By elementLocator = By.xpath(String.format("//XCUIElementTypeButton[@name=\"%s\"]",
    // text));
    //        List<WebElement> elements = (List<WebElement>) driver.findElements(elementLocator);
    //        boolean isPresent = !elements.isEmpty();
    //        boolean isVisible = false;
    //        // If found elements, check if it is visible to avoid index out of bound
    //        if (isPresent) {
    //            isVisible = elements.get(0).isDisplayed();
    //        }
    //        while ((!isPresent || !isVisible) && maxScroll > 0) {
    //            back();
    //            elements = (List<WebElement>) driver.findElements(elementLocator);
    //            isPresent = !elements.isEmpty();
    //            if (isPresent) {
    //                isVisible = elements.get(0).isDisplayed();
    //            }
    //        }
    //    }
    //
    //    public void acceptAlert() {
    //        WebDriverWait wait = new WebDriverWait(driver, TestUtils.WAIT);
    //        wait.until(ExpectedConditions.alertIsPresent());
    //        driver.switchTo().alert().accept();
    //    }
    //
    //    public void tapIconContainsText(String locator, String name) {
    //        var elementLocator = By.xpath(String.format(locator, name));
    //        try {
    //
    // createExplicitWait(10).until(ExpectedConditions.elementToBeClickable(elementLocator));
    //        } catch (TimeoutException e) {
    //            if (new GlobalParams().getPlatformName().equalsIgnoreCase("Android")) {
    //                scrollUntilElementWithText_Android(name);
    //            } else {
    //                scrollUntilElementWithText_iOS(name);
    //            }
    //        }
    //        driver.findElement(elementLocator).click();
    ////        try {
    ////            if (driver.findElement(elementLocator).isDisplayed()) {
    ////                driver.findElement(elementLocator).click();
    ////            }
    ////        } catch (NoSuchElementException e) {
    ////
    ////        }
    //    }
    //
    //    @SneakyThrows
    //    public void verifyTextIsDisplayed(String locator, String text) {
    //        var elementLocator = By.xpath(String.format(locator, text));
    //        Thread.sleep(2000);
    //        if (new GlobalParams().getPlatformName().equalsIgnoreCase("iOS")) {
    //            scrollUntilElementWithText_iOS(text);
    //        } else {
    //            //            scrollToElementWithText(text);
    //            //            scrollToElement("down",By.xpath(locator));
    //            scrollUntilElementWithText_Android(text);
    //        }
    //        try {
    //            createExplicitWait(20)
    //                    .until(ExpectedConditions.presenceOfElementLocated(elementLocator));
    //            Assert.assertTrue(
    //                    driver.findElement(elementLocator).isDisplayed(),
    //                    String.format(NOT_DISPLAY_MESSAGE, text));
    //            utils.log().info("Field '" + text + "' is display");
    //        } catch (NoSuchElementException e) {
    //            Assert.fail(String.format(NOT_FOUND_MESSAGE, text));
    //        }
    //    }
    //
    //    public MobileElement androidElementWithText(String text) {
    //        By elementLocator = By.xpath(String.format("//*[contains(@text, '%s')]", text));
    //        waitForVisibility(elementLocator);
    //        return (MobileElement) driver.findElement(elementLocator);
    //    }
    //
    //    public void swipeLeftRightByElements(MobileElement element, String status) {
    //
    //        int startX = element.getLocation().getX() + element.getSize().getWidth() - 70;
    //        int startY = element.getLocation().getY() + (int) (element.getSize().getHeight() / 2);
    //
    //        int endX = element.getLocation().getX() + 150;
    //
    //        if (status.equalsIgnoreCase("left")) {
    //            new TouchAction(driver)
    //                    .press(point(startX, startY))
    //                    .waitAction(waitOptions(ofMillis(1000)))
    //                    .moveTo(point(endX, startY))
    //                    .release()
    //                    .perform();
    //        } else {
    //            new TouchAction(driver)
    //                    .press(point(endX, startY))
    //                    .waitAction(waitOptions(ofMillis(1000)))
    //                    .moveTo(point(startX, startY))
    //                    .release()
    //                    .perform();
    //        }
    //    }
    //
    //    public void swipeUntilElementWithText(MobileElement element, String status, String text) {
    //        int maxScroll = 10;
    //        By elementLocator;
    //        if (new GlobalParams().getPlatformName().equalsIgnoreCase("iOS")) {
    //            elementLocator =
    //                    By.xpath(String.format("//XCUIElementTypeStaticText[@name=\"%s\"]",
    // text));
    //        } else {
    //            elementLocator = By.xpath(String.format("//*[@text=\"%s\"]", text));
    //        }
    //        List<WebElement> elements = (List<WebElement>) driver.findElements(elementLocator);
    //        boolean isPresent = !elements.isEmpty();
    //        boolean isVisible = false;
    //        // If found elements, check if it is visible to avoid index out of bound
    //        if (isPresent) {
    //            isVisible = elements.get(0).isDisplayed();
    //        }
    //
    //        while ((!isPresent || !isVisible) && maxScroll > 0) {
    //
    //            swipeLeftRightByElements(element, status);
    //
    //            maxScroll--;
    //
    //            elements = (List<WebElement>) driver.findElements(elementLocator);
    //            isPresent = !elements.isEmpty();
    //            if (isPresent) {
    //                isVisible = elements.get(0).isDisplayed();
    //            }
    //        }
    //    }
    //
    //    public void scrollUntilElementWithContainsText_iOS(String text) {
    //        int maxScroll = 10;
    //        By elementLocator =
    //                By.xpath(
    //                        String.format("//XCUIElementTypeStaticText[contains(@name,\"%s\")]",
    // text));
    //        List<WebElement> elements = (List<WebElement>) driver.findElements(elementLocator);
    //        boolean isPresent = !elements.isEmpty();
    //        boolean isVisible = false;
    //        // If found elements, check if it is visible to avoid index out of bound
    //        if (isPresent) {
    //            isVisible = elements.get(0).isDisplayed();
    //        }
    //
    //        while ((!isPresent || !isVisible) && maxScroll > 0) {
    //
    //            Dimension dimension = driver.manage().window().getSize();
    //            int scrollStart = (int) (dimension.getHeight() * 0.4);
    //            int scrollEnd = (int) (dimension.getHeight() * 0.3);
    //
    //            new IOSTouchAction(driver)
    //                    .press(PointOption.point(0, scrollStart))
    //                    .waitAction(WaitOptions.waitOptions(Duration.ofMillis(100)))
    //                    .moveTo(PointOption.point(0, scrollEnd))
    //                    .release()
    //                    .perform();
    //
    //            maxScroll--;
    //
    //            elements = (List<WebElement>) driver.findElements(elementLocator);
    //            isPresent = !elements.isEmpty();
    //            if (isPresent) {
    //                isVisible = elements.get(0).isDisplayed();
    //            }
    //        }
    //    }
    //
    //    public void scrollUntilElementWithContainsText_Android(String text) {
    //        int maxScroll = 10;
    //        By elementLocator = By.xpath(String.format("//*[contains(@text,\"%s\")]", text));
    //        List<WebElement> elements = (List<WebElement>) driver.findElements(elementLocator);
    //        boolean isPresent = !elements.isEmpty();
    //        boolean isVisible = false;
    //        boolean isClickable = elements.get(0).isEnabled();
    //        // If found elements, check if it is visible to avoid index out of bound
    //        if (isPresent) {
    //            isVisible = elements.get(0).isDisplayed();
    //        }
    //
    //        while ((!isPresent || !isVisible || !isClickable) && maxScroll > 0) {
    //
    //            Dimension dimension = driver.manage().window().getSize();
    //            int scrollStart = (int) (dimension.getHeight() * 0.8);
    //            int scrollEnd = (int) (dimension.getHeight() * 0.5);
    //
    //            new AndroidTouchAction(driver)
    //                    .press(PointOption.point(200, scrollStart))
    //                    .waitAction(WaitOptions.waitOptions(Duration.ofMillis(500)))
    //                    .moveTo(PointOption.point(200, scrollEnd))
    //                    .release()
    //                    .perform();
    //
    //            maxScroll--;
    //
    //            elements = (List<WebElement>) driver.findElements(elementLocator);
    //            isPresent = !elements.isEmpty();
    //            if (isPresent) {
    //                isVisible = elements.get(0).isDisplayed();
    //            }
    //        }
    //    }

    //    public MobileElement findElementWithIOSPredicate(String iOSString){
    //        return (MobileElement) driver.findElement(MobileBy.iOSNsPredicateString(iOSString));
    //    }
}
