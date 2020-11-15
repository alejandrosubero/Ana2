package com.alejandro.ana.modelo.java07;

import com.alejandro.ana.core.Creador;
import com.alejandro.ana.modelo.RepositoriesServices;
import com.alejandro.ana.notas.AnotacionesJava;
import com.alejandro.ana.pojos.ArchivoBaseDatosPojo;
import com.alejandro.ana.pojos.AtributoPojo;
import com.alejandro.ana.pojos.EntidadesPojo;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.context.annotation.Scope;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;


@Scope("singleton")
@Component
public class CreateRepositorie07 {

    // private String description;
    private String proyectoName;
    private String packageNames;
    private List<EntidadesPojo> entidades;
    private Creador creador;
    private String barra = "";
    private int relantizar = 100;
    private int relantizar2 = 300;
    private AnotacionesJava anotacionesJava = new AnotacionesJava();

    protected static final Log logger = LogFactory.getLog(RepositoriesServices.class);

    public void startCreacion(ArchivoBaseDatosPojo archivo, Creador creadors) {

        this.entidades = archivo.getEntidades();
        this.proyectoName = archivo.getProyectoName();
        this.packageNames = archivo.getPackageNames();
        this.creador = creadors;
        this.barra = creador.getBarra();
        this.anotacionesJava.activateAnotacionesJava(archivo);
        try {
            this.create();
        } catch (InterruptedException e) {
            logger.error(" ERROR : " + e);
            //	e.printStackTrace();
        }
    }


    private void create() throws InterruptedException {

        logger.info("inicia la creacion de la clase Repository");
        if (this.entidades.size() > 0) {
            for (EntidadesPojo entidad : this.entidades) {
                Thread.sleep(relantizar );
                if(entidad.getIsEntity()) {
                    logger.info("Inicia la creacion del Repository " + " "+ entidad.getNombreClase());
                    this.createRepository(entidad);
                }
            }
        }
    }


    private void createFileClass(String entidad_getNombreClase, String entidad_paquete, StringBuilder sb) throws InterruptedException {

        Thread.sleep(relantizar);
        String nameFile = entidad_getNombreClase + ".java";
        //	sb.append("}\r\n");
        String singleString = sb.toString();
        String direction = creador.getDireccionDeCarpeta() + proyectoName + barra + "src" + barra + "main" + barra
                + "java" + barra + creador.getCom() + barra + creador.getPackageNames1() + barra + creador.getArtifact()
                + barra + entidad_paquete;
        creador.crearArchivo(direction, singleString, nameFile);
        logger.info("Finalizo la creacion de CreateFileClass" + "  NOMBRE = " + entidad_getNombreClase);
    }



    private  void createRepository(EntidadesPojo entidad) throws InterruptedException {
        StringBuilder sb1 = new StringBuilder("\r\n");
        List <AtributoPojo> listAtributos = entidad.getAtributos();
        String nameOfClass =entidad.getNombreClase()+"Repository";
        logger.info("createRepository" + "  for Entity:  " + entidad.getNombreClase());
        String datoTipo = "";

        for (AtributoPojo atributoID : listAtributos) {
            if (atributoID.getsId()) {
                datoTipo = atributoID.getTipoDato();
            }
        }
        sb1.append(this.anotacionesJava.creatNotaClase() + "\r\n");
        sb1.append("package " + packageNames + ".repository;\r\n");
        sb1.append("\r\n");
        sb1.append("import java.util.List;"+"\r\n");
        sb1.append( "import java.util.Date;"+"\r\n");
        sb1.append("import org.springframework.data.repository.CrudRepository;"+"\r\n");
        sb1.append("import org.springframework.data.jpa.repository.JpaRepository;"+"\r\n");
        sb1.append("import org.springframework.data.jpa.repository.Query;"+"\r\n");
        sb1.append("import org.springframework.data.repository.query.Param;"+"\r\n");
        sb1.append("\r\n");
        sb1.append("import " + packageNames + "." + entidad.getPaquete() +"."+ entidad.getNombreClase() + ";"+"\r\n");

        sb1.append("\r\n");
        sb1.append("public interface "+nameOfClass+" extends  JpaRepository< " + entidad.getNombreClase() + ", "+ datoTipo + "> {\r\n ");
        sb1.append("\r\n");


        for (AtributoPojo atributos : listAtributos) {
            String cadenaOriginal = atributos.getAtributoName();
            String primeraLetra = cadenaOriginal.substring(0, 1).toUpperCase();
            String restoDeLaCadena = cadenaOriginal.substring(1);
            String atributoName = primeraLetra + restoDeLaCadena;

            if (atributos.getsId()){
                sb1.append("		public " + entidad.getNombreClase() + " findBy" + atributoName + "(" + atributos.getTipoDato() + " " + atributos.getAtributoName() + ");"+"\r\n");
                sb1.append("		public List<" + entidad.getNombreClase() + "> findBy"+atributoName+"Containing("+atributos.getTipoDato() + " " + atributos.getAtributoName()+");"+ "\r\n");
                sb1.append("\r\n");

                sb1.append("@Query(value = \"SELECT t FROM "+entidad.getNombreClase()+" t WHERE t.id =?1\")"+"\r\n");
                sb1.append(" public Persona findByIdQuery("+datoTipo+" id);"+"\r\n");
                sb1.append("\r\n");

            //	@Query("SELECT p FROM Product p WHERE p.name LIKE %?1%" + " OR p.brand LIKE %?1%" + " OR p.madein LIKE %?1%" + " OR CONCAT(p.price, '') LIKE %?1%")
                sb1.append(metodoSearch(entidad)+"\r\n");
            }
        }

        sb1.append("\r\n");
        sb1.append("}\r\n");
        sb1.append(AnotacionesJava.apacheSoftwareLicensed() + "\r\n");
        Thread.sleep(relantizar );
        this.createFileClass(nameOfClass, "repository", sb1);
    }



