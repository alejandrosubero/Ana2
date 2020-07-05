package com.alejandro.ana.ServiceImpl;

import java.util.List;
import java.util.Optional;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alejandro.ana.entity.Proyecto;
import com.alejandro.ana.modelo.AnadirCarpeta;
import com.alejandro.ana.repositorio.ProyectoRepository;
import com.alejandro.ana.services.ProyectService;

@Service
public class ProyectoServiceImpl implements ProyectService {

	@Autowired
	private ProyectoRepository proyectoRepository;
	
	protected static final Log logger = LogFactory.getLog(ProyectoServiceImpl.class);
	
	

	
	
	@Override
	public Proyecto findByName(String nombreProyecto) {
		logger.info("se ejecuta la busqueda del proyecto");
		
		Optional<Proyecto> fileOptional = proyectoRepository.findByName(nombreProyecto);
		
		if(fileOptional.isPresent()) {
			Proyecto file = fileOptional.get();
			logger.info("se ejecuta el envio del proyecto");
			return file;	
		}else {
			logger.info("No hay proyecto y se retorna un new proyecto");
			return new Proyecto();
		}
	}

	
	
	@Override
	public List<Proyecto> findByAll() {
		logger.info("se inicia la busqueda y retorno de todos los proyectos");
		return proyectoRepository.findAll(); 
	}

	@Override
	public boolean saveProyecto(Proyecto proyecto) {		
		try {
			logger.info("se salva el proyecto");
			proyectoRepository.save(proyecto);
			return true;
		} catch (Exception e) {	
			logger.error("ocurrio un error a salvar el proyecto" + e, e);
			return false;
			
			
		}
	}

	
	
	
	
}
