
package com.crismary.basepruebas.entitys;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import java.util.Date;

import com.crismary.basepruebas.entitys.Carne;


@Entity
@Table(name = "prueba")
public class Prueba implements Serializable {

private static final long serialVersionUID = 2767993545752213704L;

		@Id
		@GeneratedValue(generator = "sequence_mat_id_generator")
		@SequenceGenerator(name="sequence_mat_id_generator", initialValue= 25, allocationSize=1000)
		@Column(name = "id", updatable = true, nullable = false, length = 25)
		private Long id;


		@Column(name = "titulo", updatable = true, nullable = true, length = 200)
		private String titulo;


		@OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER )

		private Carne dieta;


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
		public Carne getdieta() { 
			return dieta;
		}
		public void setdieta(Carne  dieta) {
			this.dieta = dieta;
		}
			public boolean equalsPrueba(Object o) {
				if (this == o) return true;
				if (o == null || getClass() != o.getClass()) return false;
					Prueba prueba = (Prueba) o;
						return 			Objects.equals(id, prueba.id) ||
			Objects.equals(titulo, prueba.titulo);

}}
