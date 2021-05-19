package steps;

import controllers.WebDriverController;
import definitions.NewsletterDefinition;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import utils.CustomUtils;
import utils.Log;

public class NewsletterSteps {

    private NewsletterDefinition testDefinition;
    private String email="";

    @Before
    public void setUp(Scenario scenario) {
        Log.initLogs("src/test/resources/evidence/","Logger");
        Log.LOGGER.info("Test started");
        Log.LOGGER.info("Scenario: ".concat(scenario.getName()));
        testDefinition = new NewsletterDefinition();
    }

    @After
    public void tearDown() {
        WebDriverController.quitDriver();
        Log.LOGGER.info("Test finished");
        Log.LOGGER.info("---------------------------");
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
        testDefinition.openANewTab();
        testDefinition.setHoffnerLoginPage();
        testDefinition.openHoffnerLoginPage();
    }

    @Then("I can see a newsletter subscription input")
    public void i_can_see_a_newsletter_subscription_input() {
        testDefinition.verifyNewsletterSubsInputVisible();
    }

    @When("I enter a email in the input field")
    public void i_enter_a_email_in_the_input_field() {
        email= CustomUtils.generateRandomEmail();
        testDefinition.enterEmail(email);
    }

    @When("I press 'Absenden' button")
    public void i_press_button() {
        testDefinition.pressAbsendenBtn();
    }

    @Then("I can see a confirmation message that my subscription is in progress")
    public void i_can_see_a_confirmation_message_that_my_subscription_is_in_progress() {
        testDefinition.VerifyConfirmationMsgVisible();
    }

    @Given("I have an email with inbox")
    public void i_have_a_email_with_inbox() {
        email=testDefinition.getATempEmailWithInbox();
    }

    @And("I subscript my email to receive newsletter")
    public void i_subscript_my_email_To_receive_newsletter() {
        testDefinition.enterEmail(email);
        testDefinition.pressAbsendenBtn();
    }

    @Then("I receive and email to finish my subscription")
    public void i_receive_and_email_to_finish_my_subscription() {
        testDefinition.verifyConfirmationEmail();
    }

    @When("I complete my registration")
    public void i_complete_my_registration() {
        testDefinition.verifyConfirmationEmail();
        testDefinition.confirmRegistrarion();
    }

    @Then("the confirming subscription page is opened in a new tab")
    public void the_confirming_subscription_page_is_opened_in_a_new_tab() {
        testDefinition.verifySubscriptionCompleted();
    }
}
