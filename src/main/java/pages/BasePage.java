package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public abstract class BasePage {
    private WebDriver driver;
    private WebDriverWait wait;
    public final int DEFAULT_TIMEOUT=5;

    public BasePage(WebDriver driver){
        this.driver=driver;
    }

    public void waitClick(WebElement element, int timeout){
        wait = new WebDriverWait(driver, timeout);
        wait.until(ExpectedConditions.elementToBeClickable(element)).click();
    }

    public boolean waitVisible(WebElement element, int timeout){
        wait = new WebDriverWait(driver, timeout);
        return wait.until(ExpectedConditions.visibilityOf(element)).isDisplayed();
    }

    public void waitWrite(WebElement element, String text, int timeout){
        wait = new WebDriverWait(driver, timeout);
        wait.until((ExpectedCondition<Boolean>) driver -> element.isEnabled());
        element.sendKeys(text);
    }

}
