function getXhr(){
	var xhr;
    if(window.XMLHttpRequest)
        xhr = new XMLHttpRequest();
    else{
        if(window.ActiveXObject){
            try{
                xhr = new ActiveXObject("Msxml2.XMLHTTP");           
            }catch (e){
                xhr = new ActiveXObject("Microsoft.XMLHTTP");             
            }
        }
        else{
            alert("Votre navigateur n'est pas compatible Ajax.");
            xhr = false;
        }
    }
    
    return xhr;
}