class HomePage {
  getTitle() {
    return cy.get(".title");
  }

  getPriceTags() {
    return cy.get("div[class = 'inventory_item_price']");
  }

  getAddToCartButtons() {
    return cy.get("div[class = 'inventory_item_price']~button");
  }

  getShoppingCart() {
    return cy.get("#shopping_cart_container");
  }
}
export default HomePage;
