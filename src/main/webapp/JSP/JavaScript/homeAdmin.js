/*document.addEventListener("DOMContentLoaded", () => {
    // Inicializamos los elementos en un arreglo con algunos elementos predefinidos
    let elementos = JSON.parse(localStorage.getItem('elementos')) || [];

    if (elementos.length === 0) {
        // Si no hay elementos, agregamos algunos de ejemplo
        elementos = [
            { id: 1, nombre: "Libros", descripcion: "Libro de aplicaciones web" },
            { id: 2, nombre: "PC Lab4-002", descripcion: "PC de laboratorio" },
            { id: 3, nombre: "Proyector HD", descripcion: "Proyector de alta resolución" },
            { id: 4, nombre: "Aula E20P3A12", descripcion: "Aula equipada con pizarras interactivas" },
            { id: 5, nombre: "Microscopio", descripcion: "Microscopio óptico con aumento" },
            { id: 6, nombre: "Aula 1", descripcion: "Aula equipada para conferencias" }
        ];

        // Guardar los elementos iniciales en localStorage
        localStorage.setItem('elementos', JSON.stringify(elementos));
    }

    const elementosBody = document.getElementById("elementos-body");
    const searchInput = document.getElementById("search-input");

    // Guardamos los elementos en localStorage para que persistan
    const saveElementos = () => {
        localStorage.setItem('elementos', JSON.stringify(elementos));
    };

    // Generar elementos dinámicamente
    const renderElementos = (filtro = "") => {
        elementosBody.innerHTML = "";
        const filtrados = elementos.filter((el) =>
            el.nombre.toLowerCase().includes(filtro.toLowerCase())
        );

        filtrados.forEach((el) => {
            const tr = document.createElement("tr");

            tr.innerHTML = `
                <td>
                    <strong>${el.nombre}</strong>
                </td>
                <td>${el.id}</td>
                <td>${el.descripcion}</td>
                <td class="d-flex gap-2">
                    <button class="btn btn-danger" onclick="eliminarElemento(${el.id})">Eliminar</button>
                    <button class="btn btn-secondary">
                        <a href="/frontend/html/edit.html?id=${el.id}" style="color: white; text-decoration: none;">Editar</a>
                    </button>
                </td>
            `;
            elementosBody.appendChild(tr);
        });
    };

    // Función para eliminar un elemento
    window.eliminarElemento = (id) => {
        // Eliminar el elemento con el id correspondiente
        elementos = elementos.filter(el => el.id !== id);

        // Guardamos los elementos actualizados en localStorage
        saveElementos();

        // Volver a renderizar la lista después de eliminar
        renderElementos();
    };

    // Filtro de búsqueda
    searchInput.addEventListener("input", (e) => {
        renderElementos(e.target.value);
    });

    // Renderizar todos los elementos al cargar
    renderElementos();

    // Funcionalidad de Cerrar Sesión
    document.getElementById("logout-btn").addEventListener("click", () => {
        location.href = '/frontend/html/login.html'; // Redirigir al login
    });

    // Funcionalidad de Agregar
    document.getElementById("btn-agregar").addEventListener("click", () => {
        location.href = '/frontend/html/add.html'; // Redirigir a agregar nuevo
    });
});*/
