package pages.tempmail;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;
import pages.BasePage;

import java.util.List;

public class TempMailHomePage extends BasePage {

    @FindBy(how = How.ID, using = "mail")
    private WebElement mailInput;

    @FindBy(how = How.ID, using = "click-to-refresh")
    private WebElement refreshBtn;

    @FindBy(how = How.CSS, using = "div.user-data-subject>h4")
    private WebElement mailSubjectLabel;

    @FindBy(how = How.CSS, using = "div.inbox-data-content-intro td>a[title]")
    private WebElement completeRegistrationBtn;

    private final String MAIL_LIST_CSS="div.inbox-dataList>ul>li>div>a";

    public TempMailHomePage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver,this);
    }

    public String getAnEmail(){
        wait = new WebDriverWait(driver, 30);
        return wait.until((ExpectedCondition<String>) driver -> {
            String mailValue =mailInput.getAttribute("value");
            if (mailValue.contains("@")){
                return mailValue;
            }
            return null;
        });
    }

    public boolean waitANewMail(){
        wait = new WebDriverWait(driver, 60);
        return wait.until((ExpectedCondition<Boolean>) driver -> {
            if (driver.findElements(By.cssSelector(MAIL_LIST_CSS)).size()>1){
                return Boolean.TRUE;
            }
            driverSleep(2000);
            return null;
        });
    }


    public void openLastReceivedMail(){
        WebElement firstReceivedMail = driver.findElements(By.cssSelector(MAIL_LIST_CSS)).get(1);
        centerElement(firstReceivedMail);
        firstReceivedMail.click();
    }

    public String getMailSubject(){
        centerElement(mailSubjectLabel);
        waitVisible(mailSubjectLabel,10);
        return mailSubjectLabel.getText();
    }

    public void confirmMailRegistration(){
        waitClick(completeRegistrationBtn,DEFAULT_TIMEOUT);
    }

    private void driverSleep(int i) {
        try {
            Thread.sleep(i);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
