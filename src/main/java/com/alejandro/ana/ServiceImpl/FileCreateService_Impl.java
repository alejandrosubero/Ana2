package com.alejandro.ana.ServiceImpl;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Service;

import com.alejandro.ana.core.Creador;
import com.alejandro.ana.services.FileCreateService;


@Service
public class FileCreateService_Impl implements FileCreateService{

	protected static final Log logger = LogFactory.getLog(FileCreateService_Impl .class);

	@Override
	public void crearArchivo(String direccion, String escrito, String nombreArchivo) {

		logger.info("Inicia crearArchivo");
		
		String carpetas = direccion;
		String archivos = "\\" + nombreArchivo;
		String contenido1 = escrito;
		File create_carpeta = new File(carpetas);
		File create_archivo = new File(carpetas + archivos);

		if (create_archivo.exists()) {
			logger.info("El archivo existe");
		} else {
			logger.info("el archivo"+nombreArchivo+" no existe pero se creara");
		//	logger.info("La carpeta "+create_carpeta+" es creada");
			create_carpeta.mkdirs();
			try {
				if (create_archivo.createNewFile()) {
					FileWriter fw = new FileWriter(create_archivo);
					BufferedWriter bw = new BufferedWriter(fw);
					bw.write(contenido1);
					bw.close();
					logger.info("el archivo"+nombreArchivo+" fue creado en: "+carpetas );
				} else {
					logger.error("**************** El archivo"+nombreArchivo+" no fue creado *************");
				}
			} catch (IOException e) {
				e.printStackTrace();
				logger.error("Ocurrio un error: "+ e);
				Logger.getLogger(Creador.class.getName()).log(Level.SEVERE, null, e);
			}
		}
	}
}
