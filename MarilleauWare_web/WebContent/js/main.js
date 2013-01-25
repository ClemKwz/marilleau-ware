
/** Variables **/

var idReloadGame;
var idPartyMain;
var idInitGame;
/** Fonctions pour afficher et joindre les parties **/

function initJoinPartie(idUser){
	
	var servletName = "JoinParty";
	var xhr = getXhr();
	xhr.open("POST", "./" + servletName, true);
	xhr.onreadystatechange = function(){
        if(xhr.readyState == 4 && xhr.status == 200){
            response = xhr.responseText;
            document.getElementById('containerJoinParty').innerHTML = response;
       }
    };
	xhr.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded');
	xhr.send("action=1&idUser=" + idUser);
}

function joinPartie(idParty,idUser){
	
	var servletName = "JoinParty";
	var xhr = getXhr();
	xhr.open("POST", "./" + servletName, true);
	xhr.onreadystatechange = function(){
        if(xhr.readyState == 4 && xhr.status == 200){
            response = xhr.responseText;
            document.getElementById('containerJoinParty').innerHTML = response;
    		//$.getScript("js/gameFindTheDot.js", function(){
   			var scripts = document.getElementById('containerJoinParty').getElementsByTagName('script');
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
    		//});
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
	 idInitGame = setInterval("initParty()", 2000);
	 
	
}

//cette fonction permet de vérifier que le jeu courant est terminé
//SI c'est le cas contact LinkParty pour lancer le nouveau jeu
function reloadGame(){
	//alert("je suis reload le casse couille");
	var finished = document.getElementById('finishGame').value;

	if(finished == "true"){
		
//		xhr = getXhr();
		clearInterval(idReloadGame);
		
		//
		
		//Réinitialisation des div
		document.getElementById('finishGame').value = "false";
		document.getElementById('infos').innerHTML = "Attente nouveau jeu";
		 document.getElementById('chrono').innerHTML = "";
//		document.getElementById('chrono').value = "";
//		document.getElementById('divScore').value = "";
		sleep(3000);
		changeGame();
	}
}

function initParty(){
	//alert("Intalisation de la partie");
	var xhrInitParty = getXhr();
	document.getElementById('infos').innerHTML = "";
	xhrInitParty.open("POST", "./LinkParty", true);
	xhrInitParty.onreadystatechange = function(){
		if(xhrInitParty.readyState == 4 && xhrInitParty.status == 200){
	        response = xhrInitParty.responseText;
			
			var servletNextGame = response;
			//alert("ResponseLinkParty : "+ response);
			
			//La partie est en attente
			if(response =="-2"){
				document.getElementById('infos').innerHTML = "Partie en attente d'autre joueur";
				return;
			}
			
			if(response == "0"){
				document.getElementById('infos').innerHTML = "Partie en attente d'autre joueur";
				clearInterval(idInitGame);
				return;
			}
	
			clearInterval(idInitGame);
			//on demande à la servlet concerncé de nous envoyé les paramètres
//			servletName = servletNextGame;
			
//			servletName="CheckBox";
//			servletNextGame="BuildPath";
			
			var xhr2 = getXhr();
			
			//xhr2.open("POST", "./" + servletName, true);
			xhr2.open("POST", "./" + servletNextGame, true);
			xhr2.onreadystatechange = function(){
				
		        if(xhr2.readyState == 4 && xhr2.status == 200) {
		            response = xhr2.responseText;
		            //document.getElementById('infos').innerHTML = response;
					
		            
		            //alert("response: "+response);
					//On lance la fonction concerné
		           // alert("response: "+response);
		        	document.getElementById('infos').innerHTML = "Jouez !";
		            eval("run"+servletNextGame+"('" + response + "')");
		           
		          
		            idReloadGame = setInterval("reloadGame()", 800);
		       }
			document.getElementById("infos");
			};
			xhr2.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded');
			xhr2.send("action=1");
		}
		
	};
	xhrInitParty.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded');
	xhrInitParty.send("idParty="+idPartyMain);
}
function changeGame(){
	//récupération du nom du jeu suivant + idgamesuivan

	//alert("Le nouveau jeu va commencer");
	document.getElementById('infos').innerHTML = "";
	var xhr = getXhr();
	xhr.open("POST", "./LinkParty", true);
	xhr.onreadystatechange = function(){
		if(xhr.readyState == 4 && xhr.status == 200){
	        response = xhr.responseText;
			
			var servletNextGame = response;
			//alert("ResponseLinkParty : "+ response);
			
			//La partie est fini
			if(response =="0"){
				alert("partie finie");
	        	document.getElementById('infos').innerHTML = "La partie est finie !";
				return;
			}

//			servletNextGame="BuildPath";
			
			//on demande à la servlet concerncé de nous envoyé les paramètres
			//servletName = servletNextGame;
			
			var xhr2 = getXhr();
			//servletName="CheckBox";
			
			
			//xhr2.open("POST", "./" + servletName, true);
			xhr2.open("POST", "./" + servletNextGame, true);
			xhr2.onreadystatechange = function(){
				
		        if(xhr2.readyState == 4 && xhr2.status == 200) {
		            response = xhr2.responseText;
		            //document.getElementById('infos').innerHTML = response;
					
		            
		            //alert("response: "+response);
					//On lance la fonction concerné
		            // alert("response: "+response);
		        	document.getElementById('infos').innerHTML = "Jouez !";
		            eval("run"+servletNextGame+"('" + response + "')");
		           
		          
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

/* Fonction de sleep */

function sleep(milliseconds) {
  var start = new Date().getTime();
  for (var i = 0; i < 1e7; i++) {
    if ((new Date().getTime() - start) > milliseconds){
      break;
    }
  }
}

