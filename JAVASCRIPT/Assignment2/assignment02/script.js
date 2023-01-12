'use strict';
/* 
Scott Faust
script.js
22_WI_INFO_2124_WW
Yi
12/14/2022
*/

const cargoWeight = [];           //this will hold an array of different item weights
let totalCargoWeight = 0;      
let itemWeight = 0;             //this will hold the weight of a single item to be checked before adding to the array
const maxWeightLbs = 1000;        //this is the maximum weight the rocket can hold
let average = 0;                //this will hold the average weight of all items

//haveing met and unmet variables makes it eaiser to embed the condition to a place holder varriable that can be called reguardless of the condition all variables below are changes to the original code 
const readyToTakeOff = "<p>Congratuluations! The rocket can take off!</p>";
const notReadyToTakeOff = "<p>Oh no! Your Rocket is too heavy to take off!</p><p>The rocket must be less than 1000 pounds to take off!</p>"; 
let rocketStatus = "";          //this will hold the status of the rocket, one of the two above messages
let html = ''; //what is written to the page

do //do while loop to get the item weight from the user
{
    itemWeight = parseFloat(prompt("Please enter the item weight in pounds, or input -1 to exit.")); // user input
    if(itemWeight > 0) { //if the item weight is greater than 0, add it to the array
        cargoWeight[cargoWeight.length] = itemWeight;
    } else if (itemWeight <= 0 && itemWeight != -1) { //if the item weight is less than or equal to 0, but not -1, alert the user
        alert("Item weight must be a valid number that is greater than zero pounds!");
    } else { //if the item weight is -1, calculate the total cargo weight and average item weight
        //Modify the for loop so that it is a for-of loop
        if(cargoWeight.length >= 2) //if the array has more than one element, calculate the average
        {
            for (let cargo of cargoWeight) //names the elements cargo and iterates through the array cargoWeight for each element (world work reguardless of the elements name, for reference only)
            {
                totalCargoWeight = totalCargoWeight + cargo;
            }
            average = parseFloat(totalCargoWeight / cargoWeight.length);
        }else {  //if the array has only one element, set the total cargo weight and average to that element
            totalCargoWeight = cargoWeight[0];
            average = cargoWeight[0];
        }
        if (totalCargoWeight > maxWeightLbs) {  //if the total cargo weight is greater than the max weight, set the rocket status to not ready to take off
            rocketStatus = notReadyToTakeOff;
        } else { //if the total cargo weight is less than or equal to the max weight, set the rocket status to ready to take off
            rocketStatus = readyToTakeOff;
        }
        }
    html = `<h1> Space Weight </h1> <p> Total cargo weight is ${totalCargoWeight.toFixed(2)}</p> <p> Average item weight is ${average.toFixed(2)}</p> ${rocketStatus}`; 
    // save the html to a variable so that it can be written to the page
    
    //alert("Total cargo weight is " + totalCargoWeight +"; average item weight is " + average);
} while (itemWeight != -1);
document.write(html); //write the html to the page

