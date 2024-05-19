package Driver;

import POM.Base;
import Util.CustomEventListener;
import Util.LimitedBrowserException;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.events.EventFiringWebDriver;

public class Driver {

    private static WebDriver driver;

    public static WebDriver getDriver(){
        if (driver == null){
            String browserName = Base.properties.getProperty("browser");
            if(browserName.equalsIgnoreCase("chrome")){
                WebDriverManager.chromedriver().setup();
                driver = new ChromeDriver();
            }else if (browserName.equalsIgnoreCase("firefox")){
                WebDriverManager.firefoxdriver().setup();
                driver = new FirefoxDriver();
            }else {
                throw new LimitedBrowserException("Please choose a browser between Chrome and Firefox!");
            }
        }

        String listener = Base.properties.getProperty("listener");
        if(listener.equalsIgnoreCase("on")){
            EventFiringWebDriver eventDriver = new EventFiringWebDriver(driver);
            CustomEventListener eventListener = new CustomEventListener();
            eventDriver.register(eventListener);
            return driver = eventDriver;
        }else if (listener.equalsIgnoreCase("off")){
            return driver;
        }else{
            quitDriver();
            throw new IllegalArgumentException("Please type between \"on\" and \"off\"");
        }
    }

    public static void quitDriver(){
        if (driver != null){
            driver.quit();
            driver = null;
        }
    }

    private Driver(){}
}
