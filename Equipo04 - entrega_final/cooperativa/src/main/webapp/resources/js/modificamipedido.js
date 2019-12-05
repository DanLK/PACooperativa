listadoEnPedido = {};

function removerDePedido(idItem) {
	delete listadoEnPedido[idItem];
	repintarPedido();
}

function cambiarCantidadItem(cantidad, idItem) {
	if (cantidad < 1) { cantidad = 1; }
	listadoEnPedido[idItem]["cantidad"] = cantidad;
	listadoEnPedido[idItem]["subtotal"] = parseFloat(cantidad) * listadoEnPedido[idItem]["precioUnitario"];
	repintarPedido();
}

function repintarPedido() {
	
	var tBodyPedido = document.getElementById("tBodyPedido");
		tBodyPedido.innerHTML = "";
	
	var totalPedido = 0.0;
		
	var keyList = Object.keys(listadoEnPedido);
	keyList.forEach(function(key) {
		
		var productoPedido = listadoEnPedido[key];
		
		var newTRow = document.createElement("tr");
		
		var newTRVal = document.createElement("th");
			newTRVal.innerHTML = productoPedido.id;
			newTRow.appendChild(newTRVal);
			
			newTRVal = document.createElement("td");
			newTRVal.innerHTML = productoPedido.nombre;
			newTRow.appendChild(newTRVal);
			
			newTRVal = document.createElement("td");
			newTRVal.innerHTML = productoPedido.contenido;
			newTRow.appendChild(newTRVal);
			
			newTRVal = document.createElement("td");
			newTRVal.innerHTML = productoPedido.departamento;
			newTRow.appendChild(newTRVal);
			
			newTRVal = document.createElement("td");
			newTRVal.innerHTML = parseFloat(productoPedido.precioUnitario).toFixed(2);
			newTRow.appendChild(newTRVal);
			
			newTRVal = document.createElement("td");
		var newTRInput = document.createElement("input");
			newTRInput.className = "input inputPd";
			newTRInput.type = "text";
			newTRInput.value = productoPedido.cantidad;
			newTRInput.addEventListener("change", function(ev){ cambiarCantidadItem(ev.target.value, productoPedido.id); });
			newTRVal.appendChild(newTRInput);
			newTRow.appendChild(newTRVal);
		
			newTRVal = document.createElement("td");
			newTRVal.innerHTML = parseFloat(productoPedido.subtotal).toFixed(2);
			newTRow.appendChild(newTRVal);
			totalPedido += productoPedido.subtotal;
			
			newTRVal = document.createElement("td");
		var newTRBtn = document.createElement("button");
			newTRBtn.className = "button is-danger";
			newTRBtn.innerText = "Remover";
			newTRBtn.addEventListener("click", function(ev) { removerDePedido(productoPedido.id); } );
			newTRVal.appendChild(newTRBtn);
			newTRow.appendChild(newTRVal);
		// Agregamos el row
		tBodyPedido.appendChild(newTRow);
	});
	
	// Actualizamos el total
	var totalPedidoLabel = document.getElementById("totalPedidoLabel");
		totalPedidoLabel.innerText = parseFloat(totalPedido).toFixed(2);
}

function funcionParaAgregar(ev) { 
	
	var idItem = parseInt(ev.target.parentElement.parentElement.children[0].innerText); 
	var nombreItem = ev.target.parentElement.parentElement.children[1].innerText;
	var contenidoItem = ev.target.parentElement.parentElement.children[2].innerText;
	var precioUnitarioItem = parseFloat(ev.target.parentElement.parentElement.children[3].innerText);
	var departamentoItem = ev.target.parentElement.parentElement.children[4].innerText;
	
	
	// Regresamos si ya esta en el arreglo
	if (typeof listadoEnPedido[idItem] != "undefined") { return; } 
	listadoEnPedido[idItem] = {
			"id": idItem,
			"nombre": nombreItem,
			"contenido": contenidoItem,
			"departamento": departamentoItem,
			"precioUnitario": precioUnitarioItem,
			"cantidad": 1,
			"subtotal": precioUnitarioItem
	};
	
	// Repintamos el pedido
	repintarPedido();
	
}

function guardarPedido() {
	
	// Revisamos si es que tenemos al menos un objeto
	var keyList = Object.keys(listadoEnPedido);
	if (keyList.length == 0) return;
	
	// Llenamos y hacemos submit del formulario Fantasma
	var totalPedido = 0.0;
	var codeGhostPedidoID = document.getElementById("codeGhostPedidoID");
		
	var registroFormCodigo = document.getElementById("registroFormCodigo");
	
		//Verificamos si hay que codificar la entrada del select
		var getSelects = document.getElementsByTagName("select");
		registroFormCodigo.value = "";
			
		if (getSelects.length > 0) {
			registroFormCodigo.value += getSelects[0].value + "$";
		}
			
		registroFormCodigo.value += codeGhostPedidoID.innerText + "#";
		
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
	
	// Asignamos accion de agregar a pedido
	var listOfButtons = document.getElementsByClassName("is-success");
	var nButtons = listOfButtons.length;
	for (i = 0; i < nButtons; ++i) {
		listOfButtons[i].addEventListener("click", funcionParaAgregar);
	}
	
	// Asignamos la accion de Guardar cambios del pedido
	var realizarPedidoBtn = document.getElementById("guardarPedidoBtn");
		realizarPedidoBtn.addEventListener("click", guardarPedido);
		
	// Obtenemos la codificacion del pedido
	var codeGhostPedidoItems = document.getElementById("codeGhostPedidoItems");
		console.log(codeGhostPedidoItems.innerText);
	var codePedidoJson = JSON.parse(codeGhostPedidoItems.innerText);
		console.log(codePedidoJson);
		listadoEnPedido = codePedidoJson;
		repintarPedido();
}
