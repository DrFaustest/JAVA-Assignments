'use strict';
/*
Scott Faust
Script.js
22_WI_INFO_2134_WW
Yi
1/16/2023
*/


$(document).ready( () => {
    $('#submitButton').click((e)=> {
        const emailPattern      = /\b[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\.[A-Za-z]{2,4}\b/;
        const firstName         = $('#firstName').val().trim();
        const lastName          = $('#lastName').val().trim();
        const emailAddress      = $('#emailAddress').val().trim();
        const age               = $('#age').val().trim();
        const mealHolder        = $('input[name="meal"]:checked').val();
        const mealRating        = $('input[name="rating"]:checked').val();
        
        let error = false;
/*
        const formFields = [$('#firstName'), $('#lastName'), $('#emailAddress'), $('#age')];
        formFields.forEach((field) => {
            field.val($.trim(field.val()));
                if (field.val() === "") {
                    field.next('span').text("This field is required.");
                    error = true;
                }
        });
*/
        //Within the body of the click() event handler, flesh out the body of each if and if else statement so that an error message is written to the <span> following the relevant control.
        if(firstName == "") {
            //if the first name is empty
            $('#firstName').next('span').text("This field is required.");
            error = true;
        } 
        if (lastName == "") {
            //if the last name is empty
            $('#lastName').next().text("This field is required.");
            error = true;
        } 
        if (emailAddress == "")  {
            //if the email address is empty
            $('#emailAddress').next().text("This field is required.");
            error = true;
        } else if (!emailPattern.test(emailAddress)) {
            //if the email address is not in a valid email address format . . .
            $('#emailAddress').next().text("Must be a valid email address.");
            error = true;
        }
        if (age == "") {
            //if the age is empty
            $('#age').next().text("This field is required.");
            error = true;
        } else if (isNaN(age)) {
            //if the age is not a number
            $('#age').next().text("Must be a number.");
            error = true;
        } else if (age < 0) {
            //if the age is less than zero
            $('#age').next().text("Must be a positive number.");
            error = true;
        }
        if (mealHolder == "" || mealHolder == undefined) {
            //if the meal is empty
            $('#mealHolder').next().text("This field is required.");
            error = true;
        } 
        if (mealRating == "" || mealRating == undefined) {
            //if the meal rating is empty
            $('#mealRatingHolder').next().text("This field is required.");
            error = true;
        }
        if(!error) {
            $('#contactForm').submit();
        } else {
            //In the event an error has occurred, invoke the error object’s preventDefault() method.
            //log the value of each field to the console for debugging purposes
            e.preventDefault();
        }
    })

    //handle click on Reset form  button
    //move focus to first text box
    $('#resetButton').click(() => {
        $('#firstName').val("").focus();
        $('#lastName').val("");
        $('#emailAddress').val("");
        $('#age').val("");
        $('input[name="meal"]').prop('checked', false);
        $('input[name="rating"]').prop('checked', false);
        $('span').text("");
    })
});