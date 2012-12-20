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

<br>

	<p>Setting label's attributes via the bean in widgets registry.</p>

	<d:page>
		<d:label id="lbl1" text="Toto toto"/>
		<d:button id="btn1" label="Button" onclick="default" />
	</d:page>

</body>
</html>