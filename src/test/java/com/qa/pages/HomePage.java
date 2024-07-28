package com.qa.pages;

import context.ScenarioContext;

import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;

public class HomePage extends BasePage {

    protected final ScenarioContext scenarioContext;

    public HomePage() {
        this.scenarioContext = ScenarioContext.getInstance();
    }

    @iOSXCUITFindBy(accessibility = "shop now right arrow")
    @AndroidFindBy(xpath = "//android.view.View[@content-desc=\"Chuyển tiền\"]")
    private MobileElement ChuyenTien_view;

    @iOSXCUITFindBy(accessibility = "shop now right arrow")
    @AndroidFindBy(xpath = "//android.view.View[@content-desc=\"Bỏ qua\"]")
    private MobileElement Boqua_view;

    public void clickChuyenTien_btn() {
        //        if (waitForElementDisplayed(Boqua_view)) {
        //            click(Boqua_view);
        //            if (waitForElementDisplayed(Boqua_view)) {
        //                click(Boqua_view);
        //            }
        //        }
        click(ChuyenTien_view);
    }
}
