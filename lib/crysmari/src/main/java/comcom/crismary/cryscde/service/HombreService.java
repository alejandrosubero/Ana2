
package com.crysmari.cde.service ;

import java.util.Optional;import java.util.Date;

import java.util.ArrayList;
import java.util.List;
import com.crysmari.cde.entitys.Hombre;


public interface HombreService{
 
		public Hombre  findByEdad(Integer edad);

		public List<Hombre>  findByEdadContaining(Integer edad);

		public Hombre findById(Long id);
		public boolean saveHombre(Hombre hombre);
		public List<Hombre> getAllHombre();
		public boolean deleteHombre(Long id);
		public boolean updateHombre(Hombre hombre);
 		public boolean saveOrUpdateHombre(Hombre hombre);

}
