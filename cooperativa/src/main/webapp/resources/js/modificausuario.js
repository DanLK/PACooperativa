

function guardarUsuario() {
	
	// Revisamos si es que tenemos al menos un objeto
	var keyList = Object.keys(listadoEnPedido);
	if (keyList.length == 0) return;
	
	// Llenamos y hacemos submit del formulario Fantasma
	var totalPedido = 0.0;
	var codeGhostPedidoID = document.getElementById("codeGhostPedidoID");
		
	var registroFormCodigo = document.getElementById("registroFormCodigo");
		registroFormCodigo.value = "" + codeGhostPedidoID.innerText + "#";
		
		var keyList = Object.keys(listadoEnPedido);
		keyList.forEach(function(key) {
			var productoPedido = listadoEnPedido[key];
			totalPedido += productoPedido.subtotal;
			registroFormCodigo.value += "" + productoPedido.id + "," + productoPedido.cantidad + ";";
		});
		
	var registroFormPedidoTotal = document.getElementById("registroFormPedidoTotal"); 
		registroFormPedidoTotal.value = parseFloat(totalPedido).toFixed(2);
		
	var modificarPedidoForm = document.getElementById("modificarPedidoForm");
	modificarPedidoForm.submit();
	
}

window.onload = function() {
	console.log("Nuevo Pedido");
	
	
	// Asignamos la accion de Guardar cambios del pedido
	var realizarPedidoBtn = document.getElementById("guardarPedidoBtn");
		realizarPedidoBtn.addEventListener("click", guardarPedido);
		
	// Obtenemos la codificacion del pedido

}
