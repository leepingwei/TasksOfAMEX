package POM;

import Driver.Driver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CheckoutCompletePage extends Base {

    public CheckoutCompletePage(){
        PageFactory.initElements(Driver.getDriver(), this);
    }

    @FindBy(css = ".complete-header")
    private WebElement ThankYouMessage;

    WebElement element;

    public void chooseAnElementAndVerifyText (String elementName, String expectedText){
        switch (elementName){
            case "ThankYouMessage":
                element = ThankYouMessage;
        }
        verifyTextFunction(element, expectedText);
    }
}
