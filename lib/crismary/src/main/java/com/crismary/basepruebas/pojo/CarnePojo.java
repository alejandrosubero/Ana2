
package com.crismary.basepruebas.pojo;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import java.util.Date;



public class CarnePojo implements Serializable {

private static final long serialVersionUID = 2993066076853280926L;

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
			public boolean equalsCarnePojo(Object o) {
				if (this == o) return true;
				if (o == null || getClass() != o.getClass()) return false;
					CarnePojo carnepojo = (CarnePojo) o;
						return 			Objects.equals(id, carnepojo.id) ||
			Objects.equals(nombre, carnepojo.nombre);

}}
