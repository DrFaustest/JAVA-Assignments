
//Create the map
let map = [];

map[0] = "An old stone keep.";
map[1] = "A deep well.";
map[2] = "A sunny glade.";
map[3] = "A sleeping dragon.";
map[4] = "A narrow pathway.";
map[5] = "An ancient gate.";
map[6] = "The edge of a river.";
map[7] = "A lonely wooden bench.";
map[8] = "An isolated cottage. Faint music comes from inside.";
map[9] = "A dark cave.";
map[10] = "A damp swamp.";
map[11] = "A vast forest.";
map[12] = "A tall mountain.";
map[13] = "A deep valley.";
map[14] = "A bleek moor.";
map[15] = "A small village.";

//Set the player's start location
let mapLocation = 4;

//Set the images
let images = [];

images[0] = "images/keep.png";
images[1] = "images/well.png";
images[2] = "images/glade.png";
images[3] = "images/dragon.png";
images[4] = "images/path.png";
images[5] = "images/gate.png";
images[6] = "images/river.png";
images[7] = "images/bench.png";
images[8] = "images/cottage.png";
images[9] = "images/cave.jpg"; //need to add image
images[10] = "images/swamp.jpg"; //need to add image
images[11] = "images/forest.jpg"; //need to add image
images[12] = "images/mountain.jpg"; //need to add image
images[13] = "images/valley.jpg"; //need to add image
images[14] = "images/moor.jpg"; //need to add image
images[15] = "images/village.jpg"; //need to add image

//Set the blocked path messages
let blockedPathMessages = [];

blockedPathMessages[0] = "It's too dangerous to move that way.";
blockedPathMessages[1] = "A mysterious force holds you back.";
blockedPathMessages[2] = "A tangle of thorns blocks your way.";
blockedPathMessages[3] = "You can't step over the dragon.";
blockedPathMessages[4] = "Thick vines block your path.";
blockedPathMessages[5] = "";
blockedPathMessages[6] = "";
blockedPathMessages[7] = "The trees are too thick to pass.";
blockedPathMessages[8] = "You're too scared to go that way.";
blockedPathMessages[9] = "";
blockedPathMessages[10] = "";
blockedPathMessages[11] = "The forest is too dense to pass.";
blockedPathMessages[12] = "The mountain is too steep to climb.";
blockedPathMessages[13] = "The valley is covered in a dense fog.";
blockedPathMessages[14] = "The moor is vailed in a dark presence.";
blockedPathMessages[15] = "The village nesseled deep in a ravine.";

//Set the blocked path messages
let helpMessages = [];

helpMessages[0] = "";
helpMessages[1] = "I wonder if you could 'use' something to find out how deep the well is?";
helpMessages[2] = "";
helpMessages[3] = "Maybe if you had a sword, you could slay the dragon?";
helpMessages[4] = "";
helpMessages[5] = "";
helpMessages[6] = "";
helpMessages[7] = "";
helpMessages[8] = "This seems like a nice place for music.";

//Create the objects and set their locations
let items = ["stone"];
let itemLocations = [6];

//An array to store what the player is carrying
let backpack = [];

//Initialize the player's score
let score = 0;

//Initialize the player's input
let playersInput = "";

//Initialize the gameMessage
let gameMessage = "<br>Welcome to Lyrica! "
gameMessage += "Try any of these words: " 
gameMessage += "north, east, south, west, take, drop, ";
gameMessage += "use, stone, flute, sword, help.";

//Create an array of actions the game understands
//and a variable to store the current action
let actionsIKnow 
  = ["north","east", "south", "west", 
     "help", "take", "use", "drop"];
let action = "";

//An array of items the game understands
//and a variable to store the current item
let itemsIKnow = ["flute", "stone", "sword"];
let item = "";

//The img element
let image = document.querySelector("img");

//The input and output fields
let output = document.querySelector("#output");
let input = document.querySelector("#input");

//The button
let button = document.querySelector("button");
button.style.cursor = "pointer";
button.addEventListener("click", clickHandler, false);
button.addEventListener("mousedown", mousedownHandler, false);
button.addEventListener("mouseout", mouseoutHandler, false);

//Listen for enter key presses
window.addEventListener("keydown", keydownHandler, false);

