/* last modif : 30/12/12 a 18h */

/* Taille de la zone de jeu */
var ZONE_JEU_WIDTH = 400;
var ZONE_JEU_HEIGHT = 300;

/* checkbox : taille, nombre, couleurs */
var SIDE_CHECKBOX = 20;
var NB_CHECKBOX = 20;
var ROUGE;
var VERT;
var JAUNE;
var BLEU;
var NB_COLOR = 4;
var MARGE = 2;
var EPAISSEUR_COTE = 2;

/* les contextes de dessin */
var zoneJeu;


/* tableaux 2D virtuels contenant les checkbox */
var tabCheckbox;// grille couleurs a appliquer
var tabPlayer;  // grille couleurs appliquees par le joueur
var WIDTH = ZONE_JEU_WIDTH / SIDE_CHECKBOX; 
var HEIGHT = ZONE_JEU_HEIGHT / SIDE_CHECKBOX;

/* valeurs aleatoire recues */
var tabRandomPosition = [20,16,9,77,99,46,78,95,98,122,25,222,48,236,276,0,55,198,134,211];
var tabRandomColor = [44,0,400,3,6,2,2,3,6,2,1,0,4,3,6,2,2,3,6,2];
var order = [true,true,true,true]; // pour definir l'ordre des couleurs

var score = 0;
var chrono = 3000; // en centieme
var idInterv; // Boucle de rafraichissement du contexte 2D
var timeOver = false;

var x, y; // coordonnees pointeur
var caseX, caseY; // coordonnees pointeur dans la grille 

function runCheckBox(){
	alert("ok mon fr�re c'est gagn�");
//	initContext();
//	
//	initRandomOrder();
//	drawOrderedColor();
//	
//	initCheckboxRandom();
//	initPlayer();
//
//	drawTabCheckbox();
//	var mouseInfo = document.getElementById('canvasElem');
//	
//	var conteneur = document.getElementById('bc_games');
//	
//	conteneur.onclick = function(event){
//		var left = 0;
//		var top = 0;
//		/*On recupere l'element*/
//		var e = document.getElementById('bc_games');
//		/*Tant que l'on a un element parent*/
//		while (e.offsetParent != undefined && e.offsetParent != null)
//		{
//			/*On ajoute la position de l'element parent*/
//			left += e.offsetLeft + (e.clientLeft != null ? e.clientLeft : 0);
//			top += e.offsetTop + (e.clientTop != null ? e.clientTop : 0);
//			e = e.offsetParent;
//		}
//		x = event.clientX;
//		y = event.clientY;
//		x = x - left;
//		y = y - top;
//		if (!timeOver) {
//			changeColor(x,y);
//			updateScore();
//		}
//	};
//	
//	// Boucle de rafraichissement du contexte 2D
//	idInterv = setInterval(refreshGame, 10);
}

function refreshGame() {
	
	if (!timeOver) {
		drawChronoAndScore();
	}
		caseX = Math.floor(x/SIDE_CHECKBOX);
		caseY = Math.floor(y/SIDE_CHECKBOX);
		debug();
	if (chrono == 0) {
		timeOver = true;
	}
}

function drawChronoAndScore() {
	info = document.getElementById('chrono');
	chrono--;
	info.innerHTML = Math.floor(chrono/100) + "." + Math.floor(chrono%100);
	
	info = document.getElementById('divScore');
	info.innerHTML = score;
}

function initContext() {
	// context info
	// On recupere l'objet canvas
	var elem = document.getElementById('canvasInfo');
	if (!elem || !elem.getContext) {
		return;
	}
	
	// On recupere le contexte 2D
	info = elem.getContext('2d');
	if (!info) {
		return;
	}
	
	// context zoneJeu
	var elem = document.getElementById('canvasElem');
	if (!elem || !elem.getContext) {
		return;
	}
	zoneJeu = elem.getContext('2d');
	zoneJeu.clearRect(0,0,elem.width,elem.height);
	if (!zoneJeu) {
		return;
	}
}

function initInfo() {
	info.fillStyle = "red";
	info.fillRect(0, 0, SIDE_CHECKBOX, SIDE_CHECKBOX);
	info.fillStyle = "green";
	info.fillRect(SIDE_CHECKBOX+10, 0, SIDE_CHECKBOX, SIDE_CHECKBOX);
	info.fillStyle = "yellow";
	info.fillRect((SIDE_CHECKBOX+10)*2, 0, SIDE_CHECKBOX, SIDE_CHECKBOX);
	info.fillStyle = "blue";
	info.fillRect((SIDE_CHECKBOX+10)*3, 0, SIDE_CHECKBOX, SIDE_CHECKBOX);
}

