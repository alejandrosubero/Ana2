
package com.crismary.crys.entitys;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import java.util.Date;



@Entity
@Table(name = "PADRE")
public class Padre implements Serializable {

private static final long serialVersionUID = -1501263844300859806L;

		@Id
		@GeneratedValue(generator = "sequence_mat_id_generator")
		@SequenceGenerator(name="sequence_mat_id_generator", initialValue= 25, allocationSize=1000)
		@Column(name = "ID", updatable = true, nullable = false, length = 25)
		private Long id;


		@Column(name = "NOMBRE", updatable = true, nullable = true, length = 200)
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
			public boolean equalsPadre(Object o) {
				if (this == o) return true;
				if (o == null || getClass() != o.getClass()) return false;
					Padre padre = (Padre) o;
						return 			Objects.equals(id, padre.id) ||
			Objects.equals(nombre, padre.nombre);

}}
