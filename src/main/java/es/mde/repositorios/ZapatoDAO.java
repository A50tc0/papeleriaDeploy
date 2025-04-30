package es.mde.repositorios;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import es.mde.entidades.ZapatoConId;

@RepositoryRestResource(path = "zapatos", itemResourceRel = "zapato", collectionResourceRel = "zapatos")
public interface ZapatoDAO extends JpaRepository<ZapatoConId, Long> {

}

