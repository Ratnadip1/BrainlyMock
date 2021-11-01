#Feature: Calculator - Divide by Zero
#Author: Ratnadip Chaudhuri

Feature: Validate divide by zero error in Calculator

  @calc @dividebyzero
  Scenario Outline: Validate evaluation of Complex expressions
    Given User launches the Calculator application
    When User enters the expression "<expression>" and evaluates the value.
    Then Validate the validation message is "<validation message>"

    Examples: 
      | expression                | validation message | 
      | [5], divide, [0], equals	| Can't divide by 0	 | 