
package com.cosa.ascosa.pojo;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import java.util.Date;



public class SuavePojo implements Serializable {

private static final long serialVersionUID = -1862281826825035150L;

		private Long id;

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
			public boolean equalsSuavePojo(Object o) {
				if (this == o) return true;
				if (o == null || getClass() != o.getClass()) return false;
					SuavePojo suavepojo = (SuavePojo) o;
						return 			Objects.equals(id, suavepojo.id) ||
			Objects.equals(si, suavepojo.si);

}}
