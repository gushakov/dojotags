<%@taglib prefix="d" uri="dojotags"%>
<%@ taglib prefix="g" uri="http://granule.com/tags"%>

<html>
<head>
<meta charset="UTF-8">
<title>Dojotags Demo</title>

<d:config />

</head>

<body class="${dijitTheme}">
	<!-- g:compress -->
	<d:page>
		<d:flow spacerWidth="3em">
			<d:label widgetId="lbl1" text="Hello" />
			<d:button widgetId="btn1" label="Button" />
		</d:flow>
	</d:page>
	<!-- /g:compress -->
</body>
</html>