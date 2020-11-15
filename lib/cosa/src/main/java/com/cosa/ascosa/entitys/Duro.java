
package com.cosa.ascosa.entitys;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import java.util.Date;

import com.cosa.ascosa.entitys.Suave;


@Entity
@Table(name = "duro")
public class Duro implements Serializable {

private static final long serialVersionUID = 6408526172478950723L;

		@Id
		@GeneratedValue(generator = "sequence_mat_id_generator")
		@SequenceGenerator(name="sequence_mat_id_generator", initialValue= 25, allocationSize=1000)
		@Column(name = "id", updatable = true, nullable = false, length = 25)
		private Long id;


		@Column(name = "cuento", updatable = true, nullable = true, length = 200)
		private Integer cuento;


		@OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER )

		private Suave suave;


		public Long getId() { 
			return id;
		}
		public void setId(Long  id) {
			this.id = id;
		}
		public Integer getCuento() { 
			return cuento;
		}
		public void setCuento(Integer  cuento) {
			this.cuento = cuento;
		}
		public Suave getsuave() { 
			return suave;
		}
		public void setsuave(Suave  suave) {
			this.suave = suave;
		}
			public boolean equalsDuro(Object o) {
				if (this == o) return true;
				if (o == null || getClass() != o.getClass()) return false;
					Duro duro = (Duro) o;
						return 			Objects.equals(id, duro.id) ||
			Objects.equals(cuento, duro.cuento);

}}
