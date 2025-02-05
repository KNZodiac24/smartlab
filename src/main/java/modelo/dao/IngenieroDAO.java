package modelo.dao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.Persistence;
import modelo.entidades.Ingeniero;

public class IngenieroDAO {
	
	private EntityManager em;
	
	public boolean crear(Ingeniero ingeniero) {
		em = Persistence.createEntityManagerFactory("SmartLab").createEntityManager();

		em.getTransaction().begin();
		try {
			em.persist(ingeniero);
			em.getTransaction().commit();
			return true;
		} catch(Exception e) {
			if(em.getTransaction().isActive()) {
				System.out.println("ERROR: Crear ingeniero");
				em.getTransaction().rollback();
				return false;
			}
		}
		return false;
	}
	
}
