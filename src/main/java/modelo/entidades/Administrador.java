package modelo.entidades;

import java.util.List;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;

@Entity
@DiscriminatorValue("1")
public class Administrador extends Usuario {

	private static final long serialVersionUID = 1L;
	
	@ManyToMany @JoinColumn
	private List<Elemento> elementos;
	
	public Administrador() {
		super();
	}
	
}
