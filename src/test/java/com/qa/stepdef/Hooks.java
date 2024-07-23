package com.qa.stepdef;

import context.ScenarioContext;

import core.DriverManager;

import io.cucumber.java.*;

import lombok.SneakyThrows;

import org.openqa.selenium.OutputType;

import java.io.IOException;

public class Hooks {

    protected ScenarioContext scenarioContext;

    public Hooks() {
        this.scenarioContext = ScenarioContext.getInstance();
    }

    public static Scenario scenario;

    //        @Before
    //        public void initialize() {
    //            System.out.println("Run here");
    ////            AppiumDriver appiumDriver = new DriverManager().getDriver();
    ////            WebDriverWait wait = new WebDriverWait(appiumDriver, 15);
    ////            if (new GlobalParams().getPlatformName().equalsIgnoreCase("Android")){
    ////                try{
    ////
    // wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@resource-id=\"content\"]")));
    ////                    if
    // (appiumDriver.findElement(By.xpath("//*[@resource-id=\"content\"]")).isDisplayed()){
    ////                        appiumDriver.navigate().back();
    ////                    }
    ////                } catch (Exception e){
    ////                    //ignore
    ////                }
    ////            }
    //            //        new VideoManager().startRecording();
    //        }

    //    @AfterStep
    //    public void afterSteps(Scenario scenario){
    //        this.scenario = scenario;
    //    }

    @After
    @SneakyThrows
    public void quit(Scenario scenario) throws IOException {
        //        if (scenario.isFailed()) {
        //            byte[] screenshot = new
        // DriverManager().getDriver().getScreenshotAs(OutputType.BYTES);
        //            scenario.attach(screenshot, "image/png", scenario.getName());
        //        }
        byte[] screenshot = new DriverManager().getDriver().getScreenshotAs(OutputType.BYTES);
        scenario.attach(screenshot, "image/png", scenario.getName());
        //        new VideoManager().stopRecording(scenario.getName());
        //        scenarioContext.clearAllDataInThreadContext();
        //        new DriverManager().getDriver().closeApp();
        new DriverManager().getDriver().launchApp();
        //        Thread.sleep(5000);
    }
}
