package com.alejandro.ana.modelo;

import com.alejandro.ana.core.Creador;
import com.alejandro.ana.notas.AnotacionesJava;
import com.alejandro.ana.pojos.ArchivoBaseDatosPojo;
import com.alejandro.ana.pojos.EntidadesPojo;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Random;

@Scope("singleton")
@Component
public class EntityResponseClass {

    private String proyectoName;
    private String paquete;
    private List<EntidadesPojo> entidades;
    private Creador creador;
    private String barra = "";
    private int relantizar = 100;
    private int relantizar2 = 200;
    private AnotacionesJava anotacionesJava = new AnotacionesJava();
    protected static final Log logger = LogFactory.getLog(CreateControlles.class);


    public void startCreateEntityResponseClass(ArchivoBaseDatosPojo archivo, Creador creadors) {
        this.entidades = archivo.getEntidades();
        this.proyectoName = archivo.getProyectoName();
        this.paquete = creadors.getPackageNames();
        this.creador = creadors;
        this.barra = creador.getBarra();
        this.anotacionesJava.activateAnotacionesJava(archivo);
        this.create();
    }

    private void create(){
        createMapper();
        createPojo();
    }

    private void createPojo(){
        String nameOfClass = "EntityRespone";
        String entidad_paquete = "pojo";
            try {
                    Thread.sleep(relantizar);
                    String escritos = metodPojo().toString();
                    Thread.sleep(relantizar);
                    createArchivoController(escritos, nameOfClass, entidad_paquete);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }


    private void createMapper(){
        String nameOfClass = "MapperEntityRespone";
        String entidad_paquete = "mapper";
            try {
                    Thread.sleep(relantizar);
                    String escritos = metodMapper().toString();
                    Thread.sleep(relantizar);
                    createArchivoController(escritos, nameOfClass, entidad_paquete);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }


    private void createArchivoController( String escrito, String nameOfClass, String entidad_paquete  ) {
        try {
            Thread.sleep(relantizar);
            String nombreArchivo = nameOfClass + ".java";
           // String entidad_paquete = "controller";
            String direction = creador.getDireccionDeCarpeta() + proyectoName + barra + "src" + barra + "main" + barra
                    + "java" + barra + creador.getCom() + barra + creador.getPackageNames1() + barra + creador.getArtifact()
                    + barra + entidad_paquete;
            Thread.sleep(relantizar2);
            creador.crearArchivo(direction, escrito, nombreArchivo);
        } catch (Exception e) {
            logger.error(e);
        }
    }

    private  StringBuilder metodPojo() {
        Random rand = new Random();
        long rand_lub1 = rand.nextLong();
        StringBuilder sb1 = new StringBuilder();
        sb1.append(this.anotacionesJava.creatNotaClase()+"\r\n");
        sb1.append("package " + paquete + ".pojo;" + "\r\n");
        sb1.append("\r\n");
        sb1.append("import java.util.Date;"+"\r\n");
        sb1.append("import java.io.Serializable;\n" +
        "import java.util.List;\n" +
        "\n" +
        "\n" +
        "public class EntityRespone implements Serializable {");

        sb1.append("\r\n");
        sb1.append("private static final long serialVersionUID = "+rand_lub1+"L;" + "\r\n");
        sb1.append("\r\n");
        sb1.append("\r\n");
        sb1.append("    private String error;\n" +
                "   private String mensaje;\n" +
                "   private List<Object> entidades;");
        sb1.append("\r\n");
        sb1.append("\r\n");
        sb1.append("\r\n");

        sb1.append(" public String getError() {\n" +
                "        return error;\n" +
                "    }");

        sb1.append("\r\n");
        sb1.append("\r\n");
        sb1.append("    public void setError(String error) {\n" +
                "        this.error = error;\n" +
                "    }\n" +
                "\n" + "\n" +
                "    public String getMensaje() {\n" +
                "        return mensaje;\n" +
                "    }\n" +
                "\n" + "\n" +
                "    public void setMensaje(String mensaje) {\n" +
                "        this.mensaje = mensaje;\n" +
                "    }\n" +
                "\n" + "\n" +
                "    public List<Object> getEntidades() {\n" +
                "        return entidades;\n" +
                "    }\n" +
                "\n" + "\n" +
                "    public void setEntidades(List<Object> entidades) {\n" +
                "        this.entidades = entidades;\n" +
                "    }");

        sb1.append("\r\n");
        sb1.append("}");
        sb1.append(AnotacionesJava.apacheSoftwareLicensed()+"\r\n");
        return sb1;
    }



    private  StringBuilder metodMapper() {

        StringBuilder sb2 = new StringBuilder();
        sb2.append(this.anotacionesJava.creatNotaClase()+"\r\n");
        sb2.append("package " + paquete + ".mapper;" + "\r\n");
        sb2.append("import " + paquete + ".pojo.EntityRespone;"+"\r\n");

        sb2.append("\r\n");
        sb2.append("\n" +
                "import org.springframework.stereotype.Component;\n" +
                "\n" +
                "import java.util.ArrayList;\n" +
                "import java.util.List;\n" +
                "\n" +
                "@Component\n" +
                "public class MapperEntityRespone {\n");

        sb2.append("\r\n");
        sb2.append("public <T> EntityRespone setEntityT(List<T> t) {\n" +
                "        EntityRespone entityRespone = new EntityRespone();\n" +
                "        List<Object> list = (List<Object>) t;\n" +
                "        entityRespone.setEntidades(list);\n" +
                "        return entityRespone;\n" +
                "    }");

        sb2.append("\r\n");
        sb2.append("public <T> EntityRespone setEntityTobj(T t ) {\n" +
                "        EntityRespone entityRespone = new EntityRespone();\n" +
                "        List<Object> ob = new ArrayList<>();\n" +
                "        Object objects = t;\n" +
                "       ob.add(objects);\n" +
                "       entityRespone.setEntidades(ob);\n" +
                "        return entityRespone;\n" +
                "    }");


        sb2.append("\r\n");
        sb2.append(" public <T> EntityRespone setEntityResponT(T t , String mensaje, String error) {\n" +
                "        EntityRespone entityRespone = new EntityRespone();\n" +
                "        List<Object> ob = new ArrayList<>();\n" +
                "        Object objects = t;\n" +
                "        ob.add(objects);\n" +
                "        entityRespone.setError(error);\n" +
                "        entityRespone.setMensaje(mensaje);\n" +
                "        entityRespone.setEntidades(ob);\n" +
                "        return entityRespone;\n" +
                "    }\n");


        sb2.append("\r\n");
        sb2.append("  public <T> EntityRespone setEntityResponseT(List<T> t, String mensaje, String error) {\n" +
                "        EntityRespone entityRespone = new EntityRespone();\n" +
                "        List<Object> objects = (List<Object>) t;\n" +
                "        entityRespone.setError(error);\n" +
                "        entityRespone.setMensaje(mensaje);\n" +
                "        entityRespone.setEntidades(objects);\n" +
                "        return entityRespone;\n" +
                "    }");

        sb2.append("\r\n");
        sb2.append("}");
        sb2.append("\r\n");
        sb2.append(AnotacionesJava.apacheSoftwareLicensed()+"\r\n");
        return sb2;
    }



}
