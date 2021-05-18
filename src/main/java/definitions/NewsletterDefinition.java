package definitions;

import controllers.WebDriverController;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.WebDriver;

public class NewsletterDefinition {

    private String hoffnerLoginURL;
    private WebDriverController webDriverController;

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
        Assertions.fail("not implemented");
    }

    public void enterEmail(String userEmail) {
        Assertions.fail("not implemented");
    }

    public void pressAbsendenBtn() {
        Assertions.fail("not implemented");
    }

    public void VerifyConfirmationMsgVisible() {
        Assertions.fail("not implemented");
    }
}
