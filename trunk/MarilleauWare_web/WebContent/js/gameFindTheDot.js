/* Context graphique */
var context;
var canvasInfo;
var hasplayed = false;
var idCount;
var idRefresh;
var idEnd;
var gftd_date;
var end;
var s, ms;
var goalXG;
var goalYG;

var endtamere= false;

function runFindTheDot(listeparam){
	
	endtamere = false;
	
	//idUserServ, idPartyServ, idGameServ, goalX ,goalY, xInverted, yInverted
	
	var param = listeparam.split(';');
	var idUser = parseInt(param[0], 10);
	var idParty = parseInt(param[1], 10);
	var idGame = parseInt(param[2], 10);
	var goalX = parseInt(param[3], 10);
	var goalY = parseInt(param[4], 10);
	var xInverted = parseInt(param[5], 10);
	var yInverted = parseInt(param[6], 10);
	
	var elem = document.getElementById('canvasElem');
	if (!elem || !elem.getContext) {
		return;
	}
	context = elem.getContext('2d');
	if (!context) {
		return;
	}
	
	
	 canvasInfo = document.getElementById('canvasInfo');
	 var info = canvasInfo.getContext('2d');

	 /* Flush */
	 context.fillStyle = "#000000";
	 context.clearRect(0,0,elem.width,elem.height);
	 info.fillStyle = "#000000";
	 info.clearRect(0, 0, canvasInfo.width, canvasInfo.height);
	 
	 info.font="bold 13px Verdana";
	 info.fillText("Click on the (" + goalX + "," + goalY + ") pixel !", 10, 20);
	 
	// Affichage de l'echelle
	var i,j;
	for (i = 50;i <= 250;i += 50) {
		context.fillRect(0, i+1, 5, 2);
		if (yInverted == 1) {	
			goalY = 300 - goalY;
			context.fillText(300-i, 7, i+5);
		} else {
			context.fillText(i, 7, i+5);
		}
	}
	
	for (j = 50;j <= 350;j += 50) {
		context.fillRect(j-1, 0, 2, 5);
		if (xInverted == 1) {
			goalX = 400 - goalX;
			context.fillText(400-j, j-5, 15);
		} else {
			context.fillText(j, j-5, 15);
		}
	}
	
	goalXG = goalX;
	goalYG = goalY;
	
	hasplayed = false;
	gftd_date = new Date();
	idCount = setInterval("countFindTheDot("+idGame+","+idUser+","+idParty+")", 10);

	var conteneur = document.getElementById('bc_games');
	
	conteneur.onmousemove = function(event){
		
		var left = 0;
		var top = 0;
		
		/*On recupere l'element*/
		var e = document.getElementById('bc_games');
		
		/*Tant que l'on a un element parent*/
		while (e.offsetParent != undefined && e.offsetParent != null) {
			/*On ajoute la position de l'element parent*/
			left += e.offsetLeft + (e.clientLeft != null ? e.clientLeft : 0);
			top += e.offsetTop + (e.clientTop != null ? e.clientTop : 0);
			e = e.offsetParent;
		}
		var x = event.clientX;
		x = x - left;
	};
	
	// recuperer click souris
	conteneur.onclick = function(event){
		
		if (!hasplayed) {
			var left = 0;
			var top = 0;
			var e = document.getElementById('bc_games');
			while (e.offsetParent != undefined && e.offsetParent != null) {
				/*On ajoute la position de l'element parent*/
				left += e.offsetLeft + (e.clientLeft != null ? e.clientLeft : 0);
				top += e.offsetTop + (e.clientTop != null ? e.clientTop : 0);
				e = e.offsetParent;
			}
			
			var x = event.clientX - left + window.scrollX;
			var y = event.clientY - top + window.scrollY;
			
			context.beginPath();
			context.moveTo(x,y);
			context.lineTo(goalX,goalY);
			context.stroke();
			
			var d = Math.sqrt((x-goalX) * (x-goalX) + (y-goalY) * (y-goalY));
			var distance = Math.floor(d);
		
			document.getElementById('divScore').innerHTML = distance;
			
			context.fillStyle="#EE0000";
			context.fillRect(goalX-1, goalY-1, 3, 3);
			context.fillStyle="#000000";
			context.fillRect(x-1, y-1, 3, 3);
			
			hasplayed = true;
			clearInterval(idCount);
			clearInterval(idRefresh);
			finishFindTheDot(idGame,idUser,idParty,x, y);
		}
	};
}

function finishFindTheDot(idGame,idUser,idParty, x, y){
	
	var xhr = getXhr();
	xhr.open("POST", "./FindTheDot", true);
	xhr.onreadystatechange = function() {
	    if (xhr.readyState == 4 && xhr.status == 200) {
	        response = xhr.responseText;
			var dist = response;
			var distanceX = goalXG - (goalXG - x)/2;
			var distanceY = goalYG - (goalYG - y)/2;
			context.fillText("distance = " + dist, distanceX, distanceY);
	   }
	};
	xhr.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded');
	xhr.send("action=2&idUser=" + idUser + "&idParty="+ idParty + "&idGame=" + idGame+ "&x=" + x+ "&y=" + y);
	idEnd = setInterval("checkResultFindTheDot("+idGame+")", 500);
}

function countFindTheDot(idGame,idUser,idParty){
	
	end = new Date();
	var delta = end - gftd_date;
	delta = new Date(delta);
	ms = delta.getMilliseconds();
	ms2 = 1000 - ms;
	s = delta.getSeconds();
	s2 = 30-s;
	
	if (s2 <= -1) {
		s2 = 0;
		ms2 = 0 + "00";
		finishFindTheDot(idGame,idUser,idParty,999,999);
		clearInterval(idCount);
		clearInterval(idRefresh);
		hasplayed = true;
	}
	
	var chrono = document.getElementById('chrono');
	chrono.innerHTML = "" + s2 + ":" + Math.floor(ms2/10);
}

function checkResultFindTheDot(idGame){
	
	if (endtamere==false) {
		var xhr = getXhr();
		xhr.open("POST", "./FindTheDot", true);
		xhr.onreadystatechange = function() {
		    if (xhr.readyState == 4 && xhr.status == 200){
		        response = xhr.responseText;
		
		        //la partie est terminé
		    	if (response.substring(0,4)=="end_") {
		    		endtamere = true;
		    		
			    	clearInterval(idEnd);
		    		var scoreEndGame = response.substring(4,response.length-1);
		    		
		    		//la game est fini on rempli le champ finishgame et le score
			    	document.getElementById('infos').innerHTML += scoreEndGame;
			    	document.getElementById('finishGame').setAttribute("value", "true");
		    	}
		   }
		};
		xhr.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded');
		xhr.send("action=3&idGame="+idGame);
	}
}