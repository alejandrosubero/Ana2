package com.alejandro.ana.modelo;


import com.alejandro.ana.core.Creador;
import com.alejandro.ana.pojos.ArchivoBaseDatosPojo;
import com.alejandro.ana.pojos.AtributoPojo;
import com.alejandro.ana.pojos.EntidadesPojo;
import com.alejandro.ana.pojos.RelacionPojo;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Scope("singleton")
@Component
public class CreateMapper {

    private String proyectoName;
    private String paquete;
    private List<EntidadesPojo> entidades;
    private Creador creador;
    private String barra = "";
    private int relantizar = 100;
    private int relantizar2 = 200;
    private String clave = "pojo";
    protected static final Log logger = LogFactory.getLog(CreateMapper.class);

    private List<EntidadesPojo> toEntidad = new ArrayList<>();
    private List<EntidadesPojo> toPojos = new ArrayList<>();


    public void initiarCreateMapper(ArchivoBaseDatosPojo archivo, Creador creadors) {
        this.entidades = archivo.getEntidades();
        this.proyectoName = archivo.getProyectoName();
        this.paquete = creadors.getPackageNames();
        this.creador = creadors;
        this.barra = creador.getBarra();
        this.separateEntidadToPojos(this.entidades);
        this.createMapper(this.entidades);
    }


    private void separateEntidadToPojos(List<EntidadesPojo> entidadesList) {
        for (EntidadesPojo entidad : entidadesList) {
            if (entidad.getPaquete().equals(clave) && !entidad.getIsEntity()) {
                toPojos.add(entidad);
            } else {
                toEntidad.add(entidad);
            }
        }
    }

