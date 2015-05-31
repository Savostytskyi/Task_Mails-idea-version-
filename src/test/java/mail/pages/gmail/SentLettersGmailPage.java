package mail.pages.gmail;


import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class SentLettersGmailPage {
    private WebDriver driver;

    @FindBy(xpath = "(//div[@role='navigation']//a)[4]")
    private WebElement toDraftItem;

    public SentLettersGmailPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void goToGrft() {
        toDraftItem.click();

    }
}

