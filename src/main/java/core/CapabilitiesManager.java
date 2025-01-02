package core;

import com.utils.TestUtils;

import io.appium.java_client.android.options.UiAutomator2Options;
import io.appium.java_client.remote.MobileCapabilityType;

import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.http.ClientConfig;

import java.io.IOException;
import java.net.URL;
import java.time.Duration;

public class CapabilitiesManager {
    TestUtils utils = new TestUtils();

    public UiAutomator2Options getCaps() throws IOException {
        GlobalParams params = new GlobalParams();
        try {
            utils.log().info("getting capabilities");
            UiAutomator2Options   caps = new UiAutomator2Options  ();
            caps.setCapability(MobileCapabilityType.PLATFORM_NAME, params.getPlatformName());
            caps.setCapability(MobileCapabilityType.UDID, params.getUDID());
            caps.setCapability(MobileCapabilityType.DEVICE_NAME, params.getDeviceName());
            caps.setCapability(MobileCapabilityType.APP, params.getApp());

            switch (params.getPlatformName()) {
                case "Android":
                    caps.setCapability(
                            MobileCapabilityType.AUTOMATION_NAME,
                            "UiAutomator2");
//                    caps.setCapability(
//                            "appPackage",
//                            PropertiesManager.getEnvironmentSpecFromProperty("androidAppPackage"));
//                    caps.setCapability(
//                            "appActivity",
//                            PropertiesManager.getEnvironmentSpecFromProperty("androidAppActivity"));
                    caps.setCapability("noReset", "true");
                    caps.setCapability("fullReset", "false");
                    break;
                case "iOS":
                    caps.setCapability(
                            MobileCapabilityType.AUTOMATION_NAME,
                            PropertiesManager.getEnvironmentSpecFromProperty("iOSAutomationName"));
                    caps.setCapability(
                            "bundleId",
                            PropertiesManager.getEnvironmentSpecFromProperty("iOSBundleId"));
                    caps.setCapability("wdaLocalPort", params.getWdaLocalPort());
                    break;
            }
            return caps;
        } catch (Exception e) {
            e.printStackTrace();
            utils.log().fatal("Failed to load capabilities. ABORT!!" + e);
            throw e;
        }
    }
}
