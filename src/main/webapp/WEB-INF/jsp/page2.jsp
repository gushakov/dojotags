<%@taglib prefix="d" uri="dojotags"%>

<html>
<head>
<meta charset="UTF-8">
<title>Dojo Tags Demo (with tags)</title>

<d:config />

<link rel="stylesheet" type="text/css"
	href="<%=request.getContextPath()%>/resources/css/dojotags.css">

</head>

<body class="${dijitTheme}">

	<p>Trigger "onenter" event for an input element. Enter your name and press enter.</p>

	<d:page>
		<d:input wid="inp1" onenter="default"/>
		<d:label wid="lbl1"/>
	</d:page>

</body>
</html>