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

	<p>Click on the button for the Ajax round-trip to the server.</p>

	<d:page>
		<d:label wid="lbl1" text="Hello" />
		<d:button wid="btn1" label="Button" onclick="default" />
	</d:page>

</body>
</html>