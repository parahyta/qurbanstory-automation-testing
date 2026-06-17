@regression
@product
Feature: Product Validation

  Background:
    Given user is on the product catalog page

  Scenario: TC-PRD-01 Navigasi ke Detail Produk dari Katalog
    When user clicks "Lihat Detail" on product "Sapi Patungan (Patungan (1/7))"
    Then user should see product detail page with price and stock availability

  Scenario: TC-PRD-02 Validasi Blokir Checkout Saat Stok Habis
    Given user is on the product detail page with "0" stock remaining
    When user views the buy button on the product detail page
    Then user should see the buy button displays text "Stok Habis"
    And the system should disable the payment submission button