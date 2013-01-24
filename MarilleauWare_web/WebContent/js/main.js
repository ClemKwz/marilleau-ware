
/** Variables **/

var xhr;
var idReloadGame;
var idPartyMain;

/** Fonctions pour afficher et joindre les parties **/

function initJoinPartie(idUser){
	
	var servletName = "JoinParty";
	xhr = getXhr();
	xhr.open("POST", "./" + servletName, true);
	xhr.onreadystatechange = function(){
        if(xhr.readyState == 4 && xhr.status == 200){
            response = xhr.responseText;
            document.getElementById('container').innerHTML = response;
       }
    };
	xhr.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded');
	xhr.send("action=1&idUser=" + idUser);
}

function joinPartie(idParty,idUser){
	
	var servletName = "JoinParty";
	xhr = getXhr();
	xhr.open("POST", "./" + servletName, true);
	xhr.onreadystatechange = function(){
        if(xhr.readyState == 4 && xhr.status == 200){
            response = xhr.responseText;
            document.getElementById('container').innerHTML = response;
    		$.getScript("js/gameFindTheDot.js", function(){
   			var scripts = document.getElementById('container').getElementsByTagName('script');
    	    		for(var i=0; i < scripts.length;i++)
    	    		{
    	    			//alert(""+scripts[i].text);
    	    			/*Sous IE il faut faire un execScript pour que les fonctions soient définie en globale*/
    	    			if (window.execScript)
    	    			{
    	    				/*On replace les éventuels com' html car IE n'aime pas ça*/
    	    				window.execScript(scripts[i].text.replace('<!--',''));
    	    			}
    	    			/*Sous les autres navigateurs on fait un window.eval*/
    	    			else
    	    			{
    	    				window.eval(scripts[i].text);
    	    			}
    	    		}
    		});
       }
    };
	xhr.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded');
	xhr.send("action=2&idUser=" + idUser + "&idParty=" + idParty);
}





/** Fonctions pour le chargement et changement des games **/

//Initalisation d'un la première game
//TODO:enlever le code en dur
function init(id_deParty){
	
	idPartyMain = id_deParty;
	changeGame();
	
}

//cette fonction permet de vérifier que le jeu courant est terminé
//SI c'est le cas contact LinkParty pour lancer le nouveau jeu
function reloadGame(){
	//alert("je suis reload le casse couille");
	var finished = document.getElementById('finishGame').value;

	if(finished == "true"){

		xhr = getXhr();
		clearInterval(idReloadGame);
		document.getElementById('finishGame').value = "false";
		changeGame();
	}
}

function changeGame(){
	//récupération du nom du jeu suivant + idgamesuivan
	
	xhr.open("POST", "./LinkParty", true);
	xhr.onreadystatechange = function(){
		if(xhr.readyState == 4 && xhr.status == 200){
	        response = xhr.responseText;
			
			var servletNextGame = response;
			alert("ResponseLinkParty : "+ response);
			//La partie est fini
			if(response =="0"){
				alert("partie finie");
				return;
			}
			servletNextGame="BuildPath";
			
			//on demande à la servlet concerncé de nous envoyé les paramètres
			servletName = servletNextGame;
			var xhr2 = getXhr();
			
			xhr2.open("POST", "./" + servletName, true);
			xhr2.onreadystatechange = function(){
				
		        if(xhr2.readyState == 4 && xhr2.status == 200) {
		            response = xhr2.responseText;
		            document.getElementById('infos').innerHTML = response;
					
					//On lance la fonction concerné
		           // alert("response: "+response);
		            eval("run"+servletNextGame+"('" + response + "')");
		            alert("response: "+response);
		            eval("run"+servletNextGame+"('" + stringParam + "')");
		            idReloadGame = setInterval("reloadGame()", 800);
		       }
			document.getElementById("infos");
			};
			xhr2.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded');
			xhr2.send("action=1");
		}
		
	};
	xhr.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded');
	xhr.send("idParty="+idPartyMain);
}





/** Fonctions pour les scores de la partie **/

function initScoreParty(idParty){
	setInterval("scoreParty("+idParty+")", 500);
}

function scoreParty(idParty){
	var servletName = "ScoreParty";
	xhr = getXhr();
	xhr.open("POST", "./" + servletName, true);
	xhr.onreadystatechange = function(){
		
        if(xhr.readyState == 4 && xhr.status == 200) {
            response = xhr.responseText;
            document.getElementById('scoreparty').innerHTML = response;
       }
	};
	xhr.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded');
	xhr.send("idParty="+idParty);
}





/** Fonctions pour le chat **/

function initChat(idParty){
	setInterval("getAllMessage("+idParty+")", 100);
}

function getAllMessage(idParty){
	var servletName = "Chat";
	xhr = getXhr();
	xhr.open("POST", "./" + servletName, true);
	xhr.onreadystatechange = function(){
		
        if(xhr.readyState == 4 && xhr.status == 200) {
            response = xhr.responseText;
            document.getElementById('chat').innerHTML = response;
       }
	};
	xhr.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded');
	xhr.send("action=1&idParty="+idParty);
}

function sendMessage(idParty,idUser){
	var servletName = "Chat";
	xhr = getXhr();
	xhr.open("POST", "./" + servletName, true);
	xhr.onreadystatechange = function(){
		
        if(xhr.readyState == 4 && xhr.status == 200) {
            response = xhr.responseText;
            document.getElementById('chat').innerHTML = response;
       }
	};
	var msg = document.getElementsByName('message')[0].value;
	xhr.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded');
	xhr.send("action=2&idParty="+idParty+"&idUser="+idUser+"&message="+msg);
}