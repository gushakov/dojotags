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

	<d:page compress="true">
		<d:rows wclass="box" spacer="2em">
			<d:flow wclass="line" spacer="4em">
				<d:label wclass="first" text="lorem"/>
				<d:label wclass="second" text="ipsum" />
			</d:flow>
			<d:flow wclass="line" spacer="4em">
				<d:label wclass="first" text="lorem"/>
				<d:label wclass="second" text="ipsum" />
			</d:flow>
			<d:flow wclass="line" spacer="4em">
				<d:label wclass="first" text="lorem"/>
				<d:label wclass="second" text="ipsum" />
			</d:flow>
			<d:flow wclass="line" spacer="4em">
				<d:label wclass="first" text="lorem"/>
				<d:label wclass="second" text="ipsum" />
			</d:flow>
		</d:rows>
	</d:page>

</body>
</html>