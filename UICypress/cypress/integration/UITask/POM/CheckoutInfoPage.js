class CheckoutInfoPage {
  getFirstName() {
    return cy.get("#first-name");
  }

  getLastName() {
    return cy.get("#last-name");
  }

  getZipCode() {
    return cy.get("#postal-code");
  }

  getContinueButton() {
    return cy.get("#continue");
  }
}
export default CheckoutInfoPage;
