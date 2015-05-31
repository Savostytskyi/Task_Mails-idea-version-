package mail;


import core.PropertyReader;
import mail.pages.iua.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import driver.WebDriverFactory;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

public class MailIUATests extends WebDriverFactory {


    private MailIUABoxPage box;
    private MailIUAMainPage main;
    private NewLetterIUAPage letter;
    private DraftIUAPage draft;
    private SentLetterIUAPage sent;
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
            reader.setPropValues("iua");
            letterTopic = reader.getSubject();
            letterText = reader.getTexts();
            letterAdress = reader.getRecipient();
            driver = webDriverFactory.createTariffBuilder();
            driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
            driver.get(reader.getUrl());

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @Test(description = "Login to mail")
    public void loginToMail() {
        main = new MailIUAMainPage(driver);
        box = main.loginInMail(reader.getLogin(), reader.getPass());
        Assert.assertTrue(isElementPresent(By.xpath("//p[@class='make_message']//a")));
    }

    @Test(description = "Begin new letter creation", dependsOnMethods = { "loginToMail" })
    public void beginCreationOfLetter() {
        letter = box.chouseNewLetter();
        draft = letter.createNewLetter(letterAdress, letterTopic, letterText);
        Assert.assertEquals(driver.findElement(By.xpath("//div[@id='mesgList']//span[@class='sbj']/span")).getText(), letterTopic);
    }

    @Test(description = "Checking the contains of letter", dependsOnMethods = { "beginCreationOfLetter" })
    public void checkTheLetter() {
        letter = draft.openDraftLetter(letterTopic);
        Assert.assertEquals(driver.findElement(By.xpath("//textarea[@id='text']")).getText(), letterText);
        Assert.assertEquals(driver.findElement(By.xpath("//textarea[@id='to']")).getText(), letterAdress);
    }

    @Test(description = "Send the letter", dependsOnMethods = { "checkTheLetter" })
    public void sendTheLetter() {
        sent = letter.sendDraftLetter();
        Assert.assertTrue(isElementPresent(By.xpath("//span[text()='"+letterTopic+"']")));
        sent.goToGrft();
        Assert.assertFalse(isElementPresent(By.xpath("//span[text()='"+letterTopic+"']")));

    }

    @AfterClass
    public void afterClass() {
        main = draft.goToMainPage();
        main.logOut();
        driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
        driver.quit();
    }

    private boolean isElementPresent(By by) {
        return !driver.findElements(by).isEmpty();
    }

}
