
package com.crysmari.cde.pojo;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import java.util.Date;



public class HombrePojo implements Serializable {

private static final long serialVersionUID = 2128428757651528279L;

		private Long id;

		private Integer edad;

		public Long getId() { 
			return id;
		}
		public void setId(Long  id) {
			this.id = id;
		}
		public Integer getEdad() { 
			return edad;
		}
		public void setEdad(Integer  edad) {
			this.edad = edad;
		}
			public boolean equalsHombrePojo(Object o) {
				if (this == o) return true;
				if (o == null || getClass() != o.getClass()) return false;
					HombrePojo hombrepojo = (HombrePojo) o;
						return 			Objects.equals(id, hombrepojo.id) ||
			Objects.equals(edad, hombrepojo.edad);

}}
