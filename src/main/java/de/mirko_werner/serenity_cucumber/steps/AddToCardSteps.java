package de.mirko_werner.serenity_cucumber.steps;

import de.mirko_werner.serenity_cucumber.pages.CartPage;
import de.mirko_werner.serenity_cucumber.pages.HomePage;
import de.mirko_werner.serenity_cucumber.pages.StorePage;
import de.mirko_werner.serenity_cucumber.pages.components.ProductComponent;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import net.serenitybdd.core.steps.UIInteractions;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class AddToCardSteps extends UIInteractions {

    HomePage homePage;
    StorePage storePage;
    CartPage cartPage;
    ProductComponent productComponent;

    @Given("I'm on the store page")
    public void iMOnTheStorePage() {
        storePage.open();
    }

    @Given("I'm on the home page")
    public void iMOnTheHomePage() {
        homePage.open();
    }

    @When("I add {string} to the Cart")
    public void iAddToTheCart(String productName) {
        productComponent.addToCart(productName);
    }

    @Then("I see {int} {string} with {string} in the Cart")
    public void iSeeOneInTheCart(int quantity, String productName, String price) {
        assertThat(cartPage.getProductName(), is(productName));
        assertThat(cartPage.getProductPrice(), is(price));
        assertThat(Integer.parseInt(cartPage.getProductQuantity()), is(quantity));
    }

    @And("The total price is {string}")
    public void theTotalPriceIs(String totalprice) {
        assertThat(cartPage.getTotalPrice(), is(totalprice));
    }
}