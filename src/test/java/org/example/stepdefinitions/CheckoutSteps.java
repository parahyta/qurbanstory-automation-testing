package org.example.stepdefinitions;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import org.example.pages.CheckoutPage;
import org.example.utils.DriverManager;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.By;

import org.junit.jupiter.api.Assertions;

import java.time.Duration;

public class CheckoutSteps {

    private CheckoutPage checkoutPage = new CheckoutPage();

    @Given("user is on the checkout page for product {string}")
    public void userIsOnCheckoutPage(String productName) {
        DriverManager.getDriver().get("https://qurban-story.vercel.app");

        String sessionToken = System.getenv("NEXTAUTH_SESSION_TOKEN");
        if (sessionToken == null || sessionToken.isEmpty()) {
            throw new RuntimeException("ERROR: NEXTAUTH_SESSION_TOKEN belom diset di environment!");
        }

        Cookie authCookie = new Cookie.Builder("__Secure-next-auth.session-token", sessionToken)
                .domain("qurban-story.vercel.app")
                .path("/")
                .isSecure(true)
                .build();
        DriverManager.getDriver().manage().addCookie(authCookie);

        DriverManager.getDriver().get(
                "https://qurban-story.vercel.app/checkout/b1df7917-dd5a-416e-a3dc-7207ca586310"
        );

        DriverManager.getDriver().get(
                "https://qurban-story.vercel.app/checkout/80cc1750-d342-4842-a9d8-b8e9cd4339c4"
        );

        WebDriverWait wait = new WebDriverWait(DriverManager.getDriver(), Duration.ofSeconds(15));
        wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//input[@placeholder='081233445566']")
        ));

        System.out.println("Checkout page loaded with Auth! URL: " + DriverManager.getDriver().getCurrentUrl());
    }

    @When("user enters phone number {string}")
    public void userEntersPhoneNumber(String phone) {
        System.out.println("Memasukkan nomor telepon: '" + phone + "'");
        checkoutPage.inputPhone(phone);
    }

    @Then("the field value should automatically sanitize to {string}")
    public void theFieldValueShouldAutomaticallySanitizeTo(String expected) {
        String actual = checkoutPage.getPhoneValue();
        System.out.println("Expected: '" + expected + "', Actual: '" + actual + "'");
        Assertions.assertEquals(
                expected,
                actual,
                "Sanitasi nomor telepon gagal. Expected: '" + expected + "', Actual: '" + actual + "'"
        );
    }
}