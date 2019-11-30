function disableInputForma(ev) {
	ev.preventDefault();
	if (document.getElementsByTagName("input")[ev.target.id].disabled == false) {
		document.getElementsByTagName("input")[ev.target.id].disabled = true;
	} else {
	 document.getElementsByTagName("input")[ev.target.id].disabled = false;
	}
}

function submitForma(ev){
	ev.preventDefault();
	
	var containers = document.getElementsByTagName("input");
	for (var i = 0; i < containers.length - 1; ++i){
		containers[i].disabled=false;
	}
	
	ev.currentTarget.parentElement.parentElement.submit();
}

window.onload = function() {
	
	var containers = document.getElementsByTagName("input");
	for (var i = 0; i < containers.length - 1; ++i){
		var inputContainer = containers[i].parentElement.parentElement;
		var btnDisable = document.createElement("button");
		btnDisable.id=i;
		btnDisable.innerText="Modificar";
		btnDisable.className="button";
		inputContainer.appendChild(btnDisable);
		btnDisable.addEventListener("click", disableInputForma);
	}
	containers[containers.length - 1].addEventListener("click", submitForma);
}