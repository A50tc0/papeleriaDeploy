package es.mde.repositorios;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;

import es.mde.entidades.Cliente;
/**
 * Representa la interfaz que expone para la API los clientes
 *
 */
@RepositoryRestResource(path = "clientes", itemResourceRel = "cliente", collectionResourceRel = "clientes")
public interface ClienteDAO extends JpaRepository<Cliente, Long>, ClienteDAOCustom {	
	
	/**
	 * Devuelve una lista de clientes que contiene ese texto en su Nombre
	 * 
	 * @param txt Texto que esta incluido en el Nombre del Cliente buscado
	 * @return Devuelve una lista de clientes que contiene ese texto en su Nombre
	 */
	@RestResource(path = "nombre")
	List<Cliente> findByNombreIgnoreCaseContaining(@Param("txt")String txt);

	/**
	 * Devuelve una lista de clientes que contiene ese texto en su correo
	 * 
	 * @param txt Texto que esta incluido en el correo del Cliente buscado
	 * @return Devuelve una lista de clientes que contiene ese texto en su correo
	 */
	@RestResource(path = "correo")
	List<Cliente> findByCorreoContaining(@Param("txt") String txt);

	/**
	 * Elimina un cliente. No está expuesto
	 * 
	 * @param id Id del cliente a eliminar
	 */
	@RestResource(exported = false)
	void deleteById(Long id);

	/**
	 * Elimina un cliente. No está expuesto
	 * 
	 * @param cliente Cliente a eliminar
	 */
	@RestResource(exported = false)
	void delete(Cliente cliente);

}
