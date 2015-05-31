package mail.pages.yandex;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class DraftYandexPage {
    private WebDriver driver;
    @FindBy(xpath = "//a[@id='nb-1']/span[2]")
    private WebElement popMenuItem;

    @FindBy(xpath = "//div[@id='user-dropdown-popup']//a[@data-metric='Меню сервисов:Выход']")
    private WebElement toMainPageButton;

    public DraftYandexPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public NewLetterYandexPage openDraftLetter(String topic) {
        driver.findElement(By.xpath("//span[@title='"+topic+"']")).click();
        return new NewLetterYandexPage(driver);
    }

    public MailYandexMainPage goToMainPage() {
        popMenuItem.click();
        toMainPageButton.click();
        return new MailYandexMainPage(driver);
    }





}
