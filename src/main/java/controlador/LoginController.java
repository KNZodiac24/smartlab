package controlador;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import modelo.dao.ElementoDAO;
import modelo.dao.FavoritoDAO;
import modelo.dao.ReservaDAO;
import modelo.dao.UsuarioDAO;
import modelo.entidades.Administrador;
import modelo.entidades.Elemento;
import modelo.entidades.Reserva;
import modelo.entidades.Usuario;
import modelo.implementacion.jpa.ElementoDaoJPA;

import java.io.IOException;
import java.util.List;

@WebServlet("/LoginController")
public class LoginController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private UsuarioDAO usuarioDAO;
	private ReservaDAO reservaDAO;
	private ElementoDAO elementoDAO;
    private FavoritoDAO favoritoDAO;
	
    public LoginController() {
    	super();
    	usuarioDAO = new UsuarioDAO();
		reservaDAO = new ReservaDAO();
    	elementoDAO = new ElementoDaoJPA();
    	favoritoDAO = new FavoritoDAO();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.ruteador(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.ruteador(request, response);
	}

	private void ruteador(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		String ruta = (request.getParameter("ruta") == null) ? "solicitarIngreso" : request.getParameter("ruta");
		
		switch(ruta) {
			case "solicitarIngreso": this.solicitarIngreso(request, response); break;
			case "ingresar": this.ingresar(request, response); break;
			case "cerrarSesion": this.cerrarSesion(request, response); break;
		}
	}
	
	private void solicitarIngreso(HttpServletRequest request, HttpServletResponse response) throws IOException {
		response.sendRedirect("JSP/Login.jsp");
	}
	
	private void ingresar(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		Usuario usuario = (Usuario) request.getSession(false).getAttribute("usuario");
		
		if(usuario == null) {
			
			String correo = request.getParameter("email");
			String contraseña = request.getParameter("password");
			usuario = usuarioDAO.autenticar(correo, contraseña);
			
			if(usuario != null) {
	
				HttpSession session = request.getSession();
	            session.setAttribute("usuario", usuario);
	            
				if(!(usuario instanceof Administrador)) {		
					List<Reserva> listaReservasUsuario = reservaDAO.cargarReservas(usuario.getIdUser());
					List<Elemento> listaElementosUsuario = elementoDAO.cargarListaElementos();
					List<Elemento> listaFavoritosUsuario = favoritoDAO.cargarFavoritos(usuario.getIdUser());
					List<Integer> listaIdFavoritosUsuario = favoritoDAO.cargarIdFavoritos(usuario.getIdUser());
					
					request.setAttribute("listaReservasUsuario", listaReservasUsuario);
					request.setAttribute("listaElementosUsuario", listaElementosUsuario);
					request.setAttribute("listaFavoritosUsuario", listaFavoritosUsuario);
					request.setAttribute("listaIdFavoritosUsuario", listaIdFavoritosUsuario);
					request.getServletContext().getRequestDispatcher("/JSP/HomeUser.jsp").forward(request, response);
				}else{
					List<Elemento> listaElementosAdmin = elementoDAO.cargarListaElementos();
					
					request.setAttribute("listaElementosAdmin", listaElementosAdmin);
					request.getServletContext().getRequestDispatcher("/JSP/HomeAdmin.jsp").forward(request, response);
				}
			}else {
				response.sendRedirect("LoginController?ruta=solicitarIngreso");
			}
			
		}else {
			if(!(usuario instanceof Administrador)) {				
				List<Reserva> listaReservasUsuario = reservaDAO.cargarReservas(usuario.getIdUser());
				List<Elemento> listaElementosUsuario = elementoDAO.cargarListaElementos();
				List<Elemento> listaFavoritosUsuario = favoritoDAO.cargarFavoritos(usuario.getIdUser());
				List<Integer> listaIdFavoritosUsuario = favoritoDAO.cargarIdFavoritos(usuario.getIdUser());
				
				request.setAttribute("listaReservasUsuario", listaReservasUsuario);
				request.setAttribute("listaElementosUsuario", listaElementosUsuario);
				request.setAttribute("listaFavoritosUsuario", listaFavoritosUsuario);
				request.setAttribute("listaIdFavoritosUsuario", listaIdFavoritosUsuario);
				request.getServletContext().getRequestDispatcher("/JSP/HomeUser.jsp").forward(request, response);
			}else{
				List<Elemento> listaElementosAdmin = elementoDAO.cargarListaElementos();
				
				request.setAttribute("listaElementosAdmin", listaElementosAdmin);
				request.getServletContext().getRequestDispatcher("/JSP/HomeAdmin.jsp").forward(request, response);
			}
		}
	}
	
	private void cerrarSesion(HttpServletRequest request, HttpServletResponse response) throws IOException {
		HttpSession sesion = request.getSession(false);
        if (sesion != null) {
            sesion.invalidate();
        }
        response.sendRedirect("LoginController?ruta=solicitarIngreso");
	}
}
