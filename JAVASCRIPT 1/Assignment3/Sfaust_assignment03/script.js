'use strict';

/* 
Scott Faust
script.js
22_WI_INFO_2124_WW
Yi
12/14/2022
*/

//DO NOT MODIFY THIS CODE
function calculateTax(subtotal, taxRate) {
    const tax = parseFloat(subtotal * taxRate);
    return tax.toFixed(2);
}

function print(grossWages, federalTax, stateTax, ssTax, medicareTax, netWages) {
    const msg = `
                Gross wages: $${grossWages.toFixed(2)}

                Deductions:
                --------------------------------
                Federal Taxes:   $${federalTax.toFixed(2)}
                State Taxes:     $${stateTax.toFixed(2)}
                Social Security: $${ssTax.toFixed(2)}
                Medicare:        $${medicareTax.toFixed(2)}

                Net wages: $${netWages.toFixed(2)}
    `;
    alert(msg);
}
//END DO NOT MODIFY THIS CODE
//YOUR CODE GOES BELOW

const $ = (selector) => document.querySelector(selector);
// this is a function that will return the first element that matches the selector it simply makes it easier to select elements

document.addEventListener('DOMContentLoaded', () => {
    //this is an event listener that will wait for the DOM to load preventing the script from running prematurely
    $('#calculateTaxes').addEventListener('click', () => {  
        const grossWages = parseFloat($('#monthlySalary').value); 
        //# refers to id . refers to class
        const federalTax = parseFloat(calculateTax(grossWages, .12));
        //parseFloat is a function that will convert a string to a float for further calculations
        const stateTax = parseFloat(calculateTax(grossWages, .05));
        const ssTax = parseFloat(calculateTax(grossWages, .06));
        const medicareTax = parseFloat(calculateTax(grossWages, .015));
        const totalTax = parseFloat(federalTax) + parseFloat(stateTax) + parseFloat(ssTax) + parseFloat(medicareTax);
        // I added the total tax for testing purposes. it could easily be added below in parentheses but leaving it here would allow for future use if needed.
        const netWages = grossWages - totalTax;
        print(grossWages, federalTax, stateTax, ssTax, medicareTax, netWages);
    });
});
/*  Terminology
    --------------
    Here are some terms, in case you don't understand gross/net wages.

    Gross wages is your full pay, before deductions.

    Net wages is what you actually receive, once taxes, insurance, retirement, etc. have been deducted.
*/

