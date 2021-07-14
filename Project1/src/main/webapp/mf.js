const mngrID = document.getElementById("session").value;

function fetchData() {
    fetch(`http://localhost:8080/Project1/manager/employeenames`).then(res => {
        return res.json();
    }).then(data => {
        console.log(data);
        const html  = data.map(empItem =>
        {
			return `
   				<option value=${empItem.empID}>${empItem.fullName}</option>
			`;       
         }).join("");
         document.querySelector(".dropdown-names").innerHTML = html;
    }).catch(error => {
        conole.log(error);
    });
}

fetchData();


function show(el)
{

	console.log(el.value);
	
	
	 fetch(`http://localhost:8080/Project1/manager/reimbursements?id=${el.value}`).then(res => {
        return res.json();
    }).then(data => {
        console.log(data);
		const headerHtml = `<p>Reimbursement ID</p><p>Description</p><p>Amount</p><p>Status</p><p>Issue Date</p><p>Complete Date</p>`
		const line =`<hr>`
        const html  = data.map(reimbItem =>
        {
			return `
			<div class ="row-container">
   				<p class="reimb-id">${reimbItem.reimbursementID}</p>
   				<p class="descirption">${reimbItem.description}</p>
   				<p class="amount">$${reimbItem.amount}</p>
   				<p class="status">${reimbItem.status}</p>
   				<p class="issue-date">${reimbItem.issueDate}</p>
   				<p class="issue-date">${reimbItem.completedDate ? reimbItem.completedDate : 'TBD' }</p>
			</div>
			`;       
         }).join("");
         document.querySelector(".header").innerHTML = headerHtml;
         document.querySelector(".horizontal").innerHTML = line;
         document.querySelector(".container").innerHTML = html;
    }).catch(error => {
        console.log(error);
    });
}
