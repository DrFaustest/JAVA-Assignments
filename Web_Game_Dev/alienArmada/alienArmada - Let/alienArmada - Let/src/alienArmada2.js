(function(){

//The canvas
let canvas = document.querySelector("canvas");

//Create the drawing surface 
let drawingSurface = canvas.getContext("2d");

//Arrays to store the game objects and assets to load
let sprites = [];
let assetsToLoad = [];
let missiles = [];

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
let SPACE = 32;

//Directions
let moveRight = false;
let moveLeft = false;

//variables to help fire missiles
let shoot = false;
let spaceKeyIsDown = false;

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
	    break;
	 
	  case SPACE:
	    if(!spaceKeyIsDown)
	    {
	      shoot = true;
	      spaceKeyIsDown = true;
	    }
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
	    break; 
	
	  case SPACE:
	    spaceKeyIsDown = false;
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

  //Fire a missile if shoot is true
  if(shoot)
  {
    fireMissile();
    shoot = false;	
  }
  
  //Move the cannon and keep it within the screen boundaries
  cannon.x = Math.max(0, Math.min(cannon.x + cannon.vx, canvas.width - cannon.width));
  
  //Move the missiles
  for(let i = 0; i < missiles.length; i++)
  {
    let missile = missiles[i];

    //Move it up the screen
    missile.y += missile.vy;

    //Remove the missile if it crosses the top of the screen
    if(missile.y < 0 - missile.height)
    { 
      //Remove the missile from the missiles array
      removeObject(missile, missiles);

      //Remove the missile from the sprites array
      removeObject(missile, sprites);

      //Reduce the loop counter by 1 to compensate 
      //for the removed element
      i--;
    }
  }
}

function fireMissile()
{ 
  //Create a missile sprite
  let missile = Object.create(spriteObject);
  missile.sourceX = 96;
  missile.sourceWidth = 16;
  missile.sourceHeight = 16;
  missile.width = 16;
  missile.height = 16;
  
  //Center it over the cannon
  missile.x = cannon.centerX() - missile.halfWidth();
  missile.y = cannon.y - missile.height;
  
  //Set its speed
  missile.vy = -8;
  
  //Push the missile into both the sprites and missiles arrays
  sprites.push(missile);
  missiles.push(missile);
}

function removeObject(objectToRemove, array) 
{ 
  let i = array.indexOf(objectToRemove);
  if (i !== -1)
  {
    array.splice(i, 1);
  }
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
