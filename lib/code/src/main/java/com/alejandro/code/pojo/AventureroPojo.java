
package com.alejandro.code.pojo;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;


import com.alejandro.code.pojo.PiesasPojo;


public class AventureroPojo implements Serializable {

private static final long serialVersionUID = 7523023137828705228L;

		private Long id;

		private String nombre;

		private String poder;

		private PiesasPojo piesas;


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
		public String getPoder() { 
			return poder;
		}
		public void setPoder(String  poder) {
			this.poder = poder;
		}
		public PiesasPojo getpiesas() { 
			return piesas;
		}
		public void setpiesas(PiesasPojo  piesas) {
			this.piesas = piesas;
		}
			public boolean equalsAventureroPojo(Object o) {
				if (this == o) return true;
				if (o == null || getClass() != o.getClass()) return false;
					AventureroPojo aventureropojo = (AventureroPojo) o;
						return 			Objects.equals(id, aventureropojo.id) ||
			Objects.equals(nombre, aventureropojo.nombre) ||
			Objects.equals(poder, aventureropojo.poder);

}}
