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
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Scope("singleton")
@Component
public class CreateControllerCapaPojo {

    private String proyectoName;
    private String paquete;
    private List<EntidadesPojo> entidades;
    private Creador creador;
    private String barra = "";
    private int relantizar = 100;
    private int relantizar2 = 200;
    private String clave = "pojo";
    private List<EntidadesPojo> toPojos = new ArrayList<>();
    private List<EntidadesPojo> toEntidad = new ArrayList<>();


    protected static final Log logger = LogFactory.getLog(CreateControllerCapaPojo.class);


    public void startCreacionControlles(ArchivoBaseDatosPojo archivo, Creador creadors) {
        this.entidades = archivo.getEntidades();
        this.proyectoName = archivo.getProyectoName();
        this.paquete = creadors.getPackageNames();
        this.creador = creadors;
        this.barra = creador.getBarra();
        this.createController(entidades);
    }

    private void createController(List<EntidadesPojo> entidadesList){

        if (entidadesList.size() > 0) {
            try {
                Thread.sleep(relantizar);
                this.separateEntidadToPojos(entidadesList);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            for (EntidadesPojo entidad : entidadesList) {
                String nameOfClass = entidad.getNombreClase() + "Controller";
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


    private void separateEntidadToPojos(List<EntidadesPojo> entidadesList) {
        logger.info("Inicia la separacion de entidades");
        for (EntidadesPojo entidad : entidadesList) {
            if (entidad.getPaquete().equals(clave) && !entidad.getIsEntity()) {
                toPojos.add(entidad);
            } else {
                toEntidad.add(entidad);
            }
        }
    }


    private StringBuilder metods(EntidadesPojo entidad) {

        StringBuilder sb = new StringBuilder("\r\n");
        logger.info("Create Controller metodos  for Entity:  " + entidad.getNombreClase());

        try {
            Thread.sleep(relantizar);
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

            Thread.sleep(relantizar);
            sb.append(this.createDelete(entidad));

            Thread.sleep(relantizar);
            sb.append(this.findByRelacion(entidad));

            Thread.sleep(relantizar);
            sb.append(this.findByRelacionNoBidirecional(entidad));

        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        sb.append("}"+"\r\n");
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
        sb1.append("import "+paquete+".validation."+entidad.getNombreClase()+"Validation;" + "\r\n");
        sb1.append("import "+paquete+".mapper."+entidad.getNombreClase()+"Mapper;" + "\r\n");
        sb1.append("import "+paquete+".service."+entidad.getNombreClase()+"Service;"+ "\r\n");
        sb1.append("import "+paquete+".mapper.MapperEntityRespone;"+ "\r\n");
        sb1.append("import "+paquete+".pojo.EntityRespone;"+ "\r\n");
        sb1.append("import org.springframework.web.bind.annotation.*;" + "\r\n");
        sb1.append("import org.springframework.beans.factory.annotation.Autowired;" + "\r\n");
        sb1.append("import java.util.List;"+"\r\n");
        sb1.append("import org.springframework.dao.DataAccessException;"+"\r\n");
        sb1.append("import org.springframework.http.HttpStatus;"+"\r\n");
        sb1.append("import org.springframework.http.ResponseEntity;"+"\r\n");

        for (EntidadesPojo entidadPojo : toPojos) {
            String[] clavePojo = entidadPojo.getNombreClase().split("Pojo");
            if (entidad.getNombreClase().equals(clavePojo[0])) {
                sb1.append("import " + paquete + "." + entidadPojo.getPaquete()+"."+ entidadPojo.getNombreClase() + ";" + "\r\n");
            }
        }

        for (RelacionPojo relacion : entidad.getRelaciones()) {
            sb1.append("import " + paquete + "." + entidad.getPaquete() + "." + relacion.getNameClassRelacion()+";" +"\r\n");
            sb1.append("import "+paquete+".validation."+relacion.getNameClassRelacion()+"Validation;" + "\r\n");
            sb1.append("import "+paquete+".mapper."+relacion.getNameClassRelacion()+"Mapper;" + "\r\n");
        }

        for (int i=0; i<toPojos.size(); i++) {
            String[] clavePojo =toPojos.get(i).getNombreClase().split("Pojo");
            if (entidad.getNombreClase().equals(clavePojo[0])) {
                for ( RelacionPojo relacion : toPojos.get(i).getRelaciones()){
                    sb1.append("import " + paquete + "." + toPojos.get(i).getPaquete() + "." + relacion.getNameClassRelacion()+";" +"\r\n");
                }
            }
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
        sb2.append("    @Autowired"+"\r\n");
        sb2.append("    "+ entidad.getNombreClase() + "Service "+ entidad.getNombreClase().toLowerCase() +"Service;"+"\r\n");
        sb2.append("\r\n");

        sb2.append("    @Autowired"+"\r\n");
        sb2.append("    "+ entidad.getNombreClase() + "Validation "+ entidad.getNombreClase().toLowerCase() +"ValidationService;"+"\r\n");
        sb2.append("\r\n");

        sb2.append("    @Autowired"+"\r\n");
        sb2.append("   "+entidad.getNombreClase() + "Mapper "+ entidad.getNombreClase().toLowerCase() +"Mapper;"+"\r\n");
        sb2.append("\r\n");

        sb2.append("    @Autowired"+"\r\n");
        sb2.append("   MapperEntityRespone mapperEntityRespone;"+"\r\n");
        sb2.append("\r\n");


        for (RelacionPojo relacion : entidad.getRelaciones()) {
            sb2.append("    @Autowired"+"\r\n");
            sb2.append("    "+ relacion.getNameClassRelacion() + "Validation "+ relacion.getNameRelacion().toLowerCase() +"ValidationService;"+"\r\n");
            sb2.append("\r\n");

            sb2.append("    @Autowired"+"\r\n");
            sb2.append("    "+ relacion.getNameClassRelacion() + "Mapper "+ relacion.getNameRelacion().toLowerCase() +"Mapper;"+"\r\n");
            sb2.append("\r\n");
        }
        return sb2;
    }


    private StringBuilder createLoop(EntidadesPojo entidad){

        StringBuilder sb3 = new StringBuilder();
        List<AtributoPojo> listAtributos = entidad.getAtributos();
        List<AtributoPojo> listAtributo = entidad.getAtributos();
        String validationService = entidad.getNombreClase().toLowerCase() +"ValidationService";

        for (AtributoPojo atributos : listAtributos) {
            String atributoName = atributos.getAtributoName().substring(0, 1).toUpperCase()
                    + atributos.getAtributoName().substring(1);
            String atrubutoObjeto = atributos.getAtributoName().toLowerCase();

            if (!atributos.getsId()) {
                sb3.append("\r\n");
                sb3.append("        @GetMapping(\"/Get"+atrubutoObjeto+"/{"+atrubutoObjeto+"}\")" +"\r\n");
                sb3.append("        private  ResponseEntity<EntityRespone> findBy"+atributoName+"(@PathVariable(\""+atrubutoObjeto+"\") "+atributos.getTipoDato()
                        +"  "+atrubutoObjeto+") {" +"\r\n");
                sb3.append("        "+atributos.getTipoDato()+" busca = ("+atributos.getTipoDato()+") "+validationService+".validation("+atrubutoObjeto+");" + "\r\n");
                sb3.append("        try {"+"\r\n");
                sb3.append("                EntityRespone entityRespone = mapperEntityRespone.setEntityTobj("+entidad.getNombreClase().toLowerCase()+"Service.findBy"+atributoName +"(busca));"+"\r\n");
                sb3.append("                return new ResponseEntity<EntityRespone>(entityRespone, HttpStatus.OK);"+"\r\n");
                sb3.append("             } catch (DataAccessException e) {"+"\r\n");
                sb3.append("                 EntityRespone entityRespone = mapperEntityRespone.setEntityResponT(null, \"Ocurrio un error\", e.getMessage());"+"\r\n");
                sb3.append("             return new ResponseEntity<EntityRespone>(entityRespone, HttpStatus.BAD_REQUEST);"+"\r\n");
                sb3.append("        }"+"\r\n");
                sb3.append("     }"+"\r\n");
            }
        }

        for (AtributoPojo atributo : listAtributo) {
            String atributoName = atributo.getAtributoName().substring(0, 1).toUpperCase() + atributo.getAtributoName().substring(1);
            String atrubutoObjeto = atributo.getAtributoName().toLowerCase();
            if (!atributo.getsId()) {
                sb3.append("\r\n");
                sb3.append("        @GetMapping(\"/Get"+atrubutoObjeto+"contain/{"+atrubutoObjeto+"}\")" +"\r\n");
                sb3.append("        private ResponseEntity<EntityRespone> findBy"+atributoName+"Contain(@PathVariable(\""+atrubutoObjeto+"\") "+atributo.getTipoDato()
                        +"  "+atrubutoObjeto+") {" +"\r\n");
                sb3.append("              "+atributo.getTipoDato()+" busca = ("+atributo.getTipoDato()+") "+validationService+".validation("+atrubutoObjeto+");" + "\r\n");
                sb3.append("              EntityRespone entityRespone = mapperEntityRespone.setEntityT("
                        +entidad.getNombreClase().toLowerCase()+"Service.findBy"+atributoName +"Containing(busca));"+"\r\n");
                sb3.append("              return new ResponseEntity<EntityRespone>(entityRespone, HttpStatus.OK);"+"\r\n");
                sb3.append("        }"+"\r\n");
            }
        }
        return sb3;
    }


    private StringBuilder createfindId(EntidadesPojo entidad){
        String validationService = entidad.getNombreClase().toLowerCase() +"ValidationService";
        List<AtributoPojo> listAtributo = entidad.getAtributos();
        StringBuilder sb4 = new StringBuilder();
        sb4.append("\r\n");
        sb4.append("        @GetMapping(\"/Get" + entidad.getNombreClase() + "/{id}\")" + "\r\n");
        sb4.append("          private ResponseEntity<EntityRespone> findById" + "(@PathVariable(\"id\") String id) {" + "\r\n");
        sb4.append("          EntityRespone entityRespone = mapperEntityRespone.setEntityTobj("
                                                    + entidad.getNombreClase().toLowerCase() +"Service.findById("+validationService+".valida_id(id))); " + "\r\n");
        sb4.append("             return new ResponseEntity<EntityRespone>(entityRespone, HttpStatus.OK);" + "\r\n");
        sb4.append("          }" + "\r\n");
        return sb4;
    }

//    @GetMapping("/GetAventurero/{id}")
//    private ResponseEntity<EntityRespone> findById(@PathVariable("id") String id) {
//        EntityRespone entityRespone = mapperEntityRespone.setEntityTobj(aventureroService.findById(aventureroValidationService.valida_id(id)));
//        return new ResponseEntity<EntityRespone>(entityRespone, HttpStatus.OK);
//    }

    private StringBuilder createfindAll(EntidadesPojo entidad){
        StringBuilder sb5 = new StringBuilder();
        sb5.append("\r\n");
        sb5.append("        @GetMapping(\"/GetAll" + entidad.getNombreClase() + "\")" + "\r\n");
        sb5.append("        private  ResponseEntity<EntityRespone> getAll"+entidad.getNombreClase()+"(){" + "\r\n");
        sb5.append("        EntityRespone entityRespone = mapperEntityRespone.setEntityT("
                                                        + entidad.getNombreClase().toLowerCase() + "Service.getAll" + entidad.getNombreClase() + "());"+"\r\n");
        sb5.append("            return new ResponseEntity<EntityRespone>(entityRespone, HttpStatus.OK); }" + "\r\n");
        sb5.append("\r\n");
        return sb5;
    }

//    @GetMapping("/GetAllAventurero")
//    private  ResponseEntity<EntityRespone> getAllAventurero(){
//        EntityRespone entityRespone = mapperEntityRespone.setEntityT(aventureroService.getAllAventurero());
//        return new ResponseEntity<EntityRespone>(entityRespone, HttpStatus.OK);
//    }


    private StringBuilder createSalve(EntidadesPojo entidad){
        StringBuilder sb6 = new StringBuilder();
        String validationService = entidad.getNombreClase().toLowerCase() +"ValidationService";
        String mapperService =  entidad.getNombreClase().toLowerCase() +"Mapper";
        String contenido  = mapperService +".PojoToEntity("+validationService+".valida("+entidad.getNombreClase().toLowerCase()+"))";
        sb6.append("\r\n");
        sb6.append("        @PostMapping(\"/save\")" + "\r\n");
        sb6.append("        private Boolean  save" + entidad.getNombreClase() + "(@RequestBody "+entidad.getNombreClase()+"Pojo  "+entidad.getNombreClase().toLowerCase() +"){ " + "\r\n");
        sb6.append("            return " + entidad.getNombreClase().toLowerCase() + "Service.save"+entidad.getNombreClase()+ "("+contenido+" ); }" + "\r\n");
        sb6.append("\r\n");
        return sb6;
    }



    private StringBuilder createUpdate(EntidadesPojo entidad){
        String validationService = entidad.getNombreClase().toLowerCase() +"ValidationService";
        String mapperService =  entidad.getNombreClase().toLowerCase() +"Mapper";
        String contenido  = mapperService +".PojoToEntity("+validationService+".valida("+entidad.getNombreClase().toLowerCase()+"))";
        StringBuilder sb7 = new StringBuilder();
        sb7.append("\r\n");
        sb7.append("        @PostMapping(\"/Update\")" + "\r\n");
        sb7.append("        private "+idTipoDato(entidad)+" Update" + entidad.getNombreClase()
                               + "(@RequestBody "+entidad.getNombreClase()+"Pojo  "
                                   +entidad.getNombreClase().toLowerCase()+"){ " + "\r\n");
        sb7.append("        "+entidad.getNombreClase().toLowerCase()+"Service.update"+entidad.getNombreClase()+"("+contenido+");" + "\r\n");
        sb7.append("            return "+entidad.getNombreClase().toLowerCase()+".getId(); }"+"\r\n");
        return sb7;
    }


    private StringBuilder createsaveOrUpdate(EntidadesPojo entidad){

        String validationService = entidad.getNombreClase().toLowerCase() +"ValidationService";
        String mapperService =  entidad.getNombreClase().toLowerCase() +"Mapper";
        String contenido  = mapperService +".PojoToEntity("+validationService+".valida("+entidad.getNombreClase().toLowerCase()+"))";
        StringBuilder sb8 = new StringBuilder();
        sb8.append("\r\n");
        sb8.append("        @PostMapping(\"/saveOrUpdate\")" + "\r\n");
        sb8.append("        private boolean saveOrUpdate" + entidad.getNombreClase()+ "(@RequestBody "+entidad.getNombreClase()+"Pojo  "+entidad.getNombreClase().toLowerCase() +"){ " + "\r\n");
        sb8.append("            return " + entidad.getNombreClase().toLowerCase() + "Service.saveOrUpdate"+entidad.getNombreClase()+ "("+contenido+" ); }" + "\r\n");
        return sb8;
    }



    private StringBuilder createDelete(EntidadesPojo entidad){
        StringBuilder sb9 = new StringBuilder();
        String validationService = entidad.getNombreClase().toLowerCase() +"ValidationService";
        sb9.append("\r\n");
        sb9.append("        @DeleteMapping(\"/delete" + entidad.getNombreClase() + "/{id}\")" + "\r\n");
        sb9.append("            private boolean delete" + entidad.getNombreClase() + "(@PathVariable(\"id\") String id) {" + "\r\n");
        sb9.append("            return " + entidad.getNombreClase().toLowerCase() + "Service.delete"
                + entidad.getNombreClase() + "("+validationService+".valida_id(id)); }" + "\r\n");
        return sb9;
    }


    private StringBuilder findByRelacion(EntidadesPojo entidad){
        StringBuilder sb61 = new StringBuilder("\r\n");

        for (RelacionPojo relacion: entidad.getRelaciones()) {
            String validationService = relacion.getNameRelacion().toLowerCase() +"ValidationService";
            String mapperService = relacion.getNameRelacion().toLowerCase() +"Mapper";
            String contenido  = mapperService +".PojoToEntity("+validationService+".valida("+relacion.getNameRelacion().toLowerCase()+"))";

            if (relacion.getRelation().equals("ManyToMany") || relacion.getRelation().equals("OneToMany")) {
                sb61.append("\r\n");
                sb61.append("        @PostMapping(\"/Get_" + relacion.getNameRelacion() + "_contain/\")" + "\r\n");
                sb61.append("        private List<" + entidad.getNombreClase() + "> findBy" + relacion.getNameClassRelacion()
                               + "(@RequestBody " + relacion.getNameClassRelacion() + "Pojo  " +  relacion.getNameClassRelacion().toLowerCase() + "){ " + "\r\n");
                sb61.append("            return " + entidad.getNombreClase().toLowerCase() + "Service.findBy" + relacion.getNameClassRelacion()
                        + "Containing(" + contenido + "); }" + "\r\n");
                sb61.append("\r\n");
            }
        }
        return sb61;
    }

    private StringBuilder findByRelacionNoBidirecional(EntidadesPojo entidad){

        StringBuilder sb61 = new StringBuilder("\r\n");
        for (RelacionPojo relacion: entidad.getRelaciones()) {
            String validationService = relacion.getNameRelacion().toLowerCase() +"ValidationService";
            String mapperService = relacion.getNameRelacion().toLowerCase() +"Mapper";
            String contenido  = mapperService +".PojoToEntity("+validationService+".valida("+relacion.getNameRelacion().toLowerCase()+"))";
            if (!relacion.getRelation().equals("ManyToMany") && !relacion.getRelation().equals("OneToMany")) {
                sb61.append("\r\n");
                sb61.append("        @PostMapping(\"/find" + relacion.getNameRelacion() + "\")" + "\r\n");
                sb61.append("        private ResponseEntity<EntityRespone> findRelacion"+relacion.getNameClassRelacion()+"(@RequestBody "
                        + relacion.getNameClassRelacion()+ "Pojo " + relacion.getNameClassRelacion().toLowerCase()+ "){ " + "\r\n");
                sb61.append("           EntityRespone entityRespone = mapperEntityRespone.setEntityT("
                                         + entidad.getNombreClase().toLowerCase() + "Service.findByRelacion" +relacion.getNameClassRelacion()
                                                +"("+ relacion.getNameClassRelacion().toLowerCase()+"Mapper.PojoToEntity("
                                                      +relacion.getNameClassRelacion().toLowerCase()+"ValidationService.valida("
                                                          + relacion.getNameClassRelacion().toLowerCase() + "))));"+"\r\n") ;
                sb61.append("            return new ResponseEntity<EntityRespone>(entityRespone, HttpStatus.OK);" + "\r\n");
                sb61.append("}"+"\r\n");
            }
        }
        return sb61;
    }

}
// xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx
/*
    @PostMapping("/findRelacionPiesas")
    private ResponseEntity<EntityRespone> findRelacionPiesas(@RequestBody PiesasPojo piesas) {
        EntityRespone entityRespone = mapperEntityRespone.setEntityT(aventureroService.findByRelacionPiesas(piesasMapper.PojoToEntity(piesasValidationService.valida(piesas))));
        return new ResponseEntity<EntityRespone>(entityRespone, HttpStatus.OK);
    }

*/
/*
    private StringBuilder findByRelacionNoBidirecional(EntidadesPojo entidad){

        StringBuilder sb61 = new StringBuilder("\r\n");
        for (RelacionPojo relacion: entidad.getRelaciones()) {

            String validationService = relacion.getNameRelacion().toLowerCase() +"ValidationService";
            String mapperService = relacion.getNameRelacion().toLowerCase() +"Mapper";
            String contenido  = mapperService +".PojoToEntity("+validationService+".valida("+relacion.getNameRelacion().toLowerCase()+"))";

            if (!relacion.getRelation().equals("ManyToMany") && !relacion.getRelation().equals("OneToMany")) {
                sb61.append("\r\n");
                sb61.append("        @PostMapping(\"/find" + relacion.getNameRelacion() + "\")" + "\r\n");
                // sb61.append("        @PostMapping(\"/findRelacion\")" + "\r\n");

                sb61.append("        private List<" + entidad.getNombreClase() + "> findRelacion"+relacion.getNameClassRelacion()+"(@RequestBody "
                        + relacion.getNameClassRelacion()+ "Pojo " + relacion.getNameClassRelacion().toLowerCase()+ "){ " + "\r\n");
                sb61.append("            return " + entidad.getNombreClase().toLowerCase()+ "Service.findByRelacion"
                        +relacion.getNameClassRelacion()+"(" + contenido + "); }" + "\r\n");
                sb61.append("\r\n");
            }
        }
        return sb61;
    }

*/


//    private StringBuilder createLoop(EntidadesPojo entidad){
//
//        StringBuilder sb3 = new StringBuilder();
//        List<AtributoPojo> listAtributos = entidad.getAtributos();
//        List<AtributoPojo> listAtributo = entidad.getAtributos();
//        String validationService = entidad.getNombreClase().toLowerCase() +"ValidationService";
//
//        for (AtributoPojo atributos : listAtributos) {
//            String atributoName = atributos.getAtributoName().substring(0, 1).toUpperCase()
//                    + atributos.getAtributoName().substring(1);
//            String atrubutoObjeto = atributos.getAtributoName().toLowerCase();
//
//            if (!atributos.getsId()) {
//                sb3.append("\r\n");
//                sb3.append("        @GetMapping(\"/Get"+atrubutoObjeto+"/{"+atrubutoObjeto+"}\")" +"\r\n");
//                sb3.append("        private "+entidad.getNombreClase()+" findBy"+atributoName+"(@PathVariable(\""+atrubutoObjeto+"\") "+atributos.getTipoDato()
//                        +"  "+atrubutoObjeto+") {" +"\r\n");
//                // String busca = (String) poderValidationService.validation(tipo);
//                sb3.append("        "+atributos.getTipoDato()+" busca = ("+atributos.getTipoDato()+") "+validationService+".validation("+atrubutoObjeto+");" + "\r\n");
//                sb3.append("            return "+entidad.getNombreClase().toLowerCase()+"Service.findBy"+atributoName +"(busca);"+"\r\n");
//                sb3.append("        }"+"\r\n");
//            }
//        }
//
//        for (AtributoPojo atributo : listAtributo) {
//
//            String atributoName = atributo.getAtributoName().substring(0, 1).toUpperCase() + atributo.getAtributoName().substring(1);
//            String atrubutoObjeto = atributo.getAtributoName().toLowerCase();
//
//            if (!atributo.getsId()) {
//                sb3.append("\r\n");
//                sb3.append("        @GetMapping(\"/Get"+atrubutoObjeto+"contain/{"+atrubutoObjeto+"}\")" +"\r\n");
//                sb3.append("        private List<"+entidad.getNombreClase()+"> findBy"+atributoName+"Contain(@PathVariable(\""+atrubutoObjeto+"\") "+atributo.getTipoDato()
//                        +"  "+atrubutoObjeto+") {" +"\r\n");
//                sb3.append("        "+atributo.getTipoDato()+" busca = ("+atributo.getTipoDato()+") "+validationService+".validation("+atrubutoObjeto+");" + "\r\n");
//                sb3.append("            return "+entidad.getNombreClase().toLowerCase()+"Service.findBy"+atributoName
//                        +"Containing(busca);"+"\r\n");
//                sb3.append("        }"+"\r\n");
//            }
//        }
//        return sb3;
//    }

//    private StringBuilder createfindId(EntidadesPojo entidad){
//        String validationService = entidad.getNombreClase().toLowerCase() +"ValidationService";
//        List<AtributoPojo> listAtributo = entidad.getAtributos();
//        StringBuilder sb4 = new StringBuilder();
//        sb4.append("\r\n");
//        sb4.append("        @GetMapping(\"/Get" + entidad.getNombreClase() + "/{id}\")" + "\r\n");
//        sb4.append("          private " + entidad.getNombreClase() + " findById" + "(@PathVariable(\"id\") String id) {" + "\r\n");
//        sb4.append("            return " + entidad.getNombreClase().toLowerCase() +"Service.findById("+validationService+".valida_id(id));" + "\r\n");
//        //  sb4.append("            return " + entidad.getNombreClase().toLowerCase() + "Service.findById(id);" + "\r\n");
//        sb4.append("          }" + "\r\n");
//        return sb4;
//    }


//    private StringBuilder createfindAll(EntidadesPojo entidad){
//        StringBuilder sb5 = new StringBuilder();
//        sb5.append("\r\n");
//        sb5.append("        @GetMapping(\"/GetAll" + entidad.getNombreClase() + "\")" + "\r\n");
//        sb5.append("        private  List<" + entidad.getNombreClase() + "> getAll"+entidad.getNombreClase()+"(){" + "\r\n");
//        sb5.append("            return " + entidad.getNombreClase().toLowerCase() + "Service.getAll" + entidad.getNombreClase() + "();}" + "\r\n");
//        sb5.append("\r\n");
//        return sb5;
//    }