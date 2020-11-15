package com.alejandro.ana.notas;

public enum Notas {
/*
==================================================================================================================
ERRORES ENONTRADOS:
==================================================================================================================
1) manejo de los errores, solo hay pocos metodos en el controller que manejan un error si ocurre que no se encuentra lo buscado
2) en la arquitectura basada a simple no maneja un ResponseEntity<EntityRespone>(entityRespone, HttpStatus.OK); ni de error
3) hay un error en el mappeo impor de ello en la arquitectura orientada a pojos.
4) en la arquitectura capa pojo hay respuestas del controler que no son manejadas con un Response pojo.
5) en ana code no maneja un ResponseEntity<EntityRespone>(entityRespone, HttpStatus.OK); ni de error
6) en las implementaciones hay metodos que retornan una nueva entidad si esta no se encuentra esta mal hay que ver como se maneja el error bien
sea con un objeto especial para ello o con una execcion o algo asi.

NOTA: el manejo debe de hacerse en la capa inferior o con un mapeo a respuesta que evalue los errores y debuelva una respuesta
o ver como pero no en el controler
propuesta de response:

public class EntityRespone implements Serializable {

   private String error;
   private String mensaje;
   private List<Object> entidades;
   private List<List<Object>> listOnentidades;

}  //nota: se puede manejar una clase con mensajes genericos o particulares que sean como unas constantes.

7) a un no hay un pomxml para java 1.7 ni hay un controller que se adacte a ello.

NOTA : MIRAR LOS PROYECTOS DE EMAILBACK Y LOS WEB QUE E BAJADO PARA VER LOS MANEJOS DE ESTE TIPO DE RESPUESTAS ANTES DE HACER ALGO MAS.

8) no permite crear proyecto limpios sin bases de datos o definir que solo se puede crear con este programa





==================================================================================================================
* NOTAS: PIESAS EN TRABAJO

* 2) HAY OTRO TEMA QUE ES INCORPORAR LAS CLASES DE SEGURIDAD
* 3) INCORPORAR UNA CLASE DE EMAIL PARA ENVIOS  NOTAS: ver el proyecto que me enviaron de email para ver como cargar adjuntos.
* para la incorporacion de mail son 6 clases a generar mas el app properitis, configuracion,
*
* * 6) INCORPORAR UNA CLASE DE EXCEL AL PROYECTO:
* esto son deos clases que se crearan una para lectura y otra para crear mas el add al pomxml
*
* 7) consulta http:
* 8) INCORPORAR CLASES DE CARGA Y DESCARGA DE ARCHIVOS
* 10 ) sacar la estadisticas de horas de trabjo.
* 11) definir las acciones del controller de administrador
*
*12) incorporar una pagina de index para el proyecto la cual se sirva con la nueva forma de bash que estoy haciendo
*13) tambien si se puede una version o adactacion a escritorio.
* 5)) mirar si se pude correr como escritorio
*
*
* PLAN PARA PUBLICAR EL PROYECTO:
*
* INCORPORAR LA SEGURIDAD DE LOGUEO Y TOKEN PARA LOS PROYECTOS
* INCORPORAR ROL  Y MOSTRAR PROYECTOS SEGUN PERFIL (CORREO Y CLAVE)
*
* +++++++++++++++++NOTA: AVERIGUAR EL TEMA DE UN CORREO DE VALIDACION COMO SE HACE.+++++++
*
* INCORPORAR DESCARGA DEL PROYECTO EN JSON PARA LOS CLIENTES n
* LIMITAR EL SWAGUER SOLO PARA EL CLIENTE Y NO PARA LOS CONTROLADORES DE ADMINISTRADOR
* SEPARAR EL CONTROLLER DE ADMINISTRACION FUERA DEL DE CLIENTE

* * AÑADIR VERIFICACION DEL NOMBRE DEL PROYECTO ENTRE LOS PROYECTOS DEL CLIENTE Y DAR OCCION DE RENOMBRAR O SOBRE ESCRIBIR
* INCORPORAR LOGICA PARA EDITAR PROYECTOS Y USAR LOS PROYECTOS CREADOS.

* * NOTA: SE PUEDE HACER QUE DEACUERDO AL USUARIO SE LEVANTEN EN MEMORIA HASTA QUE LA SECION FINALICE.

* * CREAR UN SERVICIO QUE RETORNE EL NUMERO DE PROYECTOS, USUARIOS, GLOBALMENTE.
* +++++++++++++++++++++++Propuesta para correos en ejecucion: USO DE HILOS++++++++++++++++++++++++
* INCORPORAR UNA TABLA DE AUDITORIA PARA LA CUAL SE ACTUALIZARA EN UN NUEVO CAMPO AL GENERAR PROYECTO
* ++++++++++++++++++++ IMPLEMENTAR UN POOL DE CONECXIONES PUEDE SER EN MEMORIA O FISICA APRENDER A HACERLO++++++++
*
* INCORPORAR UN MANEJO DE ERRORES QUE ENVIE LOS MAIL DE LOS ERRORES PRESENTES ASI SE PUEDE MONITOREAR LOR ERRORES
*
*
*============================================================================================================
*
* SE COLOCARA UN OBJETO TOOL PARA AGREGAR SERVIDORES Y OTRAS COSAS
*
* */
}

/*
*
@author: Nombre del desarrollador / Nombre autor o autores
@deprecated: Indica que el método y que no se recomienda su uso / Descripción
@param: Definición de un parámetro de un método, es requerido para todos los parámetros del método/ Nombre de parámetro y descripción
@return: Informa de lo que devuelve el método, no se aplica en constructores o métodos "void"/ Descripción del valor de retorno
@see: Asocia con otro método o clase / Referencia cruzada referencia (#método(); clase#método(); paquete.clase; paquete.clase#método()).
@version: Versión del método o clase.
*
*/

/*
*
/**
ejemplo
 * Esta clase define objetos que contienen tantos enteros aleatorios entre 0 y 1000 como se le definen al crear un objeto
 * @author: Mario R. Rancel
 * @version: 22/09/2016/A
 * @see <a href = "http://www.aprenderaprogramar.com" />  Didáctica en programación </a>
 */












/**
 *
 * // Declaración de un Map (un HashMap) con clave "Integer" y Valor "String". Las claves pueden ser de cualquier tipo de objetos, aunque los más utilizados como clave son los objetos predefinidos de Java como String, Integer, Double ... !!!!CUIDADO los Map no permiten datos atómicos
 * Map<Integer, String> nombreMap = new HashMap<Integer, String>();
 * nombreMap.size(); // Devuelve el numero de elementos del Map
 * nombreMap.isEmpty(); // Devuelve true si no hay elementos en el Map y false si si los hay
 * nombreMap.put(K clave, V valor); // Añade un elemento al Map
 * nombreMap.get(K clave); // Devuelve el valor de la clave que se le pasa como parámetro o 'null' si la clave no existe
 * nombreMap.clear(); // Borra todos los componentes del Map
 * nombreMap.remove(K clave); // Borra el par clave/valor de la clave que se le pasa como parámetro
 * nombreMap.containsKey(K clave); // Devuelve true si en el map hay una clave que coincide con K
 * nombreMap.containsValue(V valor); // Devuelve true si en el map hay un Valor que coincide con V
 * nombreMap.values(); // Devuelve una "Collection" con los valores del Map
 *
 * **/