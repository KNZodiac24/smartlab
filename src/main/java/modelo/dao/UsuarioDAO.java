package modelo.dao;

import java.util.List;

import jakarta.persistence.EntityManager;
import jakarta.persistence.Persistence;
import jakarta.persistence.Query;
import modelo.entidades.Usuario;

public class UsuarioDAO {
		
	private EntityManager em;
	
	private List<Usuario> getUsuarios() {	
		em = Persistence.createEntityManagerFactory("SmartLab").createEntityManager();

		String jpql = "SELECT u FROM Usuario u";
		
		Query query = em.createQuery(jpql, Usuario.class);
		
		List<Usuario> usuarios = (List<Usuario>) query.getResultList();
		
		return usuarios;
	}

	
	public Usuario autenticar(String correo, String contraseña) {
	    List<Usuario> usuarios = getUsuarios();

	    for (Usuario usr : usuarios) {
	        if (usr.getCorreoUser().equals(correo) && usr.getContraseñaUser().equals(contraseña)) {
	            return usr;
	        }
	    }

	    return null;
	}
	
	public Usuario getUserById(int idUser) {
		em = Persistence.createEntityManagerFactory("SmartLab").createEntityManager();

		return em.find(Usuario.class, idUser);
	}
}
