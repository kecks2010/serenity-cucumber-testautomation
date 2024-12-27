package de.mirko_werner.serenity_cucumber.pages.components;

import net.serenitybdd.core.annotations.findby.FindBy;
import net.serenitybdd.core.pages.PageComponent;
import net.serenitybdd.core.pages.WebElementFacade;

import java.util.List;
import java.util.Objects;

public class ProductComponent extends PageComponent {

    @FindBy(css = "a[title='View cart']")
    private WebElementFacade viewCart;

    @FindBy(css = "a.button.product_type_simple.add_to_cart_button.ajax_add_to_cart")
    private List<WebElementFacade> addToCardLinks;

    public void addToCart(String productName) {
        WebElementFacade addToCartLink = addToCardLinks.stream()
                .filter(webElement -> Objects.requireNonNull(webElement.getDomAttribute("aria-label")).contains(productName))
                .findAny().orElse(null);
        assert addToCartLink != null;
        addToCartLink.waitUntilClickable().click();
        viewCart.waitUntilClickable().click();
    }
}
