@smoke
@landing
Feature: Landing Page Navigation

  Scenario: TC-LND-01 Navigasi dari Landing Page ke Halaman Produk
    Given user is on the landing page
    When user clicks "Lihat Produk"
    Then user should be redirected to the product catalog page
