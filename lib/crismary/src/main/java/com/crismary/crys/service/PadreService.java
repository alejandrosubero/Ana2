
package com.crismary.crys.service ;

import java.util.Optional;import java.util.Date;

import java.util.ArrayList;
import java.util.List;
import com.crismary.crys.entitys.Padre;


public interface PadreService{
 
		public Padre  findByNombre(String nombre);

		public List<Padre>  findByNombreContaining(String nombre);

		public Padre findById(Long id);
		public boolean savePadre(Padre padre);
		public List<Padre> getAllPadre();
		public boolean deletePadre(Long id);
		public boolean updatePadre(Padre padre);
 		public boolean saveOrUpdatePadre(Padre padre);

}
