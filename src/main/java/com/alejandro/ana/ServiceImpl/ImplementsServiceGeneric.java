package com.alejandro.ana.ServiceImpl;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class ImplementsServiceGeneric {

	
//	public boolean saveProyect(SalveProyect proyect);
//	public SalveProyect  findById (Long id);
//	public boolean deleteProyect(String name);
//	public List<saveProyect> findAllProyect(String name); 
	
	
	
	public <T> List<T> fromArrayToList(T[] a) {   
		
	    return Arrays.stream(a).collect(Collectors.toList());
	    
	}
	
	
	
	public boolean saveProyect( ) {
		
	return false;
	
	}
	
	
	
	
	
	
	
	
	
}


















