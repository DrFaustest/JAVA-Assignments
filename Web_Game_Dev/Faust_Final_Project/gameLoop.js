window.addEventListener("DOMContentLoaded", function () {
  // Background Animation
  var backgroundCanvas = document.getElementById("background");
  var backgroundContext = backgroundCanvas.getContext("2d");

  var numPixels = 1000;
  var pixelSize = 1;
  var pixels = [];
  var gameCanvas = document.getElementById("gameCanvas");
  var gameContext = gameCanvas.getContext("2d");
  gameCanvas.width = window.innerWidth;
  gameCanvas.height = window.innerHeight;
  const ship = { x: gameCanvas.width / 2, y: gameCanvas.height - 30, size: 20 };
  const bullets = [];
  const enemies = [];
  let gameOver = false;
  const keys = {};

  function backgroundSetup() {
    backgroundCanvas.width = window.innerWidth;
    backgroundCanvas.height = window.innerHeight;

    for (var i = 0; i < numPixels; i++) {
      var x = Math.floor(Math.random() * backgroundCanvas.width);
      var y = Math.floor(Math.random() * backgroundCanvas.height);
      var pixel = { x: x, y: y };
      pixels.push(pixel);
    }
  }

  function backgroundDraw() {
    backgroundContext.clearRect(
      0,
      0,
      backgroundCanvas.width,
      backgroundCanvas.height
    );
    backgroundContext.fillStyle = "white";

    for (var i = 0; i < numPixels; i++) {
      var pixel = pixels[i];
      pixel.y += 2;
      if (pixel.y > backgroundCanvas.height) {
        pixel.y = 0;
        pixel.x = Math.floor(Math.random() * backgroundCanvas.width);
      }
      backgroundContext.fillRect(pixel.x, pixel.y, pixelSize, pixelSize);
    }
  }

  backgroundSetup();
  setInterval(backgroundDraw, 50);

  // Game Logic


  // Define score and startTime variables here
  let score = 0;
  let startTime = null;

  // Define gameStarted variable here
  let gameStarted = false;

  function drawShip() {
    gameContext.beginPath();
    gameContext.moveTo(ship.x, ship.y);
    gameContext.lineTo(ship.x - ship.size / 2, ship.y + ship.size);
    gameContext.lineTo(ship.x + ship.size / 2, ship.y + ship.size);
    gameContext.closePath();
    gameContext.fillStyle = "white";
    gameContext.fill();
  }

  function drawBullet(bullet) {
    gameContext.beginPath();
    gameContext.arc(bullet.x, bullet.y, 5, 0, Math.PI * 2);
    gameContext.fillStyle = "white";
    gameContext.fill();
  }

  function drawEnemy(enemy) {
    gameContext.beginPath();
    gameContext.arc(enemy.x, enemy.y, enemy.size, 0, Math.PI * 2);
    gameContext.fillStyle = "red";
    gameContext.fill();
  }

  function update() {
    updateScore();
    updateClock();
    // Move ship with arrow keys
    if (keys.left) {
      ship.x -= 5;
      if (ship.x < 0) ship.x = 0;
    } else if (keys.right) {
      ship.x += 5;
      if (ship.x > gameCanvas.width) ship.x = gameCanvas.width;
    }

    bullets.forEach((bullet, index) => {
      bullet.y -= 5;
      if (bullet.y < 0) bullets.splice(index, 1);
    });

    enemies.forEach((enemy, index) => {
      enemy.y += 1;
      if (enemy.y + enemy.size > gameCanvas.height) gameOver = true;

      bullets.forEach((bullet, bulletIndex) => {
        const dist = Math.hypot(bullet.x - enemy.x, bullet.y - enemy.y);
        if (dist - enemy.size - 5 < 1) {
          score += 1;
          enemies.splice(index, 1);
          bullets.splice(bulletIndex, 1);
        }
      });
    });

    if (Math.random() < 0.01) {
      const size = 20;
      const x = Math.random() * (gameCanvas.width - size * 2) + size;
      enemies.push({ x, y: 0, size });
    }

    // Start game with spacebar
    if (!gameStarted && keys[" "]) {
      gameStarted = true;
      startTime = Date.now();
    }

    // Reset game with spacebar if game over
    if (gameOver && keys[" "]) {
      location.reload();
    }
  }

  function draw() {
    gameContext.clearRect(0, 0, gameCanvas.width, gameCanvas.height);
    if (!gameStarted) {
      drawStartScreen();
    } else {
      drawShip();
      bullets.forEach(drawBullet);
      enemies.forEach(drawEnemy);

      gameContext.fillStyle = "white";
      gameContext.font = "16px Arial";
      gameContext.fillText(`Score: ${score}`, 10, 20);

      // Draw clock
      if (startTime !== null) {
        const time = Math.floor((Date.now() - startTime) / 1000);
        gameContext.fillText(`Time: ${time}`, gameCanvas.width - 70, 20);
      }
    }

    // Check if game over and draw game over screen
    if (gameOver) {
      drawGameOverScreen();
    }
  }

  function gameLoop() {
    if (gameOver) {
      drawGameOverScreen();
      window.addEventListener("keydown", restartGame);
      window.addEventListener("click", restartGame);
      window.addEventListener("touchstart", restartGame);
      return;
    }
    update();
    draw();
    requestAnimationFrame(gameLoop);
  }

  function restartGame(e) {
    if (e.type === "keydown" && e.key !== " ") {
      return;
    }
    if (e.type === "click" || e.type === "touchstart") {
      e.preventDefault();
      location.reload();
    }
  }

  function updateScore() {
    if (score > 0 && !gameStarted) {
      gameStarted = true;
      startTime = Date.now();
    }
  }

  function updateClock() {
    if (gameStarted && startTime !== null) {
      const time = Math.floor((Date.now() - startTime) / 1000);
      gameContext.fillStyle = "white";
      gameContext.font = "16px Arial";
      gameContext.fillText(`Time: ${time}`, gameCanvas.width - 70, 20);
    }
  }

  function drawStartScreen() {
    gameContext.fillStyle = "white";
    gameContext.font = "50px Arial";
    gameContext.textAlign = "center";
    gameContext.fillText(
      "Press Space or Tap to Start",
      gameCanvas.width / 2,
      gameCanvas.height / 2
    );
  }

  function drawGameOverScreen() {
    gameContext.fillStyle = "white";
    gameContext.font = "50px Arial";
    gameContext.textAlign = "center";
    gameContext.fillText(
      `Game Over. Your score is ${score}`,
      gameCanvas.width / 2,
      gameCanvas.height / 2 - 50
    );
    gameContext.font = "30px Arial";
    gameContext.fillText(
      "Press Space to Play Again",
      gameCanvas.width / 2,
      gameCanvas.height / 2 + 50
    );
  }

  function handleStartGame() {
    gameStarted = true;
    startTime = Date.now();
    document.removeEventListener("keydown", handleStartGame);
    document.removeEventListener("mousedown", handleStartGame);
    document.removeEventListener("touchstart", handleStartGame);
  }

  document.addEventListener("keydown", handleStartGame);
  document.addEventListener("mousedown", handleStartGame);
  document.addEventListener("touchstart", handleStartGame);

  gameLoop();

  // Keyboard controls
  window.addEventListener("keydown", (e) => {
    keys[e.key] = true;
    if (e.key === " ") {
      bullets.push({ x: ship.x, y: ship.y - ship.size });
    }
  });
  window.addEventListener("keyup", (e) => {
    keys[e.key] = false;
    if (e.key === " " && gameOver) {
      gameOver = false;
      score = 0;
      bullets.length = 0;
      enemies.length = 0;
      gameStarted = false;
    }
  });

  // Mouse controls
  window.addEventListener("mousemove", (e) => {
    ship.x = e.clientX;
    if (ship.x < 0) ship.x = 0;
    if (ship.x > gameCanvas.width) ship.x = gameCanvas.width;
  });
  window.addEventListener("mousedown", (e) => {
    bullets.push({ x: ship.x, y: ship.y - ship.size });
  });

  // Touch controls
  window.addEventListener("touchmove", (e) => {
    e.preventDefault();
    ship.x = e.touches[0].clientX;
    if (ship.x < 0) ship.x = 0;
    if (ship.x > gameCanvas.width) ship.x = gameCanvas.width;
  });
  window.addEventListener("touchstart", (e) => {
    e.preventDefault();
    bullets.push({ x: ship.x, y: ship.y - ship.size });
  });
});
