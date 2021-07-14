const mngrID = document.getElementById("session").value;

function fetchData() {
    fetch(`http://localhost:8080/Project1/manager/complete`).then(res => {
        return res.json();
    }).then(data => {
        console.log(data);
        const html  = data.map(reimbItem =>
        {
			return `<div class ="row-container">
   				<p class="reimb-id">${reimbItem.reimbursementID}</p>
   				<p class="fName">${reimbItem.empFullName}</p>
   				<p class="lName">${reimbItem.mngrFullName}</p>
   				<p class="descirption">${reimbItem.description}</p>
   				<p class="amount">$${reimbItem.amount}</p>
   				<p class="status">${reimbItem.status}</p>
   				<p class="issue-date">${reimbItem.issueDate}</p>
   				<p class="issue-date">${reimbItem.completedDate}</p>
   				
			</div>
			`;       
         }).join("");
         document.querySelector(".container").innerHTML = html;
    }).catch(error => {
        console.log(error);
    });
}

fetchData();
