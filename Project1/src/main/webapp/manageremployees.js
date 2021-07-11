const mngrID = document.getElementById("session").value;

function fetchData() {
    fetch(`http://localhost:8080/Project1/manager/employees`).then(res => {
        return res.json();
    }).then(data => {
        console.log(data);
        const html  = data.map(empItem =>
        {
			return `<div class ="row-container">
   				<p class="reimb-id">${empItem.empID}</p>
   				<p class="fName">${empItem.firstName}</p>
   				<p class="lName">${empItem.lastName}</p>
   				<p class="descirption">${empItem.email}</p>
   				<p class="amount">${empItem.phoneNumber}</p>
			</div>
			`;       
         }).join("");
         document.querySelector(".container").innerHTML = html;
    }).catch(error => {
        conole.log(error);
    });
}

fetchData();
