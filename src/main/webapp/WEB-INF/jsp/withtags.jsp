<%@taglib prefix="d" uri="http://unil.ch/dojotags"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Page with tags</title>

<d:config />

</head>

<body class="claro">

	<d:parse />

	Simple form:
	<div style="width: 400px; border: 1px solid lightgray; padding: 5px;">
		<d:form name="frm1" actionPath="/submit">
			First name: 
			<d:textbox path="firstName" />
			<br>
			Last name:
			<d:textbox path="lastName" />
			<br>
			<d:button label="Submit the form" />
			<br>
			Message:
			<d:output path="message" />
		</d:form>
	</div>

	Form with a grid:
	<div style="width: 500px; border: 1px solid lightgray; padding: 5px;">
		<d:form name="frm2" actionPath="/submit">
			<d:button action="addNewItem" label="Add new item"/>
			<br>
			<d:grid name="grd1" width="450px">
				<d:gridLayout>
					<d:gridColumn field="col1" name="First Column" />
					<d:gridColumn field="col2" name="Second Column" />
				</d:gridLayout>
			</d:grid>
		</d:form>
	</div>

</body>

</html>