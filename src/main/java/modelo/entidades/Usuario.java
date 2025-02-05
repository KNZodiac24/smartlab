package modelo.entidades;

import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorColumn;
import jakarta.persistence.DiscriminatorType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name="isAdmin", discriminatorType=DiscriminatorType.INTEGER)
public abstract class Usuario implements Serializable {
	
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column
	private Integer idUser;
	@Column(unique = true, nullable=false)
	private String correoUser;
	@Column(nullable=false)
	private String contraseñaUser;
	
	public Usuario() {}
	
	public Integer getIdUser() {
		return idUser;
	}

	public void setIdUser(Integer idUser) {
		this.idUser = idUser;
	}

	public String getCorreoUser() {
		return correoUser;
	}

	public void setCorreoUser(String correoUser) {
		this.correoUser = correoUser;
	}

	public String getContraseñaUser() {
		return contraseñaUser;
	}

	public void setContraseñaUser(String contraseñaUser) {
		this.contraseñaUser = contraseñaUser;
	}
}
