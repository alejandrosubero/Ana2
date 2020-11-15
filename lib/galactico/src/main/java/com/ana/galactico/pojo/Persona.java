
package com.ana.galactico.pojo;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;



public class Persona implements Serializable {

private Integer id;


private String nombre;


private String apellido;


public Integer getId() { 
return id;
}
public void setId(Integer  id) {
this.id = id;
}
public String getNombre() { 
return nombre;
}
public void setNombre(String  nombre) {
this.nombre = nombre;
}
public String getApellido() { 
return apellido;
}
public void setApellido(String  apellido) {
this.apellido = apellido;
}
}
