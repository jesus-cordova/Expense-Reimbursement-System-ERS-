const mngrID = document.getElementById("session").value;
fetchData();




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
   				<p class="amount">${reimbItem.amount}</p>
   				<p class="status">${reimbItem.status}</p>
   				<p class="issue-date">${reimbItem.issueDate}</p>
     			<input type="button" value="Approveadf" onclick="saveAction(${reimbItem.reimbursementID}, "Approve")"/>
     			<input type="button" value="Deny" onclick="saveAction(${reimbItem.reimbursementID},"Deny)"/>
			</div>
			`;       
         }).join("");
         document.querySelector(".container").innerHTML = html;
    }).catch(error => {
        conole.log(error);
    });
}


function saveAction(id, action)
{

	console.log(id);
	console.log(action);

}