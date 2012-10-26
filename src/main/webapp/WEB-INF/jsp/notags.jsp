<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Page without tags</title>

<!-- 
	Config
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
	Parse
 -->

	<script type="dojo/require">at: "dojox/mvc/at"</script>
	<script>
		require([ "dojo/_base/window", "dojo/dom-attr", "dojo/parser" ],
				function(win, domAttr, parser) {
					parser.parse();
				});
	</script>

		<!-- 
	Form
	 -->

	<script>
		require([ "dojotags/Form", "dojo/_base/kernel" ],
				function(Form, kernel) {
					if (kernel.global["frm1"] === undefined) {
						kernel.global["frm1"] = new Form({
							actionPath : "/submit/frm1"
						});
					} else {
						throw new Error(
								"Form with name \"frm1\" exists already.");
					}
				});
	</script>
	<div data-dojo-type="dojox/mvc/Group"
		data-dojo-props="target: frm1.model">

		<!-- 
		Input
	 -->

		First name:
		<div data-dojo-type="dijit/form/TextBox"
			data-dojo-props="value: at('rel:', 'firstName')"></div>

		<br> <br>
		<!-- 
		Input
	 -->
		Last name:
		<div data-dojo-type="dijit/form/TextBox"
			data-dojo-props="value: at('rel:', 'lastName')"></div>

		<br> <br>

		<!-- 
		Button
	 -->
		<div data-dojo-type="dijit/form/Button">
			<script type="dojo/connect" data-dojo-event="onClick">
frm1.submit();
	</script>
			Submit
		</div>

		<!-- 
		Message
	 -->
		<br> <br> Message from the server:
		<span data-dojo-type="dojox/mvc/Output"
			data-dojo-props="value: at('rel:', 'message')"></span>

		<!-- 
Error
 -->

		<br> <br>
		<span data-dojo-type="dojox/mvc/Output"
			data-dojo-props="value: at('rel:', 'error'), style: 'color: red;'"></span>

	</div>

		<!-- 
	Form
	 -->

	<script>
		require([ "dojotags/Form", "dojo/_base/kernel" ],
				function(Form, kernel) {
					kernel.global["frm2"] = new Form({
						actionPath : "/submit/frm2/addNewItem",
						modelData : {
							grd1_data : [ {
								col1 : "toto",
								col2 : "tata"
							}, {
								col1 : "bla",
								col2 : "hehe"
							}, {
								col1 : "foo",
								col2 : "bar"
							} ]
						}
					});
				});
	</script>
	<div data-dojo-type="dojox/mvc/Group"
		data-dojo-props="target: frm2.model">

		<!-- 
	Button
 -->

		<div data-dojo-type="dijit/form/Button">
			<script type="dojo/connect" data-dojo-event="onClick">
frm2.submit();
	</script>
			Add new item
		</div>


		<!-- 
 	Grid
  -->

		<table id="grd1" data-dojo-type="dojox/grid/DataGrid"
			style="width: 400px; height: 20em;">
			<thead>
				<tr>
					<th field="col1" width="200px">First Column</th>
					<th field="col2" width="200px">Second Column</th>
				</tr>
			</thead>
		</table>
		<script>
			require([ "dojo/ready", "dijit/registry", "dojox/mvc/sync",
					"dojo/Stateful", "dojo/data/ObjectStore",
					"dojo/store/Memory" ], function(ready, registry, sync,
					Stateful, ObjectStore, Memory) {
				ready(function() {
					var grid = registry.byId("grd1");

					var dataStore = new Memory({
						data : []
					});

					sync(frm2.model, "grd1_data", dataStore, "data");
					frm2.model.watch("grd1_data", function() {
						console.debug("Store data changed: ", frm2.model);
						//refresh the grid
						grid.sort();
					});

					var store = new ObjectStore({
						objectStore : dataStore
					});

					grid.setStore(store);
				});
			});
		</script>

	</div>

</body>
</html>