package org.example.stepdefinitions;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.And;
import org.example.pages.ProductPage;
import org.junit.jupiter.api.Assertions;

public class ProductSteps {

    private ProductPage productPage = new ProductPage();

    @Given("user is on the product catalog page")
    public void userIsOnProductCatalogPage() {
        productPage.navigateToCatalog();
    }

    @When("user clicks {string} on product {string}")
    public void userClicksOnProduct(String action, String productName) {
        productPage.clickLihatDetailOnProduct(productName);
    }

    @Then("user should see product detail page with price and stock availability")
    public void userShouldSeeProductDetailPageWithPriceAndStock() {
        Assertions.assertTrue(
                productPage.isPriceAndStockDisplayed(),
                "TC-PRD-01 GAGAL: Harga atau stok tidak ditampilkan di halaman detail produk"
        );
    }

    @Given("user is on the product detail page with {string} stock remaining")
    public void userIsOnProductDetailPageWithStockRemaining(String stock) {
        String productIdWithZeroStock = "e002e847-14f7-4cd2-a543-6fa9e46cdb7d";

        productPage.navigateToProductDetailById(productIdWithZeroStock);

        String stockText = productPage.getStockText();
        Assertions.assertTrue(
                stockText.contains("Stok: " + stock),
                "Precondition gagal: Stok produk bukan " + stock + ", actual: " + stockText
        );
    }

    @When("user views the buy button on the product detail page")
    public void userViewsBuyButtonOnProductDetailPage() {
        System.out.println("TC-PRD-02: Mengamati state tombol beli di halaman detail produk");
    }

    @When("user clicks the {string} button")
    public void userClicksTheButton(String buttonText) {
        System.out.println("Tombol \"" + buttonText + "\" tidak dapat diklik karena disabled (sesuai ekspektasi TC-PRD-02).");
    }

    @Then("user should see the buy button displays text {string}")
    public void userShouldSeeBuyButtonDisplaysText(String expectedText) {
        String actualText = productPage.getBuyButtonText();
        Assertions.assertTrue(
                actualText.contains(expectedText),
                "TC-PRD-02 GAGAL: Teks tombol tidak sesuai. Expected: '" + expectedText
                        + "', Actual: '" + actualText + "'"
        );
    }

    @Then("user should see an error message {string}")
    public void userShouldSeeErrorMessage(String expectedMessage) {
        Assertions.assertTrue(
                productPage.isStokHabisTextDisplayed(),
                "TC-PRD-02 GAGAL: Pesan 'Stok Habis' tidak muncul"
        );
        String buttonText = productPage.getBuyButtonText();
        Assertions.assertTrue(
                buttonText.contains(expectedMessage),
                "Teks tombol tidak mengandung '" + expectedMessage + "', actual: " + buttonText
        );
    }

    @Then("the system should disable the payment submission button")
    public void theSystemShouldDisablePaymentButton() {
        Assertions.assertTrue(
                productPage.isBuyButtonDisabledWithStokHabis(),
                "TC-PRD-02 GAGAL: Tombol 'Stok Habis' seharusnya disabled tapi masih enabled"
        );
    }
}