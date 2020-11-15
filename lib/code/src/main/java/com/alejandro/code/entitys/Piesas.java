
package com.alejandro.code.entitys;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;




@Entity
@Table(name = "piesas")
public class Piesas implements Serializable {

private static final long serialVersionUID = 1074735043413582277L;

		@Id
		@GeneratedValue(generator = "sequence_mat_id_generator")
		@SequenceGenerator(name="sequence_mat_id_generator", initialValue= 25, allocationSize=1000)
		@Column(name = "id", updatable = true, nullable = false, length = 25)
		private Long id;


		@Column(name = "humanos", updatable = true, nullable = true, length = 200)
		private String humanos;


		@Column(name = "original", updatable = true, nullable = true, length = 200)
		private String original;


		public Long getId() { 
			return id;
		}
		public void setId(Long  id) {
			this.id = id;
		}
		public String getHumanos() { 
			return humanos;
		}
		public void setHumanos(String  humanos) {
			this.humanos = humanos;
		}
		public String getOriginal() { 
			return original;
		}
		public void setOriginal(String  original) {
			this.original = original;
		}
			public boolean equalsPiesas(Object o) {
				if (this == o) return true;
				if (o == null || getClass() != o.getClass()) return false;
					Piesas piesas = (Piesas) o;
						return 			Objects.equals(id, piesas.id) ||
			Objects.equals(humanos, piesas.humanos) ||
			Objects.equals(original, piesas.original);

}}
