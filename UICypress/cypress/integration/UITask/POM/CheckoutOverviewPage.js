class CheckoutOverviewPage {
  getItemTotalPrice() {
    return cy.get(".summary_subtotal_label");
  }

  getFinishButton() {
    return cy.get("#finish");
  }
}
export default CheckoutOverviewPage;
