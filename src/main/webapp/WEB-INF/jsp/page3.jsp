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

	<p>Rows and flow layouts with custom CSS.</p>

	<d:page>
		<d:rows styleClass="box" spacer="2em">
			<d:flow styleClass="line" spacer="4em">
				<d:label styleClass="first" text="lorem"/>
				<d:label styleClass="second" text="ipsum" />
			</d:flow>
			<d:flow styleClass="line" spacer="4em">
				<d:label styleClass="first" text="lorem"/>
				<d:label styleClass="second" text="ipsum" />
			</d:flow>
			<d:flow styleClass="line" spacer="4em">
				<d:label styleClass="first" text="lorem"/>
				<d:label styleClass="second" text="ipsum" />
			</d:flow>
			<d:flow styleClass="line" spacer="4em">
				<d:label styleClass="first" text="lorem"/>
				<d:label styleClass="second" text="ipsum" />
			</d:flow>
		</d:rows>
	</d:page>

</body>
</html>