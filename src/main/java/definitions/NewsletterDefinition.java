package definitions;

import controllers.WebDriverController;
import org.junit.jupiter.api.Assertions;

import pages.hoeffner.LoginPage;
import pages.tempmail.TempMailHomePage;
import utils.CustomUtils;
import utils.Log;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class NewsletterDefinition {

    private String hoffnerLoginURL;
    private final WebDriverController webDriverController;
    private LoginPage loginPage;
    private TempMailHomePage tempMailPage;
    private Properties prop;

    public NewsletterDefinition() {
        webDriverController = new WebDriverController(WebDriverController.Browser.Chrome);
        prop= CustomUtils.loadPropertiesFile("src/main/resources/properties/config.properties");
    }

    public void setHoffnerLoginPage() {
        hoffnerLoginURL=prop.getProperty("HoffnerLoginURL");
        Log.LOGGER.info(String.format("Hoffner login url used: '%s'", hoffnerLoginURL));
    }

    public void openHoffnerLoginPage() {
        webDriverController.NavigateToPage(hoffnerLoginURL);
    }

    public void verifyNewsletterSubsInputVisible() {
        loginPage = new LoginPage(webDriverController.getDriver());
        loginPage.acceptAllCookies();
        Assertions.assertTrue(loginPage.isEmailInputVisible(),"the email input was not visible");
        Log.LOGGER.info("the email input is visible");
    }

    public void enterEmail(String userEmail) {
        loginPage = new LoginPage(webDriverController.getDriver());
        loginPage.acceptAllCookies();
        loginPage.writeEmail(userEmail);
    }

    public void pressAbsendenBtn() {
        loginPage.submitMail();

    }

    public void VerifyConfirmationMsgVisible() {
        Assertions.assertTrue(loginPage.isSubscriptionInProgressMsjVisible());
        Log.LOGGER.info("the subscription in progress message is visible");
    }

    public String getATempEmailWithInbox() {
        webDriverController.NavigateToPage(prop.getProperty("TempMailURL"));
        tempMailPage = new TempMailHomePage(webDriverController.getDriver());
        return tempMailPage.getAnEmail();
    }

    public void openANewTab() {
        webDriverController.openANewTabAndSwitch();
    }

    public void verifyConfirmationEmail() {
        webDriverController.switchToTab(0);
        Assertions.assertTrue(tempMailPage.waitANewMail(),"After waiting more than a minute, no mail was received");
        Log.LOGGER.info("New mail received");

        tempMailPage.openLastReceivedMail();
        String mailSubject=tempMailPage.getMailSubject();

        Log.LOGGER.info(String.format("mail subject: '%s'", mailSubject));
        Assertions.assertTrue(mailSubject.contains("Anmeldung"), "The received mail is not to confirm the subscription");
        Log.LOGGER.info("The mail to complete the subscription was received");
    }

    public void confirmRegistrarion() {
        tempMailPage.confirmMailRegistration();
    }

    public void verifySubscriptionCompleted() {
        webDriverController.switchToLastTab();
        String expectedURl=prop.getProperty("MailConfirmedURL");
        Assertions.assertTrue(webDriverController.getCurrentURL().contains(expectedURl),
                "The completed subscription page was not opened");
        Log.LOGGER.info("The subscription was completed");
    }
}
