package POM;

import Driver.Driver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage extends Base {
    public LoginPage(){
        PageFactory.initElements(Driver.getDriver(), this);
    }

    @FindBy(css = "#user-name")
    private WebElement userName;

    @FindBy(css = "#password")
    private WebElement password;

    @FindBy(css = "#login-button")
    private WebElement loginButton;

    WebElement element;

    public void chooseAnElementAndSendKeys(String elementName, String value){
        switch (elementName){
            case "userName":
                element = userName;
                break;
            case "password":
                element = password;
        }
        sendKeysFunction(element, value);
    }

    public void chooseAnElementAndClick(String elementName){
        switch (elementName){
            case "loginButton":
                element = loginButton;
        }
        clickFunction(element);
    }
}
