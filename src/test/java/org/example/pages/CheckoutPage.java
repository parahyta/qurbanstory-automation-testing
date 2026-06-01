package org.example.pages;

import org.example.utils.DriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class CheckoutPage {

    private WebDriver driver;

    public CheckoutPage() {
        this.driver = DriverManager.getDriver();
    }

    private By phoneField =
            By.xpath("//input[@placeholder='081233445566']");

    private WebElement waitForPhoneField() {

        WebDriverWait wait =
                new WebDriverWait(driver, Duration.ofSeconds(15));

        return wait.until(
                ExpectedConditions.visibilityOfElementLocated(phoneField)
        );
    }

    public void inputPhone(String phone) {

        WebElement field = waitForPhoneField();

        field.clear();
        field.sendKeys(phone);
    }

    public String getPhoneValue() {

        return waitForPhoneField()
                .getAttribute("value");
    }
}