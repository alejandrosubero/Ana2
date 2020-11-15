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
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Scope("singleton")
@Component
public class CreateControlles {


    private String proyectoName;
    private String paquete;
    private List<EntidadesPojo> entidades;
    private Creador creador;
    private String barra = "";
    private int relantizar = 100;
    private int relantizar2 = 200;
    private AnotacionesJava anotacionesJava = new AnotacionesJava();

    protected static final Log logger = LogFactory.getLog(CreateControlles.class);


    public void startCreacionControlles(ArchivoBaseDatosPojo archivo, Creador creadors) {
        this.entidades = archivo.getEntidades();
        this.proyectoName = archivo.getProyectoName();
        this.paquete = creadors.getPackageNames();
        this.creador = creadors;
        this.barra = creador.getBarra();
        this.anotacionesJava.activateAnotacionesJava(archivo);
        this.createController(entidades);
    }

    private void createController(List<EntidadesPojo> entidadesList){

        for ( EntidadesPojo entidad: entidadesList ) {
            String nameOfClass = entidad.getNombreClase()+ "Controller";
            try {
                if(entidad.getIsEntity()) {
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
        logger.info("Create Controller metodos  for Entity:  " + entidad.getNombreClase());
        try {
            Thread.sleep(relantizar);
            sb.append(this.anotacionesJava.creatNotaClase()+"\r\n");
            sb.append("\r\n");
            sb.append(this.createImport(entidad));

            Thread.sleep(relantizar);
            sb.append("\r\n");
            sb.append(this.createTituloClass(entidad));
            sb.append("\r\n");

            Thread.sleep(relantizar);
            sb.append(this.createLoop(entidad));
            sb.append("\r\n");

            Thread.sleep(relantizar);
            sb.append(this.createfindId(entidad));
            sb.append("\r\n");

            Thread.sleep(relantizar);
            sb.append(this.createfindAll(entidad));
            sb.append("\r\n");

            Thread.sleep(relantizar);
            sb.append(this.createSalve(entidad));
            sb.append("\r\n");

            Thread.sleep(relantizar);
            sb.append(this.createUpdate(entidad));
            sb.append("\r\n");

            Thread.sleep(relantizar);
            sb.append(this.createsaveOrUpdate(entidad));
            sb.append("\r\n");

            if(entidad.getDelete()) {
                Thread.sleep(relantizar);
                sb.append(this.createDelete(entidad));
            }

            Thread.sleep(relantizar);
            sb.append(this.findByRelacion(entidad));

            Thread.sleep(relantizar);
            sb.append(this.findByRelacionNoBidirecional(entidad));

        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        sb.append("}"+"\r\n");
        sb.append(AnotacionesJava.apacheSoftwareLicensed()+"\r\n");
        return sb;
    }


    private void createArchivoController( String escrito, String nameOfClass  ) {
        try {
            Thread.sleep(relantizar);
            String nombreArchivo = nameOfClass + ".java";
            String entidad_paquete = "controller";
            String direction = creador.getDireccionDeCarpeta() + proyectoName + barra + "src" + barra + "main" + barra
                    + "java" + barra + creador.getCom() + barra + creador.getPackageNames1() + barra + creador.getArtifact()
                    + barra + entidad_paquete;

            Thread.sleep(relantizar2);
            creador.crearArchivo(direction, escrito, nombreArchivo);
        } catch (Exception e) {
            logger.error(e);
        }
    }



    private String idTipoDato(EntidadesPojo entidad){
        List<AtributoPojo> listAtributos = entidad.getAtributos();
        String datoTipo = "Integer";
        for (AtributoPojo atributoID : listAtributos) {
            if (atributoID.getsId()) {
                datoTipo = atributoID.getTipoDato();
            }
        }
        return datoTipo;
    }


    private  StringBuilder createImport(EntidadesPojo entidad) {

        StringBuilder sb1 = new StringBuilder();
            sb1.append("package " + paquete + ".controller;" + "\r\n");
            sb1.append("import "+paquete+".entitys."+entidad.getNombreClase()+";" + "\r\n");
            sb1.append("import "+paquete+".service."+entidad.getNombreClase()+"Service;"+ "\r\n");
            sb1.append("import org.springframework.web.bind.annotation.*;" + "\r\n");
            sb1.append("import org.springframework.beans.factory.annotation.Autowired;" + "\r\n");
            sb1.append( "import java.util.Date;"+"\r\n");
            sb1.append("\r\n");
            sb1.append("import java.util.List;"+"\r\n");
            for (RelacionPojo relacion : entidad.getRelaciones()) {
            sb1.append("import " + paquete + "." + entidad.getPaquete() + "." + relacion.getNameClassRelacion()+";" +"\r\n");
             }
            sb1.append("\r\n");
        return sb1;
    }


    private  StringBuilder createTituloClass(EntidadesPojo entidad){

    StringBuilder sb2 = new StringBuilder();
        sb2.append("@RestController\r\n");
        sb2.append("@CrossOrigin(origins = \"*\")\r\n");
        sb2.append("@RequestMapping(\"/"+entidad.getNombreClase().toLowerCase()+"\")"+ "\r\n");
        sb2.append("public class " + entidad.getNombreClase() + "Controller {\r\n");
        sb2.append("\r\n");
        sb2.append("@Autowired"+"\r\n");
        sb2.append( entidad.getNombreClase() + "Service "+ entidad.getNombreClase().toLowerCase() +"Service;"+"\r\n");
        sb2.append("\r\n");
        return sb2;
    }


    private StringBuilder createLoop(EntidadesPojo entidad){

        StringBuilder sb3 = new StringBuilder();
        List<AtributoPojo> listAtributos = entidad.getAtributos();
        List<AtributoPojo> listAtributo = entidad.getAtributos();

        for (AtributoPojo atributos : listAtributos) {
            String atributoName = atributos.getAtributoName().substring(0, 1).toUpperCase()
                                                + atributos.getAtributoName().substring(1);
            String atrubutoObjeto = atributos.getAtributoName().toLowerCase();
            if (!atributos.getsId()) {
                sb3.append("\r\n");
                sb3.append("        @GetMapping(\"/Get"+atrubutoObjeto+"/{"+atrubutoObjeto+"}\")" +"\r\n");
                sb3.append("        private "+entidad.getNombreClase()+" findBy"+atributoName+"(@PathVariable(\""+atrubutoObjeto+"\") "+atributos.getTipoDato()
                                                        +"  "+atrubutoObjeto+") {" +"\r\n");
                sb3.append("            return "+entidad.getNombreClase().toLowerCase()+"Service.findBy"+atributoName +"("+atrubutoObjeto+");"+"\r\n");
                sb3.append("        }"+"\r\n");
                }
        }

        for (AtributoPojo atributo : listAtributo) {
            String atributoName = atributo.getAtributoName().substring(0, 1).toUpperCase() + atributo.getAtributoName().substring(1);
            String atrubutoObjeto = atributo.getAtributoName().toLowerCase();
            if (!atributo.getsId()) {
                sb3.append("\r\n");
                sb3.append("        @GetMapping(\"/Get"+atrubutoObjeto+"contain/{"+atrubutoObjeto+"}\")" +"\r\n");
                sb3.append("        private List<"+entidad.getNombreClase()+"> findBy"+atributoName
                                                    +"Contain(@PathVariable(\""+atrubutoObjeto+"\") "+atributo.getTipoDato()
                                                            +"  "+atrubutoObjeto+") {" +"\r\n");
                sb3.append("            return "+entidad.getNombreClase().toLowerCase()+"Service.findBy"+atributoName
                                                        +"Containing("+atrubutoObjeto+");"+"\r\n");
                sb3.append("        }"+"\r\n");
            }
        }
        return sb3;
    }


    private StringBuilder createfindId(EntidadesPojo entidad){
        List<AtributoPojo> listAtributo = entidad.getAtributos();
        StringBuilder sb4 = new StringBuilder();
                sb4.append("\r\n");
                sb4.append("        @GetMapping(\"/Get" + entidad.getNombreClase() + "/{id}\")" + "\r\n");
                sb4.append("          private " + entidad.getNombreClase() + " findById" + "(@PathVariable(\"id\") " +idTipoDato(entidad) + " id) {" + "\r\n");
                sb4.append("            return " + entidad.getNombreClase().toLowerCase() + "Service.findById(id);" + "\r\n");
                sb4.append("          }" + "\r\n");
        return sb4;
    }



    private StringBuilder createfindAll(EntidadesPojo entidad){
        StringBuilder sb5 = new StringBuilder();
                sb5.append("\r\n");
                sb5.append("        @GetMapping(\"/GetAll" + entidad.getNombreClase() + "\")" + "\r\n");
                sb5.append("        private  List<" + entidad.getNombreClase() + "> getAll"+entidad.getNombreClase()+"(){" + "\r\n");
                sb5.append("            return " + entidad.getNombreClase().toLowerCase() + "Service.getAll" + entidad.getNombreClase() + "();}" + "\r\n");
                sb5.append("\r\n");
        return sb5;
    }


    private StringBuilder createSalve(EntidadesPojo entidad){
        StringBuilder sb6 = new StringBuilder();
                sb6.append("\r\n");
                sb6.append("        @PostMapping(\"/save\")" + "\r\n");
                sb6.append("        private Boolean  save" + entidad.getNombreClase() + "(@RequestBody "+entidad.getNombreClase()+" "+entidad.getNombreClase().toLowerCase() +"){ " + "\r\n");
                sb6.append("            return " + entidad.getNombreClase().toLowerCase() + "Service.save"
                                                    +entidad.getNombreClase()+ "("+entidad.getNombreClase().toLowerCase()+"); }" + "\r\n");
                sb6.append("\r\n");
                return sb6;
    }


    private StringBuilder findByRelacion(EntidadesPojo entidad){
        StringBuilder sb61 = new StringBuilder("\r\n");
        for (RelacionPojo relacion: entidad.getRelaciones()) {
//            if (relacion.getBidireccional()) {
            if (relacion.getRelation().equals("ManyToMany") || relacion.getRelation().equals("OneToMany")) {
                sb61.append("\r\n");
                sb61.append("        @PostMapping(\"/Get_" + relacion.getNameRelacion() + "_contain/\")" + "\r\n");
                sb61.append("        private List<" + entidad.getNombreClase() + "> findBy" + relacion.getNameClassRelacion() + "(@RequestBody " + relacion.getNameClassRelacion() + " " +  relacion.getNameClassRelacion().toLowerCase() + "){ " + "\r\n");
                sb61.append("            return " + entidad.getNombreClase().toLowerCase() + "Service.findBy" + relacion.getNameClassRelacion()
                        + "Containing(" + relacion.getNameClassRelacion().toLowerCase() + "); }" + "\r\n");
                sb61.append("\r\n");
            }
        }
        return sb61;
    }


    private StringBuilder findByRelacionNoBidirecional(EntidadesPojo entidad){
        StringBuilder sb61 = new StringBuilder("\r\n");
        for (RelacionPojo relacion: entidad.getRelaciones()) {
//            if (!relacion.getBidireccional()) {
                if (!relacion.getRelation().equals("ManyToMany") && !relacion.getRelation().equals("OneToMany")) {
                sb61.append("\r\n");
                sb61.append("        @PostMapping(\"/findRelacion\")" + "\r\n");
                sb61.append("        private List<" + entidad.getNombreClase() + "> findRelacion"+relacion.getNameClassRelacion()
                                                        +"(@RequestBody " + relacion.getNameClassRelacion()
                                                            + " " + relacion.getNameClassRelacion().toLowerCase()
                                                                + "){ " + "\r\n");
                sb61.append("            return " + entidad.getNombreClase().toLowerCase()
                                             + "Service.findByRelacion"+relacion.getNameClassRelacion()+"(" + relacion.getNameClassRelacion().toLowerCase()
                                                  + "); }" + "\r\n");
                sb61.append("\r\n");
            }
        }
        return sb61;
    }



    private StringBuilder createUpdate(EntidadesPojo entidad){
        StringBuilder sb7 = new StringBuilder();
        sb7.append("\r\n");
        sb7.append("        @PostMapping(\"/Update\")" + "\r\n");
        sb7.append("        private "+idTipoDato(entidad)+" Update" + entidad.getNombreClase() + "(@RequestBody "
                                                +entidad.getNombreClase()+" " +entidad.getNombreClase().toLowerCase()+"){ " + "\r\n");
        sb7.append("            "+entidad.getNombreClase().toLowerCase() + "Service.update" +entidad.getNombreClase()
                                                        + "("+entidad.getNombreClase().toLowerCase()+");"
                                                                + "\r\n");
        sb7.append("            return "+entidad.getNombreClase().toLowerCase()+".getId(); }"+"\r\n");
        return sb7;
    }


    private StringBuilder createsaveOrUpdate(EntidadesPojo entidad){

        StringBuilder sb8 = new StringBuilder();
        sb8.append("\r\n");
        sb8.append("        @PostMapping(\"/saveOrUpdate\")" + "\r\n");
        sb8.append("        private boolean saveOrUpdate" + entidad.getNombreClase()
                                                + "(@RequestBody "+entidad.getNombreClase()+" "
                                                    +entidad.getNombreClase().toLowerCase() +"){ " + "\r\n");
        sb8.append("            return " + entidad.getNombreClase().toLowerCase()
                                            + "Service.saveOrUpdate" +entidad.getNombreClase()
                                                    + "("+entidad.getNombreClase().toLowerCase()+"); }" + "\r\n");
        return sb8;
    }



    private StringBuilder createDelete(EntidadesPojo entidad){
        StringBuilder sb9 = new StringBuilder();
        sb9.append("\r\n");
        sb9.append("        @DeleteMapping(\"/delete" + entidad.getNombreClase() + "/{id}\")" + "\r\n");
        sb9.append("            private boolean delete" + entidad.getNombreClase() + "(@PathVariable(\"id\") "
                                                        +idTipoDato(entidad) + " id) {" + "\r\n");
        sb9.append("            return " + entidad.getNombreClase().toLowerCase() + "Service.delete"
                                                + entidad.getNombreClase() + "(id); }" + "\r\n");
        return sb9;
    }
}
