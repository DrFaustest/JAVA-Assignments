
let enterButton = document.querySelector("#enter");
enterButton.addEventListener("click", enterClickHandler, false);

let helloButton = document.querySelector("#hello");
helloButton.addEventListener("click", helloClickHandler, false);

let output = document.querySelector("#output");
let input = document.querySelector("#input");

function enterClickHandler() {
  output.innerHTML = inputText.value;
}

function helloClickHandler() {
    output.innerHTML = `Hello ${inputText.value}`;
    }
