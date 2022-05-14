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
