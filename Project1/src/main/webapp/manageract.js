const mngrID = document.getElementById("session").value;




function fetchData() {
    fetch(`http://localhost:8080/Project1/manageraction/pending?id=${mngrID}`).then(res => {
        return res.json();
    }).then(data => {
        console.log(data);
        const html  = data.map(reimbItem =>
        {
			return `<div class ="row-container">
   				<p class="reimb-id">${reimbItem.reimbursementID}</p>
   				<p class="fName">${reimbItem.fName}</p>
   				<p class="lName">${reimbItem.lName}</p>
   				<p class="descirption">${reimbItem.description}</p>
   				<p class="amount">$${reimbItem.amount}</p>
   				<p class="status">${reimbItem.status}</p>
   				<p class="issue-date">${reimbItem.issueDate}</p>
     			<input type="button" value="Approve" onclick="saveAction( ${reimbItem.reimbursementID}, 'Approved')"/>
     			<input type="button" value="Deny" onclick="saveAction(${reimbItem.reimbursementID}, 'Denied')"/>
			</div>
			`;       
         }).join("");
         document.querySelector(".container").innerHTML = html;
    }).catch(error => {
        conole.log(error);
    });
}


fetchData();
function saveAction(id, action)
{
	let today = new Date();
	const dd = String(today.getDate()).padStart(2, '0');
	const mm = String(today.getMonth() + 1).padStart(2, '0'); //January is 0!
	const yyyy = today.getFullYear();

	today = yyyy + '-' + mm + '-' + dd;

	console.log(today);
	console.log(id);
	console.log(action);
	
	
	fetch(`http://localhost:8080/Project1/manageraction?id=${id}&action=${action}&date=${today}`, {
	method:"Post"
	})


	    location.reload();
	


}