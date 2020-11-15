
package com.alejandro.code.repository;

import java.util.List;
import java.util.Optional;
import org.springframework.data.repository.CrudRepository;
import com.alejandro.code.entitys.Piesas;

public interface PiesasRepository extends CrudRepository< Piesas, Long> {
 
		public Optional<Piesas> findByHumanos(String humanos);
		public List<Piesas> findByHumanosContaining(String humanos);
		public Optional<Piesas> findByOriginal(String original);
		public List<Piesas> findByOriginalContaining(String original);

}
