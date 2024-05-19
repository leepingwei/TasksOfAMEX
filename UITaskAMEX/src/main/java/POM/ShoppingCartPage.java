package POM;

import Driver.Driver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class ShoppingCartPage extends Base {

    public ShoppingCartPage(){
        PageFactory.initElements(Driver.getDriver(), this);
    }

    @FindBy(css = ".cart_list")
    private List<WebElement> CartList;

    @FindBy(css = ".inventory_item_price")
    private List<WebElement> CartItemPriceTags;

    @FindBy(css = "#continue-shopping")
    private WebElement ContinueShoppingButton;

    @FindBy(css = "#checkout")
    private WebElement CheckoutButton;

    WebElement element;

    public void chooseAnElementAndClick(String elementName){
        switch (elementName){
            case "ContinueShoppingButton":
                element = ContinueShoppingButton;
                break;
            case "CheckoutButton":
                element = CheckoutButton;
        }
        clickFunction(element);
    }

    public List<WebElement> getCartItemPriceTags() {
        return CartItemPriceTags;
    }
}
