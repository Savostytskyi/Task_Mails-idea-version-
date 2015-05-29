package mail.pages.gmail;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class DraftGmailPage {
    private WebDriver driver;

    @FindBy(xpath = "//a[@id='gb_71']")
    private WebElement toMainPageButton;

    @FindBy(xpath = "//a[@aria-haspopup='true']/span")
    private WebElement dropDownItem;

    public DraftGmailPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public NewLetterGmailPage openDraftLetter(String topic) {
        driver.findElement(By.xpath("//span[text()='"+topic+"']")).click();
        return new NewLetterGmailPage(driver);
    }

    public MailGmailMainPage goToMainPage() {
        dropDownItem.click();
        toMainPageButton.click();
        return new MailGmailMainPage(driver);
    }





}
