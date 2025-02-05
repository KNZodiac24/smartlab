package modelo.dao;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.EntityManager;
import jakarta.persistence.Persistence;
import jakarta.persistence.Query;
import modelo.entidades.Reserva;

public class ReservaDAO {
	
	private EntityManager em;
		
	public boolean crearReserva(Reserva reserva) {	
		em = Persistence.createEntityManagerFactory("SmartLab").createEntityManager();

		em.getTransaction().begin();
		try {
			em.persist(reserva);
			em.getTransaction().commit();
			return true;
		} catch(Exception e) {
			if(em.getTransaction().isActive()) {
				System.out.println("ERROR: Crear reserva");
				em.getTransaction().rollback();
				return false;
			}
		}
		return false;
	}

	public List<String> cargarDisponibilidades(int idElemento, Date fechaReserva) {		
		em = Persistence.createEntityManagerFactory("SmartLab").createEntityManager();

		String jpql = "SELECT r FROM Reserva r WHERE r.elemento.idElemento = :idElemento AND r.fechaReserva = :fechaReserva";
		
		Query query = em.createQuery(jpql, String.class);
		query.setParameter("idElemento", idElemento);
		query.setParameter("fechaReserva", fechaReserva);
		
		List<Reserva> listaReservas = (List<Reserva>) query.getResultList();
		
		List<String> listaDisponibilidades = new ArrayList<>();
		for(short i = 0; i < 26; i++) listaDisponibilidades.add("Disponible");
		
		for(Reserva reserva : listaReservas) listaDisponibilidades.set(reserva.getHora().getIdHora()-1, "Reservado");
		
		return listaDisponibilidades;
	}

	public List<Reserva> cargarReservas(int idUser) {		
		em = Persistence.createEntityManagerFactory("SmartLab").createEntityManager();

		String jpql = "SELECT r FROM Reserva r WHERE r.ingeniero.idUser= :idUser";
		
		Query query = em.createQuery(jpql, Reserva.class);
		query.setParameter("idUser", idUser);
		
		List<Reserva> listaReservasUsuario = (List<Reserva>) query.getResultList();
		
		return listaReservasUsuario;
	}
}
