<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
   
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<link rel="stylesheet" href="Views/bootstrap.min.css">
		<link rel="stylesheet" href="Views/Style.css">
		<script src="Components/jquery-3.2.1.min.js"></script>
        <script src="Components/Bill.js"></script>
		<title>Bills Home</title>
	</head>
	<body>
		<nav class="navbar navbar-expand-lg navbar-dark bg-primary mb-5">
		  <a class="navbar-brand" href="BillHome.jsp">ElectroGrid</a>
		  <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarNavAltMarkup" aria-controls="navbarNavAltMarkup" aria-expanded="false" aria-label="Toggle navigation">
		    <span class="navbar-toggler-icon"></span>
		  </button>
		  <div class="collapse navbar-collapse" id="navbarNavAltMarkup">
		    <div class="navbar-nav">
		      <a class="nav-item nav-link active" href="BillHome.jsp">Home <span class="sr-only">(current)</span></a>
		      <a class="nav-item nav-link" href="NewBill.jsp">Issue Bill</a>
		    </div>
		  </div>
		</nav>
		<div class="container">
			<h1 class="mb-3 title">Search Bills</h1>
			<form id="searchForm" name="searchForm" method="POST" action="BillHome.jsp">
				<div class="row">
					<div class="col-xl-3">
						<select class="form-control" id="idType" name="idType">
							<option value="Connection">Connection ID</option>
							<option value="Customer">Customer ID</option>
						</select>
					</div>
					<div class="col-xl-7">
						<input 
							id="idCode" 
							name="idCode" 
							type="text" 
							class="form-control"
							placeholder="Enter the ID"
						>
					</div>
					<div class="col-xl-2">
						<input 
							id="btnSearch" 
							name="btnSearch" 
							type="button" 
							value="Search Bills" 
							class="btn btn-success"
						>
					</div>
				</div>
			</form>
			<br>
			<form id="updateForm" name="updateForm" method="PUT" action="BillHome.jsp">
				<div class="row">
					<div class="col-xl-3">
						<div class="form-group">
							<label for="billID">Bill ID</label>
							<input 
								id="billID" 
								name="billID" 
								type="text" 
								class="form-control"
								readonly
							>
						</div>
					</div>
					<div class="col-xl-3">
						<div class="form-group">
							<label for="connectionID">Connection ID</label>
							<input 
								id="connectionID" 
								name="connectionID" 
								type="text" 
								class="form-control"
								readonly
							>
						</div>
					</div>
					<div class="col-xl-3">
						<div class="form-group">
							<label for="paymentID">Payment ID</label>
							<input 
								id="paymentID" 
								name="paymentID" 
								type="text" 
								class="form-control"
								placeholder="Enter payment ID"
							>
						</div>
					</div>
					<div class="col-xl-3 mt-4">
						<input 
							id="update" 
							name="update" 
							type="button" 
							value="Update Bills" 
							class="btn btn-success"
						>
					</div>
				</div>
			</form>
		</div>
		<br>
		<div class="container-fluid">
			<div id="alertSuccess" class="alert alert-success"></div>
			<div id="alertError" class="alert alert-danger"></div>
			<br>
			<div id="divBillsGrid" class="blue-table box-table"></div>
		</div>
	</body>
</html>