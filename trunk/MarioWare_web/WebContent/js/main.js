
var xhr;

function init(){
	
	var servletName = "Connect";
	xhr = getXhr();
	xhr.open("POST", "./" + servletName, true);
	xhr.onreadystatechange = function(){
        if(xhr.readyState == 4 && xhr.status == 200){
            response = xhr.responseText;
            //document.getElementById('infos').innerHTML = response;
            var x = response.split(';');
        	begin1(x[0], x[1]);
       }
    };
	xhr.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded');
	xhr.send("action=1");

}