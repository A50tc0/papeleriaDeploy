package es.mde.repositorios;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;


import es.mde.entidades.Producto;

@RepositoryRestResource(path="productos",itemResourceRel="producto",collectionResourceRel="productos")
//public interface ProductoDAO extends JpaRepository<Producto, Long> {
//
//}
public interface ProductoDAO extends JpaRepository<Producto, Long>, ProductoDAOCustom {

	List<Producto> findByNombreIgnoreCaseContaining(String txt);

}

