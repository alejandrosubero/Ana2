
package com.crismary.basepruebas.service ;

import java.util.Optional;import java.util.Date;

import java.util.ArrayList;
import java.util.List;
import com.crismary.basepruebas.entitys.Carne;


public interface CarneService{
 
		public Carne  findByNombre(String nombre);

		public List<Carne>  findByNombreContaining(String nombre);

		public Carne findById(Long id);
		public boolean saveCarne(Carne carne);
		public List<Carne> getAllCarne();
		public boolean deleteCarne(Long id);
		public boolean updateCarne(Carne carne);
 		public boolean saveOrUpdateCarne(Carne carne);

}
