package es.mde.rest;

import java.time.Instant;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

public class MixIns {

	@JsonPropertyOrder({ "correo", "nombre" })
//	@JsonIgnoreProperties({"correo"})//sino me los estaría creando sin correo
	public static interface Clientes {
	}

	@JsonPropertyOrder({ "nombre", "autor" })
	public static interface Libros {
	}

	@JsonPropertyOrder({ "nombre", "hojas" })
	public static interface Cuadernos {
	}

//	public static interface Prestaciones {
//		
//		@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
//		public Instant getFechaEntrada();
//		
//		@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
//		public Instant getFechaSalida();
//	}
}

