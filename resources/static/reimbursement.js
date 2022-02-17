
 var allpd = document.getElementById('view-all button');
 allpd.addEventListener('click', () => {
const URL ='http://localhost:7070/reimbursements';

var newElement = document.createElement("ul");

var pendingList = document.getElementsByClassName("pending-list");

fetch (URL,{
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
        newElement.innerHTML += (`<li>ID:${data[i].id} Amount: ${data[i].amount} Type: ${data[i].reimbursementType} Status: ${data[i].reimbursementStatus}</li>`);
    }
    document.getElementById("PendingReimbursements").appendChild(newElement);
})

})
