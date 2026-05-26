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

    // Locators - Halaman Katalog Produk
    private By catalogHeader = By.xpath("//h1[contains(text(), 'Pilihan Hewan Qurban')]");
    private By productCardLink(String productName) {
        return By.xpath("//h3[normalize-space()='" + productName + "']/ancestor::div[contains(@class, 'rounded-xl')]//a[contains(text(), 'Lihat detail')]");
    }

    // Locators - Halaman Detail Produk
    private By productDetailTitle = By.xpath("//h1[contains(@class, 'font-bold')]");
    private By productPrice = By.xpath("//div[contains(@class, 'items-start')]//span[contains(@class, 'font-bold') and contains(@class, 'text-[#044B57]')]");
    private By productStockText = By.xpath("//p[contains(text(), 'Stok:')]");
    private By buyButtonDisabled = By.xpath("//button[@disabled and contains(text(), 'Stok Habis')]");
    private By buyButtonDisabledText = By.xpath("//button[@disabled]"); // untuk ambil teks

    public ProductPage() {
        this.driver = DriverManager.getDriver();
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    // Navigasi ke halaman katalog produk
    public void navigateToCatalog() {
        driver.get(BASE_URL + "/produk");
        wait.until(ExpectedConditions.visibilityOfElementLocated(catalogHeader));
    }

    // Klik tombol "Lihat detail" pada produk tertentu di katalog
    public void clickLihatDetailOnProduct(String productName) {
        By productLink = productCardLink(productName);
        WebElement link = wait.until(ExpectedConditions.elementToBeClickable(productLink));
        link.click();
    }

    // Verifikasi bahwa halaman detail produk menampilkan harga dan stok
    public boolean isPriceAndStockDisplayed() {
        boolean priceDisplayed = wait.until(ExpectedConditions.visibilityOfElementLocated(productPrice)).isDisplayed();
        boolean stockDisplayed = wait.until(ExpectedConditions.visibilityOfElementLocated(productStockText)).isDisplayed();
        return priceDisplayed && stockDisplayed;
    }

    // Navigasi langsung ke halaman detail produk dengan ID tertentu (untuk stok habis)
    public void navigateToProductDetailById(String productId) {
        driver.get(BASE_URL + "/produk/" + productId);
        wait.until(ExpectedConditions.visibilityOfElementLocated(productDetailTitle));
    }

    // Mendapatkan teks tombol beli (jika disabled, teks "Stok Habis")
    public String getBuyButtonText() {
        WebElement button = wait.until(ExpectedConditions.visibilityOfElementLocated(buyButtonDisabledText));
        return button.getText();
    }

    // Memeriksa apakah tombol beli disabled (tidak bisa diklik)
    public boolean isBuyButtonDisabled() {
        try {
            WebElement button = wait.until(ExpectedConditions.visibilityOfElementLocated(buyButtonDisabled));
            return !button.isEnabled();
        } catch (Exception e) {
            return false;
        }
    }

    // Memeriksa apakah pesan error "Stok Habis" tampil (baik tombol atau alert)
    public boolean isStokHabisErrorMessageDisplayed() {
        try {
            WebElement errorMsg = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[contains(text(),'Stok Habis')]")));
            return errorMsg.isDisplayed();
        } catch (Exception e) {
            // Cek tombol disabled dengan teks "Stok Habis"
            return isBuyButtonDisabled() && getBuyButtonText().contains("Stok Habis");
        }
    }

    // Mendapatkan teks stok dari halaman detail (misal "Stok: 0")
    public String getStockText() {
        WebElement stockElement = wait.until(ExpectedConditions.visibilityOfElementLocated(productStockText));
        return stockElement.getText();
    }
}