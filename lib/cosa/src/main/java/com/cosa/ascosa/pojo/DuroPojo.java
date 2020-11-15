
package com.cosa.ascosa.pojo;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import java.util.Date;

import com.cosa.ascosa.pojo.SuavePojo;


public class DuroPojo implements Serializable {

private static final long serialVersionUID = -568976957109340248L;

		private Long id;

		private Integer cuento;

		private SuavePojo suave;


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
		public SuavePojo getsuave() { 
			return suave;
		}
		public void setsuave(SuavePojo  suave) {
			this.suave = suave;
		}
			public boolean equalsDuroPojo(Object o) {
				if (this == o) return true;
				if (o == null || getClass() != o.getClass()) return false;
					DuroPojo duropojo = (DuroPojo) o;
						return 			Objects.equals(id, duropojo.id) ||
			Objects.equals(cuento, duropojo.cuento);

}}
