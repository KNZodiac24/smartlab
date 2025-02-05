package modelo.dao;

import java.util.List;

import jakarta.persistence.EntityManager;
import jakarta.persistence.Persistence;
import jakarta.persistence.Query;
import modelo.entidades.Elemento;
import modelo.entidades.Favorito;

public class FavoritoDAO {
	
	private EntityManager em;
	
    public List<Elemento> cargarFavoritos(int idUser) {
		em = Persistence.createEntityManagerFactory("SmartLab").createEntityManager();

        String jpql = "SELECT e FROM Elemento e WHERE e.idElemento IN (SELECT f.elemento.idElemento FROM Favorito f WHERE f.ingeniero.idUser = :idUser)";
        
        Query query = em.createQuery(jpql, Elemento.class);
        query.setParameter("idUser", idUser);
        
        List<Elemento> listaFavoritosUsuario = (List<Elemento>) query.getResultList();
        
        return listaFavoritosUsuario;
    }
    
    public List<Integer> cargarIdFavoritos(int idUser) {
		em = Persistence.createEntityManagerFactory("SmartLab").createEntityManager();

    	String jpql = "SELECT f.elemento.idElemento FROM Favorito f WHERE f.ingeniero.idUser = :idUser";
        
    	Query query = em.createQuery(jpql, Integer.class);
    	query.setParameter("idUser", idUser);
        
        List<Integer> listaIdFavoritosUsuario = (List<Integer>) query.getResultList();
        
        return listaIdFavoritosUsuario;
    }

    public boolean agregarFavorito(Favorito favorito) {
		em = Persistence.createEntityManagerFactory("SmartLab").createEntityManager();

    	em.getTransaction().begin();
		try {
			em.persist(favorito);
			em.getTransaction().commit();
			return true;
		} catch(Exception e) {
			if(em.getTransaction().isActive()) {
				System.out.println("ERROR: Agregar favorito");
				em.getTransaction().rollback();
				return false;
			}
		}
		return false;
    }

    public boolean eliminarFavorito(int idUser, int idElemento) {
		em = Persistence.createEntityManagerFactory("SmartLab").createEntityManager();

        String jpql = "SELECT f FROM Favorito f WHERE f.ingeniero.idUser= :idUser AND f.elemento.idElemento= :idElemento";
		
		Query query = em.createQuery(jpql);
		query.setParameter("idUser", idUser);
		query.setParameter("idElemento", idElemento);
		
		Favorito favorito = (Favorito) query.getSingleResult();
        
		em.getTransaction().begin();
		try {
			em.remove(favorito);
			em.getTransaction().commit();
			return true;
		} catch(Exception e) {
			if(em.getTransaction().isActive()) {
				System.out.println("ERROR: Eliminar favorito");
				em.getTransaction().rollback();
				return false;
			}
		}
		return false;
    }

}
