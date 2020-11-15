

package com.alejandro.code.serviceImplement ;

import com.alejandro.code.service.PiesasService;
import com.alejandro.code.repository.PiesasRepository;
import java.util.Optional;
import java.util.ArrayList;
import java.util.List;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import com.alejandro.code.entitys.Piesas;




@Service
public class PiesasServiceImplement implements PiesasService {

protected static final Log logger = LogFactory.getLog(PiesasServiceImplement.class);
@Autowired
private PiesasRepository piesasrepository;

		@Override
		public Piesas findByHumanos(String humanos){

		logger.info("Starting getPiesas");
			Piesas piesasEntity = new Piesas();
		Optional<Piesas> fileOptional1 = piesasrepository.findByHumanos(humanos);

		if (fileOptional1.isPresent()) { 

				try {
			piesasEntity = fileOptional1.get();
				} catch (DataAccessException e) {
				logger.error(" ERROR : " + e);

				}
		}
		return piesasEntity;	}
		@Override
		public Piesas findByOriginal(String original){

		logger.info("Starting getPiesas");
			Piesas piesasEntity = new Piesas();
		Optional<Piesas> fileOptional1 = piesasrepository.findByOriginal(original);

		if (fileOptional1.isPresent()) { 

				try {
			piesasEntity = fileOptional1.get();
				} catch (DataAccessException e) {
				logger.error(" ERROR : " + e);

				}
		}
		return piesasEntity;	}




		@Override
		public List<Piesas> getAllPiesas(){
		logger.info("Get allProyect");
			List<Piesas> listaPiesas = new ArrayList<Piesas>();
				piesasrepository.findAll().forEach(piesas -> listaPiesas.add(piesas));
			return listaPiesas;
}


		@Override
		public boolean savePiesas(Piesas piesas){
		logger.info("Save Proyect");


				try {
				piesasrepository.save(piesas);
				return true;
				} catch (DataAccessException e) {
				logger.error(" ERROR : " + e);
				return false;
				}
		}


		@Override
		public boolean deletePiesas( Long id){
		logger.info("Delete Proyect");
		boolean clave = false;


				try {
				piesasrepository.deleteById(id);
				clave = true;
				} catch (DataAccessException e) {
				logger.error(" ERROR : " + e);
				clave = false;
				}
		return clave;
	}



		@Override
		public boolean updatePiesas(Piesas  piesas ){
			logger.info("Update Proyect");
			boolean clave = false;
		Piesas empre = findById(piesas.getId());
			empre = piesas;

				try {
				piesasrepository.save(empre);
						clave = true;
				} catch (DataAccessException e) {
				logger.error(" ERROR : " + e);
				clave = false;
				}

					return clave;
	}



		@Override
		public Piesas findById( Long id){
				return  piesasrepository.findById(id).get();
		}



		@Override
		public boolean saveOrUpdatePiesas(Piesas  piesas ){
			logger.info("Update Proyect");
			boolean clave = false;
			logger.info("Starting getEmpresa");
			Optional<Piesas> fileOptional2 = piesasrepository.findById(piesas.getId());
			if (fileOptional2.isPresent()) { 
				clave = this.updatePiesas(piesas);
				logger.info(" is update");
			} else {
					clave = this.savePiesas(piesas);
					logger.info(" is save");
 				}
 		return clave;
		}


		@Override
		public List<Piesas> findByHumanosContaining(String humanos){
			logger.info("Get allProyect");
 			List<Piesas> listaPiesas = new ArrayList<Piesas>();
			listaPiesas = piesasrepository.findByHumanosContaining(humanos);
  			return listaPiesas;
		}

		@Override
		public List<Piesas> findByOriginalContaining(String original){
			logger.info("Get allProyect");
 			List<Piesas> listaPiesas = new ArrayList<Piesas>();
			listaPiesas = piesasrepository.findByOriginalContaining(original);
  			return listaPiesas;
		}




}
