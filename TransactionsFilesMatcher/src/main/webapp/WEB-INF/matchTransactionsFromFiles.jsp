<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix = "form" uri = "http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Paymentology</title>
</head>
<body>
	<h2 style="text-align: center;">Try matching the transactions within two Csv files</h2>

	<form action="${pageContext.request.contextPath}/match-transactions" method="post" enctype="multipart/form-data">
		<table style="width: 30%; margin-left: auto; margin-right: auto; text-align: center;">
			<thead>
				<tr>
					<th>File 1</th>
					<th>File 2</th>
				</tr>
			</thead>
			<tbody>
				<tr>
					<td> 
						<label class="labelFile" for="files" style="background-color: grey; color: white;cursor: pointer; font-weight: bold;">Upload File</label> 
						<input id="files" name="file1" style="visibility: hidden;" type="file" accept=".csv">
					</td>
					<td>
						<label class="labelFile2" for="files2" style="background-color: grey; color: white;cursor: pointer; font-weight: bold;">Upload File</label> 
						<input id="files2" name="file2" style="visibility: hidden;" type="file" accept=".csv">
					</td>
				</tr>
			</tbody>
		</table>
		<input type="submit" value="Start">
	</form>
</body>
</html>