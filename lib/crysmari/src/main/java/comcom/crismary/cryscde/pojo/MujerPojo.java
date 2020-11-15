
package com.crysmari.cde.pojo;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import java.util.Date;

import com.crysmari.cde.pojo.HombrePojo;


public class MujerPojo implements Serializable {

private static final long serialVersionUID = 148625970546469948L;

		private Long id;

		private Integer edad;

		private HombrePojo esposo;


		public Long getId() { 
			return id;
		}
		public void setId(Long  id) {
			this.id = id;
		}
		public Integer getEdad() { 
			return edad;
		}
		public void setEdad(Integer  edad) {
			this.edad = edad;
		}
		public HombrePojo getesposo() { 
			return esposo;
		}
		public void setesposo(HombrePojo  esposo) {
			this.esposo = esposo;
		}
			public boolean equalsMujerPojo(Object o) {
				if (this == o) return true;
				if (o == null || getClass() != o.getClass()) return false;
					MujerPojo mujerpojo = (MujerPojo) o;
						return 			Objects.equals(id, mujerpojo.id) ||
			Objects.equals(edad, mujerpojo.edad);

}}
