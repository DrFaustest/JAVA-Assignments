// Need to edit this code to use a 10 x 10 grid the fits inside the game area within the image
// The grid should be 10 x 10 squares
// The ship should be an equal size to the number of hits it takes to sink it
// The ship should be placed randomly on the grid at the start of the game either horizontally or vertically
// The ship should be placed so that it does not go off the grid
// The ship should be placed so that it does not overlap another ship
// The player should be able to click on a square to fire a shot or input the coordinates of the square to fire a shot
// The player shouldn't be able to fire a shot at a square that has already been fired at
// once all the ships have been sunk the game should end

//Create the 2D array for the game grid
let gameGrid = [
  [0, 0, 0, 0, 0, 0, 0, 0, 0, 0],
  [0, 0, 0, 0, 0, 0, 0, 0, 0, 0],
  [0, 0, 0, 0, 0, 0, 0, 0, 0, 0],
  [0, 0, 0, 0, 0, 0, 0, 0, 0, 0],
  [0, 0, 0, 0, 0, 0, 0, 0, 0, 0],
  [0, 0, 0, 0, 0, 0, 0, 0, 0, 0],
  [0, 0, 0, 0, 0, 0, 0, 0, 0, 0],
  [0, 0, 0, 0, 0, 0, 0, 0, 0, 0],
  [0, 0, 0, 0, 0, 0, 0, 0, 0, 0],
  [0, 0, 0, 0, 0, 0, 0, 0, 0, 0],
];
let guessXPositionConversion = [
  "A",
  "B",
  "C",
  "D",
  "E",
  "F",
  "G",
  "H",
  "I",
  "J",
];
let patrolSize = 2;
let patrolHits = 0;
let patrolSunk = false;
let patrolPosition = setShipPositions(patrolSize, gameGrid);
let shipPatrol = document.querySelector("#shipPatrol");
let destroyerSize = 3;
let destroyerHits = 0;
let destroyerSunk = false;
let destroyerPosition = setShipPositions(destroyerSize, gameGrid);
let shipDestroyer = document.querySelector("#shipDestroyer");
let battleSize = 4;
let battleHits = 0;
let battleSunk = false;
let battlePosition = setShipPositions(battleSize, gameGrid);
let shipBattle = document.querySelector("#shipBattle");
let numberSunken = 0;
let output = document.querySelector("#output");
let inputX = document.querySelector("#inputX");
let inputY = document.querySelector("#inputY");
let button = document.querySelector("#fire");
let stage = document.querySelector("#stage");
let cettHeight = 15;
let cettWidth = 15;
let cettMargin = 1;

button.style.cursor = "pointer";
button.addEventListener("click", clickHandler, false);
console.log(gameGrid);
console.log(patrolPosition);
console.log(destroyerPosition);
console.log(battlePosition);

function setShipPositions(shipSize, gameGrid) {
  let shipPosition = [];
  let shipOrientation = Math.floor(Math.random() * 2);
  let shipXPosition = Math.floor(Math.random() * 10);
  let shipYPosition = Math.floor(Math.random() * 10);
// using the game grid, ensure that the ships are only placed in valid positions on the grid with a value of 0
// the ship should be placed so that it does not go off the grid
// the ship should be placed so that it does not overlap another ship
  if (shipOrientation === 0) {
	if (shipXPosition + shipSize > 10) {
			  shipXPosition = shipXPosition - shipSize;
	}	
	for (let i = 0; i < shipSize; i++) {
			  shipPosition.push([shipXPosition + i, shipYPosition]);
	}
} else {
	if (shipYPosition + shipSize > 10) {
			  shipYPosition = shipYPosition - shipSize;
	}
	for (let i = 0; i < shipSize; i++) {
			  shipPosition.push([shipXPosition, shipYPosition + i]);
	}
} for (let i = 0; i < shipPosition.length; i++) {
	if (gameGrid[shipPosition[i][0]][shipPosition[i][1]] === 1) {
		setShipPositions(shipSize, gameGrid);
	}
}
updateGameGrid(shipPosition);
return shipPosition;
} 
function build () {
  for (let i = 0; i < 10; i++) {
	for (let j = 0; j < 10; j++) {
	  let cett = document.createElement("div");
	  cett.style.height = cettHeight + "px";
	  cett.style.width = cettWidth + "px";
	  cett.style.margin = cettMargin + "px";
	  cett.style.border = "1px solid black";
	  cett.style.float = "left";
	  cett.style.backgroundColor = "white";
	  cett.id = i + "-" + j;
	  stage.appendChild(cett);
	}
  }
}

function updateGameGrid(shipPosition) {
  for (let i = 0; i < shipPosition.length; i++) {
	gameGrid[shipPosition[i][0]][shipPosition[i][1]] = 1;
  }
}

function clickHandler() {
  let x = inputX.value.toUpperCase();
  let y = inputY.value;
  let xPosition = guessXPositionConversion.indexOf(x);
  let yPosition = y - 1;
  let guess = [yPosition, xPosition];
  console.log(guess);
  checkGuess(guess);
}

function checkGuess(guess) {
	  let x = guess[0];
  let y = guess[1];
  if (x < 0 || x > 9 || y < 0 || y > 9) {
	output.innerHTML = "Please enter a valid coordinate!";
  } else if (gameGrid[x][y] === 1) {
	output.innerHTML = "You hit a ship!";
	gameGrid[x][y] = 2;
	checkShipSunk(patrolPosition, destroyerPosition, battlePosition, gameGrid, numberSunken);
	console.log(gameGrid);
  } else if (gameGrid[x][y] === 2) {
	output.innerHTML = "You already hit this ship!";
	} else if (gameGrid[x][y] === 3) {
	output.innerHTML = "You already missed this spot!";
  } else {
	output.innerHTML = "You missed!";
	gameGrid[x][y] = 3;
	console.log(gameGrid);
  }
}

function checkShipSunk(patrolPosition, destroyerPosition, battlePosition, gameGrid, numberSunken) {
	  // compair the guess lovation to the ship positions and add 1 hit to the given ship then compair the number of hits to the ship size if they are equal then the ship is sunk
	    for (let i = 0; i < patrolPosition.length; i++) {
		if (gameGrid[patrolPosition[i][0]][patrolPosition[i][1]] === 2) {
			patrolHits++;
		} if (patrolHits === patrolSize) {
			patrolSunk = true;
			numberSunken++;
			shipPatrol.style.backgroundColor = "red";
		} else if (patrolHits < patrolSize) {
			patrolSunk = false;
		}		
	} for (let i = 0; i < destroyerPosition.length; i++) {
		if (gameGrid[destroyerPosition[i][0]][destroyerPosition[i][1]] === 2) {
			destroyerHits++;
		} if (destroyerHits === destroyerSize) {
			destroyerSunk = true;
			numberSunken++;
			shipDestroyer.style.backgroundColor = "red";
		} else if (destroyerHits < destroyerSize) {
			destroyerSunk = false;
		}
	} for (let i = 0; i < battlePosition.length; i++) {
		if (gameGrid[battlePosition[i][0]][battlePosition[i][1]] === 2) {
			battleHits++;
		} if (battleHits === battleSize) {
			battleSunk = true;
			numberSunken++;
			shipBattle.style.backgroundColor = "red";
		} else if (battleHits < battleSize) {
			battleSunk = false;
		}
	} if (numberSunken >= 3) {
		output.innerHTML = "You sunk all the ships! You win!";
		button.innerHTML = "Reset";
		button.addEventListener("click", function() {
			location.reload();
		}, false);
	}
}

//

