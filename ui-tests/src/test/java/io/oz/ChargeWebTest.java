package io.oz;

import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class ChargeWebTest extends WebTest {
    private static final String EMAIL = "test-user-01@chargelab.co";
    private static final String ACCESS_CODE = "00000";

    @Test
    public void shouldLoginSuccessfullyStartAndStopCharging() {
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

            final By selectPortButtonSelector = By.xpath("//button[text()=\"Select port\"]");
            final By portButtonSelector = By.xpath("//*[contains(text(), 'Port 1 (Left)')]");

            new WebDriverWait(webDriver, Duration.ofSeconds(10))
                    .until(ExpectedConditions.visibilityOfElementLocated(selectPortButtonSelector));
            webDriver.findElement(portButtonSelector)
                    .click();

            final By startSessionButtonSelector = By.xpath("//button[text()=\"Start session\"]");

            new WebDriverWait(webDriver, Duration.ofSeconds(10))
                    .until(ExpectedConditions.elementToBeClickable(startSessionButtonSelector));
            webDriver.findElement(startSessionButtonSelector)
                    .click();

            final By stopButtonSelector = By.xpath("//button[text()=\"Stop\"]");

            new WebDriverWait(webDriver, Duration.ofSeconds(20))
                    .until(ExpectedConditions.visibilityOfElementLocated(stopButtonSelector));
            webDriver.findElement(stopButtonSelector)
                    .click();

            final By viewFullReceiptButtonSelector = By.xpath("//button[text()=\"View full receipt\"]");
            final By sessionCompleteHeaderSelector = By.xpath("//div[text()=\"Session complete\"]");

            new WebDriverWait(webDriver, Duration.ofSeconds(20))
                    .until(ExpectedConditions.elementToBeClickable(viewFullReceiptButtonSelector));

            assertTrue(webDriver.findElement(sessionCompleteHeaderSelector)
                    .isDisplayed());
            assertTrue(webDriver.findElement(viewFullReceiptButtonSelector)
                    .isDisplayed());

        });
    }

}
