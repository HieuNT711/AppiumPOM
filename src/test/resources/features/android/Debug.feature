Feature: ABC

  Scenario: Chuyen tien MB
    When I login MB bank
    When I transfer to "MB" bank with receive account "ZZZZ" and amount "50000" with Dotp "071199"


  Scenario: Chuyen tien NAPAS
    When I login MB bank
    When I transfer to "DAB" bank with receive account "0129837294" and amount "50000" with Dotp "071199"


  Scenario: Chuyen tien MB 2
    When I login MB bank
    When I go to Inquiry Screen
    And I input transfer money Info bankCode "MB" receiveNumber "ZZZZ"  amout "20000"
    And I confirm transfer money by DOTP "071199"

  Scenario: Chuyen tien NAPAS 2
    When I login MB bank
    When I go to Inquiry Screen
    And I input transfer money Info bankCode "DAB" receiveNumber "0129837294"  amout "20000"
    And I confirm transfer money by DOTP "071199"
