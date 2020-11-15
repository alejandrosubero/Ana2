
package com.crismary.crys.entitys;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import java.util.Date;

import com.crismary.crys.entitys.Madre;
import com.crismary.crys.entitys.Padre;


@Entity
@Table(name = "HIJA")
public class Hija implements Serializable {

private static final long serialVersionUID = -5929417859297714625L;

		@Id
		@GeneratedValue(generator = "sequence_mat_id_generator")
		@SequenceGenerator(name="sequence_mat_id_generator", initialValue= 25, allocationSize=1000)
		@Column(name = "ID", updatable = true, nullable = false, length = 25)
		private Long id;


		@Column(name = "NOMBRE", updatable = true, nullable = true, length = 200)
		private String nombre;


		@OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER )

		private Madre mama;


		@OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER )

		private Padre papa;


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
		public Madre getmama() { 
			return mama;
		}
		public void setmama(Madre  mama) {
			this.mama = mama;
		}
		public Padre getpapa() { 
			return papa;
		}
		public void setpapa(Padre  papa) {
			this.papa = papa;
		}
			public boolean equalsHija(Object o) {
				if (this == o) return true;
				if (o == null || getClass() != o.getClass()) return false;
					Hija hija = (Hija) o;
						return 			Objects.equals(id, hija.id) ||
			Objects.equals(nombre, hija.nombre);

}}
