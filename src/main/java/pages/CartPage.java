package pages;

import java.time.Duration;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class CartPage {

    WebDriver driver;

    public CartPage(WebDriver driver) {
        this.driver = driver;
    }

    By removeBtn = By.cssSelector(".shelf-item__del");
    By checkoutButton = By.xpath("//div[text()='Checkout']");

    public void removeItem() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.elementToBeClickable(removeBtn)).click();
    }
    
    public void clickCheckout() {
        driver.findElement(checkoutButton).click();
    }
}