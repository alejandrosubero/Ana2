
package com.crysmari.cde.repository;

import java.util.List;import java.util.Date;

import java.util.Optional;
import org.springframework.data.repository.CrudRepository;
import com.crysmari.cde.entitys.Mujer;

public interface MujerRepository extends CrudRepository< Mujer, Long> {
 
		public Optional<Mujer> findByEdad(Integer edad);
		public List<Mujer> findByEdadContaining(Integer edad);

}
