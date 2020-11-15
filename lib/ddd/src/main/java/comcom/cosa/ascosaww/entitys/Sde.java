
package com.ddd.www.entitys;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import java.util.Date;



@Entity
@Table(name = "sde")
public class Sde implements Serializable {

private static final long serialVersionUID = 5856274562992575267L;

		@Id
		@GeneratedValue(generator = "sequence_mat_id_generator")
		@SequenceGenerator(name="sequence_mat_id_generator", initialValue= 25, allocationSize=1000)
		@Column(name = "id", updatable = true, nullable = false, length = 25)
		private Long id;


		public Long getId() { 
			return id;
		}
		public void setId(Long  id) {
			this.id = id;
		}
			public boolean equalsSde(Object o) {
				if (this == o) return true;
				if (o == null || getClass() != o.getClass()) return false;
					Sde sde = (Sde) o;
						return 			Objects.equals(id, sde.id);

}}
