
package com.ddd.www.service ;

import java.util.Optional;import java.util.Date;

import java.util.ArrayList;
import java.util.List;
import com.ddd.www.entitys.Sde;


public interface SdeService{
 
		public Sde findById(Long id);
		public boolean saveSde(Sde sde);
		public List<Sde> getAllSde();
		public boolean deleteSde(Long id);
		public boolean updateSde(Sde sde);
 		public boolean saveOrUpdateSde(Sde sde);

}
