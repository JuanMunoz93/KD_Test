package controllers;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import utils.Log;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

public class WebDriverController {

    public enum Browser {
        Chrome,
        Firefox,
        Edge
    }

    private static WebDriver driver;

    public WebDriver getDriver() {
        return driver;
    }

    public WebDriverController(Browser browser)
    {
        switch (browser)
        {
            case Chrome:
                Log.LOGGER.info("Google chrome selected as browser");
                defineDriverForSO();
                driver=new ChromeDriver(generateChromeConfig());
                break;
            case Firefox:
            case Edge:
                Log.LOGGER.info("Browser not implemented, launching a default chrome driver");
            default:
                driver=new ChromeDriver();
                break;
        }
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
    }

    public void NavigateToPage(String url) {
        Log.LOGGER.info(String.format("Opening the following url: '%s'", url));
        driver.get(url);
    }

    public static void quitDriver(){
        driver.quit();
        Log.LOGGER.info("Browser closed");
    }

    public void openANewTabAndSwitch() {
        ((JavascriptExecutor)driver).executeScript("window.open('', '_blank');");
        Log.LOGGER.info("New tab opened");
        switchToLastTab();
    }

    public void switchToTab(int tab){
        ArrayList<String> tabs = new ArrayList<>(driver.getWindowHandles());
        driver.switchTo().window(tabs.get(tab));
        Log.LOGGER.info(String.format("Switched to the tab #%s", tab));
    }

    public void switchToLastTab(){
        switchToTab(new ArrayList<>(driver.getWindowHandles()).size()-1);
        Log.LOGGER.info("Switched to the last tab opened");
    }

    public String getCurrentURL(){
        String currentUrl = driver.getCurrentUrl();
        Log.LOGGER.info(String.format("Current Url: '%s'", currentUrl));
        return currentUrl;
    }

    private void defineDriverForSO(){
        String so=System.getProperty("os.name");
        if(so.contains("Linux")) {
            System.setProperty("webdriver.chrome.driver", "src/main/resources/drivers/chromedriver");
            Log.LOGGER.info("selected chrome driver to linux");
        }else{
            System.setProperty("webdriver.chrome.driver", "src/main/resources/drivers/chromedriver.exe");
            Log.LOGGER.info("selected chrome driver to windows");
        }
    }

    private ChromeOptions generateChromeConfig(){
        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.addArguments("start-maximized");
        chromeOptions.addArguments("incognito");
        Log.LOGGER.info("Chrome driver configured to be launched on incognito mode and maximized");
        return chromeOptions;
    }

}
