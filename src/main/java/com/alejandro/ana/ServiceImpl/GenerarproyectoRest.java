package com.alejandro.ana.ServiceImpl;

import com.alejandro.ana.ServiceImpl.capas.CreateArchivosBase;
import com.alejandro.ana.ServiceImpl.capas.CreateCapaPojoForEntitys;
import com.alejandro.ana.ServiceImpl.capas.CreateClasesProyecto;
import com.alejandro.ana.ServiceImpl.capas.CreateClasesProyecto07;
import com.alejandro.ana.converter.ConvertEntityToPojo;
import com.alejandro.ana.core.Creador;
import com.alejandro.ana.modelo.AnadirCarpeta;
import com.alejandro.ana.pojos.ArchivoBaseDatosPojo;
import com.alejandro.ana.services.ServicioGenerarproyectoRest;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GenerarproyectoRest implements ServicioGenerarproyectoRest {

    protected static final Log logger = LogFactory.getLog(GenerarproyectoRest.class);

    @Autowired
    ConvertEntityToPojo convertEntityToPojo;

    @Autowired
    private Creador creador;

    @Autowired
    CreateArchivosBase createArchivosBase;

    @Autowired
    CreateClasesProyecto createClasesProyecto;

    @Autowired
    CreateCapaPojoForEntitys createCapaPojoForEntitys;

    @Autowired
    private AnadirCarpeta anadirCarpeta;

    @Autowired
    private CreateClasesProyecto07 createClasesProyecto07;

    // CAMBIAR LO QUE RETORNA Y MEJORAR EL MANEJO DE LOS ERRORES

    @Override
    public boolean ejecutaBase(ArchivoBaseDatosPojo archivo) {

        try {
          //  Boolean isToolGetPost = archivo.getToolClassPojo().getGetPostCreateTool();

            if (archivo.getCreateCapaPojoForEntitys()) {
                archivo.setEntidades(convertEntityToPojo.startConvertEntityToPojo(archivo));
            }
            creador.setDatos(archivo);
            createArchivosBase.StartCreateArchivosBase(archivo, creador);
            createClasesProyecto.StartCreateClasesProyecto(archivo, creador);
            createCapaPojoForEntitys.StartCreateCapaPojoForEntitys(archivo, creador);
            logger.info("Añadiendo proyecto a zip");
            anadirCarpeta.folderzip(creador.getProyectoName(), creador.getDireccionDeCarpeta(), creador.getProyectoName());
            logger.info("Finalizo el Añadido del proyecto a zip");
            logger.info("Guardando Proyecto, Limpiando Cahe");
            return anadirCarpeta.salveProyecto(creador.getDireccionDeCarpeta(), creador.getProyectoName());
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }


    @Override
    public boolean generarBase07(ArchivoBaseDatosPojo archivo) {
        try {
//            Boolean isToolGetPost = archivo.getToolClassPojo().getGetPostCreateTool();
            if (archivo.getCreateCapaPojoForEntitys()) {
                archivo.setEntidades(convertEntityToPojo.startConvertEntityToPojo(archivo));
            }
            creador.setDatos(archivo);
            createArchivosBase.StartCreateArchivosBase(archivo, creador);
            createClasesProyecto07.StartCreateClasesProyecto(archivo, creador);
            createCapaPojoForEntitys.StartCreateCapaPojoForEntitys(archivo, creador);
            logger.info("Añadiendo proyecto a zip");
            anadirCarpeta.folderzip(creador.getProyectoName(), creador.getDireccionDeCarpeta(), creador.getProyectoName());
            logger.info("Finalizo el Añadido del proyecto a zip");
            logger.info("Guardando Proyecto, Limpiando Cahe");
            return anadirCarpeta.salveProyecto(creador.getDireccionDeCarpeta(), creador.getProyectoName());
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }


}
