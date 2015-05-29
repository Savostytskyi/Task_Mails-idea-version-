package mail.pages.gmail;


import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class MailGmailBoxPage {
    private WebDriver driver;

    @FindBy(xpath = "//div[text()='НАПИСАТЬ']")
    private WebElement newLetterButton;

    public MailGmailBoxPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public NewLetterGmailPage chouseNewLetter() {
        newLetterButton.click();
        return new NewLetterGmailPage(driver);
    }



}
