const empID = document.getElementById("session").value;
fetchDataName();
fetchDataName()
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

function createNew() {

let today = new Date();
	const dd = String(today.getDate()).padStart(2, '0');
	const mm = String(today.getMonth() + 1).padStart(2, '0'); //January is 0!
	const yyyy = today.getFullYear();

	today = yyyy + '-' + mm + '-' + dd;


const  description = document.getElementById("description").value;
const  amount = document.getElementById("amount").value;
console.log(today);
	fetch(`http://localhost:8080/Project1/employeereimbursement?empID=${empID}&description=${description}&amount=${amount}&status=pending&issueDate=${today}`, {
	method:"Post"
	})
	
	document.getElementById("description").value = '';
	document.getElementById("amount").value = '';
}