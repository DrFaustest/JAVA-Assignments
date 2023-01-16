'use strict';
/*

*/


$(document).ready( () => {
    $('#submitButton').click((e)=> {
        const emailPattern      = /\b[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\.[A-Za-z]{2,4}\b/;
        const firstName         = $('#firstName').val();
        const lastName          = $('#lastName').val();
        const emailAddress      = $('#emailAddress').val();
        const age               = $('#age').val();
        const mealHolder        = $('input[name="meal"]:checked').val();
        const mealRating        = $('input[name="rating"]:checked').val();
        
        let error = false;

        if(firstName == "") {

        } 
        if (lastName == "") {

        } 
        if (emailAddress == "")  {

        } else if (!emailPattern.test(emailAddress)) {
            //if the email address is not in a valid email address format . . .
            
        }
        if (age == "") {

        } else if (isNaN(age)) {

        } else if (age < 0) {

        }
        if (mealHolder == "" || mealHolder == undefined) {

        } 
        if (mealRating == "" || mealRating == undefined) {

        }
        if(!error) {
            $('#contactForm').submit();
        } else {

        }
    })

    //handle click on Reset form  button
    //move focus to first text box

});