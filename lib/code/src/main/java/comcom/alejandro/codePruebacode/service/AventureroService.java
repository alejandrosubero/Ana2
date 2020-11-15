
package com.alejandro.code.service ;

import java.util.Optional;
import java.util.ArrayList;
import java.util.List;
import com.alejandro.code.entitys.Aventurero;import com.alejandro.code.entitys.Piesas;



public interface AventureroService{
 
		public Aventurero  findByNombre(String nombre);

		public Aventurero  findByPoder(String poder);

		public List<Aventurero>  findByNombreContaining(String nombre);

		public List<Aventurero>  findByPoderContaining(String poder);

		public Aventurero findById(Long id);
		public boolean saveAventurero(Aventurero aventurero);
		public List<Aventurero> getAllAventurero();
		public boolean deleteAventurero(Long id);
		public boolean updateAventurero(Aventurero aventurero);
 		public boolean saveOrUpdateAventurero(Aventurero aventurero);

		public List<Aventurero>  findByRelacionPiesas(Piesas piesas);
}
