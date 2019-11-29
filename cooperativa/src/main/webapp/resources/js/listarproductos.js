
function redirectModificar(ev) {
	var productoID = ev.currentTarget.id;
	window.location.href = "modificar/producto/" + productoID;
}

function redirectRemover(ev) {
	var productoIDStr = ev.currentTarget.id;
	var productoID = productoIDStr.split("_")[1];
	window.location.href = "remover/producto/" + productoID;
}

window.onload = function() {
	console.log("Los productos");
	
	// Agregar funcionalidad a los botones de modificar
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