//Dispay the player's location
render();

function mousedownHandler()
{
 button.style.background 
   = "-webkit-linear-gradient(top, rgba(0,0,0,0.2), rgba(255,255,255,0.3))";
 button.style.background 
   = "-moz-linear-gradient(top, rgba(0,0,0,0.2), rgba(255,255,255,0.3))";
 button.style.background 
   = "linear-gradient(top, rgba(0,0,0,0.2), rgba(255,255,255,0.3))";
}

function mouseoutHandler()
{
 button.style.background 
   = "-webkit-linear-gradient(top, rgba(255,255,255,0.6), rgba(0,0,0,0.2))";
 button.style.background 
   = "-moz-linear-gradient(top, rgba(255,255,255,0.6), rgba(0,0,0,0.2))";
 button.style.background 
   = "linear-gradient(top, rgba(255,255,255,0.6), rgba(0,0,0,0.2))";
}

function clickHandler()
{
  button.style.background 
   = "-webkit-linear-gradient(top, rgba(255,255,255,0.6), rgba(0,0,0,0.2))";
  button.style.background 
   = "-moz-linear-gradient(top, rgba(255,255,255,0.6), rgba(0,0,0,0.2))";
  button.style.background 
   = "linear-gradient(top, rgba(255,255,255,0.6), rgba(0,0,0,0.2))";
   
  playGame();
}

function keydownHandler(event)
{

  if(event.keyCode === 13)
  {
    playGame();
  }
}

function playGame()
{
  //Get the player's input and convert it to lowercase
  playersInput = input.value;
  playersInput = playersInput.toLowerCase();
  
  //Reset these variables from the previous turn
  gameMessage = "";
  action = "";
  
  //Figure out the player's action
  for(i = 0; i < actionsIKnow.length; i++)
  {
    if(playersInput.indexOf(actionsIKnow[i]) !== -1)
    {
      action = actionsIKnow[i];
      console.log("player's action: " + action);
      break;
    }
  }
  
  //Figure out the item the player wants
  for(i = 0; i < itemsIKnow.length; i++)
  {
    if(playersInput.indexOf(itemsIKnow[i]) !== -1)
    {
      item = itemsIKnow[i];
      console.log("player's item: " + item);
    }
  }
  
  //Choose the correct action
  switch(action)
  {
    case "north":
      if(mapLocation >= 4)
      {
        mapLocation -= 4;
      }
      else
      {
        gameMessage = blockedPathMessages[mapLocation];
      }
      break;
    
    case "east":
	    if(mapLocation % 4 != 3)
      {
        mapLocation += 1;
      }
      else
      {
        gameMessage = blockedPathMessages[mapLocation];
      }
      break;
      
    case "south":
      if(mapLocation < 11)
      {
        mapLocation += 4;
      }
      else
      {
        gameMessage = blockedPathMessages[mapLocation];
      }
      break;
      
    case "west":
      if(mapLocation % 4 != 0)
      {
        mapLocation -= 1;
      }
      else
      {
        gameMessage = blockedPathMessages[mapLocation];
      }
      break;
      
    case "help":
      //Display a hint if there is one for this location
      if(helpMessages[mapLocation] !== "")
      {
        gameMessage = helpMessages[mapLocation] + " ";
      }
      gameMessage += "Try any of these words: " 
      gameMessage += "north, east, south, west, take, drop, ";
      gameMessage += "use, stone, flute, sword.";
      break;
      
    case "take":
      takeItem()
		  break;
		
		case "drop":
		  dropItem();
		  break;
		  
		case "use":
		  useItem();
		  break;
    		  
		default:
		  gameMessage = "I don't understand that.";
  }
  
  //Render the game
  render();
}

function takeItem()
{
  //Find the index number of the item in the items array
  let itemIndexNumber = items.indexOf(item);
  
  //Does the item exist in the game world
  //and is it at the player's current location?
  if(itemIndexNumber !== -1 
  && itemLocations[itemIndexNumber] === mapLocation)
  {
    gameMessage = "You take the " + item + ".";
    score += 10;
    
    //Add the item to the player's backpack 
    backpack.push(item);
   
    //Remove the item from the game world
    items.splice(itemIndexNumber, 1);
    itemLocations.splice(itemIndexNumber, 1);
          
    //Display in the console for testing
    console.log("World items: " + items);
    console.log("backpack items: " + backpack);
  }
  else
  {
    //Message if you try and take an item
    //that isn't in the current location
    gameMessage = "You can't do that.";
  }
}

