/* Taille de la zone de jeu */
var ZONE_JEU_WIDTH = 400;
var ZONE_JEU_HEIGHT = 300;

/* Tailles de la zone d'arrivee */
var FINISH_WIDTH = 20;
var FINISH_HEIGHT = 20;

/* Tailles de la zone de depart */
var BEGIN_WIDTH = 20;
var BEGIN_HEIGHT = 20;

/* Largeur du Chemin */
var PATH_WIDTH = 20;

var context;
var tabObstacle = [ZONE_JEU_WIDTH];
var tabFinish = [FINISH_WIDTH];
/* Date */
var d;
var end;
var s, ms;

var idCount;
var idRefresh;

var gagne = false;
var hasBegin = false;
var hasFinish = false;
var tabParam;

var idGame;
var idParty;
var idUser;

function runBuildPath(listeParam){
	
	//tabObstacle  = new Array(ZONE_JEU_WIDTH, ZONE_JEU_HEIGHT);
	// On recupere l'objet canvas
	var elem = document.getElementById('canvasElem');
	if (!elem || !elem.getContext) {
		return;
	}
	s = 0;
	ms = 0;
	// On recupere le contexte 2D
	context = elem.getContext('2d');
	if (!context) {
		return;
	}
	context.clearRect(0,0,elem.width,elem.height);
	context.fillStyle="#000000";
	var i;
	var j;
	gagne = false;
	
	
	tabParam = listeParam.split(';');
	idUser = tabParam[0];
	idParty = tabParam[1];
	idGame = tabParam[2];
	
	
	/* Remplissage de la zone d'arrivee */
	for(i=0; i<ZONE_JEU_WIDTH; i++){
			tabFinish[i] = new Array();	
	}
	for(i=0; i<ZONE_JEU_WIDTH; i++){
		for(j=0; j<ZONE_JEU_HEIGHT; j++){
			tabFinish[i][j] = 0;
		}
	}

	/* Remplissage du tableau d'obstacles */
	for(i=0; i<ZONE_JEU_WIDTH; i++){
		tabObstacle[i] = new Array();
	}
	for(i=0; i<ZONE_JEU_WIDTH; i++){
		for(j=0; j<ZONE_JEU_HEIGHT; j++){
			tabObstacle[i][j] = 1;
		}
	}
	for(i=0; i<BEGIN_WIDTH; i++){
		for(j=0; j<BEGIN_HEIGHT; j++){
			tabObstacle[i][j] = 0;
		}
	}
	for(i=0; i<ZONE_JEU_WIDTH; i++){
		for(j=0; j<ZONE_JEU_HEIGHT; j++){
			if(tabObstacle[i][j] == 1)
				if(tabFinish[i][j]!=1)
					context.fillRect(i, j, 1, 1);
		}
	}
	
	/* Remplissage de la zone de depart */
	context.fillStyle="#AA0000";
	context.fillRect(0, 0, BEGIN_WIDTH, BEGIN_HEIGHT);
	
	/* Creation de la zone d'arrivee */
	drawFinish();
	
	var mouseInfo = document.getElementById('canvasElem');
	
	mouseInfo.onclick = function(event){
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
		y = y - top;
		
		if(x <= BEGIN_WIDTH && y <= BEGIN_HEIGHT){
			game();
		}
	};
	
	var conteneur = document.getElementById('bc_games');
	conteneur.onmousemove = function(event){
		var left = 0;
		var top = 0;
		var mouseInfo = document.getElementById('mouseInfo');
//		var info = document.getElementById('infos');
		
		
		
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
		y = y - top;
		//mouseInfo.innerHTML = "x : " + x + "  y : " + y;
		if(hasBegin && !hasFinish){
			if(tabObstacle[x][y] == 1 || x<=1 || y<=1 || x >= ZONE_JEU_WIDTH-1 || y >= ZONE_JEU_HEIGHT-1){
				if(tabFinish[x][y]==1)
					gagne = true;
				finishBP();
			}
		}
	};
	
}

function finishBP(){
	hasFinish = true;
	xhr = getXhr();
	clearInterval(idCount);
	clearInterval(idRefresh);
	if(gagne){
		document.getElementById('headConteneur').innerHTML = "Bravo!";
	}else{
		document.getElementById('headConteneur').innerHTML = "Pas cool...";
		s = -1;
		ms = -1;
	}
	var servletName = "BuildPath";
	xhr.open("POST", "./" + servletName, true);
	xhr.onreadystatechange = function(){
	    if(xhr.readyState == 4 && xhr.status == 200){
	        response = xhr.responseText;
	        alert(response);
	   }
	};
	xhr.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded');
	xhr.send("action=2&idUser=" + idUser + "&idParty="+ idParty + "&idGame=" + idGame+ "&sec=" + s + "&msec=" + ms);
	idEnd = setInterval("checkResultBP("+idGame+")", 500);
}
function checkResultBP(idGame){
	
	var servletName = "BuildPath";
	var tabId = [];
	xhr.open("POST", "./" + servletName, true);
	xhr.onreadystatechange = function(){
	    if(xhr.readyState == 4 && xhr.status == 200){
	        response = xhr.responseText;
	
	        //la partie est terminé
	    	if(response.substring(0,4)=="end_"){
	    		
		    	clearInterval(idEnd);
	    		var bp_scoreEndGame = response.substring(4,response.length-1);
	    		
	    		
	    		//la game est fini on rempli les champs idGame et finishgame
	    		
	    		//document.getElementById('idGameValue').setAttribute("value", idGame);
	    		
		    	document.getElementById('infos').innerHTML += bp_scoreEndGame;
		    	//alert("wait");
		    	document.getElementById('finishGame').setAttribute("value", "true");
		    	
		    	//document.getElementById('infos').innerHTML += "<br>Apres InitCheck";
	    	}
	   }
	};
	xhr.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded');
	xhr.send("action=3&idGame="+idGame);	
}



