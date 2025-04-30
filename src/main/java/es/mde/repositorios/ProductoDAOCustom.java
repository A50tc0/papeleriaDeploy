package es.mde.repositorios;

import java.util.List;

import es.mde.entidades.Producto;

public interface ProductoDAOCustom {

	List<Producto> getProductosDeClientesEmpresa(String tipo);
}
