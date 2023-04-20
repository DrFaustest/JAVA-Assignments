//Save the size of the cell
CELL_HEIGHT = 27;
CELL_WIDTH = 37;

//Variables for the number of hits of each ship type
let battleShipHits = 4;
let destroyerHits = 3;
let patrolShipHits = 2;

let stage = document.querySelector("#stage");

//Create a random location for the different Ship's x and y position
//patrol ship y needs to be at the highest row of 9 so that it will stay on the stage
let patrolShipRow = Math.floor(Math.random() * 9);
let patrolShipCol = Math.floor(Math.random() * 10);
//battle ship y needs to be at the highest row of 7 so that it will stay on the stage
let battleShipRow = Math.floor(Math.random() * 7);
let battleShipCol = Math.floor(Math.random() * 10);
//check to make sure the battleship and patrol ship are not overlapping
if(battleShipRow >= patrolShipRow && battleShipRow <= patrolShipRow + 2)
{
	if(battleShipCol === patrolShipCol)
	{
		//if this is true then they overlap and we need to get a new random location
		battleShipRow = Math.floor(Math.random() * 7);
		battleShipCol = Math.floor(Math.random() * 10);
	}
}
//destroyer ship y needs to be at the highest row of 8 so that it will stay on the stage
let destroyerRow = Math.floor(Math.random() * 8);
let destroyerCol = Math.floor(Math.random() * 10);

//check to make sure the destroyer does not overlap the other two ships
if(destroyerRow >= patrolShipRow && destroyerRow <= patrolShipRow + 2)
{
	if(destroyerCol === patrolShipCol)
	{
		destroyerRow = Math.floor(Math.random() * 8);
		destroyerCol = Math.floor(Math.random() * 10);
	}
}

if(destroyerRow >= battleShipRow && destroyerRow <= battleShipRow + 4)
{
	if(destroyerCol === battleShipCol)
	{
		destroyerRow = Math.floor(Math.random() * 8);
		destroyerCol = Math.floor(Math.random() * 10);
	}
}

//set the map up with initially all zeroes in the cells
let map =
[
	[0, 0, 0, 0, 0, 0, 0, 0, 0, 0],
	[0, 0, 0, 0, 0, 0, 0, 0, 0, 0],
	[0, 0, 0, 0, 0, 0, 0, 0, 0, 0],
	[0, 0, 0, 0, 0, 0, 0, 0, 0, 0],
	[0, 0, 0, 0, 0, 0, 0, 0, 0, 0],
	[0, 0, 0, 0, 0, 0, 0, 0, 0, 0],
	[0, 0, 0, 0, 0, 0, 0, 0, 0, 0],
	[0, 0, 0, 0, 0, 0, 0, 0, 0, 0],
	[0, 0, 0, 0, 0, 0, 0, 0, 0, 0],
	[0, 0, 0, 0, 0, 0, 0, 0, 0, 0]
];

//place the ships in the map using 1 for battleship, 2 for destroyer, and 3 for patrol ship
map[battleShipRow][battleShipCol] = 1;
map[battleShipRow + 1][battleShipCol] = 1;
map[battleShipRow + 2][battleShipCol] = 1;
map[battleShipRow + 3][battleShipCol] = 1;

map[destroyerRow][destroyerCol] = 2;
map[destroyerRow + 1][destroyerCol] = 2;
map[destroyerRow + 2][destroyerCol] = 2;

map[patrolShipRow][patrolShipCol] = 3;
map[patrolShipRow + 1][patrolShipCol] = 3;

let mapOutput = "";
for(let row = 0; row < map.length; row++)
{
	for(let col = 0; col < map[0].length; col++)
	{
		mapOutput += (map[row][col]);
	}
	mapOutput += "\n";
}

console.log(mapOutput);

/*
need to locate the x and y location of the image, using the 
height and width of the cells from the background image
*/
let patrolShipY = patrolShipRow * CELL_HEIGHT + CELL_HEIGHT;
let patrolShipX = patrolShipCol * CELL_WIDTH + CELL_WIDTH;

let battleShipX = battleShipCol * CELL_WIDTH + CELL_WIDTH;
let battleShipY = battleShipRow * CELL_HEIGHT + CELL_HEIGHT;

let destroyerY = destroyerRow * CELL_HEIGHT + CELL_HEIGHT;
let destroyerX = destroyerCol * CELL_WIDTH + CELL_WIDTH;

