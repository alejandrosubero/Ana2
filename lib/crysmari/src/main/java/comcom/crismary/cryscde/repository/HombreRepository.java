
package com.crysmari.cde.repository;

import java.util.List;import java.util.Date;

import java.util.Optional;
import org.springframework.data.repository.CrudRepository;
import com.crysmari.cde.entitys.Hombre;

public interface HombreRepository extends CrudRepository< Hombre, Long> {
 
		public Optional<Hombre> findByEdad(Integer edad);
		public List<Hombre> findByEdadContaining(Integer edad);

}
