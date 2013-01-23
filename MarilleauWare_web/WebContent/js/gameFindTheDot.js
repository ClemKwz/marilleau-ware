
var xhr;

/* Context graphique */
var context;
var canvasInfo;
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
var goalXG;
var goalYG;

function salut(){
	alert("ok");
}

function begin1(x,y){
	alert("coucou");
}

function runFindTheDot(idUserServ, idPartyServ, idGameServ, goalX ,goalY, xInverted, yInverted){
	idUser = idUserServ;
	idParty = idPartyServ;
	idGame = idGameServ;
	
	var elem = document.getElementById('canvasElem');
	if (!elem || !elem.getContext) {
		return;
	}
	context = elem.getContext('2d');
	if (!context) {
		return;
	}
	context.clearRect(0,0,elem.width,elem.height);
	
	 canvasInfo = document.getElementById('canvasInfo');
	 var info = canvasInfo.getContext('2d');
	 info.font="bold 13px Verdana";
	 info.fillText("Click on the (" + goalX + "," + goalY + ") pixel !", 10, 20);
	 
	//document.getElementById('headConteneur').innerHTML += "Click on the (" + goalX + "," + goalY + ") pixel !";

	// Affichage de l'echelle
	for(i = 50;i <= 250;i += 50)
	{
		context.fillRect(0, i+1, 5, 2);
		if(yInverted == 1)
		{	
			goalY = 300 - goalY;
			context.fillText(300-i, 7, i+5);
		}
		else
		{
			context.fillText(i, 7, i+5);
		}
	}
	
	for(j = 50;j <= 350;j += 50)
	{
		context.fillRect(j-1, 0, 2, 5);
		if(xInverted == 1)
		{
			goalX = 400 - goalX;
			context.fillText(400-j, j-5, 15);
		}
		else
		{
			context.fillText(j, j-5, 15);
		}
	}
	
	goalXG = goalX;
	goalYG = goalY;
	
	hasplayed = false;
	d = new Date();
	idCount = setInterval(count, 10);

	var conteneur = document.getElementById('bc_games');
	
	conteneur.onmousemove = function(event){
		var left = 0;
		var top = 0;
		/*On recupere l'element*/
		var e = document.getElementById('bc_games');
		/*Tant que l'on a un element parent*/
		while (e.offsetParent != undefined && e.offsetParent != null)
		{
			/*On ajoute la position de l'element parent*/
			left += e.offsetLeft + (e.clientLeft != null ? e.clientLeft : 0);
			top += e.offsetTop + (e.clientTop != null ? e.clientTop : 0);
			e = e.offsetParent;
		}
		var x = event.clientX;
		var y = event.clientY;
		var divInfo = document.getElementById('infos');
		x = x - left;
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
			/*On ajoute la position de l'element parent*/
			left += e.offsetLeft + (e.clientLeft != null ? e.clientLeft : 0);
			top += e.offsetTop + (e.clientTop != null ? e.clientTop : 0);
			e = e.offsetParent;
		}
		var x = event.clientX;
		var y = event.clientY;
		x = x - left;
		y = y - top;
		
		context.beginPath();
		context.moveTo(x,y);
		context.lineTo(goalX,goalY);
		context.stroke();
		
		var d = Math.sqrt((x-goalX) * (x-goalX) + (y-goalY) * (y-goalY));
		var distance = Math.floor(d);

		/*var distanceX = goalX - (goalX - x)/2;
		var distanceY = goalY - (goalY - y)/2;
		context.fillText("distance = " + distance, distanceX, distanceY);*/
	
		document.getElementById('divScore').innerHTML = distance;
		
		context.fillStyle="#EE0000";
		context.fillRect(goalX-1, goalY-1, 3, 3);
		
		context.fillStyle="#000000";
		context.fillRect(x-1, y-1, 3, 3);
		hasplayed = true;
		clearInterval(idCount);
		clearInterval(idRefresh);
		finish(x, y);
		}
	};
}

function finish(x, y){
	var servletName = "FindTheDot";
	xhr = getXhr();
	xhr.open("POST", "./" + servletName, true);
	xhr.onreadystatechange = function(){
	    if(xhr.readyState == 4 && xhr.status == 200){
	        response = xhr.responseText;
	    	//document.getElementById('infos').innerHTML = response;
			var dist = response;
			var distanceX = goalXG - (goalXG - x)/2;
			var distanceY = goalYG - (goalYG - y)/2;
			context.fillText("distance = " + dist, distanceX, distanceY);
	   }
	};
	xhr.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded');
	//document.getElementById('infos').innerHTML += "<br/><br/>action=2&idUser=" + idUser + "&idParty="+ idParty + "&idGame=" + idGame+ "&distance=" + distance;
	xhr.send("action=2&idUser=" + idUser + "&idParty="+ idParty + "&idGame=" + idGame+ "&x=" + x+ "&y=" + y);
	idEnd = setInterval("checkResult("+idGame+")", 500);
}



function count(){
	end = new Date();
	var delta = end - d;
	delta = new Date(delta);
	ms = delta.getMilliseconds();
	ms2 = 1000 - ms;
	s = delta.getSeconds();
	s2 = 30-s;
	if(s2 <= -1)
	{
		s2 = 0;
		ms2 = 0 + "00";
		finish(999);
		clearInterval(idCount);
		clearInterval(idRefresh);
		hasplayed = true;
	}
	var chrono = document.getElementById('chrono');
	chrono.innerHTML = /*"Time : "*/ + s2 + ":" + Math.floor(ms2/10);
}

function checkResult(idGame){
	
	var servletName = "FindTheDot";
	var tabId = [];
	xhr.open("POST", "./" + servletName, true);
	xhr.onreadystatechange = function(){
	    if(xhr.readyState == 4 && xhr.status == 200){
	        response = xhr.responseText;
	
	        //la partie est terminé
	    	if(response.substring(0,4)=="end_"){
	    		
		    	clearInterval(idEnd);
	    		var scoreEndGame = response.substring(4,response.length-1);
	    		
	    		
	    		//la game est fini on rempli les champs idGame et finishgame
	    		
	    		//document.getElementById('idGameValue').setAttribute("value", idGame);
	    		
		    	document.getElementById('infos').innerHTML += scoreEndGame;
		    	//alert("wait");
		    	document.getElementById('finishGame').setAttribute("value", "true");
		    	
		    	//document.getElementById('infos').innerHTML += "<br>Apres InitCheck";
	    	}
	   }
	};
	xhr.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded');
	xhr.send("action=3&idGame="+idGame);	
}