//variables for the user's guess
let guessRow = 0;
let guessColumn = "";

//variable to keep track of guesses
let guesses = 0;

//game state variable
let gameWon = false;

//Game object variables
let patrolShip = document.createElement("img");
let battleShip = document.createElement("img");
let destroyer = document.createElement("img");

patrolShip.setAttribute("class", "patrolShip");
stage.appendChild(patrolShip);
battleShip.setAttribute("class", "battleShip");
stage.appendChild(battleShip);
destroyer.setAttribute("class", "destroyer");
stage.appendChild(destroyer);
//set the location of the ship img tags on the screen
patrolShip.style.top = patrolShipY + "px";
patrolShip.style.left = patrolShipX + "px";
battleShip.style.top = battleShipY + "px";
battleShip.style.left = battleShipX + "px";
destroyer.style.top = destroyerY + "px";
destroyer.style.left = destroyerX + "px";


//Input and output variables
let inputX = document.querySelector("#inputX");
let inputY = document.querySelector("#inputY");
let output = document.querySelector("#output");

var button = document.querySelector("#fire");
button.style.cursor = "pointer";
button.addEventListener("click", clickHandler, false);

function clickHandler()
{
  validateInput();
}

function validateInput()
{
  guessColumn = inputY.value;
  guessRow = parseInt(inputX.value);
  
  if(isNaN(guessRow))
  {
    output.innerHTML = "Please enter a number for the row.";
  }
  else if(guessRow < 1 || guessRow > 10)
  {
    output.innerHTML = "Please enter a number less than 10.";
  }
  else if(guessColumn < "A" || guessColumn > "J")
  {
	  output.innerHTML = "Please enter a column between A and J.";
  }
  else
  {
    playGame();
  }
}

function playGame()
{
	guesses++;
	//subtract one from the guessRow, so that it stays within the 2D array index limit
	guessRow--;
	//Translate guessColumn into a number for the 2D array index
	let guessColumnNumber = 0;
	switch(guessColumn)
	{
		case "A":
			guessColumnNumber = 0;
			break;
		case "B":
			guessColumnNumber = 1;
			break;
		case "C":
			guessColumnNumber = 2;
			break;
		case "D":
			guessColumnNumber = 3;
			break;
		case "E":
			guessColumnNumber = 4;
			break;
		case "F":
			guessColumnNumber = 5;
			break;
		case "G":
			guessColumnNumber = 6;
			break;
		case "H":
			guessColumnNumber = 7;
			break;
		case "I":
			guessColumnNumber = 8;
			break;
		case "J":
			guessColumnNumber = 9;
			break;
		default:
			output.innerHTML = "Column guess not recognized, please guess again.";
	}
	
	//if(guessRow >= patrolShipX && guessRow <= patrolShipX + 13)
	if(map[guessRow][guessColumnNumber] != 0)
	{
		//There is a ship in this cell
		//Determine which ship was hit
		let shipHit = "";
		if(map[guessRow][guessColumnNumber] === 1)
		{
			//Hit the battleship, subtract the number of hits from the battleship
			battleShipHits--;
			shipHit = "Battleship";
			output.innerHTML = "You hit my " + shipHit + ". There are " + battleShipHits + " hits left.";
		}else if(map[guessRow][guessColumnNumber] === 2)
		{
			//Hit the destroyer, subtract the number of hits from the destroyer
			destroyerHits--;
			shipHit = "Destroyer";
			output.innerHTML = "You hit my " + shipHit + ". There are " + destroyerHits + " hits left.";
		}else if(map[guessRow][guessColumnNumber] === 3)
		{
			//Hit the patrolShip, subtract the number of hits from the patrolShip
			patrolShipHits--;
			shipHit = "Patrolship";
			output.innerHTML = "You hit my " + shipHit + ". There are " + patrolShipHits + " hits left.";
		}
		
		if(battleShipHits === 0 && destroyerHits === 0 && patrolShipHits === 0)
			{
				gameWon = true;
				endGame();
			}
	}
	else
	{
		output.innerHTML = "Miss!" 
	}
}

function endGame()
{
	patrolShip.style.visibility = "visible";
	if(gameWon)
	{
		output.innerHTML = "You sunk all of my ships! It took you " + guesses + " guesses";
		render();
	}
}

function render()
{
	patrolShip.style.visibility = "visible";
	battleShip.style.visibility = "visible";
	destroyer.style.visibility = "visible";
}