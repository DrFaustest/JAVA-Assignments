'use strict';
/*
Scott Faust
convert_temp.js
22_WI_INFO_2134_WW
YI
1/6/2023
*/

//DO NOT MODIFY THE CODE BELOW 

/*********************
*  helper functions  *
**********************/
const $ = selector => document.querySelector(selector);
const calculateCelsius = temp => (temp-32) * 5/9;
const calculateFahrenheit = temp => temp * 9/5 + 32;
const calculateMeters = feet => feet * 3.2808; // added
const calculateFeet = meters => meters / 3.2808; // added
//ADD YOUR CODE BELOW 

const toggleDisplay = (label1Text, label2Text) => {
    // update labels and clear disabled textbox
    $("#label_1").textContent = label1Text;
    $("#label_2").textContent = label2Text;
    $("#value_computed").value = "";
    // select text in degrees textbox
	$("#value_entered").select();
}

/****************************
*  event handler functions  *
*****************************/
const performConversion = () => {
    //Obtain the value input by the user. Make sure you preserve numeric precision.
    const valueEntered = parseFloat($("#value_entered").value);
    //If the value input by the user is not a number, display the appropriate error message in the <div> with the id value “message.” Then, clear the input and select the #value_entered textbox.
    if (isNaN(valueEntered)) {
        // log the valueEntered and the text that was entered
        console.log(valueEntered, $("#value_entered").value);
        $("#message").textContent = "You must enter a number.";
        $("#value_entered").value = "";
        $("#value_entered").select();
    } else {
        //If the value input by the user is a number, clear the message.
        $("#message").textContent = "";
        console.log(valueEntered);
        let checkedRadio = "";
        // Compute and display appropriate value based on which radio button is checked by invoking the appropriate function
        if ($("#to_celsius").checked) {
            $("#value_computed").value = calculateCelsius(valueEntered).toFixed(2);
            checkedRadio = "to_celsius";
        } else if ($("#to_fahrenheit").checked) {
            $("#value_computed").value = calculateFahrenheit(valueEntered).toFixed(2);
            checkedRadio = "to_fahrenheit";
        } else if ($("#to_meters").checked) {
            $("#value_computed").value = calculateMeters(valueEntered).toFixed(2);
            checkedRadio = "to_meters";
        } else if ($("#to_feet").checked) {
            $("#value_computed").value = calculateFeet(valueEntered).toFixed(2);
            checkedRadio = "to_feet";
        }
        //Select the text in the #value_entered textbox.
        console.log($("#value_computed").value, checkedRadio);
        $("#value_entered").select();
    }


};

const toggleToCelsius = () => toggleDisplay("Enter F degrees:", "Degrees Celsius:");
const toggleToFahrenheit = () => toggleDisplay("Enter C degrees:", "Degrees Fahrenheit:");
const toggleToMeters = () => toggleDisplay("Enter feet:", "Meters:"); // added
const toggleToFeet = () => toggleDisplay("Enter meters:", "Feet:"); // added

document.addEventListener("DOMContentLoaded", () => {
	// add event handlers
	$("#convert").addEventListener("click", performConversion);
    $("#to_celsius").addEventListener("click", toggleToCelsius);
    $("#to_fahrenheit").addEventListener("click", toggleToFahrenheit);
    $("#to_meters").addEventListener("click", toggleToMeters); // added
    $("#to_feet").addEventListener("click", toggleToFeet); //   added
	
	// move focus
	$("#value_entered").focus();
});



