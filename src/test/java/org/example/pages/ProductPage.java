package org.example.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.example.utils.DriverManager;

import java.time.Duration;

public class ProductPage {
    private WebDriver driver;
    private WebDriverWait wait;
    private static final String BASE_URL = "https://qurban-story.vercel.app";

    private By catalogHeader = By.xpath("//h1[contains(text(), 'Pilihan Hewan Qurban')]");

    private By productCardLihatDetail(String productName) {
        return By.xpath(
                "//h3[normalize-space()='" + productName + "']/ancestor::div[contains(@class,'rounded-xl')]" +
                        "//a[normalize-space()='Lihat detail']"
        );
    }

    private By productDetailTitle = By.xpath("//h1[contains(@class,'font-bold')]");

    private By productPrice = By.xpath(
            "//span[contains(@class,'font-bold') and contains(@class,'text-[#044B57]')]"
    );

    private By productStockText = By.xpath("//p[contains(text(),'Stok:')]");

    private By buyButtonDisabled = By.xpath(
            "//button[@disabled and contains(normalize-space(.),'Stok Habis')]"
    );

    private By anyDisabledButton = By.xpath("//button[@disabled]");

    public ProductPage() {
        this.driver = DriverManager.getDriver();
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    public void navigateToCatalog() {
        driver.get(BASE_URL + "/produk");
        wait.until(ExpectedConditions.visibilityOfElementLocated(catalogHeader));
    }

    public void clickLihatDetailOnProduct(String productName) {
        By locator = productCardLihatDetail(productName);
        WebElement link = wait.until(ExpectedConditions.elementToBeClickable(locator));
        link.click();
    }

    public boolean isPriceAndStockDisplayed() {
        boolean priceVisible = wait.until(
                ExpectedConditions.visibilityOfElementLocated(productPrice)
        ).isDisplayed();
        boolean stockVisible = wait.until(
                ExpectedConditions.visibilityOfElementLocated(productStockText)
        ).isDisplayed();
        return priceVisible && stockVisible;
    }

    public void navigateToProductDetailById(String productId) {
        driver.get(BASE_URL + "/produk/" + productId);
        wait.until(ExpectedConditions.visibilityOfElementLocated(productDetailTitle));
    }

    public String getBuyButtonText() {
        WebElement button = wait.until(
                ExpectedConditions.visibilityOfElementLocated(anyDisabledButton)
        );
        return button.getText().trim();
    }

    public boolean isBuyButtonDisabledWithStokHabis() {
        try {
            WebElement button = wait.until(
                    ExpectedConditions.presenceOfElementLocated(buyButtonDisabled)
            );
            // Cek attribute disabled ada
            return button.getAttribute("disabled") != null;
        } catch (Exception e) {
            return false;
        }
    }

    public boolean isStokHabisTextDisplayed() {
        try {
            String text = getBuyButtonText();
            return text.contains("Stok Habis");
        } catch (Exception e) {
            return false;
        }
    }

    public String getStockText() {
        return wait.until(
                ExpectedConditions.visibilityOfElementLocated(productStockText)
        ).getText();
    }
}