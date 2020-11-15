
package com.crismary.basepruebas.service ;

import java.util.Optional;import java.util.Date;

import java.util.ArrayList;
import java.util.List;
import com.crismary.basepruebas.entitys.Prueba;import com.crismary.basepruebas.entitys.Carne;



public interface PruebaService{
 
		public Prueba  findByTitulo(String titulo);

		public List<Prueba>  findByTituloContaining(String titulo);

		public Prueba findById(Long id);
		public boolean savePrueba(Prueba prueba);
		public List<Prueba> getAllPrueba();
		public boolean deletePrueba(Long id);
		public boolean updatePrueba(Prueba prueba);
 		public boolean saveOrUpdatePrueba(Prueba prueba);

		public List<Prueba>  findByRelacionCarne(Carne carne);
}
