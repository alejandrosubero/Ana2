

package com.alejandro.code.serviceImplement ;

import com.alejandro.code.service.AventureroService;
import com.alejandro.code.repository.AventureroRepository;
import java.util.Optional;
import java.util.ArrayList;
import java.util.List;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import com.alejandro.code.entitys.Aventurero;
import com.alejandro.code.entitys.Piesas;




@Service
public class AventureroServiceImplement implements AventureroService {

protected static final Log logger = LogFactory.getLog(AventureroServiceImplement.class);
@Autowired
private AventureroRepository aventurerorepository;

		@Override
		public Aventurero findByNombre(String nombre){

		logger.info("Starting getAventurero");
			Aventurero aventureroEntity = new Aventurero();
		Optional<Aventurero> fileOptional1 = aventurerorepository.findByNombre(nombre);

		if (fileOptional1.isPresent()) { 

				try {
			aventureroEntity = fileOptional1.get();
				} catch (DataAccessException e) {
				logger.error(" ERROR : " + e);

				}
		}
		return aventureroEntity;	}
		@Override
		public Aventurero findByPoder(String poder){

		logger.info("Starting getAventurero");
			Aventurero aventureroEntity = new Aventurero();
		Optional<Aventurero> fileOptional1 = aventurerorepository.findByPoder(poder);

		if (fileOptional1.isPresent()) { 

				try {
			aventureroEntity = fileOptional1.get();
				} catch (DataAccessException e) {
				logger.error(" ERROR : " + e);

				}
		}
		return aventureroEntity;	}




		@Override
		public List<Aventurero> getAllAventurero(){
		logger.info("Get allProyect");
			List<Aventurero> listaAventurero = new ArrayList<Aventurero>();
				aventurerorepository.findAll().forEach(aventurero -> listaAventurero.add(aventurero));
			return listaAventurero;
}


		@Override
		public boolean saveAventurero(Aventurero aventurero){
		logger.info("Save Proyect");


				try {
				aventurerorepository.save(aventurero);
				return true;
				} catch (DataAccessException e) {
				logger.error(" ERROR : " + e);
				return false;
				}
		}


		@Override
		public boolean deleteAventurero( Long id){
		logger.info("Delete Proyect");
		boolean clave = false;


				try {
				aventurerorepository.deleteById(id);
				clave = true;
				} catch (DataAccessException e) {
				logger.error(" ERROR : " + e);
				clave = false;
				}
		return clave;
	}



		@Override
		public boolean updateAventurero(Aventurero  aventurero ){
			logger.info("Update Proyect");
			boolean clave = false;
		Aventurero empre = findById(aventurero.getId());
			empre = aventurero;

				try {
				aventurerorepository.save(empre);
						clave = true;
				} catch (DataAccessException e) {
				logger.error(" ERROR : " + e);
				clave = false;
				}

					return clave;
	}



		@Override
		public Aventurero findById( Long id){
				return  aventurerorepository.findById(id).get();
		}



		@Override
		public boolean saveOrUpdateAventurero(Aventurero  aventurero ){
			logger.info("Update Proyect");
			boolean clave = false;
			logger.info("Starting getEmpresa");
			Optional<Aventurero> fileOptional2 = aventurerorepository.findById(aventurero.getId());
			if (fileOptional2.isPresent()) { 
				clave = this.updateAventurero(aventurero);
				logger.info(" is update");
			} else {
					clave = this.saveAventurero(aventurero);
					logger.info(" is save");
 				}
 		return clave;
		}


		@Override
		public List<Aventurero> findByNombreContaining(String nombre){
			logger.info("Get allProyect");
 			List<Aventurero> listaAventurero = new ArrayList<Aventurero>();
			listaAventurero = aventurerorepository.findByNombreContaining(nombre);
  			return listaAventurero;
		}

		@Override
		public List<Aventurero> findByPoderContaining(String poder){
			logger.info("Get allProyect");
 			List<Aventurero> listaAventurero = new ArrayList<Aventurero>();
			listaAventurero = aventurerorepository.findByPoderContaining(poder);
  			return listaAventurero;
		}




			@Override
			public List<Aventurero> findByRelacionPiesas(Piesas piesas){
				List<Aventurero> listaAventurero = new ArrayList<Aventurero>();
				for (Aventurero aventurero : this.getAllAventurero()) {
					if(aventurero.getpiesas().equalsPiesas(piesas)){
						listaAventurero.add(aventurero);
					}
				}
				return listaAventurero;
			}

}
