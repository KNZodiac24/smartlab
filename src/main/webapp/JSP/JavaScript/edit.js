document.addEventListener("DOMContentLoaded", () => {
    const params = new URLSearchParams(window.location.search);
    const id = params.get("id"); // Obtenemos el ID del elemento desde la URL

    // Recuperamos los elementos del localStorage
    let elementos = JSON.parse(localStorage.getItem('elementos')) || [];

    // Buscamos el elemento con el ID especificado en la URL
    const elemento = elementos.find(el => el.id === parseInt(id));

    if (elemento) {
        // Llenamos el formulario con los datos del elemento
        document.getElementById("id").value = elemento.id;
        document.getElementById("nombre").value = elemento.nombre;
        document.getElementById("descripcion").value = elemento.descripcion;
    } else {
        // Si no se encuentra el elemento, redirigimos al home
        window.location.href = "/frontend/html/homeAdmin.html";
    }

    // Al enviar el formulario, actualizamos el elemento y regresamos al home
    document.getElementById("edit-form").addEventListener("submit", (e) => {
        e.preventDefault();

        // Actualizamos los datos del elemento
        elemento.nombre = document.getElementById("nombre").value;
        elemento.descripcion = document.getElementById("descripcion").value;

        // Guardamos los elementos actualizados en localStorage
        localStorage.setItem('elementos', JSON.stringify(elementos));

        // Redirigimos al homeAdmin con los cambios actualizados
        window.location.href = "/frontend/html/homeAdmin.html"; // Redirigir al home
    });

    // Al cancelar, regresa al listado sin cambios
    document.getElementById("cancel-btn").addEventListener("click", () => {
        window.location.href = "/frontend/html/homeAdmin.html"; // Redirigir al home
    });
});
