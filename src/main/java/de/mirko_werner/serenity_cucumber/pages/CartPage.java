package de.mirko_werner.serenity_cucumber.pages;

import net.serenitybdd.annotations.DefaultUrl;
import net.serenitybdd.core.annotations.findby.FindBy;
import net.serenitybdd.core.pages.PageObject;
import net.serenitybdd.core.pages.WebElementFacade;
import org.openqa.selenium.Cookie;

@DefaultUrl("/cart")
public class CartPage extends PageObject {

    @FindBy(css = "td[class='product-name'] a")
    private WebElementFacade productNameField;

    @FindBy(css = "input[type='number']")
    private WebElementFacade productQuantityField;

    @FindBy(css = "span[class='woocommerce-Price-amount amount']")
    private WebElementFacade productPriceField;

    @FindBy(xpath = "//th[contains(text(), 'Total')]/following::td//bdi")
    private WebElementFacade totalPriceField;

    @FindBy(css = "a[class='checkout-button button alt wc-forward']")
    private WebElementFacade checkoutButton;

    public String getProductName() {
        return productNameField.waitUntilVisible().getText();
    }

    public String getProductQuantity() {
        return productQuantityField.waitUntilVisible().getDomAttribute("value");
    }

    public String getProductPrice() {
        return productPriceField.waitUntilVisible().getText();
    }

    public String getTotalPrice() {
        return totalPriceField.waitUntilVisible().getText();
    }

    public void proceedToCheckout() {
        checkoutButton.waitUntilVisible().click();
    }

    public void setCookies(io.restassured.http.Cookies restAssuredCookies) {
        restAssuredCookies.asList().forEach(restAssurdCookie -> getDriver().manage()
                .addCookie(new Cookie(restAssurdCookie.getName(), restAssurdCookie.getValue(), restAssurdCookie.getDomain(),
                restAssurdCookie.getPath(), restAssurdCookie.getExpiryDate(), restAssurdCookie.isSecured(),
                restAssurdCookie.isHttpOnly(), restAssurdCookie.getSameSite())));
        getDriver().navigate().refresh();
    }
}
