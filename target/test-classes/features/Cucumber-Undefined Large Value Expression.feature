#Feature: Validate evaluation of Calculator expression for Value too large.
#Author: Ratnadip Chaudhuri

Feature: Validate evaluation of Calculator expression for Value too large.

  @calc @undefinedvalue
  Scenario Outline: Validate evaluation of Complex expressions
    Given User launches the Calculator application
    When User enters the expression "<expression>" and evaluates the value.
    Then Validate the validation message is "<validation message>"

    Examples: 
      | expression                									| validation message | 
      | [9], power, [9], [9], [9], [9], [9], equals	| Value too large 	 | 
