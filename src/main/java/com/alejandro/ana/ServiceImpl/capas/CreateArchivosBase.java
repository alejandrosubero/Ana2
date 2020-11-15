package com.alejandro.ana.ServiceImpl.capas;

import com.alejandro.ana.core.Creador;
import com.alejandro.ana.modelo.ArchivosBase;
import com.alejandro.ana.modelo.PomxmlCreator;
import com.alejandro.ana.pojos.ArchivoBaseDatosPojo;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;


@Scope("singleton")
@Component
public class CreateArchivosBase {

    @Autowired
    private ArchivosBase archivosBase;

    @Autowired
    private PomxmlCreator pomxmlCreator;

    protected static final Log logger = LogFactory.getLog(CreateArchivosBase.class);


    public void StartCreateArchivosBase(ArchivoBaseDatosPojo archivo, Creador creador){

        logger.info("Creando Archivos Base");
        archivosBase.iniciarArchivosBase2(archivo, creador, 0);

        logger.info("iniciando pomxmlCreator.iniciarPomxml");
        pomxmlCreator.iniciarPomxml2(archivo, creador);
        archivosBase.iniciarArchivosBase2(archivo, creador, 1);

    }



}
