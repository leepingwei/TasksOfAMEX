class LoginPage {
  getUsernameBox() {
    return cy.get("#user-name");
  }

  getPasswordBox() {
    return cy.get("#password");
  }

  getLoginButton() {
    return cy.get("#login-button");
  }
}
export default LoginPage;
