package driver;


import java.net.MalformedURLException;

import org.openqa.selenium.WebDriver;

import driver.types.ChromeDriverBuilder;
import driver.types.FireFoxDriverBuilder;
import driver.types.IEDriverBuilder;
import driver.types.OperaDriverBuilder;
import driver.types.RemoteCloudDriverBuilder;

public class WebDriverFactory {

    public WebDriver driver;


    private enum TypeDriver {
        FIREFOX, OPERA, CHROME, IE, CLOUD
    }
    public WebDriver createTariffBuilder(String typeDriver) throws MalformedURLException {
        TypeDriver type = TypeDriver.valueOf(typeDriver.toUpperCase());
        switch (type) {
            case FIREFOX:
                return new FireFoxDriverBuilder().initializeWebDriver();
            case OPERA:
                return new OperaDriverBuilder().initializeWebDriver();
            case CHROME:
                return new ChromeDriverBuilder().initializeWebDriver();
            case IE:
                return new IEDriverBuilder().initializeWebDriver();
            case CLOUD:
                return new RemoteCloudDriverBuilder().initializeWebDriver();
            default:
                throw new EnumConstantNotPresentException(
                        type.getDeclaringClass(), type.name());
        }
    }

}