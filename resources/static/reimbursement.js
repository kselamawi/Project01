
 var allpd = document.getElementById('view-all button');
 allpd.addEventListener('click', () => {
const URL ='http://localhost:7070/reimbursements';

fetch (URL,{
    method:"GET",
    headers : {
    'Authorization':'MANAGER',
    'Content-Type':'application/json'
}
    })

.then ((res) => res.json())
.then((data) => console.log(data))

})
