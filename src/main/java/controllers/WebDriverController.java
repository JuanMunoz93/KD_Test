package controllers;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

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
                defineDriverForSO();
                driver=new ChromeDriver(generateChromeConfig());
                break;
            case Firefox:
            case Edge:
                System.out.println("not implemented, launching a default chrome driver");
            default:
                driver=new ChromeDriver();
                break;
        }
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
    }

    public void NavigateToPage(String url) {
        driver.get(url);
    }

    public static void quitDriver(){
        driver.quit();
    }

    private void defineDriverForSO(){
        String so=System.getProperty("os.name");
        if(so.contains("Linux")) {
            System.setProperty("webdriver.chrome.driver", "src/main/resources/drivers/chromedriver");
        }else{
            System.setProperty("webdriver.chrome.driver", "src/main/resources/drivers/chromedriver.exe");
        }
    }

    private ChromeOptions generateChromeConfig(){
        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.addArguments("start-maximized");
        chromeOptions.addArguments("incognito");
        return chromeOptions;
    }
}
