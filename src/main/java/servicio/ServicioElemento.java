package servicio;

import java.util.List;

import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import modelo.dao.ElementoDAO;
import modelo.entidades.Elemento;
import modelo.implementacion.jpa.ElementoDaoJPA;

@Path("/elemento")
public class ServicioElemento {
	
	private ElementoDAO elementoDAO;

	public ServicioElemento() {
		this.elementoDAO = new ElementoDaoJPA();
	}
	
	@Path("/listar")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<Elemento> cargarListaElementos(){
		return this.elementoDAO.cargarListaElementos();
	}
	
	@Path("/{idElemento}")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Elemento cargarElemento(@PathParam("idElemento") int idElemento) {
		return elementoDAO.cargarElemento(idElemento);
	}
	
	@Path("/add")
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public void guardarElemento(Elemento elemento) {
		this.elementoDAO.guardarElemento(elemento);
	}
	
	@Path("/edit")
	@PUT
	@Consumes(MediaType.APPLICATION_JSON)
	public void actualizarElemento(Elemento elemento) {
		this.elementoDAO.actualizarElemento(elemento);
	}
	
	@Path("/delete/{idElemento}")
	@DELETE
	public void eliminarElemento(@PathParam("idElemento") int idElemento) {
		this.elementoDAO.eliminarElemento(idElemento);
	}
}
