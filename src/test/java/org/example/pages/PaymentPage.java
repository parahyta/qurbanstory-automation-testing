package org.example.pages;

import org.example.utils.DriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.WindowType;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class PaymentPage {

    private WebDriver driver;
    private WebDriverWait wait;

    // Locators
    private By iframeMidtrans = By.id("snap-midtrans");
    private By vaCollapseButton = By
            .xpath("//div[contains(@class,'collapse-button')][.//span[normalize-space()='Virtual account']]");
    private By bcaLink = By.xpath("//a[contains(@href,'bca-va')]");
    private By vaField = By.id("vaField");

    private By inputMerchantId = By.id("inputMerchantId");
    private By inquireButton = By.xpath("//input[@type='submit' and @value='Inquire']");
    private By payButton = By.xpath("//input[@type='submit' and @value='Pay']");

    private By checkStatusButton = By.xpath("//button[contains(@class, 'btn') and text()='Check status']");

    public PaymentPage() {
        this.driver = DriverManager.getDriver();
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(20));
    }

    private void jsClick(WebElement el) {
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", el);
    }

    public void switchToMidtransIframe() {
        wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(iframeMidtrans));
    }

    public void clickVirtualAccountBca() {
        WebElement btn = wait.until(ExpectedConditions.elementToBeClickable(vaCollapseButton));
        jsClick(btn);

        WebElement link = wait.until(ExpectedConditions.elementToBeClickable(bcaLink));
        jsClick(link);
    }

    public String getVaNumber() {
        WebElement field = wait.until(ExpectedConditions.visibilityOfElementLocated(vaField));
        return field.getText().trim();
    }

    public void switchToDefaultContent() {
        driver.switchTo().defaultContent();
    }

    public String getCurrentWindowHandle() {
        return driver.getWindowHandle();
    }

    public void openSimulatorInNewTab() {
        driver.switchTo().newWindow(WindowType.TAB);
        driver.get("https://simulator.sandbox.midtrans.com/bca/va/index");
    }

    public void completePaymentInSimulator(String vaNumber) {
        WebElement vaInput = wait.until(ExpectedConditions.visibilityOfElementLocated(inputMerchantId));
        vaInput.clear();
        vaInput.sendKeys(vaNumber);

        WebElement btnInquire = wait.until(ExpectedConditions.elementToBeClickable(inquireButton));
        btnInquire.click();

        WebElement btnPay = wait.until(ExpectedConditions.elementToBeClickable(payButton));
        btnPay.click();
    }

    public void closeSimulatorTabAndSwitchToCheckout(String checkoutWindowHandle) {
        driver.close();
        driver.switchTo().window(checkoutWindowHandle);
    }

    public void clickCheckStatus() {
        WebElement btnCheckStatus = wait.until(ExpectedConditions.elementToBeClickable(checkStatusButton));
        btnCheckStatus.click();
    }
}
