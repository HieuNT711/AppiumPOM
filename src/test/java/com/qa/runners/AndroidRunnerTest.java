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
        features = {"src/test/resources/features/android"},
        glue = {"com.qa.stepdef"},
        dryRun = false,
        monochrome = true,
        tags = "@Android and not @ignore")
public class AndroidRunnerTest extends RunnerBase {}
