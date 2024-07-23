package com.utils;

import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
//import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.touch.offset.PointOption;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;

public class SwipeUtils {

//    public static void swipe(int startX, int startY, int endX, int endY, AndroidDriver driver) {
//        new TouchAction(driver)
//                .longPress(PointOption.point(startX, startY))
//                .moveTo(PointOption.point(endX, endY))
//                .release()
//                .perform();
//    }

//    public static AndroidElement getElement(By locator, AndroidDriver driver) {
//        AndroidElement element = null;
//        for (int i = 0; i < 20; i++) {
//            try {
//                element = (AndroidElement) driver.findElement(locator);
//            } catch (Exception e) {
//            }
//            if (null == element) {
//                swipeMobileUp(driver);
//            } else {
//                return element;
//            }
//        }
//        return null;
//    }

//    public static void swipeMobileUp(AndroidDriver driver) {
//        Dimension size = driver.manage().window().getSize();
//        int startY = (int) (size.height * 0.8);
//        int endY = (int) (size.height * 0.2);
//        int startX = size.width / 2;
//        swipe(startX, startY, startX, endY, driver);
//    }
//
//    public static void swipeMobileRightToLeft(AndroidDriver driver) {
//        Dimension size = driver.manage().window().getSize();
//        int startY = 1500;
//        int endY = 360;
//        int startX = 150;
//        swipe(startX, startY, startX, endY, driver);
//    }
}
