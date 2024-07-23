package com.utils;

import com.google.zxing.*;
import com.google.zxing.client.j2se.BufferedImageLuminanceSource;
import com.google.zxing.common.HybridBinarizer;

import lombok.SneakyThrows;

import java.awt.image.BufferedImage;
import java.io.FileInputStream;

import javax.imageio.ImageIO;

public class QRCodeDetector {

    @SneakyThrows
    public static String detectQRCodes(String imagePathFile) {
        try {
            BufferedImage bf = ImageIO.read(new FileInputStream(imagePathFile));

            BinaryBitmap bitmap =
                    new BinaryBitmap(new HybridBinarizer(new BufferedImageLuminanceSource(bf)));

            Result result = new MultiFormatReader().decode(bitmap);

            return result.getText();
        } catch (Exception e) {
            return e.getMessage();
        }
    }

//    @SneakyThrows
//    public static void takeScreenShot(String imageName) {
//        File screenshot = new DriverManager().getDriver().getScreenshotAs(OutputType.FILE);
//        FileUtils.copyFile(screenshot, new File(imageName), true);
//        // scenario.attach(screenshot, "image/png", scenario.getName());
//    }
}
