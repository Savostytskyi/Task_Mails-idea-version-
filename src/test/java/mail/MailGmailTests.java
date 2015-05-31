package mail;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import core.PropertyReader;
import mail.pages.gmail.DraftGmailPage;
import mail.pages.gmail.MailGmailBoxPage;
import mail.pages.gmail.MailGmailMainPage;
import mail.pages.gmail.NewLetterGmailPage;
import mail.pages.gmail.SentLettersGmailPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import driver.WebDriverFactory;

public class MailGmailTests {

    private MailGmailBoxPage box;
    private MailGmailMainPage main;
    private NewLetterGmailPage letter;
    private DraftGmailPage draft;
    private SentLettersGmailPage sent;
    private String letterTopic;
    private String letterText;
    private String letterAdress;
    private WebDriverFactory webDriverFactory = new WebDriverFactory();
    private WebDriver driver;
    private PropertyReader reader;


    @BeforeClass
    public void beforeClass(){
        try {
            reader = new PropertyReader();
            reader.setPropValues("gmail");
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
        main = new MailGmailMainPage(driver);
        box = main.loginInMail(reader.getLogin(), reader.getPass());
        Assert.assertTrue(isElementPresent(By.xpath("//div[text()='НАПИСАТЬ']")));
    }

    @Test(description = "Begin new letter creation", dependsOnMethods = { "loginToMail" })
    public void beginCreationOfLetter() {
        letter = box.chouseNewLetter();
        draft = letter.createNewLetter(letterAdress, letterTopic, letterText);
        Assert.assertEquals(driver.findElement(By.xpath("//span[text()='"+letterTopic+"']")).getText(), letterTopic);
    }

    @Test(description = "Checking the contains of letter", dependsOnMethods = { "beginCreationOfLetter" })
    public void checkTheLetter() {
        letter = draft.openDraftLetter(letterTopic);
        Assert.assertEquals(driver.findElement(By.xpath("//div[@aria-label='Тело письма']")).getText(), letterText);
        Assert.assertEquals(driver.findElement(By.xpath("(//form[@method='POST']//span)[2]")).getAttribute("email"), letterAdress);
    }

    @Test(description = "Send the letter", dependsOnMethods = { "checkTheLetter" })
    public void sendTheLetter() {
        sent = letter.sendDraftLetter();
        Assert.assertTrue(isElementPresent(By.xpath("//span[text()='"+letterTopic+"']")));
        sent.goToGrft();
        Assert.assertTrue(isElementPresent(By.xpath("//span[text()='"+letterTopic+"']")));

    }

    @AfterClass
    public void afterClass() {
        draft.goToMainPage();
        driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
        driver.quit();
    }

    private boolean isElementPresent(By by) {
        return !driver.findElements(by).isEmpty();
    }
}
