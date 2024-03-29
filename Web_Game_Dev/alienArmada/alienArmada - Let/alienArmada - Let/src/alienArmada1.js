(function(){

//The canvas
let canvas = document.querySelector("canvas");

//Create the drawing surface 
let drawingSurface = canvas.getContext("2d");

//Arrays to store the game objects and assets to load
let sprites = [];
let assetsToLoad = [];

//Create the background
let background = Object.create(spriteObject);
background.x = 0;
background.y = 0;
background.sourceY = 32;
background.sourceWidth = 480;
background.sourceHeight = 320;
background.width = 480;
background.height = 320;
sprites.push(background);

//Create the cannon and center it
let cannon = Object.create(spriteObject);
cannon.x = canvas.width / 2 - cannon.width / 2;
cannon.y = 280;
sprites.push(cannon);

//Load the tilesheet image
let image = new Image();
image.addEventListener("load", loadHandler, false);
image.src = "../images/alienArmada.png";
assetsToLoad.push(image);

//Variable to count the number of assets the game needs to load
let assetsLoaded = 0;

//Game states
let LOADING = 0
let PLAYING = 1;
let OVER = 2;
let gameState = LOADING;

//Arrow key codes
let RIGHT = 39;
let LEFT = 37;

//Directions
let moveRight = false;
let moveLeft = false;

//Add keyboard listeners
window.addEventListener("keydown", function(event)
{
  switch(event.keyCode)
  {
	  case LEFT:
	    moveLeft = true;
	    break;  
	    
	  case RIGHT:
	    moveRight = true;
  }
}, false);

window.addEventListener("keyup", function(event)
{
  switch(event.keyCode)
  {	    
	  case LEFT:
	    moveLeft = false;
	    break;  
	    
	  case RIGHT:
	    moveRight = false;
  }
}, false);

//Start the game animation loop
update();

function update()
{ 
  //The animation loop
  requestAnimationFrame(update, canvas);
  
  //Change what the game is doing based on the game state
  switch(gameState)
  {
    case LOADING:
      console.log("loading…");
      break;
    
    case PLAYING:
      playGame();
      break;
    
    case OVER:
      endGame();
      break;
  }
  
  //Render the game
  render();
}

function loadHandler()
{ 
  assetsLoaded++;
  if(assetsLoaded === assetsToLoad.length)
  {
    //Remove the load event listener
    image.removeEventListener("load", loadHandler, false);
         
    //Start the game 
    gameState = PLAYING;
  }
}

function playGame()
{
  //Left
  if(moveLeft && !moveRight)
  {
    cannon.vx = -8;
  }
  //Right
  if(moveRight && !moveLeft)
  {
    cannon.vx = 8;
  }

  //Set the cannon's velocity to zero if none of the keys are being pressed
  if(!moveLeft && !moveRight)
  {
    cannon.vx = 0;
  }
  
  //Move the cannon and keep it within the screen boundaries
  cannon.x = Math.max(0, Math.min(cannon.x + cannon.vx, canvas.width - cannon.width));
}

function endGame()
{
  //Empty for now
}

function render()
{ 
  drawingSurface.clearRect(0, 0, canvas.width, canvas.height);
  
  //Display the sprites
  if(sprites.length !== 0)
  {
    for(let i = 0; i < sprites.length; i++)
    {
      let sprite = sprites[i];
      drawingSurface.drawImage
      (
        image, 
        sprite.sourceX, sprite.sourceY, 
        sprite.sourceWidth, sprite.sourceHeight,
        Math.floor(sprite.x), Math.floor(sprite.y), 
        sprite.width, sprite.height
      ); 
    }
  }
}

}());
