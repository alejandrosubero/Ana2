package com.alejandro.ana.repositorio;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.alejandro.ana.entity.SalveProyect;


public interface SalveProyectRepository extends CrudRepository<SalveProyect, Long> {
	
	public Optional<SalveProyect> findByProyectoName(String name);
	public Optional<SalveProyect> findByAutor(String autor);
	public Optional<SalveProyect> findByUser(String user);
	public Optional<SalveProyect> findByProyectoNameContaining(String name);

}
