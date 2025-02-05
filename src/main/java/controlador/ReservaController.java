package controlador;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import modelo.dao.ElementoDAO;
import modelo.dao.HoraDAO;
import modelo.dao.ReservaDAO;
import modelo.dao.UsuarioDAO;
import modelo.entidades.Elemento;
import modelo.entidades.Hora;
import modelo.entidades.Ingeniero;
import modelo.entidades.Reserva;
import modelo.entidades.Usuario;
import modelo.implementacion.jpa.ElementoDaoJPA;

import java.io.IOException;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/ReservaController")
public class ReservaController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ElementoDAO elementoDAO;
	private HoraDAO horaDAO;
	private ReservaDAO reservaDAO;
	private UsuarioDAO usuarioDAO;

    public ReservaController() {
        super();
		elementoDAO = new ElementoDaoJPA();
		horaDAO = new HoraDAO();
		reservaDAO = new ReservaDAO();
		usuarioDAO = new UsuarioDAO();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.ruteador(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.ruteador(request, response);
	}

	private void ruteador(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		String ruta = (request.getParameter("ruta") == null) ? "verificarDisponibilidad" : request.getParameter("ruta");
		
		switch(ruta) {
			case "verificarDisponibilidad": this.verificarDisponibilidad(request, response); break;
			case "solicitarReserva": this.solicitarReserva(request, response); break;
		}
	}
	
	private void verificarDisponibilidad(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int idElemento = Integer.parseInt(request.getParameter("idElemento"));
		Date fechaReserva = null;
		if(request.getParameter("fechaReserva") != null) fechaReserva = Date.valueOf(request.getParameter("fechaReserva"));
		
		Elemento elemento = elementoDAO.cargarElemento(idElemento);
		List<Hora> listaHoras = horaDAO.cargarHoras();
		List<String> listaDisponibilidades = new ArrayList<>();
		if(fechaReserva == null) {
			listaDisponibilidades = reservaDAO.cargarDisponibilidades(idElemento, new Date(System.currentTimeMillis()));
		}else {
			listaDisponibilidades = reservaDAO.cargarDisponibilidades(idElemento, fechaReserva);
		}
		
		request.setAttribute("elemento", elemento);
		request.setAttribute("listaHoras", listaHoras);
		request.setAttribute("listaDisp", listaDisponibilidades);
		request.setAttribute("fechaReserva", fechaReserva);
		getServletContext().getRequestDispatcher("/JSP/Reservar.jsp").forward(request, response);
	}
	
	private void solicitarReserva(HttpServletRequest request, HttpServletResponse response) throws IOException {
		Usuario usuario = (Usuario) request.getSession(false).getAttribute("usuario");
		Ingeniero ingeniero = (Ingeniero) usuarioDAO.getUserById(usuario.getIdUser());
		Elemento elemento = elementoDAO.cargarElemento(Integer.parseInt(request.getParameter("idElemento")));
		Hora hora = horaDAO.getHoraById(Integer.parseInt(request.getParameter("idHora")));
		Date fechaReserva = Date.valueOf(request.getParameter("fechaReserva"));
		
		Reserva reserva = new Reserva();
		reserva.setIngeniero(ingeniero);
		reserva.setElemento(elemento);
		reserva.setHora(hora);
		reserva.setFechaReserva(fechaReserva);
		
		reservaDAO.crearReserva(reserva);

		response.sendRedirect("LoginController?ruta=ingresar");
	}
	
}
