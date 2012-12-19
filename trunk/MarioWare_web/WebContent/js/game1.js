
var xhr;

/* Context graphique */
var context;

function begin1(x,y){
	var elem = document.getElementById('canvasElem');
	if (!elem || !elem.getContext) {
		return;
	}
	context = elem.getContext('2d');
	if (!context) {
		return;
	}
	
	//document.getElementById('headConteneur').innerHTML = "Click on the (" + x + "," + y + ") pixel.";

	var conteneur = document.getElementById('bc_games');
	
	context.fillStyle="#EE0000";
	context.fillRect(x, y, 5, 5);
	
	conteneur.onmousemove = function(event){
		var left = 0;
		var top = 0;
		/*On récupère l'élément*/
		var e = document.getElementById('bc_games');
		/*Tant que l'on a un élément parent*/
		while (e.offsetParent != undefined && e.offsetParent != null)
		{
			/*On ajoute la position de l'élément parent*/
			left += e.offsetLeft + (e.clientLeft != null ? e.clientLeft : 0);
			top += e.offsetTop + (e.clientTop != null ? e.clientTop : 0);
			e = e.offsetParent;
		}
		var x = event.clientX;
		var y = event.clientY;
		var divInfo = document.getElementById('infos');
		x = x - left;
		y = y - top;
		divInfo.innerHTML = "x : " + x + " y : " + y;
	}
	
	// recupérer click souris
	conteneur.onclick = function(event){
		
		
		var left = 0;
		var top = 0;
		/*On récupère l'élément*/
		var e = document.getElementById('bc_games');
		/*Tant que l'on a un élément parent*/
		while (e.offsetParent != undefined && e.offsetParent != null)
		{
			/*On ajoute la position de l'élément parent*/
			left += e.offsetLeft + (e.clientLeft != null ? e.clientLeft : 0);
			top += e.offsetTop + (e.clientTop != null ? e.clientTop : 0);
			e = e.offsetParent;
		}
		var x = event.clientX;
		var y = event.clientY;
		var divInfo = document.getElementById('infos');
		x = x - left;
		y = y - top;
		context.fillStyle="#000000";
		context.fillRect(x, y, 5, 5);
		finish(x, y);
	}
}

function finish(x,y){
	var servletName = "Connect";
	xhr = getXhr();
	xhr.open("POST", "./" + servletName, true);
	xhr.onreadystatechange = function(){
	    if(xhr.readyState == 4 && xhr.status == 200){
	        response = xhr.responseText;
	    	//document.getElementById('infos').innerHTML = response;
	   }
	};
	xhr.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded');
	xhr.send("action=2&x=" + x + "&y=" + y);
}