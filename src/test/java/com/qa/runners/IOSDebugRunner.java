package com.qa.runners;

import io.cucumber.testng.CucumberOptions;

/**
 * An example of using TestNG when the test class does not inherit from AbstractTestNGCucumberTests
 * but still executes each scenario as a separate TestNG test.
 */
@CucumberOptions(
        plugin = {
            "pretty",
            "html:target/IOS/cucumber",
            "summary",
            "me.jvt.cucumber.report.PrettyReports:report/IOS"
        },
        //        features = {"src/test/resources/debug"},
        features = {"src/test/resources/features/ios"},
        glue = {"com.qa.stepdef"},
        dryRun = false,
        monochrome = true,
        tags = "@iOS and not @ignore")
public class IOSDebugRunner extends RunnerBase {}
