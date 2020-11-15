package com.alejandro.ana.pojos;

public class AtributoPojo {

	private Boolean sId; //el dato es tipo id o no lo es true / false
	private String idName = "id";
	
	private String modificadorExtra; // static, final 
	private Boolean ismodificadorExtra;
	
	private String tipoModificador; // private, public, protected 
	private String tipoDato; // Integer, Long, Double, String, Chat, Byte
	private String atributoName;
	private String nameColum;
	private Boolean atributoUpdatable;
	private Boolean atributoNullable;
	private Long length;

	private Boolean generatedValue; // 
	private String tipoGeneratedValor; // strategy = GenerationType.AUTO, GenerationType.SEQUENCE
	private Boolean sequenceGenerator; // este es mas profundo de usar
	private Boolean tipoGenerador; // tipo de generador de sequencia
	private String sequenseName;
	private String nameSequenceTable;
	private Integer initialValue;
	private  Integer allocationSize;
	private Boolean transiente; // @Transient

	// private Boolean addSizeCheck;// @Size(min = 3, max = 255)
    // private Integer minSizeCheck;
    // private Integer maxSizeCheck;


	public AtributoPojo() {	}

	public Boolean getsId() {
		return sId;
	}

	public void setsId(Boolean sId) {
		this.sId = sId;
	}

	public Boolean getTipoGenerador() {
		return tipoGenerador;
	}

	public void setTipoGenerador(Boolean tipoGenerador) {
		this.tipoGenerador = tipoGenerador;
	}

	public String getIdName() {
		return idName;
	}

	public void setIdName(String idName) {
		this.idName = idName;
	}

	public String getModificadorExtra() {
		return modificadorExtra;
	}

	public void setModificadorExtra(String modificadorExtra) {
		this.modificadorExtra = modificadorExtra;
	}

	public Boolean getIsmodificadorExtra() {
		return ismodificadorExtra;
	}

	public void setIsmodificadorExtra(Boolean ismodificadorExtra) {
		this.ismodificadorExtra = ismodificadorExtra;
	}

	public String getTipoModificador() {
		return tipoModificador;
	}

	public void setTipoModificador(String tipoModificador) {
		this.tipoModificador = tipoModificador;
	}

	public String getTipoDato() {
		return tipoDato;
	}

	public void setTipoDato(String tipoDato) {
		this.tipoDato = tipoDato;
	}

	public String getAtributoName() {
		return atributoName;
	}

	public void setAtributoName(String atributoName) {
		this.atributoName = atributoName;
	}

	public String getNameColum() {
		return nameColum;
	}

	public void setNameColum(String nameColum) {
		this.nameColum = nameColum;
	}

	public Boolean getGeneratedValue() {
		return generatedValue;
	}

	public void setGeneratedValue(Boolean generatedValue) {
		this.generatedValue = generatedValue;
	}

	public String getTipoGeneratedValor() {
		return tipoGeneratedValor;
	}

	public void setTipoGeneratedValor(String tipoGeneratedValor) {
		this.tipoGeneratedValor = tipoGeneratedValor;
	}

	public Boolean getSequenceGenerator() {
		return sequenceGenerator;
	}

	public void setSequenceGenerator(Boolean sequenceGenerator) {
		this.sequenceGenerator = sequenceGenerator;
	}

	public Long getLength() {
		return length;
	}

	public void setLength(Long length) {
		this.length = length;
	}

	public String getSequenseName() {
		return sequenseName;
	}

	public void setSequenseName(String sequenseName) {
		this.sequenseName = sequenseName;
	}

	public String getNameSequenceTable() {
		return nameSequenceTable;
	}

	public void setNameSequenceTable(String nameSequenceTable) {
		this.nameSequenceTable = nameSequenceTable;
	}

	public Integer getInitialValue() {
		return initialValue;
	}

	public void setInitialValue(Integer initialValue) {
		this.initialValue = initialValue;
	}

	public Integer getAllocationSize() {
		return allocationSize;
	}

	public void setAllocationSize(Integer allocationSize) {
		this.allocationSize = allocationSize;
	}

	public Boolean getAtributoUpdatable() {
		return atributoUpdatable;
	}

	public void setAtributoUpdatable(Boolean atributoUpdatable) {
		this.atributoUpdatable = atributoUpdatable;
	}

	public Boolean getAtributoNullable() {
		return atributoNullable;
	}

	public void setAtributoNullable(Boolean atributoNullable) {
		this.atributoNullable = atributoNullable;
	}

	public Boolean getTransiente() {
		return transiente;
	}

	public void setTransiente(Boolean transiente) {
		this.transiente = transiente;
	}
}




//	public Boolean getAddSizeCheck() {
//		return addSizeCheck;
//	}
//
//	public void setAddSizeCheck(Boolean addSizeCheck) {
//		this.addSizeCheck = addSizeCheck;
//	}
//
//	public Integer getMinSizeCheck() {
//		return minSizeCheck;
//	}
//
//	public void setMinSizeCheck(Integer minSizeCheck) {
//		this.minSizeCheck = minSizeCheck;
//	}
//
//	public Integer getMaxSizeCheck() {
//		return maxSizeCheck;
//	}
//
//	public void setMaxSizeCheck(Integer maxSizeCheck) {
//		this.maxSizeCheck = maxSizeCheck;
//	}

//					sbx.append("\r\n");
//					sbx.append("\r\n");
//					sbx.append("		@Override" + "\r\n");
//					sbx.append("		public List<" + getNombreClase + "> findBy" + relacion.getNameClassRelacion() + "Containing(" + relacion.getNameClassRelacion() + " " + relacion.getNameRelacion() + "){" + "\r\n");
//					// sbx.append("				logger.info(\"metodo: metodContainingRelacion NEW \");" + "\r\n");// comentarriooooooooooooooooooooooooooooo
//					sbx.append("			logger.info(\"Get allProyect\");" + "\r\n");
//					sbx.append(" 			List<" + getNombreClase + "> lista" + getNombreClase + " = new ArrayList<" + getNombreClase + ">();" + "\r\n");
//					sbx.append("	for (" + getNombreClase + " " + getNombre + " : this.getAll" + getNombreClase + "()) {" + "\r\n");
//
//					// cunado sepa como hacer un metodo generico. picamos ak
//
//					sbx.append("		for (" + relacion.getNameClassRelacion() + " " + relacion.getNameRelacion() + "x : "
//							+ getNombre + ".get" + relacion.getNameRelacion() + "()) { " + "\r\n");
//					sbx.append("				if(" + getNombreClase + ".get" + relacion.getNameRelacion() + "().contains(" + relacion.getNameRelacion() + ".get" + relacion.getNameRelacion() + "())) {	" + "\r\n");
//					sbx.append("					lista" + getNombreClase + ".add(" + getNombre + "x);	" + "\r\n");
//					sbx.append("				}" + "\r\n");
//					sbx.append("	  	 	}" + "\r\n");
//					sbx.append("		}" + "\r\n");
//					sbx.append("					return lista" + getNombreClase + ";	" + "\r\n");
//					sbx.append("\r\n");
//					sbx.append("	}" + "\r\n");