function drawOrderedColor() {
	info.fillStyle = "red";
	info.fillRect((SIDE_CHECKBOX+10)*ROUGE, 0, SIDE_CHECKBOX, SIDE_CHECKBOX);
	info.fillStyle = "green";
	info.fillRect((SIDE_CHECKBOX+10)*VERT, 0, SIDE_CHECKBOX, SIDE_CHECKBOX);
	info.fillStyle = "yellow";
	info.fillRect((SIDE_CHECKBOX+10)*JAUNE, 0, SIDE_CHECKBOX, SIDE_CHECKBOX);
	info.fillStyle = "blue";
	info.fillRect((SIDE_CHECKBOX+10)*BLEU, 0, SIDE_CHECKBOX, SIDE_CHECKBOX);
}

function initRandomOrder() {
	ROUGE = tabRandomPosition[0] % NB_COLOR;
	order[ROUGE] = false;
	
	VERT = getFreeOrder(tabRandomPosition[1] % (NB_COLOR-1));
	order[VERT] = false;
	
	JAUNE = getFreeOrder(tabRandomPosition[2] % (NB_COLOR-2));
	order[JAUNE] = false;
	
	BLEU = getFreeOrder(0);
}

function getFreeOrder(random) {
	for (var i=0; i<=random; i++) {
		if (!order[i]) {
			random++;
		}
	}
	return i-1;
} 

function initCheckboxRandom() {
	
	tabCheckbox = [WIDTH];
	for (var i=0; i<WIDTH; i++) {
		tabCheckbox[i] = [HEIGHT];
	}
	for (var i=0; i<WIDTH; i++) {
		for (var j=0; j<HEIGHT; j++) {
			tabCheckbox[i][j] = -1;
		}
	}
	
	for (var i=0; i<NB_CHECKBOX; i++) {
		x = tabRandomPosition[i] % WIDTH; 
		y = Math.floor(tabRandomPosition[i] / WIDTH);
		tabCheckbox[x][y] = tabRandomColor[i] % NB_COLOR;
	}
}

function initPlayer() {
	tabPlayer = [WIDTH];
	for (var i=0; i<WIDTH; i++) {
		tabPlayer[i] = [HEIGHT];
	}
	for (var i=0; i<WIDTH; i++) {
		for (var j=0; j<HEIGHT; j++) {
			tabPlayer[i][j] = NB_COLOR;
		}
	}
}

function drawTabCheckbox() {
	for (var i=0; i<WIDTH; i++) {
		for (var j=0; j<HEIGHT; j++) {
			drawEmptyCheckbox(SIDE_CHECKBOX*i, SIDE_CHECKBOX*j, getColor(tabCheckbox[i][j]));
		}	
	}
}

/* Met la couleur suivante a la box contenant le point (x,y) */
function changeColor(x,y) {
	
	caseX = Math.floor(x/SIDE_CHECKBOX);
	caseY = Math.floor(y/SIDE_CHECKBOX);
	
	if (tabCheckbox[caseX][caseY] >= 0 && tabCheckbox[caseX][caseY] < NB_COLOR) {
		if (tabPlayer[caseX][caseY] == NB_COLOR) {
			tabPlayer[caseX][caseY] = 0;
		} else {
			tabPlayer[caseX][caseY] = (tabPlayer[caseX][caseY] + 1) % NB_COLOR;
		}
		drawCheckbox(SIDE_CHECKBOX*caseX, SIDE_CHECKBOX*caseY, getColor(tabPlayer[caseX][caseY]));
	}
	
}

function drawCheckbox(x,y,color) {
	zoneJeu.fillStyle = color;
	zoneJeu.fillRect(x+MARGE+EPAISSEUR_COTE, y+MARGE+EPAISSEUR_COTE,
						SIDE_CHECKBOX-(MARGE+EPAISSEUR_COTE)*2, SIDE_CHECKBOX-(MARGE+EPAISSEUR_COTE)*2);
	
}

function drawEmptyCheckbox(x,y,color) {
	zoneJeu.strokeStyle = color;
	zoneJeu.strokeRect(x+MARGE, y+MARGE, SIDE_CHECKBOX-(MARGE*2), SIDE_CHECKBOX-(MARGE*2));
}


function getColor(numColor) {
	var color;
	switch (numColor) {
		case ROUGE:
		color = "red";
		break;
		case VERT:
		color = "green";
		break;
		case JAUNE:
		color = "yellow";
		break;
		case BLEU: 
		color = "blue";
		break;
		default: 
		color = "white";
		break;
	}
	return color;
}

function updateScore() {
	score = 0;
	for (var i=0; i<WIDTH; i++) {
		for (var j=0; j<HEIGHT; j++) {
			if (tabCheckbox[i][j]==tabPlayer[i][j]) {
				score++;
			}
		}	
	}
}

function debug() {
	divInfo = document.getElementById('infos');
	divInfo.innerHTML =
	"x : " + x + " y : " + y + "<br />"
	+ tabCheckbox[caseX][caseY] + " == " + tabPlayer[caseX][caseY] + "<br />"
	+ "R="+ROUGE + "V="+VERT + "J="+JAUNE + "B="+BLEU
	+ "Yeah 52"
	;
}
