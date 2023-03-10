/*
Scott Faust
script.js
22_WI_INFO_2134_WW
Yi
2/21/2023
*/
'use strict';

$(document).ready(() => {
  const baseUrl = 'https://www.mccinfo.net/epsample/employees';
  const employeeList = document.getElementById('employeeList');
  const employeeInfo = document.getElementById('employeeInfo');

  $('#errorHolder').hide();
  $('#loadingHolder').hide();
  $('#employeeList').hide();

  //the animation for the get employees button, swaps to the loading animation
  $('#getEmployees').click((evt) => {
    $(evt.target).parent().slideUp();
    $('#loadingHolder').slideDown();

    //the api call to get the employees json data
    fetch(baseUrl)
      .then(response => response.json())
      .then(employees => {
        //for loop that builds the employee link list from the json data
        for (let employee of employees) {
          let div = document.createElement('div');
          let a = document.createElement('a');

          a.href = '#';
          a.id = employee.id;
          a.innerHTML = `${employee.first_name} ${employee.last_name}`;
          a.addEventListener('click', onEmployeeClicked);

          div.appendChild(a);
          employeeList.appendChild(div);
        }
        //once the list is built the loading animation is swapped out for the employee list
        $('#loadingHolder').fadeOut(400, () => {
          $('#employeeList').fadeIn();
        });
      });
  });

    //the function that is called when an employee link is clicked
  const onEmployeeClicked = (e) => {
    $('#errorHolder').hide();
    $('#errorHolder').html('');
    employeeInfo.innerHTML = '';
    //the api call to get the employee json data
    fetch (`${baseUrl}\/${e.currentTarget.id}`)
      .then(response => response.json())
      .then((employee) => {
        //the html element variables are created
        const div = document.createElement('div');
        const img = document.createElement('img');
        const h1 = document.createElement('h1');
        const divDepartment = document.createElement('div');
        const divSalary = document.createElement('div');
        const hireDate = new SuperDate(employee.hire_date);
        const divHireDate = document.createElement('div');
        //the hire date is formatted
        const fullDayName = hireDate.getFullDayName();
        const fullMonthName = hireDate.getFullMonthName();
        const year = hireDate.getFullYear().toString();
        const ordinal = hireDate.getDateOrdinal();
        const hireDateText = `${fullDayName}, ${fullMonthName}, ${ordinal}, ${year}`;
        //the card information is built from the json data
        img.src = employee.image_filename;
        img.alt = `${employee.first_name} ${employee.last_name}`;
        h1.innerText = `${employee.first_name} ${employee.last_name}`;
        divDepartment.innerText = `Department: ${employee.department.name}`;
        divSalary.innerText = `Annual Salary: ${accounting.formatMoney(employee.annual_salary)}`;
        divHireDate.innerText = `Hire Date: ${hireDateText}`;
        //the card is appended to the employee info div
        div.appendChild(img);
        div.appendChild(h1);
        div.appendChild(divDepartment);
        div.appendChild(divSalary);
        div.appendChild(divHireDate);

        employeeInfo.appendChild(div);
      })
      // if the api call fails the error message is displayed in the console
      .catch((e) => {
        console.log(e.message);
      });
  };
});
