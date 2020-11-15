
package com.crismary.crys.pojo;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import java.util.Date;

import com.crismary.crys.pojo.MadrePojo;
import com.crismary.crys.pojo.PadrePojo;


public class HijaPojo implements Serializable {

private static final long serialVersionUID = 7234189286598601233L;

		private Long id;

		private String nombre;

		private MadrePojo mama;


		private PadrePojo papa;


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
		public MadrePojo getmama() { 
			return mama;
		}
		public void setmama(MadrePojo  mama) {
			this.mama = mama;
		}
		public PadrePojo getpapa() { 
			return papa;
		}
		public void setpapa(PadrePojo  papa) {
			this.papa = papa;
		}
			public boolean equalsHijaPojo(Object o) {
				if (this == o) return true;
				if (o == null || getClass() != o.getClass()) return false;
					HijaPojo hijapojo = (HijaPojo) o;
						return 			Objects.equals(id, hijapojo.id) ||
			Objects.equals(nombre, hijapojo.nombre);

}}
