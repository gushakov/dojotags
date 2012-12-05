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
				"dojotags/Rows" ], function(ready, Page, Button, Label, Rows) {

			var page_1354615034125 = new Page({
				id : "page_1354615034125"
			});
			console.debug("Created page ", page_1354615034125);

			ready(function() {
				console.debug("DOM is ready");
				page_1354615034125.startup();
			});

			var rows_1354615034131 = new Rows({
				id : "rows_1354615034131",
				parent : page_1354615034125,
				spacerHeight : ""
			});
			console.debug("Created row layout ", rows_1354615034131);
			var lbl1 = new Label({
				id : "label_1354615034134",
				parent : rows_1354615034131,
				text : "toto"
			});
			console.debug("Created label ", label_1354615034134);
			var button_1354615034137 = new Button({
				id : "button_1354615034137",
				parent : rows_1354615034131,
				label : "toto"
			});
			console.debug("Created button ", button_1354615034137);
		});
	</script>