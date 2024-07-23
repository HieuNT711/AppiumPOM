// package core;
//
// import com.utils.TestUtils;
// import io.appium.java_client.service.local.AppiumDriverLocalService;
// import io.appium.java_client.service.local.AppiumServerHasNotBeenStartedLocallyException;
// import io.appium.java_client.service.local.AppiumServiceBuilder;
// import io.appium.java_client.service.local.flags.GeneralServerFlag;
//
// import java.io.File;
// import java.util.HashMap;
//
// public class ServerManager {
//    private static final ThreadLocal<AppiumDriverLocalService> server = new ThreadLocal<>();
//    TestUtils utils = new TestUtils();
//
//    public AppiumDriverLocalService getServer() {
//        return server.get();
//    }
//
//    public void startServer() {
//        utils.log().info("starting appium server");
//        //        AppiumDriverLocalService server = WindowsGetAppiumService();
//        AppiumDriverLocalService server = MacGetAppiumService();
//        server.start();
//        server.clearOutPutStreams();
//        if (server == null || !server.isRunning()) {
//            utils.log().fatal("Appium server not started. ABORT!!!");
//            throw new AppiumServerHasNotBeenStartedLocallyException(
//                    "Appium server not started. ABORT!!!");
//        }
//        //        server.clearOutPutStreams();
//        ServerManager.server.set(server);
//        utils.log().info("Appium server started");
//    }
//
//    public AppiumDriverLocalService getAppiumServerDefault() {
//        return AppiumDriverLocalService.buildDefaultService();
//    }
//
//    public AppiumDriverLocalService WindowsGetAppiumService() {
//        GlobalParams params = new GlobalParams();
//        return AppiumDriverLocalService.buildService(
//                new AppiumServiceBuilder()
//                        .usingAnyFreePort()
//                        .withArgument(GeneralServerFlag.SESSION_OVERRIDE)
//                        .withLogFile(
//                                new File(
//                                        params.getPlatformName()
//                                                + "_"
//                                                + params.getDeviceName()
//                                                + File.separator
//                                                + "Server.log")));
//    }
//
//    public AppiumDriverLocalService MacGetAppiumService() {
//        GlobalParams params = new GlobalParams();
//        HashMap<String, String> environment = new HashMap<String, String>();
//        environment.put(
//                "PATH",
//
// "/usr/local/bin:/usr/bin:/bin:/usr/sbin:/sbin:/Library/Apple/usr/bin:/Users/admin/.cargo/bin"
//                        + System.getenv("PATH"));
//        environment.put("ANDROID_HOME", "/Users/sotatek/Library/Android/sdk");
//        return AppiumDriverLocalService.buildService(
//                new AppiumServiceBuilder()
//                        .usingDriverExecutable(new File("/usr/local/bin/node"))
//                        .withAppiumJS(
//                                new File("/usr/local/lib/node_modules/appium/build/lib/main.js"))
//                        .usingPort(4723)
//                        .withArgument(GeneralServerFlag.SESSION_OVERRIDE)
//                        .withEnvironment(environment)
//                        .withLogFile(
//                                new File(
//                                        params.getPlatformName()
//                                                + "_"
//                                                + params.getDeviceName()
//                                                + File.separator
//                                                + "Server.log")));
//    }
// }
