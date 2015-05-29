package driver.types;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class FireFoxDriverBuilder {

    public WebDriver initializeWebDriver() {
        WebDriver driver = new FirefoxDriver();
        return driver;
    }
}
