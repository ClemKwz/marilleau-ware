<html>
	<head>
		<title>Colored Checkbox</title>
		<style type='text/css'>
			body {
				background: #555555;
			}
			h1 {
				text-align: center;
				font-variant: small-caps;
				color: #FFF;
			}
			#canvasZoneJeu {
				background: #FFFFFF;
			}

			#canvasInfo {
				background: #FFFFFF;
			}
			#headConteneur {
			}
			#divChrono {
				background: #FFFFFF;
				width: 150;
				height: 30;
				text-align: center;
				font-size: 25px;
				font-weight: bold;
			}
			#divScore {
				background: #FFFFFF;
				width: 50;
				height: 30;
				text-align: center;
				font-size: 25px;
				font-weight: bold;
			}
		</style>
		
		<script type="text/javascript" src="js/gameCheckbox.js">
		</script>
	</head>

	<body onload="begin();">
		<h1>Colored CheckBox</h1>

		<div id="headConteneur">
			<table>
				<tr>
					<td>
						<canvas id="canvasInfo" width="200" height="30"></canvas>
					</td>
					<td>
						<div id="divChrono"></div>
					</td>
					<td>
						<div id="divScore"></div>
					</td>
				</tr>
			</table>
		</div>
		<br />
		<div id="bc_games">
			<canvas id="canvasZoneJeu" width="400" height="300">
			</canvas>
		</div>
		<div id="infos">
		</div>

	</body>
</html>
