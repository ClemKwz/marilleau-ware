/* Taille de la zone de jeu */
var ZONE_JEU_WIDTH = 400;
var ZONE_JEU_HEIGHT = 300;

var context;
/* Date */
var d;
var end;
var s, ms;

var idCount;
var idRefresh;

function begin(){
	var elem = document.getElementById('canvasElem');
	if (!elem || !elem.getContext) {
		return;
	}
	s = 0;
	ms = 0;
	// On récupère le contexte 2D
	context = elem.getContext('2d');
	if (!context) {
		return;
	}
	
	var mouseInfo = document.getElementById('canvasElem');
	
	//Initialiser le goal
	var randomX=Math.floor(Math.random()*ZONE_JEU_WIDTH);
	var randomY=Math.floor(Math.random()*ZONE_JEU_HEIGHT);
	var goal = document.getElementById('goal');
	goal.innerHTML = "Goal = x : " + randomX + "  y : " + randomY;
	
	context.fillRect(randomX, randomY, 2, 2);
	var conteneur = document.getElementById('conteneur');
	// recupérer click souris
	conteneur.onclick = function(event){
		var left = 0;
		var top = 0;
		/*On récupère l'élément*/
		var e = document.getElementById('conteneur');
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
		var divInfo = document.getElementById('divInfo');
		x = x - left;
		y = y - top;
		//divInfo.innerHTML = "x : " + x + "  y : " + y;
		
	};
	// debug : afficher coordonnées souris
	conteneur.onmousemove = function(event){
		var left = 0;
		var top = 0;
		/*On récupère l'élément*/
		var e = document.getElementById('conteneur');
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
		var divInfo = document.getElementById('divInfo');
		x = x - left;
		y = y - top;
		divInfo.innerHTML = "x : " + x + "  y : " + y;
	};
}

/* Fin de la partie */
function endGame(){
	clearInterval(idCount);
	clearInterval(idRefresh);
	msg_head.innerHTML = (gagne)?"GAGNE !!!":"PERDU";
}

function game(){
	d = new Date();
	idCount = setInterval(count, 20);
}
/* Affiche le compteur, appel chaque 10 ms */
function count(){
	end = new Date();
	var delta = end - d;
	delta = new Date(delta);
	ms = delta.getMilliseconds();
	s = delta.getSeconds();
	chrono.innerHTML = s + ":" + ms;
}



