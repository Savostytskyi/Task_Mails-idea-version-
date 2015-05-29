package mail;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

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
    private String letterTopic = "It is test letter";
    private String letterText = "Some text for test";
    private String letterAdress = "savostytskyi.anton@gmail.com";
    private WebDriverFactory webDriverFactory = new WebDriverFactory();
    private WebDriver driver;


    @BeforeClass
    public void beforeClass(){
        try {
            driver = webDriverFactory.createTariffBuilder("chrome");
            driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
            driver.get("https://accounts.google.com/ServiceLogin");
        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    @Test(description = "Login to mail")
    public void loginToMail() {
        main = new MailGmailMainPage(driver);
        box = main.loginInMail("petrov.vas123321123@gmail.com", "123an123");
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
        Assert.assertEquals(driver.findElement(By.xpath("//span[@class='vN Y7BVp a3q']")).getAttribute("email"), letterAdress);
    }

    @Test(description = "Send the letter", dependsOnMethods = { "checkTheLetter" })
    public void sendTheLetter() {
        sent = letter.sendDraftLetter();
        Assert.assertTrue(isElementPresent(By.xpath("//span[text()='"+letterTopic+"']")));
        sent.goToGrft();
        driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
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
