package com.alejandro.ana.pojos;

import java.util.List;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Scope("singleton")
@Component
public class ArchivoBaseDatosPojo {

	private String autor;
	private String user;
	private  String context;
	private String proyectoName;
	private String packageNames;
	private String description;
	private Boolean createCapaPojoForEntitys;

	// para el pomxml
	private Boolean wihtSegurity; //spring segurity o no
	private Boolean dataBase; // true o false
	private Boolean databaseTest; // usar databade test y Database
	private String databaseName; // nombre de base de datos
	private Integer tipoDatabase; // oracle = 2, Mysql = 1, h2 = 3.
    private Boolean nativeMysql; // usar generador nativo de mysql
	private Double javaVersion;
	private List<EntidadesPojo> entidades;


	public ArchivoBaseDatosPojo() {	}

	public String getProyectoName() {
		return proyectoName;
	}

	public void setProyectoName(String proyectoName) {
		this.proyectoName = proyectoName;
	}

	public String getPackageNames() {
		return packageNames;
	}

	public void setPackageNames(String packageNames) {
		this.packageNames = packageNames;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public List<EntidadesPojo> getEntidades() {
		return entidades;
	}

	public void setEntidades(List<EntidadesPojo> entidades) {
		this.entidades = entidades;
	}

	public Boolean getWihtSegurity() {
		return wihtSegurity;
	}

	public void setWihtSegurity(Boolean wihtSegurity) {
		this.wihtSegurity = wihtSegurity;
	}

	public String getAutor() {
		return autor;
	}

	public void setAutor(String autor) {
		this.autor = autor;
	}

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}


	public Boolean getDataBase() {
		return dataBase;
	}

	public void setDataBase(Boolean dataBase) {
		this.dataBase = dataBase;
	}

	public Integer getTipoDatabase() {
		return tipoDatabase;
	}

	public void setTipoDatabase(Integer tipoDatabase) {
		this.tipoDatabase = tipoDatabase;
	}

	public Double getJavaVersion() {
		return javaVersion;
	}

	public void setJavaVersion(Double javaVersion) {
		this.javaVersion = javaVersion;
	}

	public String getDatabaseName() {
		return databaseName;
	}

	public void setDatabaseName(String databaseName) {
		this.databaseName = databaseName;
	}

    public Boolean getNativeMysql() {
        return nativeMysql;
    }

    public void setNativeMysql(Boolean nativeMysql) {
        this.nativeMysql = nativeMysql;
    }

	public String getContext() {
		return context;
	}

	public void setContext(String context) {
		this.context = context;
	}

	public Boolean getDatabaseTest() {
		return databaseTest;
	}

	public void setDatabaseTest(Boolean databaseTest) {
		this.databaseTest = databaseTest;
	}

	public Boolean getCreateCapaPojoForEntitys() {
		return createCapaPojoForEntitys;
	}

	public void setCreateCapaPojoForEntitys(Boolean createCapaPojoForEntitys) {
		this.createCapaPojoForEntitys = createCapaPojoForEntitys;
	}
}
