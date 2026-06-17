package org.example.pages;

import org.example.utils.DriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class InvoicePage {

    private WebDriver driver;
    private WebDriverWait wait;

    public InvoicePage() {
        this.driver = DriverManager.getDriver();
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(20));
    }

    public void waitForInvoicePageToLoad() {
        wait.until(ExpectedConditions.urlContains("qurban-story.vercel.app/invoice/"));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.tagName("body")));
    }

    public String getCurrentUrl() {
        return driver.getCurrentUrl();
    }

    public boolean isPaymentStatusSuccess() {
        WebElement status = wait
                .until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[text()='BERHASIL']")));
        return status.isDisplayed();
    }

    public boolean isInvoiceNumberDisplayed() {
        return wait.until(
                ExpectedConditions.visibilityOfElementLocated(
                        By.xpath("//span[contains(text(),'ORD-')]")
                )
        ).isDisplayed();
    }

    public boolean isTransactionAmountDisplayed() {
        return wait.until(
                ExpectedConditions.visibilityOfElementLocated(
                        By.xpath("//span[contains(text(),'Rp')]")
                )
        ).isDisplayed();
    }

    public boolean isPaymentMethodDisplayed() {
        return wait.until(
                ExpectedConditions.visibilityOfElementLocated(
                        By.xpath("//span[text()='Virtual Account']")
                )
        ).isDisplayed();
    }

    public boolean isTransactionDateDisplayed() {
        return wait.until(
                ExpectedConditions.visibilityOfElementLocated(
                        By.xpath("//span[contains(text(),'-')]")
                )
        ).isDisplayed();
    }

    public boolean isPaymentStatusExpired() {
        WebElement status = wait.until(
                ExpectedConditions.visibilityOfElementLocated(
                        By.xpath("//span[text()='KADALUARSA']")
                )
        );

        return status.isDisplayed();
    }

}
