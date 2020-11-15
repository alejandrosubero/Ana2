package com.alejandro.ana.modelo;

import java.util.List;
import java.util.Objects;
import java.util.Random;

import com.alejandro.ana.notas.AnotacionesJava;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.alejandro.ana.core.Creador;
import com.alejandro.ana.pojos.ArchivoBaseDatosPojo;
import com.alejandro.ana.pojos.AtributoPojo;
import com.alejandro.ana.pojos.EntidadesPojo;
import com.alejandro.ana.pojos.RelacionPojo;

import javax.persistence.*;

@Scope("singleton")
@Component
public class CreacionDeClases {


    private AnotacionesJava anotacionesJava = new AnotacionesJava();
    // private String description;
    private String proyectoName;
    private String packageNames;

    private List<EntidadesPojo> entidades;
    private Creador creador;
    private String barra = "";
    private int relantizar = 300;
    private String databaseName;
    private Boolean nativeMysql;
    private Integer tipoDatabase;

    protected static final Log logger = LogFactory.getLog(CreacionDeClases.class);

    public CreacionDeClases() {
    }

    public void startCreacionDeClases(ArchivoBaseDatosPojo archivo, Creador creadors) {
        this.entidades = archivo.getEntidades();
        this.proyectoName = archivo.getProyectoName();
        this.packageNames = archivo.getPackageNames();
        this.creador = creadors;
        this.barra = creador.getBarra();
        this.databaseName = archivo.getDatabaseName();
        this.nativeMysql = archivo.getNativeMysql();
        this.tipoDatabase = archivo.getTipoDatabase();
        this.anotacionesJava.activateAnotacionesJava(archivo);
    }

    public void createClass2() throws InterruptedException {

        logger.info("inicia la creacion de la clase");
        if (this.entidades.size() > 0) {
            for (EntidadesPojo entidad : this.entidades) {
                StringBuilder sb = new StringBuilder("\r\n");

                this.createaNotaciones(sb);

                logger.info("Inicia la creacion de: " + "Es una entidad: " + entidad.getIsEntity() + "  NOMBRE = "
                        + entidad.getNombreClase());
                logger.info("Inicia la creacion de CreateImport" + "  NOMBRE = " + entidad.getNombreClase());

                this.createImport(entidad, sb);
                logger.info("Inicia la creacion de CreateTitleClass" + "  NOMBRE = " + entidad.getNombreClase());
                Thread.sleep(relantizar);
                this.createTitleClass(entidad.getNombreTabla(), entidad.getNombreClase(), entidad.getIsEntity(), sb);

                logger.info("Inicia la creacion de GenerateAtributosRelaciones" + "  NOMBRE = " + entidad.getNombreClase());
                Thread.sleep(relantizar);
                this.generateAtributosRelaciones(entidad, sb);

                logger.info("Inicia la creacion de GenerateSetterGetter" + "  NOMBRE = " + entidad.getNombreClase());
                Thread.sleep(relantizar);
                this.generateSetterGetter(entidad, sb);

                logger.info("Inicia la creacion de equals Metodo" + "  NOMBRE = " + entidad.getNombreClase());
                Thread.sleep(relantizar);
                this.generateEqualsmetodo(entidad, sb);

                logger.info("Inicia la creacion de CreateFileClass" + "  NOMBRE = " + entidad.getNombreClase());
                Thread.sleep(relantizar);
                this.createFileClass(entidad.getNombreClase(), entidad.getPaquete(), sb);
                Thread.sleep(100);
            }
        }

    }


    private void createaNotaciones(StringBuilder sb) {
        sb.append(this.anotacionesJava.creatNotaClase());
    }

    private void createaNotacionesPie(StringBuilder sb) {
        sb.append(AnotacionesJava.apacheSoftwareLicensed());
    }


    private void createFileClass(String entidad_getNombreClase, String entidad_paquete, StringBuilder sb)
            throws InterruptedException {
        Thread.sleep(relantizar);
        String nameFile = entidad_getNombreClase + ".java";
        sb.append("}\r\n");
        this.createaNotacionesPie(sb);
        String singleString = sb.toString();
        String direction = creador.getDireccionDeCarpeta() + proyectoName + barra + "src" + barra + "main" + barra
                + "java" + barra + creador.getCom() + barra + creador.getPackageNames1() + barra + creador.getArtifact()
                + barra + entidad_paquete;
        creador.crearArchivo(direction, singleString, nameFile);
        logger.info("Finalizo la creacion de CreateFileClass" + "  NOMBRE = " + entidad_getNombreClase);
    }