    private String metodoSearch(EntidadesPojo entidad) {
        StringBuilder search = new StringBuilder("@Query(value = \"SELECT p FROM "+ entidad.getNombreClase()+"p WHERE CONCAT(");
        List <AtributoPojo> listAtributos = entidad.getAtributos();
        List<String> atributosName = new ArrayList<String>();

        for (AtributoPojo atributos : listAtributos) {
            if (!atributos.getsId()) { atributosName.add(atributos.getAtributoName()); }
        }

        for (int i = 0; i < atributosName.size(); i++ ) {
            if(atributosName.size() > i){search.append(" p."+atributosName.get(i)+", ' '," );}
            if(atributosName.size() == i){search.append(" p."+atributosName.get(i)+") LIKE %?1%\")");}
        }
        search.append("public List<Persona> finBySearch(String keyword);"+"\r\n");
          //  @Query(value = "SELECT p FROM Persona p WHERE CONCAT(p.nombre, ' ', p.apellido, ' ', p.nacimiento, '' ,p.canciones) LIKE %?1%")
        // search.append("\r\n");
        return search.toString();
    }



}


//    private String metodo(StringBuilder sb, String nameOfClass, String numeral) {
//        sb.append("		if (fileOptional"+numeral+".isPresent()) {"+"\r\n");
//        sb.append("\r\n");
//        sb.append("		try {"+"\r\n");
//        sb.append("\r\n");
//        sb.append("	logger.info(\"the proyect be updated\");"+"\r\n");
//        sb.append("\r\n");
//        sb.append("		"+nameOfClass+" proyectoBDA"+numeral+" = fileOptional.get();"+"\r\n");
//        sb.append("\r\n");
//        sb.append("		return proyectoBDA"+numeral+"; "+"\r\n");
//        sb.append("		} catch (DataAccessException e) {  "+"\r\n");
//        sb.append("		logger.error(\" ERROR : \" + e); "+"\r\n");
//        sb.append("		}"+"\r\n");
//        sb.append("  	}else { "+"\r\n");
//        sb.append("		return new "+nameOfClass+"(); "+"\r\n");
//        sb.append("		}"+"\r\n");
//        sb.append("\r\n");
//        return sb.toString();
//    }
//
//
//    private String metodTrycath(StringBuilder sb, String operacion, String operacionElse ) {
//        sb.append("		try {"+"\r\n");
//        sb.append("\r\n");
//        sb.append(operacion);
//        sb.append("\r\n");
//        sb.append("		} catch (DataAccessException e) {"+"\r\n");
//        sb.append("		logger.error(\" ERROR : \" + e);"+"\r\n");
//        sb.append(operacionElse);
//        sb.append("\r\n");
//        return sb.toString();
//    }
//
//
//    private String metodoGeneric(StringBuilder sb, String nameOfClass, String numeral, String operacion, String operacionElse ) {
//
//        sb.append("		if (fileOptional"+numeral+".isPresent()) {"+"\r\n");
//        sb.append("\r\n");
//        sb.append("		try {"+"\r\n");
//        sb.append("\r\n");
//        sb.append("	logger.info(\"the proyect be updated\");"+"\r\n");
//        sb.append("\r\n");
//        sb.append("		"+nameOfClass+" proyectoBDA"+numeral+" = fileOptional"+numeral+".get();"+"\r\n");
//        sb.append("\r\n");
//        sb.append(operacion);
//        sb.append("		} catch (DataAccessException e) {  "+"\r\n");
//        sb.append("		logger.error(\" ERROR : \" + e); "+"\r\n");
//        sb.append("		}"+"\r\n");
//        sb.append("  	}else { "+"\r\n");
//        sb.append(operacionElse);
//        sb.append("		}"+"\r\n");
//        sb.append("\r\n");
//        return sb.toString();
//    }
// }
