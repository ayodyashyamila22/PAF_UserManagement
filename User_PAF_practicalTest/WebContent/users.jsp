<%@ page import="com.User"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>User Management</title>

<script src="Components/jquery-3.2.1.min.js"></script>
<script src="Components/users.js"></script>
<link rel="stylesheet" href="Views/bootstrap.min.css">

</head>
<body>
	<div class="container">
		<div class="row">
			<div class="col">
			
				<h1>User Management</h1>
				
				<form id="formUser" name="formUser">
 						User FirstName:
 						<input id="userfirstName" name="userfirstName" type="text" class="form-control form-control-sm">
 						<br> 
 						
 						User LastName:
 						<input id="userlastName" name="userlastName" type="text" class="form-control form-control-sm">
 						<br> 
 						
 						User Password:
 						<input id="userpassword" name="userpassword" type="text" class="form-control form-control-sm">
 						<br> 
 						
 						User Email:
 						<input id="userEmail" name="userEmail" type="text" class="form-control form-control-sm">
 						<br>
 						
 						
 						<input id="btnSave" name="btnSave" type="button" value="Save" class="btn btn-primary">
 						<input type="hidden" id="hidUserIDSave" name="hidUserIDSave" value="">
				</form>

				<br>

				<div id="alertSuccess" class="alert alert-success"></div>
				<div id="alertError" class="alert alert-danger"></div>

				<br>
				<div id="divUsersGrid">
					<%
							User usertObjRead = new User();
							out.print(usertObjRead.readUser());
					%>
				</div>

			</div>
		</div>
	</div>
</body>
</html>