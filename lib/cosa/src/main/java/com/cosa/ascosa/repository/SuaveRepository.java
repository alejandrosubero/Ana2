
package com.cosa.ascosa.repository;

import java.util.List;import java.util.Date;

import java.util.Optional;
import org.springframework.data.repository.CrudRepository;
import com.cosa.ascosa.entitys.Suave;

public interface SuaveRepository extends CrudRepository< Suave, Long> {
 
		public Optional<Suave> findBySi(Boolean si);
		public List<Suave> findBySiContaining(Boolean si);

}
