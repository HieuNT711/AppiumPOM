package com.qa.stepdef;

import com.qa.pages.PageInjection;
import io.cucumber.java.en.When;

public class TransferSteps extends PageInjection {
    public TransferSteps() {
    }

    @When("I login MB bank")
    public void loginAPI() {
        System.out.println("Herreeee");
        inquiryPage.nhapThongTinChuyenTien();
    }
}
