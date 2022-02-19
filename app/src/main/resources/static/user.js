 var allpending = document.getElementById('view-pending-button');
 var allresolved = document.getElementById('view-resolved-button');
 var viewAccount = document.getElementById('view-account-button');
 var updatedAccount = document.getElementById('view updated account');
 var createReimbursement = document.getElementById('submit-reimbursement');
 var logout = document.getElementById('logout-button');
 const URL = "http://34.138.63.74:7070";

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

logout.addEventListener('click', () =>{
    fetch(apiUrl = URL + "/logout")
    .then(res => {
        document.cookie = 'id=;';
        document.cookie = 'authorization=;';
        window.location.href="/Login.html";
    })  
})

createReimbursement.addEventListener('click', () =>{
    // var viewAccountInfo = document.getElementsByClassName("viewAccountInfo");
    var userId = getCookie("id");
    var amount = document.getElementById('reimbAmount').value;
    var reimbType = document.getElementById('reimbTypeList').value;
        var createReimbObject = {
        "amount": amount,
        "author_id": userId,
        "reimbursementType": reimbType
    }

    fetch(apiUrl = URL + "/users/" + userId + "/reimbursements/create", {
        method: "POST",
        header: {
            "Authorization": "EMPLOYEE"
        },
        body: JSON.stringify(createReimbObject)
    })
    .then((res) => {
        res.json;
        if(res.status==200){
            var newElement = document.createElement("div");
            newElement.innerHTML = "<p>Reimbursement created</p>"
            document.getElementById("submitReimbursement").appendChild(newElement);
            console.log("SUCCESS");
        }
    })

})

 
updatedAccount.addEventListener('click', () =>{
    // var viewAccountInfo = document.getElementsByClassName("viewAccountInfo");
    var userId = getCookie("id");
    var f_name =document.getElementById("firstname").value;
    var l_name= document.getElementById("lastname").value;
    var email= document.getElementById("email").value;
    var password =document.getElementById("password").value;
    var newElement = document.createElement("p");

  fetch(apiUrl = URL +"/users/" +userId,{
      method: "PUT",
      headers:{
        'Authorization':'EMPLOYEE'
      },
      body: JSON.stringify({
          "f_name" :f_name,
          "l_name" :l_name,
          "email" :email,
          "password": password
      })
  })
  .then((data) => {
  })    
  .catch((error) => {
  })

})

viewAccount.addEventListener('click', () => {
var newElement = document.createElement("ul");
var viewAccountInfo = document.getElementsByClassName("viewAccountInfo");
var userId = getCookie("id");
console.log(userId);

fetch (apiUrl = URL + "/users/" + userId ,{
    method:"GET",
    headers : {
    'Authorization':'EMPLOYEE',
    'Content-Type':'application/json'
}
    })

.then ((res) => res.json())
.catch((error) => {
         console.log("No Such Account");
         newElement.innerHTML = `<p>No Such Account</p>`;
         document.getElementById("viewAccountInfo").appendChild(newElement);

    })
.then((data) => {console.log(data)
        var i = 0;
        console.log(`<li>ID:${data.id}</li>`);
        newElement.innerHTML += (`<li class="not-selected">ID:${data.id}</li>`);
        newElement.innerHTML += (`<li class="not-selected">First Name: ${data.f_name}</li>`);
        newElement.innerHTML += (`<li class="not-selected">Last Name: ${data.l_name}</li>`);
        newElement.innerHTML += (`<li class="not-selected">Email: ${data.email}</li>`);

    document.getElementById("viewAccountInfo").appendChild(newElement);
})
})



allresolved.addEventListener('click', () => {
var newElement = document.createElement("ul");
var pendingList = document.getElementsByClassName("resolved-list");
var userId = getCookie("id");
console.log(userId);

fetch (apiUrl = URL + "/users/" + userId + "/reimbursements/resolved",{
    method:"GET",
    headers : {
    'Authorization':'EMPLOYEE',
    'Content-Type':'application/json'
}
    })

.then ((res) => res.json())
.catch((error) => {
         console.log("No Resolved Reimbursements");
         newElement.innerHTML = `<p>No Resolved Reimbursements</p>`;
         document.getElementById("ResolvedReimbursements").appendChild(newElement);

    })
.then((data) => {console.log(data)
    for(var i = 0; i < data.length; i++){
        console.log(`<li>ID:${data[i].id}</li>`);
        newElement.innerHTML += (`<li class="not-selected">ID:${data[i].id} Amount: ${data[i].amount} Type: ${data[i].reimbursementType} Status: ${data[i].reimbursementStatus}</li>`);
    }
    document.getElementById("ResolvedReimbursements").appendChild(newElement);
})
})



allpending.addEventListener('click', () => {
document.getElementById("PendingReimbursements").innerHTML="";
var newElement = document.createElement("ul");
var pendingList = document.getElementsByClassName("pending-list");
var userId = getCookie("id");
console.log(userId);
fetch (apiUrl = URL + "/users/" + userId + "/reimbursements/pending",{
    method:"GET",
    headers : {
    'Authorization':'EMPLOYEE',
    'Content-Type':'application/json'
}
    })

.then ((res) => res.json())
    .catch((error) => {
         console.log("No Pending Reimbursements");
         newElement.innerHTML = `<p>No Pending Reimbursements</p>`;
         document.getElementById("PendingReimbursements").appendChild(newElement);

    })
.then((data) => {
        for(var i = 0; i < data.length; i++){
            console.log(`<li>ID:${data[i].id}</li>`);
            newElement.innerHTML += (`<li class="not-selected">ID:${data[i].id} Amount: ${data[i].amount} Type: ${data[i].reimbursementType} Status: ${data[i].reimbursementStatus}</li>`);
        }
    document.getElementById("PendingReimbursements").appendChild(newElement);
})
})
