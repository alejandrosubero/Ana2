
package com.crismary.crys.repository;

import java.util.List;import java.util.Date;

import java.util.Optional;
import org.springframework.data.repository.CrudRepository;
import com.crismary.crys.entitys.Padre;

public interface PadreRepository extends CrudRepository< Padre, Long> {
 
		public Optional<Padre> findByNombre(String nombre);
		public List<Padre> findByNombreContaining(String nombre);

}
