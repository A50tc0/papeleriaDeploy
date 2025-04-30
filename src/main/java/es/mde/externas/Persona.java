package es.mde.externas;

import java.util.ArrayList;
import java.util.Collection;

public class Persona {

	private String nombre;
	private Collection<Zapato> zapatos = new ArrayList<Zapato>();

	public Persona() {
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public Collection<Zapato> getZapatos() {
		return zapatos;
	}

	public void setZapatos(Collection<Zapato> zapatos) {
		this.zapatos = zapatos;
	}

}

