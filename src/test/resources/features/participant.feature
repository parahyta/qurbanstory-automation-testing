@regression
@participant
Feature: Checkout Participant Quota

  Background:
    Given user has logged in and is on the checkout page for patungan product

  Scenario: TC-QTY-01 Sistem menerima 1 nama pequrban
    When user adds pequrban name 1 times
    Then the pequrban counter should be "2 / 7 Orang"

  Scenario: TC-QTY-02 Sistem menerima 4 nama pequrban
    When user adds pequrban name 4 times
    Then the pequrban counter should be "5 / 7 Orang"

  Scenario: TC-QTY-03 Tombol tambah disabled saat kuota penuh
    When user adds pequrban name 6 times
    Then the pequrban counter should be "7 / 7 Orang"
    And the add button should be hidden

  Scenario: TC-QTY-04 Sistem menolak penambahan melebihi kuota
    When user adds pequrban name 6 times
    Then the pequrban counter should be "7 / 7 Orang"
    And the add button should be hidden
    And user should not be able to add more pequrban names