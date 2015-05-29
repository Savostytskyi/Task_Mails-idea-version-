package driver.types;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.opera.OperaDriver;

public class OperaDriverBuilder {

    public WebDriver initializeWebDriver() {
        System.setProperty("webdriver.opera.driver", "libdrivers/operadriver64.exe");
        WebDriver driver = new OperaDriver();
        return driver;
    }

}
