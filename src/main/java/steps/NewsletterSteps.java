package steps;

import controllers.WebDriverController;
import definitions.NewsletterDefinition;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class NewsletterSteps {

    private NewsletterDefinition definition;

    @Before
    public void setUp(Scenario scenario) {
        WebDriverController webDriverController = new WebDriverController(WebDriverController.Browser.Chrome);
        definition= new NewsletterDefinition(webDriverController.getDriver());
    }

    @Given("a Hoffner login page")
    public void a_hoffner_login_page() {
        definition.setHoffnerLoginPage();
    }

    @When("I open a page")
    public void i_open_a_page() {
        definition.openHoffnerLoginPage();
    }

    @Then("I can see a newsletter subscription input")
    public void i_can_see_a_newsletter_subscription_input() {
        definition.verifyNewsletterSubsInputVisible();
    }

    @When("I enter my email in the input field")
    public void i_enter_my_email_in_the_input_field() {
        definition.enterEmail("");
    }

    @When("I press 'Absenden' button")
    public void i_press_button() {
        definition.pressAbsendenBtn();
    }

    @Then("I can see a confirmation message that my subscription is in progress")
    public void i_can_see_a_confirmation_message_that_my_subscription_is_in_progress() {
        definition.VerifyConfirmationMsgVisible();
    }
}
