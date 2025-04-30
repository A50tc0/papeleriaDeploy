package es.mde.rest;

import java.util.List;

import org.springframework.data.rest.webmvc.PersistentEntityResource;
import org.springframework.data.rest.webmvc.PersistentEntityResourceAssembler;
import org.springframework.data.rest.webmvc.RepositoryRestController;
import org.springframework.hateoas.CollectionModel;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import es.mde.entidades.Producto;
import es.mde.repositorios.ProductoDAO;

@RepositoryRestController
public class ProductoController {

	private ProductoDAO productoDAO;

	public ProductoController(ProductoDAO productoDAO) {
		this.productoDAO = productoDAO;
	}

	@GetMapping("/productos/search/clientes-por-correo")
	@ResponseBody
	public CollectionModel<PersistentEntityResource> getProductosDeClientesEmpresa(@RequestParam("empresa") String tipo,
			PersistentEntityResourceAssembler assembler) {
		List<Producto> productos = productoDAO.getProductosDeClientesEmpresa(tipo);

		return assembler.toCollectionModel(productos);
	}
}
