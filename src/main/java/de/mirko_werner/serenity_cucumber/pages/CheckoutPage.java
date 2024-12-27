package de.mirko_werner.serenity_cucumber.pages;

import de.mirko_werner.testdata.persistence.models.Address;
import de.mirko_werner.testdata.persistence.models.Customer;
import net.serenitybdd.core.annotations.findby.FindBy;
import net.serenitybdd.core.pages.PageObject;
import net.serenitybdd.core.pages.WebElementFacade;
import org.openqa.selenium.Keys;

import java.util.List;

public class CheckoutPage extends PageObject {

    @FindBy(id = "billing_first_name")
    private WebElementFacade firstName;

    @FindBy(id = "billing_last_name")
    private WebElementFacade lastName;

    @FindBy(id = "select2-billing_country-container")
    private WebElementFacade countryDropdownIcon;

    @FindBy(css = "input[class='select2-search__field']")
    private WebElementFacade searchFieldCountry;

    @FindBy(id = "billing_address_1")
    private WebElementFacade streetAndNumber;

    @FindBy(id = "billing_city")
    private WebElementFacade city;

    @FindBy(id = "select2-billing_state-container")
    private WebElementFacade stateDropdownIcon;

    @FindBy(css = "input[class='select2-search__field']")
    private WebElementFacade searchFieldState;

    @FindBy(id = "billing_postcode")
    private WebElementFacade postcode;

    @FindBy(id = "billing_email")
    private WebElementFacade email;

    @FindBy(id = "place_order")
    private WebElementFacade placeOrderBtn;

    @FindBy(css = ".blockUI.blockOverlay")
    private List<WebElementFacade> blockOverlays;

    @FindBy(css = "p[class='woocommerce-notice woocommerce-notice--success woocommerce-thankyou-order-received']")
    private WebElementFacade thankYouOrderText;

    public void addBillingDetails(Customer customer) {
        email.waitUntilVisible();
        Address mainAddress = customer.getAddressList().stream()
                .filter(address -> address.addressType().contentEquals("primary")).findFirst()
                        .orElse(null);
        assert mainAddress != null;
        firstName.sendKeys(customer.getFirstName());
        lastName.sendKeys(customer.getLastName());
        postcode.sendKeys(mainAddress.postalCode());
        streetAndNumber.sendKeys(mainAddress.street() + " " + mainAddress.number());
        email.sendKeys(customer.getEmailAddress());
        city.sendKeys(mainAddress.city());
        countryDropdownIcon.click();
        searchFieldCountry.sendKeys(mainAddress.country().getName());
        searchFieldCountry.sendKeys(Keys.RETURN);
        stateDropdownIcon.click();
        searchFieldState.sendKeys(mainAddress.state().getName());
        searchFieldState.sendKeys(Keys.RETURN);
    }
    public void placeOrder() {
        blockOverlays.forEach(WebElementFacade::waitUntilNotVisible);
        placeOrderBtn.waitUntilClickable().click();
    }

    public String checkThankYouOrderText() {
        blockOverlays.forEach(WebElementFacade::waitUntilNotVisible);

        return thankYouOrderText.waitUntilVisible().getText();
    }
}
