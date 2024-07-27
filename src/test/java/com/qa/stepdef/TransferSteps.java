package com.qa.stepdef;

import com.qa.pages.PageInjection;

import context.ScenarioContext;

import io.cucumber.java.en.When;

public class TransferSteps extends PageInjection {

    protected final ScenarioContext scenarioContext;

    public TransferSteps() {
        this.scenarioContext = ScenarioContext.getInstance();
    }

    @When("I transfer to {string} and amount {string} with Dotp {string}")
    public void clickChuyenTienBtn(String receiveNumber, String amount, String dotp) {
        homePage.clickChuyenTien_btn();
        typeTransferPage.clickSoTaiKhoan_btn();
        inquiryPage.nhapThongTinChuyenTien(receiveNumber, amount);
        verifyPage.xacNhanChuyenTien(dotp);
    }
}
