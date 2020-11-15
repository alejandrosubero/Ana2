
package com.cosa.ascosa.entitys;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import java.util.Date;



@Entity
@Table(name = "suave")
public class Suave implements Serializable {

private static final long serialVersionUID = -2493053851233769734L;

		@Id
		@GeneratedValue(generator = "sequence_mat_id_generator")
		@SequenceGenerator(name="sequence_mat_id_generator", initialValue= 25, allocationSize=1000)
		@Column(name = "id", updatable = true, nullable = false, length = 25)
		private Long id;


		@Column(name = "si", updatable = true, nullable = true, length = 200)
		private Boolean si;


		public Long getId() { 
			return id;
		}
		public void setId(Long  id) {
			this.id = id;
		}
		public Boolean getSi() { 
			return si;
		}
		public void setSi(Boolean  si) {
			this.si = si;
		}
			public boolean equalsSuave(Object o) {
				if (this == o) return true;
				if (o == null || getClass() != o.getClass()) return false;
					Suave suave = (Suave) o;
						return 			Objects.equals(id, suave.id) ||
			Objects.equals(si, suave.si);

}}
