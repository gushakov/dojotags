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

	<d:page view="com.github.dojotags.test.web.mvvm.Page1View">

		<d:input name="firstName" onEnter="changeFirstName" />

	</d:page>

</body>
</html>