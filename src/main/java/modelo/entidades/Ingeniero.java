package modelo.entidades;

import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

@Entity
@DiscriminatorValue("0")
public class Ingeniero extends Usuario{

	private static final long serialVersionUID = 1L;
	@Column(nullable=false)
	private String nombreUser;
	
	public Ingeniero() {}

	public String getNombreUser() {
		return nombreUser;
	}

	public void setNombreUser(String nombreUser) {
		this.nombreUser = nombreUser;
	}
}
