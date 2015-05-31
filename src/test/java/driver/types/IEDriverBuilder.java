package driver.types;

import java.io.File;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;

public class IEDriverBuilder {

    public WebDriver initializeWebDriver() {
        File file = new File("libdrivers/IEDriverServer32.exe");
        System.setProperty("webdriver.ie.driver", file.getAbsolutePath());
        WebDriver driver = new InternetExplorerDriver();
        return driver;
    }
}
