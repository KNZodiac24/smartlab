<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="modelo.entidades.Usuario" %>
<%@ page import="modelo.entidades.Administrador" %>
<%@ page session="true" %>
<%
    Usuario usuario = (Usuario) session.getAttribute("usuario");
    if (usuario == null) {
        response.sendRedirect("Login.jsp");
        return;
    }
    
    if(!(usuario instanceof Administrador)){
    	response.sendRedirect("Login.jsp");
    	return;
    }
%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Agregar Elemento</title>
    <!-- Bootstrap CSS -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
    <link rel="stylesheet" href="style/edit.css">
</head>
<body>
    <div class="container-fluid vh-100 bg-primario">
        <header class="py-3 bg-secundario text-white text-center shadow">
            <h1 class="titulo">Agregar Implemento</h1>
        </header>
        <main class="container py-4">
            <section class="shadow p-4 bg-white rounded">
				<form method="POST" action="../GestionController?ruta=agregarElemento" id="add-form">
				    <div class="mb-3">
				        <label for="nombre" class="form-label">Nombre</label>
				        <input type="text" class="form-control" name="nombre" id="nombre" required>
				    </div>
				    <div class="mb-3">
				        <label for="descripcion" class="form-label">Descripción</label>
				        <input type="text" class="form-control" name="descripcion" id="descripcion" required>
				    </div>
				    <div class="mb-3">
				        <button type="submit" id="boton-crear" class="btn btn-success">Crear Implemento</button>
				    </div>
				</form>
				<button class="btn btn-secondary" id="cancel-btn" onclick="location.href='../LoginController?ruta=ingresar'">Cancelar</button>
            </section>
        </main>
    </div>
    <!-- <script src="JavaScript/add.js"></script> -->
    <script type="text/javascript">
    	const btnCrear = document.getElementById("boton-crear");
    	
    	btnCrear.addEventListener("click", ()=>{
    		alert("Implemento creado con éxito.");
    	});
    </script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
