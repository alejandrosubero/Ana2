
package com.crysmari.cde.service ;

import java.util.Optional;import java.util.Date;

import java.util.ArrayList;
import java.util.List;
import com.crysmari.cde.entitys.Mujer;import com.crysmari.cde.entitys.Hombre;



public interface MujerService{
 
		public Mujer  findByEdad(Integer edad);

		public List<Mujer>  findByEdadContaining(Integer edad);

		public Mujer findById(Long id);
		public boolean saveMujer(Mujer mujer);
		public List<Mujer> getAllMujer();
		public boolean deleteMujer(Long id);
		public boolean updateMujer(Mujer mujer);
 		public boolean saveOrUpdateMujer(Mujer mujer);

		public List<Mujer>  findByRelacionHombre(Hombre hombre);
}
