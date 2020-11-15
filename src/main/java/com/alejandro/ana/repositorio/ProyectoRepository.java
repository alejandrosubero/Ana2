package com.alejandro.ana.repositorio;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.alejandro.ana.entity.Proyecto;

public interface ProyectoRepository extends JpaRepository<Proyecto, Long> {

	public Optional<Proyecto> findByName(String name);
	
}
