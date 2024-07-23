package com.qa.runners;

import io.cucumber.testng.CucumberOptions;

/**
 * An example of using TestNG when the test class does not inherit from AbstractTestNGCucumberTests
 * but still executes each scenario as a separate TestNG test.
 */
@CucumberOptions(
        plugin = {
            "pretty",
            "html:target/Android/cucumber",
            "summary",
            "me.jvt.cucumber.report.PrettyReports:report/Android",
            //                "com.epam.reportportal.cucumber.ScenarioReporter"
        },
//        features = {"src/test/resources/features/android"},
                features = {"D:\\AppiumCode\\app-autotest\\src\\test\\resources\\features\\android\\Debug.feature"},
        glue = {"com.qa.stepdef"},
        dryRun = false,
        monochrome = true
//        tags = "@Android and not @ignore"
        )
public class AndroidDebugRunner extends RunnerBase {}
