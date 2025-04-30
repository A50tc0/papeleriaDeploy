package es.mde.repositorios;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import es.mde.externas.Perro;

@RepositoryRestResource(path = "perros", itemResourceRel = "perro", collectionResourceRel = "perros")
public interface PerroDAO extends JpaRepository<Perro, Long> {

}
