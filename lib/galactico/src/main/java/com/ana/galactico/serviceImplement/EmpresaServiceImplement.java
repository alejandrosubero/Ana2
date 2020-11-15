

package com.ana.galactico.serviceImplement ;

import com.ana.galactico.service.EmpresaService;

import com.ana.galactico.repository.EmpresaRepository;

import java.util.Optional;
import java.util.ArrayList;
import java.util.List;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import com.ana.galactico.entitys.Empresa;



@Service
public class EmpresaServiceImplement implements EmpresaService {
 

protected static final Log logger = LogFactory.getLog(EmpresaServiceImplement.class);

@Autowired
private EmpresaRepository empresarepository;



		@Override
		public Empresa findById(Integer id){
		logger.info("Starting getEmpresa");
			Empresa empresaEntity = new Empresa();
		Optional<Empresa> fileOptional1 = empresarepository.findById(id);
		if (fileOptional1.isPresent()) { 

				try {

			empresaEntity = fileOptional1.get();
				} catch (DataAccessException e) {
				logger.error(" ERROR : " + e);

				}


		}
		return empresaEntity;

	}

		@Override
		public Empresa findByNombre(String nombre){
		logger.info("Starting getEmpresa");
			Empresa empresaEntity = new Empresa();
		Optional<Empresa> fileOptional1 = empresarepository.findByNombre(nombre);
		if (fileOptional1.isPresent()) { 

				try {

			empresaEntity = fileOptional1.get();
				} catch (DataAccessException e) {
				logger.error(" ERROR : " + e);

				}


		}
		return empresaEntity;

	}

		@Override
		public Empresa findBySucursal(String sucursal){
		logger.info("Starting getEmpresa");
			Empresa empresaEntity = new Empresa();
		Optional<Empresa> fileOptional1 = empresarepository.findBySucursal(sucursal);
		if (fileOptional1.isPresent()) { 

				try {

			empresaEntity = fileOptional1.get();
				} catch (DataAccessException e) {
				logger.error(" ERROR : " + e);

				}


		}
		return empresaEntity;

	}




		@Override
		public List<Empresa> getAllEmpresa(){
		logger.info("Get allProyect");

 		Iterable<Empresa> iterator = empresarepository.findAll();
			List<Empresa> listaEmpresa = new ArrayList<Empresa>();

			for (Empresa emp : iterator ) {	
				listaEmpresa.add(emp);
			}

			return listaEmpresa;
		}



		@Override
		public boolean saveEmpresa(Empresa empresa){

		logger.info("Save Proyect");


				try {

		empresarepository.save(empresa);
		return true;

				} catch (DataAccessException e) {
				logger.error(" ERROR : " + e);
		return false;
				}

}


		@Override
		public boolean deleteEmpresa( Integer id){
		logger.info("Delete Proyect");

		boolean clave = false;



		for ( Empresa empre : empresarepository.findAll()) {
			if (empre.getId() == id) {

				try {

				empresarepository.delete(empre);
				clave = true;

				} catch (DataAccessException e) {
				logger.error(" ERROR : " + e);
				clave = false;
				}
			}
		}

		return clave;

	}



		@Override
		public boolean updateEmpresa( Integer id){
		logger.info("Update Proyect");

		boolean clave = false;



		for ( Empresa empre : empresarepository.findAll()) {
			if (empre.getId() == id) {

				try {

					empresarepository.save(empre);
					clave = true;

				} catch (DataAccessException e) {
				logger.error(" ERROR : " + e);
					clave = false;
				}
			}
		}

		return clave;

	}

}
