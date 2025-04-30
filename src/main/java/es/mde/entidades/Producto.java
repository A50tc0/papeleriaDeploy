package es.mde.entidades;

import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorColumn;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

/**
 * Representa un Producto
 *
 */
@Entity
@Table(name = "PRODUCTOS")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "TIPO")
@DiscriminatorValue("PRODUCTO")

public class Producto {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(unique = true)
	private Long id;
	private String nombre;
	boolean pagado = false;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "CLIENTE")
	private Cliente cliente;

	/**
	 * Crea un Producto
	 */
	public Producto() {
	}

	/**
	 * Crea un Producto
	 * 
	 * @param nombre Nombre del Producto
	 */
	public Producto(String nombre) {
		this.nombre = nombre;
	}

	/**
	 * Crea un Producto
	 * 
	 * @param nombre  Nombre del Producto
	 * @param cliente Cliente del Producto
	 */
	public Producto(String nombre, Cliente cliente) {
		this.nombre = nombre;
		this.cliente = cliente;
	}

	/**
	 * Devuelve un Id de un Producto
	 * 
	 * @return Devuelve el Id de un Producto
	 */
	public Long getId() {
		return id;
	}

	/**
	 * Guarda el Id de un Producto
	 * 
	 * @param id Id del Producto
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * Devuelve un nombre de un Producto
	 * 
	 * @return Devuelve el nombre de un Producto
	 */
	public String getNombre() {
		return nombre;
	}

	/**
	 * Guarda el nombre de un Producto
	 * 
	 * @param nombre Nombre del Producto
	 */
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	/**
	 * Devuelve el estado de pago de un Producto
	 * 
	 * @return Devuelve estado de pago de un Producto
	 */
	public boolean isPagado() {
		return pagado;
	}

	/**
	 * Guarda el estado de pago de un Producto
	 * 
	 * @param pagado Estado de pago de un Producto
	 */
	public void setPagado(boolean pagado) {
		this.pagado = pagado;
	}

	/**
	 * Devuelve el cliente de un Producto
	 * 
	 * @return Devuelve el cliente de un Producto
	 */
	public Cliente getCliente() {
		return cliente;
	}

	/**
	 * Guarda el cliente de un Producto
	 * 
	 * @param cliente Cliente de un Producto
	 */
	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	/**
	 * Genera el método toString del Producto
	 * 
	 */
	@Override
	public String toString() {
		return getNombre() + " y " + getCliente().getCorreo();

	}

}

