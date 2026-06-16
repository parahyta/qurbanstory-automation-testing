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
    private WebDriverWait wait;

    private By phoneField = By.xpath("//input[@placeholder='081233445566']");
    private By phoneInputFallback = By.xpath("//input[@type='tel']");
    private By tambahButtonLocator = By.xpath("//button[contains(.,'Tambah Nama Pequrban')]");
    private By bayarButtonLocator = By.xpath("//button[contains(.,'Bayar Sekarang')]");

    public CheckoutPage() {
        this.driver = DriverManager.getDriver();
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(15));
    }

    private WebElement waitForPhoneField() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(phoneField));
    }

    public void inputPhone(String phone) {
        WebElement field = waitForPhoneField();
        field.clear();
        field.sendKeys(phone);
    }

    public String getPhoneValue() {
        return waitForPhoneField().getAttribute("value");
    }

    public int getPhoneMaxLength() {
        String maxLength = waitForPhoneField().getAttribute("maxlength");
        if (maxLength == null || maxLength.isEmpty())
            return -1;
        return Integer.parseInt(maxLength);
    }

    public boolean isPhoneLengthWithinMaxLength() {
        int maxLength = getPhoneMaxLength();
        String currentValue = getPhoneValue();
        return currentValue.length() <= maxLength;
    }

    public void inputPhoneFallback(String phone) {
        WebElement field = wait.until(ExpectedConditions.visibilityOfElementLocated(phoneInputFallback));
        field.clear();
        field.sendKeys(phone);
    }

    public void clickTambahNamaPequrban(int times) throws InterruptedException {
        for (int i = 0; i < times; i++) {
            WebElement addBtn = wait.until(ExpectedConditions.elementToBeClickable(tambahButtonLocator));
            addBtn.click();
            Thread.sleep(300);
        }
    }

    public boolean isCounterDisplayed(String expectedCounterText) {
        WebElement counter = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//span[contains(.,'" + expectedCounterText + "')]")));
        return counter.isDisplayed();
    }

    public boolean isTambahButtonHidden() {
        return driver.findElements(tambahButtonLocator).isEmpty();
    }

    public void clickBayarSekarang() {
        WebElement bayarBtn = wait.until(ExpectedConditions.elementToBeClickable(bayarButtonLocator));
        bayarBtn.click();
    }
}