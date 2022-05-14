$(document).ready(function() {
    if ($('#alertSuccess').text().trim() == "") {
        $('#alertSuccess').hide();
    }
    $('#alertError').hide();
    $('#updateForm').hide();
})

// SAVE
$(document).on("click","#btnSearch", function(event) {
    // Clear alerts
    $("#alertSuccess").text(""); 
    $("#alertSuccess").hide(); 
    $("#alertError").text(""); 
    $("#alertError").hide();

    // Form validation
    var status = validateSearchForm(); 
    if (status != true) 
    { 
        $("#alertError").text(status); 
        $("#alertError").show(); 
        return; 
    } 

    // ajax communication
    $.ajax({
        url: "BillAPI",
        type: "GET",
        data: $("#searchForm").serialize(),
        dataType: "text",
        complete: function(response, status) {
            onSearchComplete(response.responseText, status);
        }
    });
});

function onSearchComplete(response, status) {
    if (status == "success") { //if the response status is success
        // reading response object
        var resultSet = JSON.parse(response);

        if (resultSet.status.trim() === "success") { //if the json status is success
            //load data in json to html
            $("#divBillsGrid").html(resultSet.data);

        } else if (resultSet.status.trim() === "error") { //if the json status is error
            //display error alert
            $("#alertError").text(resultSet.data);
            $("#alertError").show();
        }
    } else if (status == "error") { 
        //if the response status is error
        $("#alertError").text("Error while searching");
        $("#alertError").show();
    } else { 
        //if an unknown error occurred
        $("#alertError").text("Unknown error occurred while searching");
        $("#alertError").show();
    } 
}

// UPDATE
$(document).on("click", ".btnUpdate", function(event) 
{ 
    // Clear alerts
    $("#alertSuccess").text(""); 
    $("#alertSuccess").hide(); 
    $("#alertError").text(""); 
    $("#alertError").hide();

    //get item id from the data-itemid attribute in update button
    $("#billID").val($(this).data('billid')); 
    //get data from <td> element
    $("#connectionID").val($(this).closest("tr").find('td:eq(0)').text()); 

    $('#updateForm').show();
});

$(document).on("click","#update", function(event) {
    // Clear alerts
    $("#alertSuccess").text(""); 
    $("#alertSuccess").hide(); 
    $("#alertError").text(""); 
    $("#alertError").hide();

    // ajax communication
    $.ajax({
        url: "BillAPI",
        type: "PUT",
        data: $("#updateForm").serialize(),
        dataType: "text",
        complete: function(response, status) {
            onBillUpdateComplete(response.responseText, status);
        }
    });
});

// after completing update request
function onBillUpdateComplete(response, status) {

    if (status == "success") { //if the response status is success
        var resultSet = JSON.parse(response);

        if (resultSet.status.trim() === "success") { //if the json status is success
            //display success alert
            $("#alertSuccess").text("Successfully updated");
            $("#alertSuccess").show();
    
            //load data in json to html
            $("#divBillsGrid").html(resultSet.data.data);

        } else if (resultSet.status.trim() === "error") { //if the json status is error
            //display error alert
            $("#alertError").text(resultSet.data);
            $("#alertError").show();
        }
    } else if (status == "error") { 
        //if the response status is error
        $("#alertError").text("Error while updating");
        $("#alertError").show();
    } else { 
        //if an unknown error occurred
        $("#alertError").text("Unknown error occurred while updating");
        $("#alertError").show();
    } 

    $("#updateForm")[0].reset();
    $('#updateForm').hide();
}
