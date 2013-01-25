/** Fonctions pour les scores de la partie **/

function initScoreParty(idParty){
	setInterval("scoreParty("+idParty+")", 200);
}

function scoreParty(idParty){
	var servletName = "ScoreParty";
	var xhr = getXhr();
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