
package com.cosa.ascosa.service ;

import java.util.Optional;import java.util.Date;

import java.util.ArrayList;
import java.util.List;
import com.cosa.ascosa.entitys.Suave;


public interface SuaveService{
 
		public Suave  findBySi(Boolean si);

		public List<Suave>  findBySiContaining(Boolean si);

		public Suave findById(Long id);
		public boolean saveSuave(Suave suave);
		public List<Suave> getAllSuave();
		public boolean deleteSuave(Long id);
		public boolean updateSuave(Suave suave);
 		public boolean saveOrUpdateSuave(Suave suave);

}
