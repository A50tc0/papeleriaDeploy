package es.mde.entidades;

import java.util.Collection;

import es.mde.externas.Persona;
import es.mde.externas.Zapato;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "PERSONAS")
public class PersonaConId extends Persona {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(unique = true)
	Long id;

	public PersonaConId() {}
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@Override
	@OneToMany(targetEntity = ZapatoConId.class)
	public Collection<Zapato> getZapatos() {
		return super.getZapatos();
	}
	
	// Establece la relacion en los dos sentidos
	public void addZapatoConId(ZapatoConId zapato) {
		super.getZapatos().add(zapato);
		zapato.setPersona(this);
	}
	
}
