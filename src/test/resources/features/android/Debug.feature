Feature: ABC

  Scenario: Đăng kí DOTP
    When I login MB bank
    And I go to Digital OTP screen
    When I register DOTP

  Scenario: Chuyen tien MB
    When I login MB bank
    When I transfer to "MB" bank with receive account "ZZZZ" and amount "50000" with DOTP


  Scenario: Chuyen tien NAPAS
    When I login MB bank
    When I transfer to "DAB" bank with receive account "0129837294" and amount "50000" with DOTP


  Scenario: Chuyen tien MB 2
    When I login MB bank
    When I go to Inquiry Screen
    And I input transfer money Info bankCode "MB" receiveNumber "ZZZZ"  amout "50000"
    And I confirm transfer money by DOTP

  Scenario: Chuyen tien NAPAS 2
    When I login MB bank
    When I go to Inquiry Screen
    And I input transfer money Info bankCode "DAB" receiveNumber "0129837294"  amout "50000"
    And I confirm transfer money by DOTP
