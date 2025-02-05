// Referencias a los elementos del formulario
const form = document.getElementById('registerForm');
const nombreInput = document.getElementById('nombre');
const emailInput = document.getElementById('email');
const passwordInput = document.getElementById('password');
const confirmPasswordInput = document.getElementById('confirm-password');
const registerBtn = document.getElementById('registerBtn');

// Referencias a los mensajes de error
const nombreError = document.getElementById('nombreError');
const emailError = document.getElementById('emailError');
const passwordError = document.getElementById('passwordError');
const confirmPasswordError = document.getElementById('confirmPasswordError');

// Función para validar el formulario
//function validateForm() {
    //let isValid = true;

	nombreInput.addEventListener("input", () => {
	    // Validar que el nombre no tenga números
	    if (!/^[a-zA-Z\s]+$/.test(nombreInput.value)) {
	        nombreError.textContent = 'El nombre no debe contener números.';
	        registerBtn.disabled = true;
	    } else {
	        nombreError.textContent = '';
			registerBtn.disabled = false;
	    }	
	});

	emailInput.addEventListener("input", () => {
	    // Validar el correo electrónico
	    if (!/^[a-z]+\.[a-z]+(?:(0[1-9])|([1-9]\d))?@epn\.edu\.ec$/.test(emailInput.value)) {
	        emailError.textContent = 'Por favor, ingresa un correo válido.';
			registerBtn.disabled = true;
	    } else {
	        emailError.textContent = '';
			registerBtn.disabled = false;
	    }
	});

	passwordInput.addEventListener("input", () => {
	    // Validar la contraseña
	    if (passwordInput.value.length < 8) {
	        passwordError.textContent = 'La contraseña debe tener al menos 8 caracteres.';
			registerBtn.disabled = true;
	    } else {
	        passwordError.textContent = '';
			registerBtn.disabled = false;
	    }
	});

	confirmPasswordInput.addEventListener("input", () => {
	    // Validar que las contraseñas coincidan
	    if (passwordInput.value !== confirmPasswordInput.value) {
	        confirmPasswordError.textContent = 'Las contraseñas no coinciden.';
			registerBtn.disabled = true;
	    } else {
	        confirmPasswordError.textContent = '';
			registerBtn.disabled = false;
    	}
	});

    //return isValid;
//}

// Escuchar el clic del botón de registro
/*registerBtn.addEventListener('click', () => {
    if (validateForm()) {
        // Redirigir al login después de un registro exitoso
        window.location.href = 'login.html';
    }
});*/
