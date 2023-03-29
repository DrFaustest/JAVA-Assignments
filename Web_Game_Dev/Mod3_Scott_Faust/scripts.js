/*
Battle Ship style game
Use the JavaScript from the alien attack example in the text book to make the fire button shoot at the patrol 
ship using the x and y inputs that the player enters. When the user guesses where the ship is it will show the ship 
and output that the game is over, the player won, and how many guesses it took. The ship is 13 by 55 pixels. 
You will need this size to find the area that the ship takes up. \
*/

//Game variables
let shipX = 80;
let shipY = 20;
let guessX = 0;
let guessY = 0;
let gameWon = false;
let guesses = 0;
let validInput = false;
let output = document.querySelector("#outputText");
let ship = document.querySelector("#ship");
let inputX = document.querySelector("#inputX");
let inputY = document.querySelector("#inputY");
let button = document.querySelector("button");
button.style.cursor = "pointer";
button.addEventListener("click", clickHandler, false);

function render() {
  //Position the ship
  ship.style.left = shipX + "px";
  ship.style.top = shipY + "px";
}

function validateInput() {
  //check that the player has entered a number
  if (isNaN(inputX.value) || isNaN(inputY.value)) {
    output.innerHTML = "Please enter a number for both X and Y";
    validInput = false;
  } else {
    validInput = true;
  }
}

function clickHandler() {
  playGame();
}

function playGame() {
  //Validate the player's input
  validateInput();
  //If the input is valid, play the game
  if (validInput) {
    //Get the player's guess
    guessX = parseInt(inputX.value);
    guessY = parseInt(inputY.value);
    //Find out whether the player's x and y guesses are inside
    //The ships's area
    if (guessX >= shipX && guessX <= shipX + 14) {
      //Yes, it's within the X range, so now let's
      //check the Y range
      if (guessY >= shipY && guessY <= shipY + 50) {
        //It's in both the X and Y range, so it's a hit!
        gameWon = true;
        //Change the visibility of the ship to visible
        ship.style.visibility = "visible";
        //Change the output message
        output.innerHTML = "Hit! You sunk the ship! It took you " + guesses + " guesses.";
        //End the game
        endGame();
      }
    } else {
      //Increment the number of guesses
      guesses++;
      //The player's guess was not a hit
      output.innerHTML = "Miss! Try again! Guesses: " + guesses;
    }
  } else {
    //The player's input was not valid
    output.innerHTML = "Please enter a number for both X and Y";
  }
}

