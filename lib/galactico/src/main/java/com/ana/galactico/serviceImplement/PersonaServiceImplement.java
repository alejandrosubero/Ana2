

package com.ana.galactico.serviceImplement ;

import com.ana.galactico.service.PersonaService;

import com.ana.galactico.repository.PersonaRepository;

import java.util.Optional;
import java.util.ArrayList;
import java.util.List;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import com.ana.galactico.entitys.Persona;



@Service
public class PersonaServiceImplement implements PersonaService {
 

protected static final Log logger = LogFactory.getLog(PersonaServiceImplement.class);

@Autowired
private PersonaRepository personarepository;



		@Override
		public Persona findById(Integer id){
		logger.info("Starting getPersona");
			Persona personaEntity = new Persona();
		Optional<Persona> fileOptional1 = personarepository.findById(id);
		if (fileOptional1.isPresent()) { 

				try {

			personaEntity = fileOptional1.get();
				} catch (DataAccessException e) {
				logger.error(" ERROR : " + e);

				}


		}
		return personaEntity;

	}

		@Override
		public Persona findByNombre(String nombre){
		logger.info("Starting getPersona");
			Persona personaEntity = new Persona();
		Optional<Persona> fileOptional1 = personarepository.findByNombre(nombre);
		if (fileOptional1.isPresent()) { 

				try {

			personaEntity = fileOptional1.get();
				} catch (DataAccessException e) {
				logger.error(" ERROR : " + e);

				}


		}
		return personaEntity;

	}

		@Override
		public Persona findByApellido(String apellido){
		logger.info("Starting getPersona");
			Persona personaEntity = new Persona();
		Optional<Persona> fileOptional1 = personarepository.findByApellido(apellido);
		if (fileOptional1.isPresent()) { 

				try {

			personaEntity = fileOptional1.get();
				} catch (DataAccessException e) {
				logger.error(" ERROR : " + e);

				}


		}
		return personaEntity;

	}




		@Override
		public List<Persona> getAllPersona(){
		logger.info("Get allProyect");

 		Iterable<Persona> iterator = personarepository.findAll();
			List<Persona> listaPersona = new ArrayList<Persona>();

			for (Persona emp : iterator ) {	
				listaPersona.add(emp);
			}

			return listaPersona;
		}



		@Override
		public boolean savePersona(Persona persona){

		logger.info("Save Proyect");


				try {

		personarepository.save(persona);
		return true;

				} catch (DataAccessException e) {
				logger.error(" ERROR : " + e);
		return false;
				}

}


		@Override
		public boolean deletePersona( Integer id){
		logger.info("Delete Proyect");

		boolean clave = false;



		for ( Persona empre : personarepository.findAll()) {
			if (empre.getId() == id) {

				try {

				personarepository.delete(empre);
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
		public boolean updatePersona( Integer id){
		logger.info("Update Proyect");

		boolean clave = false;



		for ( Persona empre : personarepository.findAll()) {
			if (empre.getId() == id) {

				try {

					personarepository.save(empre);
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