    private void createTitleClass(String entidad_getNombreTabla, String entidad_getNombreClase,
                                  Boolean entidad_getIsEntity, StringBuilder sb) throws InterruptedException {

        Random rand = new Random();
        Thread.sleep(100);
        if (entidad_getIsEntity) {
            sb.append("\r\n");
            sb.append("\r\n");
            sb.append("@Entity\r\n");
            sb.append("@Table(name = \"" + entidad_getNombreTabla + "\")\r\n");
            sb.append("public class " + entidad_getNombreClase + " implements Serializable {\r\n");
            long rand_lub1 = rand.nextLong();
            sb.append("\r\n");
            sb.append("private static final long serialVersionUID = " + rand_lub1 + "L;" + "\r\n");
            sb.append("\r\n");
        } else {
            sb.append("\r\n");
            sb.append("\r\n");
            sb.append("public class " + entidad_getNombreClase + " implements Serializable {\r\n");
            long rand_lub2 = rand.nextLong();
            sb.append("\r\n");
            sb.append("private static final long serialVersionUID = " + rand_lub2 + "L;" + "\r\n");
            sb.append("\r\n");
        }
        logger.info("Finalizo la creacion de CreateTitleClass" + "  NOMBRE = " + entidad_getNombreClase);
    }


    private void createImport(EntidadesPojo entidad, StringBuilder sb) {
        sb.append("package " + packageNames + "." + entidad.getPaquete() + ";\r\n"); // nombre del paquete hay
        sb.append("\r\n");
        sb.append("import javax.persistence.*;\r\n");
        sb.append("import java.io.Serializable;\r\n");
        sb.append("import java.util.ArrayList;\r\n");
        sb.append("import java.util.List;\r\n");
        sb.append("import java.util.Objects;\n" + "\r\n");
        sb.append("import java.util.Date;" + "\r\n");
        sb.append("\r\n");
        // sb.append("import java.util.Date;\r\n");

        if (entidad.getRelaciones().size() > 0) {
            List<RelacionPojo> relacionesImport = entidad.getRelaciones();
            for (RelacionPojo relacion : relacionesImport) {
                sb.append("import " + packageNames + "." + entidad.getPaquete() + "."
                        + relacion.getNameClassRelacion()
                        + ";\r\n");
            }
        }
        logger.info("Finalizo la creacion de CreateImport" + "  NOMBRE = " + entidad.getNombreClase());
    }


    private void generateAtributosRelaciones(EntidadesPojo entidad, StringBuilder sb) throws InterruptedException {
        Boolean isEntity = entidad.getIsEntity();
        String nombreEntidad = entidad.getNombreClase();
        if (entidad.getAtributos().size() > 0) {
            for (AtributoPojo atributo : entidad.getAtributos()) {
                this.generateAtributo(atributo, isEntity, sb, nombreEntidad);
            }
        }
        if (entidad.getRelaciones().size() > 0) {
            for (RelacionPojo relacion : entidad.getRelaciones()) {
                this.generateRelacion(relacion, isEntity, sb);
            }
        }
        logger.info("Finalizo la creacion de GenerateAtributosRelaciones" + "  NOMBRE = " + entidad.getNombreClase());
    }

