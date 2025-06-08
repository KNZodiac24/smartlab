const listaBotonesEliminar = document.querySelectorAll(".boton-eliminar");

listaBotonesEliminar.forEach((btn) =>{
	btn.addEventListener("click", ()=>{
		alert("Implemento eliminado con éxito");
	});	
});