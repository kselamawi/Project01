
 var allpd = document.getElementById('view-all button');
 allpd.addEventListener('click', () => {
const URL ='http://34.138.63.74:7070/reimbursements';
var authorization ={ "Authorization":"MANAGER" };
fetch (URL,{
    method:"GET",
    headers :
    {
    authorization,
    "Content-Type":"application/json"
}
    })

.then ((res) => res.json())
.then((data) => console.log(data))

})
