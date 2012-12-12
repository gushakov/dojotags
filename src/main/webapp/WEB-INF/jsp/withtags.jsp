<%@taglib prefix="d" uri="dojotags"%>

<html>
<head>
<meta charset="UTF-8">
<title>Dojo Tags Demo (with tags)</title>

<d:config />

<style type="text/css">
.form {
	border: lightblue thin solid;
	background-color: azure;
	width: 50%;
	padding: 5px;
}

.error {
	color: maroon;
}
</style>

</head>

<body class="${dijitTheme}">

	<d:page widgetId="pag1">
		<d:rows>
			<d:label widgetId="lbl1" text="test" />
			<d:button widgetId="btn1" label="Button" onClick="default" />
			<d:form widgetId="frm1" widgetClass="form" formClass="com.github.dojotags.test.form.Person">
				<d:rows>
					<d:flow>
						<d:label text="Enter first name:" />
						<d:input path="firstName" />
						<d:error path="firstName" widgetClass="error" />
					</d:flow>
					<d:flow>
						<d:label text="Enter last name:" />
						<d:input path="lastName" />
					</d:flow>
					<d:button label="Submit" onClick="submit" />
				</d:rows>
			</d:form>
		</d:rows>
	</d:page>

</body>
</html>