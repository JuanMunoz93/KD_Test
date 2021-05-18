package definitions;

import controllers.WebDriverController;
import org.junit.jupiter.api.Assertions;
import pages.hoeffner.LoginPage;
import pages.tempmail.TempMailHomePage;

public class NewsletterDefinition {

    private String hoffnerLoginURL;
    private final WebDriverController webDriverController;
    private LoginPage loginPage;

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

    public void getATempMailWithInbox() {
        webDriverController.NavigateToPage("https://temp-mail.org/");
        TempMailHomePage tempMailHomePage = new TempMailHomePage(webDriverController.getDriver());
        String email= tempMailHomePage.getAnEmail();
        System.out.println(email);
        tempMailHomePage.waitEmailAndOpenIt();
        System.out.println(email);
    }
}
