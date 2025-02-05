package controlador;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import modelo.dao.ElementoDAO;
import modelo.dao.FavoritoDAO;
import modelo.dao.UsuarioDAO;
import modelo.entidades.Elemento;
import modelo.entidades.Favorito;
import modelo.entidades.Ingeniero;
import modelo.entidades.Usuario;
import modelo.implementacion.jpa.ElementoDaoJPA;

import java.io.IOException;

@WebServlet("/FavoritoController")
public class FavoritoController extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private ElementoDAO elementoDAO;
    private FavoritoDAO favoritoDAO;
    private UsuarioDAO usuarioDAO;
    
	public FavoritoController() {
		super();
    	elementoDAO = new ElementoDaoJPA();
    	favoritoDAO = new FavoritoDAO();
    	usuarioDAO = new UsuarioDAO();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	this.ruteador(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.ruteador(request, response);
    }

    private void ruteador(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String ruta = (request.getParameter("ruta") == null) ? "agregarFavorito" : request.getParameter("ruta");

        switch (ruta) {
            case "agregarFavorito": this.agregarFavorito(request, response); break;
            case "eliminarFavorito": this.eliminarFavorito(request, response); break;
        }
    }
    
    private void agregarFavorito(HttpServletRequest request, HttpServletResponse response) throws IOException {
    	Usuario usuario = (Usuario) request.getSession(false).getAttribute("usuario");
    	Ingeniero ingeniero = (Ingeniero) usuarioDAO.getUserById(usuario.getIdUser());
        Elemento elemento = elementoDAO.cargarElemento(Integer.parseInt(request.getParameter("idElemento")));
        
        Favorito favorito = new Favorito();
        favorito.setIngeniero(ingeniero);
        favorito.setElemento(elemento);

        favoritoDAO.agregarFavorito(favorito);
        
        response.sendRedirect("LoginController?ruta=ingresar");
    }

    private void eliminarFavorito(HttpServletRequest request, HttpServletResponse response)throws IOException {
    	Usuario usuario = (Usuario) request.getSession(false).getAttribute("usuario");
    	int idUser = usuario.getIdUser();
        int idElemento = Integer.parseInt(request.getParameter("idElemento"));

        favoritoDAO.eliminarFavorito(idUser, idElemento);
        
        response.sendRedirect("LoginController?ruta=ingresar");
		
	}
    
}
