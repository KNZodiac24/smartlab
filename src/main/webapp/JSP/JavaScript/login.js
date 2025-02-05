// Referencias a los elementos del formulario
const form = document.getElementById('loginForm');
const emailInput = document.getElementById('email');
const passwordInput = document.getElementById('password');
const loginBtn = document.getElementById('loginBtn');

// Referencias a los mensajes de error
const emailError = document.getElementById('emailError');
const passwordError = document.getElementById('passwordError');

// Función para validar el formulario
//function validateLoginForm() {
    //let isValid = true;

    // Validar el correo electrónico
	emailInput.addEventListener("input", () => {
		// Validar el correo electrónico
		if (!/^[a-z]+\.[a-z]+(?:(0[1-9])|([1-9]\d))?@epn\.edu\.ec$/.test(emailInput.value)) {
			emailError.textContent = 'Por favor, ingresa un correo válido.';
			loginBtn.disabled = true;
		} else {
	        emailError.textContent = '';
			loginBtn.disabled = false;
		}
	});

    // Validar que la contraseña no esté vacía
	passwordInput.addEventListener("input", () => {
	    if (passwordInput.value.trim() === '') {
	        passwordError.textContent = 'La contraseña no puede estar vacía.';
	        loginBtn.disabled = true;
	    } else {
	        passwordError.textContent = '';
			loginBtn.disabled = false;
	    }
	});

    //return isValid;
//}

// Escuchar el clic del botón de inicio de sesión
/*loginBtn.addEventListener('click', () => {
    if (validateLoginForm()) {
        const email = emailInput.value.trim();
        const password = passwordInput.value.trim();

        // Verificar si es admin o usuario normal
        if (email === 'admin@gmail.com' && password === 'admin') {
            // Redirigir al Home Admin
            window.location.href = '/frontend/html/homeAdmin.html';
        } else {
            // Redirigir al Home User
            window.location.href = '/frontend/html/homeUser.html';
        }
    }
});*/
