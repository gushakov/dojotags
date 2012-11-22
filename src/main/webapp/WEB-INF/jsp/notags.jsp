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
	Parse directive
 -->

	<script>
/* 		require([ "dojo/parser" ],
				function(parser) {
					parser.parse();
				});
 */	</script>


	<!-- 
Page
 -->
	<script>
		require([ "dojotags/Page" ],
				function(Page) {
			
			var page1 = new Page({id: "page1"});
			console.debug("Created page ", page1);
					
				});
	</script>

	<!-- 
Button
 -->
	<script>
		require([ "dojotags/Button", ],
				function(Button) {
					var btn1 = new Button({id: "btn1", parent: page1, label: "Button 1"});
					console.debug("Created button ", btn1);
				});
	</script>

<!-- 
Label
 -->
	<script>
		require([ "dojotags/Label", ],
				function(Label) {
					var lbl1 = new Label({id: "lbl1", parent: page1, text: "Empty label"});
					console.debug("Created label ", lbl1);
				});
	</script>

	<!-- 
Button
 -->
	<script>
		require([ "dojotags/Button", ],
				function(Button) {
					var btn2 = new Button({id: "btn2", parent: page1, label: "Button 2"});
					console.debug("Created button ", btn2);
				});
	</script>

	<!-- 
Button
 -->
	<script>
		require([ "dojotags/Button", ],
				function(Button) {
					var btn3 = new Button({id: "btn3", parent: page1, label: "Button 3"});
					console.debug("Created button ", btn3);
				});
	</script>

</body>
</html>