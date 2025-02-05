package modelo.entidades;

import java.io.Serializable;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;

@Entity
public class Elemento implements Serializable {

	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column
	private Integer idElemento;
	@Column(nullable=false)
	private String nombreElemento;
	@Column(nullable=false)
	private String descripcion;
	
	@OneToMany(cascade=CascadeType.REMOVE, mappedBy="elemento")
	private List<Favorito> favoritos;
	@OneToMany(cascade=CascadeType.REMOVE, mappedBy="elemento")
	private List<Reserva> reservas;
	@ManyToMany(mappedBy="elementos") @JoinColumn
	private List<Administrador> administradores;
	
	public Elemento() {}

	public Integer getIdElemento() {
		return idElemento;
	}

	public void setIdElemento(Integer idElemento) {
		this.idElemento = idElemento;
	}

	public String getNombreElemento() {
		return nombreElemento;
	}

	public void setNombreElemento(String nombreElemento) {
		this.nombreElemento = nombreElemento;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

}
