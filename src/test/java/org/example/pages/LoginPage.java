package org.example.pages;

import org.example.utils.DriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class LoginPage {

    private WebDriver driver;
    private WebDriverWait wait;

    private static final String BASE_URL = "https://qurban-story.vercel.app";

    private By masukButton =
            By.xpath("//a[@href='/login']");

    private By googleLoginButton =
            By.xpath("//*[contains(text(),'Lanjutkan dengan Google')]");

    public LoginPage() {
        this.driver = DriverManager.getDriver();
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    public void navigateToLoginPage() {
        driver.get(BASE_URL);

        wait.until(
                ExpectedConditions.elementToBeClickable(masukButton)
        ).click();
    }

    public void clickGoogleLogin() {
        driver.findElement(googleLoginButton).click();
    }

    public String getCurrentUrl() {
        return driver.getCurrentUrl();
    }
}