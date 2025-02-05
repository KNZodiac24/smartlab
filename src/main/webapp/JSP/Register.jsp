<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Registro - Gestor de Aulas</title>
    <!-- Bootstrap CSS -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
    <link rel="stylesheet" href="style/register.css">
</head>
<body>
    <div class="container-fluid vh-100 d-flex align-items-center justify-content-center">
        <div class="row w-100 h-100">
            <!-- SecciÃ³n de Imagen -->
            <div class="col-lg-6 d-flex align-items-center justify-content-center bg-morado imagen-seccion">
                <img src="img/LogoCarlitos.png" alt="Ícono de educación" class="img-fluid" style="max-width: 60%;">
            </div>

            <!-- SecciÃ³n de Formulario -->
            <div class="col-lg-6 col-md-12 d-flex flex-column justify-content-center bg-white formulario-seccion p-5">
                <h2 class="titulo mb-4 text-center">Crear Cuenta</h2>
                <form id="registerForm" method="POST" action="../RegisterController?ruta=guardar">
                    <div class="mb-3 position-relative">
                        <label for="nombre" class="form-label">Nombre completo</label>
                        <input type="text" id="nombre" name="nombre" class="form-control form-control-lg" placeholder="Ingresa tu nombre" required>
                        <small id="nombreError" class="text-danger"></small>
                    </div>

                    <div class="mb-3 position-relative">
                        <label for="email" class="form-label">Correo electrónico</label>
                        <input type="email" id="email" name="email" class="form-control form-control-lg" placeholder="correo@epn.edu.ec" required>
                        <small id="emailError" class="text-danger"></small>
                    </div>

                    <div class="mb-3 position-relative">
                        <label for="password" class="form-label">Contraseña</label>
                        <input type="password" id="password" name="password" class="form-control form-control-lg" placeholder="Contraseña" required>
                        <small id="passwordError" class="text-danger"></small>
                    </div>

                    <div class="mb-4 position-relative">
                        <label for="confirm-password" class="form-label">Confirmar contraseña</label>
                        <input type="password" id="confirm-password" name="confirm-password" class="form-control form-control-lg" placeholder="Confirmar contraseña" required>
                        <small id="confirmPasswordError" class="text-danger"></small>
                    </div>

                    <button type="submit" id="registerBtn" class="btn btn-primary btn-lg w-100 fw-bold boton-morado">Registro</button>
                </form>

                <p class="mt-3 text-center">¿Ya tienes una cuenta? <a href="../LoginController?ruta=solicitarIngreso" class="link-morado">Iniciar Sesión</a></p>
            </div>
        </div>
    </div>
    <script src="JavaScript/register.js"></script>
    <!-- Bootstrap JS -->
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
