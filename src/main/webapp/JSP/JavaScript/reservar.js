const listaDeHoras = document.querySelectorAll("#lista-horas li");
const btnReservar = document.querySelector(".btn-reservar");
var horaSeleccionada = null;
var fechaSeleccionada = null;

listaDeHoras.forEach((li) => {
	if(li.classList.contains("disponible")){
		li.addEventListener("click", () => {
	    	document.querySelectorAll("#lista-horas li").forEach((item) => item.classList.remove("seleccionada"));
	    	li.classList.add("seleccionada");
	    	horaSeleccionada = Array.from(listaDeHoras).findIndex(li => li.classList.contains("disponible") && li.classList.contains("seleccionada")) + 1;
	    	btnReservar.style.display = "block";
	    	fechaSeleccionada = fechaInput.value;
	    });
	}
        	
    if(li.classList.contains("reservado")){
    	li.addEventListener("click", () => {
			alert("El implemento ya se encuentra reservado en este horario.");
    		document.querySelectorAll("#lista-horas li").forEach((item) => item.classList.remove("seleccionada"));
			btnReservar.style.display = "none";
	        horaSeleccionada = 0;
	        fechaSeleccionada = fechaInput.value;
	  	});
	}
});

btnReservar.addEventListener("click", ()=>{
	if(fechaSeleccionada != null && horaSeleccionada != null){
		alert("Reserva realizada con éxito.");
	}
});