#Feature: Calculator Addition
#Author: Ratnadip Chaudhuri

Feature: Validating calculation of complex expression using Calculator

  @calc @evaluateexpression
  Scenario Outline: Validate evaluation of Complex expressions
    Given User launches the Calculator application
    When User taps on "RAD" to switch to radian mode.
    And User enters the expression "<expression>" and evaluates the value.
    Then Result should be "<result>" corrected to 3 decimal places

    Examples: 
      | expression                                                                                               | result |
      | sine, pi, divide, [6], right parenthesis, divide, logarithm, square root, [e], right parenthesis, equals |  2.303 |
