package com.qa.runners;

import com.utils.TestUtils;

import core.DriverManager;
import core.GlobalParams;
import core.PropertiesManager;

import io.cucumber.testng.FeatureWrapper;
import io.cucumber.testng.PickleWrapper;
import io.cucumber.testng.TestNGCucumberRunner;

import org.apache.logging.log4j.ThreadContext;
import org.testng.annotations.*;

public class RunnerBase {
    public static String userName;
    public static String passWord;
    TestUtils utils = new TestUtils();
    private static final ThreadLocal<TestNGCucumberRunner> testNGCucumberRunner =
            new ThreadLocal<>();

    public static TestNGCucumberRunner getRunner() {
        return testNGCucumberRunner.get();
    }

    private static void setRunner(TestNGCucumberRunner testNGCucumberRunner1) {
        testNGCucumberRunner.set(testNGCucumberRunner1);
    }

    @Parameters({
        "platformName",
        "udid",
        "deviceName",
        "systemPort",
        "chromeDriverPort",
        "deviceIndex",
        "module",
        "app",
        "wdaLocalPort",
        "webkitDebugProxyPort",
    })
    @BeforeClass(alwaysRun = true)
    public void setUpClass(
            String platformName,
            String udid,
            String deviceName,
            @Optional("Android") String systemPort,
            @Optional("Android") String chromeDriverPort,
            @Optional String deviceIndex,
            @Optional String module,
            @Optional String app,
            @Optional("iOS") String wdaLocalPort,
            @Optional("iOS") String webkitDebugProxyPort)
            throws Exception {

        ThreadContext.put("ROUTINGKEY", platformName + "_" + deviceName);

        GlobalParams params = new GlobalParams();
        params.setPlatformName(platformName);
        params.setUDID(udid);
        params.setDeviceName(deviceName);
        params.setApp(app);

        switch (platformName) {
            case "Android":
                params.setSystemPort(systemPort);
                break;
            case "iOS":
                params.setWdaLocalPort(wdaLocalPort);
                break;
        }
        utils.log().info("deviceIndex: " + deviceIndex);
        utils.log().info("module: " + module);

        userName =
                PropertiesManager.getEnvironmentSpecFromProperty(
                        module + "." + deviceIndex + ".username");
        passWord =
                PropertiesManager.getEnvironmentSpecFromProperty(
                        module + "." + deviceIndex + ".password");

        //        new ServerManager().startServer();
        new DriverManager().initializeDriver();

        setRunner(new TestNGCucumberRunner(this.getClass()));
    }

    @Test(groups = "cucumber", description = "Runs Cucumber Scenarios", dataProvider = "scenarios")
    public void scenario(PickleWrapper pickle, FeatureWrapper cucumberFeature) throws Throwable {
        getRunner().runScenario(pickle.getPickle());
    }

    @DataProvider
    public Object[][] scenarios() {
        return getRunner().provideScenarios();
    }

    @AfterClass(alwaysRun = true)
    public void tearDownClass() {
        //        DriverManager driverManager = new DriverManager();
        //        if (driverManager.getDriver() != null) {
        //            driverManager.getDriver().quit();
        //            driverManager.setDriver(null);
        //        }
        //        ServerManager serverManager = new ServerManager();
        //        if (serverManager.getServer() != null) {
        //            serverManager.getServer().stop();
        //        }
        if (testNGCucumberRunner != null) {
            getRunner().finish();
        }
    }
}
