package com.qa.pages.transfer;

import com.qa.pages.BasePage;

import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;

public class VerifyPage extends BasePage {
    @iOSXCUITFindBy(accessibility = "shop now right arrow")
    @AndroidFindBy(xpath = "//android.view.View[@content-desc=\"Xác nhận\"]")
    private MobileElement XacNhan_view;

    @iOSXCUITFindBy(accessibility = "shop now right arrow")
    @AndroidFindBy(
            xpath =
                    "//android.view.View[@content-desc=\"Xác thực Digital OTP\n"
                            + "Vui lòng nhập mã PIN Digital OTP để nhận mã xác thực giao"
                            + " dịch\"]/android.widget.EditText")
    private MobileElement Dotp_edit;

    @iOSXCUITFindBy(accessibility = "shop now right arrow")
    @AndroidFindBy(accessibility = "Xác thực")
    private MobileElement XacThuc_view;

    public void xacNhanChuyenTienDOTP(String dotp) {
        waitForElementDisplayed(XacNhan_view);
        click(XacNhan_view);
        click(Dotp_edit);
        sendKeys(Dotp_edit, dotp);
        click(XacThuc_view);
    }
}
