package es.mde.repositorios;

import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import es.mde.entidades.Cliente;
import jakarta.persistence.PostRemove;
import jakarta.persistence.PostUpdate;
import jakarta.persistence.PrePersist;

@Component
public class ClienteListener {
	private Logger log = LoggerFactory.getLogger(ClienteListener.class);
	private static ApplicationContext applicationContext;

	@Autowired
	public void setApplicationContext(ApplicationContext applicationContext) {
		ClienteListener.applicationContext = applicationContext;
	}

	private static ClienteDAO getClienteDAO() {
		return applicationContext.getBean(ClienteDAO.class);
	}

	/**
	 * Crea un Listener que se ejecuta antes de guardar un cliente
	 * 
	 * @param cliente Cliente que se ha guardado
	 */
	@PrePersist
	public void preGuardar(Cliente cliente) throws Exception {
		boolean condicion = false;
		if (getClienteDAO().count() != 0) {
			List<Cliente> clientes = getClienteDAO().findAll().stream().collect(Collectors.toList());
			System.err.println("Leyendo lista de clientes: Primer cliente" + clientes.get(0).getNombre());
		}
		if (condicion) {
			throw new Exception("Se cumple mi condición para no crearse el cliente");
		} else {
			System.err.println("Se va a guardar un cliente: " + cliente.getNombre());
		}
	}

	@PostRemove
	public void postBorrar(Cliente cliente) {
		System.err.println("Se ha borrado al cliente: " + cliente.getNombre());
	}

	@PostUpdate
	public void postActualizar(Cliente cliente) {
		System.err.println("Se ha actualizado al cliente: " + cliente.getNombre());
	}

//	@PostLoad
//	public void postGuardar(Cliente cliente) {
//		log.warn("has guardado un cliente: " + cliente.getNombre());
//	}
}

