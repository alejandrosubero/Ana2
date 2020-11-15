
package com.ana.galactico.service ;

import java.util.Optional;
import java.util.ArrayList;
import java.util.List;
import com.ana.galactico.entitys.Persona;


public interface PersonaService{
 
		public Persona  findById(Integer id);
		public Persona  findByNombre(String nombre);
		public Persona  findByApellido(String apellido);


		public boolean savePersona(Persona persona);

		public List<Persona> getAllPersona();

		public boolean deletePersona(Integer id);

		public boolean updatePersona(Integer id);
}
