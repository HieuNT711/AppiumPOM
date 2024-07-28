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
    @AndroidFindBy(
            xpath =
                    "//android.widget.ImageView[@content-desc=\"MBBank (MB)\n"
                            + "Ngân hàng TMCP Quân"
                            + " đội\"]/../../preceding-sibling::android.widget.EditText")
    private MobileElement ChonNganHang_edit;

    @iOSXCUITFindBy(accessibility = "shop now right arrow")
    @AndroidFindBy(accessibility = "MBBank (MB)\n" + "Ngân hàng TMCP Quân đội")
    private MobileElement MBBank_view;

    @iOSXCUITFindBy(accessibility = "shop now right arrow")
    @AndroidFindBy(
            xpath =
                    "//android.widget.ImageView[@content-desc=\"DongA Bank (DAB)\n"
                            + "Ngân hàng TMCP Đông Á\"]")
    private MobileElement DABBank_view;

    @iOSXCUITFindBy(accessibility = "shop now right arrow")
    @AndroidFindBy(
            xpath =
                    "//android.widget.ImageView[@content-desc=\"Techcombank (TCB)\n"
                            + "Ngân hàng TMCP Kỹ Thương Việt Nam\"]")
    private MobileElement TCBBank_view;

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

    @iOSXCUITFindBy(accessibility = "shop now right arrow")
    @AndroidFindBy(xpath = "//android.view.View[@content-desc=\"Đã hiểu\"]")
    private MobileElement DaHieu_view; // cảnh báo lừa đảo A05 popup

    @SneakyThrows
    public void nhapThongTinChuyenTien(String bankCode, String receiveNumber, String amount) {
        chonNganHangThuHuong(bankCode);
        nhapThongTinNguoiNhan(receiveNumber);
        nhapSoTien(amount);
        click(TiepTuc_view);

        if (waitForElementDisplayed(DaHieu_view)) { // xử lý exception (case hiển thị popup A05)
            click(DaHieu_view);
            click(TiepTuc_view);
        }
    }

    public void chonNganHangThuHuong(String bankCode) {
        click(NganHang_view);
        click(ChonNganHang_edit);
        sendKeys(ChonNganHang_edit, bankCode);
        switch (bankCode) {
            case "MB":
                click(MBBank_view);
                break;
            case "DAB":
                click(DABBank_view);
                break;
            case "TCB":
                click(TCBBank_view);
            default:
        }
    }

    public void nhapThongTinNguoiNhan(String receiveNumber) {
        click(SoTaiKhoan_edit);
        sendKeys(SoTaiKhoan_edit, receiveNumber);
        click(ChuyenTienToiSoTaiKhoan_view);
    }

    public void nhapSoTien(String amount) {
        click(Amount_view);
        sendKeys(Amount_edit, amount);
        waitClickableIsTrue(TiepTuc_view, 3);
    }
}
