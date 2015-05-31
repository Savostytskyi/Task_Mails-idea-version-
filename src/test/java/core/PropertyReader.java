package core;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * Created by Savostytskyi Anton on 31.05.2015.
 */
public class PropertyReader {

    private String recipient;
    private String subject;
    private String texts;
    private String url;
    private String login;
    private String pass;


    public void setPropValues(String mailBox) throws IOException {

        Properties prop = new Properties();
        String propFileName = "mails.properties";
        InputStream inputStream = getClass().getClassLoader().getResourceAsStream(propFileName);

        if (inputStream != null) {
            prop.load(inputStream);
        } else {
            throw new FileNotFoundException("property file '" + propFileName + "' not found in the classpath");
        }
        this.recipient = prop.getProperty("ml.recipient");
        this.subject = prop.getProperty("ml.subject");
        this.texts = prop.getProperty("ml.texts");
        this.url = prop.getProperty(mailBox+".url");
        this.login = prop.getProperty(mailBox+".login");
        this.pass = prop.getProperty(mailBox+".pass");
    }


    public String getRecipient() {
        return recipient;
    }

    public String getTexts() {
        return texts;
    }

    public String getSubject() {
        return subject;
    }

    public String getUrl() {
        return url;
    }

    public String getLogin() {
        return login;
    }

    public String getPass() {
        return pass;
    }
}

