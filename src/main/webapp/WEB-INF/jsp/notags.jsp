<html>
<head>
<meta charset="UTF-8">
<title>Page without tags</title>

<!-- 
	Dojo configuration
 -->

<link rel="stylesheet" type="text/css"
	href="/resources/dijit/themes/claro/claro.css">
<link rel="stylesheet" type="text/css"
	href="/resources/dojox/grid/resources/claroGrid.css">
<script src="/resources/dojo/dojo.js"
	data-dojo-config="async: true,
	packages: [{name: 'dojotags', location: '/resources/dojotags'}]"></script>

</head>

<body class="claro">

	<!-- 
Page
 -->
	<script>
		require([ "dojo/ready", "dojotags/Page", "dojotags/Button", "dojotags/Label",
				"dojotags/Rows", "dojotags/Input", "dojotags/Form" ], function(ready, Page, Button,
				Label, Rows, Input, Form) {

			var pag1 = new Page({
				id : "pag1"
			});
			console.debug("Created page ", pag1);

			var frm1 = new Form({
				id : "frm1",
				parent : pag1
			});

			var row1 = new Rows({
				id : "row1",
				parent : frm1
			});
						
			var inp1 = new Input({
				id : "inp1",
				parent : row1,
				value : "toto",
				path : "firstName"
			});

			var inp2 = new Input({
				id : "inp2",
				parent : row1,
				value : "tata",
				path : "lastName"
			});

			ready(function() {
				console.debug("DOM is ready");
				pag1.startup();
			});

		});
	</script>