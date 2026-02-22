package pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.*;

import java.time.Duration;

public class LoginPage {

	WebDriver driver;
	WebDriverWait wait;

	public LoginPage(WebDriver driver) {
		this.driver = driver;
		wait = new WebDriverWait(driver, Duration.ofSeconds(15));
	}

	By signInBtn = By.id("signin");
	By usernameDropdown = By.id("username");
	By passwordDropdown = By.id("password");
	By loginBtn = By.id("login-btn");
	By productsLoaded = By.className("shelf-item");

	public void login(String username, String password) {

		// Click Sign In
		wait.until(ExpectedConditions.elementToBeClickable(signInBtn)).click();

		// Select Username
		wait.until(ExpectedConditions.elementToBeClickable(usernameDropdown)).click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[text()='" + username + "']"))).click();

		// Select Password
		wait.until(ExpectedConditions.elementToBeClickable(passwordDropdown)).click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[text()='" + password + "']"))).click();

		// Click Login
		wait.until(ExpectedConditions.elementToBeClickable(loginBtn)).click();

		// Wait max 5 sec for products (only if login succeeds)
		try {
			wait.withTimeout(Duration.ofSeconds(5))
					.until(ExpectedConditions.visibilityOfElementLocated(productsLoaded));
		} catch (Exception e) {
			// Invalid login case - ignore
		}
	}

	public void clickLoginWithoutCredentials() {
		wait.until(ExpectedConditions.elementToBeClickable(signInBtn)).click();
		wait.until(ExpectedConditions.elementToBeClickable(loginBtn)).click();
	}
}