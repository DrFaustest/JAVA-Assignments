/*
Scott Faust
clock.js
22_WI_INFO_2134_WW
Yi
2/15/2023
*/

'use strict';


const Clock = (() => {
    let canvas; 
    let ctx;    
    let radius; 

    const startClock = () => {
        canvas = document.getElementById('clock');
        ctx = canvas.getContext('2d');
        radius = canvas.height / 2;
        ctx.translate(radius, radius);
        radius = radius * 0.90;
        setInterval(drawClock, 1000);
    }
    const drawClock = () => {
        drawFace(ctx, radius);
        drawNumbers(ctx, radius);
        drawTime(ctx, radius);
    }

    const drawTime = (ctx, radius) => {
        const now = new Date();
        let hour = now.getHours();
        let minute = now.getMinutes();
        let second = now.getSeconds();

        hour = hour%12;
        hour = (hour*Math.PI/6)+(minute*Math.PI/(6*60))+(second*Math.PI/(360*60));
        drawHand(ctx, hour, radius*0.5, radius*0.07);
        //minute
        minute = (minute*Math.PI/30)+(second*Math.PI/(30*60));
        drawHand(ctx, minute, radius*0.8, radius*0.07);
        // second
        second = (second*Math.PI/30);
        drawHand(ctx, second, radius*0.9, radius*0.02);
    }

    const drawHand = (ctx, pos, length, width) => {
        ctx.beginPath();
        ctx.lineWidth = width;
        ctx.lineCap = "round";
        ctx.moveTo(0,0);
        ctx.rotate(pos);
        ctx.lineTo(0, -length);
        ctx.stroke();
        ctx.rotate(-pos);
    }

    const drawNumbers = (ctx, radius) => {
        let ang;
        let num;

        ctx.font = radius * 0.15 + 'px arial';
        ctx.textBaseline = 'middle';
        ctx.textAlign = 'center';

        for(let num = 1; num < 13; num++) {
            ang = num * Math.PI / 6;
            ctx.rotate(ang);
            ctx.translate(0, -radius * 0.85);
            ctx.rotate(-ang);
            ctx.fillText(num.toString(), 0, 0);
            ctx.rotate(ang);
            ctx.translate(0, radius * 0.85);
            ctx.rotate(-ang);
        }
    }

    const drawFace = () => {
        let grad; 

        ctx.beginPath();
        ctx.arc(0,0, radius, 0, 2 * Math.PI);
        ctx.fillStyle = 'white';
        ctx.fill();

        grad = ctx.createRadialGradient(0,0, radius * 0.95, 0, 0, radius * 1.05);
        grad.addColorStop(0, '#333');
        grad.addColorStop(0.5, 'white');
        grad.addColorStop(1, '#333');
        ctx.strokeStyle = grad;
        ctx.lineWidth = radius * 0.1;
        ctx.stroke();

        ctx.beginPath();
        ctx.arc(0,0, radius * 0.1, 0, 2 * Math.PI);
        ctx.fillStyle = '#333';
        ctx.fill();
    }

    return {
        startClock
    };
})();


