package mail;

import core.PropertyReader;
import mail.pages.yandex.*;
import mail.pages.yandex.MailYandexMainPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import driver.WebDriverFactory;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

public class MailYandexTests extends WebDriverFactory {

    private MailYandexBoxPage box;
    private MailYandexMainPage main;
    private NewLetterYandexPage letter;
    private DraftYandexPage draft;
    private SentLettersYandexPage sent;
    private String letterTopic;
    private String letterText;
    private String letterAdress;
    private WebDriverFactory webDriverFactory = new WebDriverFactory();
    private WebDriver driver;
    private PropertyReader reader;

    @BeforeClass
    public void beforeClass() {
        try {
            reader = new PropertyReader();
            reader.setPropValues("yandex");
            letterTopic = reader.getSubject();
            letterText = reader.getTexts();
            letterAdress = reader.getRecipient();
            driver = webDriverFactory.createTariffBuilder();
            driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
            driver.get(reader.getUrl());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test(description = "Login to mail")
    public void loginToMail() {
        main = new MailYandexMainPage(driver);
        box = main.loginInMail(reader.getLogin(), reader.getPass());
        Assert.assertTrue(isElementPresent(By.xpath("//div[@id='js-page']//img[@class='b-ico b-ico_compose']")));
    }

    @Test(description = "Begin new letter creation", dependsOnMethods = { "loginToMail" })
    public void beginCreationOfLetter() {
        letter = box.chouseNewLetter();
        draft = letter.createNewLetter(letterAdress, letterTopic, letterText);
        Assert.assertEquals(driver.findElement(By.xpath("//span[@title='"+letterTopic+"']")).getText(), letterTopic);
       // sent.goToDraft();
    }

    @Test(description = "Checking the contains of letter", dependsOnMethods = { "beginCreationOfLetter" })
    public void checkTheLetter() {
        letter = draft.openDraftLetter(letterTopic);
        Assert.assertEquals(driver.findElement(By.xpath("//textarea[@id='compose-send']")).getAttribute("value"), letterText);
        Assert.assertEquals(driver.findElement(By.xpath("//div[@id='js-page']/div//span[@class='b-yabble__text__content']")).getText(), letterAdress);
    }

    @Test(description = "Send the letter", dependsOnMethods = { "checkTheLetter" })
    public void sendTheLetter() {
        sent = letter.sendDraftLetter();
        Assert.assertTrue(isElementPresent(By.xpath("//span[@title='"+letterTopic+"']")));
        sent.goToDraft();
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        Assert.assertTrue(isElementPresent(By.xpath("//div[@class='block-messages-wrap']//span[@title='"+letterTopic+"']")));
    }

    @AfterClass
    public void afterClass() {
        draft.goToMainPage();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.quit();
    }

    private boolean isElementPresent(By by) {
        return !driver.findElements(by).isEmpty();
    }

}