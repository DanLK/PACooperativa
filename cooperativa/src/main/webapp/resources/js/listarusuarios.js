
function toogleDropdown(ev) {
	ev.currentTarget.classList.toggle('is-active');
}

function redirectModificar(ev) {
	var usuarioID = ev.currentTarget.id;
	window.location.href = "modificar/usuario/" + usuarioID;
}

function redirectRemover(ev) {
	var usuarioIDStr = ev.currentTarget.id;
	var usuarioID = usuarioIDStr.split("_")[1];
	window.location.href = "remover/usuario/" + usuarioID;
}

window.onload = function() {
	console.log("Mis Pedidos");
	
	// Agregar funcionalidad a los botones de mostrar productos en el pedido
	var dropdowns = document.getElementsByClassName("dropdown");
	for(var i = 0; i < dropdowns.length; ++i) {
		dropdowns[i].addEventListener("click", toogleDropdown);
	}
	
	// Agregar funcioinalidad a los botones de modificar
	var buttonsModify = document.getElementsByClassName("is-link");
	for(var i = 0; i < buttonsModify.length; ++i) {
		buttonsModify[i].addEventListener("click", redirectModificar);
	}
	
	// Agregar funcioinalidad a los botones de modificar
	var buttonsModify = document.getElementsByClassName("is-danger");
	for(var i = 0; i < buttonsModify.length; ++i) {
		buttonsModify[i].addEventListener("click", redirectRemover);
	}
	
}

