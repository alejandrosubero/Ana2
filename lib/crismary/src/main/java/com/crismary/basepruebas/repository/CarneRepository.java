
package com.crismary.basepruebas.repository;

import java.util.List;import java.util.Date;

import java.util.Optional;
import org.springframework.data.repository.CrudRepository;
import com.crismary.basepruebas.entitys.Carne;

public interface CarneRepository extends CrudRepository< Carne, Long> {
 
		public Optional<Carne> findByNombre(String nombre);
		public List<Carne> findByNombreContaining(String nombre);

}
