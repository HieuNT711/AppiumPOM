package core;

import com.utils.TestUtils;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;

import java.io.IOException;
import java.net.URL;

public class DriverManager {
    private static final ThreadLocal<AppiumDriver> driver = new ThreadLocal<>();
    TestUtils utils = new TestUtils();

    public AppiumDriver getDriver() {
        return driver.get();
    }

    public void setDriver(AppiumDriver driver2) {
        driver.set(driver2);
    }

    public void initializeDriver() throws Exception {
        AppiumDriver driver = null;
        GlobalParams params = new GlobalParams();
        if (driver == null) {
            try {
                utils.log().info("initializing Appium driver");
                switch (params.getPlatformName()) {
                    case "Android":
                        driver =
                                new AndroidDriver<>(
                                        new URL(
                                                PropertiesManager.getEnvironmentSpecFromProperty(
                                                        "appiumURL")),
                                        new CapabilitiesManager().getCaps());
                        break;
                    case "iOS":
                        driver =
                                new IOSDriver(
                                        new URL(
                                                PropertiesManager.getEnvironmentSpecFromProperty(
                                                        "appiumURL")),
                                        new CapabilitiesManager().getCaps());
                        break;
                }
                if (driver == null) {
                    throw new Exception("driver is null. ABORT!!!");
                }
                utils.log().info("Driver is initialized");
                DriverManager.driver.set(driver);
            } catch (IOException e) {
                e.printStackTrace();
                utils.log().fatal("Driver initialization failure. ABORT !!!!" + e);
                throw e;
            }
        }
    }
}
