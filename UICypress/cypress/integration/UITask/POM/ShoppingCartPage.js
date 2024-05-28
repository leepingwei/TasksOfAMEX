class ShoppingCartPage {
  getCartList() {
    return cy.get(".cart_list");
  }

  getCartItemPriceTags() {
    return cy.get(".inventory_item_price");
  }

  getContiuneShoppingButton() {
    return cy.get("#continue-shopping");
  }

  getCheckoutButton() {
    return cy.get("#checkout");
  }
}
export default ShoppingCartPage;
