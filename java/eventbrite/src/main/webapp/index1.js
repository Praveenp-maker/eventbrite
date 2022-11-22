
let Events=[];
updateIndex;
function get(){
    console.log("hiii");
    selectElement = document.querySelector('#utype');
    output = selectElement.value;
    console.log(output);
    if(output=='viewer'){
       // showdetails();
        location.href="viewer.html";
    }
    else{
        location.href="insertdetails.html";
    //insertdetails();
    }
    // if(output=='viewer')
    // {
    //     $.ajax({
    //         url: "http://localhost:9090/servlet1",
    //         type: "GET",
    //         //dataType: "json",
    //         success: function (data) {
    //             console.log("success");
    //         },
    //         error: function (error) {
    //             console.log(`Error ${error}`);
    //         },
    //     });
    // }
    // else{
    // }
}
function showdetails(){
    console.log("enter into showdetails");
    $.ajax({
        url: "http://localhost:9090/servlet1",
        type: "GET",
        dataType: "json",
        success: function (data) {
            console.log(data);
        Event=data;
    displayItems(data);
        },
        error: function (error) {
            console.log(`Error ${error}`);
        },
    });
}
function insertdetails(){
 const date = document.getElementById("date").value;
const [month, year, day] = date.split('-');
const result = [year, month, day].join('-');
console.log(result);
    var newitem = {
        "event_id": document.getElementById("event_id").value,
        "event_name": document.getElementById("event_name").value,
        "description": document.getElementById("decs").value,
        "location": document.getElementById("location").value,
        "date": document.getElementById("date").value,
        "time": document.getElementById("time").value,
        "venue": document.getElementById("venue").value,
        "concept": document.getElementById("concept").value,
        "company": document.getElementById("company").value,
        "prize":document.getElementById("prize").value
    }
    $.ajax({
        type: "POST",
        url: "http://localhost:9090/servlet1",
        data: JSON.stringify(newitem),
        success: function (result) {
            //getAll();
            console.log("sucesss");
            //getnew();
        },
        error: function (result) {
            alert("msg");
        },
    });
}
function editItem(event_id) {
    sessionStorage.setItem("event_id",event_id);
   // document.getElementById("iid").style.display="none";
    //console.log(updateIndex);
    location.href="order.html";
}
function getedit(){
console.log(sessionStorage.getItem("event_id"));
  var newitem={
    "event_id":sessionStorage.getItem("event_id")
   } 
   $.ajax({
    type: "PUT",
    url: "http://localhost:9090/servlet1",
    data: JSON.stringify(newitem),
    success: function (data) {
        console.log("sucess");
        Events=data;
        console.log(data);
        data = JSON.parse(data);
       displayItem(data);
        //getAll();
        console.log("sucesss");
        //getnew();
    },
    error: function (data) {
        alert("msg");
    },
});
  }
function displayItems(data){
    //location.href="viewer.html";
    console.log("inside the display function");
    const tEvent = document.getElementById("event");
 console.log(tEvent);
 const button = document.createElement("button");
  data.forEach((item) => {
    let editButton = button.cloneNode(false);
    editButton.innerText = "Book Here";
    editButton.setAttribute("onclick", `editItem(${item.event_id})`);
        console.log(item.event_id);
        let tr = tEvent.insertRow();
        let td1 = tr.insertCell(0);
        let event_id = document.createTextNode(item.event_id);
        td1.appendChild(event_id);
        let td2 = tr.insertCell(1);
        let event_name = document.createTextNode(item.event_name);
        td2.appendChild(event_name);
        let td3 = tr.insertCell(2);
        let description = document.createTextNode(item.description);
        td3.appendChild(description);
        let td4 = tr.insertCell(3);
        let location = document.createTextNode(item.location);
        td4.appendChild(location);
        let td5 = tr.insertCell(4);
        let date = document.createTextNode(item.date);
        td5.appendChild(date);
        let td6 = tr.insertCell(5);
        let time = document.createTextNode(item.time);
        td6.appendChild(time);
        let td7 = tr.insertCell(6);
        let venue = document.createTextNode(item.venue);
        td7.appendChild(venue);
        let td8 = tr.insertCell(7);
        let concept = document.createTextNode(item.concept);
        td8.appendChild(concept);
        let td9 = tr.insertCell(8);
        let company = document.createTextNode(item.company);
        td9.appendChild(company);
        let td10 = tr.insertCell(9);
        let prize = document.createTextNode(item.prize);
        td10.appendChild(prize);
        let td11= tr.insertCell(10);
        td11.appendChild(editButton);
    });
    Event= data;
}
function getnew(){
    showdetails();
    // console.log("hai");
    // $('#tableid').DataTable( {
    //     "ajax": {
    //         "url": "http://localhost:9090/servlet1",
    //         "dataSrc": ""
    //     },
    //     "columns": [
    //         { "data": "event_id" },
    //         { "data": "event_name" },
    //         { "data": "description" },
    //         { "data": "location" },
    //         { "data": "date" },
    //         { "data": "time" },
    //         { "data": "venue" },
    //         { "data": "concept" },
    //         { "data": "company" }
    //     ]
    // } );
}
function displayItem(data){
    //location.href="viewer.html";
    console.log("inside the display2 function");
    const tEvents = document.getElementById("order");
//  const button = document.createElement("button");
  data.forEach((item) => {
    // let editButton = button.cloneNode(false);
    // editButton.innerText = "Book Here";
    // editButton.setAttribute("onclick", `editItem(${item.event_id})`);
        console.log(item.event_id);
        let tr = tEvents.insertRow();
        let td1 = tr.insertCell(0);
        let event_id = document.createTextNode(item.event_id);
        td1.appendChild(event_id);
        let td2 = tr.insertCell(1);
        let event_name = document.createTextNode(item.event_name);
        td2.appendChild(event_name);
        let td3 = tr.insertCell(2);
        let description = document.createTextNode(item.description);
        td3.appendChild(description);
        let td4 = tr.insertCell(3);
        let location = document.createTextNode(item.location);
        td4.appendChild(location);
        let td5 = tr.insertCell(4);
        let date = document.createTextNode(item.date);
        td5.appendChild(date);
        let td6 = tr.insertCell(5);
        let time = document.createTextNode(item.time);
        td6.appendChild(time);
        let td7 = tr.insertCell(6);
        let venue = document.createTextNode(item.venue);
        td7.appendChild(venue);
        let td8 = tr.insertCell(7);
        let concept = document.createTextNode(item.concept);
        td8.appendChild(concept);
        let td9 = tr.insertCell(8);
        let company = document.createTextNode(item.company);
        td9.appendChild(company);
        let td10 = tr.insertCell(9);
        let prize = document.createTextNode(item.prize);
        td10.appendChild(prize);
        // let td11= tr.insertCell(10);
        // td11.appendChild(editButton);
    });
    Events= data;
}
function getorder(){
    console.log("hai");
    displayItem(data);
}
