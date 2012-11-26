<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Page without tags</title>

<!-- 
	Dojo configuration
 -->

<link rel="stylesheet" type="text/css"
	href="<%=request.getContextPath()%>/resources/dijit/themes/claro/claro.css">
<link rel="stylesheet" type="text/css"
	href="<%=request.getContextPath()%>/resources/dojox/grid/resources/claroGrid.css">
<script src="<%=request.getContextPath()%>/resources/dojo/dojo.js"
	data-dojo-config="async: true,
	packages: [{name: 'dojotags', location: '<%=request.getContextPath()%>/resources/dojotags'}]"></script>

</head>

<body class="claro">

	<!-- 
Page
 -->
	<script>
		require([ "dojo/ready", "dojotags/Page" ], function(ready, Page) {

			var page1 = new Page({
				id : "page1"
			});
			console.debug("Created page ", page1);

			ready(function() {
				console.debug("DOM is ready");
				page1.startup();
			});

		});
	</script>

	<!-- 
Row layout
 -->
	<script>
		require([ "dojotags/Rows", ], function(Rows) {
			var rows1 = new Rows({
				id : "rows1",
				parent : page1
			});
			console.debug("Created row layout ", rows1);
		});
	</script>


	<!-- 
Button
 -->
	<script>
		require([ "dojotags/Button", ], function(Button) {
			var btn1 = new Button({
				id : "btn1",
				parent : rows1,
				label : "Button 1"
			});
			console.debug("Created button ", btn1);
		});
	</script>



	<!-- 
Label
 -->
	<script>
		require([ "dojotags/Label", ], function(Label) {
			var lbl1 = new Label({
				id : "lbl1",
				parent : rows1,
				text : "Label 1"
			});
			console.debug("Created label ", lbl1);
		});
	</script>


	<!-- 
Label
 -->
	<script>
		require([ "dojotags/Label", ], function(Label) {
			var lbl2 = new Label({
				id : "lbl2",
				parent : page1,
				text : "Label 2"
			});
			console.debug("Created label ", lbl2);
		});
	</script>


	<!-- 
Button
 -->
	<script>
		require([ "dojotags/Button", ], function(Button) {
			var btn2 = new Button({
				id : "btn2",
				parent : page1,
				label : "Button 2"
			});
			console.debug("Created button ", btn2);
		});
	</script>

</body>
</html>