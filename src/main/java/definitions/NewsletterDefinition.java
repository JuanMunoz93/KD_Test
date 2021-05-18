package definitions;

import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.WebDriver;

public class NewsletterDefinition {

    private String hoffnerLoginURL;
    private WebDriver driver;

    public NewsletterDefinition(WebDriver driver) {
        this.driver = driver;
    }

    public void setHoffnerLoginPage() {
        hoffnerLoginURL="https://www.hoeffner.de/login";
    }

    public void openHoffnerLoginPage() {
        driver.get(hoffnerLoginURL);

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
