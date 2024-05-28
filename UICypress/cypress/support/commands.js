// ***********************************************
// This example commands.js shows you how to
// create various custom commands and overwrite
// existing commands.
//
// For more comprehensive examples of custom
// commands please read more here:
// https://on.cypress.io/custom-commands
// ***********************************************
//
//
// -- This is a parent command --
// Cypress.Commands.add('login', (email, password) => { ... })

import HomePage from "../integration/UITask/POM/HomePage";
const homePage = new HomePage();

Cypress.Commands.add("pureNum", (element) => {
  return cy.wrap(Number(element.text().replace(/[^0-9.]/g, "")));
});

Cypress.Commands.add("getMinIndex", () => {
  let minNum = Number.MAX_VALUE;
  let minIndex = 0;
  homePage
    .getPriceTags()
    .each(($ele, index, $list) => {
      const pureNum = Number($ele.text().replace(/[^0-9.]/g, ""));
      if (pureNum < minNum) {
        minNum = pureNum;
        minIndex = index;
      }
    })
    .then(() => {
      return cy.wrap(minIndex);
    });
});

Cypress.Commands.add("getMinNum", () => {
  let minNum = Number.MAX_VALUE;
  homePage
    .getPriceTags()
    .each(($ele, index, $list) => {
      const pureNum = Number($ele.text().replace(/[^0-9.]/g, ""));
      if (pureNum < minNum) {
        minNum = pureNum;
      }
    })
    .then(() => {
      return cy.wrap(minNum);
    });
});

//
// -- This is a child command --
// Cypress.Commands.add('drag', { prevSubject: 'element'}, (subject, options) => { ... })
//
//
// -- This is a dual command --
// Cypress.Commands.add('dismiss', { prevSubject: 'optional'}, (subject, options) => { ... })
//
//
// -- This will overwrite an existing command --
// Cypress.Commands.overwrite('visit', (originalFn, url, options) => { ... })
