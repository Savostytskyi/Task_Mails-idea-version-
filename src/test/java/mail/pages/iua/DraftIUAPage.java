package mail.pages.iua;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class DraftIUAPage {
    private WebDriver driver;

    @FindBy(xpath = "//ul[@class='ho_menu ho_menu-main']//li[@class='ho_menu_item']/a")
    private WebElement toMainPageButton;

    public DraftIUAPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public NewLetterIUAPage openDraftLetter(String topic) {
        driver.findElement(By.xpath("//span[text()='"+topic+"']")).click();
        return new NewLetterIUAPage(driver);
    }

    public MailIUAMainPage goToMainPage() {
        toMainPageButton.click();
        return new MailIUAMainPage(driver);
    }





}
