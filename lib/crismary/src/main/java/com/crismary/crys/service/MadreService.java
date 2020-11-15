
package com.crismary.crys.service ;

import java.util.Optional;import java.util.Date;

import java.util.ArrayList;
import java.util.List;
import com.crismary.crys.entitys.Madre;


public interface MadreService{
 
		public Madre  findByNombre(String nombre);

		public List<Madre>  findByNombreContaining(String nombre);

		public Madre findById(Long id);
		public boolean saveMadre(Madre madre);
		public List<Madre> getAllMadre();
		public boolean deleteMadre(Long id);
		public boolean updateMadre(Madre madre);
 		public boolean saveOrUpdateMadre(Madre madre);

}
