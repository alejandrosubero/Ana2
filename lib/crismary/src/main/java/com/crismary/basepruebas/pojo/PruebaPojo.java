
package com.crismary.basepruebas.pojo;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import java.util.Date;

import com.crismary.basepruebas.pojo.CarnePojo;


public class PruebaPojo implements Serializable {

private static final long serialVersionUID = -4308795589775540502L;

		private Long id;

		private String titulo;

		private CarnePojo dieta;


		public Long getId() { 
			return id;
		}
		public void setId(Long  id) {
			this.id = id;
		}
		public String getTitulo() { 
			return titulo;
		}
		public void setTitulo(String  titulo) {
			this.titulo = titulo;
		}
		public CarnePojo getdieta() { 
			return dieta;
		}
		public void setdieta(CarnePojo  dieta) {
			this.dieta = dieta;
		}
			public boolean equalsPruebaPojo(Object o) {
				if (this == o) return true;
				if (o == null || getClass() != o.getClass()) return false;
					PruebaPojo pruebapojo = (PruebaPojo) o;
						return 			Objects.equals(id, pruebapojo.id) ||
			Objects.equals(titulo, pruebapojo.titulo);

}}
