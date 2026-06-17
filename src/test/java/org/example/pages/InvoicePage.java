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

//    private By invoiceNumber =
//            By.xpath("//span[contains(text(),'Nomor Invoice')]/following-sibling::span");
//
//    private By nominal =
//            By.xpath("//span[contains(text(),'Nominal')]/following-sibling::span");
//
//    private By paymentMethod =
//            By.xpath("//span[contains(text(),'Metode Pembayaran')]/following-sibling::span");
//
//    private By transactionDate =
//            By.xpath("//span[contains(text(),'Tanggal Transaksi')]/following-sibling::span");
//
//    public boolean isInvoiceNumberDisplayed() {
//        return wait.until(
//                ExpectedConditions.visibilityOfElementLocated(invoiceNumber)
//        ).isDisplayed();
//    }
//
//    public boolean isNominalDisplayed() {
//        return wait.until(
//                ExpectedConditions.visibilityOfElementLocated(nominal)
//        ).isDisplayed();
//    }
//
//    public boolean isPaymentMethodDisplayed() {
//        return wait.until(
//                ExpectedConditions.visibilityOfElementLocated(paymentMethod)
//        ).isDisplayed();
//    }
//
//    public boolean isTransactionDateDisplayed() {
//        return wait.until(
//                ExpectedConditions.visibilityOfElementLocated(transactionDate)
//        ).isDisplayed();
//    }
}
