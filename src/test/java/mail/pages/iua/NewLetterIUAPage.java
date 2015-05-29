package mail.pages.iua;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class NewLetterIUAPage {
    private WebDriver driver;

    @FindBy(xpath = "//textarea[@id='to']")
    private WebElement toTextArea;

    @FindBy(xpath = "//input[@name='subject']")
    private WebElement subjectField;

    @FindBy(xpath = "//textarea[@id='text']")
    private WebElement letterTextArea;

    @FindBy(xpath = "//p[@class='send_container']//input[@name='save_in_drafts']")
    private WebElement saveInDraftButton;

    @FindBy(xpath = "//ul[@class='list_underlined']//li[3]//a")
    private WebElement toDraftItem;

    public NewLetterIUAPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public DraftIUAPage createNewLetter(String adress, String title,
                                        String text) {
        toTextArea.sendKeys(adress);
        subjectField.sendKeys(title);
        letterTextArea.sendKeys(text);
        saveInDraftButton.click();
        toDraftItem.click();
        return new DraftIUAPage(driver);
    }

    public SentLetterIUAPage sendDraftLetter() {
        driver.findElement(By.xpath("//p[@class='send_container clear']/input[@name='send']")).click();
        driver.findElement(By.xpath("//ul[@class='list_underlined']//li[2]//a")).click();
        return new SentLetterIUAPage(driver);
    }

}

