<html>
<head>
<meta charset="UTF-8">
<title>Dojo Tags Demo (no tags)</title>

<link rel="stylesheet" type="text/css"
	href="/resources/dijit/themes/claro/claro.css">
<link rel="stylesheet" type="text/css"
	href="/resources/dojox/grid/resources/claroGrid.css">
<script src="/resources/dojo/dojo.js"
	data-dojo-config="async: true,
	packages: [{name: 'dojotags', location: '/resources/dojotags'}]"></script>

<style type="text/css">
.errors {
	color: maroon;
}
</style>

</head>

<body class="claro">

	<script>
		require([ "dojo/ready", "dojotags/Page", "dojotags/Button", "dojotags/Label",
				"dojotags/Rows", "dojotags/Input", "dojotags/Form", "dojotags/Error" ], function(
				ready, Page, Button, Label, Rows, Input, Form, Error) {

			var pag1 = new Page({
				id : "pag1"
			});
			console.debug("Created page ", pag1);

			var frm1 = new Form({
				id : "frm1",
				parent : pag1
			});

			var inp1 = new Input({
				id : "inp1",
				parent : frm1,
				value : "toto",
				path : "firstName",
				onEnter : "ignore"
			});

			var btn2 = new Button({
				id : "btn2",
				parent : frm1,
				label : "Button",
				onClick : "submit"
			});

			var err1 = new Error({
				id : "err1",
				parent : frm1,
				path : "firstName",
				errorClass : "errors"
			});

			ready(function() {
				console.debug("DOM is ready");
				pag1.startup();
			});

		});
	</script>