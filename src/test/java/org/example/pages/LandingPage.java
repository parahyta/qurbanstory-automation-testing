package org.example.pages;

import org.example.utils.DriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class LandingPage {

    private WebDriver driver;
    private WebDriverWait wait;

    private static final String BASE_URL = "https://qurban-story.vercel.app";

    private By lihatProdukButton =
            By.xpath("//button[contains(text(),'Lihat Produk')]");

    private By loginButton =
            By.xpath("//a[@href='/login']");

    public LandingPage() {
        this.driver = DriverManager.getDriver();
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    public void navigateToLandingPage() {
        driver.get(BASE_URL);
    }

    public void clickLihatProduk() {
        driver.findElement(lihatProdukButton).click();
    }

    public String getCurrentUrl() {
        return driver.getCurrentUrl();
    }

    public void clickLogin() {
        driver.findElement(loginButton).click();
    }
}