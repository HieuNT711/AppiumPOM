package com.qa.stepdef;

import com.qa.pages.PageInjection;

import context.ScenarioContext;

import io.cucumber.java.en.When;

public class TransferSteps extends PageInjection {

    protected final ScenarioContext scenarioContext;

    public TransferSteps() {
        this.scenarioContext = ScenarioContext.getInstance();
    }

    @When(
            "I transfer to {string} bank with receive account {string} and amount {string} with"
                    + " Dotp {string}")
    public void transferMoneyToAccount(
            String bankCode, String receiveNumber, String amount, String dotp) {
        goToInquiryScreen();
        inputMoneyTransfer(bankCode, receiveNumber, amount);
        confirmTransferMoneyDOTP(dotp);
    }

    @When("I go to Inquiry Screen")
    public void goToInquiryScreen() {
        homePage.clickChuyenTien_btn();
        typeTransferPage.clickSoTaiKhoan_btn();
    }

    @When("I input transfer money Info bankCode {string} receiveNumber {string}  amout {string}")
    public void inputMoneyTransfer(String bankCode, String receiveNumber, String amount) {
        inquiryPage.nhapThongTinChuyenTien(bankCode, receiveNumber, amount);
    }

    @When("I confirm transfer money by DOTP {string}")
    public void confirmTransferMoneyDOTP(String dotp) {
        verifyPage.xacNhanChuyenTienDOTP(dotp);
    }
}
