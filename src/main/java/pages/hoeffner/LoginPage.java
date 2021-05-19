package pages.hoeffner;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import pages.BasePage;
import utils.Log;

public class LoginPage extends BasePage {

    @FindBy(how = How.CSS, using = "div.consentForm__acceptButton[data-accept-action='all']>button.button")
    private WebElement acceptCookiesBtn;

    @FindBy(how = How.ID, using = "email")
    private WebElement emailInput;

    @FindBy(how = How.ID, using = "newsletterFormSubmitBtn")
    private WebElement submitMailBtn;

    @FindBy(how = How.CSS, using = "div.footerNewsletter__confirmation")
    private WebElement subscriptionInProgressMsj;


    public LoginPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver,this);
    }

    public void acceptAllCookies(){
        waitClick(acceptCookiesBtn,10);
        Log.LOGGER.info("All cookies accepted");
    }

    public boolean isEmailInputVisible(){
        return waitVisible(emailInput,DEFAULT_TIMEOUT);
    }

    public void writeEmail(String email){
        waitWrite(emailInput,email,DEFAULT_TIMEOUT);
        Log.LOGGER.info(String.format("email '%s' wrote in the email input", email));
    }

    public void submitMail(){
        waitClick(submitMailBtn,DEFAULT_TIMEOUT);
        Log.LOGGER.info("Absenden Button clicked");
    }

    public boolean isSubscriptionInProgressMsjVisible(){
        return waitVisible(subscriptionInProgressMsj,15);
    }

}
