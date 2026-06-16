package org.example.stepdefinitions;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.example.pages.CheckoutPage;
import org.example.pages.InvoicePage;
import org.example.pages.PaymentPage;
import org.example.utils.DriverManager;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.Cookie;

public class PaymentSteps {

    private CheckoutPage checkoutPage = new CheckoutPage();
    private PaymentPage paymentPage = new PaymentPage();
    private InvoicePage invoicePage = new InvoicePage();

    private String savedVaNumber;
    private String checkoutWindowHandle;

    @Given("user has logged in and is on the checkout page for patungan product")
    public void userHasLoggedInAndIsOnCheckoutPageForPatunganProduct() {
        DriverManager.getDriver().get("https://qurban-story.vercel.app");

        // The session token is necessary to bypass login
        String sessionToken = "eyJhbGciOiJkaXIiLCJlbmMiOiJBMjU2R0NNIn0..lZfE4u7kQbHRanaC.bYQf9Q6zyZlV-8sIRiPUHrothBWGFQw7f7BfPblSSByQmV7kjOAphX_S82TB6XvO5DZUnzQXMLL-0GzcE3IeA0O9jGla770uaB1odY2C-EwrxJNx3QHF61KGNUgPNosM9hlT5TL1H5tZxRn0tzUp0YsOfYA4d25GyR7G4JrVIoZj5YcEjElD1TAAQeGLtx0w5yJsKDrP_5TwfqWxzCMfJmrDAirvufAfpyCiybTGBLfrgXLIcRLuardcGTlHNsmIfg8xMj0ouEnHVAc6AGycbsLLpbfa7lBAf7qaqdSFTcQGHFs3Jjc6RcYk2nz-R87ldSF59L_aAavzN5D6IETQ1Z8rZntp8w1UR-cVQmqxN6lvtzf6ml7WGePfqCdqGc7Wxrwby59KAqds7aq2n-FZTouW09We_9nrV0BaCzLYQ_RVoTd61zqPNtPY8u3g463a.1lMLUwA97wmt8g_WtPblsg";

        Cookie sessionCookie = new Cookie.Builder("__Secure-next-auth.session-token", sessionToken)
                .isHttpOnly(true)
                .isSecure(true)
                .path("/")
                .build();

        DriverManager.getDriver().manage().addCookie(sessionCookie);
        DriverManager.getDriver().navigate().refresh();

        DriverManager.getDriver().get("https://qurban-story.vercel.app/checkout/a626be25-2937-4589-84b0-1b109e74c9bc");
    }

    @And("user inputs {string} as the phone number in checkout form")
    public void userInputsAsPhoneInCheckoutForm(String phone) {
        checkoutPage.inputPhoneFallback(phone);
    }

    @And("user adds pequrban name {int} times")
    public void userAddsPequrbanNameTimes(int times) throws InterruptedException {
        checkoutPage.clickTambahNamaPequrban(times);
    }

    @And("the pequrban counter should be {string} and the add button is hidden")
    public void thePequrbanCounterShouldBeAndAddButtonIsHidden(String expectedCounterText) {
        Assertions.assertTrue(checkoutPage.isCounterDisplayed(expectedCounterText), "Counter " + expectedCounterText + " tidak tampil");
        Assertions.assertTrue(checkoutPage.isTambahButtonHidden(), "Tombol tambah pequrban masih muncul padahal kuota penuh");
    }

    @When("user clicks on Bayar Sekarang button")
    public void userClicksOnBayarSekarangButton() {
        checkoutPage.clickBayarSekarang();
    }

    @And("user selects BCA Virtual Account payment method")
    public void userSelectsBCAVirtualAccountPaymentMethod() {
        paymentPage.switchToMidtransIframe();
        paymentPage.clickVirtualAccountBca();
        savedVaNumber = paymentPage.getVaNumber();
        Assertions.assertFalse(savedVaNumber.isEmpty(), "Nomor VA kosong");
        paymentPage.switchToDefaultContent();
    }

    @And("user completes the payment in the Midtrans simulator")
    public void userCompletesThePaymentInTheMidtransSimulator() throws InterruptedException {
        checkoutWindowHandle = paymentPage.getCurrentWindowHandle();
        paymentPage.openSimulatorInNewTab();
        paymentPage.completePaymentInSimulator(savedVaNumber);

        // Wait 1s as in original code
        Thread.sleep(1000);

        paymentPage.closeSimulatorTabAndSwitchToCheckout(checkoutWindowHandle);
    }

    @And("user checks the payment status")
    public void userChecksThePaymentStatus() {
        paymentPage.switchToMidtransIframe();
        paymentPage.clickCheckStatus();
        paymentPage.switchToDefaultContent();
    }

    @Then("user should be redirected to the invoice page and see BERHASIL payment status")
    public void userShouldBeRedirectedToTheInvoicePageAndSeeBERHASILPaymentStatus() {
        invoicePage.waitForInvoicePageToLoad();
        String currentUrl = invoicePage.getCurrentUrl();
        Assertions.assertTrue(currentUrl.contains("qurban-story.vercel.app/invoice/"),
                "URL bukan halaman invoice: " + currentUrl);

        boolean isSuccess = invoicePage.isPaymentStatusSuccess();
        Assertions.assertTrue(isSuccess, "Status 'BERHASIL' tidak ditemukan di halaman invoice");
    }
}
