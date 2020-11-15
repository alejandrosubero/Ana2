package com.alejandro.ana.services;

import java.util.List;
import com.alejandro.ana.entity.Proyecto;


public interface ProyectService {

	public Proyecto findByName(String nombreProyecto);
	public List<Proyecto> findByAll();
	public boolean saveProyecto(Proyecto proyecto);
}
