
var xhr;
var idReloadGame;

//Initalisation d'un la première game
//TODO:enlever le code en dur
function init(){
	var servletName = "FindTheDot";
	xhr = getXhr();
	
	xhr.open("POST", "./" + servletName, true);
	xhr.onreadystatechange = function(){
		
        if(xhr.readyState == 4 && xhr.status == 200) {
            response = xhr.responseText;
            document.getElementById('infos').innerHTML = response;
            var x = response.split(';');
            runFindTheDot(x[0], x[1], x[2], x[3], x[4], x[5], x[6]);
            idReloadGame = setInterval("reloadGame()", 800);
       }
	var info = document.getElementById("infos");
	};
	xhr.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded');
	xhr.send("action=1");


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
		//récupération de l'id du game fini
		var idGame= document.getElementById('idGameValue').value;
		
		
		
		//récupération du nom du jeu suivant + idgamesuivan
		var tabId = [];
		xhr.open("POST", "./LinkParty", true);
		xhr.onreadystatechange = function(){
			if(xhr.readyState == 4 && xhr.status == 200){
		        response = xhr.responseText;
				
				//alert(response);
				var tab = response.split(";");
				var idNextGame= tab[0];
				var servletNextGame = tab[1];
				//alert("servlet : "+servletNextGame + "\nidNextGame : " + idNextGame+ "\nservletNextGame : " + servletNextGame);
				//servletNextGame= "CheckBox";
				eval("run"+servletNextGame+"()");
			}
			
		};
		xhr.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded');
		xhr.send("idGame="+idGame);
	}
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