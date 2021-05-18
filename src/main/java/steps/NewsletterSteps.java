package steps;

import controllers.WebDriverController;
import definitions.NewsletterDefinition;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import utils.CustomUtils;

public class NewsletterSteps {

    private NewsletterDefinition testDefinition;

    @Before
    public void setUp(Scenario scenario) {
        testDefinition = new NewsletterDefinition();
    }

    @After
    public void tearDown() {
        WebDriverController.quitDriver();
    }

    @Given("a Hoffner login page")
    public void a_hoffner_login_page() {
        testDefinition.setHoffnerLoginPage();
    }

    @When("I open a page")
    public void i_open_a_page() {
        testDefinition.openHoffnerLoginPage();
    }

    @Given("I open the Hoffner login page")
    public void i_open_the_hoffner_login_page() {
        testDefinition.setHoffnerLoginPage();
        testDefinition.openHoffnerLoginPage();
    }

    @Then("I can see a newsletter subscription input")
    public void i_can_see_a_newsletter_subscription_input() {
        testDefinition.verifyNewsletterSubsInputVisible();
    }

    @When("I enter a email in the input field")
    public void i_enter_a_email_in_the_input_field() {
        String randomEmail= CustomUtils.generateRandomEmail();
        testDefinition.enterEmail(randomEmail);
    }

    @When("I press 'Absenden' button")
    public void i_press_button() {
        testDefinition.pressAbsendenBtn();
    }

    @Then("I can see a confirmation message that my subscription is in progress")
    public void i_can_see_a_confirmation_message_that_my_subscription_is_in_progress() {
        testDefinition.VerifyConfirmationMsgVisible();
    }
}
