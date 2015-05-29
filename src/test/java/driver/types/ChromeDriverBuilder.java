package driver.types;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;

public class ChromeDriverBuilder {

    public WebDriver initializeWebDriver() {
	/*	ChromeDriverService service = new ChromeDriverService.Builder()
				.usingDriverExecutable(new File("libdrivers/chromedriver.exe"))
				.usingAnyFreePort().build();
		try {
			service.start();
		} catch (IOException e) {
			e.printStackTrace();
		}
		WebDriver driver = new ChromeDriver(service);*/


        final DesiredCapabilities dc = DesiredCapabilities.chrome();

        dc.setCapability(ChromeOptions.CAPABILITY, new ChromeOptions() {
            {
                setExperimentalOption("mobileEmulation", new HashMap<String, Object>() {
                    {
                        put("deviceName", "Google Nexus 5");
                    }
                });
            }
        });
        System.setProperty("webdriver.chrome.driver", "libdrivers/chromedriver.exe");
        WebDriver driver = new ChromeDriver(dc);

        return driver;
    }

}

