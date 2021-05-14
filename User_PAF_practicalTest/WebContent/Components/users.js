$(document).ready(function () {
    if ($("#alertSuccess").text().trim() == "") {
        $("#alertSuccess").hide();
    }
    $("#alertError").hide();
});



///CLIENT-MODEL================================================================
function validateUserForm() {
	
    // FNAME-------------------------------------
    if ($("#userfirstName").val().trim() == "") {
        return "Insert First Name.";
    }
    // LNAME-------------------------------------
    if ($("#userlastName").val().trim() == "") {
        return "Insert Last Name.";
    }
    // TOTALPRICE-------------------------------
    if ($("#userpassword").val().trim() == "") {
        return "Insert Password.";
    }
    // TIME------------------------------------
    if ($("#userEmail").val().trim() == "") {
        return "Insert Email.";
    }
    return true;
}



///SAVE-BUTTON================================================================
$(document).on("click", "#btnSave", function (event) 
{
    // Clear alerts
    $("#alertSuccess").text("");
    $("#alertSuccess").hide();
    $("#alertError").text("");
    $("#alertError").hide();
    
    // Form validation
    var status = validateUserForm();
    if (status != true) 
    {
        $("#alertError").text(status);
        $("#alertError").show();
        
        return;
    }
    
    // If valid
    var type = ($("#hidUserIDSave").val() == "") ? "POST" : "PUT";
    $.ajax(
        {
            url: "UsersAPI",
            type: type,
            data: $("#formUser").serialize(),
            dataType: "text",
            complete: function (response, status) {
            	onUserSaveComplete(response.responseText, status);
            }
        });
});


function onUserSaveComplete(response, status) 
{
    	if (status == "success") 
    	{
    			var resultSet = JSON.parse(response);
    			
    			if (resultSet.status.trim() == "success") 
    			{
    					$("#alertSuccess").text("Successfully saved.");
	    				$("#alertSuccess").show();
	    				
	    				$("#divUsersGrid").html(resultSet.data);
    			} 
    			else if (resultSet.status.trim() == "error") 
    			{
    					$("#alertError").text(resultSet.data);
    					$("#alertError").show();
    			}
    	}
    	
    	else if (status == "error") 
    	{
    			$("#alertError").text("Error while saving.");
    			$("#alertError").show();
    	}	 
    	
    	else 
    	{
    			$("#alertError").text("Unknown error while saving..");
    			$("#alertError").show();
    	}
    	
    	$("#hidUserIDSave").val("");
    	$("#formUser")[0].reset();
}


///UPDATE-BUTTON================================================================
$(document).on("click", ".btnUpdate", function (event) 
{
    	$("#hidUserIDSave").val($(this).data("userid"));
    	$("#userfirstName").val($(this).closest("tr").find('td:eq(0)').text());
    	$("#userlastName").val($(this).closest("tr").find('td:eq(1)').text());
    	$("#userpassword	").val($(this).closest("tr").find('td:eq(2)').text());
    	$("#userEmail").val($(this).closest("tr").find('td:eq(3)').text());
});


///DELETE-BUTTON================================================================
$(document).on("click", ".btnRemove", function (event) 
{
    $.ajax(
        {
            url: "UsersAPI",
            type: "DELETE",
            data: "userID=" + $(this).data("userid"),
            dataType: "text",
            complete: function (response, status) 
            {
            	onUserDeleteComplete(response.responseText, status);
            }
        });
});


function onUserDeleteComplete(response, status) 
{
    	if (status == "success") 
    	{
    			var resultSet = JSON.parse(response);
    			
    			if (resultSet.status.trim() == "success") 
    			{
    					$("#alertSuccess").text("Successfully deleted.");
    					$("#alertSuccess").show();
    					
    					$("#divUsersGrid").html(resultSet.data);
    			}
    			
    			else if (resultSet.status.trim() == "error")
    			{
    					$("#alertError").text(resultSet.data);
    					$("#alertError").show();
    			}
    	} 
    	
    	else if (status == "error") 
    	{
    			$("#alertError").text("Error while deleting.");
    			$("#alertError").show();
    	} 
    	
    	else 
    	{
    			$("#alertError").text("Unknown error while deleting..");
    			$("#alertError").show();
    	}
}