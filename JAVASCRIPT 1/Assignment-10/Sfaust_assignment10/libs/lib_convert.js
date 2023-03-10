/*
Scott Faust
lib_convert.js
22_WI_INFO_2134_WW
Yi
2/21/2023
*/
'use strict';

class SuperDate extends Date {
    constructor(date) {
        if (date == undefined) {
            super();
        } else {
            super(date);
        }
    }
    getFullDayName() {
        const days = ["Sunday", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday"];
        return days[this.getDay()];
    }
    getShortDayName() {
        return this.getFullDayName().substring(0, 3); 
        /*--substr is deprecated in favor of substring--
        https://stackoverflow.com/questions/52640271/why-is-string-prototype-substr-deprecated -- original search result
        https://developer.mozilla.org/en-US/docs/Web/JavaScript/Reference/Global_Objects/String/substring -- documentation
        */
    }
    getFullMonthName() {
        const months = ["January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December"];
        return months[this.getMonth()];
    }
    getShortMonthName() {
        return this.getFullMonthName().substring(0, 3);
    }
    getDateOrdinal() {
        const day = this.getDate();
        const suffix = ["th", "st", "nd", "rd"];
        const value = day % 100;
        /*  --logic--
        --The algorithem isn't mine but the explination is--
        This would be easy if if it was 11st but its not so in order to avoid the 11-13 we 
        need to subtract 20 from the value to return a negitive number that will evaluate 
        to falsey and move to the next condition. because the value of 11-13 is outside 
        the scope of the array it will return undefined and be evaluated as falsey and 
        move to the next condition that defaults to "th"
        -aka the third condition if for integers that are >3 and <20

        because the value of 1-3 directly coorisponds to the index of the array we use the 
        second check to evaluate and assign the correct suffix
        -aka the second condition is for integers that are 1-3

        After 20 the pattern normalizes and we can use the first check to evaluate and 
        assign the correct suffix based on the mod 10 value of the day that basically just 
        checks for 1, 2, or 3
        -aka the first condition is for integers that are >=20
        https://leancrew.com/all-this/2020/06/ordinal-numerals-and-javascript/
        --the author names  jlbruno as the original author of the algorithem--
        */
        return day + (suffix[(value - 20) % 10] || suffix[value] || suffix[0]);

    }
};
