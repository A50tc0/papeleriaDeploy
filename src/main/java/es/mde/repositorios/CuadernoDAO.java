package es.mde.repositorios;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
//import org.springframework.stereotype.Repository;

import es.mde.entidades.Cuaderno;

@RepositoryRestResource(path="cuadernos",itemResourceRel="cuaderno",collectionResourceRel="cuadernos")
public interface CuadernoDAO extends JpaRepository<Cuaderno, Long>{

}
