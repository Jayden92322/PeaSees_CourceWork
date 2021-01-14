"use strict";
function getPartsList() {
    //debugger;
    console.log("Invoked getPartsList()");     //console.log your BFF for debugging client side - also use debugger statement
    const url = "/Parts/list";    		// API method on web server will be in Users class, method list
    fetch(url, {
        method: "GET",				//Get method
    }).then(response => {
        return response.json();                 //return response as JSON
    }).then(response => {
        if (response.hasOwnProperty("Error")) { //checks if response from the web server has an "Error"
            alert(JSON.stringify(response));    // if it does, convert JSON object to string and alert (pop up window)
        } else {
            formatPartsList(response);          //this function will create an HTML table of the data (as per previous lesson)
        }
    });
}
function formatPartsList(myJSONArray){
    let dataHTML = "";
    for (let item of myJSONArray) {
        dataHTML += "<tr><td>" + item.PartID + "<td><td>" + item.PartDescription + "<tr><td>";
    }
    document.getElementById("PartsTable").innerHTML = dataHTML;
}
//function searchitem() {
   // let input = document.getElementById('searchbar').value
//    input=input.toLowerCase();
//    let x = document.getElementsByClassName('Parts');
//
//    for (i = 0; i < x.length; i++) {
//        if (!x[i].innerHTML.toLowerCase().includes(input)) {
 //           x[i].style.display="none";
 //       }
 //       else {
 //           x[i].style.display="list-item";
 //       }
 //   }
//}
