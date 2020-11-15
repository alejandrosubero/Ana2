
package com.crismary.crys.pojo;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import java.util.Date;



public class MadrePojo implements Serializable {

private static final long serialVersionUID = 4349058007638632121L;

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
			public boolean equalsMadrePojo(Object o) {
				if (this == o) return true;
				if (o == null || getClass() != o.getClass()) return false;
					MadrePojo madrepojo = (MadrePojo) o;
						return 			Objects.equals(id, madrepojo.id) ||
			Objects.equals(nombre, madrepojo.nombre);

}}
