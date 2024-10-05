<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>Murach's Java Servlets and JSP</title>

<link rel="stylesheet" href="css/main.css?v=2" type="text/css" />
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH"
	crossorigin="anonymous">

</head>
<body>

	<div class="container w-50 mx-auto">
		<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
		<c:if test="${sqlStatement == null}">
			<c:set var="sqlStatement" value="select * from UserTest" />
		</c:if>

		<h1>The SQL Gateway</h1>
		<p>Enter an SQL statement and click the Execute button.</p>

		<p>
			<b>SQL statement:</b>
		</p>

		<div class="mb-3 d-flex gap-3">
			<input class="form-control" readonly disabled
				value="CREATE TABLE UserTest (
				  UserID INT NOT NULL AUTO_INCREMENT, 
				  FirstName VARCHAR(50), 
				  LastName VARCHAR(50), 
				  EmailAddress VARCHAR(50), 
				
				  PRIMARY KEY (UserID)
				)">
			<button class="btn btn-primary btn-choose">Choose</button>
		</div>
		<div class="mb-3 d-flex gap-3">
			<input class="form-control" readonly disabled
				value="select * from UserTest">
			<button class="btn btn-primary btn-choose">Choose</button>
		</div>
		<div class="mb-3 d-flex gap-3">
			<input class="form-control" readonly disabled
				value="INSERT INTO UserTest 
    (FirstName, LastName, EmailAddress)
VALUES 
    ('John', 'Smith', 'jsmith@gmail.com'), 
    ('Andrea', 'Steelman', 'andrea@murach.com'), 
    ('Joel', 'Murach', 'joelmurach@yahoo.com')
">
			<button class="btn btn-primary btn-choose">Choose</button>
		</div>
		<div class="mb-3 d-flex gap-3">
			<input class="form-control" readonly disabled
				value="DELETE FROM UserTest WHERE UserID > 2">
			<button class="btn btn-primary btn-choose">Choose</button>
		</div>
		<div class="mb-3 d-flex gap-3">
			<input class="form-control" readonly disabled
				value="DROP TABLE UserTest">
			<button class="btn btn-primary btn-choose">Choose</button>
		</div>

		<form class="form" action="sqlgateway" method="post">
			<textarea readonly name="sqlStatement" cols="30" rows="4">${sqlStatement}</textarea>
			<button type="submit" class="btn btn-primary mt-3 w-25 mx-auto">Execute</button>
		</form>

		<p>
			<b>SQL result:</b>
		</p>

		${sqlResult}

	</div>
	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"
		integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz"
		crossorigin="anonymous"></script>

	<script>
		const btnChooseList = document.querySelectorAll(".btn-choose");
		const textArea = document.querySelector("textarea[name='sqlStatement']");
		
		[...btnChooseList].forEach(btn => {
			btn.addEventListener("click", () => {
				console.log(btn.previousElementSibling.value);
				textArea.value = btn.previousElementSibling.value;
			
			})
		});
		</script>
<body />
</html>