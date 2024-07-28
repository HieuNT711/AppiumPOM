package com.qa.pages;

import enums.GlobalVariables;

import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AndroidFindBy;

public class DigitalOTPPage extends BasePage {
    @AndroidFindBy(xpath = "//android.view.View[@content-desc=\"Đăng ký Digital OTP\"]")
    private MobileElement DangKiDOTP_view;

    @AndroidFindBy(
            xpath =
                    "//android.view.View[@content-desc=\"Bỏ"
                        + " qua\"]/following-sibling::android.view.View[1]//android.widget.EditText[1]")
    private MobileElement NhapSMS_edit;

    @AndroidFindBy(
            xpath =
                    "//android.view.View[contains(@content-desc, \"Đặt mã"
                            + " PIN\")]/android.widget.EditText[1]")
    private MobileElement NhapDOTPLan1_edit;

    @AndroidFindBy(
            xpath =
                    "//android.view.View[contains(@content-desc, \"Đặt mã"
                            + " PIN\")]/android.widget.EditText[2]")
    private MobileElement NhapDOTPLan2_edit;

    @AndroidFindBy(
            xpath =
                    "//android.view.View[contains(@content-desc, \"Tôi đã đọc, hiểu và đồng ý với"
                            + " các điều khoản và điều kiện của MB về:\")]/android.view.View")
    private MobileElement ConfirmDOTP_view;

    @AndroidFindBy(xpath = "//android.view.View[@content-desc=\"Tiếp tục\"]")
    private MobileElement TiepTuc_view;

    public boolean checkDOTP() {
        Boolean checkDOTP = false;
        if (waitForElementDisplayed(DangKiDOTP_view)) {
            waitClickableIsTrue(DangKiDOTP_view, 3);
            click(DangKiDOTP_view);
            checkDOTP = true;
        }
        return checkDOTP;
    }

    public void nhapSMS() {
        waitForElementDisplayed(NhapSMS_edit);
        sendKeys(NhapSMS_edit, GlobalVariables.SMS);
    }

    public void dangKiDOTP() {
        waitForElementDisplayed(NhapDOTPLan1_edit);
        sendKeys(NhapDOTPLan1_edit, GlobalVariables.DOTP);
        sendKeys(NhapDOTPLan2_edit, GlobalVariables.DOTP);
        click(ConfirmDOTP_view);
        waitClickableIsTrue(TiepTuc_view, 5);
        click(TiepTuc_view);
    }
}
