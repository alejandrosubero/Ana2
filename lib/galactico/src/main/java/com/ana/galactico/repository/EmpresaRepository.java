
package com.ana.galactico.repository;

import java.util.Optional;
import org.springframework.data.repository.CrudRepository;
import com.ana.galactico.entitys.Empresa;


public interface EmpresaRepository extends CrudRepository< Empresa, Integer> {
 
		public Optional<Empresa> findById(Integer id);
		public Optional<Empresa> findByIdContaining(Integer id);
		public Optional<Empresa> findByNombre(String nombre);
		public Optional<Empresa> findByNombreContaining(String nombre);
		public Optional<Empresa> findBySucursal(String sucursal);
		public Optional<Empresa> findBySucursalContaining(String sucursal);

}
