
function toogleDropdown(ev) {
	ev.currentTarget.classList.toggle('is-active');
}

window.onload = function() {
	console.log("Mis Pedidos");
	
	// Agregar funcionalidad a los botones de mostrar productos en el pedido
	var dropdowns = document.getElementsByClassName("dropdown");
	for(var i = 0; i < dropdowns.length; ++i) {
		dropdowns[i].addEventListener("click", toogleDropdown);
	}
}

