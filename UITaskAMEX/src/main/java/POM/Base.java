package POM;

import Driver.Driver;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class Base {

    public static Properties properties;
    WebDriver driver;
    WebDriverWait wait;

    public Base(){
        properties = new Properties();
        try {
            // Might need your own path here
            FileInputStream fis = new FileInputStream("/Users/lee/Desktop/AMEXTasks/AMEXUITask/src/main/java/Util/config.properties");
            properties.load(fis);
        } catch (IOException e) {
            e.printStackTrace();
        }
        driver = Driver.getDriver();

        long explicitlyWaitDuration = Long.parseLong(properties.getProperty("setExplicitlyWait"));
        wait = new WebDriverWait(driver, explicitlyWaitDuration);
    }

    public void sendKeysFunction(WebElement element, String value){
        wait.until(ExpectedConditions.visibilityOf(element));
        element.sendKeys(value);
    }

    public void clickFunction(WebElement element){
        wait.until(ExpectedConditions.elementToBeClickable(element));
        element.click();
    }

    public void verifyTextFunction(WebElement element, String expectedText){
        wait.until(ExpectedConditions.visibilityOf(element));
        Assert.assertTrue(element.getText().toLowerCase().contains(expectedText));
    }

    public void verifyElementIsDisplayed(WebElement element){
        wait.until(ExpectedConditions.visibilityOf(element));
        Assert.assertTrue(element.isDisplayed());
    }

    public double numOnly(String str){
        String digit = str.replaceAll("[^0-9.]","").trim();
        double num = Double.parseDouble(digit);
        return num;
    }

    public int minIndex(double[] arr){
        double minNum = Double.MAX_VALUE;
        int minIndex = 0;
        for(int i = 0; i< arr.length;i++){
            if (arr[i] < minNum){
                minNum = arr[i];
                minIndex = i;
            }
        }return minIndex;
    }

}
