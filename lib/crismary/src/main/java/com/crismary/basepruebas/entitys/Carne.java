
package com.crismary.basepruebas.entitys;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import java.util.Date;



@Entity
@Table(name = "carne")
public class Carne implements Serializable {

private static final long serialVersionUID = 5665605159538455788L;

		@Id
		@GeneratedValue(generator = "sequence_mat_id_generator")
		@SequenceGenerator(name="sequence_mat_id_generator", initialValue= 25, allocationSize=1000)
		@Column(name = "id", updatable = true, nullable = false, length = 25)
		private Long id;


		@Column(name = "nombre", updatable = true, nullable = true, length = 200)
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
			public boolean equalsCarne(Object o) {
				if (this == o) return true;
				if (o == null || getClass() != o.getClass()) return false;
					Carne carne = (Carne) o;
						return 			Objects.equals(id, carne.id) ||
			Objects.equals(nombre, carne.nombre);

}}
