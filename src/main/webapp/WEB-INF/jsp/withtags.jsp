<%@taglib prefix="d" uri="http://unil.ch/dojotags"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Testing Dojo JSP Taglib</title>

<d:config />

</head>

<body class="claro">

	<d:parse />


	<d:form name="frm1" actionPath="/submit">


First name: <d:textbox path="firstName" />
		<br>
		<br>

		<d:button>Submit</d:button>
		
		<br>
		<br>
Message: <d:output path="message"/>		

	</d:form>
	
	
	<d:form name="frm3" actionPath="/submit">


First name: <d:textbox path="firstName" />
		<br>
		<br>

		<d:button>Submit</d:button>
		
		<br>
		<br>
Message: <d:output path="message"/>		

	</d:form>

<br>
<br>

	<d:form name="frm2" actionPath="/toto">


First name: <d:textbox path="firstName" />
		<br>
		<br>
Last name: <d:textbox path="lastName" />
		<br>
		<br>

		<d:button>Submit</d:button>

		<br>
		<br>

Error: <span style="color: red;"><d:output path="error"/></span>		

	</d:form>


</body>

</html>