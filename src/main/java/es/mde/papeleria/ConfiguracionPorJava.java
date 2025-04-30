package es.mde.papeleria;

import java.util.AbstractMap;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;
import org.springframework.data.rest.webmvc.RepositorySearchesResource;
import org.springframework.hateoas.server.RepresentationModelProcessor;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.fasterxml.jackson.databind.ObjectMapper;

import es.mde.entidades.Cliente;
import es.mde.entidades.Cuaderno;
import es.mde.entidades.Libro;
import es.mde.entidades.Producto;
import es.mde.rest.ConfiguracionRest;
import es.mde.rest.MixIns;
import es.mde.rest.ProductoController;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import org.springframework.hateoas.Link;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;

import java.lang.reflect.Method;
import java.net.URI;
import java.net.URLDecoder;
import java.util.stream.Collectors;
import java.util.stream.Stream;



@Configuration
@EnableTransactionManagement
@PropertySource({ "classpath:config/rest.properties", "classpath:config/jackson.properties", 
	"classpath:config/gestionBBDD.properties", //"classpath:config/passwordsBD.properties"
	})
@EnableJpaRepositories("${misRepositorios}") // leer valor de propiedades? pero solo para las entidades anotadas
@Import(ConfiguracionRest.class)
@ComponentScan({"es.mde.repositorios", "es.mde.rest"})//para que escanee los Listener y los Controller...
public class ConfiguracionPorJava {

	@Value("${misEntidades}")
	String entidades;

	@Bean
	public LocalContainerEntityManagerFactoryBean entityManagerFactory(DataSource dataSource, Environment env,
			JpaVendorAdapter vendorAdapter) {

		LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();
		em.setDataSource(dataSource);
//	    JpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter(); // O pedirlo como parametro y que haga el Autowired
		em.setJpaVendorAdapter(vendorAdapter);

		em.setPackagesToScan(entidades); // leer valor de propiedades? pero solo para las entidades anotadas
		// em.setMappingResources("jpa/Usuario.orm.xml", "jpa/Cuaderno.orm.xml"); //para escanear archivos xml...
		// leerValorDePropiedades?
		//em.setMappingResources("jpa/Perro.orm.xml"); //para escanear archivos xml...
		em.setMappingResources("jpa/Perro.orm.xml","jpa/Persona.orm.xml","jpa/Zapato.orm.xml"); //para escanear archivos xml...


		Properties jpaProperties = new Properties();
		Arrays.asList("dialect", "show_sql", "hbm2ddl.auto", "enable_lazy_load_no_trans") //  leer valor de	para las entidades anotadas 
				.stream().map(s -> "hibernate." + s)
				.map(p -> new AbstractMap.SimpleEntry<String, String>(p, env.getProperty(p)))
				.filter(e -> e.getValue() != null).forEach(e -> jpaProperties.put(e.getKey(), e.getValue()));
		em.setJpaProperties(jpaProperties);

		return em;
	}

	@Bean
	public EntityManager entityManager(EntityManagerFactory emf) {
		System.err.println("--- LAS ENTIDADES MAPEADAS SON ---");
		emf.getMetamodel().getEntities().forEach(System.err::println);
		System.err.println("----------------------------------");

		return emf.createEntityManager();
	}
	
	@Bean
	public ObjectMapper getObjectMapper() {

		ObjectMapper mapper = new ObjectMapper();
		mapper.addMixIn(Cliente.class, MixIns.Clientes.class);
		mapper.addMixIn(Libro.class, MixIns.Libros.class);
		mapper.addMixIn(Cuaderno.class, MixIns.Cuadernos.class);
		return mapper;
	}
	
//	/**
//	 * Enlaza automaticamente los links de los controladores registrados siguiendo
//	 * las <a href=
//	 * "https://www.hijosdelspectrum.com/2020/05/codigo-util-clase-configuracionrest.html">instrucciones
//	 * </a>
//	 * 
//	 * @param config {@link RepositoryRestConfiguration} para recuperar al
//	 *               {@code basePath}
//	 * @return el bean del tipo
//	 *         {@code RepresentationModelProcessor<RepositorySearchesResource>}
//	 */
//	@Bean
//	RepresentationModelProcessor<RepositorySearchesResource> addSearchLinks(RepositoryRestConfiguration config) {
//		Map<Class<?>, Class<?>> controllersRegistrados = new HashMap<>();
//		controllersRegistrados.put(Producto.class, ProductoController.class);
//
//		return new RepresentationModelProcessor<RepositorySearchesResource>() {
//
//			@Override
//			public RepositorySearchesResource process(RepositorySearchesResource searchResource) {
//				if (controllersRegistrados.containsKey(searchResource.getDomainType())) {
//					Class<?> controller = controllersRegistrados.get(searchResource.getDomainType());
//					Method[] metodos = controller.getDeclaredMethods();
//					URI uriController = linkTo(controller).toUri();
//					String controllerPath = config.getBasePath() + uriController.getPath();
//					Stream.of(metodos).filter(
//							m -> m.isAnnotationPresent(ResponseBody.class) && m.isAnnotationPresent(GetMapping.class))
//							.map(m -> {
//								Link link = null;
//								try {
//									String pathMetodo = String.join("", m.getAnnotation(GetMapping.class).value());
//									String pathRecurso = new URI(uriController.getScheme(), uriController.getUserInfo(),
//											uriController.getHost(), uriController.getPort(),
//											controllerPath + pathMetodo, null, null).toString();
//									String requestParams = Stream.of(m.getParameters())
//											.filter(p -> p.isAnnotationPresent(RequestParam.class)).map(p -> {
//												String nombreParametro = p.getAnnotation(RequestParam.class).value();
//												return !"".equals(nombreParametro) ? nombreParametro : p.getName();
//											}).collect(Collectors.joining(","));
//									link = Link.of(URLDecoder.decode(pathRecurso, "UTF-8") + "{?" + requestParams + "}",
//											m.getName());
//								} catch (Exception e) {
//									e.printStackTrace();
//								}
//								return link;
//							}).filter(l -> l != null).forEach(searchResource::add);
//				}
//
//				return searchResource;
//			}
//
//		};
//	}

}
