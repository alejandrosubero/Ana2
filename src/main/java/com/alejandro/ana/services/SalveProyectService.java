package com.alejandro.ana.services;

import java.util.Optional;

import com.alejandro.ana.entity.SalveProyect;

public interface SalveProyectService {
	public void saveProyectInternamente(SalveProyect proyect);
	public boolean saveProyect(SalveProyect proyect);
	public SalveProyect findByProyectoName(String name);
	public SalveProyect findByAutor(String autor);
	public  SalveProyect findByUser(String user);
	public SalveProyect  findById (Long id);
//	public boolean deleteProyect(String name);
}
