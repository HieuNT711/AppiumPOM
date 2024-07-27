package com.qa.stepdef;

import com.qa.pages.IntroPage;

import context.ScenarioContext;

import io.cucumber.java.en.When;

public class LoginSteps extends BaseSteps {
    IntroPage introPage = new IntroPage();
    protected final ScenarioContext scenarioContext;

    public LoginSteps() {
        this.scenarioContext = ScenarioContext.getInstance();
    }

    @When("I login in MB bank")
    public void loginAPI() {
        introPage.verifyTaiKhoanKhac_btnIsPresent();
        introPage.loginApp();
    }
}
