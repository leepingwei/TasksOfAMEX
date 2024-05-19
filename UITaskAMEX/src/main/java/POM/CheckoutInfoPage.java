package POM;

import Driver.Driver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CheckoutInfoPage extends Base {

    public CheckoutInfoPage(){
        PageFactory.initElements(Driver.getDriver(), this);
    }

    @FindBy(css = "#first-name")
    private WebElement FirstName;

    @FindBy(css = "#last-name")
    private WebElement LastName;

    @FindBy(css = "#postal-code")
    private WebElement ZipCode;

    @FindBy(css = "#continue")
    private WebElement ContinueButton;

    WebElement element;

    public void chooseAnElementAndSendKeys(String elementName, String value){
        switch (elementName){
            case "FirstName":
                element = FirstName;
                break;
            case "LastName":
                element = LastName;
                break;
            case "ZipCode":
                element = ZipCode;
        }
        sendKeysFunction(element, value);
    }

    public void chooseAnElementAndClick(String elementName){
        switch (elementName){
            case "ContinueButton":
                element = ContinueButton;
        }
        clickFunction(element);
    }
}
