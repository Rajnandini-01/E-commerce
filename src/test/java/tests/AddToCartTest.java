package tests;

import pages.*;
import org.testng.Assert;
import org.testng.annotations.Test;

import base.BaseTest;

public class AddToCartTest extends BaseTest {

    @Test
    public void TC_004_addSingleItem() throws InterruptedException {

        LoginPage login = new LoginPage(getDriver());
        login.login("demouser", "testingisfun99");

        ProductPage product = new ProductPage(getDriver());
        product.addSingleItem();
    }

    @Test
    public void TC_005_addMultipleItems() throws InterruptedException {

        LoginPage login = new LoginPage(getDriver());
        login.login("demouser", "testingisfun99");

        ProductPage product = new ProductPage(getDriver());
        product.addMultipleItems(2);
    }

    @Test
    public void TC_006_removeItemFromCart() throws InterruptedException {

        LoginPage login = new LoginPage(getDriver());
        login.login("demouser", "testingisfun99");

        ProductPage product = new ProductPage(getDriver());
        product.addSingleItem();
        product.openCart();

        CartPage cart = new CartPage(getDriver());
        cart.removeItem();
    }
}