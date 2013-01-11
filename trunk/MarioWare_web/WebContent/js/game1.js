
var xhr;

/* Context graphique */
var context;
var hasplayed = false;
var idCount;
var idRefresh;
var idEnd;
var d;
var end;
var s, ms;
var idUser;
var idParty;
var idGame;

function begin1(idUser, idParty, idGame, x,y){
	var elem = document.getElementById('canvasElem');
	if (!elem || !elem.getContext) {
		return;
	}
	context = elem.getContext('2d');
	if (!context) {
		return;
	}
	
	 hasplayed = false;
	 d = new Date();
	 idCount = setInterval(count, 10);
	
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
		//divInfo.innerHTML = "x : " + x + " y : " + y;
	};
	// recuperer click souris
	conteneur.onclick = function(event){
		
	if(!hasplayed)
	{
		var left = 0;
		var top = 0;
		var e = document.getElementById('bc_games');
		while (e.offsetParent != undefined && e.offsetParent != null)
		{
			/*On ajoute la position de l'élément parent*/
			left += e.offsetLeft + (e.clientLeft != null ? e.clientLeft : 0);
			top += e.offsetTop + (e.clientTop != null ? e.clientTop : 0);
			e = e.offsetParent;
		}
		var x = event.clientX;
		var y = event.clientY;
		x = x - left;
		y = y - top;
		context.fillStyle="#000000";
		context.fillRect(x, y, 5, 5);
		hasplayed = true;
		clearInterval(idCount);
		clearInterval(idRefresh);
		finish(x, y);
		}
	};
}

function finish(x,y){
	var servletName = "LinkBowser";
	xhr = getXhr();
	xhr.open("POST", "./" + servletName, true);
	xhr.onreadystatechange = function(){
	    if(xhr.readyState == 4 && xhr.status == 200){
	        response = xhr.responseText;
	    	document.getElementById('infos').innerHTML = response;
	   }
	};
	xhr.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded');
	xhr.send("action=2&idUser=" + idUser + "&idParty="+ idParty + "&idGame=" + idGame+ "&x=" + x + "&y=" + y);
	idEnd = setInterval(checkResult, 500);
}

function checkResult(){
	var servletName = "LinkBowser";
	xhr.open("POST", "./" + servletName, true);
	xhr.onreadystatechange = function(){
	    if(xhr.readyState == 4 && xhr.status == 200){
	        response = xhr.responseText;
	    	document.getElementById('infos').innerHTML = response;
	    	clearInterval(idEnd);
	   }
	};
	xhr.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded');
	xhr.send("action=3");
}

function count(){
	end = new Date();
	var delta = end - d;
	delta = new Date(delta);
	ms = delta.getMilliseconds();
	ms2 = 1000 - ms;
	s = delta.getSeconds();
	s2 = 7-s;
	if(s2 <= -1)
	{
		s2 = 0;
		ms2 = 0 + "00";
		finish(999,999);
		clearInterval(idCount);
		clearInterval(idRefresh);
	}
	chrono.innerHTML = s2 + ":" + ms2;
}