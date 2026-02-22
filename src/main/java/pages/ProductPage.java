package pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.*;
import java.time.Duration;
import java.util.List;

public class ProductPage {

    WebDriver driver;
    WebDriverWait wait;

    public ProductPage(WebDriver driver) {
        this.driver = driver;
        wait = new WebDriverWait(driver, Duration.ofSeconds(15));
    }

    By addToCartButtons = By.xpath("//div[@class='shelf-item__buy-btn']");
    By cartIcon = By.className("bag");
    By cartCount = By.className("bag__quantity");

    public void waitForProducts() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(addToCartButtons));
    }

    public void addSingleItem() {
        waitForProducts();
        List<WebElement> items = driver.findElements(addToCartButtons);
        items.get(0).click();
    }

    public void addMultipleItems(int count) {
        waitForProducts();
        List<WebElement> items = driver.findElements(addToCartButtons);

        for (int i = 0; i < count && i < items.size(); i++) {
            items.get(i).click();
        }
    }

    public void openCart() {

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));

        WebElement cart = wait.until(
            ExpectedConditions.elementToBeClickable(cartIcon)
        );

        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", cart);
    }

    public String getCartCount() {
        return driver.findElement(cartCount).getText();
    }
}