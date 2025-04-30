package es.mde.papeleria;

import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import es.mde.entidades.Cliente;
import es.mde.entidades.Producto;
import es.mde.repositorios.ClienteDAO;
import es.mde.repositorios.ProductoDAO;

@SpringBootApplication
public class MipapeleriaApplication {

	private static final Logger log = LoggerFactory.getLogger(MipapeleriaApplication.class);

	public static void main(String[] args) {
		// SpringApplication.run(MipapeleriaApplication.class, args);

		ConfigurableApplicationContext context = SpringApplication.run(MipapeleriaApplication.class, args);

		System.err.println("Está funcionado la aplicación");
		log.debug("Está funcionando la aplicación");

		// Creando clientes
//				ClienteDAO clienteDAO = context.getBean(ClienteDAO.class);
//				clienteDAO.save(generaCliente());
//				clienteDAO.save(generaCliente());
//				clienteDAO.save(generaCliente());
//				List<Cliente> clientes = clienteDAO.findAll();
//				clientes.stream().map(Cliente::toString).forEach(log::info);

		// Creando productos
				ProductoDAO productoDAO = context.getBean(ProductoDAO.class);
//				productoDAO.save(generaProducto());
//				productoDAO.save(generaProducto());
//				productoDAO.save(generaProducto());
//				List<Producto> productos = productoDAO.findAll();
//				productos.stream().map(Producto::toString).forEach(log::info);
				productoDAO.getProductosDeClientesEmpresa("gmail.com").stream().map(Producto::toString).forEach(log::error);

//		ClienteDAO clienteDAO = context.getBean(ClienteDAO.class);
//		Cliente cliente1 = clienteDAO.save(generaCliente());
//		Cliente cliente2 = clienteDAO.save(generaCliente());
//		Cliente cliente3 = clienteDAO.save(generaCliente());
//		ProductoDAO productoDAO = context.getBean(ProductoDAO.class);
//
//		productoDAO.save(generaProductoConCliente(cliente1));
//		productoDAO.save(generaProductoConCliente(cliente2));
//		productoDAO.save(generaProductoConCliente(cliente3));
//		productoDAO.save(generaProductoConCliente(cliente1));
//		productoDAO.save(generaProductoConCliente(cliente2));
//		productoDAO.save(generaProductoConCliente(cliente3));
//		productoDAO.save(generaProductoConCliente(cliente1));
//		productoDAO.save(generaProductoConCliente(cliente2));
//		productoDAO.save(generaProductoConCliente(cliente3));

//				context.close();
//				System.err.println("Está cerrándose la aplicación");

//		String producto = "Producto" + ThreadLocalRandom.current().nextInt(numero, numero * 20);
//		return new Producto(producto, cliente);
//
//
	}
//	static Cliente generaCliente() {
//
//		int numero = 10000;
//		String cliente = "Cliente" + ThreadLocalRandom.current().nextInt(numero, numero * 20);
//		return new Cliente(cliente, cliente + "@mail.com");
//	}
//	static Producto generaProducto() {
//
//		int numero = 10000;
//		String producto = "Producto" + ThreadLocalRandom.current().nextInt(numero, numero * 20);
//		return new Producto(producto);
//	}
//	
//	static Producto generaProductoConCliente(Cliente cliente) {
//
//		int numero = 10000;
//		String producto = "Producto" + ThreadLocalRandom.current().nextInt(numero, numero * 20);
//		return new Producto(producto, cliente);
//	}

}
