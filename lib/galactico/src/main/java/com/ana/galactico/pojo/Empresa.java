
package com.ana.galactico.pojo;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.ana.galactico.pojo.Persona;


public class Empresa implements Serializable {

private Integer id;


private String nombre;


private String sucursal;


private Persona pesson;


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
public String getSucursal() { 
return sucursal;
}
public void setSucursal(String  sucursal) {
this.sucursal = sucursal;
}
public Persona pesson() { 
return pesson;
}
public void setpesson(Persona  pesson) {
this.pesson = pesson;
}
}
