
package com.ana.galactico.entitys;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.ana.galactico.entitys.Persona;


@Entity
@Table(name = "empresa")
public class Empresa implements Serializable {


@Id
@GeneratedValue(strategy = GenerationType.AUTO)@Column(name ="id")
private Integer id;


@Column(name ="nombre")
private String nombre;


@Column(name ="sucursal")
private String sucursal;


@OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
@JoinColumn(name = "id_name")
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
