package mail.pages.yandex;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class SentLettersYandexPage {
    private WebDriver driver;
    @FindBy(xpath = "//a[@title='Черновики']")
    private WebElement toDraftItem;

    public SentLettersYandexPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void goToGrft() {
        toDraftItem.click();
    }
}
