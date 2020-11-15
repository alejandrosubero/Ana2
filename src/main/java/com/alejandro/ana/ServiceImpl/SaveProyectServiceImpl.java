package com.alejandro.ana.ServiceImpl;

import java.util.Optional;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import com.alejandro.ana.entity.Proyecto;
import com.alejandro.ana.entity.SalveProyect;
import com.alejandro.ana.repositorio.SalveProyectRepository;
import com.alejandro.ana.services.SalveProyectService;

@Service
public class SaveProyectServiceImpl implements SalveProyectService {

	protected static final Log logger = LogFactory.getLog(SaveProyectServiceImpl.class);

	@Autowired
	private SalveProyectRepository salveProyectRepository;


	@Override
	public SalveProyect findByProyectoName(String name) {
		SalveProyect proyectoBDA = null;
		Optional<SalveProyect> fileOptional = salveProyectRepository.findByProyectoName(name);
		if (fileOptional.isPresent()) {
			 proyectoBDA = fileOptional.get();
		}
		return proyectoBDA;
	}

	@Override
	public SalveProyect findByAutor(String autor) {
		SalveProyect proyectoBDA = null;
		Optional<SalveProyect> fileOptional = salveProyectRepository.findByAutor(autor);
		if (fileOptional.isPresent()) {
			proyectoBDA = fileOptional.get();
		}
		return proyectoBDA;
	}

	@Override
	public SalveProyect findByUser(String user) {
		SalveProyect proyectoBDA = null;
		Optional<SalveProyect> fileOptional = salveProyectRepository.findByUser(user);
		if (fileOptional.isPresent()) {
			proyectoBDA = fileOptional.get();
		}
		return proyectoBDA;
	}



	@Override
	public void saveProyectInternamente(SalveProyect proyect) {
		logger.info("Starting saveProyectInternamente");
		 Optional<SalveProyect> fileOptional = salveProyectRepository.findByProyectoName(proyect.getProyectoName());
		if (fileOptional.isPresent()) {
			try {
				logger.info("the proyect be updated");
//				SalveProyect proyectoBDA = salveProyectRepository.findById(fileOptional.get().getId()).get();
				SalveProyect proyectoBDA = fileOptional.get();
				proyectoBDA.setEntidades(proyect.getEntidades());
				proyectoBDA.setAutor(proyect.getAutor());
				proyectoBDA.setProyectoName(proyect.getProyectoName());
				proyectoBDA.setDescription(proyect.getDescription());
				proyectoBDA.setPackageNames(proyect.getPackageNames());
				proyectoBDA.setUser(proyect.getUser());
				salveProyectRepository.save(proyectoBDA);
				logger.info("Save the Proyect");
			} catch (DataAccessException e) { logger.error(" ERROR : " + e); }
		} else {
			logger.info("No hay proyecto y se retorna un new proyecto");
			try {
				salveProyectRepository.save(proyect);
				logger.info("Save the Proyect");
			} catch (DataAccessException e) {
				logger.error(" ERROR : " + e);
			}
		}
	}


	@Override
	public boolean saveProyect(SalveProyect proyect){
		logger.info("Starting saveProyect");
		boolean resultado = false;
		Optional<SalveProyect> fileOptional = salveProyectRepository.findByProyectoName(proyect.getProyectoName());
		if (fileOptional.isPresent()) {
			try {
				logger.info("the proyect be updated");
				SalveProyect proyectoBDA = fileOptional.get();
				proyectoBDA.setEntidades(proyect.getEntidades());
				proyectoBDA.setAutor(proyect.getAutor());
				proyectoBDA.setProyectoName(proyect.getProyectoName());
				proyectoBDA.setDescription(proyect.getDescription());
				proyectoBDA.setPackageNames(proyect.getPackageNames());
				proyectoBDA.setUser(proyect.getUser());
				salveProyectRepository.save(proyectoBDA);
				logger.info("Save the Proyect");
				resultado = true;
			} catch (DataAccessException e) {
				logger.error(" ERROR : " + e);
			}
		} else {
			logger.info("No hay proyecto y se retorna un new proyecto");
			try {
				salveProyectRepository.save(proyect);
				logger.info("Save the Proyect");
				resultado = true;
			} catch (DataAccessException e) {
				logger.error(" ERROR : " + e);
			}
		}
		return resultado;
	}




	@Override
	public SalveProyect findById(Long id) {
		logger.info("ejecute  findById()");
		Optional<SalveProyect> fileOptional = salveProyectRepository.findById(id);
		if (fileOptional.isPresent()) {
			SalveProyect proyectoBDA = fileOptional.get();
			return proyectoBDA;
		}else {
			return new SalveProyect();
		}
	}
}



//	@Override
//	public boolean saveProyect(SalveProyect proyect) {
//		logger.info("Starting saveProyect()");
//		try {
//			salveProyectRepository.save(proyect);
//			return true;
//		} catch (DataAccessException e) {
//			logger.error(" ERROR : " + e);
//			return false;
//		}
//	}

















