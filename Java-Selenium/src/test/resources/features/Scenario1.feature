Feature: Capstone Project 4

  Scenario: Capstone Project 4
    Given I am on home page
    Then I verify the title

    When I click on "A/B Testing" link
    Then I verify one of following text is present in page
    |A/B Test Control|
    |A/B Test Variation 1|
    Then I navigate back

    When I click on "Dropdown" link
    Then I select "Option 1" from "dropdown"
    Then I verify "Option 1" is selected in "dropdown"
    Then I navigate back
    
    When I click on "Frames" link
    Then I verify all of below "links" are present in page
    |Nested Frames|
    |iFrame|

    Then I quit