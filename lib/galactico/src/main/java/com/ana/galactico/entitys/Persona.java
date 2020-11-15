
package com.ana.galactico.entitys;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;



@Entity
@Table(name = "persona")
public class Persona implements Serializable {


@Id
@GeneratedValue(strategy = GenerationType.AUTO)@Column(name ="id")
private Integer id;


@Column(name ="nombre")
private String nombre;


@Column(name ="apellido")
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
