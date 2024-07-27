package com.qa.stepdef;

import com.qa.pages.PageInjection;
import com.utils.TestUtils;

import context.ScenarioContext;

import io.cucumber.java.en.When;

public class BaseSteps extends PageInjection {
    //    IntroPage introPage = new IntroPage();
    protected final ScenarioContext scenarioContext;
    TestUtils utils = new TestUtils();

    public BaseSteps() {
        this.scenarioContext = ScenarioContext.getInstance();
    }

    @When("I login in MB bank")
    public void loginAPI() {
        introPage.verifyTaiKhoanKhac_btnIsPresent();
        introPage.loginApp();
    }

    //    @When("Back to {string} page")
    //    public void backToHomePage(String screen) {
    //        menuPage.pressReturn();
    //        menuPage.backUntilElementWithText_iOS(screen);
    //    }
    //
    //    @SneakyThrows
    //    @Then("Web view must display with text {string}")
    //    public void verifyWebViewDisplay(String text) {
    //        Thread.sleep(5000);
    //        assetsPage.verifyTextFieldDisplay(XPATH_LABEL_BY_TEXT, text);
    //    }
    //
    //    @Then("Web view must display with text {string} devices Android")
    //    public void verifyWebViewDisplayAndroidDevices(String text) {
    //        assetsPage.verifyTextFieldDisplay(XPATH_BY_TEXT_ANDROID, text);
    //    }
    //
    //    @When("I scroll and press to {string}")
    //    public void pressScrollElement(String text) {
    //        if (new GlobalParams().getPlatformName().equalsIgnoreCase("Android"))
    //            shoppingPage.tapIconContainsText(XPATH_FOLLOWING_BY_TEXT_ANDROID, text);
    //        else shoppingPage.tapIconContainsText(XPATH_FOLLOWING_BY_TEXT_IOS, text);
    //    }
    //
    //    @When("I swipe {string}")
    //    public void swipeRightLeft(String direction) {
    //        swipeLeftRight(direction);
    //    }
    //
    //    @When("I scroll {string}")
    //    public void scrollUpDown(String direction) {
    //        scroll(direction);
    //    }
    //
    //    @Then("With text {string} must be show in this screen")
    //    public void verifyTextMustBeShow(String text) {
    //        String getText;
    //        try {
    //            if (new GlobalParams().getPlatformName().equalsIgnoreCase("iOS")) {
    //                getText = getText(By.xpath(String.format(XPATH_LABEL_BY_TEXT, text)), "Get
    // text of: " + text);
    //            } else {
    //                getText = getText(By.xpath(String.format(XPATH_BY_TEXT_ANDROID, text)), "Get
    // text of: " + text);
    //            }
    //            Assert.assertTrue(getText.equals(text), "Verify text");
    //        } catch (Exception e) {
    //            Assert.fail("Text not show in this screen");
    //        }
    //    }
    //
    //    @And("Text must match with pattern {string}")
    //    public void verifyQRCode(String pattern) {
    //        Assert.assertTrue(RegexUtils.isPatternMatched(pattern, getText(By.xpath(pattern), "Get
    // text")), "Qr text not match");
    //    }
}
