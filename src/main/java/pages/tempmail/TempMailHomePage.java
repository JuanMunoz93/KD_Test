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

    public void waitEmailAndOpenIt(){
        wait = new WebDriverWait(driver, 60);
        wait.until((ExpectedCondition<WebElement>) driver -> {
            List<WebElement> mails = driver.findElements(By.cssSelector("div.inbox-dataList>ul>li>div>a"));
            if (mails.size()>1){
                return mails.get(1);
            }
            return null;
        }).click();
    }
}
