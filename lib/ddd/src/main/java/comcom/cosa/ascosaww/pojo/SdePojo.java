
package com.ddd.www.pojo;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import java.util.Date;



public class SdePojo implements Serializable {

private static final long serialVersionUID = 5815781116535035924L;

		private Long id;

		public Long getId() { 
			return id;
		}
		public void setId(Long  id) {
			this.id = id;
		}
			public boolean equalsSdePojo(Object o) {
				if (this == o) return true;
				if (o == null || getClass() != o.getClass()) return false;
					SdePojo sdepojo = (SdePojo) o;
						return 			Objects.equals(id, sdepojo.id);

}}
