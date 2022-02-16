// JavaScript source code

//Declare an object and reference the submit button.
let submitButton = document.getElementById("submitButton");
// Declare an object and put the API link inside.
let URL = "http://localhost:8090";
//Create an element to be placed in the HTML
let newDiv = document.createElement("div");

let newPokemonClass = document.getElementsByClassName("pokemon")[0];
let newLogin = document.getElementById("new-login");

//create post request
submitButton.addEventListener('click', post);

function post() {
    let email = document.getElementById('email').value;
    let password = document.getElementById('password').value;

    console.log(email, password);

    let loginInfo = {
        email,
        password
    }

    let apiUrl = `/api/login`;

    fetch(apiUrl, {
        method: 'POST',
        headers: {
            'Content-Type': "application/json"
        },
        body: JSON.stringify(loginInfo)
    })
        .then((res) => res.json())
        .then((data) => console.log(data));
}

    