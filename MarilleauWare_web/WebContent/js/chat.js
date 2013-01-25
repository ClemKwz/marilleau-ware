/** Fonctions pour le chat **/

function initChat(idParty){
	setInterval("getAllMessage("+idParty+")", 500);
}

function getAllMessage(idParty){
	var servletName = "Chat";
	var msg_xhr = getXhr();
	msg_xhr.open("POST", "./" + servletName, true);
	msg_xhr.onreadystatechange = function(){
		
        if(msg_xhr.readyState == 4 && msg_xhr.status == 200) {
            response = msg_xhr.responseText;
            document.getElementById('chat').innerHTML = response;
       }
	};
	msg_xhr.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded');
	msg_xhr.send("action=1&idParty="+idParty);
}

function sendMessage(idParty,idUser){
	var servletName = "Chat";
	var sendMsg_xhr = getXhr();
	sendMsg_xhr.open("POST", "./" + servletName, true);
	sendMsg_xhr.onreadystatechange = function(){
		
        if(sendMsg_xhr.readyState == 4 && sendMsg_xhr.status == 200) {
            response = sendMsg_xhr.responseText;
            document.getElementById('chat').innerHTML = response;
       }
	};
	var msg = document.getElementsByName('message')[0].value;
	document.getElementsByName('message')[0].value = "";
	sendMsg_xhr.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded');
	sendMsg_xhr.send("action=2&idParty="+idParty+"&idUser="+idUser+"&message="+msg);
}
