
package com.cosa.ascosa.service ;

import java.util.Optional;import java.util.Date;

import java.util.ArrayList;
import java.util.List;
import com.cosa.ascosa.entitys.Duro;import com.cosa.ascosa.entitys.Suave;



public interface DuroService{
 
		public Duro  findByCuento(Integer cuento);

		public List<Duro>  findByCuentoContaining(Integer cuento);

		public Duro findById(Long id);
		public boolean saveDuro(Duro duro);
		public List<Duro> getAllDuro();
		public boolean deleteDuro(Long id);
		public boolean updateDuro(Duro duro);
 		public boolean saveOrUpdateDuro(Duro duro);

		public List<Duro>  findByRelacionSuave(Suave suave);
}
