package mail.pages.gmail;


import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class SentLettersGmailPage {
    private WebDriver driver;

    @FindBy(xpath = "(//a[@aria-label])[3]")
    private WebElement toDraftItem;

    public SentLettersGmailPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void goToGrft() {
        toDraftItem.click();
        (new WebDriverWait(driver, 20))
                .until(ExpectedConditions.titleContains("Черновики"));
    }
}