    private void generateAtributo(AtributoPojo atributo, Boolean entidad_getIsEntity, StringBuilder sb, String nombreEntidad) throws InterruptedException {

        logger.info("Inicia la creacion de GenerateAtributos" + "  NOMBRE = " + atributo.getAtributoName());
        Thread.sleep(100);
        if (entidad_getIsEntity) {
            // logger.info("PASO #  1 " + "  NOMBRE = " + atributo.getAtributoName());
            if (atributo.getsId()) {
                sb.append("		@Id\r\n");
                // logger.info("PASO #  2 " + "  NOMBRE = " + atributo.getAtributoName());
                if (atributo.getSequenceGenerator()) {
                    atributo.setGeneratedValue(false);
                    // 	logger.info("PASO #  3.1.1 " + "  NOMBRE = " + atributo.getAtributoName());
                    sb.append("		@GeneratedValue(generator = \"sequence_" + atributo.getNameSequenceTable() + "_generator\")" + "\r\n");
                    sb.append("		@SequenceGenerator(name=\"sequence_" + atributo.getNameSequenceTable() + "_generator\", " + "initialValue" +
                            "= " + atributo.getInitialValue() + ", " + "allocationSize=" + atributo.getAllocationSize() + ")" + "\r\n");
                    // sb.append("\r\n");
                }

                if (atributo.getTipoGenerador()) {
                    // 	logger.info("PASO # tabla generador de sequencia  3.1.2 " + "  NOMBRE = " + atributo.getAtributoName());
                    atributo.setGeneratedValue(false);
                    atributo.setSequenceGenerator(false);
                    String force2 = "		@TableGenerator(name = \"" + atributo.getNameSequenceTable() + "\", " +
                            "table = \"" + databaseName + "." + creador.getArtifact() + "_SEQUENCE\", " +
                            "pkColumnName = \"SEQUENCE_NAME\", " +
                            "valueColumnName = \"SEQUENCE_COUNT\", " +
                            "pkColumnValue = \t\"P" + nombreEntidad + "\", " +
                            "initialValue = " + atributo.getInitialValue() + ", allocationSize " +
                            "= " + atributo.getAllocationSize() + ")\n" +
                            "\t@GeneratedValue(strategy = GenerationType.TABLE, " +
                            "generator = \"" + atributo.getNameSequenceTable() + "\")\n" +
                            "\t";
                    sb.append(force2 + "\r\n");
                }

                if (atributo.getGeneratedValue()) {
                    //	logger.info("PASO #  3.2 " + "  NOMBRE = " + atributo.getAtributoName());

                    if (tipoDatabase == 1 && nativeMysql) {
                        //		logger.info("PASO #  3.2.1 " + "  NOMBRE = " + atributo.getAtributoName());
                        sb.append("		@GeneratedValue(strategy = GenerationType." + atributo.getTipoGeneratedValor() + ", generator = \"native\" )" + "\r\n");
                        sb.append("		@GenericGenerator(name = \"native\", strategy = \"native\")" + "\r\n");
                    } else {
                        //		logger.info("PASO #  3.2.2 " + "  NOMBRE = " + atributo.getAtributoName());
                        sb.append("		@GeneratedValue(strategy = GenerationType." + atributo.getTipoGeneratedValor() + ")" + "\r\n");
                        //		logger.info("PASO #  3.2.2  GENERO VALOR" + "  NOMBRE = " + atributo.getAtributoName());
                    }
                }
                //	logger.info("PASO #  FIN DE ID" + "  NOMBRE = " + atributo.getAtributoName());
            } // fin de isID

            if (atributo.getTransiente()) {
                //		logger.info("PASO #  4 es trasiente" + "  NOMBRE = " + atributo.getAtributoName());
                sb.append("  @Transient" + "\r\n");
            }
            //	logger.info("PASO #  6 COLUMN" + "  NOMBRE = " + atributo.getAtributoName());
            sb.append("		@Column(name = \"" + atributo.getNameColum() + "\", " +
                    "updatable = " + atributo.getAtributoUpdatable() + ", " +
                    "nullable = " + atributo.getAtributoNullable() + ", length = " + atributo.getLength() + ")\r\n");
            //	logger.info("PASO #  7 ATRIBUTO" + "  NOMBRE = " + atributo.getAtributoName());

            sb.append("		private " + atributo.getTipoDato() + " " + atributo.getAtributoName() + ";\r\n");
            sb.append("\r\n");
            sb.append("\r\n");
            //	logger.info("PASO #  FIN Entidad " + "  NOMBRE = " + atributo.getAtributoName());
        } else {
            //	logger.info("PASO #  8 POJO " + "  NOMBRE = " + atributo.getAtributoName());
            // sb.append(atributoss.get(j).getNameColum() + "\")\r\n");
            sb.append("		private " + atributo.getTipoDato() + " " + atributo.getAtributoName() + ";\r\n");
            sb.append("\r\n");
        }
        logger.info("Finalizo la creacion de GenerateAtributo" + "  NOMBRE = " + atributo.getAtributoName());
    }