    private void createMapper(List<EntidadesPojo> entidadesList) {

        for (EntidadesPojo entidad : entidadesList) {
            String nameOfClass = entidad.getNombreClase() + "Mapper";
            try {
                if (entidad.getIsEntity()) {
                    Thread.sleep(relantizar);
                    String escritos = metods(entidad).toString();
                    Thread.sleep(relantizar);
                    createArchivoController(escritos, nameOfClass);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private StringBuilder metods(EntidadesPojo entidad) {

        StringBuilder sb = new StringBuilder("\r\n");
        logger.info("Create Mapper metodos  for Entity:  " + entidad.getNombreClase());
        try {
            Thread.sleep(relantizar);
            sb.append("\r\n");
            sb.append(this.createImport(entidad));
            Thread.sleep(relantizar);
            sb.append(this.createTituloClass(entidad));
            Thread.sleep(relantizar);
            sb.append(this.createEntityToPojo(entidad));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        sb.append("}" + "\r\n");
        return sb;
    }

    private StringBuilder createImport(EntidadesPojo entidad) {

        StringBuilder sb1 = new StringBuilder();
        sb1.append("package " + paquete + ".mapper;" + "\r\n");
        sb1.append("import " + paquete + ".entitys." + entidad.getNombreClase() + ";" + "\r\n");
        sb1.append("import " + paquete + ".pojos." + entidad.getNombreClase() + ";" + "\r\n");
        for (RelacionPojo relacion : entidad.getRelaciones()) {
            sb1.append("import " + paquete + "." + entidad.getPaquete() + "." + relacion.getNameClassRelacion() + ";" + "\r\n");
        }
        // seguro que requiere mas logica para que genere los import bien

        sb1.append("import org.springframework.web.bind.annotation.*;" + "\r\n");
        sb1.append("import org.springframework.stereotype.Component;" + "\r\n");
        sb1.append("import org.springframework.beans.factory.annotation.Autowired;" + "\r\n");
        sb1.append("\r\n");
        sb1.append("import java.util.List;" + "\r\n");
        sb1.append("import java.util.ArrayList;" + "\r\n");
        sb1.append("\r\n");
        return sb1;
    }

    private StringBuilder createTituloClass(EntidadesPojo entidad) {
        StringBuilder sb2 = new StringBuilder();
        sb2.append("@Component\r\n");
        sb2.append("public class " + entidad.getNombreClase() + "Mapper {\r\n");
        sb2.append("\r\n");
        // se puede separar de este encabezado para generar adecuadamente en otro metodo
        for (RelacionPojo relacion : entidad.getRelaciones()) {
            sb2.append("@Autowired" + "\r\n");
            sb2.append("private " + entidad.getNombreClase() + entidad.getNombreClase().toLowerCase() + ";" + "\r\n");
            sb2.append("\r\n");
        }
//le falta mejorar esta logica no esta bien
        return sb2;
    }


    private StringBuilder createEntityToPojo(EntidadesPojo entidad) {
        StringBuilder sb3 = new StringBuilder();
        for (EntidadesPojo entity : toEntidad) {
            for (EntidadesPojo pojo : toPojos) {
                String[] clavePojo = pojo.getNombreClase().split("Pojo");
                if (entity.getNombreClase().equals(clavePojo[0])) {
                    sb3.append(" public " + entity.getNombreClase() + " PojoToEntity(" + entity.getNombreClase() + " entity, " + pojo.getNombreClase() + " Pojo) {");

                    if (entity.getRelaciones().size() > 0) {
                        for (RelacionPojo relacion : entity.getRelaciones()) {
                            String relacionClase = relacion.getNameClassRelacion();
                            String relacionName = relacion.getNameRelacion();
                            sb3.append("List<" + relacionClase + "> list" + relacionName + " = new ArrayList<" + relacionClase + ">();" + "\r\n");
                        }
                    }

                    if (entity.getAtributos().size() > 0) {
                        for (AtributoPojo atributo : entity.getAtributos()) {
                            String atributoName = atributo.getAtributoName().substring(0, 1).toUpperCase() + atributo.getAtributoName().substring(1);
                            String atrubutoObjeto = atributo.getAtributoName().toLowerCase();
                            sb3.append("entity.set" + atributoName + "(pojo.get" + atributoName + ");" + "\r\n");
                        }
                    }

                    if (entity.getRelaciones().size() > 0) {
                        for (RelacionPojo relacion : entity.getRelaciones()) {
                            String nombreMapper = (relacion.getNameClassRelacion() + "Mapper").toLowerCase();

                            if (relacion.getRelation().equals("OneToOne")) {
                                sb3.append("entity.set" + relacion.getNameRelacion() + "(" + nombreMapper + ".PojoToEntity(pojo.get"
                                        + relacion.getNameRelacion() + "()));" + "\r\n");
                            }

                            if (relacion.getRelation().equals("OneToMany") || relacion.getRelation().equals("ManyToOne")
                                    || relacion.getRelation().equals("ManyToMany")) {
                                String relacionClase = relacion.getNameClassRelacion();
                                String relacionName = relacion.getNameRelacion();
                                sb3.append(" for (" + relacionClase + " " + relacionName + "pojo" + " : pojo.get" + relacionName + "()) {" + "\r\n");
                                sb3.append("list" + relacionName + ".add(" + nombreMapper + ".PojoToEntity(" + relacionName + "pojo" + "));" + "\r\n");
                                sb3.append("}" + "\r\n");
                                sb3.append("entity.set" + relacionName + "(" + "list" + relacionName + ");" + "\r\n");
                            }
                        }
                    }
                }
            }
        }
        sb3.append("return entity;" + "\r\n");
        sb3.append(" }" + "\r\n");
        return sb3;
    }


    private void createArchivoController(String escrito, String nameOfClass) {
        try {
            Thread.sleep(relantizar);
            String nombreArchivo = nameOfClass + ".java";
            String entidad_paquete = "mapper";
            String direction = creador.getDireccionDeCarpeta() + proyectoName + barra + "src" + barra + "main" + barra
                    + "java" + barra + creador.getCom() + barra + creador.getPackageNames1() + barra + creador.getArtifact()
                    + barra + entidad_paquete;
            Thread.sleep(relantizar2);
            creador.crearArchivo(direction, escrito, nombreArchivo);
        } catch (Exception e) {
            logger.error(e);
        }
    }
}

