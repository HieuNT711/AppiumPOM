package com.qa.stepdef;

import com.qa.pages.PageInjection;

import context.ScenarioContext;

import enums.GlobalVariables;

import io.cucumber.java.en.When;

public class TransferSteps extends PageInjection {

    protected final ScenarioContext scenarioContext;

    public TransferSteps() {
        this.scenarioContext = ScenarioContext.getInstance();
    }

    @When(
            "I transfer to {string} bank with receive account {string} and amount {string} with"
                    + " DOTP")
    public void transferMoneyToAccount(String bankCode, String receiveNumber, String amount) {
        goToInquiryScreen();
        inputMoneyTransfer(bankCode, receiveNumber, amount);
        confirmTransferMoneyDOTP();
    }

    @When("I go to Inquiry Screen")
    public void goToInquiryScreen() {
        homePage.clickChuyenTien_view();
        typeTransferPage.SoTaiKhoan_view();
    }

    @When("I input transfer money Info bankCode {string} receiveNumber {string}  amout {string}")
    public void inputMoneyTransfer(String bankCode, String receiveNumber, String amount) {
        inquiryPage.nhapThongTinChuyenTien(bankCode, receiveNumber, amount);
    }

    @When("I confirm transfer money by DOTP")
    public void confirmTransferMoneyDOTP() {
        verifyPage.xacNhanChuyenTienDOTP(GlobalVariables.DOTP);
    }

    @When("I go to Digital OTP screen")
    public void goToDOTPScreen() {
        homePage.clickProfile();
        profilePage.clickCaiDat_view();
        settingPage.clickThietLapDigitalTP_view();
    }

    @When("I register DOTP")
    public void dangKiDOTP() {
        if (digitalOTPpage.checkDOTP()) {
            digitalOTPpage.nhapSMS();
            digitalOTPpage.dangKiDOTP();
        }
        utils.log().info("Đã đăng kí DOTP");
    }
}
