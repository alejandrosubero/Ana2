
package com.crismary.crys.entitys;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import java.util.Date;



@Entity
@Table(name = "MADRE")
public class Madre implements Serializable {

private static final long serialVersionUID = -3663300079452293918L;

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
			public boolean equalsMadre(Object o) {
				if (this == o) return true;
				if (o == null || getClass() != o.getClass()) return false;
					Madre madre = (Madre) o;
						return 			Objects.equals(id, madre.id) ||
			Objects.equals(nombre, madre.nombre);

}}
