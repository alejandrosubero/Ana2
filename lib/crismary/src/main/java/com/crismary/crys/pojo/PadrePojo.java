
package com.crismary.crys.pojo;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import java.util.Date;



public class PadrePojo implements Serializable {

private static final long serialVersionUID = 2981601283939720261L;

		private Long id;

		private String nombre;

		public Long getId() { 
			return id;
		}
		public void setId(Long  id) {
			this.id = id;
		}
		public String getNombre() { 
			return nombre;
		}
		public void setNombre(String  nombre) {
			this.nombre = nombre;
		}
			public boolean equalsPadrePojo(Object o) {
				if (this == o) return true;
				if (o == null || getClass() != o.getClass()) return false;
					PadrePojo padrepojo = (PadrePojo) o;
						return 			Objects.equals(id, padrepojo.id) ||
			Objects.equals(nombre, padrepojo.nombre);

}}
