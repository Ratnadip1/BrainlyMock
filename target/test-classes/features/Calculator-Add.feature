#Feature: Calculator Addition
#Author: Ratnadip Chaudhuri

Feature: Validating addition functionality of Calculator
  It validates the functionality of the Calculator

  @calc @addition
  Scenario Outline: Validate addition of 2 numbers
    Given User launches the Calculator application
    When User adds the numbers
      | <num1> |
      | <num2> |
    Then Result should be "<result>"

    Examples: 
      | num1 | num2 | result |
      |    2 |    2 |      4 |
