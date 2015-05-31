package mail.pages.gmail;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class NewLetterGmailPage {
    private WebDriver driver;

    @FindBy(xpath = "//textarea[@aria-label='Кому']")
    private WebElement toTextArea;

    @FindBy(xpath = "//input[@name='subjectbox']")
    private WebElement subjectField;

    @FindBy(xpath = "//div[@aria-label='Тело письма']")
    private WebElement letterTextArea;

    @FindBy(xpath = "//img[@aria-label='Сохранить и закрыть']")
    private WebElement saveInDraftButton;

    @FindBy(xpath = "//div[text()='Отправить']")
    private WebElement sendButton;

    @FindBy(xpath = "//a[text()='Отправленные']")
    private WebElement sentLettersItem;

    @FindBy(xpath = "(//div[@role='navigation']//a)[4]")
    private WebElement toDraftItem;

    public NewLetterGmailPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public DraftGmailPage createNewLetter(String adress, String title,
                                          String text) {
        toTextArea.sendKeys(adress);
        subjectField.sendKeys(title);
        letterTextArea.sendKeys(text);
        saveInDraftButton.click();
        toDraftItem.click();
        return new DraftGmailPage(driver);
    }

    public SentLettersGmailPage sendDraftLetter() {
        sendButton.click();
        sentLettersItem.click();
        return new SentLettersGmailPage(driver);
    }

}

