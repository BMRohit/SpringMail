<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<html>
<head>
	<title>Spring MVC Multiple File Upload</title>
<style>
	body {font-family: "Trebuchet MS";}
	h1 {font-size: 1.5em;}
</style>

<script 
src="http://ajax.googleapis.com/ajax/libs/jquery/1.8.2/jquery.min.js"></script>

<script>
$(document).ready(function() {

	$('#addFile').click(function() {
		var fileIndex = $('#fileTable tr').children().length - 1;
		$('#fileTable').append(
				'<tr><td>'+
				'	<input type="file" name="files['+ fileIndex +']" required="required" />'+
				'</td></tr>');
	});
	
});
</script>
</head>
<body>
<h1>Spring Mail Example with Multiple Files Upload.</h1>

<form:form method="post" action="sendmailaction" 
		modelAttribute="mailDetails" enctype="multipart/form-data">

	
	<table>
		
		<tr>
			<td> From Email : <br/> <input name="fromEmail" type="text" required="required"/></td>
		</tr>
		<tr>
			<td> From Email Password : <br/> <input name="fromPassword" type="password" required="required" /></td>
		</tr>
		<tr>
			<td> To Email : <br/> <input name="toEmail" type="text" required="required" /></td>
		</tr>
		<tr>
			<td> Subject : <br/> <input name="subject" type="text"  required="required" /></td>
		</tr>
		<tr>
			<td> Body : <br/> <textarea name="text"  required="required" cols="60" rows="10"> </textarea></td>
		</tr>
		
		</table>
		<table id="fileTable">
		<tr>
			<td><input id="addFile" type="button" value=" Add More Attachments" /></td>
		</tr>
		<tr>
			<td><input name="files[0]" type="file"  required="required"/></td>
		</tr>
		<tr>
			<td><input name="files[1]" type="file"  required="required"/></td>
		</tr>
	</table>
	<br/><input type="submit" value="Send Email" />
</form:form>


<br/><br/>
<h5>Note : You need to login to the gmail account in browser from which email is being sent!</h5>
</body>
</html>
