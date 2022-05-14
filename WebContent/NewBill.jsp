<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<link rel="stylesheet" href="Views/bootstrap.min.css">
		<link rel="stylesheet" href="Views/Style.css">
		<script src="Components/jquery-3.2.1.min.js"></script>
        <script src="Components/NewBill.js"></script>
		<title>Issue Bill</title>
	</head>
	<body>
		<nav class="navbar navbar-expand-lg navbar-dark bg-primary mb-5">
			<a class="navbar-brand" href="BillHome.jsp">ElectroGrid</a>
			<button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarNavAltMarkup" aria-controls="navbarNavAltMarkup" aria-expanded="false" aria-label="Toggle navigation">
				<span class="navbar-toggler-icon"></span>
			</button>
			<div class="collapse navbar-collapse" id="navbarNavAltMarkup">
				<div class="navbar-nav">
					<a class="nav-item nav-link" href="BillHome.jsp">Home</a>
					<a class="nav-item nav-link active" href="NewBill.jsp">Issue Bill <span class="sr-only">(current)</span></a>
				</div>
			</div>
		</nav>
		<div class="container">
			<h1 class="mb-3 title">Issue Bill</h1>
			<form id="newBillForm" name="newBillForm" method="POST" class="formBox" action="NewBill.jsp"> 
				<div class="row">
					<div class="col-xl-8">
						<div class="form-group">
							<label for="conId">Connection ID</label>
							<input type="text" class="form-control" id="conId" name="conId" placeholder="Connection ID">
						</div>
						<div class="form-group">
							<label for="meterReading">Meter Reading</label>
							<input type="text" class="form-control" id="meterReading" name="meterReading" placeholder="Meter Reading">
						</div>
					</div>
					<div class="col-xl-4">
						
					</div>
				</div>                   
				<input 
					id="btnIssue" 
					name="btnIssue" 
					type="button" 
					value="Issue Bill" 
					class="btn btn-primary"
				>
			</form>
		
			<br>
			<div id="alertSuccess" class="alert alert-success"></div>
			<div id="alertError" class="alert alert-danger"></div>
			<br>
			<div id="newBillGrid" class="formBox">
				<div class="row">
					<div class="col-xl-12" >
						<h3 class="title mb-3" align="center">ElectroGrid Monthly Bill</h3>
						
						<h5>Customer Name: <span id="customerName"></span></h5>
						<h5>Customer ID: <span id="customerID"></span></h5>
						<h5>Connection ID: <span id="connectionID"></span></h5>
						<hr>
					</div>
					<div class="col-xl-12">
						<div id="newBillTable" class="blue-table box-table">
							<table >
								<tr>
									<th>Bill ID</th>
									<th>Issued Date</th>
									<th>Due Date</th>
									<th>Units</th>
									<th>Amount</th>
									<th>Payment Status</th>
								</tr>
							</table>
						</div>
					</div>
				</div>
			</div>
		</div>
	</body>
</html>