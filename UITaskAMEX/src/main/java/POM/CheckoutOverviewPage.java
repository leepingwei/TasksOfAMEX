package POM;

import Driver.Driver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CheckoutOverviewPage extends Base {

    public CheckoutOverviewPage(){
        PageFactory.initElements(Driver.getDriver(), this);
    }

    @FindBy(css = ".summary_subtotal_label")
    private WebElement ItemTotalPrice;

    @FindBy(css = "#finish")
    private WebElement FinishButton;

    WebElement element;

    public void chooseAnElementAndClick(String elementName){
        switch (elementName){
            case "FinishButton":
                element = FinishButton;
        }
        clickFunction(element);
    }

    public WebElement getItemTotalPrice() {
        return ItemTotalPrice;
    }
}
