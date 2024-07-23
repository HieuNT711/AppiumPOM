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

    @When("Welcome Intro Page")
    public void loginAPI() {
        introPage.tapShopNowButton();
    }


}
