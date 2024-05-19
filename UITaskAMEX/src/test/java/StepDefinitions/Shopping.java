package StepDefinitions;

import Driver.Driver;
import POM.*;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class Shopping extends Base {

    LoginPage loginPage = new LoginPage();
    HomePage homePage = new HomePage();
    ShoppingCartPage shoppingCartPage = new ShoppingCartPage();
    CheckoutOverviewPage checkoutOverviewPage = new CheckoutOverviewPage();
    CheckoutInfoPage checkoutInfoPage = new CheckoutInfoPage();
    CheckoutCompletePage checkoutCompletePage = new CheckoutCompletePage();
    private static double minNumber = 0;

    @Given("Navigate to saucedemo website")
    public void navigate_to_saucedemo_website() {
        Driver.getDriver().manage().deleteAllCookies();
        long timeForPageLoad = Long.parseLong(Base.properties.getProperty("setPageLoadTimeOut"));
        long timeForImplicitlyWait = Long.parseLong(Base.properties.getProperty("setImplicitlyWait"));
        Driver.getDriver().manage().timeouts().pageLoadTimeout(timeForPageLoad, TimeUnit.SECONDS);
        Driver.getDriver().manage().timeouts().implicitlyWait(timeForImplicitlyWait, TimeUnit.SECONDS);

        Driver.getDriver().get(Base.properties.getProperty("url"));
        Driver.getDriver().manage().window().maximize();
    }
    @When("Standard user enter valid {string} and {string}")
    public void standardUserEnterValidAnd(String user, String pass) {
        loginPage.chooseAnElementAndSendKeys("userName", user);
        loginPage.chooseAnElementAndSendKeys("password", pass);
    }
    @Then("User click Login button to login")
    public void user_click_login_button_to_login() {
        loginPage.chooseAnElementAndClick("loginButton");
    }
    @Then("Verify user is on the homepage")
    public void verify_user_is_on_the_homepage() {
        homePage.chooseAnElementAndCheckDisplayed("Title");
    }
    @Then("Find out the cheapest item add to the cart and verify")
    public void find_out_the_cheapest_item_add_to_the_cart_and_verify() {
        List<WebElement> listElements = homePage.getPriceTags();
        double[] tempNum = new double[listElements.size()];
        for (int i = 0; i<listElements.size();i++) {
            String raw = listElements.get(i).getText();
            if (raw != "" || raw != null){
                double num = numOnly(raw);
                tempNum[i] = num;
            }
        }
        List<WebElement> listElementsBtn = homePage.getAddToCartButtons();
        clickFunction(listElementsBtn.get(minIndex(tempNum)));

        homePage.chooseAnElementAndClick("shoppingCart");

        List<WebElement> priceTags = shoppingCartPage.getCartItemPriceTags();
        minNumber = tempNum[minIndex(tempNum)];
        for(WebElement element: priceTags){
            Assert.assertTrue(minNumber == numOnly(element.getText()));
        }
    }
    @Then("User click continue shopping")
    public void user_click_continue_shopping() {
        shoppingCartPage.chooseAnElementAndClick("ContinueShoppingButton");
    }
    @Then("Go to cart and click checkout")
    public void go_to_cart_and_click_checkout() {
        homePage.chooseAnElementAndClick("shoppingCart");
        shoppingCartPage.chooseAnElementAndClick("CheckoutButton");
    }
    @Then("Fill up with {string} {string} {string} and click continue")
    public void fill_up_with_and_click_continue(String firstName, String lastName, String zipCode) {
        checkoutInfoPage.chooseAnElementAndSendKeys("FirstName", firstName);
        checkoutInfoPage.chooseAnElementAndSendKeys("LastName", lastName);
        checkoutInfoPage.chooseAnElementAndSendKeys("ZipCode", zipCode);
        checkoutInfoPage.chooseAnElementAndClick("ContinueButton");
    }
    @Then("Verify the item total price and click finish")
    public void verify_the_item_total_price_and_click_finish() {
        Assert.assertTrue(minNumber == numOnly(checkoutOverviewPage.getItemTotalPrice().getText()));
        checkoutOverviewPage.chooseAnElementAndClick("FinishButton");
    }
    @Then("Shopping process complete and user suppose to see a Thank you message")
    public void shopping_process_complete_and_user_suppose_to_see_a_thank_you_message() {
        checkoutCompletePage.chooseAnElementAndVerifyText("ThankYouMessage", "thank you");
    }
    @Then("User quit from the browser")
    public void user_quit_from_the_browser() {
        Driver.quitDriver();
    }
}
