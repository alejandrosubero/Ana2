
package com.alejandro.code.repository;

import java.util.List;
import java.util.Optional;
import org.springframework.data.repository.CrudRepository;
import com.alejandro.code.entitys.Sombis;

public interface SombisRepository extends CrudRepository< Sombis, Long> {
 
		public Optional<Sombis> findByDia(String dia);
		public List<Sombis> findByDiaContaining(String dia);

}
