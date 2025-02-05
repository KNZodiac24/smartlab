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
    	response.sendRedirect("JSP/Login.jsp");
    	return;
    }
%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>
<!DOCTYPE html>
<html lang="es">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Admin - Lista de Elementos</title>
<!-- Bootstrap CSS -->
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css"
	rel="stylesheet">
<link rel="stylesheet" href="JSP/style/homeAdmin.css">
</head>
<body>
	<div class="container-fluid vh-100 bg-primario">
		<header
			class="py-3 bg-secundario text-white text-center shadow d-flex justify-content-between align-items-center px-3">
			<h1 class="titulo">Bienvenido Administrador</h1>
			<!-- BotÃ³n de Cerrar SesiÃ³n -->
			<button class="btn btn-danger" id="logout-btn" 
			onclick="location.href='LoginController?ruta=cerrarSesion'">Cerrar Sesión</button>
		</header>
		<main class="container py-4">
			<section class="shadow p-4 bg-white rounded">
				<div
					class="row mb-3 d-flex justify-content-between align-items-center">
					<div class="col-auto">
						<h2 class="subtitulo">Lista de Elementos</h2>
					</div>
					<div class="col-auto">
						<button class="btn btn-primary boton-morado" id="btn-agregar" 
						        onclick="location.href='GestionController?ruta=solicitarAgregar'">+ Agregar</button>
					</div>
				</div>
				<p class="text-muted">elementos encontrados</p>

				<table class="table table-hover table-striped">
					<thead class="table-light">
						<tr>
							<th>ID</th>
							<th>Nombre</th>
							<th>Descripción</th>
							<th>Acciones</th>
						</tr>
					</thead>
					<tbody id="elementos-body">
						<!-- Elementos dinÃ¡micos se generarÃ¡n aquÃ­ -->
						<c:forEach items="${listaElementosAdmin}" var="elemento">
							<tr>
								<td>${elemento.idElemento}</td>
								<td>${elemento.nombreElemento}</td>
								<td>${elemento.descripcion}</td>
								<td class="d-flex gap-2">
									<!-- Enlace para Eliminar, con más parámetros --> <a
									href="GestionController?ruta=solicitarEliminar&idElemento=${elemento.idElemento}"
									class="btn btn-danger"> Eliminar </a> <!-- Enlace para Editar, con más parámetros -->
									<a
									href="GestionController?ruta=solicitarEdicion&idElemento=${elemento.idElemento}"
									class="btn btn-secondary"> Editar </a>
								</td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
			</section>
		</main>
	</div>
	<script src="JavaScript/homeAdmin.js"></script>
	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
