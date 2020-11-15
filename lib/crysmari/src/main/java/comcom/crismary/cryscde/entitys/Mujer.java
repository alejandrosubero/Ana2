
package com.crysmari.cde.entitys;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import java.util.Date;

import com.crysmari.cde.entitys.Hombre;


@Entity
@Table(name = "mujer")
public class Mujer implements Serializable {

private static final long serialVersionUID = 3681660198194436335L;

		@Id
		@GeneratedValue(generator = "sequence_mat_id_generator")
		@SequenceGenerator(name="sequence_mat_id_generator", initialValue= 25, allocationSize=1000)
		@Column(name = "id", updatable = true, nullable = false, length = 25)
		private Long id;


		@Column(name = "edad", updatable = true, nullable = true, length = 200)
		private Integer edad;


		@OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER )

		private Hombre esposo;


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
		public Hombre getesposo() { 
			return esposo;
		}
		public void setesposo(Hombre  esposo) {
			this.esposo = esposo;
		}
			public boolean equalsMujer(Object o) {
				if (this == o) return true;
				if (o == null || getClass() != o.getClass()) return false;
					Mujer mujer = (Mujer) o;
						return 			Objects.equals(id, mujer.id) ||
			Objects.equals(edad, mujer.edad);

}}
