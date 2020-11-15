
package com.alejandro.code.pojo;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;




public class PiesasPojo implements Serializable {

private static final long serialVersionUID = -2729366784525579258L;

		private Long id;

		private String humanos;

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
			public boolean equalsPiesasPojo(Object o) {
				if (this == o) return true;
				if (o == null || getClass() != o.getClass()) return false;
					PiesasPojo piesaspojo = (PiesasPojo) o;
						return 			Objects.equals(id, piesaspojo.id) ||
			Objects.equals(humanos, piesaspojo.humanos) ||
			Objects.equals(original, piesaspojo.original);

}}
