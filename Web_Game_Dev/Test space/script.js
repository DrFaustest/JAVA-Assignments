let canvas = document.getElementById("myCanvas");
let ctx = canvas.getContext("2d");
let explosion = new Image();
explosion.addEventListener("load", loadHandler, false);
explosion.src = "Images/exp2.png";

function loadHandler() {
    explosionObject.animation();
}

let explosionObject = {
    frame: [
        [187.5, 187.5, 62.5, 62.5], [125, 187.5, 62.5, 62.5], [62.5, 187.5, 62.5, 62.5], [0, 187.5, 62.5, 62.5],
        [187.5, 125, 62.5, 62.5], [125, 125, 62.5, 62.5], [62.5, 125, 62.5, 62.5], [0, 125, 62.5, 62.5],
        [187.5, 62.5, 62.5, 62.5], [125, 62.5, 62.5, 62.5], [62.5, 62.5, 62.5, 62.5], [0, 62.5, 62.5, 62.5],
        [187.5, 0, 62.5, 62.5], [125, 0, 62.5, 62.5], [62.5, 0, 62.5, 62.5], [0, 0, 62.5, 62.5]
    ],
    currentFrame: 0,
    frameIncrement: 1,
    count: 0,
    animation: function () {
        if (this.currentFrame >= 0 && this.currentFrame <= 15) {
            ctx.clearRect(0, 0, canvas.width, canvas.height);
            ctx.drawImage(
                explosion,
                explosionObject.frame[this.currentFrame][0], explosionObject.frame[this.currentFrame][1],
                explosionObject.frame[this.currentFrame][2], explosionObject.frame[this.currentFrame][3],
                0, 0, 62.5, 62.5
            );
            this.currentFrame += this.frameIncrement;
            if (this.currentFrame >= 15 || this.currentFrame <= 0) {
                this.frameIncrement = -this.frameIncrement;
                this.count++;
                //once the animation is done, hide the canvas
                if (this.count >= 2) {
                    canvas.style.display = "none";
                }
            }
            setTimeout(() => this.animation(), 100);
        }
    }
}
