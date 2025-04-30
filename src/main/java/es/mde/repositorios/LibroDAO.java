package es.mde.repositorios;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
//import org.springframework.stereotype.Repository;
import org.springframework.data.rest.core.annotation.RestResource;

import es.mde.entidades.Libro;

@RepositoryRestResource(path="libros",itemResourceRel="libro",collectionResourceRel="libros")
public interface LibroDAO extends JpaRepository<Libro, Long>{

	@RestResource(path = "autor-nombre")
	List<Libro> findByAutorIgnoreCaseContainingOrNombreIgnoreCaseContaining(@Param("autor-nombre") String autor,
			@Param("autor-nombre") String nombre);

}
