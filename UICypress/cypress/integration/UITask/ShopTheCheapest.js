/// <reference types="Cypress"/>

import LoginPage from "./POM/LoginPage";
import HomePage from "./POM/HomePage";
import ShoppingCartPage from "./POM/ShoppingCartPage";
import CheckoutInfoPage from "./POM/CheckoutInfoPage";
import CheckoutOverviewPage from "./POM/CheckoutOverviewPage";
import CheckoutCompletePage from "./POM/CheckoutCompletePage";

describe("Shopping Test", function () {
  before(function () {
    cy.fixture("testData").then(function (data) {
      this.data = data;
    });
  });

  it("Shop the cheapest item", function () {
    const loginPage = new LoginPage();
    const homePage = new HomePage();
    const shoppingCartPage = new ShoppingCartPage();
    const checkoutInfoPage = new CheckoutInfoPage();
    const checkoutOverviewPage = new CheckoutOverviewPage();
    const checkoutCompletePage = new CheckoutCompletePage();
    let min = 0;

    cy.visit(this.data.url);
    loginPage.getUsernameBox().type(this.data.username);
    loginPage.getPasswordBox().type(this.data.password);
    loginPage.getLoginButton().click();

    homePage.getTitle().should("be.visible");
    cy.getMinIndex().then((index) => {
      homePage.getAddToCartButtons().eq(index).click();
    });
    homePage.getShoppingCart().click();

    cy.getMinNum().then((minNum) => {
      min = minNum;
    });
    shoppingCartPage.getCartItemPriceTags().each(($ele, index, $list) => {
      cy.pureNum($ele).then((element) => {
        expect(element).to.eq(min);
      });
    });
    shoppingCartPage.getContiuneShoppingButton().click();

    homePage.getShoppingCart().click();
    shoppingCartPage.getCheckoutButton().click();

    checkoutInfoPage.getFirstName().type(this.data.firstName);
    checkoutInfoPage.getLastName().type(this.data.lastName);
    checkoutInfoPage.getZipCode().type(this.data.zipCode);
    checkoutInfoPage.getContinueButton().click();

    checkoutOverviewPage.getItemTotalPrice().each(($ele, index, $list) => {
      cy.pureNum($ele).then((element) => {
        expect(element).to.eq(min);
      });
    });
    checkoutOverviewPage.getFinishButton().click();

    checkoutCompletePage
      .getThankYouMsg()
      .should("have.text", "Thank you for your order!");
  });
});
