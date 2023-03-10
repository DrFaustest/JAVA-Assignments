/*
Scott Faust
lib_convert.js
22_WI_INFO_2134_WW
Yi
2/6/2023
*/

'use strict';

/* --Assignment 8:--
1. Define a new class named SuperDate, which extends the existing JavaScript Date class
2. Define a constructor function, which accepts a date unit to convert.
3. Inside the body of the constructor function, you should check if the passed unit is 
undefined. It it is undefined, you should invoke super(), if the passed unit is not 
undefined, invoke super() and pass the passed unit into super(). This will create a new 
Date object with either the passed date value, or a new object that represents the current date.
4. Implement the following methods:
    getFullDayName() - returns the full day name (e.g. Monday, Tuesday, etc.)
    getShortDayName() - returns the short day name (e.g. Mon, Tue, etc.) 
    --take the day names from full day name and return the first three letters--
    getFullMonthName() - returns the full month name (e.g. January, February, etc.)
    getShortMonthName() - returns the short month name (e.g. Jan, Feb, etc.) 
    --take the month names fron full month name and return the first three letters--
*/

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
};
