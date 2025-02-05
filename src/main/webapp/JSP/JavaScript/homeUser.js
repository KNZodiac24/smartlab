document.addEventListener("DOMContentLoaded", () => {
    const reservasBody = document.getElementById("reservaciones-body");
    const favoritosBody = document.getElementById("favoritos-body");
    const searchInput = document.querySelector(".filter-container input");
    const reservarRows = document.querySelectorAll("section:nth-child(2) tbody tr");
    const logoutBtn = document.getElementById("logout-btn");

      // Función para cerrar sesión
      logoutBtn.addEventListener("click", () => {
        localStorage.clear(); // Limpiar el localStorage
        window.location.href = "/frontend/html/login.html"; // Redirigir al login
    });


    // Cargar las reservas desde el localStorage
    const cargarReservas = () => {
        reservasBody.innerHTML = "";
        const reservas = JSON.parse(localStorage.getItem("reservas")) || [];
        reservas.forEach((reserva) => {
            const tr = document.createElement("tr");
            tr.innerHTML = `
                <td>${reserva.elemento}</td>
                <td>${reserva.descripcion}</td>
                <td>${reserva.fecha}</td>
                <td>${reserva.hora}</td>
            `;
            reservasBody.appendChild(tr);
        });
    };

    // Cargar los favoritos desde el localStorage
    const cargarFavoritos = () => {
        favoritosBody.innerHTML = "";
        const favoritos = JSON.parse(localStorage.getItem("favoritos")) || [];
        favoritos.forEach((favorito) => {
            const tr = document.createElement("tr");
            tr.setAttribute("data-id", favorito.id);
            tr.innerHTML = `
                <td>${favorito.elemento}</td>
                <td>${favorito.descripcion}</td>
                <td class="d-flex align-items-center gap-3">
                    <button class="btn btn-primary boton-morado verificar-btn">Verificar Disponibilidad</button>
                </td>
            `;

            // Reactivar el evento de "Verificar Disponibilidad" en el clon
            const botonDisponibilidad = tr.querySelector(".verificar-btn");
            if (botonDisponibilidad) {
                botonDisponibilidad.addEventListener("click", () => {
                    localStorage.setItem("elementoSeleccionado", favorito.elemento);
                    localStorage.setItem("descripcionSeleccionada", favorito.descripcion);
                    window.location.href = "/frontend/html/reservar.html";
                });
            }

            favoritosBody.appendChild(tr);
        });
    };

    // Guardar los favoritos en el localStorage
    const guardarFavoritos = () => {
        const favoritos = [];
        favoritosBody.querySelectorAll("tr").forEach((row) => {
            const id = row.getAttribute("data-id");
            const elemento = row.querySelector("td:first-child").textContent.trim();
            const descripcion = row.querySelector("td:nth-child(2)").textContent.trim();
            favoritos.push({ id, elemento, descripcion });
        });
        localStorage.setItem("favoritos", JSON.stringify(favoritos));
    };

    // Actualizar eventos de las estrellas y mover filas a favoritos
    const actualizarEstrellas = () => {
        const estrellas = document.querySelectorAll(".estrella");
        estrellas.forEach((estrella) => {
            estrella.addEventListener("click", () => {
                const fila = estrella.closest("tr");
                const idElemento = fila.getAttribute("data-id");
                const elementoEnFavoritos = favoritosBody.querySelector(`[data-id="${idElemento}"]`);

                if (!elementoEnFavoritos) {
                    const clon = fila.cloneNode(true);

                    // Reactivar el evento de "Verificar Disponibilidad" en el clon
                    const botonDisponibilidad = clon.querySelector(".verificar-btn");
                    if (botonDisponibilidad) {
                        botonDisponibilidad.addEventListener("click", () => {
                            const elemento = clon.querySelector("td:first-child").textContent.trim();
                            const descripcion = clon.querySelector("td:nth-child(2)").textContent.trim();
                            localStorage.setItem("elementoSeleccionado", elemento);
                            localStorage.setItem("descripcionSeleccionada", descripcion);
                            window.location.href = "/frontend/html/reservar.html";
                        });
                    }

                    // Remover la estrella en el clon
                    const estrellaEnClon = clon.querySelector(".estrella");
                    if (estrellaEnClon) estrellaEnClon.remove();

                    favoritosBody.appendChild(clon);

                    estrella.classList.add("morada");
                    guardarFavoritos(); // Guardar en localStorage
                } else {
                    elementoEnFavoritos.remove();
                    estrella.classList.remove("morada");
                    guardarFavoritos(); // Actualizar en localStorage
                }
            });
        });
    };

    // Llamar a la función para inicializar los eventos en las estrellas
    actualizarEstrellas();

    // Búsqueda dinámica en "Reservar Ahora"
    searchInput.addEventListener("input", () => {
        const valor = searchInput.value.toLowerCase();
        reservarRows.forEach((row) => {
            const texto = row.querySelector("td:first-child").textContent.toLowerCase();
            row.style.display = texto.includes(valor) ? "" : "none";
        });
    });

    // Configurar eventos de "Verificar Disponibilidad" para los elementos de "Reservar Ahora"
    const agregarEventosVerificarDisponibilidad = (rows) => {
        rows.forEach((row) => {
            const btn = row.querySelector(".verificar-btn");
            if (btn) {
                btn.addEventListener("click", () => {
                    const elemento = row.querySelector("td:first-child").textContent.trim();
                    const descripcion = row.querySelector("td:nth-child(2)").textContent.trim();
                    localStorage.setItem("elementoSeleccionado", elemento);
                    localStorage.setItem("descripcionSeleccionada", descripcion);
                    window.location.href = "/frontend/html/reservar.html";
                });
            }
        });
    };

    agregarEventosVerificarDisponibilidad(reservarRows);

    // Cargar las reservas y favoritos al inicio
    cargarReservas();
    cargarFavoritos();
});
