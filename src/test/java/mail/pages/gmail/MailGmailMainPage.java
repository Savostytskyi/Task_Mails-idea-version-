package mail.pages.gmail;



import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import static com.gargoylesoftware.htmlunit.WebAssert.assertElementPresent;

public class MailGmailMainPage {
    private WebDriver driver;
    @FindBy(xpath = "//input[@id='Email']")
    private WebElement loginField;

    @FindBy(xpath = "//input[@id='Passwd']")
    private WebElement passwordField;

    @FindBy(xpath = "//input[@id='signIn']")
    private WebElement loginButton;

    @FindBy(xpath = "//a[@title='выйти']")
    private WebElement logoutButton;

    @FindBy(xpath = "//input[@id='next']")
    private WebElement nextButton;

    @FindBy(xpath = "//a[@title='Сервисы']")
    private WebElement servicesButton;

    @FindBy(xpath = "//span[text()='Почта']")
    private WebElement mailButton;

    public MailGmailMainPage(WebDriver driver) {
        this.driver=driver;
        PageFactory.initElements(driver, this);
    }

    public MailGmailBoxPage loginInMail(String login, String password) {
        loginField.sendKeys(login);
        if (isElementPresent(By.xpath("//input[@id='Passwd']"))) {
            passwordField.sendKeys(password);
            loginButton.click();
        } else {
            nextButton.click();
            passwordField.sendKeys(password);
            loginButton.click();
        }
        servicesButton.click();
        mailButton.click();

        (new WebDriverWait(driver, 20))
                .until(ExpectedConditions.titleContains("Входящие"));
        return new MailGmailBoxPage(driver);
    }

    public void logOut() {
        logoutButton.click();

    }

    private boolean isElementPresent(By by) {
        return !driver.findElements(by).isEmpty();
    }

}
