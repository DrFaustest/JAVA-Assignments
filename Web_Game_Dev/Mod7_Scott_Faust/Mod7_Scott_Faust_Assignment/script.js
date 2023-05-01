const canvas = document.getElementById('ballCanvas');
const ctx = canvas.getContext('2d');
const increaseSpeedBtn = document.getElementById('increaseSpeed');
const decreaseSpeedBtn = document.getElementById('decreaseSpeed');
const increaseSizeBtn = document.getElementById('increaseSize');
const decreaseSizeBtn = document.getElementById('decreaseSize');
const randomColorBtn = document.getElementById('randomColor');

let x = canvas.width / 2;
let y = canvas.height / 2;
let ballRadius = 10;
let dx = 2;
let dy = -2;
let ballColor = '#0095DD';

function drawBall() {
    ctx.beginPath();
    ctx.arc(x, y, ballRadius, 0, Math.PI * 2);
    ctx.fillStyle = ballColor;
    ctx.fill();
    ctx.closePath();
}

function updateBallPosition() {
    ctx.clearRect(0, 0, canvas.width, canvas.height);
    drawBall();
    x += dx;
    y += dy;

    if (x + dx > canvas.width - ballRadius || x + dx < ballRadius) {
        dx = -dx;
    }
    if (y + dy > canvas.height - ballRadius || y + dy < ballRadius) {
        dy = -dy;
    }
}

function increaseSpeed() {
    dx *= 1.1;
    dy *= 1.1;
}

function decreaseSpeed() {
    dx *= 0.9;
    dy *= 0.9;
}

function increaseSize() {
    ballRadius += 2;
}

function decreaseSize() {
    if (ballRadius > 2) {
        ballRadius -= 2;
    }
}

function randomColor() {
    const random = () => Math.floor(Math.random() * 256);
    ballColor = `rgb(${random()}, ${random()}, ${random()})`;
}

increaseSpeedBtn.addEventListener('click', increaseSpeed);
decreaseSpeedBtn.addEventListener('click', decreaseSpeed);
increaseSizeBtn.addEventListener('click', increaseSize);
decreaseSizeBtn.addEventListener('click', decreaseSize);
randomColorBtn.addEventListener('click', randomColor);

setInterval(updateBallPosition, 10);
