
package com.crismary.crys.repository;

import java.util.List;import java.util.Date;

import java.util.Optional;
import org.springframework.data.repository.CrudRepository;
import com.crismary.crys.entitys.Madre;

public interface MadreRepository extends CrudRepository< Madre, Long> {
 
		public Optional<Madre> findByNombre(String nombre);
		public List<Madre> findByNombreContaining(String nombre);

}
