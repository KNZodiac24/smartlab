package controlador;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import modelo.dao.ElementoDAO;
import modelo.entidades.Elemento;
import modelo.implementacion.jpa.ElementoDaoJPA;

import java.io.IOException;

@WebServlet("/GestionController")
public class GestionController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ElementoDAO elementoDAO;
	
	public GestionController() {
		super();
		elementoDAO = new ElementoDaoJPA();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.ruteador(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.ruteador(request, response);
	}
	
	private void ruteador(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		String ruta = (request.getParameter("ruta") == null) ? "solicitarEdicion" : request.getParameter("ruta");
		
		switch(ruta) {
			case "solicitarEdicion": this.solicitarEdicion(request, response); break;
			case "editarElemento": this.editarElemento(request, response); break;
			case "solicitarAgregar": this.solicitarAgregar(request, response); break;
			case "agregarElemento": this.agregarElemento(request, response); break;
			case "solicitarEliminar": this.solicitarEliminar(request, response); break;
		}
	}

	private void solicitarEdicion(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int idElemento = Integer.parseInt(request.getParameter("idElemento"));
				
		Elemento elemento = elementoDAO.cargarElemento(idElemento);
		
		request.setAttribute("elemento", elemento);
		getServletContext().getRequestDispatcher("/JSP/Edit.jsp").forward(request, response);
	}

	private void editarElemento(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	    int idElemento = Integer.parseInt(request.getParameter("idElemento"));
	    String nombreElemento = request.getParameter("nombreElemento");
	    String descripcion = request.getParameter("descripcionElemento");

	    Elemento elemento = new Elemento();
	    elemento.setIdElemento(idElemento);
	    elemento.setNombreElemento(nombreElemento);
	    elemento.setDescripcion(descripcion);

	    elementoDAO.actualizarElemento(elemento);
	    
	    response.sendRedirect("LoginController?ruta=ingresar");
	}

	private void solicitarAgregar(HttpServletRequest request, HttpServletResponse response) throws IOException {
		response.sendRedirect("JSP/Add.jsp");
	}

	private void agregarElemento(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	    String nombreElemento = request.getParameter("nombre");
	    String descripcion = request.getParameter("descripcion");

	    Elemento elemento = new Elemento();
	    elemento.setNombreElemento(nombreElemento);
	    elemento.setDescripcion(descripcion);

	    elementoDAO.guardarElemento(elemento);

	    response.sendRedirect("LoginController?ruta=ingresar");
	}

	private void solicitarEliminar(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	    int idElemento = Integer.parseInt(request.getParameter("idElemento"));

	    elementoDAO.eliminarElemento(idElemento);

	    response.sendRedirect("LoginController?ruta=ingresar");
	}

}
