package com.qa.pages;

import com.qa.runners.RunnerBase;

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
    @AndroidFindBy(xpath = "//android.view.View[@content-desc=\"Tài khoản khác\"]")
    private MobileElement TaiKhoanKhac_view;

    @iOSXCUITFindBy(accessibility = "shop now right arrow")
    @AndroidFindBy(
            xpath =
                    "//android.view.View[@content-desc=\"Chào mừng bạn\n"
                            + "đến với MB Bank\"]/following-sibling::android.widget.EditText[1]")
    private MobileElement TenDangNhap_edit;

    @AndroidFindBy(
            xpath =
                    "//android.view.View[@content-desc=\"Chào mừng bạn\n"
                            + "đến với MB Bank\"]/following-sibling::android.widget.EditText[2]")
    private MobileElement MatKhau_edit;

    @AndroidFindBy(xpath = "//android.view.View[@content-desc=\"Đăng nhập\"]")
    private MobileElement DangNhap_view;

    @AndroidFindBy(xpath = "//android.view.View[@content-desc='Chào mừng bạn\\nđến với MBBank']")
    private MobileElement title_view;

    public void verifyTaiKhoanKhac_btnIsPresent() {
        if (waitForElementDisplayed(TaiKhoanKhac_view)) {
            click(TaiKhoanKhac_view);
        }
    }

    public void loginApp() {
        click(TenDangNhap_edit);
        sendKeys(TenDangNhap_edit, RunnerBase.userName);
        click(MatKhau_edit);
        sendKeys(MatKhau_edit, RunnerBase.passWord);
        click(DangNhap_view);
    }
}
