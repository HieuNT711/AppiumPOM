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

    @AndroidFindBy(xpath = "//android.view.View[@content-desc=\"Bỏ qua\"]")
    private MobileElement Boqua_view;

    @AndroidFindBy(
            xpath = "//android.view.View[@content-desc=\"Tổng số dư VND\n" + "*** *** VND\"]")
    private MobileElement TongSoDu_view;

    @AndroidFindBy(
            xpath =
                    "//android.view.View[@content-desc=\"Trang"
                            + " chủ\"]/preceding-sibling::android.view.View[5]")
    private MobileElement Profile_view;

    @AndroidFindBy(
            xpath =
                    "//android.view.View[@content-desc=\"Trang"
                            + " chủ\"]/preceding-sibling::android.view.View[3]")
    private MobileElement QuaChuong_view;

    @AndroidFindBy(
            xpath =
                    "//android.view.View[@content-desc=\"Trang"
                            + " chủ\"]/preceding-sibling::android.view.View[2]")
    private MobileElement Search_view;

    public void clickChuyenTien_view() {
        //        if (waitForElementDisplayed(Boqua_view)) {
        //            click(Boqua_view);
        //            if (waitForElementDisplayed(Boqua_view)) {
        //                click(Boqua_view);
        //            }
        //        }
        click(ChuyenTien_view);
    }

    public void clickProfile() {
        waitClickableIsTrue(Profile_view, 5);
        click(Profile_view);
    }
}
