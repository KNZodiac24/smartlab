<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="modelo.entidades.Usuario" %>
<%@ page session="true" %>
<%
    Usuario usuario = (Usuario) session.getAttribute("usuario");
    if (usuario != null) {
        response.sendRedirect("../LoginController?ruta=ingresar");
        return;
    }
%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Iniciar Sesión - Gestor de Aulas</title>
    <!-- Bootstrap CSS -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
    <link rel="stylesheet" href="style/login.css">
</head>
<body>
    <div class="container-fluid vh-100 d-flex align-items-center justify-content-center">
        <div class="row w-100 h-100">
            <!-- SecciÃ³n de Formulario -->
            <div class="col-md-8 col-lg-5 mx-auto d-flex align-items-center">
                <div class="credenciales shadow rounded p-5 text-center w-100">
                    <h2 class="titulo mb-4">SmartLab</h2>
                    <form id="loginForm" method="POST" action="../LoginController?ruta=ingresar">
                        <div class="mb-4 text-start">
                            <label for="email" class="form-label">Correo Electrónico</label>
                            <input type="email" id="email" name="email" class="form-control form-control-lg" placeholder="correo@epn.edu.ec" required>
                            <small id="emailError" class="text-danger"></small>
                        </div>
                        <div class="mb-4 text-start">
                            <label for="password" class="form-label">Contraseña</label>
                            <input type="password" id="password" name="password" class="form-control form-control-lg" placeholder="Contraseña" required>
                            <small id="passwordError" class="text-danger"></small>
                        </div>
                        <div class="botones d-grid gap-3 mt-4">
                            <button type="submit" id="loginBtn" class="btn btn-primary btn-lg boton-morado">Iniciar Sesión</button>
                            <button type="button" class="btn btn-light btn-lg boton-gris" onclick="location.href='../RegisterController?ruta=registrarse'">Regístrate ahora</button>
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </div>
    <script src="JavaScript/login.js"></script>
    <!-- Bootstrap JS -->
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
