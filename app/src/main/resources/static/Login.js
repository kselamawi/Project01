// login function
  var form = document.getElementById("form");

  function OpenRegister() {
    form.style.transform = "rotateY(-180deg)";
  }
  function OpenLogin() {
    form.style.transform = "rotateY(0deg)";
  }

function getCookie(cname) {
  let name = cname + "=";
  let decodedCookie = decodeURIComponent(document.cookie);
  let ca = decodedCookie.split(';');
  for(let i = 0; i <ca.length; i++) {
    let c = ca[i];
    while (c.charAt(0) == ' ') {
      c = c.substring(1);
    }
    if (c.indexOf(name) == 0) {
      return c.substring(name.length, c.length);
    }
  }
  return "";
}

function login(){
    let email = document.getElementById('email').value;
    let password = document.getElementById('password').value;

    let loginObj = {
        email,
        password
    }

    fetch('http://localhost:7070/login', {
        method: 'POST',
        body: JSON.stringify(loginObj)
    })
    .then((res) => {
        console.log(res.headers.get('id'));
        console.log(res.headers.get("Authorization"));
        if(res.headers.get('id')==null){
            alert("Error, incorrect email or password");
        }

        //This is how we would save the cookies on the browser
        document.cookie = `id=${res.headers.get('id')};`;
        document.cookie = `authorization=${res.headers.get('Authorization')};`;
        //setMessage();
        console.log(res);
        if(res.headers.get("Authorization")=="MANAGER"){
            window.location.href="/reimbursement.html";
        } else {
            window.location.href="/user.html";
        }
    });
    event.preventDefault();
}
let loginBtn = document.getElementById('submitButton').addEventListener('click', login);


function register(){
    let email = document.getElementById('registerEmail').value;
    let password = document.getElementById('registerPassword').value;
    let firstName = document.getElementById('firstName').value;
    let lastName = document.getElementById('lastName').value;

    let registerObj = {
        "f_name":firstName,
        "l_name":lastName,
        "email":email,
        "password":password
    }

    fetch('http://localhost:7070/register', {
        method: 'POST',
        body: JSON.stringify(registerObj)
    })
    .then((res) => {
        console.log(res);
        if(res.status==200){
            alert("Account was created for " + registerObj.f_name);
            window.location.href="/Login.html";
        } else {
            alert("Account could not be created");
        }
    })
    .catch((error) => {
        alert("Account could not be created");
    });
    event.preventDefault();
}
let registerBtn = document.getElementById('registerBtn').addEventListener('click', register);


