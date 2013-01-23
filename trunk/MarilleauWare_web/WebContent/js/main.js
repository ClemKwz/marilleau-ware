
var xhr;
var idReloadGame;
var idParty;
//Initalisation d'un la première game
//TODO:enlever le code en dur
function init(id_deParty){
	
	idParty = id_deParty;
	changeGame();
	
}

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
function initChat(idParty){
	var v = setInterval("getAllMessage("+idParty+")", 100);
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
	var tabId = [];
	xhr.open("POST", "./LinkParty", true);
	xhr.onreadystatechange = function(){
		if(xhr.readyState == 4 && xhr.status == 200){
	        response = xhr.responseText;
			
			var servletNextGame = response
			alert("ResponseLinkParty : "+ response);
			//La partie est fini
			if(response =="0"){
				alert(fini);
				return;
			}
			servletNextGame="FindTheDot";
			
			//on demande à la servlet concerncé de nous envoyé les paramètres
			servletName = servletNextGame;
			xhr = getXhr();
			
			xhr.open("POST", "./" + servletName, true);
			xhr.onreadystatechange = function(){
				
		        if(xhr.readyState == 4 && xhr.status == 200) {
		            response = xhr.responseText;
		            document.getElementById('infos').innerHTML = response;
		            var param = response.split(';');
		            var stringParam="";
		            
		          //On créer la liste des paramètre sous forme de string
					for(var i=0;i<param.length -1;i++){
						stringParam += param[i]+","
					}
					stringParam += param[param.length-1];
					
					//On lance la fonction concerné
		            eval("run"+servletNextGame+"(" + stringParam + ")");
		            idReloadGame = setInterval("reloadGame()", 800);
		       }
			var info = document.getElementById("infos");
			};
			xhr.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded');
			xhr.send("action=1");
		}
		
	};
	xhr.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded');
	xhr.send("idParty="+idParty);
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