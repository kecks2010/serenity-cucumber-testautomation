package de.mirko_werner.serenity_cucumber.api;

import de.mirko_werner.testdata.persistence.models.Product;
import de.mirko_werner.testdata.repositories.ProductRepository;
import io.restassured.http.Cookies;
import io.restassured.response.Response;
import net.serenitybdd.core.pages.PageObject;
import net.serenitybdd.rest.SerenityRest;
import net.thucydides.model.util.EnvironmentVariables;

@EndPoint("/?wc-ajax=add_to_cart")
public class AddToCartInterface extends PageObject {

    EnvironmentVariables environmentVariables;

    public Cookies addRandomProductToCart(int quantity) {

        Product product = ProductRepository.getInstance().getRandomProduct();

        String url = environmentVariables.getProperty("webdriver.base.url");
        String endpoint = this.getClass().getAnnotation(EndPoint.class).value();

        Response response = SerenityRest
                .given()
                    .contentType("application/x-www-form-urlencoded")
                    .formParam("product_sku", "")
                    .formParam("product_id", product.id())
                    .formParam("quantity", quantity)
                .when()
                    .post(url + endpoint)
                .then()
                    .extract().response();
        if (response.getStatusCode() != 200) {
            throw new RuntimeException("Failed to add product " + product.id() + " to the cart, " +
                    "HTTP status code: " + response.getStatusCode());
        }

        return response.detailedCookies();
    }
}
