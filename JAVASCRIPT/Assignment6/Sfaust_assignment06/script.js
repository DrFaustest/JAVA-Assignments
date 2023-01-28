'use strict';
/*
Scott Faust
script.js
22_WI_INFO_2134_WW
Yi
1/25/2023
*/

//This is a helper function to convert a string
//to a numeric value
//Returns:
//      - An integer or a float value of the string
//Throws:
//      - Error if the string is not a number
function convertToNumber(numVal) {
    if(!isNaN(numVal)){ 
    (Number.isInteger(numVal)) ? parseInt(numVal) : parseFloat(numVal);
    } else {
        throw new Error("Error: Value to convert is not a number!");
    }
}

$(document).ready(()=> {
    valueToConvert = 0;
    valueHolder = $('#valueHolder');
    convertButton.addEventListener('click', ()=> {
        const selectedOptionValue = $('#conversionSelector :selected').val();
        
        try {
        valueToConvert = valueHolder.val();
        valueToConvert = convertToNumber(valueToConvert);
        } catch (error) {
            alert(error.message);
            reset();
            return;
        }

        if(valueToConvert < 0) { //doesnt allow for negitive temps
            alert("Error: Value to convert cannot be less than zero!");
            reset();
            return;
        }else if(valueToConvert === 0) { 
            alert("Error: Value to convert cannot be zero!");
            reset();
            return;
        }
        
        //BEGIN SWITCH
        switch(selectedOptionValue) {
            case "m2k": //miles to kilometers
                valueToConvert = valueToConvert * 1.609344;
                alert(valueHolder.val() + " miles is " + valueToConvert + " kilometers.");
                reset();
                break;
            case "k2m": //kilometers to miles
                valueToConvert = valueToConvert * 0.62137;
                alert(valueHolder.val() + " kilometers is " + valueToConvert + " miles.");
                reset();
                break;
            case "p2k": //pounds to kilograms
                valueToConvert = valueToConvert * 0.45359237;
                alert(valueHolder.val() + " pounds is " + valueToConvert + " kilograms.");
                reset();
                break;
            case "k2p": //kilograms to pounds
                valueToConvert = valueToConvert / 0.45359237;
                alert(valueHolder.val() + " kilograms is " + valueToConvert + " pounds.");
                reset();
                break;
            case "f2m": //feet to meters
                valueToConvert = valueToConvert * 0.3048;
                alert(valueHolder.val() + " feet is " + valueToConvert + " meters.");
                reset();
                break;
            case "m2f": //meters to feet
                valueToConvert = valueToConvert / 0.3048;
                alert(valueHolder.val() + " meters is " + valueToConvert + " feet.");
                reset();
                break;
            case "ctf": //celsius to fahrenheit
                valueToConvert = (valueToConvert * 1.8) + 32;
                alert(valueHolder.val() + " celsius is " + valueToConvert + " fahrenheit.");
                reset();
                break;
            case "ftc": //fahrenheit to celsius
                valueToConvert = (valueToConvert - 32) / 1.8;
                alert(valueHolder.val() + " fahrenheit is " + valueToConvert + " celsius.");
                reset();
                break;
        }
        //END SWITCH 

        function reset() {
            valueHolder.val("0"); //resets valueHolder to a string of 0 because thats the original value from the html
            valueHolder.focus(); //puts the cursor back in the valueHolder 
            
        }
    });
});

