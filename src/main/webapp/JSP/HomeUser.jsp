<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="modelo.dao.ElementoDAO" %>
<%@ page import="modelo.entidades.Elemento" %>
<%@ page import="modelo.entidades.Usuario" %>
<%@ page import="modelo.entidades.Administrador" %>
<%@ page session="true" %>
<%
    Usuario usuario = (Usuario) session.getAttribute("usuario");
    if (usuario == null) {
        response.sendRedirect("Login.jsp");
        return;
    }
    
    if(usuario instanceof Administrador){
    	response.sendRedirect("JSP/Login.jsp");
    	return;
    }
%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Home User - Gestor de Aulas</title>
    <!-- Bootstrap CSS -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
    <link rel="stylesheet" href="JSP/style/homeUser.css">
</head>
<body>
    <div class="container-fluid vh-100 bg-primario">
        <!-- Fondo adicional al final -->
        <div class="bg-primario py-1"></div>
        <header class="py-3 text-center bg-secundario shadow d-flex justify-content-between align-items-center px-3">
            <h1 class="titulo">Bienvenido ${usuario.nombreUser}</h1>
            <!-- BotÃ³n de Cerrar SesiÃ³n -->
            <button class="btn btn-danger" id="logout-btn" 
            onclick="location.href='LoginController?ruta=cerrarSesion'">Cerrar Sesión</button>
        </header>

        <main class="container py-5">
        	<!-- Favoritos -->
            <section class="shadow p-4 mb-5 bg-white rounded">
                <h2 class="subtitulo">Favoritos:</h2>
                <table class="table table-hover mt-3">
                    <thead class="table-light">
                        <tr>
                            <th>Elemento</th>
                            <th>Descripción</th>
                            <th>Acciones</th>
                        </tr>
                    </thead>
                    <tbody id="favoritos-body">
                        <!-- Aquí se agregarán las filas de favoritos dinámicamente -->
                    	<c:forEach items="${listaFavoritosUsuario}" var="elemento">
                    		<tr>
	                            <td>${elemento.nombreElemento}</td>
	                            <td>${elemento.descripcion}</td>
	                            <td class="d-flex align-items-center gap-3">
	                                <button class="btn btn-primary boton-morado verificar-btn" onclick="location.href='ReservaController?ruta=verificarDisponibilidad&idElemento=${elemento.idElemento}'">Verificar Disponibilidad</button>
	                                <button class="btn btn-primary boton-morado verificar-btn" onclick="location.href='FavoritoController?ruta=eliminarFavorito&idElemento=${elemento.idElemento}'">Eliminar Favorito</button>
	                            </td>

	                        </tr>
                    	</c:forEach>
                    </tbody>
                </table>
            </section>
            <!-- Tus Reservaciones -->
            <section class="shadow p-4 mb-5 bg-white rounded">
                <h2 class="subtitulo">Tus reservaciones</h2>
                <table class="table table-hover mt-3">
                    <thead class="table-light">
                        <tr>
                            <th>Elemento</th>
                            <th>Descripción</th>
                            <th>Fecha</th>
                            <th>Hora</th>
                        </tr>
                    </thead>
                    <tbody id="reservaciones-body">
                        <!-- Reservas dinámicas -->
                    	<c:forEach items="${listaReservasUsuario}" var="reserva">
	                        <tr>
	                            <td>${reserva.elemento.nombreElemento}</td>
	                            <td>${reserva.elemento.descripcion}</td>
	                            <td>${reserva.fechaReserva}</td>
	                            <td>${reserva.hora.horaInicio} - ${reserva.hora.horaFin}</td>
	                        </tr>
	                    </c:forEach>
                    </tbody>
                </table>
            </section>

            <!-- Reservar Ahora -->
            <section class="shadow p-4 mb-5 bg-white rounded">
                <h2 class="subtitulo">Reserva ahora</h2>
                <p class="fw-bold">Disponibles:</p>
                              
                <table class="table table-hover">
                    <thead class="table-light">
                        <tr>
                            <th>Elemento</th>
                            <th>Descripción</th>
                            <th>Acciones</th>
                        </tr>
                    </thead>
                    <tbody>
                        <!-- Elementos dinámicos agregados -->
                        <c:forEach items="${listaElementosUsuario}" var="elemento" varStatus="status">
	                        <tr>
	                            <td>${elemento.nombreElemento}</td>
	                            <td>${elemento.descripcion}</td>
	                            <td class="d-flex align-items-center gap-3">
	                            	<button class="btn btn-primary boton-morado verificar-btn" onclick="location.href='ReservaController?ruta=verificarDisponibilidad&idElemento=${elemento.idElemento}'">Verificar Disponibilidad</button>
                                	<c:choose>
                                		<c:when test="${listaIdFavoritosUsuario.contains(elemento.idElemento)}">
<!--                                 			<button class="btn btn-primary boton-morado verificar-btn" disabled>Agregar Favorito</button> -->
                                		</c:when >
                                		<c:otherwise >
                                			<button class="btn btn-primary boton-morado verificar-btn" onclick="location.href='FavoritoController?ruta=agregarFavorito&idElemento=${elemento.idElemento}'">Agregar Favorito</button>
                                		</c:otherwise>
                                	</c:choose>
	                            </td>
	                        </tr>
	                    </c:forEach>
                    </tbody>
                </table>
            </section>

            
        </main>
    </div>
    <!-- Fondo adicional al final -->
    <div class="bg-primario py-4"></div>
    <!-- FontAwesome for Icons -->
    <script src="https://kit.fontawesome.com/a076d05399.js" crossorigin="anonymous"></script>
    <script src="JavaScript/homeUser.js"></script>
    <!-- Bootstrap JS -->
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
