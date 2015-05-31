package driver;


import java.net.MalformedURLException;

import org.openqa.selenium.WebDriver;

import driver.types.ChromeDriverBuilder;
import driver.types.FireFoxDriverBuilder;
import driver.types.IEDriverBuilder;

public class WebDriverFactory {

    public WebDriver driver;

    public static final String typeDriver="firefox";

    private enum TypeDriver {
        FIREFOX, CHROME, IE
    }
    public WebDriver createTariffBuilder() throws MalformedURLException {
        TypeDriver type = TypeDriver.valueOf(typeDriver.toUpperCase());
        switch (type) {
            case FIREFOX:
                return new FireFoxDriverBuilder().initializeWebDriver();
            case CHROME:
                return new ChromeDriverBuilder().initializeWebDriver();
            case IE:
                return new IEDriverBuilder().initializeWebDriver();
            default:
                throw new EnumConstantNotPresentException(
                        type.getDeclaringClass(), type.name());
        }
    }

}