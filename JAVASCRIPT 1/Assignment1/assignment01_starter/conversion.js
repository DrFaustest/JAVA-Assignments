'use strict';
/* 
Scott Faust
conversion.js
22_WI_INFO_2124_WW
Yi
12/7/2022
*/

 

let lbs = parseInt(prompt("Please enter your weight in pounds."));
let inches = parseInt(prompt("Please enter your height in inches"));
let farenheit = parseInt(prompt("Please enter the current temperature in Fahrenheit"));
//kg = lb x 0.45359237 rounded to 2 decimal places
const kg = parseInt(lbs * 0.45359237);
//cm = inches x 2.54
const cm = parseInt(inches * 2.54);
//c = (f -32) * 5/9
const celsius = parseInt(farenheit - 32) * 5/9;
const html = 
`
    <h1>The Metroficator</h1>  
    <p>${lbs} pounds equals ${kg.toFixed(2)} kg</p>
    <p>${inches} inches equals ${cm.toFixed(2)} cm</p>
    <p>${farenheit} F equals ${celsius.toFixed(2)} C</p>
`;
document.write(html);


