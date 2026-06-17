//package org.example.stepdefinitions;
//
//import io.cucumber.java.en.Then;
//import org.example.pages.InvoicePage;
//import org.junit.jupiter.api.Assertions;
//
//public class InvoiceSteps {
//
//    private InvoicePage invoicePage = new InvoicePage();
//
//    @Then("invoice detail information should be displayed")
//    public void invoiceDetailInformationShouldBeDisplayed() {
//
//        Assertions.assertTrue(
//                invoicePage.isInvoiceNumberDisplayed(),
//                "Nomor invoice tidak tampil"
//        );
//
//        Assertions.assertTrue(
//                invoicePage.isNominalDisplayed(),
//                "Nominal tidak tampil"
//        );
//
//        Assertions.assertTrue(
//                invoicePage.isPaymentMethodDisplayed(),
//                "Metode pembayaran tidak tampil"
//        );
//
//        Assertions.assertTrue(
//                invoicePage.isTransactionDateDisplayed(),
//                "Tanggal transaksi tidak tampil"
//        );
//    }
//}