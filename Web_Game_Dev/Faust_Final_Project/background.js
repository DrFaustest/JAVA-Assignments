var canvas = document.getElementById('animation');
var context = canvas.getContext('2d');

var numPixels = 1000;
var pixelSize = 0.75;
var pixels = [];

function setup() {
  canvas.width = window.innerWidth;
  canvas.height = window.innerHeight;
  
  for (var i = 0; i < numPixels; i++) {
    var x = Math.floor(Math.random() * canvas.width);
    var y = Math.floor(Math.random() * canvas.height);
    var pixel = { x: x, y: y };
    pixels.push(pixel);
  }
}

function draw() {
  context.clearRect(0, 0, canvas.width, canvas.height);
  context.fillStyle = 'white';
  
  for (var i = 0; i < numPixels; i++) {
    var pixel = pixels[i];
    pixel.y += 2;
    if (pixel.y > canvas.height) {
      pixel.y = 0;
      pixel.x = Math.floor(Math.random() * canvas.width);
    }
    context.fillRect(pixel.x, pixel.y, pixelSize, pixelSize);
  }
}

setup();
setInterval(draw, 50);
