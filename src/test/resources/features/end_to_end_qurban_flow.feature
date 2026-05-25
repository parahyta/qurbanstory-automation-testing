Feature: End-to-End Flow Pembelian Hewan Qurban Patungan

  As a Customer
  I want to purchase a shared qurban product successfully
  So that I can complete payment and monitor qurban documentation

  Background: User Access Application
    Given user accesses the Qurban Story website

  Scenario: Navigasi dari Landing Page ke Halaman Produk
    Given user is on the landing page
    When user clicks "Lihat Produk"
    Then user should be redirected to the product catalog page

  Scenario: User Login Menggunakan Google OAuth
    Given user is on the login page
    When user clicks "Login with Google"
    Then user should be redirected to the customer dashboard

  Scenario: Navigasi ke Detail Produk dari Katalog
    Given user is on the product catalog page
    When user clicks "Lihat Detail" on product "Sapi Patungan 1/7"
    Then user should see product detail page with price and stock availability

  Scenario: Validasi Blokir Checkout Saat Stok Habis
    Given user is on the product detail page with "0" stock remaining
    When user clicks the "Beli Sekarang" button
    Then user should see an error message "Stok Habis"
    And the system should disable the payment submission button

  Scenario Outline: Validasi Input Nomor Telepon Donatur
    Given user is on the checkout page for product "Sapi Patungan 1/7"
    When user enters phone number "<input_phone>"
    Then the field value should automatically sanitize to "<expected_phone>"

    Examples:
      | input_phone     | expected_phone  |
      | 081234567890    | 081234567890    |
      | 0812-3456-7890  | 081234567890    |
      | 0812abc3456     | 08123456        |

  Scenario: Validasi Batas Maksimal Partisipan Patungan
    Given user already inputs 7 participant names
    When user attempts to add another participant
    Then system should reject additional participant input
    And the add participant button should be disabled

  Scenario: Transaksi Berhasil dan Invoice Ditampilkan
    Given user is on the checkout page with valid form inputs
    When user clicks "Bayar Sekarang"
    And user completes payment using Midtrans Sandbox
    Then user should see the Invoice page with payment status "PAID"
    And transaction status should be recorded successfully

  Scenario: Validasi Transaksi Kadaluarsa
    Given user has an unpaid transaction
    When payment exceeds expiration limit
    Then system should display transaction status as "Expired"
    And user should be able to create a new transaction