<%@taglib prefix="d" uri="dojotags"%>

<html>
<head>
<meta charset="UTF-8">
<title>Dojo Tags Demo</title>

<d:config />

<link rel="stylesheet" type="text/css"
	href="<%=request.getContextPath()%>/resources/css/dojotags.css">

</head>

<body class="${dijitTheme}">

	<p>Form with a backing bean for initial form values and error
		validation. Try to submit a fist name which has some non-letter
		characters.</p>

	<d:page>
		<d:form id="frm1" styleClass="form" bean="${person}">
			<d:rows>
				<d:flow>
					<d:input path="firstName" />
					<d:error styleClass="error" path="firstName" />
				</d:flow>
				<d:flow>
					<d:input path="lastName" />
					<d:error styleClass="error" path="lastName" />
				</d:flow>
				<d:button label="Submit" onclick="submit" />
				<d:label id="lbl1" />
			</d:rows>
		</d:form>
	</d:page>

</body>
</html>