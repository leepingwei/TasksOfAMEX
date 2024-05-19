package POM;

import Driver.Driver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class HomePage extends Base{

    public HomePage(){
        PageFactory.initElements(Driver.getDriver(), this);
    }

    @FindBy(css = ".title")
    private WebElement Title;

    @FindBy(css = "div[class = 'inventory_item_price']")
    private List<WebElement> priceTags;

    @FindBy(css = "div[class = 'inventory_item_price']~button")
    private List<WebElement> addToCartButtons;

    @FindBy(css = "#shopping_cart_container")
    private WebElement shoppingCart;

    WebElement element;

    public void chooseAnElementAndClick(String elementName){
        switch (elementName){
            case "shoppingCart":
                element = shoppingCart;
        }
        clickFunction(element);
    }

    public void chooseAnElementAndCheckDisplayed(String elementName){
        switch (elementName){
            case "Title":
                element = Title;
        }
        verifyElementIsDisplayed(element);
    }

    public List<WebElement> getPriceTags() {
        return priceTags;
    }

    public List<WebElement> getAddToCartButtons() {
        return addToCartButtons;
    }
}
