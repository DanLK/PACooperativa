listadoEnPedido = {};

function removerDePedido(idItem) {
	delete listadoEnPedido[idItem];
	repintarPedido();
}

function validateCantidad(cantidad) {	
	var reg = /^\d+$/;	
	var reg2 = /^0+/;	
	return reg.test(cantidad) && (!reg2.test(cantidad));	
}	

function cambiarCantidadItem(cantidad, idItem) {	
		
	if (validateCantidad(cantidad)) {	
		listadoEnPedido[idItem]["cantidad"] = cantidad;	
		listadoEnPedido[idItem]["subtotal"] = parseFloat(cantidad) * listadoEnPedido[idItem]["precioUnitario"];	
		repintarPedido();	
	} else {	
		startModal("La cantidad \" " + cantidad + " \" para el Producto: " + idItem + " - "+ listadoEnPedido[idItem]["nombre"] + ", no es valida. La cantidad debe ser mayor a 0.");	
	}	
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
	if (typeof listadoEnPedido[idItem] != "undefined") { 	
		startModal("El producto ya esta en el pedido");	
		return; 	
	} 
	
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

function realizarPedido(ev) {
	ev.preventDefault();
	
	// Revisamos si es que tenemos al menos un objeto
	var keyList = Object.keys(listadoEnPedido);
	if (keyList.length == 0){	
		startModal("El pedido debe tener al menos un producto");	
		return;	
	} 	
	
	// Debemos tener cantidades validas	
	var inputList = document.getElementsByClassName("inputPd");	
	for (var i = 0; i < inputList.length; ++i) {	
		if (!validateCantidad(inputList[i].value)) {	
			startModal("La cantidad \" " + inputList[i].value + " \", no es valida. La cantidad debe ser mayor a 0.");	
			return;	
		}	
	}
	
	// Llenamos y hacemos submit del formulario Fantasma
	var totalPedido = 0.0;
	var registroFormCodigo = document.getElementById("registroFormCodigo");
		registroFormCodigo.value = "";
		
		var keyList = Object.keys(listadoEnPedido);
		keyList.forEach(function(key) {
			var productoPedido = listadoEnPedido[key];
			totalPedido += productoPedido.subtotal;
			registroFormCodigo.value += "" + productoPedido.id + "," + productoPedido.cantidad + ";";
		});
		
	var registroFormPedidoTotal = document.getElementById("registroFormPedidoTotal"); 
		registroFormPedidoTotal.value = parseFloat(totalPedido).toFixed(2);
		
	var registrarPedidoForm = document.getElementById("registrarPedidoForm");
		registrarPedidoForm.submit();
	
}


//Damos funcionalidad al modal	
function startModal(messageAImprimir) {	
	var messageImp = document.getElementById("messageModalImp");	
		messageImp.innerText = messageAImprimir;	
	var modal = document.querySelector('.modal');  // assuming you have only 1	
	var html = document.querySelector('html');	
		
	modal.classList.add('is-active');	
	html.classList.add('is-clipped');	
		
	function closeModal(e) {	
		e.preventDefault();	
		modal.classList.remove('is-active');	
		html.classList.remove('is-clipped');	
	}	
		
	modal.querySelector('.modal-background').addEventListener('click', closeModal);	
	modal.querySelector('.modal-close').addEventListener('click', closeModal);	
} 

window.onload = function() {
	console.log("Nuevo Pedido");
	
	// Asignamos accion de agregar a pedido
	var listOfButtons = document.getElementsByClassName("is-success");
	var nButtons = listOfButtons.length;
	for (i = 0; i < nButtons; ++i) {
		listOfButtons[i].addEventListener("click", funcionParaAgregar);
	}
	
	// Asignamos la accion de registrar el pedido
	var realizarPedidoBtn = document.getElementById("realizarPedidoBtn");
		realizarPedidoBtn.addEventListener("click", realizarPedido);
}
