package pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.*;
import java.time.Duration;

public class CheckoutPage {

    WebDriver driver;
    WebDriverWait wait;

    public CheckoutPage(WebDriver driver) {
        this.driver = driver;
        wait = new WebDriverWait(driver, Duration.ofSeconds(15));
    }

    By checkoutBtn = By.xpath("//div[text()='Checkout']");
    By continueBtn = By.id("checkout-shipping-continue");
    By firstName = By.id("firstNameInput");
    By lastName = By.id("lastNameInput");
    By address = By.id("addressLine1Input");
    By postalCode = By.id("postCodeInput");
    By submitBtn = By.id("checkout-shipping-continue");

    public void placeOrder() {

        wait.until(ExpectedConditions.elementToBeClickable(checkoutBtn)).click();

        wait.until(ExpectedConditions.visibilityOfElementLocated(firstName)).sendKeys("Juhi");
        driver.findElement(lastName).sendKeys("Giri");
        driver.findElement(address).sendKeys("Mumbai");
        driver.findElement(postalCode).sendKeys("400001");

        driver.findElement(submitBtn).click();
    }
}