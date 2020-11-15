
package com.alejandro.code.entitys;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;




@Entity
@Table(name = "sombis")
public class Sombis implements Serializable {

private static final long serialVersionUID = -4558826568203150487L;

		@Id
		@GeneratedValue(generator = "sequence_mat_id_generator")
		@SequenceGenerator(name="sequence_mat_id_generator", initialValue= 25, allocationSize=1000)
		@Column(name = "id", updatable = true, nullable = false, length = 25)
		private Long id;


		@Column(name = "dia", updatable = true, nullable = true, length = 200)
		private String dia;


		public Long getId() { 
			return id;
		}
		public void setId(Long  id) {
			this.id = id;
		}
		public String getDia() { 
			return dia;
		}
		public void setDia(String  dia) {
			this.dia = dia;
		}
			public boolean equalsSombis(Object o) {
				if (this == o) return true;
				if (o == null || getClass() != o.getClass()) return false;
					Sombis sombis = (Sombis) o;
						return 			Objects.equals(id, sombis.id) ||
			Objects.equals(dia, sombis.dia);

}}
