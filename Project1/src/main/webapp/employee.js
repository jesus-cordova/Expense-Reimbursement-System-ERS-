const empID = document.getElementById("session").value;
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