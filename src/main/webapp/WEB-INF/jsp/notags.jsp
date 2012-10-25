<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Testing Dojo</title>

<!-- 
	Header tags: declare dojo script, css theme
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
	Body tags: setup requires, and parser
 -->

	<script type="dojo/require">at: "dojox/mvc/at"</script>
	<script>
		require([ "dojo/_base/window", "dojo/dom-attr", "dojo/parser" ],
				function(win, domAttr, parser) {
					parser.parse();
				});
	</script>


	<!-- 
	Form tag: declare form instance, setup dojox.mvc.Group unique name from the attribute of the tag
 -->
	<script>
		require([ "dojotags/Form", "dojo/_base/kernel" ],
				function(Form, kernel) {
					if (kernel.global["frm1"] === undefined) {
						kernel.global["frm1"] = new Form({
							actionPath : "/submit"
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
		Input tag
	 -->

		First name:
		<div data-dojo-type="dijit/form/TextBox"
			data-dojo-props="value: at('rel:', 'firstName')"></div>

		<br> <br>
		<!-- 
		Input tag
	 -->
		Last name:
		<div data-dojo-type="dijit/form/TextBox"
			data-dojo-props="value: at('rel:', 'lastName')"></div>

		<br> <br>

		<!-- 
		Submit button tag
	 -->
		<div data-dojo-type="dijit/form/Button">
			<script type="dojo/connect" data-dojo-event="onClick">
frm1.submit();
	</script>
			Submit
		</div>

		<!-- 
		Message and error tags
	 -->
		<br> <br> Message from the server:
		<div data-dojo-type="dojox/mvc/Output"
			data-dojo-props="value: at('rel:', 'message')"></div>

		<br> <br>
		<div data-dojo-type="dojox/mvc/Output"
			data-dojo-props="value: at('rel:', 'error'), style: 'color: red;'"></div>

	</div>

	<!-- 
	End of form
 -->




	<script>
		require([ "dojotags/Form", "dojo/_base/kernel" ],
				function(Form, kernel) {
					kernel.global["frm2"] = new Form({
						actionPath : "/submit",
						modelData : {
							data : [ {
// 								id : 1,
								col1 : "toto",
								col2 : "tata"
							}, {
// 								id : 2,
								col1 : "bla",
								col2 : "hehe"
							}, {
// 								id : 3,
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
			var grid, dataStore;
			require([ "dojo/ready", "dijit/registry", "dojox/mvc/sync", "dojo/Stateful", "dojo/data/ObjectStore",
					"dojo/store/Memory" ], function(ready, registry, sync,
							Stateful, ObjectStore, Memory) {
				ready(function() {
					grid = registry.byId("grd1");
					
					dataStore = new Memory({
						data : []
					});

					//sync(frm2.model, "data", dataStore, "data");
					frm2.model.watch("data", function(){
						console.debug("Sotor data changed: ", frm2.model);
					});

					var store = new ObjectStore({
						objectStore : dataStore
					});

					grid.setStore(store);

					
				});
			});
		</script>
	
	
	<div data-dojo-type="dijit/form/Button">
			<script type="dojo/connect" data-dojo-event="onClick">
frm2.submit();
	</script>
			Submit
		</div>
	
		</div>
	



</body>
</html>