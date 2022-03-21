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
<style type="text/css">
	.labelFile{background-color: black; color: white; cursor: pointer; font-weight: bold;}
	.labelFile:hover {color: navajowhite}
	
	.submitButton{border: 1px solid black; width: 150px; margin-left: auto; margin-right: auto; background-color: #0F1F38; color: white; font-weight: bold; cursor: pointer; border-radius: 3px; height: 30px;}
	.submitButton:hover {background-color: white; color: #0F1F38; border-color: black;}
</style>
</head>
<body>
	<img src="${pageContext.request.contextPath}/images/paymentologySymbol.png" style="height: 50px;">
	<br/><br/>
	<div style="text-align: center;"><label style="text-align: center; font-family: fangsong; font-weight: bold;">Try matching the transactions within two Csv files. Just upload 2 files and click 'Match Files'.</label></div>
	<br/>
	<form action="${pageContext.request.contextPath}/matchFiles" method="post" enctype="multipart/form-data">
		<c:if test="${not empty error}"><h3 style="border: 1px solid black; width: 60%; border-style: double; color: darkred; text-align: center; margin-left: auto; margin-right: auto;">${error}</h3><br/></c:if>
		<table style="width: 40%; margin-left: auto; margin-right: auto; text-align: center; min-width: 500px;">
			<thead>
				<tr>
					<th>File 1</th>
					<th>File 2</th>
				</tr>
			</thead>
			<tbody>
				<tr>
					<td> 
						<div style="background-color: black; width: 92px; height: 20px; border-radius: 20px; text-align: center; margin: 0 auto;">
							<label class="labelFile" for="files">Upload File</label> 
							<input id="files" name="file1" style="visibility: hidden;" type="file" accept=".csv">
						</div>
					</td>
					<td>
						<div style="background-color: black; width: 92px; height: 20px; border-radius: 20px; text-align: center; text-align: center; margin: 0 auto;">
							<label class="labelFile" for="files2">Upload File</label> 
							<input id="files2" name="file2" style="visibility: hidden;" type="file" accept=".csv">
						</div>
					</td>
				</tr>
			</tbody>
		</table>
		<div style="text-align: center;">
		<input class="submitButton" style="" 
				type="submit" value="Match Files"></div>
	</form>
	<br/>
	<c:if test="${not empty perfectlyMatchedNumber}"><div style="text-align: center; font-size: 20px; text-decoration: underline; color: darkblue;"><label style="text-align: center; font-family: fangsong; font-weight: bold;">Number of perfectly matched transactions: ${perfectlyMatchedNumber}</label></div><br/><br/></c:if>
	
	<c:if test="${not empty probableMatches}">
		<div style="text-align: center;"><label style="text-align: center; font-family: fangsong; font-weight: bold;">Some transactions were not correlated when matching the files. The list below shows possible matches  of those, separated by lines.</label></div>
		<div style="text-align: center;"><label style="text-align: center; font-family: fangsong; color: darkgreen; font-weight: bold;">Number of possible matches: ${possibleMatchesNumber}</label></div>
		<table style="width: 98%; margin-left: auto; margin-right: auto; text-align: center; border-style: groove; border-collapse: collapse;">
			<thead style="display: block;">
				<tr>
					<th style="width:12%; color: white; background-color: #0F1F38; min-width: 100px">PROFILE NAME</th>
					<th style="width:8%; color: white; background-color: #0F1F38; min-width: 140px">TRANSACTION AMOUNT</th>
					<th style="width:11%; color: white; background-color: #0F1F38; min-width: 110px">TRANSACTION DATE</th>
					<th style="width:9%; color: white; background-color: #0F1F38; min-width: 130px">TRANSACTION DESCRIPTION</th>
					<th style="width:11%; color: white; background-color: #0F1F38; min-width: 120px">TRANSACTION ID</th>
					<th style="width:25%; color: white; background-color: #0F1F38; min-width: 200px">TRANSACTION NARRATIVE</th>
					<th style="width:8%; color: white; background-color: #0F1F38; min-width: 120px">TRANSACTION TYPE</th>
					<th style="width:16%; color: white; background-color: #0F1F38; min-width: 360px">WALLET REFERENCE</th>
				</tr>
			<tbody style=" height: 250px; overflow-y: auto; overflow-x: hidden; display: block;">
				<c:forEach items="${probableMatches}" var="m" varStatus="loop">
					<c:choose>
						<c:when test="${(loop.index + 1) % 2 eq 0}">
							<tr>
								<td style="width:12%; border-bottom-style: ridge; border-bottom: 1.8px solid black; border-bottom-color: black; height: 35px; min-width: 90px">${m.profileName}</td>
								<td style="width:8%; border-bottom-style: ridge; border-bottom: 1.8px solid black;  border-bottom-color: black; height: 35px; min-width: 130px">${m.transactionAmount}</td>
								<td style="width:11%; border-bottom-style: ridge; border-bottom: 1.8px solid black;  border-bottom-color: black; height: 35px; min-width: 150px">${m.transactionDate}</td>
								<td style="width:9%; border-bottom-style: ridge; border-bottom: 1.8px solid black;  border-bottom-color: black; height: 35px; min-width: 100px">${m.transactionDescription}</td>
								<td style="width:11%; border-bottom-style: ridge; border-bottom: 1.8px solid black;  border-bottom-color: black; height: 35px; min-width: 140px">${m.transactionId}</td>
								<td style="width:25%; border-bottom-style: ridge; border-bottom: 1.8px solid black;  border-bottom-color: black; height: 35px; min-width: 130px">${m.transactionNarrative}</td>
								<td style="width:8%; border-bottom-style: ridge; border-bottom: 1.8px solid black;  border-bottom-color: black; height: 35px; min-width: 100px">${m.transactionType}</td>
								<td style="width:16%; border-bottom-style: ridge; border-bottom: 1.8px solid black;  border-bottom-color: black;height: 35px; min-width: 100px">${m.walletReference}</td>
							</tr>
						</c:when>
						<c:otherwise>
							<tr>
								<td>${m.profileName}</td>
								<td>${m.transactionAmount}</td>
								<td>${m.transactionDate}</td>
								<td>${m.transactionDescription}</td>
								<td>${m.transactionId}</td>
								<td>${m.transactionNarrative}</td>
								<td>${m.transactionType}</td>
								<td>${m.walletReference}</td>
							</tr>					
						</c:otherwise>
					</c:choose>
				</c:forEach>
			</tbody>
		</table>
	</c:if>
	<br/>
	<c:if test="${not empty notFoundMatches}">
		<div style="text-align: center;"><label style="text-align: center; font-family: fangsong; font-weight: bold;">Below are transactions where possible matches were not quickly found.</label></div>
		<div style="text-align: center;"><label style="text-align: center; font-family: fangsong; color: darkred; font-weight: bold;">Number of not found matches: ${notFoundMatchedNumber}</label></div>
		<table style="width: 98%; margin-left: auto; margin-right: auto; text-align: center; border-style: groove; border-collapse: collapse;">
			<thead style="display: block;">
				<tr>
					<th style="width:12%; color: white; background-color: #0F1F38; min-width: 100px">PROFILE NAME</th>
					<th style="width:8%; color: white; background-color: #0F1F38; min-width: 140px">TRANSACTION AMOUNT</th>
					<th style="width:11%; color: white; background-color: #0F1F38; min-width: 110px">TRANSACTION DATE</th>
					<th style="width:9%; color: white; background-color: #0F1F38; min-width: 130px">TRANSACTION DESCRIPTION</th>
					<th style="width:11%; color: white; background-color: #0F1F38; min-width: 120px">TRANSACTION ID</th>
					<th style="width:25%; color: white; background-color: #0F1F38; min-width: 200px">TRANSACTION NARRATIVE</th>
					<th style="width:8%; color: white; background-color: #0F1F38; min-width: 120px">TRANSACTION TYPE</th>
					<th style="width:16%; color: white; background-color: #0F1F38; min-width: 360px">WALLET REFERENCE</th>
				</tr>
			<tbody style=" height: 250px; overflow-y: auto; overflow-x: hidden; display: block;">
				<c:forEach items="${notFoundMatches}" var="m" varStatus="loop">
					<tr>
						<td style="width:12%; border-bottom-style: ridge; border-bottom: 1.8px solid black; border-bottom-color: black; height: 35px; min-width: 90px">${m.profileName}</td>
						<td style="width:8%; border-bottom-style: ridge; border-bottom: 1.8px solid black;  border-bottom-color: black; height: 35px; min-width: 130px">${m.transactionAmount}</td>
						<td style="width:11%; border-bottom-style: ridge; border-bottom: 1.8px solid black;  border-bottom-color: black; height: 35px; min-width: 150px">${m.transactionDate}</td>
						<td style="width:9%; border-bottom-style: ridge; border-bottom: 1.8px solid black;  border-bottom-color: black; height: 35px; min-width: 100px">${m.transactionDescription}</td>
						<td style="width:11%; border-bottom-style: ridge; border-bottom: 1.8px solid black;  border-bottom-color: black; height: 35px; min-width: 140px">${m.transactionId}</td>
						<td style="width:25%; border-bottom-style: ridge; border-bottom: 1.8px solid black;  border-bottom-color: black; height: 35px; min-width: 130px">${m.transactionNarrative}</td>
						<td style="width:8%; border-bottom-style: ridge; border-bottom: 1.8px solid black;  border-bottom-color: black; height: 35px; min-width: 100px">${m.transactionType}</td>
						<td style="width:16%; border-bottom-style: ridge; border-bottom: 1.8px solid black;  border-bottom-color: black;height: 35px; min-width: 100px">${m.walletReference}</td>
					</tr>					
				</c:forEach>
			</tbody>
		</table>
	</c:if>
	<br/>
	<c:if test="${not empty processingErrorsTransactions}">
		<div style="text-align: center;"><label style="text-align: center; font-family: fangsong; font-weight: bold;">Check the transactions below, they couldn't be processed due to format or other issues from both files.</label></div>
		<div style="text-align: center;"><label style="text-align: center; font-family: fangsong; color: darkblue; font-weight: bold;">Number of transactions with processing errors: ${processingErrorsNumber}</label></div>
		<table style="width: 98%; margin-left: auto; margin-right: auto; text-align: center; border-style: groove; border-collapse: collapse;">
			<thead style="display: block;">
				<tr>
					<th style="width:12%; color: white; background-color: darkred; min-width: 100px">PROFILE NAME</th>
					<th style="width:8%; color: white; background-color: darkred; min-width: 140px">TRANSACTION AMOUNT</th>
					<th style="width:11%; color: white; background-color: darkred; min-width: 110px">TRANSACTION DATE</th>
					<th style="width:9%; color: white; background-color: darkred; min-width: 130px">TRANSACTION DESCRIPTION</th>
					<th style="width:11%; color: white; background-color: darkred; min-width: 120px">TRANSACTION ID</th>
					<th style="width:25%; color: white; background-color: darkred; min-width: 200px">TRANSACTION NARRATIVE</th>
					<th style="width:8%; color: white; background-color: darkred; min-width: 120px">TRANSACTION TYPE</th>
					<th style="width:16%; color: white; background-color: darkred; min-width: 310px">WALLET REFERENCE</th>
					<th style="width:16%; color: white; background-color: darkred; min-width: 100px">FILE</th>
				</tr>
			<tbody style=" height: 250px; overflow-y: auto; overflow-x: hidden; display: block;">
				<c:forEach items="${processingErrorsTransactions}" var="m" varStatus="loop">
					<tr>
						<td style="width:12%; border-bottom-style: ridge; border-bottom: 1.8px solid black; border-bottom-color: black; height: 35px; min-width: 90px">${m.profileName}</td>
						<td style="width:8%; border-bottom-style: ridge; border-bottom: 1.8px solid black;  border-bottom-color: black; height: 35px; min-width: 130px">${m.transactionAmount}</td>
						<td style="width:11%; border-bottom-style: ridge; border-bottom: 1.8px solid black;  border-bottom-color: black; height: 35px; min-width: 150px">${m.transactionDate}</td>
						<td style="width:9%; border-bottom-style: ridge; border-bottom: 1.8px solid black;  border-bottom-color: black; height: 35px; min-width: 100px">${m.transactionDescription}</td>
						<td style="width:11%; border-bottom-style: ridge; border-bottom: 1.8px solid black;  border-bottom-color: black; height: 35px; min-width: 140px">${m.transactionId}</td>
						<td style="width:25%; border-bottom-style: ridge; border-bottom: 1.8px solid black;  border-bottom-color: black; height: 35px; min-width: 130px">${m.transactionNarrative}</td>
						<td style="width:8%; border-bottom-style: ridge; border-bottom: 1.8px solid black;  border-bottom-color: black; height: 35px; min-width: 100px">${m.transactionType}</td>
						<td style="width:16%; border-bottom-style: ridge; border-bottom: 1.8px solid black;  border-bottom-color: black;height: 35px; min-width: 100px">${m.walletReference}</td>
						<td style="width:10%; border-bottom-style: ridge; border-bottom: 1.8px solid black;  border-bottom-color: black;height: 35px; min-width: 100px">${m.file}</td>
					</tr>					
				</c:forEach>
			</tbody>
		</table>
	</c:if>
	<br/><br/>
</body>
</html>