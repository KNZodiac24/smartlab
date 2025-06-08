const listaBotonesFavoritos = document.querySelectorAll(".boton-favoritos");
const listaBotonesEliminarFavoritos = document.querySelectorAll(".boton-eliminar-favoritos");

document.getElementById("buscadorElementos").addEventListener("keyup", function() {
  const filtro = this.value.toLowerCase();
  const filas = document.querySelectorAll("#tablaAllElements tbody tr");
  let hayCoincidencias = false;

  filas.forEach(fila => {
    const texto = fila.cells[0].textContent.toLowerCase();
    if (texto.includes(filtro)) {
      fila.style.display = "";
	  hayCoincidencias = true;
    } else {
      fila.style.display = "none";
    }
  });
  
  document.getElementById("mensaje-vacio").innerText = hayCoincidencias ? "" : "No se encontraron implementos con el nombre "+filtro;
  document.getElementById("mensaje-vacio").style.display = hayCoincidencias ? "none" : "block";
});

listaBotonesFavoritos.forEach((btn) =>{
	btn.addEventListener("click", ()=>{
		alert("El implemento se ha añadido a tus favoritos.");
	});	
});

listaBotonesEliminarFavoritos.forEach((btn) =>{
	btn.addEventListener("click", ()=>{
		alert("El implemento se ha eliminado de tus favoritos.");
	});	
});