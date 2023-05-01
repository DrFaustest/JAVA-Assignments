//Save the size of the cell
CELL_HEIGHT = 27;
CELL_WIDTH = 37;

class Ship {
	constructor(name, size, row, col, hits) {
	  this.name = name;
	  this.size = size;
	  this.row = row;
	  this.col = col;
	  this.hits = hits;
	}
  
	hit() {
	  this.hits--;
	  if (this.hits === 0) {
		console.log(`${this.name} sunk!`);
	  }
	}
  }

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
//destroyer ship y needs to be at the highest row of 8 so that it will stay on the stage
let destroyerRow = Math.floor(Math.random() * 8);
let destroyerCol = Math.floor(Math.random() * 10);

let battleShip = new Ship("Battleship", 4, battleShipRow, battleShipCol, 4);
let destroyer = new Ship("Destroyer", 3, destroyerRow, destroyerCol, 3);
let patrolShip = new Ship("Patrol Ship", 2, patrolShipRow, patrolShipCol, 2);

function drawShips() {
	ctx.fillStyle = "blue";
	ctx.fillRect(battleShip.col * CELL_WIDTH, battleShip.row * CELL_HEIGHT, CELL_WIDTH, battleShip.size * CELL_HEIGHT);
	ctx.fillRect(destroyer.col * CELL_WIDTH, destroyer.row * CELL_HEIGHT, CELL_WIDTH, destroyer.size * CELL_HEIGHT);
	ctx.fillRect(patrolShip.col * CELL_WIDTH, patrolShip.row * CELL_HEIGHT, CELL_WIDTH, patrolShip.size * CELL_HEIGHT);
  }
  


let canvas = document.getElementById("myCanvas");
canvas.style.display = "none";
let ctx = canvas.getContext("2d");

render();

let explosion = new Image();
//explosion.addEventListener("load", loadHandler, false);
explosion.src = "Images/exp2.png";

function shipHitAnimation() {
    explosionObject.animation();
}

let explosionObject = {
	visibility: "none",
	frame: [
	  [187.5, 187.5, 62.5, 62.5], [125, 187.5, 62.5, 62.5], [62.5, 187.5, 62.5, 62.5], [0, 187.5, 62.5, 62.5],
	  [187.5, 125, 62.5, 62.5], [125, 125, 62.5, 62.5], [62.5, 125, 62.5, 62.5], [0, 125, 62.5, 62.5],
	  [187.5, 62.5, 62.5, 62.5], [125, 62.5, 62.5, 62.5], [62.5, 62.5, 62.5, 62.5], [0, 62.5, 62.5, 62.5],  
	  [187.5, 0, 62.5, 62.5], [125, 0, 62.5, 62.5], [62.5, 0, 62.5, 62.5], [0, 0, 62.5, 62.5]
	],
	currentFrame: 0,
	frameIncrement: 1,
	animation: function() {
		canvas.style.display = "block";
		animate();
		function animate() {
		  ctx.clearRect(0, 0, canvas.width, canvas.height);
		  ctx.drawImage(
			explosion,
			explosionObject.frame[explosionObject.currentFrame][0], explosionObject.frame[explosionObject.currentFrame][1],
			explosionObject.frame[explosionObject.currentFrame][2], explosionObject.frame[explosionObject.currentFrame][3],
			0, 0, 62.5, 62.5
		  );
		  explosionObject.currentFrame += explosionObject.frameIncrement;
		  if (explosionObject.currentFrame < 15) {
			// Set a timer to control the speed of the animation
			setTimeout(animate, 100); // 100 milliseconds between frames
		  } else {
			// end the animation and hide the canvas
			canvas.style.display = "none";
			explosionObject.currentFrame = 0;
			console.log("Animation finished");
		  }
		}
	  }
	  
  };
  
  
  
    
    


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
	
	let ship = map[guessRow][guessColumnNumber];

  if (ship != 0) {
    if (ship === 1) {
      battleShip.hit();
      shipHitAnimation();
      output.innerHTML = `You hit my ${battleShip.name}. There are ${battleShip.hits} hits left.`;
    } else if (ship === 2) {
      destroyer.hit();
      shipHitAnimation();
      output.innerHTML = `You hit my ${destroyer.name}. There are ${destroyer.hits} hits left.`;
    } else if (ship === 3) {
      patrolShip.hit();
      shipHitAnimation();
      output.innerHTML = `You hit my ${patrolShip.name}. There are ${patrolShip.hits} hits left.`;
    }

    if (battleShip.hits === 0 && destroyer.hits === 0 && patrolShip.hits === 0) {
      gameWon = true;
      endGame();
    }
  } else {
    output.innerHTML = "Miss!";
  }
}

function endGame() {
	if (gameWon) {
	  output.innerHTML =
		"You sunk all of my ships! It took you " + guesses + " guesses";
	  render();
	}
  }
  

function render() {
	drawShips();
  }
  


  

  