package definitions;

import controllers.WebDriverController;
import org.junit.jupiter.api.Assertions;
import pages.hoeffner.LoginPage;
import pages.tempmail.TempMailHomePage;

public class NewsletterDefinition {

    private String hoffnerLoginURL;
    private final WebDriverController webDriverController;
    private LoginPage loginPage;
    private TempMailHomePage tempMailPage;

    public NewsletterDefinition() {
        webDriverController = new WebDriverController(WebDriverController.Browser.Chrome);
    }

    public void setHoffnerLoginPage() {
        hoffnerLoginURL="https://www.hoeffner.de/login";
    }

    public void openHoffnerLoginPage() {
        webDriverController.NavigateToPage(hoffnerLoginURL);
    }

    public void verifyNewsletterSubsInputVisible() {
        loginPage = new LoginPage(webDriverController.getDriver());
        loginPage.acceptAllCookies();
        Assertions.assertTrue(loginPage.isEmailInputVisible(),"the email input was not visible");
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
    }

    public String getATempEmailWithInbox() {
        webDriverController.NavigateToPage("https://temp-mail.org/");
        tempMailPage = new TempMailHomePage(webDriverController.getDriver());
        return tempMailPage.getAnEmail();
    }

    public void openANewTab() {
        webDriverController.openANewTabAndSwitch();
    }

    public void verifyConfirmationEmail() {
        webDriverController.switchToTab(0);
        Assertions.assertTrue(tempMailPage.waitANewMail(),"After waiting a minute, no mail was received");

        tempMailPage.openLastReceivedMail();
        String mailSubject=tempMailPage.getMailSubject();
        Assertions.assertTrue(mailSubject.contains("Anmeldung"), "The received mail is not to confirm the subscription");
    }

    public void confirmRegistrarion() {
        tempMailPage.confirmMailRegistration();
    }

    public void verifyRegistrationCompleted() {
        webDriverController.switchToLastTab();
        Assertions.assertTrue(webDriverController.getCurrentURL().contains("https://www.hoeffner.de/nl-anmeldung"),
                "The completed registration page was not opened");
    }
}
