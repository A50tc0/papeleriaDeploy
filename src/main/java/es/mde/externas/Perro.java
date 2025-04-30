package es.mde.externas;

public class Perro {
	private Long id;
	private String nombre;
	private String size;

	public Perro() {
	}

	public Perro(String nombre, String size) {
		this.nombre = nombre;
		this.size = size;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getSize() {
		return size;
	}

	public void setSize(String size) {
		this.size = size;
	}

}
