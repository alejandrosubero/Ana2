

package com.alejandro.code.serviceImplement ;

import com.alejandro.code.service.SombisService;
import com.alejandro.code.repository.SombisRepository;
import java.util.Optional;
import java.util.ArrayList;
import java.util.List;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import com.alejandro.code.entitys.Sombis;




@Service
public class SombisServiceImplement implements SombisService {

protected static final Log logger = LogFactory.getLog(SombisServiceImplement.class);
@Autowired
private SombisRepository sombisrepository;

		@Override
		public Sombis findByDia(String dia){

		logger.info("Starting getSombis");
			Sombis sombisEntity = new Sombis();
		Optional<Sombis> fileOptional1 = sombisrepository.findByDia(dia);

		if (fileOptional1.isPresent()) { 

				try {
			sombisEntity = fileOptional1.get();
				} catch (DataAccessException e) {
				logger.error(" ERROR : " + e);

				}
		}
		return sombisEntity;	}




		@Override
		public List<Sombis> getAllSombis(){
		logger.info("Get allProyect");
			List<Sombis> listaSombis = new ArrayList<Sombis>();
				sombisrepository.findAll().forEach(sombis -> listaSombis.add(sombis));
			return listaSombis;
}


		@Override
		public boolean saveSombis(Sombis sombis){
		logger.info("Save Proyect");


				try {
				sombisrepository.save(sombis);
				return true;
				} catch (DataAccessException e) {
				logger.error(" ERROR : " + e);
				return false;
				}
		}


		@Override
		public boolean deleteSombis( Long id){
		logger.info("Delete Proyect");
		boolean clave = false;


				try {
				sombisrepository.deleteById(id);
				clave = true;
				} catch (DataAccessException e) {
				logger.error(" ERROR : " + e);
				clave = false;
				}
		return clave;
	}



		@Override
		public boolean updateSombis(Sombis  sombis ){
			logger.info("Update Proyect");
			boolean clave = false;
		Sombis empre = findById(sombis.getId());
			empre = sombis;

				try {
				sombisrepository.save(empre);
						clave = true;
				} catch (DataAccessException e) {
				logger.error(" ERROR : " + e);
				clave = false;
				}

					return clave;
	}



		@Override
		public Sombis findById( Long id){
				return  sombisrepository.findById(id).get();
		}



		@Override
		public boolean saveOrUpdateSombis(Sombis  sombis ){
			logger.info("Update Proyect");
			boolean clave = false;
			logger.info("Starting getEmpresa");
			Optional<Sombis> fileOptional2 = sombisrepository.findById(sombis.getId());
			if (fileOptional2.isPresent()) { 
				clave = this.updateSombis(sombis);
				logger.info(" is update");
			} else {
					clave = this.saveSombis(sombis);
					logger.info(" is save");
 				}
 		return clave;
		}


		@Override
		public List<Sombis> findByDiaContaining(String dia){
			logger.info("Get allProyect");
 			List<Sombis> listaSombis = new ArrayList<Sombis>();
			listaSombis = sombisrepository.findByDiaContaining(dia);
  			return listaSombis;
		}




}
