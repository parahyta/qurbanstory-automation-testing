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

  Scenario: TC-PRD-01 Navigasi ke Detail Produk dari Katalog
    Given user is on the product catalog page
    When user clicks "Lihat Detail" on product "Sapi Patungan (Patungan (1/7))"
    Then user should see product detail page with price and stock availability

  Scenario: TC-PRD-02 Validasi Blokir Checkout Saat Stok Habis
    Given user is on the product detail page with "0" stock remaining
    When user views the buy button on the product detail page
    Then user should see the buy button displays text "Stok Habis"
    And the system should disable the payment submission button

  Scenario Outline: Validasi Input Nomor Telepon Donatur
    Given user is on the checkout page for product "Sapi Patungan (Patungan (1/7))"
    When user enters phone number "<input_phone>"
    Then the field value should automatically sanitize to "<expected_phone>"

    Examples:
    Examples:
      | tc_id    | teknik    | input_phone       | expected_phone  | keterangan                                 |
      | TC-CHK-01| EP Valid  | 81234567890       | 81234567890     | Input valid, hanya angka                   |
      | TC-CHK-02| EP Invalid| 0812abc345        | 0812345         | Non-angka dihapus otomatis                 |
      | TC-CHK-03| BVA Min-1 | 81234567          | 81234567        | 8 digit, di bawah minimum 9                |
      | TC-CHK-04| BVA Min   | 812345678         | 812345678       | 9 digit, tepat minimum                     |
      | TC-CHK-05| BVA Max   | 812345678901234   | 812345678901234 | 15 digit, masih dalam batas (maxLength=15) |
      | TC-CHK-06| BVA Max+1 | 8123456789012345  | 812345678901234 | 16 digit dipotong ke 15 (maxLength)        |

  Scenario: Proses Checkout dan Pembayaran menggunakan BCA Virtual Account
    Given user has logged in and is on the checkout page for patungan product
    And user inputs "081234567890" as the phone number in checkout form
    And user adds pequrban name 6 times
    And the pequrban counter should be "7 / 7 Orang" and the add button is hidden
    When user clicks on Bayar Sekarang button
    And user selects BCA Virtual Account payment method
    And user completes the payment in the Midtrans simulator
    And user checks the payment status
    Then user should be redirected to the invoice page and see BERHASIL payment status
