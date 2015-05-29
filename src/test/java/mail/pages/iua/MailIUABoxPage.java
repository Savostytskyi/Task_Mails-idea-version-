package mail.pages.iua;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class MailIUABoxPage {
    private WebDriver driver;

    @FindBy(xpath = "//p[@class='make_message']//a")
    private WebElement newLetterButton;

    public MailIUABoxPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public NewLetterIUAPage chouseNewLetter() {
        newLetterButton.click();
        return new NewLetterIUAPage(driver);
    }



}
