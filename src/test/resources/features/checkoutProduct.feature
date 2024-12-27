@Test
Feature: Checkout a product

  @Debug
  Scenario: Checkout a product as guest customer
    Given I'm a guest customer
      | firstName | lastName |
      | Harry     | Potter   |
    And 1 product is in the cart
    And on the checkout page
    When I enter my billing details
    And place an order
    Then the order should be placed successfully