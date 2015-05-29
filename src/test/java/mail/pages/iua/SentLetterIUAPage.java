package mail.pages.iua;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class SentLetterIUAPage {
    private WebDriver driver;

    @FindBy(xpath = "//ul[@class='list_underlined']//li[3]//a")
    private WebElement toDraftItem;

    public SentLetterIUAPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void goToGrft() {
        toDraftItem.click();
    }
}
