
package com.crismary.basepruebas.repository;

import java.util.List;import java.util.Date;

import java.util.Optional;
import org.springframework.data.repository.CrudRepository;
import com.crismary.basepruebas.entitys.Prueba;

public interface PruebaRepository extends CrudRepository< Prueba, Long> {
 
		public Optional<Prueba> findByTitulo(String titulo);
		public List<Prueba> findByTituloContaining(String titulo);

}
