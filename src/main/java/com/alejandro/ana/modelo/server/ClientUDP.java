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

import java.io.IOException;
import java.net.*;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

@Scope("singleton")
@Component
public class ClientUDP {


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

    public void startCreateServerSocketUDP(ArchivoBaseDatosPojo archivo, Creador creadors) {

        this.entidades = archivo.getEntidades();
        this.proyectoName = archivo.getProyectoName();
        this.paquete = creadors.getPackageNames();
        this.creador = creadors;
        this.barra = creador.getBarra();
        this.anotacionesJava.activateAnotacionesJava(archivo);
        this.createClientesocket();
    }

    private void createClientesocket(){
        String nameOfClass = "ClientUDP";
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
        logger.info("Create cliente socket UDP metodos ");
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
            sb.append(this.createCostructor());
            sb.append("\r\n");
            sb.append(AnotacionesJava.apacheSoftwareLicensed() + "\r\n");

//            Thread.sleep(relantizar);
//            sb.append(this.createRun());
//            sb.append("\r\n");
////
//            Thread.sleep(relantizar);
//            sb.append(this.createThreadClass());
//            sb.append("\r\n");
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
        sb1.append("import java.io.BufferedReader;" + "\r\n");
        sb1.append("import org.apache.commons.logging.Log;" + "\r\n");
        sb1.append("import org.apache.commons.logging.LogFactory;" + "\r\n");
        sb1.append( "import java.util.Date;"+"\r\n");
        return sb1;
    }

    private  StringBuilder createTituloClass() {
        StringBuilder sb2 = new StringBuilder();

        sb2.append("" + "\r\n");
        //  sb2.append("@Component" + "\r\n");
        sb2.append(" public class ClientUDP extends Thread {" + "\r\n");
        sb2.append("" + "\r\n");
        sb2.append("    private static final Logger loger = LoggerFactory.getLogger(ClientUDP.class);" + "\r\n");
        sb2.append("    protected DatagramSocket socket = null;" + "\r\n");
        sb2.append("    private Integer portServer;" + "\r\n");
        sb2.append("" + "\r\n");
        sb2.append("    protected boolean running;" + "\r\n");
        sb2.append("    protected byte[] buf = new byte[1024];" + "\r\n");
        sb2.append("    protected byte[] buffer = new byte[1024];" + "\r\n");
        sb2.append("" + "\r\n");
        sb2.append("" + "\r\n");
        return sb2;
    }


    private  StringBuilder createCostructor() {
        StringBuilder sb3 = new StringBuilder();

        sb3.append(" public static void starmain(Integer portServer, String ip, String mensaje) {" + "\r\n");
        sb3.append("    loger.info(\"Iniciado el Cliente-TCP\");" + "\r\n");
        sb3.append("    try {" + "\r\n");
        sb3.append("    //Obtengo la localizacion de localhost  " + "\r\n");
        sb3.append("         InetAddress direccionServidor = InetAddress.getByName(ip);" + "\r\n");
        sb3.append("     //Creo el socket de UDP" + "\r\n");
        sb3.append("    DatagramSocket socketUDP = new DatagramSocket();" + "\r\n");
        // sb3.append("    String mensaje = \"¡Hola mundo desde el cliente!\";" + "\r\n");
        sb3.append("    //Convierto el mensaje a bytes" + "\r\n");
        sb3.append("    buffer = mensaje.getBytes();" + "\r\n");
        sb3.append("    //Creo un datagrama" + "\r\n");
        sb3.append("     DatagramPacket pregunta = new DatagramPacket(buffer, buffer.length, direccionServidor, portServer);" + "\r\n");
        sb3.append("    //Lo envio con send" + "\r\n");
        sb3.append("     socketUDP.send(pregunta);" + "\r\n");
        sb3.append("    //Preparo la respuesta" + "\r\n");
        sb3.append(" DatagramPacket peticion = new DatagramPacket(buffer, buffer.length);" + "\r\n");
        sb3.append("  //Recibo la respuesta" + "\r\n");
        sb3.append("    socketUDP.receive(peticion);" + "\r\n");

        sb3.append("//Cojo los datos que envia el servidor y lo muestro" + "\r\n");
        sb3.append("  String  mensajeserver = new String(peticion.getData());" + "\r\n");
        sb3.append("//cierro el socket" + "\r\n");
        sb3.append("  socketUDP.close();" + "\r\n");
        // sb3.append(" " + "\r\n");
        sb3.append("  } catch (SocketException ex) {" + "\r\n");
        sb3.append("Logger.getLogger(UdpCliente.class.getName()).log(Level.SEVERE, null, ex);" + "\r\n");
        sb3.append("} catch (UnknownHostException ex) {" + "\r\n");
        sb3.append("Logger.getLogger(UdpCliente.class.getName()).log(Level.SEVERE, null, ex);" + "\r\n");
        sb3.append("} catch (IOException ex) {" + "\r\n");
        sb3.append("Logger.getLogger(UdpCliente.class.getName()).log(Level.SEVERE, null, ex);" + "\r\n");
        sb3.append("    }" + "\r\n");
        sb3.append("    }" + "\r\n");
        sb3.append(" }" + "\r\n");
        sb3.append("" + "\r\n");
        sb3.append("" + "\r\n");
        return sb3;
    }


//
//private  StringBuilder createRun() {
//        StringBuilder sb4 = new StringBuilder();
//        sb4.append("" + "\r\n");
//        sb4.append("" + "\r\n");
//        sb4.append("" + "\r\n");
//        sb4.append("" + "\r\n");
//        sb4.append("" + "\r\n");
//        sb4.append("" + "\r\n");
//        sb4.append("" + "\r\n");
//        sb4.append("" + "\r\n");
//        sb4.append("" + "\r\n");
//        sb4.append("" + "\r\n");
//        sb4.append("" + "\r\n");
//        sb4.append("" + "\r\n");
//        sb4.append("" + "\r\n");
//        sb4.append("" + "\r\n");
//        sb4.append("" + "\r\n");
//        return sb4;
//    }


//    private  StringBuilder createThreadClass() {
//        StringBuilder sb5 = new StringBuilder();
//        sb5.append("" + "\r\n");
//        sb5.append("" + "\r\n");
//        sb5.append("" + "\r\n");
//        sb5.append("" + "\r\n");
//        sb5.append("" + "\r\n");
//        sb5.append("" + "\r\n");
//        sb5.append("" + "\r\n");
//        sb5.append("" + "\r\n");
//        sb5.append("" + "\r\n");
//        sb5.append("" + "\r\n");
//        return sb5;
//    }
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
