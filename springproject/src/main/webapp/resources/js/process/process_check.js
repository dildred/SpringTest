/**
 * 
 */
$(function(){
	var URLCheck = location.href;
	if(URLCheck.lastIndexOf("/")==URLCheck.length-1){
		URLCheck = URLCheck.substring(0,URLCheck.lastIndexOf("/"));
		history.pushState(null,null,URLCheck);
	}
});