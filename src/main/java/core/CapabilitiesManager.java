package core;

import com.utils.TestUtils;
import io.appium.java_client.remote.MobileCapabilityType;

import org.openqa.selenium.remote.DesiredCapabilities;

import java.io.IOException;

public class CapabilitiesManager {
    TestUtils utils = new TestUtils();

    public DesiredCapabilities getCaps() throws IOException {
        GlobalParams params = new GlobalParams();

        try {
            utils.log().info("getting capabilities");
            DesiredCapabilities caps = new DesiredCapabilities();
            caps.setCapability(MobileCapabilityType.PLATFORM_NAME, params.getPlatformName());
            caps.setCapability(MobileCapabilityType.UDID, params.getUDID());
            caps.setCapability(MobileCapabilityType.DEVICE_NAME, params.getDeviceName());

            switch (params.getPlatformName()) {
                case "Android":
                    caps.setCapability(
                            MobileCapabilityType.AUTOMATION_NAME,
                            PropertiesManager.getEnvironmentSpecFromProperty("androidAutomationName"));
                    caps.setCapability("appPackage", PropertiesManager.getEnvironmentSpecFromProperty("androidAppPackage"));
                    caps.setCapability("appActivity", PropertiesManager.getEnvironmentSpecFromProperty("androidAppActivity"));
//                    caps.setCapability("systemPort", params.getSystemPort());
//                    caps.setCapability("app", "D:\\AppiumCode\\app-autotest\\src\\test\\resources\\data\\Banggood - Online Shopping_7.51.0_Apkpure.apk");
                    caps.setCapability("noReset", "true");
                    caps.setCapability("fullReset", "false");
                    break;
                case "iOS":
                    caps.setCapability(
                            MobileCapabilityType.AUTOMATION_NAME,
                            PropertiesManager.getEnvironmentSpecFromProperty("iOSAutomationName"));
                    caps.setCapability("bundleId", PropertiesManager.getEnvironmentSpecFromProperty("iOSBundleId"));
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
