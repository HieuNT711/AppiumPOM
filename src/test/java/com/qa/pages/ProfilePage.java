package com.qa.pages;

import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AndroidFindBy;

public class ProfilePage extends BasePage {

    @AndroidFindBy(
            xpath =
                    "//android.view.View[@content-desc=\"Cấu"
                        + " hình\"]/ancestor::android.view.View[5]/preceding-sibling::android.view.View[1]")
    private MobileElement x_view;

    @AndroidFindBy(
            xpath =
                    "//android.view.View[@content-desc=\"Cấu"
                        + " hình\"]/ancestor::android.view.View[5]/preceding-sibling::android.view.View[2]")
    private MobileElement HoSoNguoiDung_view;

    @AndroidFindBy(xpath = "//android.view.View[@content-desc=\"Cấu hình\"]")
    private MobileElement CauHinh_view;

    @AndroidFindBy(xpath = "//android.view.View[@content-desc=\"Cài đặt\"]")
    private MobileElement CaiDat_view;

    @AndroidFindBy(xpath = "//android.widget.ImageView[@content-desc=\"Hội viên MB\"]")
    private MobileElement HoiVienMB_view;

    public void clickCaiDat_view() {
        click(CaiDat_view);
    }
}
