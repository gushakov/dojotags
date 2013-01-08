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

	<d:page styleClass="form" view="com.github.dojotags.test.web.mvvm.Page1View">

		<d:flow>
			<d:label text="First name" />
			<d:input name="firstName" onEnter="greet" />
		</d:flow>

		<d:flow>
			<d:label text="Last name" />
			<d:input name="lastName"  onEnter="greet"/>
		</d:flow>

		<d:label name="greeting" styleClass="greeting" />

	</d:page>

</body>
</html>