function game(){
	d = new Date();
	hasBegin = true;
	idCount = setInterval(count, 20);
//	idRefresh = setInterval(refresh, 20);
	drawTheLab();
}

function refresh(){

}

/* Affiche le compteur, appel chaque 10 ms */
function count(){
	end = new Date();
	var delta = end - d;
	delta = new Date(delta);
	ms = delta.getMilliseconds();
	s = delta.getSeconds();
	document.getElementById('chrono').innerHTML = s + ":" + ms;
}

/* Dessine la zone d'arrivee */
function drawFinish(){
	context.fillStyle="#00CC00";
	context.fillRect(ZONE_JEU_WIDTH-FINISH_WIDTH, ZONE_JEU_HEIGHT-FINISH_HEIGHT, FINISH_WIDTH, FINISH_HEIGHT);
//	context.fillRect(50, 50, FINISH_WIDTH, FINISH_HEIGHT);
	//infos.innerHTML += document.getElementById("conteneur").width;
}

function drawTheLab(){
	var tabParam2 = Array();
	for(var i=3; i<tabParam.length; i++){
		tabParam2[i-3] = parseInt(tabParam[i], 10);
	}
	drawLab(tabParam2);
}

/* Dessine les obstacles */
function drawLab(tabTurn){
	
	
	var iMax;
	var jMax;
	var i; var j;
	/* Ajoute la zone d'arrivee dans les obstacles */
	for(i=0; i<ZONE_JEU_WIDTH; i++){
		for(j=0; j<ZONE_JEU_HEIGHT; j++){
			if(tabFinish[i][j]==1)
				tabObstacle[i][j] = 1;
		}
	}
	var n=2;
	
	for(i=BEGIN_WIDTH; i<tabTurn[0]; i++){
		for(j=0; j<PATH_WIDTH; j++){
			tabObstacle[i][j] = 0;
		}
	}
	iMax=i;
	jMax=j;
	//infos.innerHTML += "<br/>iMax => " + iMax + "  jMax => " + jMax + "  n => " + n + "   tab => " + tabTurn[n];
	for(i; i<tabTurn[0]+PATH_WIDTH; i++){
		for(j=0; j<tabTurn[1]; j++){
			tabObstacle[i][j] = 0;
		}
	}
	iMax=i;
	jMax=j;
	//infos.innerHTML += "<br/>iMax => " + iMax + "  jMax => " + jMax + "  n => " + n + "   tab => " + tabTurn[n];
	
	while(tabTurn[n]!=null){
		//infos.innerHTML += "<br/>i => " + i + "  j => " + j + "  n => " + n + "   tab => " + tabTurn[n];
		
		/* Segment chemin horizontal */
		if(n%2==0){		
		
			if(tabTurn[n]<0){
				for(i=iMax; i>iMax+tabTurn[n]; i--){
					for(j=jMax; j<jMax+PATH_WIDTH; j++){
							tabObstacle[i][j] = 0;
					}
				}
			}else{
				for(i=iMax-PATH_WIDTH; i<iMax+tabTurn[n]; i++){
					for(j=jMax; j<jMax+PATH_WIDTH; j++){
							tabObstacle[i][j] = 0;
					}
				}
			}
			iMax=i;
			jMax=j;
			
		/* Segment chemin vertical */
		}else{
			
			if(tabTurn[n]<0){
				for(i=iMax; i<iMax+PATH_WIDTH; i++){
					for(j=jMax; j>jMax+tabTurn[n]; j--){
						tabObstacle[i][j] = 0;
					}
				}
			}else{
				for(i=iMax; i<iMax+PATH_WIDTH; i++){
					for(j=jMax-PATH_WIDTH; j<jMax+tabTurn[n]; j++){
							tabObstacle[i][j] = 0;
					}
				}
			}
			
			iMax=i;
			jMax=j;
		}
		n++;
	}
	//infos.innerHTML += "<br/>iMax => " + iMax + "  jMax => " + jMax + "  n => " + n + "   tab => " + tabTurn[n];
	for(i=iMax-FINISH_WIDTH; i<iMax; i++){
		for(j=jMax; j<jMax+PATH_WIDTH; j++){
			tabFinish[i][j] = 1;
		}
	}
	context.fillStyle="#FFFFFF";
	for(i=BEGIN_WIDTH; i<ZONE_JEU_WIDTH; i++){
		for(j=0; j<ZONE_JEU_HEIGHT; j++){
			if(tabObstacle[i][j] == 0)
				context.fillRect(i, j, 1, 1);
		}
	}
	context.fillStyle="#00FF00";
	for(i=BEGIN_WIDTH; i<ZONE_JEU_WIDTH; i++){
		for(j=0; j<ZONE_JEU_HEIGHT; j++){
			if(tabFinish[i][j] == 1)
					context.fillRect(i, j, 1, 1);
		}
	}



	
		

}

