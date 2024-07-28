package com.qa.pages;

import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AndroidFindBy;

public class SettingPage extends BasePage {
    @AndroidFindBy(xpath = "//android.view.View[@content-desc=\"Thiết lập Digital OTP\"]")
    private MobileElement ThietLapDigitalTP_view;

    public void clickThietLapDigitalTP_view() {
        click(ThietLapDigitalTP_view);
    }
}
