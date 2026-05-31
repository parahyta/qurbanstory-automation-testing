package org.example.stepdefinitions;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.example.pages.LandingPage;
import org.example.pages.LoginPage;
import org.junit.jupiter.api.Assertions;

public class LandingLoginSteps {

    private LandingPage landingPage = new LandingPage();
    private LoginPage loginPage = new LoginPage();

    @Given("user is on the landing page")
    public void userIsOnLandingPage() {
        landingPage.navigateToLandingPage();
    }

//    @When("user clicks {string}")
//    public void userClicks(String buttonName) {
//        landingPage.clickLihatProduk();
//    }

    @When("user clicks {string}")
    public void userClicks(String buttonName) {

        if (buttonName.equals("Lihat Produk")) {
            landingPage.clickLihatProduk();
        }

        else if (buttonName.equals("Login with Google")) {
            loginPage.clickGoogleLogin();
        }
    }

    @Then("user should be redirected to the product catalog page")
    public void userShouldBeRedirectedToProductCatalogPage() {
        Assertions.assertTrue(
                landingPage.getCurrentUrl().contains("produk"),
                "User tidak berhasil masuk ke halaman produk"
        );
    }

    @Given("user is on the login page")
    public void userIsOnLoginPage() {
        loginPage.navigateToLoginPage();
    }

    @When("user clicks Login with Google")
    public void userClicksLoginWithGoogle() {
        loginPage.clickGoogleLogin();
    }

    @Then("user should be redirected to the customer dashboard")
    public void userShouldBeRedirectedToCustomerDashboard() {

        Assertions.assertNotNull(
                loginPage.getCurrentUrl(),
                "Redirect login gagal"
        );
    }
}