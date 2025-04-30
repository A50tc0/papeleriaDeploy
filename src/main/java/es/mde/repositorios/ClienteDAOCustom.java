package es.mde.repositorios;

import java.util.List;

import es.mde.entidades.Producto;

/**
 * Representa la interfaz con los metodos personalizados de los Clientes
 */
public interface ClienteDAOCustom {

	/**
	 * Devuelve una lista de productos que ha pagado el cliente
	 * 
	 * @param id Id del cliente
	 * @return Devuelve una lista de productos que ha pagado el cliente
	 */
	List<Producto> getProductosPagadosDeCliente(Long id);

	/**
	 * Devuelve una lista de productos de un cliente
	 * 
	 * @param id Id del cliente
	 * @return Devuelve una lista de productos que del cliente
	 */
	List<Producto> getProductosDeCliente(Long id);

}
