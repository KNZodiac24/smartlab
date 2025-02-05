<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="modelo.entidades.Usuario" %>
<%@ page import="modelo.entidades.Administrador" %>
<%@ page session="true" %>
<%
    Usuario usuario = (Usuario) session.getAttribute("usuario");
    if (usuario == null) {
        response.sendRedirect("JSP/Login.jsp");
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
    <title>Reserva - Gestor de Aulas</title>
    <!-- Bootstrap CSS -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
    <link rel="stylesheet" href="JSP/style/reservar.css">
</head>
<body>
    <div class="container py-5">
        <h2 class="titulo">Reserva</h2>
        <div class="row g-4">
            <!-- InformaciÃ³n del Elemento -->
            <div class="col-lg-4">
                <div class="info-elemento">
                    <h5>Elemento:</h5>
                    <input type="text" class="form-control mb-3" id="elemento" value="${elemento.nombreElemento}" disabled>
                    <h5>Descripción:</h5>
                    <textarea class="form-control" id="descripcion" rows="6" style="resize: none;" disabled>${elemento.descripcion}</textarea>
                </div>
            </div>
            <!-- SelecciÃ³n de Fecha -->
            <div class="col-lg-4">
                <div class="calendario">
                    <h5>Seleccionar fecha</h5>
                    <input type="date" id="fecha-seleccionada" class="form-control" onchange="location.href='ReservaController?ruta=verificarDisponibilidad&idElemento=${elemento.idElemento}&fechaReserva='+fechaInput.value">
                </div>
            </div>
            <!-- Horas Disponibles -->
            <div class="col-lg-4">
                <div class="horas-disponibles">
                    <h5>Horas disponibles:</h5>
                    <ul id="lista-horas">
                        <c:forEach var="hora" items="${listaHoras}" varStatus="status">
                        	<c:set var="disp" value="${listaDisp.get(status.index)}"/>
                        	<c:choose>
                        		<c:when test="${disp == 'Disponible'}">
    								<li class="disponible">${hora.horaInicio} - ${hora.horaFin}</li>
    							</c:when>
    							<c:otherwise>
    								<li class="reservado">${hora.horaInicio} - ${hora.horaFin}</li>
    							</c:otherwise>
    						</c:choose>
						</c:forEach>
                    </ul>
                </div>
            </div>
        </div>
        <!-- Botones -->
        <div class="botones-acciones mt-4">
            <button class="btn btn-cancelar" onclick="location.href='LoginController?ruta=ingresar'">Cancelar</button>
            <button class="btn btn-reservar" style="display: none;" onclick="location.href='ReservaController?ruta=solicitarReserva&idElemento=${elemento.idElemento}&idHora='+horaSeleccionada+'&fechaReserva='+fechaSeleccionada">Reservar</button>
        </div>        
    </div>

    <!-- Modal de ConfirmaciÃ³n -->
    <div class="modal fade" id="confirmacionModal" tabindex="-1" aria-labelledby="modalLabel" aria-hidden="true">
        <div class="modal-dialog modal-dialog-centered">
            <div class="modal-content">
                <div class="modal-header bg-secundario text-white">
                    <h5 class="modal-title" id="modalLabel">Confirmar Reserva</h5>
                    <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Cerrar"></button>
                </div>
                <div class="modal-body">
                    <p><strong>Elemento:</strong> <span id="modal-elemento"></span></p>
                    <p><strong>Descripción:</strong> <span id="modal-descripcion"></span></p>
                    <p><strong>Fecha:</strong> <span id="modal-fecha"></span></p>
                    <p><strong>Hora:</strong> <span id="modal-hora"></span></p>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Cancelar</button>
                    <button type="button" id="btn-confirmar" class="btn btn-primary">Aceptar</button>
                </div>
            </div>
        </div>
    </div>

	<script>
	    const fechaInput = document.getElementById("fecha-seleccionada");
	    const hoySinFormatear = new Date();
	    const options = {
	        timeZone: 'America/Guayaquil',
	        year: 'numeric',
	        month: '2-digit',
	        day: '2-digit'
	    };
	    const hoy = new Intl.DateTimeFormat('es-EC', options).formatToParts(hoySinFormatear);
	    const anio = hoy[4].value;
	    const mes = hoy[2].value;
	    const dia = hoy[0].value;
	    const hoyISO = anio+'-'+mes+'-'+dia;
	    
	    var fecha = '<%= request.getAttribute("fechaReserva") %>';
	    if(fecha == 'null'){
	    	fechaInput.min = hoyISO;
		    fechaInput.value = hoyISO;
	    }else{
	    	fechaInput.min = hoyISO;
	    	fechaInput.value = fecha;
	    }
	    
    </script>
    <script src="JSP/JavaScript/reservar.js"></script>
    
    <!-- Bootstrap JS -->
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
