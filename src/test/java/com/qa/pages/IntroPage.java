package com.qa.pages;

import context.ScenarioContext;

import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;

public class IntroPage extends BasePage {
    protected final ScenarioContext scenarioContext;

    public IntroPage() {
        this.scenarioContext = ScenarioContext.getInstance();
    }

    @iOSXCUITFindBy(accessibility = "shop now right arrow")
    @AndroidFindBy(xpath = "//*[@resource-id='com.banggood.client:id/tab_text']")
    private MobileElement accountButton;

    @iOSXCUITFindBy(accessibility = "shop now right arrow")
    @AndroidFindBy(id = "com.socialgood_foundation.sg_app_android_dev:id/ivShopNow")
    private MobileElement shopNowButton;

    @AndroidFindBy(
            xpath =
                    "//android.widget.FrameLayout[@content-desc=\"Staking\"]/android.view.ViewGroup/android.widget.TextView")
    private MobileElement stakingButton;

    public void tapShopNowButton() {
        waitForVisibility(stakingButton, 10);
        click(stakingButton, "AB");
    }
}
