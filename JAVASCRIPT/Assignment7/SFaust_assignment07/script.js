"use strict";
/*
Scott Faust
SFaust_assignment07
INFO_2124_WW
Yi
1/31/2023
*/

//DO NOT MODIFY THE CODE BELOW
//wait for everything to load before executing this here code
$(document).ready(() => {
  // create a new constant to hold a date reference for the current moment
  const dt = new Date();
  //Get the current year from the date reference, and put it
  //in the yield field in the footer.
  $("#year").text(dt.getFullYear());
});
//ADD YOUR CODE BELOW
const listArray = []; // create an empty array to hold the list items
const listArrayCaps = []; // create an empty array to hold the list items in all caps for comparison

function buildList() { // take the listArray and build the html <li>
  let listHTML = "";
  for (let i = 0; i < listArray.length; i++) {
    listHTML += `<li>${listArray[i]}</li>`;
  }
  $("#listItemsHolder").html(listHTML);
}

function clearList() { //clear the list and build it
    listArray.length = 0;
    listArrayCaps.length = 0;
    buildList();
}

function verrifyInput() { // check to see if the input is valid
    const itemToAdd = $("#listItemInput").val().trim();
    if (listArray.length >= 6) { // check to see if the list is full
      alert(
        "Error! Franz liszt's list is full. Franz liszt's list can only hold 6 items."
      );
    } else if (itemToAdd === "") { // check to see if the input is empty
      alert(
        "Error! Franz liszt's list item cannot be empty. This is unacceptable. Franz liszt demands you correct his list!."
      );
    } else if (listArrayCaps.includes(itemToAdd.toUpperCase())) { // check to see if the input is a duplicate
      alert("Error! You are attempting to enter a duplicate value.");
    } else { // add the item to the list and build it
        listArray.push(itemToAdd);
        listArrayCaps.push(itemToAdd.toUpperCase());
    }
}

function refocusInput() { // refocus the input
    $("#listItemInput").val("").focus(); // clear the input and focus it
}

$(document).ready(() => {
  $("#addItemToList").click(() => {
    verrifyInput();
    buildList();
    refocusInput();
  });

  $("#clearList").click(() => { // clear the list and build it
    clearList();
    refocusInput();
  });
});
