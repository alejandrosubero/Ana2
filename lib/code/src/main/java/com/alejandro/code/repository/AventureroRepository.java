
package com.alejandro.code.repository;

import java.util.List;
import java.util.Optional;
import org.springframework.data.repository.CrudRepository;
import com.alejandro.code.entitys.Aventurero;

public interface AventureroRepository extends CrudRepository< Aventurero, Long> {
 
		public Optional<Aventurero> findByNombre(String nombre);
		public List<Aventurero> findByNombreContaining(String nombre);
		public Optional<Aventurero> findByPoder(String poder);
		public List<Aventurero> findByPoderContaining(String poder);

}
