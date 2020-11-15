
package com.crismary.crys.repository;

import java.util.List;import java.util.Date;

import java.util.Optional;
import org.springframework.data.repository.CrudRepository;
import com.crismary.crys.entitys.Hija;

public interface HijaRepository extends CrudRepository< Hija, Long> {
 
		public Optional<Hija> findByNombre(String nombre);
		public List<Hija> findByNombreContaining(String nombre);

}
