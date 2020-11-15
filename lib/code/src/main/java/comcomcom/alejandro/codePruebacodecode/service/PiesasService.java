
package com.alejandro.code.service ;

import java.util.Optional;
import java.util.ArrayList;
import java.util.List;
import com.alejandro.code.entitys.Piesas;


public interface PiesasService{
 
		public Piesas  findByHumanos(String humanos);

		public Piesas  findByOriginal(String original);

		public List<Piesas>  findByHumanosContaining(String humanos);

		public List<Piesas>  findByOriginalContaining(String original);

		public Piesas findById(Long id);
		public boolean savePiesas(Piesas piesas);
		public List<Piesas> getAllPiesas();
		public boolean deletePiesas(Long id);
		public boolean updatePiesas(Piesas piesas);
 		public boolean saveOrUpdatePiesas(Piesas piesas);

}
