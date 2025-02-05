package modelo.entidades;

import java.io.Serializable;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;

@Entity
public class Favorito implements Serializable {

	private static final long serialVersionUID = 1L;
	@Id
	@OneToOne
	@JoinColumn(name="idUser")
	private Ingeniero ingeniero;
	@Id
	@ManyToOne
	@JoinColumn //(name="idElemento")
	private Elemento elemento;
	
	public Favorito() {}

	public Ingeniero getIngeniero() {
		return ingeniero;
	}

	public void setIngeniero(Ingeniero ingeniero) {
		this.ingeniero = ingeniero;
	}

	public Elemento getElemento() {
		return elemento;
	}

	public void setElemento(Elemento elemento) {
		this.elemento = elemento;
	}
}
