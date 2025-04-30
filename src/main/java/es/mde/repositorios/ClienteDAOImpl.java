package es.mde.repositorios;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.transaction.annotation.Transactional;

import es.mde.entidades.Producto;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

/**
 * Representa la clase implementada con los metodos personalizados de los clientes 
 */
@Transactional(readOnly = true)
public class ClienteDAOImpl implements ClienteDAOCustom {

	private ApplicationContext applicationContext;
	
	@Autowired
	public void setApplicationContext(ApplicationContext applicationContext) {
		this.applicationContext = applicationContext;
	}
	
	private ClienteDAO getClienteDAO() {
		return applicationContext.getBean(ClienteDAO.class);
	}
	
	@PersistenceContext
	EntityManager entityManager;

	/**
	 * Devuelve una lista de productos que ha pagado un cliente
	 * @return Devuelve una lista de productos que ha pagado un cliente
	 */
	@Override
	public List<Producto> getProductosPagadosDeCliente(Long id) {
		List<Producto> productos = getClienteDAO().findById(id).get().getProductos().stream()
				.filter(p -> p.isPagado() == true).collect(Collectors.toList());
		return productos;
	}

	/**
	 * Devuelve una lista de productos que de un cliente
	 * @return Devuelve una lista de productos de un cliente
	 */
	@Override
	public List<Producto> getProductosDeCliente(Long id) {
		List<Producto> productos = getClienteDAO().findById(id).get().getProductos().stream().toList();
		return productos;
	}
}
