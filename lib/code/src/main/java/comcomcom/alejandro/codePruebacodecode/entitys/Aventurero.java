
package com.alejandro.code.entitys;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;


import com.alejandro.code.entitys.Piesas;


@Entity
@Table(name = "aventurero")
public class Aventurero implements Serializable {

private static final long serialVersionUID = 1570084001923268078L;

		@Id
		@GeneratedValue(generator = "sequence_mat_id_generator")
		@SequenceGenerator(name="sequence_mat_id_generator", initialValue= 25, allocationSize=1000)
		@Column(name = "id", updatable = true, nullable = false, length = 25)
		private Long id;


		@Column(name = "nombre", updatable = true, nullable = true, length = 200)
		private String nombre;


		@Column(name = "poder", updatable = true, nullable = true, length = 200)
		private String poder;


		@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER )

		private Piesas piesas;


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
		public Piesas getpiesas() { 
			return piesas;
		}
		public void setpiesas(Piesas  piesas) {
			this.piesas = piesas;
		}
			public boolean equalsAventurero(Object o) {
				if (this == o) return true;
				if (o == null || getClass() != o.getClass()) return false;
					Aventurero aventurero = (Aventurero) o;
						return 			Objects.equals(id, aventurero.id) ||
			Objects.equals(nombre, aventurero.nombre) ||
			Objects.equals(poder, aventurero.poder);

}}
