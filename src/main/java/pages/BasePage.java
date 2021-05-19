package pages;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public abstract class BasePage {
    protected final WebDriver driver;
    protected WebDriverWait wait;
    protected final int DEFAULT_TIMEOUT=5;

    public BasePage(WebDriver driver){
        this.driver=driver;
    }

    protected void waitClick(WebElement element, int timeout){
        wait = new WebDriverWait(driver, timeout);
        wait.until(ExpectedConditions.elementToBeClickable(element)).click();
    }

    protected boolean waitVisible(WebElement element, int timeout){
        wait = new WebDriverWait(driver, timeout);
        return wait.until(ExpectedConditions.visibilityOf(element)).isDisplayed();
    }

    protected void waitWrite(WebElement element, String text, int timeout){
        wait = new WebDriverWait(driver, timeout);
        wait.until((ExpectedCondition<Boolean>) driver -> element.isEnabled());
        element.sendKeys(text);
    }

    protected void centerElement(WebElement element){
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
    }

}
