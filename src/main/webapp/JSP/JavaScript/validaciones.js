// REGISTER

// Función para mostrar y ocultar la contraseña
function togglePassword(inputId) {
    const input = document.getElementById(inputId);
    input.type = input.type === "password" ? "text" : "password";
}

// Función para validar el formulario
function validateForm() {
    const nombre = document.getElementById("nombre").value.trim();
    const email = document.getElementById("email").value.trim();
    const password = document.getElementById("password").value.trim();
    const confirmPassword = document.getElementById("confirm-password").value.trim();

    // Verificar si todos los campos están llenos
    if (!nombre || !email || !password || !confirmPassword) {
        alert("Por favor, llena todos los campos.");
        return;
    }

    // Validar formato del correo electrónico
    if (!validateEmail(email)) {
        alert("Por favor, ingresa un correo electrónico válido.");
        return;
    }

    // Verificar si las contraseñas coinciden
    if (password !== confirmPassword) {
        alert("Las contraseñas no coinciden.");
        return;
    }

    // Redirigir al inicio de sesión si todo está correcto
    window.location.href = '/frontend/login.html';
}

// Función para validar el formato del correo electrónico
function validateEmail(email) {
    const regex = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;
    return regex.test(email);
}
