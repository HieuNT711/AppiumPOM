package com.qa.pages.transfer;

import com.qa.pages.BasePage;

import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;

import lombok.SneakyThrows;

public class InquiryPage extends BasePage {

    @iOSXCUITFindBy(accessibility = "shop now right arrow")
    @AndroidFindBy(xpath = "//android.view.View[@content-desc=\"Chuyển tiền tới số tài khoản\"]")
    private MobileElement ChuyenTienToiSoTaiKhoan_view;

    @iOSXCUITFindBy(accessibility = "shop now right arrow")
    @AndroidFindBy(
            xpath =
                    "//android.view.View[@content-desc=\"Chuyển"
                            + " đến\"]/following-sibling::android.view.View[1]")
    private MobileElement NganHang_view;

    @iOSXCUITFindBy(accessibility = "shop now right arrow")
    @AndroidFindBy(accessibility = "MBBank (MB)\n" + "Ngân hàng TMCP Quân đội")
    private MobileElement MBBank_view;

    @iOSXCUITFindBy(accessibility = "shop now right arrow")
    @AndroidFindBy(
            xpath =
                    "//android.view.View[@content-desc=\"Chuyển"
                            + " đến\"]/following-sibling::android.view.View[1]//android.widget.EditText")
    private MobileElement SoTaiKhoan_edit;

    @iOSXCUITFindBy(accessibility = "shop now right arrow")
    @AndroidFindBy(
            xpath =
                    "//android.view.View[@content-desc=\"Chuyển"
                            + " đến\"]/following-sibling::android.widget.EditText[1]")
    private MobileElement Amount_view;

    @iOSXCUITFindBy(accessibility = "shop now right arrow")
    @AndroidFindBy(
            xpath =
                    "//android.view.View[@content-desc=\""
                            + " VND\"]/following-sibling::android.widget.EditText[1]")
    private MobileElement Amount_edit;

    @iOSXCUITFindBy(accessibility = "shop now right arrow")
    @AndroidFindBy(
            xpath =
                    "//android.view.View[@content-desc=\""
                            + " VND\"]/following-sibling::android.view.View[4]")
    private MobileElement NoiDungChuyenTien_x_view;

    @iOSXCUITFindBy(accessibility = "shop now right arrow")
    @AndroidFindBy(
            xpath =
                    "//android.view.View[@content-desc=\"Chuyển"
                            + " đến\"]/following-sibling::android.widget.EditText[2]")
    private MobileElement NoiDungChuyenTien_edit;

    @iOSXCUITFindBy(accessibility = "shop now right arrow")
    @AndroidFindBy(xpath = "//android.view.View[@content-desc=\"Tiếp tục\"]")
    private MobileElement TiepTuc_view;

    @SneakyThrows
    public void nhapThongTinChuyenTien(String receiveNumber, String amount) {
        click(NganHang_view);
        click(MBBank_view);
        click(SoTaiKhoan_edit);
        sendKeys(SoTaiKhoan_edit, receiveNumber);
        click(ChuyenTienToiSoTaiKhoan_view);
        Thread.sleep(3000);
        click(Amount_view);
        sendKeys(Amount_edit, amount);
        click(TiepTuc_view);
    }
}
