package mail.pages.yandex;


import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;


public class NewLetterYandexPage {
    private WebDriver driver;
    @FindBy(xpath = "//div[@class='b-yabble b-yabble_focused']/descendant-or-self::input")
    private WebElement toTextArea;

    @FindBy(xpath = "//input[@id='compose-subj']")
    private WebElement subjectField;

    @FindBy(xpath = "//textarea[@id='compose-send']")
    private WebElement letterTextArea;

    @FindBy(xpath = "//button[@id='nb-22']/span/span")
    private WebElement prevButton;

    @FindBy(xpath = "//button[@data-nb='button']//span[text()='Сохранить и перейти']")
    private WebElement saveInDraftButton;

    @FindBy(xpath = ".//a[@href='#draft']")
    private WebElement toDraftItem;

    @FindBy(xpath = "//button[@id='compose-submit']")
    private WebElement submitButton;

    @FindBy(xpath = ".//a[@href='#sent']")
    private WebElement sentItem;

    public NewLetterYandexPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public DraftYandexPage createNewLetter(String adress, String title,
                                           String text) {
        toTextArea.sendKeys(adress);
        subjectField.sendKeys(title);
        letterTextArea.sendKeys(text);
        prevButton.click();
        saveInDraftButton.click();
        toDraftItem.click();
        return new DraftYandexPage(driver);
    }

    public SentLettersYandexPage sendDraftLetter() {
        submitButton.click();
        sentItem.click();
        return new SentLettersYandexPage(driver);
    }
}
