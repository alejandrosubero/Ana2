
package com.crysmari.cde.entitys;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import java.util.Date;



@Entity
@Table(name = "hombre")
public class Hombre implements Serializable {

private static final long serialVersionUID = -5957981387611851789L;

		@Id
		@GeneratedValue(generator = "sequence_mat_id_generator")
		@SequenceGenerator(name="sequence_mat_id_generator", initialValue= 25, allocationSize=1000)
		@Column(name = "id", updatable = true, nullable = false, length = 25)
		private Long id;


		@Column(name = "edad", updatable = true, nullable = true, length = 200)
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
			public boolean equalsHombre(Object o) {
				if (this == o) return true;
				if (o == null || getClass() != o.getClass()) return false;
					Hombre hombre = (Hombre) o;
						return 			Objects.equals(id, hombre.id) ||
			Objects.equals(edad, hombre.edad);

}}
