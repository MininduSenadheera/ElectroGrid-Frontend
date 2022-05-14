$(document).ready(function() {
    if ($('#alertSuccess').text().trim() == "") {
        $('#alertSuccess').hide();
    }

	$('#newBillGrid').hide();
    $('#alertError').hide();
})

// SAVE
$(document).on("click","#btnIssue", function(event) {
    // Clear alerts
    $("#alertSuccess").text(""); 
    $("#alertSuccess").hide(); 
    $("#alertError").text(""); 
    $("#alertError").hide();

    // Form validation
    var status = validateIssueForm(); 
    if (status != true) 
    { 
        $("#alertError").text(status); 
        $("#alertError").show(); 
        return; 
    } 

    // ajax communication
    $.ajax({
        url: "BillAPI",
        type: "POST",
        data: $("#newBillForm").serialize(),
        dataType: "text",
        complete: function(response, status) {
            onSaveComplete(response.responseText, status);
        }
    });
});

function onSaveComplete(response, status) {

    if (status == "success") { //if the response status is success
        var resultSet = JSON.parse(response);

        if (resultSet.status.trim() === "success") { //if the json status is success
            //display success alert
            $("#alertSuccess").text("Successfully Issued");
            $("#alertSuccess").show();
            
            $("#newBillGrid").show();

            //load data in json to html
            $('#customerName').text(resultSet.data.data.customerName.trim()); 
            $('#customerID').text(resultSet.data.data.customerID); 
            $('#connectionID').text(resultSet.data.data.connectionID); 
            $('#newBillTable tr:last').after('<tr><td>'+resultSet.data.data.billID+
                                            '</td><td>'+resultSet.data.data.issuedDate+
                                            '</td><td>'+resultSet.data.data.dueDate+
                                            '</td><td>'+resultSet.data.data.units+
                                            '</td><td>'+resultSet.data.data.amount+
                                            '</td><td>'+resultSet.data.data.status+'</td></tr>');

        } else if (resultSet.status.trim() === "error") { //if the json status is error
            //display error alert
            $("#alertError").text(resultSet.data);
            $("#alertError").show();
        }
    } else if (status == "error") { 
        //if the response status is error
        $("#alertError").text("Error while issuing bill");
        $("#alertError").show();
    } else { 
        //if an unknown error occurred
        $("#alertError").text("Unknown error occurred while saving");
        $("#alertError").show();
    } 

    //resetting the form
    $("#newBillForm")[0].reset();
}

// VALIDATION
function validateIssueForm() { 

    // connection Id 
    if ($("#conId").val().trim() == "") 
    { 
        return "Insert an ID."; 
    } 

    // is numerical value 
    var tmpID = $("#conId").val().trim(); 
    if (!$.isNumeric(tmpID)) 
    { 
        return "Insert a numerical value for ID."; 
    } 
    
    // meter reading
    if ($("#meterReading").val().trim() == "") 
    { 
        return "Insert a meter reading."; 
    } 
    
    // is numerical value 
    var tmpReading = $("#meterReading").val().trim(); 
    if (!$.isNumeric(tmpReading)) 
    { 
        return "Insert a numerical value for meter reading."; 
    } 
    
    return true; 
} 
 