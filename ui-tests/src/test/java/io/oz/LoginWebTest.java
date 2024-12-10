package io.oz;

import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class LoginWebTest extends WebTest {
    private static final String EMAIL = "test-user-01@chargelab.co";
    private static final String BAD_EMAIL = "bad";
    private static final String ACCESS_CODE = "00000";
    private static final String BAD_ACCESS_CODE = "12345";

    @Test
    public void shouldSuccessfullyLogout() {
        withDriver(webDriver -> {
            final By logInButtonSelector = By.xpath("//button[text()=\"Log in\"]");

            new WebDriverWait(webDriver, Duration.ofSeconds(5))
                    .until(ExpectedConditions.elementToBeClickable(logInButtonSelector));
            webDriver.findElement(logInButtonSelector)
                    .click();

            final By inputSelector = By.className("MuiInputBase-input");

            new WebDriverWait(webDriver, Duration.ofSeconds(5))
                    .until(ExpectedConditions.visibilityOfElementLocated(inputSelector));
            webDriver.findElement(inputSelector)
                    .sendKeys(EMAIL);

            final By nextButtonSelector = By.xpath("//button[text()=\"Next\"]");
            final By signInButtonSelector = By.xpath("//button[text()=\"Sign in\"]");

            webDriver.findElement(nextButtonSelector)
                    .click();

            new WebDriverWait(webDriver, Duration.ofSeconds(10))
                    .until(ExpectedConditions.visibilityOfElementLocated(signInButtonSelector));
            webDriver.findElement(inputSelector)
                    .sendKeys(ACCESS_CODE);

            final By accountButtonSelector = By.xpath("//button[@aria-label=\"Go to my account\"]");

            new WebDriverWait(webDriver, Duration.ofSeconds(10))
                    .until(ExpectedConditions.visibilityOfElementLocated(accountButtonSelector));
            webDriver.findElement(accountButtonSelector)
                    .click();

            final By logOutButtonSelector = By.xpath("//button[text()=\"Log out\"]");

            new WebDriverWait(webDriver, Duration.ofSeconds(10))
                    .until(ExpectedConditions.elementToBeClickable(logOutButtonSelector));
            webDriver.findElement(logOutButtonSelector)
                    .click();

            new WebDriverWait(webDriver, Duration.ofSeconds(10))
                    .until(ExpectedConditions.elementToBeClickable(logOutButtonSelector));
            webDriver.findElement(logOutButtonSelector)
                    .click();

            new WebDriverWait(webDriver, Duration.ofSeconds(5))
                    .until(ExpectedConditions.visibilityOfElementLocated(inputSelector));

            final WebElement input = webDriver.findElement(inputSelector);
            assertTrue(input.isDisplayed());
            assertEquals(input.getDomAttribute("placeholder"), "Email address or mobile phone number");
        });
    }

    @Test
    public void shouldSuccessfullyLogin() {
        withDriver(webDriver -> {
            final By logInButtonSelector = By.xpath("//button[text()=\"Log in\"]");

            new WebDriverWait(webDriver, Duration.ofSeconds(5))
                    .until(ExpectedConditions.elementToBeClickable(logInButtonSelector));
            webDriver.findElement(logInButtonSelector)
                    .click();

            final By inputSelector = By.className("MuiInputBase-input");

            new WebDriverWait(webDriver, Duration.ofSeconds(5))
                    .until(ExpectedConditions.visibilityOfElementLocated(inputSelector));
            webDriver.findElement(inputSelector)
                    .sendKeys(EMAIL);

            final By nextButtonSelector = By.xpath("//button[text()=\"Next\"]");
            final By signInButtonSelector = By.xpath("//button[text()=\"Sign in\"]");

            webDriver.findElement(nextButtonSelector)
                    .click();

            new WebDriverWait(webDriver, Duration.ofSeconds(10))
                    .until(ExpectedConditions.visibilityOfElementLocated(signInButtonSelector));
            webDriver.findElement(inputSelector)
                    .sendKeys(ACCESS_CODE);

            final By accountButtonSelector = By.xpath("//button[@aria-label=\"Go to my account\"]");

            new WebDriverWait(webDriver, Duration.ofSeconds(10))
                    .until(ExpectedConditions.visibilityOfElementLocated(accountButtonSelector));

            final WebElement input = webDriver.findElement(accountButtonSelector);
            assertTrue(input.isDisplayed());
        });
    }

    @Test
    public void shouldLoginWrongEmail() {
        withDriver(webDriver -> {
            final By logInButtonSelector = By.xpath("//button[text()=\"Log in\"]");

            new WebDriverWait(webDriver, Duration.ofSeconds(5))
                    .until(ExpectedConditions.elementToBeClickable(logInButtonSelector));
            webDriver.findElement(logInButtonSelector)
                    .click();

            final By inputSelector = By.className("MuiInputBase-input");

            new WebDriverWait(webDriver, Duration.ofSeconds(5))
                    .until(ExpectedConditions.visibilityOfElementLocated(inputSelector));
            webDriver.findElement(inputSelector)
                    .sendKeys(BAD_EMAIL);

            final By nextButtonSelector = By.xpath("//button[text()=\"Next\"]");
            final By snackBarSelector = By.className("MuiSnackbarContent-message");

            webDriver.findElement(nextButtonSelector)
                    .click();
            new WebDriverWait(webDriver, Duration.ofSeconds(5))
                    .until(ExpectedConditions.visibilityOfElementLocated(snackBarSelector));

            final WebElement errorMessage = webDriver.findElement(snackBarSelector);
            assertEquals(errorMessage.getText(), "Invalid email or phone number");
        });
    }

    @Test
    public void shouldLoginWrongAccessCode() {
        withDriver(webDriver -> {
            final By logInButtonSelector = By.xpath("//button[text()=\"Log in\"]");

            new WebDriverWait(webDriver, Duration.ofSeconds(5))
                    .until(ExpectedConditions.elementToBeClickable(logInButtonSelector));
            webDriver.findElement(logInButtonSelector)
                    .click();

            final By inputSelector = By.className("MuiInputBase-input");

            new WebDriverWait(webDriver, Duration.ofSeconds(5))
                    .until(ExpectedConditions.visibilityOfElementLocated(inputSelector));
            webDriver.findElement(inputSelector)
                    .sendKeys(EMAIL);

            final By nextButtonSelector = By.xpath("//button[text()=\"Next\"]");
            final By signInButtonSelector = By.xpath("//button[text()=\"Sign in\"]");

            webDriver.findElement(nextButtonSelector)
                    .click();

            new WebDriverWait(webDriver, Duration.ofSeconds(10))
                    .until(ExpectedConditions.visibilityOfElementLocated(signInButtonSelector));
            webDriver.findElement(inputSelector)
                    .sendKeys(BAD_ACCESS_CODE);

            final By snackBarSelector = By.className("MuiSnackbarContent-message");
            new WebDriverWait(webDriver, Duration.ofSeconds(5))
                    .until(ExpectedConditions.visibilityOfElementLocated(snackBarSelector));

            final WebElement errorMessage = webDriver.findElement(snackBarSelector);
            assertEquals(errorMessage.getText(), "Sign in failed. Invalid one time password.");
        });
    }

    @Test
    public void shouldResendCodeOnLogin() {
        withDriver(webDriver -> {
            final By logInButtonSelector = By.xpath("//button[text()=\"Log in\"]");

            new WebDriverWait(webDriver, Duration.ofSeconds(5))
                    .until(ExpectedConditions.elementToBeClickable(logInButtonSelector));
            webDriver.findElement(logInButtonSelector)
                    .click();

            final By inputSelector = By.className("MuiInputBase-input");

            new WebDriverWait(webDriver, Duration.ofSeconds(5))
                    .until(ExpectedConditions.visibilityOfElementLocated(inputSelector));
            webDriver.findElement(inputSelector)
                    .sendKeys(EMAIL);

            final By nextButtonSelector = By.xpath("//button[text()=\"Next\"]");
            final By signInButtonSelector = By.xpath("//button[text()=\"Sign in\"]");

            webDriver.findElement(nextButtonSelector)
                    .click();

            new WebDriverWait(webDriver, Duration.ofSeconds(10))
                    .until(ExpectedConditions.visibilityOfElementLocated(signInButtonSelector));
            webDriver.findElement(inputSelector)
                    .sendKeys(BAD_ACCESS_CODE);

            final By snackBarSelector = By.className("MuiSnackbarContent-message");
            new WebDriverWait(webDriver, Duration.ofSeconds(5))
                    .until(ExpectedConditions.visibilityOfElementLocated(snackBarSelector));

            assertEquals(webDriver.findElement(snackBarSelector).getText(),
                    "Sign in failed. Invalid one time password.");

            final By closeButtonSelector = By.xpath("//button[@aria-label=\"close\"]");

            new WebDriverWait(webDriver, Duration.ofSeconds(2))
                    .until(ExpectedConditions.visibilityOfElementLocated(closeButtonSelector));
            webDriver.findElement(closeButtonSelector)
                    .click();

            new WebDriverWait(webDriver, Duration.ofSeconds(10))
                    .until(ExpectedConditions.visibilityOfElementLocated(signInButtonSelector));
            webDriver.findElement(inputSelector)
                    .sendKeys(ACCESS_CODE);

            new WebDriverWait(webDriver, Duration.ofSeconds(5))
                    .until(ExpectedConditions.visibilityOfElementLocated(snackBarSelector));
            assertEquals(webDriver.findElement(snackBarSelector).getText(),
                    "The one time code is no longer valid. Please click the 'RESEND CODE' and try again.");
        });
    }

}
