package mail;


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
    private String letterTopic = "It is test letter";
    private String letterText = "Some text for test";
    private String letterAdress = "savostytskyi.anton@gmail.com";
    private WebDriverFactory webDriverFactory = new WebDriverFactory();
    private WebDriver driver;


    @BeforeClass
    public void beforeClass() {
        try {
            driver = webDriverFactory.createTariffBuilder("firefox");
            driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
            driver.get("http://www.i.ua/");

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @Test(description = "Login to mail")
    public void loginToMail() {
        main = new MailIUAMainPage(driver);
        box = main.loginInMail("test_mail_box@i.ua", "testmail");
        Assert.assertTrue(isElementPresent(By.xpath("//p[@class='make_message']//a")));
    }

    @Test(description = "Begin new letter creation", dependsOnMethods = { "loginToMail" })
    public void beginCreationOfLetter() {
        letter = box.chouseNewLetter();
        draft = letter.createNewLetter(letterAdress, letterTopic, letterText);
        Assert.assertEquals(driver.findElement(By.xpath("//*[@id='mesgList']//span[@class='sbj']/span")).getText(), letterTopic);
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
