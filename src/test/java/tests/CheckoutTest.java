package tests;

import org.testng.annotations.Test;

import base.BaseTest;
import pages.LoginPage;
import pages.ProductPage;
import pages.CheckoutPage;

public class CheckoutTest extends BaseTest {

    // TC_007 - Place order with item
    @Test
    public void TC_007_placeOrder() throws InterruptedException {

        // Login first (IMPORTANT)
        LoginPage login = new LoginPage(getDriver());
        login.login("demouser", "testingisfun99");

        // Add product
        ProductPage product = new ProductPage(getDriver());
        product.addSingleItem();

        // Open cart
        product.openCart();

        // Checkout
        CheckoutPage checkout = new CheckoutPage(getDriver());
        checkout.placeOrder();
    }

    // TC_008 - Checkout without adding items
    @Test
    public void TC_008_checkoutWithoutItems() {

        LoginPage login = new LoginPage(getDriver());
        login.login("demouser", "testingisfun99");

        ProductPage product = new ProductPage(getDriver());
        product.openCart();

        // Do NOT call placeOrder
        // Just verify cart opened
    }
}