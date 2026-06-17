@regression
@payment
Feature: Payment Process

  Scenario: TC-PAY-01 Proses Checkout dan Pembayaran menggunakan BCA Virtual Account
    Given user has logged in and is on the checkout page for patungan product
    And user inputs "081234567890" as the phone number in checkout form
    And user adds pequrban name 6 times
    And the pequrban counter should be "7 / 7 Orang" and the add button is hidden
    When user clicks on Bayar Sekarang button
    And user selects BCA Virtual Account payment method
    And user completes the payment in the Midtrans simulator
    And user checks the payment status
    Then user should be redirected to the invoice page and see BERHASIL payment status
