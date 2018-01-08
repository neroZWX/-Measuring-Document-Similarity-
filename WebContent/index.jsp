<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Document Comparison Service</title> 
<script type="text/javascript">
	function toVaild() {
		var file = document.forms[0].uploadFile.value;
		if (file == null || file == "") { //check if the document has been uploaded
			alert("choose a file and try again!");
			return false;
		}
		return true;
	}
</script>
</head>
<body>
	<center>
		<h1>Document Comparison Service</h1>
		<form method="post" action="UploadServlet" enctype="multipart/form-data" onsubmit="return toVaild()">
			<p>
				<input type="file" name="uploadFile"/>
			</p>
			<p>
				<input type="submit" value="Compare Document" />
			</p>
		</form>
	</center>
</body>
</html>