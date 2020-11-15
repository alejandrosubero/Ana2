package com.alejandro.ana.modelo;


import com.alejandro.ana.core.Creador;
import com.alejandro.ana.notas.AnotacionesJava;
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
  
    
    private List<EntidadesPojo> toEntidad;
    private List<EntidadesPojo> toPojos;
    private AnotacionesJava anotacionesJava;
    
    
//    private StringBuilder sb1;
//    private StringBuilder sb2;
//    private StringBuilder sb3;
//    private StringBuilder sb4;
    
    protected static final Log logger = LogFactory.getLog(CreateMapper.class);
    
    public void initiarCreateMapper(ArchivoBaseDatosPojo archivo, Creador creadors) {
    	
    	
//    	this.sb1 = new StringBuilder("\r\n");
//    	this.sb2 = new StringBuilder("\r\n");
//    	this.sb3 = new StringBuilder("\r\n");
//    	this.sb4 = new StringBuilder("\r\n");
        toEntidad = new ArrayList<EntidadesPojo>();
        toPojos = new ArrayList<EntidadesPojo>();
        anotacionesJava = new AnotacionesJava();
    	
        this.entidades = archivo.getEntidades();
        this.proyectoName = archivo.getProyectoName();
        this.paquete = creadors.getPackageNames();
        this.creador = creadors;
        this.barra = creador.getBarra();
        this.anotacionesJava.activateAnotacionesJava(archivo);
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
            if (entidad.getIsEntity()) {
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
    }

    private StringBuilder metods(EntidadesPojo entidad) {

        StringBuilder sb = new StringBuilder("\r\n");
        logger.info("Create Mapper metodos  for Entity:  " + entidad.getNombreClase());
        try {
            Thread.sleep(relantizar);
           // sb1.append(this.anotacionesJava.creatNotaClase()+"\r\n");
            sb.append("\r\n");
            sb.append(this.createImport(entidad));
            Thread.sleep(relantizar);
            sb.append(this.createTituloClass(entidad));
            Thread.sleep(relantizar);
            sb.append(this.createEntityToPojo(entidad));
            Thread.sleep(relantizar);
            sb.append(this.createPojoToEntity(entidad));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        sb.append("}" + "\r\n");
      //  sb.append(AnotacionesJava.apacheSoftwareLicensed()+"\r\n");
        return sb;
    }

    private StringBuilder createImport(EntidadesPojo entidads) {

        StringBuilder sb1 = new StringBuilder();
        sb1.append(this.anotacionesJava.creatNotaClase()+"\r\n");
        sb1.append("package " + paquete + ".mapper;" + "\r\n");
        sb1.append("import " + paquete + ".entitys." + entidads.getNombreClase() + ";" + "\r\n");
        sb1.append("import " + paquete + ".pojo." + entidads.getNombreClase() + "Pojo"+";" + "\r\n");

        for (RelacionPojo relacion : entidads.getRelaciones()) {
            sb1.append("import " + paquete + "." + entidads.getPaquete() + "." + relacion.getNameClassRelacion() + ";" + "\r\n");
         //   String[] clavePojo = pojo.getNombreClase().split("Pojo");
            sb1.append("import " + paquete + ".pojo."+ relacion.getNameClassRelacion() +"Pojo"+ ";" + "\r\n");
        }
        sb1.append("import org.springframework.web.bind.annotation.*;" + "\r\n");
        sb1.append("import org.springframework.stereotype.Component;" + "\r\n");
        sb1.append("import org.springframework.beans.factory.annotation.Autowired;" + "\r\n");
        sb1.append("\r\n");
        sb1.append("import java.util.List;" + "\r\n");
        sb1.append( "import java.util.Date;"+"\r\n");
        sb1.append("import java.util.ArrayList;" + "\r\n");
        sb1.append("\r\n");
        return sb1;
    }

    private StringBuilder createTituloClass(EntidadesPojo entidad) {
        StringBuilder sb2 = new StringBuilder();
        sb2.append("    @Component\r\n");
        sb2.append("    public class " + entidad.getNombreClase() + "Mapper {\r\n");
        sb2.append("\r\n");

        for (RelacionPojo relacion : entidad.getRelaciones()) {
            String nombreMapper = (relacion.getNameClassRelacion() + "Mapper");
            sb2.append("      @Autowired" + "\r\n");
            sb2.append("      private " + nombreMapper+" "+ nombreMapper.toLowerCase() + ";" + "\r\n");
            sb2.append("\r\n");
        }
        return sb2;
    }


    private StringBuilder createEntityToPojo(EntidadesPojo entity) throws InterruptedException {
     
    	 StringBuilder sb3 = new StringBuilder();

            for (EntidadesPojo pojo : toPojos) {
                String[] clavePojo = pojo.getNombreClase().split("Pojo");

                if (entity.getNombreClase().equals(clavePojo[0])) {
                    sb3.append("        public " + entity.getNombreClase() + " PojoToEntity(" + pojo.getNombreClase() + " pojo) {" + "\r\n");
                    sb3.append( "           "+entity.getNombreClase() + " entity = new "+entity.getNombreClase()+"();" + "\r\n");

                    
                    if (entity.getAtributos().size() > 0) {
                        for (AtributoPojo atributo : entity.getAtributos()) {
                            String atributoNameSetGet = atributo.getAtributoName().substring(0, 1).toUpperCase()
                                    + atributo.getAtributoName().substring(1).toLowerCase();
                            String atrubutoObjeto = atributo.getAtributoName().toLowerCase();
                            sb3.append("          entity.set" + atributoNameSetGet + "(pojo.get" + atributoNameSetGet + "());" + "\r\n");
                        }
                    }
                    
                    
                    if (entity.getRelaciones().size() > 0) {
                        for (RelacionPojo relacion : entity.getRelaciones()) {
                            String relacionClase = relacion.getNameClassRelacion();
                            String relacionName = relacion.getNameRelacion();
                            sb3.append("        List<" + relacionClase + "> list" + relacionName + " = new ArrayList<" + relacionClase + ">();" + "\r\n");
                        }
                    }

                    
                    
                    if (entity.getRelaciones().size() > 0) {
                        for (RelacionPojo relacion : entity.getRelaciones()) {
                            String nombreMapper = (relacion.getNameClassRelacion() + "Mapper");

                            if (relacion.getRelation().equals("OneToOne") || relacion.getRelation().equals("ManyToOne")) {
                                sb3.append("        entity.set" + relacion.getNameRelacion() + "(" + nombreMapper.toLowerCase() + ".PojoToEntity(pojo.get"
                                        + relacion.getNameRelacion() + "()));" + "\r\n");
                            }

                            
                            if (relacion.getRelation().equals("OneToMany") || relacion.getRelation().equals("ManyToMany")) {
                                String relacionClase = relacion.getNameClassRelacion();
                                String relacionName = relacion.getNameRelacion();
                                sb3.append("        for (" + relacionClase + "Pojo" + " " + relacionName + "pojo" + " : pojo.get" + relacionName + "()) {" + "\r\n");
                                sb3.append("            list" + relacionName + ".add(" + nombreMapper.toLowerCase() + ".PojoToEntity(" + relacionName + "pojo" + "));" + "\r\n");
                                sb3.append("        }" + "\r\n");
                                sb3.append("        entity.set" + relacionName + "(" + "list" + relacionName + ");" + "\r\n");
                            }
                        }
                    }
                } // if (entity.getNombreClase().equals(clavePojo[0])) {
            }
        sb3.append("            return entity;" + "\r\n");
        sb3.append("        }" + "\r\n");
        sb3.append("\r\n");
        return sb3;
    }

//+++++++++++++++++++++++++++++++++++++++++++++++++++++
   // AHORA CREAR EL METODO DE ENTITY TO POJO
//++++++++++++++++++++++++++++++++++++++++++++++++++++


    private StringBuilder createPojoToEntity(EntidadesPojo entity) {
       
    	 StringBuilder sb4 = new StringBuilder();
       
        for (EntidadesPojo pojo : toPojos) {
        	
            String[] clavePojo = pojo.getNombreClase().split("Pojo");

            if (entity.getNombreClase().equals(clavePojo[0])) {
                sb4.append("\r\n");
                sb4.append("    public " + pojo.getNombreClase() + " entityToPojo(" + entity.getNombreClase() + " entity) {" + "\r\n");
                sb4.append("        "+ pojo.getNombreClase() + " pojo = new "+pojo.getNombreClase()+"();" + "\r\n");

                if (entity.getRelaciones().size() > 0) {
                    for (RelacionPojo relacion : pojo.getRelaciones()) {
                        String relacionClase = relacion.getNameClassRelacion();
                        String relacionNameList = relacion.getNameRelacion();
                        sb4.append("        List<" + relacionClase + "> list" + relacionNameList + " = new ArrayList<" + relacionClase + ">();" + "\r\n");
                    }
                }

                if (entity.getAtributos().size() > 0) {
                    for (AtributoPojo atributo : pojo.getAtributos()) {
                        String atributoNameSetGet = atributo.getAtributoName().substring(0, 1).toUpperCase()
                                + atributo.getAtributoName().substring(1).toLowerCase();
                        String atrubutoObjeto = atributo.getAtributoName().toLowerCase();
                        sb4.append("        pojo.set" + atributoNameSetGet + "(entity.get" + atributoNameSetGet + "());" + "\r\n");
                    }
                }
                
                sb4.append("\r\n");

                if (entity.getRelaciones().size() > 0) {
                    for (RelacionPojo relacion : pojo.getRelaciones()) {

                        String[] EntidadClase =  relacion.getNameClassRelacion().split("Pojo");
                        String relacionClase = EntidadClase[0];
                        String nombreMapper = (relacionClase + "Mapper");
                        String[] Entidadname =  relacion.getNameRelacion().split("Pojo");;
                        String relacionName = Entidadname[0];
                        String relacionNameList = relacion.getNameRelacion();

                        if (relacion.getRelation().equals("OneToOne") || relacion.getRelation().equals("ManyToOne") ) {
                            sb4.append("        pojo.set" + relacion.getNameRelacion() + "(" + nombreMapper.toLowerCase() + ".entityToPojo(entity.get"
                                    + relacion.getNameRelacion() + "()));" + "\r\n");
                        }

                        if (relacion.getRelation().equals("OneToMany") || relacion.getRelation().equals("ManyToMany")) {

                            sb4.append("        for (" + relacionClase + " " + relacionName + "entity" + " : entity.get" + relacionName + "()) {" + "\r\n");
                            sb4.append("            list" + relacionNameList + ".add(" + nombreMapper.toLowerCase() + ".entityToPojo(" + relacionName +"entity"+" ));" + "\r\n");
                            sb4.append("        }" + "\r\n");
                            sb4.append("        pojo.set" + relacionName + "(" + "list" + relacionNameList + ");" + "\r\n");
                        }
                    }
                }
            }
        }
        sb4.append("            return pojo;" + "\r\n");
        sb4.append("        }" + "\r\n");
        sb4.append("\r\n");
        return sb4;
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



/*
 *  private StringBuilder createPojoToEntitys(EntidadesPojo entity) {
    	
        StringBuilder sb3 = new StringBuilder();
       
        for (EntidadesPojo pojo : toPojos) {
        	
            String[] clavePojo = pojo.getNombreClase().split("Pojo");

            if (entity.getNombreClase().equals(clavePojo[0])) {
                sb3.append("\r\n");
                sb3.append("    public " + pojo.getNombreClase() + " entityToPojo(" + entity.getNombreClase() + " entity) {" + "\r\n");
                sb3.append("        "+ pojo.getNombreClase() + " pojo = new "+pojo.getNombreClase()+"();" + "\r\n");

//                if (entity.getRelaciones().size() > 0) {
//                    for (RelacionPojo relacion : pojo.getRelaciones()) {
//                        String relacionClase = relacion.getNameClassRelacion();
//                        String relacionNameList = relacion.getNameRelacion();
//                        sb3.append("        List<" + relacionClase + "> list" + relacionNameList + " = new ArrayList<" + relacionClase + ">();" + "\r\n");
//                    }
//                }

                if (entity.getAtributos().size() > 0) {
                    for (AtributoPojo atributo : pojo.getAtributos()) {
                        String atributoNameSetGet = atributo.getAtributoName().substring(0, 1).toUpperCase()
                                + atributo.getAtributoName().substring(1).toLowerCase();
                        String atrubutoObjeto = atributo.getAtributoName().toLowerCase();
                        sb3.append("        pojo.set" + atributoNameSetGet + "(entity.get" + atributoNameSetGet + "());" + "\r\n");
                    }
                }
                
                sb3.append("\r\n");

                if (entity.getRelaciones().size() > 0) {
                    for (RelacionPojo relacion : pojo.getRelaciones()) {

                        String[] EntidadClase =  relacion.getNameClassRelacion().split("Pojo");
                        String relacionClase = EntidadClase[0];
                        String nombreMapper = (relacionClase + "Mapper");
                        String[] Entidadname =  relacion.getNameRelacion().split("Pojo");;
                        String relacionName = Entidadname[0];
                        String relacionNameList = relacion.getNameRelacion();

                        if (relacion.getRelation().equals("OneToOne") || relacion.getRelation().equals("ManyToOne") ) {
                            sb3.append("        pojo.set" + relacion.getNameRelacion() + "(" + nombreMapper.toLowerCase() + ".entityToPojo(entity.get"
                                    + relacion.getNameRelacion() + "()));" + "\r\n");
                        }

                        if (relacion.getRelation().equals("OneToMany") || relacion.getRelation().equals("ManyToMany")) {

                            sb3.append("        for (" + relacionClase + " " + relacionName + "entity" + " : entity.get" + relacionName + "()) {" + "\r\n");
                            sb3.append("            list" + relacionNameList + ".add(" + nombreMapper.toLowerCase() + ".entityToPojo(" + relacionName +"entity"+" ));" + "\r\n");
                            sb3.append("        }" + "\r\n");
                            sb3.append("        pojo.set" + relacionName + "(" + "list" + relacionNameList + ");" + "\r\n");
                        }
                    }
                }
            }
            // sb3.append("            return pojo;" + "\r\n");
        }
        sb3.append("            return pojo;" + "\r\n");
        sb3.append("        }" + "\r\n");
        sb3.append("\r\n");
        return sb3;
    }*/
