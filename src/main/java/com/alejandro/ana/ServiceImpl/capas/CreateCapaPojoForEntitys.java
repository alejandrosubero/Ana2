package com.alejandro.ana.ServiceImpl.capas;

import com.alejandro.ana.ServiceImpl.GenerarInstanciasServiceImpl;
import com.alejandro.ana.ServiceImpl.tool.CreateToolImpl;
import com.alejandro.ana.core.Creador;
import com.alejandro.ana.modelo.*;
import com.alejandro.ana.pojos.ArchivoBaseDatosPojo;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;


@Scope("singleton")
@Component
public class CreateCapaPojoForEntitys {

    @Autowired
    private CreateControllerCapaPojo createControllerCapaPojo;

    @Autowired
    private CreateMapper createMapper;

    @Autowired
    private CreateValidation createValidation;

    @Autowired
    private CreateToolImpl createTool;

    @Autowired
    private CreateControlles createControlles;

    @Autowired
    private EntityResponseClass entityResponseClass;

    protected static final Log logger = LogFactory.getLog(CreateCapaPojoForEntitys.class);

    public void StartCreateCapaPojoForEntitys(ArchivoBaseDatosPojo archivo, Creador creador){


        if(archivo.getCreateCapaPojoForEntitys()) {
            createControllerCapaPojo.startCreacionControlles(archivo, creador);
            createMapper.initiarCreateMapper(archivo, creador);
            createValidation.startCreacion(archivo, creador);
            entityResponseClass.startCreateEntityResponseClass(archivo, creador);
        }else {
            createControlles.startCreacionControlles(archivo, creador);
        }

        if(archivo.getIsToolActive()){
            createTool.inicioCreate(archivo, creador);
        }

        logger.info("Finalizo Creando Archivos de repositorios, servicios proyecto, mappers");
    }


}
