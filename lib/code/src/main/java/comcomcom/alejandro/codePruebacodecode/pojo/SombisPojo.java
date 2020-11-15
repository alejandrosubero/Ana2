
package com.alejandro.code.pojo;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;




public class SombisPojo implements Serializable {

private static final long serialVersionUID = -9174482514341761505L;

		private Long id;

		private String dia;

		public Long getId() { 
			return id;
		}
		public void setId(Long  id) {
			this.id = id;
		}
		public String getDia() { 
			return dia;
		}
		public void setDia(String  dia) {
			this.dia = dia;
		}
			public boolean equalsSombisPojo(Object o) {
				if (this == o) return true;
				if (o == null || getClass() != o.getClass()) return false;
					SombisPojo sombispojo = (SombisPojo) o;
						return 			Objects.equals(id, sombispojo.id) ||
			Objects.equals(dia, sombispojo.dia);

}}
