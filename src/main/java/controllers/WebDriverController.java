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
        String so=System.getProperty("os.name");
        switch (browser)
        {
            case Chrome:
                if(so.contains("Linux")) {
                    System.setProperty("webdriver.chrome.driver", "src/main/resources/drivers/chromedriver");
                }else{
                    System.setProperty("webdriver.chrome.driver", "src/main/resources/drivers/chromedriver.exe");
                }
                ChromeOptions chromeOptions = new ChromeOptions();
                chromeOptions.addArguments("start-maximized");
                chromeOptions.addArguments("incognito");
                driver=new ChromeDriver(chromeOptions);
                break;
            case Firefox:
            case Edge:
                System.out.println("not implemented, lauching chrome");
            default:
                driver=new ChromeDriver();
                break;
        }
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
    }
}