function dropItem()
{
  //Try to drop the item only if the backpack isn't empty
  if(backpack.length !== 0)
  {
    //Find the item's array index number in the backpack
    let backpackIndexNumber = backpack.indexOf(item);
	  
	  //The item is in the backpack if backpackIndex number isn't -1
    if(backpackIndexNumber !== -1)
    {
    
     //Tell the player that the item has been dropped
   	 gameMessage = "You drop the " + item + ".";
     
     //Add the item from the backpack to the game world 
     items.push(backpack[backpackIndexNumber]);
     itemLocations.push(mapLocation); 
     
     //Remove the item from the player's backpack 
     backpack.splice(backpackIndexNumber, 1);
    }
    else
    {
      //Message if the player tries to drop
      //something that's not in the backpack
      gameMessage = "You can't do that.";
    }
  }
  else
  {
    //Message if the backpack is empty
    gameMessage = "You're not carrying anything.";
  }
}

function useItem()
{
  //1. Find out if the item is in the backpack
  
  //Find the item's array index number in the backpack
  let backpackIndexNumber = backpack.indexOf(item);
       
  //If the index number is -1, then it isn't in the backpack.
  //Tell the player that he or she isn't carrying it.
  if(backpackIndexNumber === -1)
  {
    gameMessage = "You're not carrying it.";
  }
  
  //If there are no items in the backpack, then
  //tell the player the backpack is empty
  if(backpack.length === 0)
  {
    gameMessage += " Your backpack is empty";
  }
   
  //2. If the item is found in the backpack
  //figure out what to do with it
  if(backpackIndexNumber !== -1)
  {
    switch(item)
    {
	    case "flute":
	      if(mapLocation === 8)
        {
          gameMessage = "Beautiful music fills the air.";
          gameMessage += "A wizend old man steps outside " 
          gameMessage += "and hands you a sword!";
          
          //Add the sword to the world
          items.push("sword");
          itemLocations.push(mapLocation);
          
          //Reset the location's help message
          helpMessages[mapLocation] = "";
        }
        else
        {
          gameMessage = "You try and play the flute " 
          gameMessage += "but it makes no sound here.";
        }
	      break;
	      
	    case "sword":
	      if(mapLocation === 3)
        {
          gameMessage 
            = "You swing the sword and slay the dragon! ";
          score += 50;
          gameMessage 
            += "You've saved the forest of Lyrica! \n score: " + score;
          
          //Reset the location's help message
          helpMessages[mapLocation] = "";  
        }
        else
        {
          gameMessage 
            = "You swing the sword listlessly.";
        }
	      break;
	      
	    case "stone":
	      if(mapLocation === 1)
	      {
	        gameMessage = "You drop the stone in the well.";
	        gameMessage += " A magical flute appears!";
	        
	        //Remove the stone from the player's backpack 
          backpack.splice(backpackIndexNumber, 1);
          
          //Add the flute to the world
	        items.push("flute");
	        itemLocations.push(mapLocation);
	        
	        //Reset the location's help message
          helpMessages[mapLocation] = "";
	      }
        else
        {
	        gameMessage 
	          = "You fumble with the stone in your pocket.";
	      }
	      break;			          
	   }
   }
}

function render()
{
  //Render the location
  output.innerHTML = map[mapLocation];
  image.src = images[mapLocation];
  
  //Display an item if there's one in this location
  //1. Loop through all the game items
  for(let i = 0; i < items.length; i++)
  {
   //Find out if there's an item at this location
   if(mapLocation === itemLocations[i])
   {
     //Display it
     output.innerHTML 
      += "<br>You see a <strong>" 
      + items[i]
      + "</strong> here.";
   }
  }
  
  //Display the player's backpack contents
  if(backpack.length !== 0)
  {
    output.innerHTML += "<br>You are carrying: " + backpack.join(", ");  
  }
  
  //Display the game message
  output.innerHTML += "<br><em>" + gameMessage + "</em>";
  
  //Clear the input field
  input.value = "";
}
