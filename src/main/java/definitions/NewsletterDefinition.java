package definitions;

import controllers.WebDriverController;
import org.testng.Assert;
import pages.hoeffner.LoginPage;
import pages.tempmail.TempMailHomePage;
import utils.CustomUtils;
import utils.Log;
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
        Assert.assertTrue(loginPage.isEmailInputVisible(),"the email input was not visible");
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
        Assert.assertTrue(loginPage.isSubscriptionInProgressMsjVisible());
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
        Assert.assertTrue(tempMailPage.waitANewMail(),"After waiting more than a minute, no mail was received");
        Log.LOGGER.info("New mail received");

        tempMailPage.openLastReceivedMail();
        String mailSubject=tempMailPage.getMailSubject();

        Log.LOGGER.info(String.format("mail subject: '%s'", mailSubject));
        Assert.assertTrue(mailSubject.contains("Anmeldung"), "The received mail is not to the expected");
        Log.LOGGER.info("The mail to complete the subscription was received");
    }

    public void verifyConfirmationLink() {
        Assert.assertTrue(tempMailPage.isCompleteRegistrationLinkInMail(),"The received mail doesnt contains the confirmation link");
        Log.LOGGER.info("The mail contains the confirmation link");
    }

    public void confirmRegistration() {
        tempMailPage = new TempMailHomePage(webDriverController.getDriver());
        tempMailPage.confirmMailRegistration();
    }

    public void verifySubscriptionCompleted() {
        String expectedURl=prop.getProperty("MailConfirmedURL");

        webDriverController.switchToLastTab();
        Assert.assertTrue(webDriverController.getCurrentURL().contains(expectedURl),
                "The completed subscription page was not opened");
        Log.LOGGER.info("The subscription was completed");
    }


}
