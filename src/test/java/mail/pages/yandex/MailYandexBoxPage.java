package mail.pages.yandex;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class MailYandexBoxPage {
    private WebDriver driver;
    @FindBy(xpath = ".//*[@id='js-page']//img[@class='b-ico b-ico_compose']")
    private WebElement newLetterButton;

    public MailYandexBoxPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public NewLetterYandexPage chouseNewLetter() {
        newLetterButton.click();
        return new NewLetterYandexPage(driver);
    }



}
