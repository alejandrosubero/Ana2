
package com.ana.galactico.service ;

import java.util.Optional;
import java.util.ArrayList;
import java.util.List;
import com.ana.galactico.entitys.Empresa;


public interface EmpresaService{
 
		public Empresa  findById(Integer id);
		public Empresa  findByNombre(String nombre);
		public Empresa  findBySucursal(String sucursal);


		public boolean saveEmpresa(Empresa empresa);

		public List<Empresa> getAllEmpresa();

		public boolean deleteEmpresa(Integer id);

		public boolean updateEmpresa(Integer id);
}
