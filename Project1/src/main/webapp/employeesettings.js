const empID = document.getElementById("session").value;

	fetchData();
function saveEdits() {

const  newFName = document.getElementById("first-name");
const  newLName = document.getElementById("last-name");
const  newEmail = document.getElementById("email");
const  newPhone = document.getElementById("phone");

const  fName = newFName.innerHTML;
const  lName = newLName.innerHTML;
const  email = newEmail.innerHTML;
const  phone = newPhone.innerHTML;

	fetch(`http://localhost:8080/Project1/employeesettings?fName=${fName}&lName=${lName}&email=${email}&phone=${phone}&id=${empID}`, {
	method:"Post"
	})
    location.reload();
	}

function fetchData() {
    fetch(`http://localhost:8080/Project1/employee/settings?id=${empID}`).then(res => {
        return res.json();
    }).then(data => {
        console.log(data);
		const  f = document.getElementById("first-name");
		const  l = document.getElementById("last-name");
		const  e = document.getElementById("email");
		const  p = document.getElementById("phone");
		const  n = document.getElementById("name");

		f.innerHTML = data.firstName;        
		n.innerHTML = data.firstName;
		l.innerHTML = data.lastName;        
		e.innerHTML = data.email;        
		p.innerHTML = data.phoneNumber;        
		
    }).catch(error => {
        console.log(error);
    });
}
