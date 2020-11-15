package com.alejandro.ana.modelo.server;


import com.alejandro.ana.core.Creador;
import com.alejandro.ana.modelo.tool.CreateGetPostTool;
import com.alejandro.ana.notas.AnotacionesJava;
import com.alejandro.ana.pojos.ArchivoBaseDatosPojo;
import com.alejandro.ana.pojos.EntidadesPojo;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.List;

@Scope("singleton")
@Component
public class ClienteTCP {

    private String proyectoName;
    private String paquete;
    private List<EntidadesPojo> entidades;
    private Creador creador;
    private String barra = "";
    private int relantizar = 50;
    private int relantizar2 = 60;
    private Boolean isServer;
    private AnotacionesJava anotacionesJava = new AnotacionesJava();

    protected static final Log logger = LogFactory.getLog(CreateGetPostTool.class);

    public void startCreateServerSocketTCP(ArchivoBaseDatosPojo archivo, Creador creadors) {

        this.entidades = archivo.getEntidades();
        this.proyectoName = archivo.getProyectoName();
        this.paquete = creadors.getPackageNames();
        this.creador = creadors;
        this.barra = creador.getBarra();
        this.anotacionesJava.activateAnotacionesJava(archivo);
        this.createClientesocket();
    }

    private void createClientesocket(){
        String nameOfClass = "ClienteTCP";
        try {
            Thread.sleep(relantizar);
            String escritos = metods().toString();
            Thread.sleep(relantizar);
            createArchivoGetPostTool(escritos, nameOfClass);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void createArchivoGetPostTool( String escrito, String nameOfClass  ) {

        try {
            Thread.sleep(relantizar);
            String nombreArchivo = nameOfClass + ".java";
            String entidad_paquete = "serviceImplement";
            String direction = creador.getDireccionDeCarpeta() + proyectoName + barra + "src" + barra + "main" + barra
                    + "java" + barra + creador.getCom() + barra + creador.getPackageNames1() + barra + creador.getArtifact()
                    + barra + entidad_paquete;
            Thread.sleep(relantizar2);
            creador.crearArchivo(direction, escrito, nombreArchivo);
        } catch (Exception e) {
            logger.error(e);
        }
    }


    private StringBuilder metods() {
        StringBuilder sb = new StringBuilder("\r\n");
        logger.info("Create Server socket UDP metodos ");
        try {
            Thread.sleep(relantizar);
            sb.append(this.anotacionesJava.creatNotaClase() + "\r\n");
            sb.append("\r\n");
            sb.append(this.createImport());

            Thread.sleep(relantizar);
            sb.append("\r\n");
            sb.append(this.createTituloClass());
            sb.append("\r\n");

            Thread.sleep(relantizar);
            sb.append(this.createstartConnection());
            sb.append("\r\n");

            Thread.sleep(relantizar);
            sb.append(this.createsendMessage());
            sb.append("\r\n");

            Thread.sleep(relantizar);
            sb.append(this.createSop());
            sb.append("\r\n");
            sb.append(AnotacionesJava.apacheSoftwareLicensed() + "\r\n");
//
//            Thread.sleep(relantizar);
//            sb.append(this.createsendPosta());
//            sb.append("\r\n");

        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        //  sb.append("}"+"\r\n");
        return sb;
    }


    private  StringBuilder createImport() {
        StringBuilder sb1 = new StringBuilder();
        sb1.append("package " + paquete + ".serviceImplement;" + "\r\n");
        sb1.append("import com.google.gson.Gson;" + "\r\n");
        sb1.append("import org.slf4j.Logger;" + "\r\n");
        sb1.append("import org.slf4j.LoggerFactory;" + "\r\n");
        sb1.append("import java.net.*;" + "\r\n");
        sb1.append("import java.io.*;" + "\r\n");
        sb1.append("import org.apache.commons.logging.Log;" + "\r\n");
        sb1.append("import org.apache.commons.logging.LogFactory;" + "\r\n");
        sb1.append( "import java.util.Date;"+"\r\n");
        return sb1;
    }

    private  StringBuilder createTituloClass() {
        StringBuilder sb2 = new StringBuilder();

        sb2.append("" + "\r\n");
        //  sb2.append("@Component" + "\r\n");
        sb2.append(" public class ClienteTCP {" + "\r\n");
        sb2.append("" + "\r\n");
        sb2.append("    private static final Logger loger = LoggerFactory.getLogger(ClienteTCP.class);" + "\r\n");
        sb2.append("    private Socket clientSocket;" + "\r\n");
        sb2.append("    private PrintWriter out;" + "\r\n");
        sb2.append("    private BufferedReader in;" + "\r\n");
        sb2.append("" + "\r\n");
        sb2.append("" + "\r\n");
        return sb2;
    }

    private  StringBuilder createstartConnection() {
        StringBuilder sb3 = new StringBuilder();

        sb3.append(" public void startConnection(String ip, int port) {" + "\r\n");
        sb3.append("    loger.info(\"Iniciado el Cliente-TCP\");" + "\r\n");
        sb3.append("    try {" + "\r\n");
        sb3.append("    clientSocket = new Socket(ip, port);" + "\r\n");
        sb3.append("    out = new PrintWriter(clientSocket.getOutputStream(), true);" + "\r\n");
        sb3.append("    in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));" + "\r\n");
        sb3.append("" + "\r\n");
        sb3.append("    } catch (SocketException e, IOException e) {" + "\r\n");
        sb3.append("    e.printStackTrace(); " + "\r\n");
        sb3.append("    LOG.debug(\"Error when initializing connection\", e);" + "\r\n");
        sb3.append("    }" + "\r\n");
        sb3.append(" }" + "\r\n");
        sb3.append("" + "\r\n");
        return sb3;
    }



    private  StringBuilder createsendMessage() {
        StringBuilder sb4 = new StringBuilder();
        sb4.append("    public String sendMessage(String msg) {" + "\r\n");
        sb4.append("       try {" + "\r\n");
        sb4.append("          out.println(msg);" + "\r\n");
        sb4.append("          return in.readLine();" + "\r\n");
        sb4.append("         } catch (Exception e) {" + "\r\n");
        sb4.append("      return null;" + "\r\n");
        sb4.append("      }" + "\r\n");
        sb4.append("    }" + "\r\n");
        sb4.append("" + "\r\n");
        sb4.append("" + "\r\n");
        return sb4;
    }


    private  StringBuilder createSop() {
        StringBuilder sb5 = new StringBuilder();
        sb5.append("" + "\r\n");
        sb5.append("    public void stopConnection() {" + "\r\n");
        sb5.append("        try {" + "\r\n");
        sb5.append("         in.close();" + "\r\n");
        sb5.append("         out.close();" + "\r\n");
        sb5.append("         clientSocket.close();" + "\r\n");
        sb5.append("        } catch (IOException e) {" + "\r\n");
        sb5.append("    LOG.debug(\"error when closing\", e);" + "\r\n");
        sb5.append("            }" + "\r\n");
        sb5.append("    }" + "\r\n");
        sb5.append("}" + "\r\n");
        return sb5;
    }

//
//    private  StringBuilder createsendPosta() {
//        StringBuilder sb6 = new StringBuilder();
//        sb6.append("" + "\r\n");
//        sb6.append("" + "\r\n");
//        sb6.append("" + "\r\n");
//        sb6.append("" + "\r\n");
//        sb6.append("" + "\r\n");
//        sb6.append("" + "\r\n");
//        sb6.append("" + "\r\n");
//        sb6.append("" + "\r\n");
//        sb6.append("" + "\r\n");
//        sb6.append("" + "\r\n");
//        sb6.append("" + "\r\n");
//        sb6.append("" + "\r\n");
//        return sb6;
//    }


}
