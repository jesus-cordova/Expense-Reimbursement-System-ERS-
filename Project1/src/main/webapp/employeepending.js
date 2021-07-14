const empID = document.getElementById("session").value;
fetchDataName();
function fetchData() {
    fetch(`http://localhost:8080/Project1/employee/pending?id=${empID}`).then(res => {
        return res.json();
    }).then(data => {
        console.log(data);
        const html  = data.map(reimbItem =>
        {
			return `<div class ="row-container">
   				<p class="reimb-id">${reimbItem.reimbursementID}</p>
   				<p class="descirption">${reimbItem.description}</p>
   				<p class="amount">$${reimbItem.amount}</p>
   				<p class="status">${reimbItem.status}</p>
   				<p class="issue-date">${reimbItem.issueDate}</p>
			</div>
			`;       
         }).join("");
         document.querySelector(".container").innerHTML = html;
    }).catch(error => {
        conole.log(error);
    });
}

fetchData();

fetchDataName();
function fetchDataName() {
    fetch(`http://localhost:8080/Project1/employee/settings?id=${empID}`).then(res => {
        return res.json();
    }).then(data => {
        console.log(data);
		const  n = document.getElementById("name");

		n.innerHTML = data.firstName;
		
    }).catch(error => {
        console.log(error);
    });
}