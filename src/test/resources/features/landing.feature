@smoke
@landing
Feature: Landing Page Navigation

  Background:
    Given user accesses the Qurban Story website

  Scenario: TC-LND-01 Navigasi dari Landing Page ke Halaman Produk
    Given user is on the landing page
    When user clicks "Lihat Produk"
    Then user should be redirected to the product catalog page

  Scenario: TC-LND-02 Navigasi dari Landing Page ke Halaman Login
    Given user is on the landing page
    When user clicks "Login"
    Then user should be redirected to the login page