package org.example.stepdefinitions;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import org.example.pages.CheckoutPage;
import org.example.utils.DriverManager;

import org.junit.jupiter.api.Assertions;

public class CheckoutSteps {

    private CheckoutPage checkoutPage =
            new CheckoutPage();

    @Given("user is on the checkout page for product {string}")
    public void userIsOnCheckoutPage(String productName)
            throws InterruptedException {

        DriverManager.getDriver().get(
                "https://qurban-story.vercel.app/checkout/b1df7917-dd5a-416e-a3dc-7207ca586310"
        );

        Thread.sleep(5000);

        System.out.println("URL = " +
                DriverManager.getDriver().getCurrentUrl());

        System.out.println("TITLE = " +
                DriverManager.getDriver().getTitle());
    }

    @When("user enters phone number {string}")
    public void userEntersPhoneNumber(String phone) {

        System.out.println(
                "Current URL: " +
                        DriverManager.getDriver().getCurrentUrl()
        );

        checkoutPage.inputPhone(phone);
    }

    @Then("the field value should automatically sanitize to {string}")
    public void theFieldValueShouldAutomaticallySanitizeTo(String expected) {

        String actual =
                checkoutPage.getPhoneValue();

        Assertions.assertEquals(
                expected,
                actual,
                "Sanitasi nomor telepon gagal"
        );
    }
}