    private void generateRelacion(RelacionPojo relacion, Boolean entidad_getIsEntity, StringBuilder sb) throws InterruptedException {

        logger.info("Inicia la creacion de GenerateRelaciones" + "  NOMBRE = " + relacion.getNameRelacion());
        Thread.sleep(100);
        if (entidad_getIsEntity) {
            //	logger.info("Inicia la creacion de GenerateRelaciones " + entidad_getIsEntity);
            //como establecer el dueño de la relacion para el mapeby
            if (relacion.getBidireccional()) {//============================================================================================

                if (relacion.getMappedByRelacion()) {
                    if (relacion.getRelation().equals("ManyToMany")) {
                        sb.append(" @JsonIgnore" + "\r\n");
                        sb.append("		@" + relacion.getRelation() + "(cascade = CascadeType." + relacion.getCascadeType());
                        sb.append(", fetch = FetchType.LAZY");
                        sb.append(", mappedBy = \"" + relacion.getMappedBy() + "\"");
                        sb.append(", orphanRemoval = " + relacion.getOrphanRemoval() + ")" + "\r\n");
                        sb.append("\r\n");
                    }
                } else {
                    if (relacion.getRelation().equals("ManyToMany")) {
                        sb.append("		@" + relacion.getRelation() + "(cascade = CascadeType." + relacion.getCascadeType());
                        sb.append(", fetch = FetchType.LAZY");
                        sb.append(", orphanRemoval = " + relacion.getOrphanRemoval() + ")" + "\r\n");
                        sb.append("\r\n");
                    }
                }
                if (relacion.getRelation().equals("ManyToOne")) {
                    sb.append(" @JsonIgnore" + "\r\n");
                    sb.append("		@" + relacion.getRelation() + "(cascade = CascadeType." + relacion.getCascadeType());
                    sb.append(", fetch = FetchType.EAGER");
                    sb.append(", orphanRemoval = " + relacion.getOrphanRemoval() + ")" + "\r\n");
                    sb.append("\r\n");
                }
            }
//			sb.append("		@" + relacion.getRelation() + "(cascade = CascadeType." + relacion.getCascadeType());
//			sb.append(", fetch = " + relacion.getFetchTypes());
//			sb.append( ", orphanRemoval = "+relacion.getOrphanRemoval()+")" + "\r\n");
//			sb.append("\r\n");

            if (relacion.getRelation().equals("OneToOne") || relacion.getRelation().equals("ManyToOne")) {
                sb.append("		@" + relacion.getRelation() + "(cascade = CascadeType." + relacion.getCascadeType());
                sb.append(", fetch = FetchType.EAGER");
                sb.append(" )" + "\r\n");
                sb.append("\r\n");
            }

            if (relacion.getRelation().equals("OneToMany") || relacion.getRelation().equals("ManyToMany")) {
                sb.append("		@" + relacion.getRelation() + "(cascade = CascadeType." + relacion.getCascadeType());
                sb.append(", fetch = FetchType.LAZY");
                sb.append(", orphanRemoval = " + relacion.getOrphanRemoval() + ")" + "\r\n");
                sb.append("\r\n");
            }

            if (relacion.getIsJoinTable()) {
                logger.info("Inicia la creacion de GenerateRelaciones" + relacion.getIsJoinTable());
                //	sb.append("@ManyToMany(fetch = FetchType.LAZY)");
                if (relacion.getJointabaleTipo()) {
                    String force = "		@JoinTable(name = \"" + relacion.getJoinTableName() + "\", " +
                            "joinColumns = @JoinColumn(name= \"" + relacion.getJoinColumnName() + "\"), " +
                            "inverseJoinColumns = @JoinColumn(name=\"" + relacion.getJoinColumnName2() + "\"))";

                    sb.append(force);
                } else {
                    String force1 = "		@JoinTable(name=\"" + relacion.getJoinTableName() + "\",  " +
                            "joinColumns = @JoinColumn(name= \"" + relacion.getJoinColumnName() + "_id\",  " +
                            "referencedColumnName=\"" + relacion.getJoinColumnNameReferencedColumnName() + "\"),  " +
                            "inverseJoinColumns = @JoinColumn(name= \"" + relacion.getJoinColumnName2() + "\"))\n";
                    sb.append(force1);
                }
            }
            // si se escoje joinColumn para colocar en la relacion.
            if (relacion.getJoinColumn()) {
                logger.info("Inicia la creacion de GenerateRelaciones" + relacion.getJoinColumnName());
                sb.append("		@JoinColumn(name = \"id_" + relacion.getJoinColumnName() + "\")");
                sb.append("\r\n");
            }

            if (relacion.getRelation().equals("OneToOne") || relacion.getRelation().equals("ManyToOne")) {
                sb.append("		private " + relacion.getNameClassRelacion() + " " + relacion.getNameRelacion() + ";");
                sb.append("\r\n");
            } else {
                sb.append("		private   List<" + relacion.getNameClassRelacion() + ">" + relacion.getNameRelacion() + "= new ArrayList<>();");
            }
        } else {

            if (relacion.getRelation().equals("OneToOne") || relacion.getRelation().equals("ManyToOne")) {
                sb.append("		private " + relacion.getNameClassRelacion() + " " + relacion.getNameRelacion() + ";");
                sb.append("\r\n");
            } else {
                sb.append("		private   List<" + relacion.getNameClassRelacion() + ">" + relacion.getNameRelacion() + "= new ArrayList<>();");
                sb.append("\r\n");
            }
        }
        sb.append("\r\n");
        sb.append("\r\n");
        logger.info("Finalizo la creacion de GenerateRelaciones" + "  NOMBRE = " + relacion.getNameRelacion());
    }

