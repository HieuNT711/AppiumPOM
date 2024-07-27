package com.qa.pages.transfer;

import com.qa.pages.BasePage;

import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;

public class TypeTransferPage extends BasePage {

    @iOSXCUITFindBy(accessibility = "shop now right arrow")
    @AndroidFindBy(xpath = "//android.view.View[@content-desc=\"Số tài\n" + "khoản\"]")
    private MobileElement SoTaiKhoan_view;

    @iOSXCUITFindBy(accessibility = "shop now right arrow")
    @AndroidFindBy(xpath = "//android.view.View[@content-desc=\"Số\n" + "điện thoại\"]")
    private MobileElement SoDienThoai_view;

    @iOSXCUITFindBy(accessibility = "shop now right arrow")
    @AndroidFindBy(xpath = "//android.view.View[@content-desc=\"Số thẻ\"]")
    private MobileElement SoThe_view;

    @iOSXCUITFindBy(accessibility = "shop now right arrow")
    @AndroidFindBy(xpath = "//android.view.View[@content-desc=\"Đối tác MB\"]")
    private MobileElement DoiTacMB_view;

    public void clickSoTaiKhoan_btn() {
        click(SoTaiKhoan_view);
    }
}
