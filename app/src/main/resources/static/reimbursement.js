
 var allpd = document.getElementById('view-pending-button');
 var allrd = document.getElementById('view-resolved-button');
 var approveReimb = document.getElementById("approve-button");
 var denyReimb = document.getElementById("deny-button");
 var selectObjects = document.getElementsByClassName("not-selected");
 var getReimbByUserId = document.getElementById("get-reimbursement");
 var viewAllEmployees = document.getElementById("25");
 var logout = document.getElementById('logout-button');
 const url = "http://34.138.63.74:7070";



 approveReimb.addEventListener('click', () => {

   var apiUrl = url + `/reimbursements/${document.getElementById("reimbID").value}/approve`;
   console.log(apiUrl);
    // document.getElementById("cookiesomething")
    var authorID = 3;
    
    fetch (apiUrl, {
        method:"PUT",
        headers : {
        'Authorization':'MANAGER',
    }, 
        body: JSON.stringify({"id": 3}),
        })
    })


    logout.addEventListener('click', () =>{
        fetch(apiUrl = url + "/logout")
        .then(res => {
            document.cookie = 'id=;';
            document.cookie = 'authorization=;';
            window.location.href="/Login.html";
        })  
    })

denyReimb.addEventListener('click', () => {
   var apiUrl = url + `/reimbursements/${document.getElementById("reimbID").value}/deny`;
   console.log(apiUrl);
    // document.getElementById("cookiesomething")
    var authorID = 3;

    fetch (apiUrl, {
        method:"PUT",
        headers : {
        'Authorization':'MANAGER',
    },
        body: JSON.stringify({"id": 3}),
        })
    })

 allrd.addEventListener('click', () => {
    var newElement = document.createElement("ul");
    var pendingList = document.getElementsByClassName("resolved-list");
    
    fetch (apiUrl = url + "/reimbursements/resolved",{
        method:"GET",
        headers : {
        'Authorization':'MANAGER',
        'Content-Type':'application/json'
    }
        })
    
    .then ((res) => res.json())
    .then((data) => {console.log(data)
        console.log(data[1]);
        for(var i = 0; i < data.length; i++){
            console.log(`<li>ID:${data[i].id}</li>`);
            newElement.innerHTML += (`<li class="not-selected">ID:${data[i].id} Amount: ${data[i].amount} Type: ${data[i].reimbursementType} Status: ${data[i].reimbursementStatus}</li>`);
        }
        document.getElementById("ResolvedReimbursements").appendChild(newElement);
    })
    
    })

getReimbByUserId.addEventListener('click', () => {
    var employeeId = document.getElementById("employeeID").value;

    var newElement = document.createElement("li");
    fetch (apiUrl = url + "/users/" + employeeId + "/reimbursements",{
    method:"GET",
    headers: {
    'Authorization':'MANAGER'

    }
    })
    .then ((res) => res.json())
    .then((data) => {console.log(data)

        for(var i = 0; i < data.length; i++){
            console.log(`<li>ID:${data[i].id}</li>`);
            newElement.innerHTML += (`<li class="not-selected">ID:${data[i].id} Amount: ${data[i].amount} Type: ${data[i].reimbursementType} Status: ${data[i].reimbursementStatus}</li>`);
        }
        document.getElementById("viewEmployeeReimb").appendChild(newElement);
    })
})

 allpd.addEventListener('click', () => {
var newElement = document.createElement("ul");
var pendingList = document.getElementsByClassName("pending-list");

fetch (apiUrl = url + "/reimbursements/pending",{
    method:"GET",
    headers : {
    'Authorization':'MANAGER',
    'Content-Type':'application/json'
}
    })

.then ((res) => res.json())
.then((data) => {console.log(data)
    console.log(data[1]);
    for(var i = 0; i < data.length; i++){
        console.log(`<li>ID:${data[i].id}</li>`);
        newElement.innerHTML += (`<li class="not-selected">ID:${data[i].id} Amount: ${data[i].amount} Type: ${data[i].reimbursementType} Status: ${data[i].reimbursementStatus}</li>`);
    }
    document.getElementById("PendingReimbursements").appendChild(newElement);
})



})

viewAllEmployees.addEventListener('click', () => {
    var newElement = document.createElement("li");
    var employeeList = document.getElementsByClassName("viewAllEmployees");
console.log("stuff and things");
    fetch (apiUrl = url + "/users", {
        method:"GET",
        headers : {
        'Authorization':'MANAGER',
        'Content-Type':'application/json'
        }
    })

    .then ((res) => res.json())
    .then((data) => {console.log(data)
        console.log(data[1]);
        for(var i = 0; i < data.length; i++){
            console.log(`<li>ID:${data[i].id}</li>`);
            newElement.innerHTML += (`<li class="not-selected">ID:${data[i].id} Name: ${data[i].f_name} ${data[i].l_name} </li>`);
        }
        document.getElementById("viewAllEmployees").appendChild(newElement);

    })

    })
