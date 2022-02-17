
 var allpd = document.getElementById('view-pending-button');
 var allrd = document.getElementById('view-resolved-button');
 var approveReimb = document.getElementById("approve-button");
 var selectObjects = document.getElementsByClassName("not-selected");


 approveReimb.addEventListener('click', () => {
    const URL ='http://localhost:7070/';

   var apiUrl = `http://localhost:7070/reimbursements/${document.getElementById("reimbID").value}/approve`;
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
    const URL ='http://localhost:7070/';
    var newElement = document.createElement("ul");
    var pendingList = document.getElementsByClassName("resolved-list");
    
    fetch (apiUrl = URL + "reimbursements/resolved",{
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

 allpd.addEventListener('click', () => {
const URL ='http://localhost:7070/';
var newElement = document.createElement("ul");
var pendingList = document.getElementsByClassName("pending-list");

fetch (apiUrl = URL + "reimbursements/pending",{
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
