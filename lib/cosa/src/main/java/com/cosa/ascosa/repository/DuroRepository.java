
package com.cosa.ascosa.repository;

import java.util.List;import java.util.Date;

import java.util.Optional;
import org.springframework.data.repository.CrudRepository;
import com.cosa.ascosa.entitys.Duro;

public interface DuroRepository extends CrudRepository< Duro, Long> {
 
		public Optional<Duro> findByCuento(Integer cuento);
		public List<Duro> findByCuentoContaining(Integer cuento);

}
