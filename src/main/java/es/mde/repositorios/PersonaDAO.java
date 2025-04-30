package es.mde.repositorios;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import es.mde.entidades.PersonaConId;


@RepositoryRestResource(path = "personas", itemResourceRel = "persona", collectionResourceRel = "personas")
public interface PersonaDAO extends JpaRepository<PersonaConId, Long> {


}

