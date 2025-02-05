package modelo.dao;

import java.util.List;

import jakarta.persistence.EntityManager;
import jakarta.persistence.Persistence;
import jakarta.persistence.Query;
import modelo.entidades.Hora;

public class HoraDAO {
	
	private EntityManager em;
	
	public List<Hora> cargarHoras() {	
		em = Persistence.createEntityManagerFactory("SmartLab").createEntityManager();

		String jpql = "SELECT h FROM Hora h";
		
		Query query = em.createQuery(jpql, Hora.class);
		
		List<Hora> listaHoras = (List<Hora>) query.getResultList();
				
		return listaHoras;
	}
	
	public Hora getHoraById(int idHora) {
		em = Persistence.createEntityManagerFactory("SmartLab").createEntityManager();

		return em.find(Hora.class, idHora);
	}
}
