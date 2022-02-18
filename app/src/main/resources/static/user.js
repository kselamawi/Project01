 var allpending = document.getElementById('view-pending-button');
 var allresolved = document.getElementById('view-resolved-button');
 var viewAccount = document.getElementById('view-account-button');



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


viewAccount.addEventListener('click', () => {
const URL ='http://localhost:7070/';
var newElement = document.createElement("ul");
var viewAccountInfo = document.getElementsByClassName("viewAccountInfo");
var userId = getCookie("id");
console.log(userId);

fetch (apiUrl = URL + "users/" + userId ,{
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
const URL ='http://localhost:7070/';
var newElement = document.createElement("ul");
var pendingList = document.getElementsByClassName("resolved-list");
var userId = getCookie("id");
console.log(userId);

fetch (apiUrl = URL + "users/" + userId + "/reimbursements/resolved",{
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
const URL ='http://localhost:7070/';
var newElement = document.createElement("ul");
var pendingList = document.getElementsByClassName("pending-list");
var userId = getCookie("id");
console.log(userId);
fetch (apiUrl = URL + "users/" + userId + "/reimbursements/pending",{
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
