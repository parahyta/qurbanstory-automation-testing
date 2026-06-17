@regression
@checkout
Feature: Checkout Phone Validation

  Scenario Outline: Validasi Input Nomor Telepon Donatur
    Given user is on the checkout page for product "Sapi Patungan (Patungan (1/7))"
    When user enters phone number "<input_phone>"
    Then the field value should automatically sanitize to "<expected_phone>"

    Examples:
      | tc_id     | teknik     | input_phone      | expected_phone  | keterangan                                 |
      | TC-CHK-01 | EP Valid   | 81234567890      | 81234567890     | Input valid, hanya angka                   |
      | TC-CHK-02 | EP Invalid | 0812abc345       | 0812345         | Non-angka dihapus otomatis                 |
      | TC-CHK-03 | BVA Min-1  | 81234567         | 81234567        | 8 digit, di bawah minimum 9                |
      | TC-CHK-04 | BVA Min    | 812345678        | 812345678       | 9 digit, tepat minimum                     |
      | TC-CHK-05 | BVA Max    | 812345678901234  | 812345678901234 | 15 digit, masih dalam batas (maxLength=15) |
      | TC-CHK-06 | BVA Max+1  | 8123456789012345 | 812345678901234 | 16 digit dipotong ke 15 (maxLength)        |

