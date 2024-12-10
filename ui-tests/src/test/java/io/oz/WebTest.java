package io.oz;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.BeforeAll;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.function.Consumer;

public class WebTest {
    static final String PAGE_URL = "https://webapp.v1.dev.chargelab.io/Take-Home-Challenge-1";

    @BeforeAll
    public static void setUp() {
        WebDriverManager.chromedriver().setup();
    }

    void withDriver(Consumer<WebDriver> test) {
        WebDriver driver = new ChromeDriver();
        driver.get(PAGE_URL);
        driver.manage().window().maximize();

        test.accept(driver);
    }
}