    private void generateSetterGetter(EntidadesPojo entidad, StringBuilder sb) throws InterruptedException {
        if (entidad.getAtributos().size() > 0) {
            for (AtributoPojo atributo : entidad.getAtributos()) {
                this.setterGetterAtributo(atributo, sb);
            }
        }

        if (entidad.getRelaciones().size() > 0) {
            for (RelacionPojo relacions : entidad.getRelaciones()) {
                this.setterGetterRelacion(relacions, sb);
            }
        }
        logger.info("Finalizo la creacion de GenerateSetterGetter" + "  NOMBRE = " + entidad.getNombreClase());
    }


    private void setterGetterAtributo(AtributoPojo atributo, StringBuilder sb) {
        logger.info("Inicia la creacion de GenerateSetterGetterAtributo" + "  NOMBRE = " + atributo.getAtributoName());
        String atributoName = atributo.getAtributoName().substring(0, 1).toUpperCase()
                + atributo.getAtributoName().substring(1).toLowerCase();

        sb.append("		public " + atributo.getTipoDato() + " get" + atributoName + "() { \r\n");
        sb.append("			return " + atributo.getAtributoName() + ";\r\n");
        sb.append("		}\r\n");

        sb.append("		public void set" + atributoName + "(" + atributo.getTipoDato() + "  "
                + atributo.getAtributoName() + ") {\r\n");
        sb.append("			this." + atributo.getAtributoName() + " = " + atributo.getAtributoName() + ";\r\n");
        sb.append("		}\r\n");
        logger.info("Finalizo la creacion de GenerateSetterGetterAtributo" + "  NOMBRE = " + atributo.getAtributoName());
    }


    public void setterGetterRelacion(RelacionPojo relacions, StringBuilder sb) {
        logger.info("Inicia la creacion de GenerateSetterGetterRelacion" + "  NOMBRE = " + relacions.getNameRelacion());
        if (relacions.getRelation().equals("OneToOne") || relacions.getRelation().equals("ManyToOne")) {
            sb.append("		public " + relacions.getNameClassRelacion() + " get" + relacions.getNameRelacion() + "() { \r\n");
            sb.append("			return " + relacions.getNameRelacion() + ";\r\n");
            sb.append("		}\r\n");
            sb.append("		public void set" + relacions.getNameRelacion() + "(" + relacions.getNameClassRelacion()
                    + "  " + relacions.getNameRelacion() + ") {\r\n");
            sb.append("			this." + relacions.getNameRelacion() + " = " + relacions.getNameRelacion() + ";\r\n");
            sb.append("		}\r\n");
        } else {
            sb.append("		public List<" + relacions.getNameClassRelacion() + "> get" + relacions.getNameRelacion()
                    + "() { \r\n");
            sb.append("			return " + relacions.getNameRelacion() + ";\r\n");
            sb.append("		}\r\n");
            sb.append("		public void set" + relacions.getNameRelacion() + "( List<" + relacions.getNameClassRelacion()
                    + ">  " + relacions.getNameRelacion() + ") {\r\n");
            sb.append("			this." + relacions.getNameRelacion() + " = " + relacions.getNameRelacion() + ";\r\n");
            sb.append("		}\r\n");
        }
        logger.info("Finalizo la creacion de GenerateSetterGetterRelacion" + "  NOMBRE = " + relacions.getNameRelacion());
    }

    private void generateEqualsmetodo(EntidadesPojo entidad, StringBuilder sb) throws InterruptedException {
        logger.info("Inicia la creacion de GenerateSetterGetterRelacion" + "  NOMBRE = " + entidad.getNombreClase());
        sb.append("			public boolean equals" + entidad.getNombreClase() + "(Object o) {" + "\r\n");
        sb.append("				if (this == o) return true;" + "\r\n");
        sb.append("				if (o == null || getClass() != o.getClass()) return false;" + "\r\n");
        sb.append("					" + entidad.getNombreClase() + " " + entidad.getNombreClase().toLowerCase() + " = (" + entidad.getNombreClase() + ") o;" + "\r\n");
        sb.append("						return ");
        for (int i = 0; i < entidad.getAtributos().size(); i++) {
            sb.append("			Objects.equals(" + entidad.getAtributos().get(i).getAtributoName() + ", " + entidad.getNombreClase().toLowerCase()
                    + "." + entidad.getAtributos().get(i).getAtributoName() + ")");
            if (i < entidad.getAtributos().size() - 1) {
                sb.append(" ||" + "\r\n");
            }
        }
        sb.append(";" + "\r\n");
        sb.append("\r\n");
        sb.append("}");
    }
}


