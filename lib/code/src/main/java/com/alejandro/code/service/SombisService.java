
package com.alejandro.code.service ;

import java.util.Optional;
import java.util.ArrayList;
import java.util.List;
import com.alejandro.code.entitys.Sombis;


public interface SombisService{
 
		public Sombis  findByDia(String dia);

		public List<Sombis>  findByDiaContaining(String dia);

		public Sombis findById(Long id);
		public boolean saveSombis(Sombis sombis);
		public List<Sombis> getAllSombis();
		public boolean deleteSombis(Long id);
		public boolean updateSombis(Sombis sombis);
 		public boolean saveOrUpdateSombis(Sombis sombis);

}
