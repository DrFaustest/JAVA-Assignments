//DO NOT CHANGE THE CODE IN THIS FILE

"use strict";
window.addEventListener('load', function() {
    const userInput = document.getElementById('userInput');
    const action = document.getElementById('action');
    const resultHolder = document.getElementById('resultHolder');
    let superDate;

    function checkValue(str, max) {
        if (str.charAt(0) !== '0' || str == '00') {
            let num = parseInt(str);
            if (isNaN(num) || num <= 0 || num > max) num = 1;
            str = num > parseInt(max.toString().charAt(0)) && num.toString().length == 1 ? '0' + num : num.toString();
        };
        return str;
    };

    userInput.addEventListener('input', function(e) {
        const field = e.currentTarget;
        field.type = 'text';
        let input = field.value;
        if (/\D\/$/.test(input)) input = input.substr(0, input.length - 3);
        let values = input.split('/').map(function(v) {
            return v.replace(/\D/g, '');
        });
        if (values[0]) values[0] = checkValue(values[0], 12);
        if (values[1]) values[1] = checkValue(values[1], 31);
        let output = values.map(function(v, i) {
          return v.length == 2 && i < 2 ? v + ' / ' : v;
        });
        field.value = output.join('').substr(0, 14);
        if(input == "") resultHolder.innerHTML = "";
    });

    userInput.addEventListener('blur', function(e) {
        const field = e.currentTarget;
        field.type = 'text';
        let input = field.value;
        let values = input.split('/').map(function(v, i) {
          return v.replace(/\D/g, '')
        });
        let output = '';
        
        if (values.length == 3) {
          let year = values[2].length !== 4 ? parseInt(values[2]) + 2000 : parseInt(values[2]);
          let month = parseInt(values[0]) - 1;
          let day = parseInt(values[1]);
          let d = new Date(year, month, day);
          if (!isNaN(d)) {
            let dates = [d.getMonth() + 1, d.getDate(), d.getFullYear()];
            output = dates.map(function(v) {
              v = v.toString();
              return v.length == 1 ? '0' + v : v;
            }).join(' / ');
            superDate = new SuperDate(d);
          };
        };
        field.value = output;
      });

      action.addEventListener('click', function(e) {
        const currentDate = new Date();
        let verb = superDate.getFullYear() > currentDate.getFullYear() ? "will take" : "took";
        
        if (superDate != undefined) {
            const s = `
                <h2>Date-A-Late:</h2>
                <div>This event ${verb} place on a ${superDate.getFullDayName()} (${superDate.getShortDayName()})</div>
                <div>in the month of ${superDate.getFullMonthName()} (${superDate.getShortMonthName()}).</div>
            `
            resultHolder.innerHTML = s;
        }
      })
});