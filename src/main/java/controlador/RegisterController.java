package controlador;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import modelo.dao.IngenieroDAO;
import modelo.entidades.Ingeniero;

import java.io.IOException;

@WebServlet("/RegisterController")
public class RegisterController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private IngenieroDAO ingenieroDAO;
       
    public RegisterController() {
        super();
        ingenieroDAO = new IngenieroDAO();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException {
		try {
			this.ruteador(request, response);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException{
		try {
			this.ruteador(request, response);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void ruteador(HttpServletRequest request, HttpServletResponse response) throws IOException  {
		String ruta = (request.getParameter("ruta") == null) ? "registrarse" : request.getParameter("ruta");
		
		switch(ruta) {
			case "registrarse": this.registrarse(request, response); break;
			case "guardar": this.guardar(request, response); break;
		}
	}
	
	private void registrarse(HttpServletRequest request, HttpServletResponse response) throws IOException {
		response.sendRedirect("JSP/Register.jsp");
	}
	
	private void guardar(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String nombre = request.getParameter("nombre");
		String correo = request.getParameter("email");
		String contraseña = request.getParameter("password");
		
		Ingeniero ingeniero = new Ingeniero();
		ingeniero.setNombreUser(nombre);
		ingeniero.setCorreoUser(correo);
		ingeniero.setContraseñaUser(contraseña);
		
		ingenieroDAO.crear(ingeniero);
		
		response.sendRedirect("LoginController?ruta=solicitarIngreso");
	}
}
