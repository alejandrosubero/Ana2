
package com.crismary.crys.service ;

import java.util.Optional;import java.util.Date;

import java.util.ArrayList;
import java.util.List;
import com.crismary.crys.entitys.Hija;import com.crismary.crys.entitys.Madre;
import com.crismary.crys.entitys.Padre;



public interface HijaService{
 
		public Hija  findByNombre(String nombre);

		public List<Hija>  findByNombreContaining(String nombre);

		public Hija findById(Long id);
		public boolean saveHija(Hija hija);
		public List<Hija> getAllHija();
		public boolean deleteHija(Long id);
		public boolean updateHija(Hija hija);
 		public boolean saveOrUpdateHija(Hija hija);

		public List<Hija>  findByRelacionMadre(Madre madre);
		public List<Hija>  findByRelacionPadre(Padre padre);
}
