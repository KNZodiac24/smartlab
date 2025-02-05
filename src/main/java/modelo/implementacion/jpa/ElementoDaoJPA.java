package modelo.implementacion.jpa;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.EntityManager;
import jakarta.persistence.Persistence;
import jakarta.persistence.Query;
import modelo.dao.ElementoDAO;
import modelo.entidades.Elemento;

public class ElementoDaoJPA implements ElementoDAO{
	
	private EntityManager em;
	
	@Override
	public List<Elemento> cargarListaElementos() {
		em = Persistence.createEntityManagerFactory("SmartLab").createEntityManager();
		
		String jpql = "SELECT e FROM Elemento e";
		
		Query query = em.createQuery(jpql, Elemento.class);
		
		List<Elemento> listaElementos = new ArrayList<>();
		listaElementos = (List<Elemento>) query.getResultList();
		
		return listaElementos;
	}

	@Override
	public Elemento cargarElemento(int idElemento) {
		em = Persistence.createEntityManagerFactory("SmartLab").createEntityManager();

		return em.find(Elemento.class, idElemento);
	}
	
	@Override
	public boolean guardarElemento(Elemento elemento) {
		em = Persistence.createEntityManagerFactory("SmartLab").createEntityManager();

		em.getTransaction().begin();
		try {
			em.persist(elemento);
			em.getTransaction().commit();
			return true;
		} catch(Exception e) {
			if(em.getTransaction().isActive()) {
				System.out.println("ERROR: Guardar elemento");
				em.getTransaction().rollback();
				return false;
			}
		}
		return false;
	}

	@Override
	public boolean actualizarElemento(Elemento elemento) {
		em = Persistence.createEntityManagerFactory("SmartLab").createEntityManager();

		em.getTransaction().begin();
		try {
			em.merge(elemento);
			em.getTransaction().commit();
			return true;
		} catch(Exception e) {
			if(em.getTransaction().isActive()) {
				System.out.println("ERROR: Actualizar elemento");
				em.getTransaction().rollback();
				return false;
			}
		}
		return false;
	}
	
	@Override
	public boolean eliminarElemento(int idElemento)  {
		em = Persistence.createEntityManagerFactory("SmartLab").createEntityManager();

		Elemento elemento = em.find(Elemento.class, idElemento);
		
		em.getTransaction().begin();
		try {
			em.remove(elemento);
			em.getTransaction().commit();
			return true;
		} catch(Exception e) {
			if(em.getTransaction().isActive()) {
				System.out.println("ERROR: Eliminar elemento");
				em.getTransaction().rollback();
				return false;
			}
		}
		return false;
	}
}
