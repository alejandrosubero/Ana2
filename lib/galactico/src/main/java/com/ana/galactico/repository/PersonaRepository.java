
package com.ana.galactico.repository;

import java.util.Optional;
import org.springframework.data.repository.CrudRepository;
import com.ana.galactico.entitys.Persona;


public interface PersonaRepository extends CrudRepository< Persona, Integer> {
 
		public Optional<Persona> findById(Integer id);
		public Optional<Persona> findByIdContaining(Integer id);
		public Optional<Persona> findByNombre(String nombre);
		public Optional<Persona> findByNombreContaining(String nombre);
		public Optional<Persona> findByApellido(String apellido);
		public Optional<Persona> findByApellidoContaining(String apellido);

}
