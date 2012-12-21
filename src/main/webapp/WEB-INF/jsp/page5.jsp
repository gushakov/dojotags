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

	<p>Select widget with the update from the server on the manual open
		event.</p>

	<d:page>
		<d:select id="sel1" value="1" onopen="update" />
	</d:page>

</body>
</html>