package modelo.dao;

import java.util.List;

import modelo.entidades.Elemento;

public interface ElementoDAO {
		
	public List<Elemento> cargarListaElementos();

	public Elemento cargarElemento(int idElemento);
	
	public boolean guardarElemento(Elemento elemento);

	public boolean actualizarElemento(Elemento elemento);

	public boolean eliminarElemento(int idElemento);

}
