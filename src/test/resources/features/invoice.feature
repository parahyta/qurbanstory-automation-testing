@regression
@invoice
Feature: Invoice Validation

  Scenario: TC-INV-01 Transaksi Berhasil Dibayar
    Given user has logged in and is on the checkout page for patungan product
    And user inputs "081234567890" as the phone number in checkout form
    And user adds pequrban name 6 times
    And the pequrban counter should be "7 / 7 Orang" and the add button is hidden
    When user clicks on Bayar Sekarang button
    And user selects BCA Virtual Account payment method
    And user completes the payment in the Midtrans simulator
    And user checks the payment status
    Then user should be redirected to the invoice page and see BERHASIL payment status

#  Scenario: TC-INV-02 Detail Invoice Ditampilkan
#    Given user already has a paid transaction
#    When user opens the invoice page
#    Then invoice number should be displayed
#    And transaction detail should be displayed
#
#  Scenario: TC-INV-03 Transaksi Kadaluarsa
#    Given user has an unpaid transaction
#    When payment exceeds expiration limit
#    Then invoice status should be displayed as "Expired"