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
    private MobileElement TaiKhoanKhac_btn;

    @iOSXCUITFindBy(accessibility = "shop now right arrow")
    @AndroidFindBy(
            xpath =
                    "//android.view.View[@content-desc=\"Chào mừng bạn\n"
                            + "đến với MB Bank\"]/following-sibling::android.widget.EditText[1]")
    private MobileElement TenDangNhap_input;

    @AndroidFindBy(
            xpath =
                    "//android.view.View[@content-desc=\"Chào mừng bạn\n"
                            + "đến với MB Bank\"]/following-sibling::android.widget.EditText[2]")
    private MobileElement MatKhau_input;

    @AndroidFindBy(xpath = "//android.view.View[@content-desc=\"Đăng nhập\"]")
    private MobileElement DangNhap_btn;

    @AndroidFindBy(xpath = "//android.view.View[@content-desc='Chào mừng bạn\\nđến với MBBank']")
    private MobileElement title_txt;

    public void verifyTaiKhoanKhac_btnIsPresent() {
        if (waitForElementDisplayed(TaiKhoanKhac_btn)) {
            click(TaiKhoanKhac_btn);
        }
    }

    public void loginApp() {
        click(TenDangNhap_input);
        sendKeys(TenDangNhap_input, RunnerBase.userName);
        click(MatKhau_input);
        sendKeys(MatKhau_input, RunnerBase.passWord);
        click(DangNhap_btn);
    }
}
