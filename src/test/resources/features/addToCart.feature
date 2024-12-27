@Test
Feature: Add to cart

  Rule: Add from home page
    @Debug
    Scenario Outline: Add one product to the cart
      Given I'm on the home page
      When I add "<product_name>" to the Cart
      Then I see <quantity> "<product_name>" with "<price>" in the Cart
      And The total price is "<total_price>"
      Examples:
        | product_name     | quantity | price  | total_price |
        | Denim Blue Jeans | 1        | $100.00 | $112.50   |


  Rule: Add from store page
    @Smoke
    @Debug
    Scenario: Add on product to the cart
      Given I'm on the store page
      When I add "Basic Blue Jeans" to the Cart
      Then I see 1 "Basic Blue Jeans" with "$30.00" in the Cart
      And The total price is "$37.25"