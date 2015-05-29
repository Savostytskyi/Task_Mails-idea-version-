package driver.types;

import java.net.MalformedURLException;
import java.net.URL;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

public class RemoteCloudDriverBuilder {

    public WebDriver initializeWebDriver() throws MalformedURLException {
        WebDriver driver = new RemoteWebDriver(new URL("http://Savostytskyi:10a26e45-99a7-4f43-a4db-fdc17cf19190@ondemand.saucelabs.com:80/wd/hub"),
                DesiredCapabilities.internetExplorer());
        return driver;
    }

}

