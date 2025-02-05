document.addEventListener("DOMContentLoaded", () => {
    let elementos = JSON.parse(localStorage.getItem('elementos')) || [];

//    const addForm = document.getElementById("add-form");

    // Función para generar un ID secuencial basado en el último ID en la lista
    const generarIdSecuencial = () => {
        return elementos.length > 0 ? elementos[elementos.length - 1].id + 1 : 1;
    };

    // Al enviar el formulario, agregamos el nuevo elemento
	/*
    addForm.addEventListener("submit", (e) => {
        e.preventDefault();

        const nuevoElemento = {
            id: generarIdSecuencial(), // Generar un ID secuencial
            nombre: document.getElementById("nombre").value,
            descripcion: document.getElementById("descripcion").value
        };

        // Agregar el nuevo elemento al arreglo
        elementos.push(nuevoElemento);

        // Guardar los cambios en localStorage
        localStorage.setItem('elementos', JSON.stringify(elementos));

        // Redirigir al home con los cambios
        window.location.href = "homeAdmin.html"; // Redirigir al home
    });
	*/

    // Al cancelar, regresa al listado sin cambios
    document.getElementById("cancel-btn").addEventListener("click", () => {
        window.location.href = "homeAdmin.html"; // Redirigir al home
    });
});
