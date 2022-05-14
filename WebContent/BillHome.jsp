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
	</body>
</html>