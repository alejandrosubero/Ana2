package com.alejandro.ana.services;

import com.alejandro.ana.pojos.ArchivoBaseDatosPojo;

public interface ServicioGenerarproyectoRest {

    public boolean ejecutaBase(ArchivoBaseDatosPojo archivo) throws Exception;

    public boolean generarBase07(ArchivoBaseDatosPojo archivoBaseDatosPojo);

}
