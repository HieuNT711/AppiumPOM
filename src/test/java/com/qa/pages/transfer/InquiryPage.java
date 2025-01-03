package com.qa.pages.transfer;


import com.qa.pages.BasePage;
import lombok.SneakyThrows;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;


public class InquiryPage extends BasePage {
    WebElement element = driver.findElement(By.xpath("//android.widget.CheckBox[@resource-id=\"cm.aptoide.pt:id/games_chip\"]"));
    WebElement ungDung = driver.findElement(By.xpath("//android.widget.CheckBox[@resource-id=\"cm.aptoide.pt:id/apps_chip\"]"));

    public InquiryPage() {
    }

    @SneakyThrows
    public void nhapThongTinChuyenTien() {
        waitForVisibility(element);
        element.click();
        ungDung.click();
    }


}
