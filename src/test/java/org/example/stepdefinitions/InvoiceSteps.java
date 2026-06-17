package org.example.stepdefinitions;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import org.example.pages.InvoicePage;
import org.example.utils.DriverManager;
import org.junit.jupiter.api.Assertions;

public class InvoiceSteps {

    private InvoicePage invoicePage = new InvoicePage();

    @Given("user is on a paid invoice page")
    public void userIsOnPaidInvoicePage() {

        DriverManager.getDriver().get(
                "https://qurban-story.vercel.app/invoice/37f78bb8-b2a8-4a52-97de-025c3ee420b0"
        );

        invoicePage.waitForInvoicePageToLoad();
    }

    @Then("invoice number should be displayed")
    public void invoiceNumberShouldBeDisplayed() {
        Assertions.assertTrue(
                invoicePage.isInvoiceNumberDisplayed(),
                "Nomor invoice tidak tampil"
        );
    }

    @Then("transaction amount should be displayed")
    public void transactionAmountShouldBeDisplayed() {
        Assertions.assertTrue(
                invoicePage.isTransactionAmountDisplayed(),
                "Nominal transaksi tidak tampil"
        );
    }

    @Then("payment method should be displayed")
    public void paymentMethodShouldBeDisplayed() {
        Assertions.assertTrue(
                invoicePage.isPaymentMethodDisplayed(),
                "Metode pembayaran tidak tampil"
        );
    }

    @Then("transaction date should be displayed")
    public void transactionDateShouldBeDisplayed() {
        Assertions.assertTrue(
                invoicePage.isTransactionDateDisplayed(),
                "Tanggal transaksi tidak tampil"
        );
    }

    @Given("user is on an expired invoice page")
    public void userIsOnAnExpiredInvoicePage() {

        DriverManager.getDriver().get(
                "https://qurban-story.vercel.app/invoice/1f16a275-ffc7-49f1-af34-e500d36cb687"
        );

        invoicePage.waitForInvoicePageToLoad();
    }

    @Then("invoice status should be displayed as expired")
    public void invoiceStatusShouldBeDisplayedAsExpired() {

        Assertions.assertTrue(
                invoicePage.isPaymentStatusExpired(),
                "Status KADALUARSA tidak ditemukan"
        );
    }
}